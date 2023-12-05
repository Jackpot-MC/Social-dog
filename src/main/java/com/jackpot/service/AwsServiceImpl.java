package com.jackpot.service;

import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import com.jackpot.mapper.DogMapper;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
@Log4j
public class AwsServiceImpl implements AwsService{

    @Autowired
    private DogMapper dogMapper;

    @Autowired
    private AmazonS3Client s3;

    private final String bucketName = "sample-social-dog";

//    @Override
//    public void createBucket(String bucketName) {
//        try{
//            //버킷이름 중복되지 않는지 확인
//            if(s3.doesBucketExistV2(bucketName)){
//                System.out.format("Bucket %s는 이미 존재합니다.\n", bucketName);
//            }else{
//                s3.createBucket(bucketName);
//                System.out.format("Bucket %s가 만들어졌습니다.\n", bucketName);
//            }
//        }catch(AmazonS3Exception e){
//            e.printStackTrace();
//        }catch(SdkClientException e){
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void deleteBucket(String bucketName) {
//        try{
//            //버킷 있으면 버킷 삭제
//            if(s3.doesBucketExistV2(bucketName)){
//                //안의 오브젝트 모조리 삭제
//                ObjectListing objectListing = s3.listObjects(bucketName);
//                while(true){
//                    for(Iterator<?> iterator = objectListing.getObjectSummaries().iterator(); iterator.hasNext();){
//                        S3ObjectSummary summary = (S3ObjectSummary) iterator.next();
//                        s3.deleteObject(bucketName, summary.getKey());
//                    }
//
//                    if(objectListing.isTruncated()){
//                        objectListing = s3.listNextBatchOfObjects(objectListing);
//                    }else{
//                        break;
//                    }
//                }
//
//                //abort incomplete multipart uploads
//                MultipartUploadListing multipartUploadListing = s3.listMultipartUploads(new ListMultipartUploadsRequest(bucketName));
//                while(true){
//                    for(Iterator<?> iterator = multipartUploadListing.getMultipartUploads().iterator(); iterator.hasNext();){
//                        MultipartUpload multipartUpload = (MultipartUpload) iterator.next();
//                        s3.abortMultipartUpload(new AbortMultipartUploadRequest(bucketName, multipartUpload.getKey(), multipartUpload.getUploadId()));
//                    }
//
//                    if(multipartUploadListing.isTruncated()){
//                        ListMultipartUploadsRequest listMultipartUploadsRequest = new ListMultipartUploadsRequest(bucketName);
//                        listMultipartUploadsRequest.withUploadIdMarker(multipartUploadListing.getNextUploadIdMarker());
//                        multipartUploadListing = s3.listMultipartUploads(listMultipartUploadsRequest);
//                    } else {
//                        break;
//                    }
//                }
//                s3.deleteBucket(bucketName);
//                System.out.printf("Bucket %s has been deleted \n", bucketName);
//            } else{
//                System.out.printf("Bucket %s does not exist \n", bucketName);
//            }
//        }catch (AmazonS3Exception e){
//            e.printStackTrace();
//        }catch(SdkClientException e){
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public List<Bucket> getBucketList() {
//        try{
//            List<Bucket> buckets = s3.listBuckets();
//            System.out.println("Bucket List : ");
//            for(Bucket bucket : buckets){
//                System.out.println("       name="+bucket.getName() + ", creation_date=" + bucket.getCreationDate() + ", owner="+bucket.getOwner().getId());
//            }
//            return buckets;
//        } catch(AmazonS3Exception e){
//            e.printStackTrace();
//            return null;
//        }catch (SdkClientException e){
//            e.printStackTrace();
//            return null;
//        }
//    }

    @Override
    public void uploadFile(String objectName, String filePath) {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(0L);
        objectMetadata.setContentType("application/x-directory");
        try{
            s3.putObject(bucketName, objectName, new File(filePath));
            System.out.printf("Object %s가 생성되었습니다.\n", objectName);
        }catch (AmazonS3Exception e){
            e.printStackTrace();
        }catch (SdkClientException e){
            e.printStackTrace();
        }
    }

    @Override
    public void uploadFolder(String folderName) {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(0L);
        objectMetadata.setContentType("application/x-directory");
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, folderName, new ByteArrayInputStream(new byte[0]), objectMetadata);
        try{
            s3.putObject(putObjectRequest);
            System.out.printf("Folder %s가 생성되었습니다.\n", folderName);
        }catch (AmazonS3Exception e){
            e.printStackTrace();
        }catch(SdkClientException e){
            e.printStackTrace();
        }

    }

    @Override
    public void uploadMultipart(String objectName, String filePath) {
        File file = new File(filePath);
        long contentLength = file.length();
        long partSize = 10 * 1024 * 1024;

        try{
            //initailize and get upload ID
            InitiateMultipartUploadResult initiateMultipartUploadResult = s3.initiateMultipartUpload(new InitiateMultipartUploadRequest(bucketName, objectName));
            String uploadId = initiateMultipartUploadResult.getUploadId();

            //upload parts
            List<PartETag> partETagList = new ArrayList<PartETag>();

            long fileOffset = 0;
            for(int i = 0; fileOffset < contentLength; i++){
                partSize = Math.min(partSize, (contentLength - fileOffset));

                UploadPartRequest uploadPartRequest = new UploadPartRequest()
                        .withBucketName(bucketName)
                        .withKey(objectName)
                        .withUploadId(uploadId)
                        .withPartNumber(i)
                        .withFile(file)
                        .withFileOffset(fileOffset)
                        .withPartSize(partSize);

                UploadPartResult uploadPartResult = s3.uploadPart(uploadPartRequest);
                partETagList.add(uploadPartResult.getPartETag());

                fileOffset += partSize;
            }

            //멀티파트 삭제
//            s3.abortMultipartUpload(new AbortMultipartUploadRequest(bucketName, objectName, uploadId));

            //complete
            CompleteMultipartUploadResult completeMultipartUploadResult = s3.completeMultipartUpload(new CompleteMultipartUploadRequest(bucketName, objectName, uploadId, partETagList));
        }catch(AmazonS3Exception e){
            e.printStackTrace();
        }catch(SdkClientException e){
            e.printStackTrace();
        }
    }

    @Override
    public void deleteFile(String objectName) {
        try{
            s3.deleteObject(bucketName, objectName);
            System.out.printf("Object %s는 삭제되었습니다.\n"+objectName);
        }catch (AmazonS3Exception e){
            e.printStackTrace();
        }catch(SdkClientException e){
            e.printStackTrace();
        }
    }

    @Override
    public void getFileList() {
        try {
            ListObjectsRequest listObjectsRequest = new ListObjectsRequest()
                    .withBucketName(bucketName)
                    .withMaxKeys(300);

            ObjectListing objectListing = s3.listObjects(listObjectsRequest);

            System.out.println("Object List:");
            while (true) {
                for (S3ObjectSummary objectSummary : objectListing.getObjectSummaries()) {
                    System.out.println("      name=" + objectSummary.getKey() + ", size=" + objectSummary.getSize() + ", owner=" + objectSummary.getOwner().getId());
                }

                if (objectListing.isTruncated()) {
                    objectListing = s3.listNextBatchOfObjects(objectListing);
                } else {
                    break;
                }
            }
        } catch (AmazonS3Exception e) {
            System.err.println(e.getErrorMessage());
            System.exit(1);
        }
    }

    @Override
    public void getFolderList() {
        try {
            ListObjectsRequest listObjectsRequest = new ListObjectsRequest()
                    .withBucketName(bucketName)
                    .withDelimiter("/")
                    .withMaxKeys(300);

            ObjectListing objectListing = s3.listObjects(listObjectsRequest);

            System.out.println("Folder List:");
            for (String commonPrefixes : objectListing.getCommonPrefixes()) {
                System.out.println("     name=" + commonPrefixes);
            }

            System.out.println("File List:");
            for (S3ObjectSummary objectSummary : objectListing.getObjectSummaries()) {
                System.out.println("      name=" + objectSummary.getKey() + ", size=" + objectSummary.getSize() + ", owner=" + objectSummary.getOwner().getId());
            }
        } catch (AmazonS3Exception e) {
            e.printStackTrace();
        } catch (SdkClientException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void copyFolder(String sourceKey, String destinationKey) {
        CopyObjectRequest copyObjectRequest = new CopyObjectRequest()
                .withSourceBucketName(bucketName)
                .withSourceKey(sourceKey)
                .withDestinationBucketName(bucketName)
                .withDestinationKey(destinationKey);

        s3.copyObject(copyObjectRequest);
    }
}

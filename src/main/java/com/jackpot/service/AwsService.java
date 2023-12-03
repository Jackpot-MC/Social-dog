package com.jackpot.service;

public interface AwsService {
    //버킷
//    void createBucket(String bucketName);
//    void deleteBucket(String bucketName);
//    List<Bucket> getBucketList();

    //파일(사진)
    void uploadFile(String objectName, String filePath);
    void uploadFolder(String folderName);
    void uploadMultipart(String objectName, String filePath);
    void deleteFile(String objectName);
    void getFileList();
    void getFolderList();
    void copyFolder(String sourceKey, String destinationKey);

}

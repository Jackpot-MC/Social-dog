package com.jackpot.service;

public interface AwsService {
    //버킷
//    void createBucket(String bucketName);
//    void deleteBucket(String bucketName);
//    List<Bucket> getBucketList();

    //파일(사진)
    void uploadFile(String folderName, String objectName, String filePath);
    void uploadMultipart(String objectName, String filePath);
    void deleteFile(String objectName);
    void getFileList();
    void getFolderList();


}

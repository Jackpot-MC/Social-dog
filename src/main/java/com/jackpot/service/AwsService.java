package com.jackpot.service;

public interface AwsService {

    //파일(사진)
    void uploadFile(String objectName, String filePath);
    void uploadFolder(String folderName);
    void uploadMultipart(String objectName, String filePath);
    void deleteFile(String objectName);
    void getFileList();
    void getFolderList();
    void copyFolder(String sourceKey, String destinationKey);

}

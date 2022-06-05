package com.chetakcargo.imageupload.model;

import java.io.Serializable;

/**
 * Created by Ravinder Birhman on 05,June,2022
 * Country India,
 * State Haryana,
 * Email ravinder.birhman2000@gmail.com
 **/
public class ImageResponseModel implements Serializable {
    private String fileName;
    private String fileDownloadUri;
    private String fileViewUri;
    private String fileType;
    private String size;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileDownloadUri() {
        return fileDownloadUri;
    }

    public void setFileDownloadUri(String fileDownloadUri) {
        this.fileDownloadUri = fileDownloadUri;
    }

    public String getFileViewUri() {
        return fileViewUri;
    }

    public void setFileViewUri(String fileViewUri) {
        this.fileViewUri = fileViewUri;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
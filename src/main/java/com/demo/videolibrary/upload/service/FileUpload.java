package com.demo.videolibrary.upload.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileUpload {
    String upload(MultipartFile file, String dir) throws IOException;
    boolean delete(String file);
}

package com.demo.videolibrary.upload.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@Slf4j
public class FileUploadImpl implements FileUpload {
    @Override
    public String upload(MultipartFile file, String dir) throws IOException {

        if (dir.isEmpty()) {
           dir = "video/";
        }

        try{
            String name = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            Path path = Paths.get("uploads/"+dir, name);
            Files.copy(file.getInputStream(), path);
            return path.toString();
        }
        catch (IOException e){
            log.error("Erreur lors de l'upload de l'image", e.getMessage());
            throw new IOException(e.getMessage());
        }
    }

    @Override
    public boolean delete(String file) {
        //TODO : delete file by path
        return true;
    }
}

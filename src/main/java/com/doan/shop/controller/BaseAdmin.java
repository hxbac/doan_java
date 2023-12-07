package com.doan.shop.controller;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import org.springframework.web.multipart.MultipartFile;

public class BaseAdmin {
    /**
     * Upload file into uploads/
     * @param file
     * @return fileName
     */
    public String uploadFile(MultipartFile file) {
        Path path = Paths.get("uploads/");
        if (!file.isEmpty()) {
            try {
                InputStream inputStream = file.getInputStream();
                long timestampMillis = System.currentTimeMillis();
                Files.copy(
                    inputStream, path.resolve(timestampMillis + file.getOriginalFilename()), 
                    StandardCopyOption.REPLACE_EXISTING
                );
                return "/getImage/" + timestampMillis + file.getOriginalFilename();
            } catch (Exception e) {
                return "";
            }
        }
        return "";
    }
}

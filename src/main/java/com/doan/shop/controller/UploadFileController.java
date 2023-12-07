package com.doan.shop.controller;

// import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;

public class UploadFileController {
    @PostMapping("/upload")
    public ResponseEntity<String> handleFileUpload(@RequestParam("inputImage") MultipartFile file) {
        // Kiểm tra xem file có dữ liệu không
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Chọn một file để upload");
        }

        try {
            // Lấy đường dẫn để lưu trữ file trên server
            String uploadDir = "/path/to/your/upload/directory/";
            
            // Tạo đường dẫn đầy đủ cho file
            String filePath = uploadDir + file.getOriginalFilename();
            
            // Lưu file lên server
            File dest = new File(filePath);
            FileUtils.writeByteArrayToFile(dest, file.getBytes());

            return ResponseEntity.ok("Upload thành công: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Lỗi khi upload file");
        }
    }
}

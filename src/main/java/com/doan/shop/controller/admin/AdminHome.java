package com.doan.shop.controller.admin;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdminHome {

    @GetMapping("/admin/index")
    public String index() {
        return "admin/home/index";
    }
    
    @RequestMapping(value = "/getImage/{photo}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<ByteArrayResource> getImage(@PathVariable("photo") String photo) {
        Path fileName = Paths.get("uploads/", "no-avatar.jpg");
        if (!photo.equals("") || photo != null) {
            fileName = Paths.get("uploads/", photo);
        }
        try {
            byte[] buffer = Files.readAllBytes(fileName);
            ByteArrayResource byteArrayResource = new ByteArrayResource(buffer);

            return ResponseEntity.ok()
                .contentLength(buffer.length)
                .contentType(MediaType
                .parseMediaType("image/jpeg"))
                .body(byteArrayResource);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return ResponseEntity.badRequest().build();
    }
}

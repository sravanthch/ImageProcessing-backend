package com.example.imageprocessing;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000") // Allow Next.js frontend
public class ImageController {

    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping("/process")
    public ResponseEntity<byte[]> processImage(@RequestParam("file") MultipartFile file) {
        try {
            byte[] processedImage = imageService.processImage(file.getBytes());
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_PNG)
                    .body(processedImage);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}

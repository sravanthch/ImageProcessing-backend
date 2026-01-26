package com.example.imageprocessing;

import org.springframework.stereotype.Service;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class ImageService {

    public byte[] processImage(byte[] imageBytes) throws IOException {
        // 1. Save input bytes to a temp file
        Path inputPath = Files.createTempFile("input_", ".png");
        Path outputPath = Files.createTempFile("output_", ".png");

        try {
            Files.write(inputPath, imageBytes);

            // 2. Call Python script
            ProcessBuilder processBuilder = new ProcessBuilder(
                    "python",
                    "process_image.py",
                    inputPath.toString(),
                    outputPath.toString());

            // Set working directory to where the script is (project root/backend)
            processBuilder.directory(new File("."));
            processBuilder.redirectErrorStream(true);

            Process process = processBuilder.start();
            int exitCode = process.waitFor();

            if (exitCode != 0) {
                // Read output for debugging
                String errorOutput = new String(process.getInputStream().readAllBytes());
                throw new IOException("Python script failed: " + errorOutput);
            }

            // 3. Read the output file back into bytes
            return Files.readAllBytes(outputPath);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IOException("Image processing interrupted", e);
        } finally {
            // 4. Cleanup
            Files.deleteIfExists(inputPath);
            Files.deleteIfExists(outputPath);
        }
    }
}

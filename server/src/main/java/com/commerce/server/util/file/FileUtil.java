package com.commerce.server.util.file;

import com.commerce.server.exception.CustomBadRequestException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Component
public class FileUtil {
    @Value("file.upload.dir")
    private String uploadDir;

    public String  saveFile(MultipartFile file){

        if (file == null || file.isEmpty()){
            throw new CustomBadRequestException("File is null or empty");
        }
        try {
            Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath)){
                Files.createDirectories(uploadPath);
            }
            String originalName = file.getOriginalFilename();
            String extension=".tmp";

            if (originalName != null && originalName.contains(".")){
                extension = originalName.substring(originalName.indexOf("."));
            }
            String fileName = UUID.randomUUID() + extension;

            Path path = uploadPath.resolve(fileName);

            file.transferTo(path.toFile());

            return path.toString();

        } catch (IOException exception){
            throw new CustomBadRequestException(exception.getMessage());
        }
    }
}

package br.com.mariaschwinn.service.s3;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3Object;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

@Slf4j
@Component
public class FileContentService {

    @Autowired
    private AmazonS3 amazonS3;

    @Value("${s3.bucket}")
    private String directory;

    public String getContent(String file) {
        try {
            S3Object s3Object = amazonS3.getObject(directory, file);
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(s3Object.getObjectContent(), StandardCharsets.ISO_8859_1))) {
                return reader.lines().collect(Collectors.joining("\n"));
            }
        } catch (AmazonServiceException e) {
            log.error("Error accessing S3: {}", e.getMessage());
            return null;
        } catch (IOException e) {
            log.error("Error reading file content: {}", e.getMessage());
            return null;
        } catch (Exception e) {
            log.error("Unexpected error getting file content: {}", e.getMessage());
            return null;
        }
    }
}

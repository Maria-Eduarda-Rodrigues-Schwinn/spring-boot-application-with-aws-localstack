package br.com.mariaschwinn.service.s3;

import com.amazonaws.services.s3.AmazonS3;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class FileExistenceService {

    @Autowired
    private AmazonS3 amazonS3;

    @Value("${s3.bucket}")
    private String directory;

    public boolean exists(String file) {
        try {
            return amazonS3.doesObjectExist(directory, file);
        } catch (Exception e) {
            log.error("Error checking if file exists: {}", e.getMessage());
            return false;
        }
    }
}

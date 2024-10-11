package br.com.mariaschwinn.service.s3;

import br.com.mariaschwinn.exception.FileListenerException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Slf4j
@Component
public class FileUploadService {

    @Autowired
    private AmazonS3 amazonS3;

    @Value("${s3.bucket}")
    private String directory;

    public void upload(InputStream from, String to) throws FileListenerException {
        try {
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(from.available());
            metadata.setContentType("text/plain; charset=ISO-8859-1");

            amazonS3.putObject(new PutObjectRequest(directory, to, from, metadata));
        } catch (IOException e) {
            log.error("IO error: {}", e.getMessage(), e);
            throw new FileListenerException(e.getMessage(), e);
        } catch (AmazonServiceException e) {
            log.error("Error uploading to S3: {}", e.getMessage(), e);
            throw new FileListenerException(e.getMessage(), e);
        }
    }
}

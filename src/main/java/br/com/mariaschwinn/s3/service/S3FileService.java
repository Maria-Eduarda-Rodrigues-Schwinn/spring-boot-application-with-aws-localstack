package br.com.mariaschwinn.s3.service;

import br.com.mariaschwinn.exception.FileListenerException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import io.awspring.cloud.core.io.s3.PathMatchingSimpleStorageResourcePatternResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class S3FileService {

    @Value("${s3.bucket}")
    private String directory;

    @Autowired
    private AmazonS3 amazonS3;

    @Autowired
    private ResourceLoader resourceLoader;

    private ResourcePatternResolver resourcePatternResolver;

    @Autowired
    public void setupResolver(ApplicationContext applicationContext, AmazonS3 amazonS3) {

        this.resourcePatternResolver =
                new PathMatchingSimpleStorageResourcePatternResolver(amazonS3, applicationContext);
    }

    public boolean isFileExists(String file) {
        try {

            return amazonS3.doesObjectExist(directory, file);

        } catch (Exception e) {
            log.error("Error checking if file exists: {}", e.getMessage());
            return false;
        }
    }


    public void saveFile(InputStream from, String to) throws FileListenerException {
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


    public List<Resource> searchFile(String name, boolean exact) {

        Resource[] resources = null;

        try {
            if (exact)
                resources = this.resourcePatternResolver.getResources(String.format("s3://%s/%s", directory, name));
            else
                resources = this.resourcePatternResolver.getResources(String.format("s3://%s/%s*.*", directory, name));

        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return Arrays.stream(resources)
                .sorted(Comparator
                        .comparing(Resource::getFilename))
                .collect(Collectors.toList());
    }

    public String contentFile(String file) {
        try {
            S3Object s3Object = amazonS3.getObject(directory, file);

            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(s3Object.getObjectContent(), StandardCharsets.ISO_8859_1))) {
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

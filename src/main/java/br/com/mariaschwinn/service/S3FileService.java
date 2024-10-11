package br.com.mariaschwinn.service;

import br.com.mariaschwinn.exception.FileListenerException;
import br.com.mariaschwinn.service.s3.FileContentService;
import br.com.mariaschwinn.service.s3.FileExistenceService;
import br.com.mariaschwinn.service.s3.FileSearchService;
import br.com.mariaschwinn.service.s3.FileUploadService;
import com.amazonaws.services.s3.AmazonS3;
import io.awspring.cloud.core.io.s3.PathMatchingSimpleStorageResourcePatternResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;

@Slf4j
@Service
public class S3FileService {

    private ResourcePatternResolver resourcePatternResolver;

    @Autowired
    public void setupResolver(ApplicationContext applicationContext, AmazonS3 amazonS3) {
        this.resourcePatternResolver = new PathMatchingSimpleStorageResourcePatternResolver(amazonS3, applicationContext);
    }

    @Autowired
    private FileExistenceService fileExistenceService;

    @Autowired
    private FileUploadService fileUploadService;

    @Autowired
    private FileSearchService fileSearchService;

    @Autowired
    private FileContentService fileContentService;

    public boolean isFileExists(String file) {
        return fileExistenceService.exists(file);
    }

    public void saveFile(InputStream from, String to) throws FileListenerException {
        fileUploadService.upload(from, to);
    }

    public List<Resource> searchFile(String name, boolean exact) {
        return fileSearchService.search(name, exact);
    }

    public String contentFile(String file) {
        return fileContentService.getContent(file);
    }
}

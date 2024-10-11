package br.com.mariaschwinn.service.s3;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class FileSearchService {

    @Value("${s3.bucket}")
    private String directory;

    @Autowired
    private ResourcePatternResolver resourcePatternResolver;

    public List<Resource> search(String name, boolean exact) {
        Resource[] resources = null;
        try {
            String pattern = exact ? String.format("s3://%s/%s", directory, name)
                    : String.format("s3://%s/%s*.*", directory, name);
            resources = this.resourcePatternResolver.getResources(pattern);
        } catch (Exception e) {
            log.error("Error searching for files: {}", e.getMessage());
        }

        return Arrays.stream(resources)
                .sorted(Comparator.comparing(Resource::getFilename))
                .collect(Collectors.toList());
    }
}

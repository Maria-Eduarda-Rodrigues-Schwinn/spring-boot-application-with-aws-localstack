package br.com.mariaschwinn.controller;

import br.com.mariaschwinn.dto.S3FileDTO;
import br.com.mariaschwinn.service.S3FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Controller
@RequestMapping("/")
public class S3Controller {

    private static final String FILE_NOT_EXISTS = "Arquivo não existe";

    @Autowired
    private S3FileService fileService;

    @PostMapping("/s3/files")
    public ResponseEntity<S3FileDTO> createFile() {

        Path tempFile;

        try {
            tempFile = Files.createTempFile("prefixo-", ".txt");
            String content = "Conteúdo do Arquivo: " + UUID.randomUUID();
            Files.writeString(tempFile, content, StandardCharsets.ISO_8859_1, StandardOpenOption.APPEND);

            fileService.saveFile(Files.newInputStream(tempFile), tempFile.toFile().getName());

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(S3FileDTO.getInstance(e.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.ok(S3FileDTO.getInstance(tempFile.toFile().getName(), null));
    }

    @GetMapping("/s3/files")
    public ResponseEntity<List<S3FileDTO>> listFiles(@RequestParam("fileName") String fileName) {

        List<S3FileDTO> s3Files = new ArrayList<>();

        try {
            List<Resource> resources = fileService.searchFile(fileName, false);

            if (resources.isEmpty()) {
                s3Files.add(S3FileDTO.getInstance(FILE_NOT_EXISTS, null));
                return new ResponseEntity<>(s3Files, HttpStatus.NOT_FOUND);
            }

            for (Resource resource : resources)
                s3Files.add(S3FileDTO.getInstance(resource.getFilename(), null));

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(s3Files, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.ok(s3Files);
    }

    @GetMapping("/s3/files/{file}")
    public ResponseEntity<S3FileDTO> contentFile(@PathVariable("file") String file) {

        if (fileService.isFileExists(file)) {
            List<Resource> resources = fileService.searchFile(file, true);

            if (resources.isEmpty()) {
                return new ResponseEntity<>(S3FileDTO.getInstance(FILE_NOT_EXISTS, null), HttpStatus.NOT_FOUND);
            }

            Resource resource = resources.get(0);

            String content = fileService.contentFile(resource.getFilename());

            return ResponseEntity.ok(S3FileDTO.getInstance(
                    resource.getFilename(),
                    content));
        } else {
            return new ResponseEntity<>(S3FileDTO.getInstance(FILE_NOT_EXISTS, null), HttpStatus.NOT_FOUND);
        }
    }
}

package br.com.mariaschwinn.s3.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class S3FileDTO {

    private String fileName;
    private String content;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String getContent() {
        return content;
    }

    public static S3FileDTO getInstance(String fileName, String content) {
        S3FileDTO s3File = new S3FileDTO();
        s3File.setFileName(fileName);
        s3File.setContent(content);
        return s3File;
    }
}

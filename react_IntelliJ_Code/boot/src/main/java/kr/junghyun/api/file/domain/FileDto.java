package kr.junghyun.api.file.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class FileDto {

    private long seqf;
    private String saveFilename;
    private String orginalFilname;
    private long fsize;
}

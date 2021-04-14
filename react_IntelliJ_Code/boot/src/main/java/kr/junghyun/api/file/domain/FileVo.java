package kr.junghyun.api.file.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "files")
public class FileVo {

    @Id

    @Column(name = "seqf")
    private long seqf;
    @Column(name = "save_filename")
    private String saveFilename;
    @Column(name = "original_filename")
    private String orginalFilname;
    @Column(name = "fsize")
    private long fsize;


}

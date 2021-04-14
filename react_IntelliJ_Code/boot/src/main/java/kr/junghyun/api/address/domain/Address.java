package kr.junghyun.api.address.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "addresses")
public class Address {
    @Id
    @GeneratedValue

    @Column(name = "seq")
    private long seq;
    @Column(name = "name")
    private String name;
    @Column(name = "addr")
    private String addr;
    @Column(name = "rdate")
    private String rdate;
}

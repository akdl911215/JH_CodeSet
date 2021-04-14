package kr.junghyun.api.address.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@Component
public class AddressDto {

    private long seq;
    private String name;
    private String addr;
    private String rdate;
}

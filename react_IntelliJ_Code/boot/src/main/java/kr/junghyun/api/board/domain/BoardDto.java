package kr.junghyun.api.board.domain;

import lombok.Data;
import org.springframework.stereotype.Component;


@Data
@Component
public class BoardDto {

    private long boardId;
    private String title;

}

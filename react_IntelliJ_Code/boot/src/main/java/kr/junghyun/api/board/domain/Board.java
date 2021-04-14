package kr.junghyun.api.board.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "boards")
public class Board {
    @Id
    @GeneratedValue

    @Column(name = "board_id")
    private long boardId;
    @Column(name = "title")
    private String title;
}

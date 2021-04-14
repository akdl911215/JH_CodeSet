package jung.uss.domain;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Board {

	private long seq;
	private String writer;
	private String email;
	private String subject;
	private String content;
	private Date rdate;
	private String fname;
	private String ofname;
	private String fsize;
}

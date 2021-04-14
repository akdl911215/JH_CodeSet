package jung.md.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor // 디폴트 파라미터 생성자 생성
@AllArgsConstructor // get, set 까지 다만들어주는 생성자
@Data // Dto 객체는 @Data
public class Human {
	private String name;
	private int age;
}

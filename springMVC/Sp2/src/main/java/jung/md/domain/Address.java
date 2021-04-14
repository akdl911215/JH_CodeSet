package jung.md.domain;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {
	private long seq;
	private String name;
	private String addr;
	private Date rdate;
	
	private AddressFile addressFile;
}

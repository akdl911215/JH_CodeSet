package jung.md.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressFile {
	private long seqf;
	private String ofname;
	private String sfname;
	private long fsize;
	private long seq;
}

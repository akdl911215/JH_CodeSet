package jung.md.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import jung.md.domain.Address;
import jung.md.mapper.AddressMapper;
import lombok.extern.log4j.Log4j;

@Log4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class AddressMapperTests {
	@Autowired
	private AddressMapper addressMapper;
	/*
	@Test
	public void testList() {
		log.info("#AddressMapperTests addressMapper: " + addressMapper);
		log.info("#AddressMapperTests testList() : " + addressMapper.list());
	};
	*/
	/*
	@Test
	public void testInsert() {
		Address address = new Address(-1, "힘든", "셋팅", null);
		addressMapper.insert(address);
		log.info("#AddressMapperTests insert() 수행완료");
	};
	*/
	@Test
	public void testDelete() {
		long seq = 6L;
		log.info("#실행된 seq = " + seq);
		addressMapper.delete(seq);
		log.info("#AddressMapperTests delete() 수행완료");
	};
}

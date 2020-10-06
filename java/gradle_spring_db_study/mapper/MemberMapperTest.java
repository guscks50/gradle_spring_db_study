package gradle_spring_db_study.mapper;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import gradle_spring_db_study.config.AppCtx;
import gradle_spring_db_study.spring.Member;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppCtx.class} )
public class MemberMapperTest {
	private static final Log log = LogFactory.getLog(MemberMapperTest.class);
	
	@Autowired
	private MemberMapper mapper;
	
	@Test
	public void testSelectAll() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName()+"()");
		List<Member> list = mapper.selectAll();
		Assert.notNull(list,"맴버 목록");
		list.stream().forEach(System.out::println);
	}
	
	@Test
	public void testInsert() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName()+"()");
		Member member = new Member("aaa@aaa.co.kr", "1234", "aaaa", LocalDateTime.now());
		int res = mapper.insert(member);
		Assert.isTrue(res==1,"같음");
	}


}

package gradle_spring_db_study;

import java.time.LocalDateTime;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.IncorrectResultSizeDataAccessException;

import gradle_spring_db_study.config.AppCtx;
import gradle_spring_db_study.spring.ChangePaswordService;
import gradle_spring_db_study.spring.Member;
import gradle_spring_db_study.spring.MemberDao;
import gradle_spring_db_study.spring.MemberNotFoundException;
import gradle_spring_db_study.spring.WrongldPasswordException;

public class MainForCPS {

	public static void main(String[] args) {
		try(AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtx.class);){
			MemberDao memberDao = ctx.getBean(MemberDao.class);
			memberDao.insert(new Member("test@test.co.kr","1234","test",LocalDateTime.now()));
			System.out.println("회원을 추가했습니다. \n");
			
			ChangePaswordService cps= ctx.getBean(ChangePaswordService.class);
			cps.changePassword("test@test.co.kr", "1234", "new1234");
			System.out.println("암호를 변경했습니다. \n");
			
			Member member = memberDao.selectByEmail("test@test.co.kr");
			memberDao.delete(member);
			System.out.println("회원을 삭제했습니다.\n");
		}catch (IncorrectResultSizeDataAccessException e) {
			throw new MemberNotFoundException(e);
		}catch (WrongldPasswordException e) {
			System.err.println("이메일과 암호가 일치하지 않습니다.\n");
		}
	}

}

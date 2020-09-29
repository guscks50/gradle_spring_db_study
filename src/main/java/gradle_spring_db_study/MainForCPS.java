package gradle_spring_db_study;

import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import gradle_spring_db_study.config.AppCtx;
import gradle_spring_db_study.spring.ChangePasswordService;
import gradle_spring_db_study.spring.Member;
import gradle_spring_db_study.spring.MemberDao;
import gradle_spring_db_study.spring.MemberNotFoundException;
import gradle_spring_db_study.spring.WrongIdPasswordException;

public class MainForCPS {
	public static void main(String[] args) throws IOException{
		try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtx.class);) {
			MemberDao memberDao = ctx.getBean(MemberDao.class);
			memberDao.insert(new Member("test@test.co.kr", "1234", "test", LocalDateTime.now()));
			System.out.println("회원을추가했습니다.\n");
			ChangePasswordService cps = ctx.getBean(ChangePasswordService.class);
			cps.changePassword("test@test.co.kr", "1234", "new1234");
			System.out.println("암호를변경했습니다.\n");
			Member member = memberDao.selectByEmail("test@test.co.kr");
			memberDao.delete(member);
			System.out.println("회원을삭제했습니다.\n");
		} catch (MemberNotFoundException e) {
			System.err.println("존재하지않는이메일입니다.\n");
		} catch (WrongIdPasswordException e) {
			System.err.println("이메일과암호가일치하지않습니다.\n");
		}
	}

}

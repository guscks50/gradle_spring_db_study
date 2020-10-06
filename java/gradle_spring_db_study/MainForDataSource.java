package gradle_spring_db_study;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;

import javax.sql.DataSource;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import gradle_spring_db_study.config.AppCtx;
import gradle_spring_db_study.spring.Member;
import gradle_spring_db_study.spring.MemberDao;

public class MainForDataSource {
	private static MemberDao memberDao;
	
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddHHmmss");

	public static void main(String[] args) {
		try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtx.class);) {
			DataSource ds = ctx.getBean(DataSource.class);
			System.out.println(ds);

			memberDao = ctx.getBean(MemberDao.class);
			/*
			 * selectAll(); System.out.println("-------------------------------");
			 * selectByEmail();
			 */
			selectAll();
			insertMember();
			selectAll();
			updateMember();
			selectAll();
			deleteMember();
			selectAll();
			
			
		}
	}
	private static void insertMember() {
		System.out.println("============ insertMember");
		String prefix = formatter.format(LocalDateTime.now());
		Member member = new Member(prefix+"@test.co.kr",prefix,prefix,LocalDateTime.now());
		memberDao.insert(member);
		System.out.println(member.getId()+"데이터 추가");
	}
	
	private static void updateMember() {
		System.out.println("============= updateMember");
		Member member = memberDao.selectByEmail("test@test.co.kr");
		String oldPw = member.getPassword();
		String newPw = Double.toHexString(Math.random());
		member.changePassword(oldPw, newPw);
		
		memberDao.update(member);
		System.out.println("암호 번경 :"+oldPw+">"+newPw);
		System.out.println(member.getId()+"데이터 삭제");
	}
	private static void deleteMember() {
		System.out.println("=========== deleteMember");
		Member member = memberDao.selectByEmail("test@test.co.kr");
		memberDao.delete(member);
	}

	private static void selectByEmail() {
		System.out.println("selectByEmail()");
		Member findMember = memberDao.selectByEmail("test@test.co.kr");
		System.out.printf("%d : %s : %s%n", findMember.getId(), findMember.getEmail(), findMember.getName());

	}

	private static void selectAll() {
		System.out.println("selectAll()");
		int total = memberDao.count();
		System.out.println("전체 데이터 "+ total);
		Collection<Member> findMember = memberDao.selectAll();
		for(Member member : findMember) {
			System.out.printf("%d : %s : %s%n", member.getId(), member.getEmail(), member.getName());
		}
	}
}

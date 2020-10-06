package gradle_spring_db_study.spring;

import org.springframework.dao.IncorrectResultSizeDataAccessException;

@SuppressWarnings("serial")
public class MemberNotFoundException extends RuntimeException{

	public MemberNotFoundException(IncorrectResultSizeDataAccessException e) {
		System.out.println("존재하지 않는 이메일 입니다");
	}
}

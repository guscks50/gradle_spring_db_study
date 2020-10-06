package gradle_spring_db_study.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ChangePaswordService {
	private MemberDao memberDao;
	
	@Transactional
	public void changePassword(String email,String oldPwd,String newPwd) {
		Member member = memberDao.selectByEmail(email);
		if(member==null) {
			throw new MemberNotFoundException(null);
		}
		member.changePassword(oldPwd, newPwd);
		memberDao.update(member);
	}
	
	@Autowired
	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
}

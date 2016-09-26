/**
 * 
 */
package work.model.service;

import java.util.ArrayList;
import java.util.HashMap;

import work.model.dao.MemberDao;
import work.model.dto.Member;
import work.util.Utility;

/**
 * ## 회원관리시스템에 대한 Service 클래스
 * -- 회원관리시스템의 기능들에 대한 business logic 클래스
 */
public class MemberService {
	/** 회원 dao 클래스 */
	private MemberDao dao = MemberDao.getInstance();
	
	
	/** 로그인 요청 서비스  */
	public HashMap<String, String> login(String userId, String userPw) {
		HashMap<String, String> map = dao.selectLogin(userId, userPw);
		return map;
	}
	
	/** 회원가입 서비스 */
	public int registerMember(Member dto) {
		dto.setVisitCount(0);
		dto.setEntryDate(Utility.getCurrentDate("yyyy/MM/dd"));
		dto.setGrade("G");
		return dao.insertMember(dto);
	}

	/** 아이디 찾기 */
	public String findId(int type, String name, String data) {
		return dao.selectId(type, name, data);
	}
	
	/** 비밀 번호 찾기 */
	public String findPw(int type, String userId, String data) {
		return dao.selectPw(type, userId, data);
	}
	
	/** 회원 탈퇴 */
	public int removeMember(String userId) {
		return dao.deleteMember(userId);
	}
	
	/** 내 정보 변경 */
	public int changeMyInfo(Member dto) {
		return dao.updateMember(dto);
	}
	
	/** 아이디 중복 조회 */
	public int idCheck(String userId) {
		return dao.selectDuplicateId(userId);
	}
	
	/** 관리자 : 회원 정보 상세 조회*/
	public Member detailMember(String userId) {
		return dao.selectMember(userId);
	}
	
	/** 회원 정보 상세 조회*/
	public Member myInfo(String userId) {
		return dao.selectMember(userId);
	}
	
	/** 관리자 : 회원 전체 조회 */
	public ArrayList<Member> listMember() {
		return dao.selectList();
	}
	
	/** 관리자 : 회원 검색 */
	public ArrayList<Member> searchMember(String keyName, String partialData) {
		return dao.selectData(keyName, partialData);
	}
	
	/** 관리자 : 회원정보 수정 */
	public int changeMember(Member dto) {
		return dao.updateAll(dto);
	}
}

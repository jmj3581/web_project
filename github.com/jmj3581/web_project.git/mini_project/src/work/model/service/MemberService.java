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
 * ## ȸ�������ý��ۿ� ���� Service Ŭ����
 * -- ȸ�������ý����� ��ɵ鿡 ���� business logic Ŭ����
 */
public class MemberService {
	/** ȸ�� dao Ŭ���� */
	private MemberDao dao = MemberDao.getInstance();
	
	
	/** �α��� ��û ����  */
	public HashMap<String, String> login(String userId, String userPw) {
		HashMap<String, String> map = dao.selectLogin(userId, userPw);
		return map;
	}
	
	/** ȸ������ ���� */
	public int registerMember(Member dto) {
		dto.setVisitCount(0);
		dto.setEntryDate(Utility.getCurrentDate("yyyy/MM/dd"));
		dto.setGrade("G");
		return dao.insertMember(dto);
	}

	/** ���̵� ã�� */
	public String findId(int type, String name, String data) {
		return dao.selectId(type, name, data);
	}
	
	/** ��� ��ȣ ã�� */
	public String findPw(int type, String userId, String data) {
		return dao.selectPw(type, userId, data);
	}
	
	/** ȸ�� Ż�� */
	public int removeMember(String userId) {
		return dao.deleteMember(userId);
	}
	
	/** �� ���� ���� */
	public int changeMyInfo(Member dto) {
		return dao.updateMember(dto);
	}
	
	/** ���̵� �ߺ� ��ȸ */
	public int idCheck(String userId) {
		return dao.selectDuplicateId(userId);
	}
	
	/** ������ : ȸ�� ���� �� ��ȸ*/
	public Member detailMember(String userId) {
		return dao.selectMember(userId);
	}
	
	/** ȸ�� ���� �� ��ȸ*/
	public Member myInfo(String userId) {
		return dao.selectMember(userId);
	}
	
	/** ������ : ȸ�� ��ü ��ȸ */
	public ArrayList<Member> listMember() {
		return dao.selectList();
	}
	
	/** ������ : ȸ�� �˻� */
	public ArrayList<Member> searchMember(String keyName, String partialData) {
		return dao.selectData(keyName, partialData);
	}
	
	/** ������ : ȸ������ ���� */
	public int changeMember(Member dto) {
		return dao.updateAll(dto);
	}
}

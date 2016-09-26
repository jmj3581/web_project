package work.model.dto;

import java.io.Serializable;

import work.util.Utility;

/** 
 * ȸ�� Ŭ���� 
 * @author ������
 * @version ver 1.0
 * @since JDK1.0
 */ 
public class Member implements Serializable{
	/** ȸ�� ���̵� ���� */
	private String userId = "Guest";

	/** ȸ�� ��й�ȣ ���� */
	private String userPw;

	/** ȸ�� �̸� ���� */
	private String name;

	/** ȸ�� ��ȭ��ȣ ���� */
	private String mobile;

	/** ȸ�� �̸��� ���� */
	private String email;
	
	/** ȸ�� �ּ� */
	private String address;
	
	/** ������ �湮 Ƚ�� */
	private int visitCount;
	
	/** ȸ�� ������ ����*/
	private String entryDate;

	/** ȸ�� ��� */
	private String grade;
	
	
	/** �⺻ ������*/
	public Member() {}
	
	/** ȸ���� �⺻ ������ �ʱ�ȭ �ϴ� ������ */
	public Member(String userId, String userPw, String name, String mobile, String email, String address) {
		super();
		this.userId = userId;
		this.userPw = userPw;
		this.name = name;
		this.mobile = mobile;
		this.email = email;
		this.address = address;
	}
	
	/** ȸ���� ��� ������ �ʱ�ȭ �ϴ� ������ */
	public Member(String userId, String userPw, String name, String mobile, String email, String address,
			int visitCount, String entryDate, String grade) {
		super();
		this.userId = userId;
		this.userPw = userPw;
		this.name = name;
		this.mobile = mobile;
		this.email = email;
		this.address = address;
		this.visitCount = visitCount;
		this.entryDate = entryDate;
		this.grade = grade;
	}
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPw() {
		return userPw;
	}

	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getVisitCount() {
		return visitCount;
	}

	public void setVisitCount(int visitCount) {
		this.visitCount = visitCount;
	}

	public String getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(userId);
		builder.append(", ");
		builder.append(Utility.convertSecureCode(userPw));
		builder.append(", ");
		builder.append(name);
		builder.append(", ");
		builder.append(mobile);
		builder.append(", ");
		builder.append(email);
		builder.append(", ");
		builder.append(address);
		builder.append(", ");
		builder.append(visitCount);
		builder.append(", ");
		builder.append(entryDate);
		builder.append(", ");
		builder.append(grade);
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Member other = (Member) obj;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

}
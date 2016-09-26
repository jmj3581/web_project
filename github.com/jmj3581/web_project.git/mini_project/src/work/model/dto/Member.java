package work.model.dto;

import java.io.Serializable;

import work.util.Utility;

/** 
 * 회원 클래스 
 * @author 정민지
 * @version ver 1.0
 * @since JDK1.0
 */ 
public class Member implements Serializable{
	/** 회원 아이디 정보 */
	private String userId = "Guest";

	/** 회원 비밀번호 정보 */
	private String userPw;

	/** 회원 이름 정보 */
	private String name;

	/** 회원 전화번호 정보 */
	private String mobile;

	/** 회원 이메일 정보 */
	private String email;
	
	/** 회원 주소 */
	private String address;
	
	/** 음식점 방문 횟수 */
	private int visitCount;
	
	/** 회원 가입일 정보*/
	private String entryDate;

	/** 회원 등급 */
	private String grade;
	
	
	/** 기본 생성자*/
	public Member() {}
	
	/** 회원의 기본 정보를 초기화 하는 생성자 */
	public Member(String userId, String userPw, String name, String mobile, String email, String address) {
		super();
		this.userId = userId;
		this.userPw = userPw;
		this.name = name;
		this.mobile = mobile;
		this.email = email;
		this.address = address;
	}
	
	/** 회원의 모든 정보를 초기화 하는 생성자 */
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
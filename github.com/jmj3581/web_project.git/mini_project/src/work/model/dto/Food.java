package work.model.dto;

import java.io.Serializable;

public class Food implements Serializable{
	/** ������ ��ȣ */
	private int foodNo;
	
	/** ����*/
	private String kind;
	
	/** ���� */
	private String country;
	
	/** ���Ը� */
	private String storeName;
	
	/** �ּ� */
	private String address;
	
	/** Ȩ������ �ּ� */
	private String homepage;
	
	/** ��ȭ��ȣ */
	private String phoneNumber;

	/** �̹��� ���ϸ� */
	private String fileName;
	
	
	/** �⺻ ������ */
	public Food() {}
	
	public Food(int foodNo, String kind, String country, String storeName, String address, String homepage,
			String phoneNumber, String fileName) {
		this.foodNo = foodNo;
		this.kind = kind;
		this.country = country;
		this.storeName = storeName;
		this.address = address;
		this.homepage = homepage;
		this.phoneNumber = phoneNumber;
		this.fileName = fileName;
	}

	public int getfoodNo() {
		return foodNo;
	}

	public void setfoodNo(int foodNo) {
		this.foodNo = foodNo;
	}

	public String getkind() {
		return kind;
	}

	public void setkind(String kind) {
		this.kind = kind;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getHomepage() {
		return homepage;
	}

	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + foodNo;
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
		Food other = (Food) obj;
		if (foodNo != other.foodNo)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(foodNo);
		builder.append(", ");
		builder.append(kind);
		builder.append(", ");
		builder.append(country);
		builder.append(", ");
		builder.append(storeName);
		builder.append(", ");
		builder.append(address);
		builder.append(", ");
		builder.append(homepage);
		builder.append(", ");
		builder.append(phoneNumber);
		builder.append(", ");
		builder.append(fileName);
		return builder.toString();
	}
}

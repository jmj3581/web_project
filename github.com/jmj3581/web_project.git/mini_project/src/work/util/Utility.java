package work.util;

import java.io.UnsupportedEncodingException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class Utility {
	/** 4�ڸ� �������� ��ȯ */
	public static String getSecureCode() {
		return getSecureCode(4);
	}
	
	/** ���� ��ŭ ���� ���� ��ȯ*/
	public static String getSecureCode(int length) {
		StringBuilder code = new StringBuilder();
		for (int i = 0; i < length; i++) {
			code.append((int)(Math.random()*10));
		}
		return code.toString();
	}
	
	/** ����2�ڸ��� ���ĺ�2�ڸ��� ���� ���ڿ� ��ȯ*/
	public static String getSecureCodeNumberAndAlphabet() {
		Random num = new Random();
		StringBuilder code = new StringBuilder();
		for (int i = 0; i < 2; i++){
			code.append(num.nextInt(10));
		}
		for (int i = 2; i < 4; i++){
			code.append((char)(num.nextInt(10) + 65));
		}
		return code.toString();
	}
	
	public static String getSecureCodeNumberAndAlphabet(int length) {
		Random num = new Random();
		StringBuilder code = new StringBuilder();
		for (int i = 0; i < 2; i++){
			code.append(num.nextInt(10));
		}
		for (int i = 2; i < length; i++){
			code.append((char)(num.nextInt(26) + 65));

		}
		return code.toString();
	}
	
	public static String getSecureCodeAlphabet() {
		Random num = new Random();
		StringBuilder code = new StringBuilder();
		for (int i = 0; i < 4; i++) {
			code.append((char)(num.nextInt(26) + 65));
		}
		return code.toString();
	}
	
	public static String getSecureCodeAlphabet(int length) {
		Random num = new Random();
		StringBuilder code = new StringBuilder();
		for (int i = 0; i < length; i++) {
			code.append((char)(num.nextInt(26) + 65));
//			code.append((char)((int)(Math.random()*26) + 65));
		}
		return code.toString();
	}
	
	
	public static String[] ascSort(String[] str) {
		String temp = null;
		for (int i = 0; i < str.length; i++) {
			for (int j = i+1 ; j < str.length; j++) {
				if (str[i].compareTo(str[j]) > 0){
					temp = str[i];
					str[i] = str[j];
					str[j] = temp;
				}
			}
		}
		return str;
	}
	
	public static String[] descSort(String[] str) {
		String temp = null;
		for (int i = 0; i < str.length; i++) {
			for (int j = i+1 ; j < str.length; j++) {
				if (str[i].compareTo(str[j]) < 0){
					temp = str[i];
					str[i] = str[j];
					str[j] = temp;
				}
			}
		}
		return str;
	}
	
	public static int[] ascSort(int[] str) {
		int temp;
		for (int i = 0; i < str.length; i++) {
			for (int j = i+1 ; j < str.length; j++) {
				if (str[i] > str[j]){
					temp = str[i];
					str[i] = str[j];
					str[j] = temp;
				}
			}
		}
		return str;
	}
	
	public static int[] descSort(int[] str) {
		int temp;
		for (int i = 0; i < str.length; i++) {
			for (int j = i+1 ; j < str.length; j++) {
				if (str[i] < str[j]){
					temp = str[i];
					str[i] = str[j];
					str[j] = temp;
				}
			}
		}
		return str;
	}	
	
	/** 
	 * ���� ��¥�� �⺻������ ���ڿ��� ��ȯ
	 * 
	 * @see java.text.SimpleDateFormat
	 * @see java.text.DateFormat
	 * @see java.util.Date
	 * @see java.util.Locale
	 */
	public static String getCurrentDate() {
		return getCurrentDate("yyyy/MM/dd");
	}
	
	public static String getCurrentDate(String pattern) {
		SimpleDateFormat date = new SimpleDateFormat(pattern);
		
		return date.format(new Date());
	}
	
	public static String getCurrentDate(String pattern, Locale locale) {
		return new SimpleDateFormat(pattern, locale).format(new Date());
	}
	
	/**
	 * ���� �����͸� õ�������� �ĸ�ǥ�� ��ȯ
	 * 
	 * @see java.text.NumberFormat
	 * @param data
	 * @return
	 */
	public static String convertNumber(long data) {
		return NumberFormat.getInstance().format(data);
	}
	
	public static String convertCurrency(long data) {
		return NumberFormat.getCurrencyInstance().format(data);
	}
	
	public static String convertCurrency(long data, Locale locale) {
		return NumberFormat.getCurrencyInstance(locale).format(data);
	}
	
	public static String convertSecureCode(String pw) {
		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append(pw.substring(0, 2));
		for (int i = 2; i < pw.length(); i++) {
			strBuilder.append("*");
		}
		return strBuilder.toString();
	}
	
	public static String convertSecureCode(String pw, int length) {
			StringBuilder strBuilder = new StringBuilder(pw.substring(0, length));
			for (int i = length; i < pw.length(); i++) {
				strBuilder.append("*");
			}
			return strBuilder.toString();
	}
	
	/** �����ø����̼��� get ����� �ѱ� ���ڵ� ��ȯ �޼��� */
	public static String toKor(String data) {
		try {
			return new String(data.getBytes("8859_1"), "euc-kr");
		} catch(UnsupportedEncodingException e) {
			System.out.println(e.getMessage());
		}
		return data;
	}
}


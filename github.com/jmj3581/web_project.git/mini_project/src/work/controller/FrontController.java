package work.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import work.model.dto.Board;
import work.model.dto.Food;
import work.model.dto.Member;
import work.model.service.BoardService;
import work.model.service.FoodService;
import work.model.service.MemberService;

public class FrontController extends HttpServlet {
	private MemberService memberService = new MemberService();
	private FoodService foodService = new FoodService();
	private BoardService boardService = new BoardService();
	
	/** ȸ���α��� ��� */
	protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		String userPw = request.getParameter("userPw");
		
		if(userId.trim().length() == 0 || userPw.trim().length() == 0 ||
				userId == null || userPw == null) {
			request.setAttribute("message", "�α��� ������ �Է��Ͻñ� �ٶ��ϴ�.");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
		
		HashMap<String, String> loginMap = memberService.login(userId, userPw);
		
		if(loginMap != null){
			HttpSession session = request.getSession(true);
			session.setAttribute("userId", userId);
			session.setAttribute("name", loginMap.get("name"));
			session.setAttribute("grade", loginMap.get("grade"));
			
			request.getRequestDispatcher("index.jsp").forward(request, response);
		} else {
			request.setAttribute("message", "���̵� �Ǵ� ��й�ȣ�� �ٽ� Ȯ���ϼ���.<br>��ϵ��� ���� ���̵��̰ų�, ���̵� �Ǵ� ��й�ȣ�� �߸� �Է��ϼ̽��ϴ�.");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}
	
	/** �α׾ƿ� ��û ���� �޼��� */
	protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		if(session != null && session.getAttribute("userId") != null ) {
			session.removeAttribute("userId");
			session.removeAttribute("name");
			session.removeAttribute("grade");
			
			session.invalidate();
			
			response.sendRedirect("index.jsp");
		} else {
			request.setAttribute("message", "�α׾ƿ��� �����Ͽ����ϴ�.");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}
	
	/** ȸ�� ���� ��û ���� �޼��� */
	protected void registerMember(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		String userPw = request.getParameter("userPw");
		String name = request.getParameter("name");
		String mobile1 = request.getParameter("mobile1");
		String mobile2 = request.getParameter("mobile2");
		String mobile3 = request.getParameter("mobile3");
		String email = request.getParameter("email");
		String email1 = request.getParameter("email1");
		String postcode = request.getParameter("postcode");
		String address = request.getParameter("address");
		String address2 = request.getParameter("address2");
		
		String mobile = mobile1 + "-" + mobile2 + "-" + mobile3;
		String email2 = email + "@" + email1;
		StringBuilder address1 = new StringBuilder();
		address1.append("(" + postcode +")");
		address1.append(address);
		address1.append(address2);
		
		if(userId == null || userId.trim().length() == 0 ||
			userPw == null || userPw.trim().length() == 0 ||
			name == null || name.trim().length() == 0 ||
			mobile == null || mobile.trim().length() == 0 ||
			email2 == null || email2.trim().length() == 0) {
			request.setAttribute("message", "�ٽ� �Է��Ͻñ� �ٶ��ϴ�.");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		} else {
			Member dto = new Member(userId, userPw, name, mobile, email2, address1.toString());
			int isRegister = memberService.registerMember(dto);
			if(isRegister > 0) {
				request.getRequestDispatcher("login.jsp").forward(request, response);
			} else {
				request.setAttribute("message", "ȸ�����Կ� �����Ͽ����ϴ�.");
				request.getRequestDispatcher("error.jsp").forward(request, response);
			}
		}
	}
	
	/** ���̵� �ߺ�Ȯ�� ��û ���� �޼��� */
	protected void checkId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		int idCheck = memberService.idCheck(userId);
		if(idCheck == 1) {
			request.setAttribute("message", "�̹� �����ϴ� ���̵� �Դϴ�.");
			request.getRequestDispatcher("checkId.jsp").forward(request, response);
		} else {
			request.setAttribute("message", "��밡���� ���̵� �Դϴ�.");
			request.getRequestDispatcher("checkId.jsp").forward(request, response);
		}
	}
	
	/** ���̵� ã�� ��û ���� �޼��� */
	protected void findId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int type = Integer.parseInt(request.getParameter("type"));
		String userId = null;
		if(type == 1) {
			String name = request.getParameter("name1");
			String mobile1 = request.getParameter("mobile1");
			String mobile2 = request.getParameter("mobile2");
			String mobile3 = request.getParameter("mobile3");
			String mobile = mobile1 + "-" + mobile2 +"-" + mobile3;
			if(name == null || name.trim().length() == 0 ||
				mobile == null || mobile.trim().length() == 0) {
				request.setAttribute("message", "�ٽ� �Է��Ͻñ� �ٶ��ϴ�.");
				request.getRequestDispatcher("error.jsp").forward(request, response);
			} else {
				userId = memberService.findId(type, name, mobile);
			}
		} else {
			String name = request.getParameter("name2");
			String email = request.getParameter("email");
			String email1 = request.getParameter("email1");
			String email2 = email + "@" + email1;
			System.out.println(email2);
			if(name == null || name.trim().length() == 0 ||
				email2 == null || email2.trim().length() == 0) {
				request.setAttribute("message", "�ٽ� �Է��Ͻñ� �ٶ��ϴ�.");
				request.getRequestDispatcher("error.jsp").forward(request, response);
			}
			userId = memberService.findId(type, name, email2);
		} 
		if(userId == null) {
			request.setAttribute("message", "�����ϴ� ȸ���� �����ϴ�.");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		} else {
			request.setAttribute("userId", userId);
			request.getRequestDispatcher("findIdPw.jsp").forward(request, response);
		}
	}
	
	/** ��й�ȣ ã�� ��û ���� �޼��� */
	protected void findPw(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int type = Integer.parseInt(request.getParameter("type2"));
		String userPw = null;

		if(type == 3) {
			String userId = request.getParameter("userId1");
			String mobile4 = request.getParameter("mobile4");
			String mobile5 = request.getParameter("mobile5");
			String mobile6 = request.getParameter("mobile6");
			String mobile = mobile4 + "-" + mobile5 + "-" + mobile6; 
			if(userId == null || userId.trim().length() == 0 ||
				mobile == null || mobile.trim().length() == 0) {
				request.setAttribute("message", "�ٽ� �Է��Ͻñ� �ٶ��ϴ�.");
				request.getRequestDispatcher("error.jsp").forward(request, response);
			}
			userPw = memberService.findPw(type, userId, mobile);
		} else {
			String userId = request.getParameter("userId2");
			String email3 = request.getParameter("email3");
			String email4 = request.getParameter("email4");
			String email = email3 + "@" + email4;
			if(userId == null || userId.trim().length() == 0 ||
				email == null || email.trim().length() == 0) {
				request.setAttribute("message", "�ٽ� �Է��Ͻñ� �ٶ��ϴ�.");
				request.getRequestDispatcher("error.jsp").forward(request, response);
			}
			userPw = memberService.findPw(type, userId, email);
		} 
		if(userPw == null) {
			request.setAttribute("message", "�����ϴ� ȸ���� �����ϴ�.");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		} else {
			request.setAttribute("userPw", userPw);
			request.getRequestDispatcher("findIdPw.jsp").forward(request, response);
		}	
	}
	
	/** ������ : ȸ��Ż�� ��û ���� �޼��� */
	protected void removeMember(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if(session != null && session.getAttribute("userId") != null) {
			int isDelete = memberService.removeMember(request.getParameter("userId"));
			if(isDelete == 0) {
				request.setAttribute("message", "ȸ��Ż�� �����Ͽ����ϴ�.");
				request.getRequestDispatcher("error.jsp").forward(request, response);
			} else {
				listMember(request, response);
			}
		} else {
			request.setAttribute("message", "�α��� �� �̿��� �ּ���");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}
	
	/** ȸ��Ż�� ��û ���� �޼��� */
	protected void removeMyInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		String userId = (String)session.getAttribute("userId");
		if(session != null && userId != null) {
			int isDelete = memberService.removeMember(userId);
			if(isDelete == 0) {
				request.setAttribute("message", "ȸ��Ż�� �����Ͽ����ϴ�.");
			} else {
				session.removeAttribute("userId");
				session.removeAttribute("name");
				session.removeAttribute("grade");
				
				session.invalidate();
				
				response.sendRedirect("index.jsp");
			}
		} else {
			request.setAttribute("message", "�α��� �� �̿��� �ּ���");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}
	
	/** ������ : ȸ�� ���߻��� ��û ���� �޼��� */
	protected void removeMultiMember(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		if(session != null && session.getAttribute("userId") != null) {
			String grade = (String)session.getAttribute("grade");
			if(grade != null && grade.equals("M")) {
				String[] no = request.getParameterValues("no");
				for(int i = 0; i < no.length; i++) {
					String userId = no[i];
					int isDelete = memberService.removeMember(userId);
					if(isDelete == 0) {
						request.setAttribute("message", "ȸ�� ������ �����Ͽ����ϴ�.");
						request.getRequestDispatcher("error.jsp").forward(request, response);
					} 
				}
				listMember(request, response);
			} else {
				request.setAttribute("message", "���� ������ �����մϴ�.");
				request.getRequestDispatcher("error.jsp").forward(request, response);
			}
		} else {
			request.setAttribute("message", "�α��� �� �̿��� �ּ���");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}
	
	/** �� ���� ���� ��û ���� �޼��� */
	protected void changeMyInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		if(session != null && session.getAttribute("userId") != null) {
			String userId = (String) session.getAttribute("userId");
			String userPw = request.getParameter("userPw");
			String name = request.getParameter("name");
			String mobile = request.getParameter("mobile");
			String email = request.getParameter("email");
			String address = request.getParameter("address");
			if(userPw == null || userPw.trim().length() == 0 ||
				name == null || name.trim().length() == 0 || 
				mobile == null || mobile.trim().length() == 0 ||
				email == null || email.trim().length() == 0 ) {
				request.setAttribute("message", "������ �ùٸ��� �Է��� �ּ���");	
				request.getRequestDispatcher("error.jsp").forward(request, response);
			} else {
				Member dto = new Member(userId, userPw, name, mobile, email, address);
				System.out.println(dto);
				int isCheck = memberService.changeMyInfo(dto);
				System.out.println("isCheck : " + isCheck);
				if(isCheck == 0) {
					request.setAttribute("message", "ȸ������ ���濡 �����Ͽ����ϴ�.");
					request.getRequestDispatcher("error.jsp").forward(request, response);
				} else {
					myInfo(request, response);
				}
			}
		} else {
			request.setAttribute("message", "�α��� �� �̿��� �ּ���.");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}
	
	/** ������ : ȸ�� ���� ��û ���� �޼��� */
	protected void changeMember(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		if(session != null && session.getAttribute("userId") != null) {
			String userId = request.getParameter("userId");
			String userPw = request.getParameter("userPw");
			String name = request.getParameter("name");
			String mobile = request.getParameter("mobile");
			String email = request.getParameter("email");
			String address = request.getParameter("address");
			int visitCount = Integer.parseInt(request.getParameter("visitCount"));
			String entryDate = request.getParameter("entryDate");
			String grade = request.getParameter("grade");
			
			Member dto = new Member(userId, userPw, name, mobile, email, address, visitCount, entryDate, grade);
			int isCheck = memberService.changeMember(dto);
			
			if(isCheck == 0) {
				request.setAttribute("message", "ȸ������ ���濡 �����Ͽ����ϴ�.");
				request.getRequestDispatcher("error.jsp").forward(request, response);
			} else {
				listMember(request, response);
			}
		} else {
			request.setAttribute("message", "�α��� �� �̿��� �ּ���.");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}
	
	/** ������ : ȸ�� �� ���� ��û ���� �޼��� */
	protected void detailMember(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		if(session != null && session.getAttribute("userId") != null) {
			String grade = (String)session.getAttribute("grade");
			if (grade != null && grade.equals("M")) {
				Member dto = memberService.detailMember(request.getParameter("userId"));
				request.setAttribute("dto", dto);
				request.getRequestDispatcher("memberOne.jsp").forward(request, response);
			} else {
				request.setAttribute("message", "�������� ������ �����մϴ�.");
				request.getRequestDispatcher("error.jsp").forward(request, response);
			}
		} else {
			request.setAttribute("message", "�α��� �� �̿��� �ּ���.");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}
	
	/** ȸ�� �� ���� ��û ���� �޼��� */
	protected void myInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		String userId = (String)session.getAttribute("userId");
		
		if(session != null && userId != null) {
			Member dto = memberService.myInfo(userId);
			request.setAttribute("dto", dto);
			request.getRequestDispatcher("myInfo.jsp").forward(request, response);
		} else {
			request.setAttribute("message", "�α��� �� �̿��� �ּ���.");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}
	
	/** ȸ�� ��ü ��ȸ ���� ��û ���� �޼��� */
	protected void listMember(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if(session != null && session.getAttribute("userId") != null) {
			String grade = (String)session.getAttribute("grade");
			if(grade != null && grade.equals("M")) {
				ArrayList<Member> list = memberService.listMember();
				request.setAttribute("list", list);
				request.getRequestDispatcher("memberList.jsp").forward(request, response);
			} else {
				request.setAttribute("message", "�������� ������ �����մϴ�.");
				request.getRequestDispatcher("error.jsp").forward(request, response);
			}
		} else {
			request.setAttribute("message", "�α��� �� �̿��Ͻñ� �ٶ��ϴ�.");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}
	
	/** ȸ�� �˻��ϱ� ��û ���� �޼��� */
	protected void searchMember(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		if(session != null && session.getAttribute("userId") != null) {
			String grade = (String)session.getAttribute("grade");
			if(grade != null && grade.equals("M")) {
				String key = request.getParameter("key");
				String data = request.getParameter("data");
				ArrayList<Member> list = memberService.searchMember(key, data);
				request.setAttribute("list", list);
				request.getRequestDispatcher("memberList.jsp").forward(request, response);
			} else {
				request.setAttribute("message", "�������� ������ �����մϴ�.");
				request.getRequestDispatcher("error.jsp").forward(request, response);
			}
		} else {
			request.setAttribute("message", "�α��� �� �̿��Ͻñ� �ٶ��ϴ�.");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}
	
	/** ������ : ������ ��� ��û ���� �޼��� */
	protected void registerFood(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);

		if(session != null && session.getAttribute("userId") != null) {
			String grade = (String)session.getAttribute("grade");
			if(grade != null && grade.equals("M")) {
				int foodNo = Integer.parseInt(request.getParameter("foodNo"));
				String kind = request.getParameter("kind");
				String country = request.getParameter("country");
				String storeName = request.getParameter("storeName");
				String address1 = request.getParameter("postcode");
				String address2 = request.getParameter("address");
				String address3 = request.getParameter("address2");
				String homepage = request.getParameter("homepage");
				String mobile1 = request.getParameter("mobile1");
				String mobile2 = request.getParameter("mobile2");
				String mobile3 = request.getParameter("mobile3");
				String fileName = request.getParameter("fileName");
				StringBuilder address = new StringBuilder();
				address.append("(" + address1 + ")");
				address.append(address2);
				address.append(address3);
				String phoneNumber = mobile1 + "-" + mobile2 + "-" + mobile3;
				if(foodNo == 0 || 
					kind == null || kind.trim().length() == 0 ||
					country == null || country.trim().length() == 0 ||
					storeName == null || storeName.trim().length() == 0 ||
					address.toString() == null || address.toString().trim().length() == 0 ) {
					request.setAttribute("message", "�ٽ� �Է��Ͻñ� �ٶ��ϴ�.");
					request.getRequestDispatcher("error.jsp").forward(request, response);
				} else {
					Food food = new Food(foodNo, kind, country, storeName, address.toString(), homepage, phoneNumber, fileName);
					int isCheck = foodService.registerFood(food);
					if(isCheck == 0) {
						request.setAttribute("message", "��Ͽ� �����Ͽ����ϴ�.");
						request.getRequestDispatcher("error.jsp").forward(request, response);
					} else {
						listFood(request, response);
					}
				}
			} else {
				request.setAttribute("message", "�������� ������ �����մϴ�.");
				request.getRequestDispatcher("error.jsp").forward(request, response);
			}
		} else {
			request.setAttribute("message", "�α��� �� �̿��Ͻñ� �ٶ��ϴ�.");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}
	
	/** ������ �˻� ��û ���� �޼��� */
	protected void searchFood(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String key = request.getParameter("key");
		String data = request.getParameter("data");
		
		// �⺻�� ����
		if(key == null) {
			key = "country";
		}
		ArrayList<Food> list = foodService.searchFood(key, data);
		request.setAttribute("list", list);
		request.getRequestDispatcher("foodList.jsp").forward(request, response);
	} 
	
	/** ������ : ������ ���� ��û ���� �޼��� */
	protected void removeFood(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		if(session != null && session.getAttribute("userId") != null) {
			String grade = (String)session.getAttribute("grade");
			if(grade != null && grade.equals("M")) {
				int isDelete = foodService.removeFood(Integer.parseInt(request.getParameter("foodNo")));
				if(isDelete == 0) {
					request.setAttribute("message", "������ ������ �����Ͽ����ϴ�.");
				} else {
					listFood(request, response);
				}
			} else {
				request.setAttribute("message", "���� ������ �����մϴ�.");
				request.getRequestDispatcher("error.jsp").forward(request, response);
			}
		} else {
			request.setAttribute("message", "�α��� �� �̿��� �ּ���");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}
	
	/** ������ : ������ ���߻��� ��û ���� �޼��� */
	protected void removeMultiFood(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		if(session != null && session.getAttribute("userId") != null) {
			String grade = (String)session.getAttribute("grade");
			if(grade != null && grade.equals("M")) {
				String[] no = request.getParameterValues("fNo");
				for(int i = 0; i < no.length; i++) {
					int foodNo = Integer.parseInt(no[i]);
					int isDelete = foodService.removeFood(foodNo);
					if(isDelete == 0) {
						request.setAttribute("message", "������ ������ �����Ͽ����ϴ�.");
					} 
				}
				listFood(request, response);
			} else {
				request.setAttribute("message", "���� ������ �����մϴ�.");
				request.getRequestDispatcher("error.jsp").forward(request, response);
			}
		} else {
			request.setAttribute("message", "�α��� �� �̿��� �ּ���");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}
	
	/** ������ : ������ ���� ��û ���� �޼��� */
	protected void changeFood(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		if(session != null && session.getAttribute("userId") != null) {
			String grade = (String)session.getAttribute("grade");
			if(grade != null && grade.equals("M")) {
				int foodNo = Integer.parseInt(request.getParameter("foodNo"));
				String kind = request.getParameter("kind");
				String country = request.getParameter("country");
				String storeName = request.getParameter("storeName");
				String address = request.getParameter("address");
				String homepage = request.getParameter("homepage");
				String phoneNumber = request.getParameter("phoneNumber");
				String fileName = request.getParameter("fileName");
				
				Food food = new Food(foodNo, kind, country, storeName, address, homepage, phoneNumber, fileName);
				int isChange = foodService.changeFood(food);
				if(isChange == 0) {
					request.setAttribute("message", "������ ���� ���濡 �����Ͽ����ϴ�.");
					request.getRequestDispatcher("error.jsp").forward(request, response);
				} else {
					listFood(request, response);
				}
			} else {
				request.setAttribute("message", "�������� ������ �����մϴ�.");
				request.getRequestDispatcher("error.jsp").forward(request, response);
			}
		} else {
			request.setAttribute("message", "�α��� �� �̿��Ͻñ� �ٶ��ϴ�.");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}
	
	/** ������ �� ��ȸ ��û ���� �޼��� */
	protected void detailFood(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		int foodNo = Integer.parseInt(request.getParameter("foodNo"));
		if(session != null && session.getAttribute("userId") != null) {
			Food dto = foodService.detailFood(foodNo);
			request.setAttribute("dto", dto);
			request.getRequestDispatcher("foodOne.jsp").forward(request, response);
		} else {
			request.setAttribute("message", "�α��� �� �̿��� �ּ���.");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}
	
	/** ������ ��ü ��ȸ ���� ��û ���� �޼��� */
	protected void listFood(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Food> list = foodService.listFood();
		request.setAttribute("list", list);
		request.getRequestDispatcher("foodList.jsp").forward(request, response);
	}
	
	/** �Խ��� ��� ��û ���� �޼��� */
	protected void registerBoard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		String userId = (String) session.getAttribute("userId");
		if(session != null && userId != null) {
			
			String title = request.getParameter("title");
			String contents = request.getParameter("contents");
			if(title == null || title.trim().length() == 0 ||
				contents == null || contents.trim().length() == 0 ) {
				request.setAttribute("message", "�ٽ� �Է��Ͻñ� �ٶ��ϴ�.");
				request.getRequestDispatcher("error.jsp").forward(request, response);
			} else {
				Board dto = new Board(title, userId, contents);
				int isRegister = boardService.registerBoard(dto);
				if(isRegister > 0) {
					listBoard(request, response);
				} else {
					request.setAttribute("message", "�Խ��� ��Ͽ� �����Ͽ����ϴ�.");
					request.getRequestDispatcher("error.jsp").forward(request, response);
				}
			}
		}
	}
	
	/** �Խ��� ���� ��û ���� �޼��� */
	protected void removeBoard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if(session != null && (String)session.getAttribute("userId") != null) {
			int boardNo = Integer.parseInt(request.getParameter("boardNo"));
			int isDelete = boardService.removeBoard(boardNo);
			if(isDelete == 0) {
				request.setAttribute("message", "�Խñ� ������ �����Ͽ����ϴ�.");
				request.getRequestDispatcher("error.jsp").forward(request, response);
			} else {
				listBoard(request, response);
			}
		} else {
			request.setAttribute("message", "�α��� �� �̿��� �ּ���");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}
	
	/** �Խ��� ��ü��ȸ ��û ���� �޼��� */
	protected void listBoard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pageNum = 1;
		if(request.getParameter("pageNum") != null) {
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		}
		ArrayList<Board> list = boardService.listBoard(pageNum);
		int listCount = boardService.totalCount();
		int maxPage = ((listCount / 10) == 0) ? (listCount / 10) : (listCount / 10) + 1;
		
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("maxPage", maxPage);
		request.setAttribute("list", list);
		request.getRequestDispatcher("boardList.jsp").forward(request, response);
	}
	
	/** �Խ��� �� ���� ��û ���� �޼��� */
	protected void detailBoard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);

		if(session != null && (String)session.getAttribute("userId") != null) {
			int boardNo = Integer.parseInt(request.getParameter("boardNo"));
			boardService.changeCount(boardNo);
			Board dto = boardService.detailBoard(boardNo);
			request.setAttribute("dto", dto);
			request.getRequestDispatcher("boardOne.jsp").forward(request, response);
		} else {
			request.setAttribute("message", "�α��� �� �̿��� �ּ���.");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}
	
	/** �Խñ� ���� ��û ���� �޼��� */
	protected void changeBoard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		if(session != null && session.getAttribute("userId") != null) {
			int boardNo = Integer.parseInt(request.getParameter("boardNo"));
			String title = request.getParameter("title");
			String contents = request.getParameter("contents");
			
			Board dto = new Board(boardNo, title, contents);
			int isCheck = boardService.changeBoard(dto);
			
			if(isCheck == 0) {
				request.setAttribute("message", "�Խñ� ���濡 �����Ͽ����ϴ�.");
				request.getRequestDispatcher("error.jsp").forward(request, response);
			} else {
				listBoard(request, response);
			}
		} else {
			request.setAttribute("message", "�α��� �� �̿��� �ּ���.");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}
	
	/** �Խ��� �˻��ϱ� ��û ���� �޼��� */
	protected void searchBoard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if(session != null && session.getAttribute("userId") != null) {
			String key = request.getParameter("key");
			String data = request.getParameter("data");

			ArrayList<Board> list = boardService.searchBoard(key, data);
			request.setAttribute("list", list);
			request.getRequestDispatcher("boardList.jsp").forward(request, response);
		} else {
			request.setAttribute("message", "�α��� �� �̿��Ͻñ� �ٶ��ϴ�.");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}
	
	
	/** get, post ��û�� ó���ϴ� ���� �޼��� 
	 * @throws IOException 
	 * @throws ServletException */
	protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		if(action != null) {
			switch(action) {
			case "login":
				login(request, response);
				break;
				
			case "logout":
				logout(request, response);
				break;
				
			case "join":
				registerMember(request, response);
				break;
				
			case "findId":
				findId(request, response);
				break;
				
			case "findPw":
				findPw(request, response);
				break;
				
			case "removeMember":
				removeMember(request, response);
				break;
				
			case "removeMyInfo":
				removeMyInfo(request, response);
				break;
				
			case "changeMyInfo":
				changeMyInfo(request, response);
				break;
				
			case "myInfo":
				myInfo(request, response);
				break;
				
			case "listMember":
				listMember(request, response);
				break;
				
			case "searchMember":
				searchMember(request, response);
				break;
				
			case "memberOne":
				detailMember(request, response);
				break;
				
			case "changeMember":
				changeMember(request, response);
				break;
			
			case "registerFood":
				registerFood(request, response);
				break;
				
			case "listFood":
				listFood(request, response);
				break;
				
			case "foodOne":
				detailFood(request, response);
				break;
				
			case "removeFood":
				removeFood(request, response);
				break;
				
			case "changeFood":
				changeFood(request, response);
				break;
				
			case "searchFood":
				searchFood(request, response);
				break;
				
			case "listBoard":
				listBoard(request, response);
				break;
				
			case "registerBoard":
				registerBoard(request, response);
				break;
				
			case "boardOne":
				detailBoard(request, response);
				break;
				
			case "removeBoard":
				removeBoard(request, response);
				break;
				
			case "changeBoard":
				changeBoard(request, response);
				break;
				
			case "searchBoard":
				searchBoard(request, response);
				break;
				
			case "removeMultiFood":
				removeMultiFood(request, response);
				break;
				
			case "checkId":
				checkId(request, response);
				break;
				
			case "removeMultiMember":
				removeMultiMember(request, response);
				break;
				
			default:
				break;
			}
		} else {

		}
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("EUC-KR");
		process(request, response);
	}

}

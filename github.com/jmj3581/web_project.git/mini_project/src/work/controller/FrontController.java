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
	
	/** 회원로그인 기능 */
	protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		String userPw = request.getParameter("userPw");
		
		if(userId.trim().length() == 0 || userPw.trim().length() == 0 ||
				userId == null || userPw == null) {
			request.setAttribute("message", "로그인 정보를 입력하시기 바랍니다.");
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
			request.setAttribute("message", "아이디 또는 비밀번호를 다시 확인하세요.<br>등록되지 않은 아이디이거나, 아이디 또는 비밀번호를 잘못 입력하셨습니다.");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}
	
	/** 로그아웃 요청 서비스 메서드 */
	protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		if(session != null && session.getAttribute("userId") != null ) {
			session.removeAttribute("userId");
			session.removeAttribute("name");
			session.removeAttribute("grade");
			
			session.invalidate();
			
			response.sendRedirect("index.jsp");
		} else {
			request.setAttribute("message", "로그아웃에 실패하였습니다.");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}
	
	/** 회원 가입 요청 서비스 메서드 */
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
			request.setAttribute("message", "다시 입력하시기 바랍니다.");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		} else {
			Member dto = new Member(userId, userPw, name, mobile, email2, address1.toString());
			int isRegister = memberService.registerMember(dto);
			if(isRegister > 0) {
				request.getRequestDispatcher("login.jsp").forward(request, response);
			} else {
				request.setAttribute("message", "회원가입에 실패하였습니다.");
				request.getRequestDispatcher("error.jsp").forward(request, response);
			}
		}
	}
	
	/** 아이디 중복확인 요청 서비스 메서드 */
	protected void checkId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		int idCheck = memberService.idCheck(userId);
		if(idCheck == 1) {
			request.setAttribute("message", "이미 존재하는 아이디 입니다.");
			request.getRequestDispatcher("checkId.jsp").forward(request, response);
		} else {
			request.setAttribute("message", "사용가능한 아이디 입니다.");
			request.getRequestDispatcher("checkId.jsp").forward(request, response);
		}
	}
	
	/** 아이디 찾기 요청 서비스 메서드 */
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
				request.setAttribute("message", "다시 입력하시기 바랍니다.");
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
				request.setAttribute("message", "다시 입력하시기 바랍니다.");
				request.getRequestDispatcher("error.jsp").forward(request, response);
			}
			userId = memberService.findId(type, name, email2);
		} 
		if(userId == null) {
			request.setAttribute("message", "존재하는 회원이 없습니다.");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		} else {
			request.setAttribute("userId", userId);
			request.getRequestDispatcher("findIdPw.jsp").forward(request, response);
		}
	}
	
	/** 비밀번호 찾기 요청 서비스 메서드 */
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
				request.setAttribute("message", "다시 입력하시기 바랍니다.");
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
				request.setAttribute("message", "다시 입력하시기 바랍니다.");
				request.getRequestDispatcher("error.jsp").forward(request, response);
			}
			userPw = memberService.findPw(type, userId, email);
		} 
		if(userPw == null) {
			request.setAttribute("message", "존재하는 회원이 없습니다.");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		} else {
			request.setAttribute("userPw", userPw);
			request.getRequestDispatcher("findIdPw.jsp").forward(request, response);
		}	
	}
	
	/** 관리자 : 회원탈퇴 요청 서비스 메서드 */
	protected void removeMember(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if(session != null && session.getAttribute("userId") != null) {
			int isDelete = memberService.removeMember(request.getParameter("userId"));
			if(isDelete == 0) {
				request.setAttribute("message", "회원탈퇴에 실패하였습니다.");
				request.getRequestDispatcher("error.jsp").forward(request, response);
			} else {
				listMember(request, response);
			}
		} else {
			request.setAttribute("message", "로그인 후 이용해 주세요");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}
	
	/** 회원탈퇴 요청 서비스 메서드 */
	protected void removeMyInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		String userId = (String)session.getAttribute("userId");
		if(session != null && userId != null) {
			int isDelete = memberService.removeMember(userId);
			if(isDelete == 0) {
				request.setAttribute("message", "회원탈퇴에 실패하였습니다.");
			} else {
				session.removeAttribute("userId");
				session.removeAttribute("name");
				session.removeAttribute("grade");
				
				session.invalidate();
				
				response.sendRedirect("index.jsp");
			}
		} else {
			request.setAttribute("message", "로그인 후 이용해 주세요");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}
	
	/** 관리자 : 회원 다중삭제 요청 서비스 메서드 */
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
						request.setAttribute("message", "회원 삭제에 실패하였습니다.");
						request.getRequestDispatcher("error.jsp").forward(request, response);
					} 
				}
				listMember(request, response);
			} else {
				request.setAttribute("message", "접근 권한이 부족합니다.");
				request.getRequestDispatcher("error.jsp").forward(request, response);
			}
		} else {
			request.setAttribute("message", "로그인 후 이용해 주세요");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}
	
	/** 내 정보 변경 요청 서비스 메서드 */
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
				request.setAttribute("message", "정보를 올바르게 입력해 주세요");	
				request.getRequestDispatcher("error.jsp").forward(request, response);
			} else {
				Member dto = new Member(userId, userPw, name, mobile, email, address);
				System.out.println(dto);
				int isCheck = memberService.changeMyInfo(dto);
				System.out.println("isCheck : " + isCheck);
				if(isCheck == 0) {
					request.setAttribute("message", "회원정보 변경에 실패하였습니다.");
					request.getRequestDispatcher("error.jsp").forward(request, response);
				} else {
					myInfo(request, response);
				}
			}
		} else {
			request.setAttribute("message", "로그인 후 이용해 주세요.");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}
	
	/** 관리자 : 회원 변경 요청 서비스 메서드 */
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
				request.setAttribute("message", "회원정보 변경에 실패하였습니다.");
				request.getRequestDispatcher("error.jsp").forward(request, response);
			} else {
				listMember(request, response);
			}
		} else {
			request.setAttribute("message", "로그인 후 이용해 주세요.");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}
	
	/** 관리자 : 회원 상세 정보 요청 서비스 메서드 */
	protected void detailMember(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		if(session != null && session.getAttribute("userId") != null) {
			String grade = (String)session.getAttribute("grade");
			if (grade != null && grade.equals("M")) {
				Member dto = memberService.detailMember(request.getParameter("userId"));
				request.setAttribute("dto", dto);
				request.getRequestDispatcher("memberOne.jsp").forward(request, response);
			} else {
				request.setAttribute("message", "접근위한 권한이 부족합니다.");
				request.getRequestDispatcher("error.jsp").forward(request, response);
			}
		} else {
			request.setAttribute("message", "로그인 후 이용해 주세요.");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}
	
	/** 회원 상세 정보 요청 서비스 메서드 */
	protected void myInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		String userId = (String)session.getAttribute("userId");
		
		if(session != null && userId != null) {
			Member dto = memberService.myInfo(userId);
			request.setAttribute("dto", dto);
			request.getRequestDispatcher("myInfo.jsp").forward(request, response);
		} else {
			request.setAttribute("message", "로그인 후 이용해 주세요.");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}
	
	/** 회원 전체 조회 정보 요청 서비스 메서드 */
	protected void listMember(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if(session != null && session.getAttribute("userId") != null) {
			String grade = (String)session.getAttribute("grade");
			if(grade != null && grade.equals("M")) {
				ArrayList<Member> list = memberService.listMember();
				request.setAttribute("list", list);
				request.getRequestDispatcher("memberList.jsp").forward(request, response);
			} else {
				request.setAttribute("message", "접근위한 권한이 부족합니다.");
				request.getRequestDispatcher("error.jsp").forward(request, response);
			}
		} else {
			request.setAttribute("message", "로그인 후 이용하시기 바랍니다.");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}
	
	/** 회원 검색하기 요청 서비스 메서드 */
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
				request.setAttribute("message", "접근위한 권한이 부족합니다.");
				request.getRequestDispatcher("error.jsp").forward(request, response);
			}
		} else {
			request.setAttribute("message", "로그인 후 이용하시기 바랍니다.");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}
	
	/** 관리자 : 음식점 등록 요청 서비스 메서드 */
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
					request.setAttribute("message", "다시 입력하시기 바랍니다.");
					request.getRequestDispatcher("error.jsp").forward(request, response);
				} else {
					Food food = new Food(foodNo, kind, country, storeName, address.toString(), homepage, phoneNumber, fileName);
					int isCheck = foodService.registerFood(food);
					if(isCheck == 0) {
						request.setAttribute("message", "등록에 실패하였습니다.");
						request.getRequestDispatcher("error.jsp").forward(request, response);
					} else {
						listFood(request, response);
					}
				}
			} else {
				request.setAttribute("message", "접근위한 권한이 부족합니다.");
				request.getRequestDispatcher("error.jsp").forward(request, response);
			}
		} else {
			request.setAttribute("message", "로그인 후 이용하시기 바랍니다.");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}
	
	/** 음식점 검색 요청 서비스 메서드 */
	protected void searchFood(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String key = request.getParameter("key");
		String data = request.getParameter("data");
		
		// 기본값 설정
		if(key == null) {
			key = "country";
		}
		ArrayList<Food> list = foodService.searchFood(key, data);
		request.setAttribute("list", list);
		request.getRequestDispatcher("foodList.jsp").forward(request, response);
	} 
	
	/** 관리자 : 음식점 삭제 요청 서비스 메서드 */
	protected void removeFood(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		if(session != null && session.getAttribute("userId") != null) {
			String grade = (String)session.getAttribute("grade");
			if(grade != null && grade.equals("M")) {
				int isDelete = foodService.removeFood(Integer.parseInt(request.getParameter("foodNo")));
				if(isDelete == 0) {
					request.setAttribute("message", "음식점 삭제에 실패하였습니다.");
				} else {
					listFood(request, response);
				}
			} else {
				request.setAttribute("message", "접근 권한이 부족합니다.");
				request.getRequestDispatcher("error.jsp").forward(request, response);
			}
		} else {
			request.setAttribute("message", "로그인 후 이용해 주세요");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}
	
	/** 관리자 : 음식점 다중삭제 요청 서비스 메서드 */
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
						request.setAttribute("message", "음식점 삭제에 실패하였습니다.");
					} 
				}
				listFood(request, response);
			} else {
				request.setAttribute("message", "접근 권한이 부족합니다.");
				request.getRequestDispatcher("error.jsp").forward(request, response);
			}
		} else {
			request.setAttribute("message", "로그인 후 이용해 주세요");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}
	
	/** 관리자 : 음식점 변경 요청 서비스 메서드 */
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
					request.setAttribute("message", "음식점 정보 변경에 실패하였습니다.");
					request.getRequestDispatcher("error.jsp").forward(request, response);
				} else {
					listFood(request, response);
				}
			} else {
				request.setAttribute("message", "접근위한 권한이 부족합니다.");
				request.getRequestDispatcher("error.jsp").forward(request, response);
			}
		} else {
			request.setAttribute("message", "로그인 후 이용하시기 바랍니다.");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}
	
	/** 음식점 상세 조회 요청 서비스 메서드 */
	protected void detailFood(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		int foodNo = Integer.parseInt(request.getParameter("foodNo"));
		if(session != null && session.getAttribute("userId") != null) {
			Food dto = foodService.detailFood(foodNo);
			request.setAttribute("dto", dto);
			request.getRequestDispatcher("foodOne.jsp").forward(request, response);
		} else {
			request.setAttribute("message", "로그인 후 이용해 주세요.");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}
	
	/** 음식점 전체 조회 정보 요청 서비스 메서드 */
	protected void listFood(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Food> list = foodService.listFood();
		request.setAttribute("list", list);
		request.getRequestDispatcher("foodList.jsp").forward(request, response);
	}
	
	/** 게시판 등록 요청 서비스 메서드 */
	protected void registerBoard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		String userId = (String) session.getAttribute("userId");
		if(session != null && userId != null) {
			
			String title = request.getParameter("title");
			String contents = request.getParameter("contents");
			if(title == null || title.trim().length() == 0 ||
				contents == null || contents.trim().length() == 0 ) {
				request.setAttribute("message", "다시 입력하시기 바랍니다.");
				request.getRequestDispatcher("error.jsp").forward(request, response);
			} else {
				Board dto = new Board(title, userId, contents);
				int isRegister = boardService.registerBoard(dto);
				if(isRegister > 0) {
					listBoard(request, response);
				} else {
					request.setAttribute("message", "게시판 등록에 실패하였습니다.");
					request.getRequestDispatcher("error.jsp").forward(request, response);
				}
			}
		}
	}
	
	/** 게시판 삭제 요청 서비스 메서드 */
	protected void removeBoard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if(session != null && (String)session.getAttribute("userId") != null) {
			int boardNo = Integer.parseInt(request.getParameter("boardNo"));
			int isDelete = boardService.removeBoard(boardNo);
			if(isDelete == 0) {
				request.setAttribute("message", "게시글 삭제에 실패하였습니다.");
				request.getRequestDispatcher("error.jsp").forward(request, response);
			} else {
				listBoard(request, response);
			}
		} else {
			request.setAttribute("message", "로그인 후 이용해 주세요");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}
	
	/** 게시판 전체조회 요청 서비스 메서드 */
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
	
	/** 게시판 상세 정보 요청 서비스 메서드 */
	protected void detailBoard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);

		if(session != null && (String)session.getAttribute("userId") != null) {
			int boardNo = Integer.parseInt(request.getParameter("boardNo"));
			boardService.changeCount(boardNo);
			Board dto = boardService.detailBoard(boardNo);
			request.setAttribute("dto", dto);
			request.getRequestDispatcher("boardOne.jsp").forward(request, response);
		} else {
			request.setAttribute("message", "로그인 후 이용해 주세요.");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}
	
	/** 게시글 변경 요청 서비스 메서드 */
	protected void changeBoard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		if(session != null && session.getAttribute("userId") != null) {
			int boardNo = Integer.parseInt(request.getParameter("boardNo"));
			String title = request.getParameter("title");
			String contents = request.getParameter("contents");
			
			Board dto = new Board(boardNo, title, contents);
			int isCheck = boardService.changeBoard(dto);
			
			if(isCheck == 0) {
				request.setAttribute("message", "게시글 변경에 실패하였습니다.");
				request.getRequestDispatcher("error.jsp").forward(request, response);
			} else {
				listBoard(request, response);
			}
		} else {
			request.setAttribute("message", "로그인 후 이용해 주세요.");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}
	
	/** 게시판 검색하기 요청 서비스 메서드 */
	protected void searchBoard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if(session != null && session.getAttribute("userId") != null) {
			String key = request.getParameter("key");
			String data = request.getParameter("data");

			ArrayList<Board> list = boardService.searchBoard(key, data);
			request.setAttribute("list", list);
			request.getRequestDispatcher("boardList.jsp").forward(request, response);
		} else {
			request.setAttribute("message", "로그인 후 이용하시기 바랍니다.");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}
	
	
	/** get, post 요청을 처리하는 서비스 메서드 
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

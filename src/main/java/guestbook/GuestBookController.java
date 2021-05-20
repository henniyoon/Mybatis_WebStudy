package guestbook;

import java.io.IOException;
import java.util.List;

import guestbook.dao.GuestBookDAO;
import guestbook.dto.GuestBookDTO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/guestbook_servlet/*")
public class GuestBookController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		
		String uri = request.getRequestURI();	// 요청한 주소
		GuestBookDAO dao = new GuestBookDAO();
		
		// 리스트
		if(uri.indexOf("list.do") != -1) {
			String searchkey = "name";	// 검색옵션
			String search = "";			// 검색 키워드
			if(request.getParameter("searchkey") != null) {
				searchkey = request.getParameter("searchkey");
			}
			if(request.getParameter("search") != null) {
				search = request.getParameter("search");
			}
			List<GuestBookDTO> items = dao.getList(searchkey, search);
			int count = items.size();
			request.setAttribute("list", items);
			request.setAttribute("count", count);
			request.setAttribute("searchkey", searchkey);
			request.setAttribute("search", search);
			String url = "/guestbook/list.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(url);
			rd.forward(request, response);
			
		// 추가
		} else if(uri.indexOf("insert.do") != -1) {
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String passwd = request.getParameter("passwd");
			String content = request.getParameter("content");
			GuestBookDTO dto = new GuestBookDTO();
			dto.setName(name);
			dto.setEmail(email);
			dto.setPasswd(passwd);
			dto.setContent(content);
			dao.gbInsert(dto);
			String url = "/guestbook_servlet/list.do";
			response.sendRedirect(request.getContextPath() + url);
			
		// 비밀번호 체크	
		} else if(uri.indexOf("passwd_check.do") != -1) {
			int idx = Integer.parseInt(request.getParameter("idx"));
			String passwd = request.getParameter("passwd");
			boolean result = dao.passwdCheck(idx, passwd);
			String url = "";
			if(result) {
				url = "/guestbook/edit.jsp";
				GuestBookDTO dto = dao.gbDetail(idx);
				request.setAttribute("dto", dto);
			} else {
				url = "/guestbook_servlet/list.do";
			}
			RequestDispatcher rd = request.getRequestDispatcher(url);
			rd.forward(request, response);
		
		// 수정
		} else if(uri.indexOf("update.do") != -1) {
			int idx = Integer.parseInt(request.getParameter("idx"));
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String passwd = request.getParameter("passwd");
			String content = request.getParameter("content");
			GuestBookDTO dto = new GuestBookDTO();
			dto.setIdx(idx);
			dto.setName(name);
			dto.setEmail(email);
			dto.setPasswd(passwd);
			dto.setContent(content);
			dao.gbUpdate(dto);
			String url = "/guestbook_servlet/list.do";
			response.sendRedirect(request.getContextPath() + url);
		
		// 삭제
		} else if(uri.indexOf("delete.do") != -1) {
			int idx = Integer.parseInt(request.getParameter("idx"));
			dao.gbDelete(idx);
			String url = "/guestbook_servlet/list.do";
			response.sendRedirect(request.getContextPath() + url);
		}
	}
		
	protected void doPost(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}

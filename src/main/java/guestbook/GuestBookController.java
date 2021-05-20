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
				search = request.getParameter("searach");
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
			
		}
	}
		
	protected void doPost(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}

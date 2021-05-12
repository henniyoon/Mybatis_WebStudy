package memo;

import java.io.IOException;
import java.util.List;

import jakarta.security.auth.message.callback.PrivateKeyCallback.Request;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import memo.dao.MemoDAO;
import memo.dto.MemoDTO;

@WebServlet("/memo_servlet/*")
public class MemoController extends HttpServlet	{
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		MemoDAO dao = new MemoDAO();
		String url = request.getRequestURL().toString();
		
		// list
		if(url.indexOf("list.do") != -1) {
			String searchkey = request.getParameter("searchkey");	// 검색 옵션
			// null 또는 빈 값이면
			if(searchkey == null || searchkey.equals(""))
				searchkey = "writer";	// 기본값
			String search = request.getParameter("search");			// 검색 키워드
			if(search == null)	search = "";	// null이면 기본값
			
			List<MemoDTO> list = dao.listMemo(searchkey, search);
			request.setAttribute("list", list);
			request.setAttribute("searchkey", searchkey);
			request.setAttribute("search", search);
			String page = "/memo/memo_list.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
			
		// insert	
		} else if(url.indexOf("insert.do") != -1) {
				String writer = request.getParameter("writer");
				String memo = request.getParameter("memo");
				dao.insertMemo(new MemoDTO(writer, memo));
				
		// view		
		} else if(url.indexOf("view.do") != -1) {	// 메모 상세
			int idx = Integer.parseInt(request.getParameter("idx"));	// 글번호
			MemoDTO dto = dao.viewMemo(idx);
			request.setAttribute("dto", dto);
			String page = "/memo/memo_view.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(page);
			rd.forward(request, response);
		}
	}
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}

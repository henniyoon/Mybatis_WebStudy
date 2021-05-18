package survey;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletSecurityElement;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import survey.dao.SurveyDAO;
import survey.dto.SurveyDTO;
import survey.dto.SurveyResultDTO;
import survey.dto.SurveySummaryDTO;

@WebServlet("/survey_servlet/*")
public class SurveyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		
		String path = request.getContextPath();		// 컨텍스트 패스
		String url = request.getRequestURI();		// 사용자가 요청한 주소
		SurveyDAO dao = new SurveyDAO();
		
		// 설문조사 입력
		if(url.indexOf("input.do") != -1) {
			SurveyDTO dto = dao.viewQuestion(1);	// 1번 문제 불러오기
			request.setAttribute("dto", dto);
			RequestDispatcher rd 
				= request.getRequestDispatcher("/survey/survey_input.jsp");	// 입력 페이지로 이동
			rd.forward(request, response);
		
		// 추가
		} else if(url.indexOf("insert.do") != -1) {
			int survey_idx = Integer.parseInt(request.getParameter("survey_idx"));	// 문제 번호
			int num = Integer.parseInt(request.getParameter("num"));	// 응답 번호
			SurveyResultDTO dto = new SurveyResultDTO();
			dto.setSurvey_idx(survey_idx);
			dto.setNum(num);
			dao.insertSurvey(dto);
			response.sendRedirect(path + "/survey/input_result.jsp");	// 결과 페이지로 이동
		
		// 설문조사 결과
		} else if(url.indexOf("survey_result.do") != -1) {
			int survey_idx = Integer.parseInt(request.getParameter("survey_idx"));
			List<SurveySummaryDTO> items = dao.listSummary(survey_idx);
			request.setAttribute("list", items);
			RequestDispatcher rd = 
					request.getRequestDispatcher("/survey/survey_result.jsp");	// 출력 페이지
			rd.forward(request, response);
		}
	}
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}

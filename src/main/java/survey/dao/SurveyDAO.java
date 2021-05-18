package survey.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import sqlmap.MybatisManager;
import survey.dto.SurveyDTO;
import survey.dto.SurveyResultDTO;
import survey.dto.SurveySummaryDTO;

public class SurveyDAO {
	// 설문결과를 리턴하는 코드
	public List<SurveySummaryDTO> listSummary(int survey_idx) {
		// sql 실행 세션 객체를 만들고
		SqlSession session = MybatisManager.getInstance().openSession();
		// 네임스페이스.sqlID 명령어를 호출하여 리스트를 받아옴
		List<SurveySummaryDTO> items = session.selectList("survey.list_summary", survey_idx);
		session.close();	// sql 세션을 닫고
		return items;		// 리턴
	}
	
	public void insertSurvey(SurveyResultDTO dto) {
		SqlSession session = MybatisManager.getInstance().openSession();
		session.insert("survey.insert_survey", dto);
		session.commit();
		session.close();
	}
	// 설문문제를 dto에 저장하여 리턴하는 코드
	public SurveyDTO viewQuestion(int survey_idx) {
		SqlSession session = MybatisManager.getInstance().openSession();
		SurveyDTO dto = session.selectOne("survey.view_question", survey_idx);
		session.close();
		return dto;
	}
	
}

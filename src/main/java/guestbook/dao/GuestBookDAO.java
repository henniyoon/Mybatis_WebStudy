package guestbook.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import guestbook.dto.GuestBookDTO;
import sqlmap.MybatisManager;

public class GuestBookDAO {
	
	// 검색 select
	public List<GuestBookDTO> getList(String searchkey, String search) {
		SqlSession session = MybatisManager.getInstance().openSession();
		List<GuestBookDTO> list = null;
		if(searchkey.equals("name_content")) {	// 이름 + 내용 검색
			list = session.selectList("gbListAll", search);
		} else {
			Map<String, Object> map = new HashMap<>();
			map.put("searchkey", searchkey);
			map.put("search", search);
			list = session.selectList("gbList", map);
		}
		session.close();
		return list;
	}
	
	// 추가
	public void gbInsert(GuestBookDTO dto) {
		SqlSession session = MybatisManager.getInstance().openSession();
		String content = dto.getContent();
		// 태그 문자가 인식되지 않도록 처리
		content = content.replace("<", "&lt;");
		content = content.replace(">", "&gt;");
		// 줄바꿈 문자 처리
		content = content.replace("\n", "<br>");
		// 공백 문자 처리
		content = content.replace(" ", "&nbsp;&nbsp;");
		dto.setContent(content);
		session.insert("gbInsert", dto);
		session.commit();	// insert, update, delete 명령어는 커밋을 해야함
		session.close();
	}
	
	// 비밀번호 확인
	public boolean passwdCheck(int idx, String passwd) {
		boolean result = false;
		SqlSession session = MybatisManager.getInstance().openSession();
		GuestBookDTO dto = new GuestBookDTO();
		dto.setIdx(idx);
		dto.setPasswd(passwd);
		// 비밀번호가 맞으면 1, 틀리면 0
		int count = session.selectOne("passwdCheck", dto);
		result = count == 1 ? true : false;
		session.close();
		return result;
	}
	
	// 방명록 상세 정보
	public GuestBookDTO gbDetail(int idx) {
		GuestBookDTO dto = new GuestBookDTO();
		SqlSession session = MybatisManager.getInstance().openSession();
		dto = session.selectOne("gbDetail", idx);
		session.close();
		return dto;
	}
	
	// 수정
	public void gbUpdate(GuestBookDTO dto) {
		SqlSession session = MybatisManager.getInstance().openSession();
		session.update("gbUpdate", dto);
		session.commit();
		session.close();
	}
	
	
	
}

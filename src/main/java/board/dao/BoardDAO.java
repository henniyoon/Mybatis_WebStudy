package board.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import board.dto.BoardDTO;
import sqlmap.MybatisManager;

public class BoardDAO {
	
	private static BoardDAO instance;	// 싱글톤 인스턴스
	
	private BoardDAO() {}	// 외부에서 호출할 수 없는 생성자
	
	public static BoardDAO getInstance() {
		if(instance == null) {
			instance = new BoardDAO();	// 인스턴스 생성
		}
		return instance;
	}
	
	public int count(String search_option, String keyword) {
		int result = 0;
		
		try(SqlSession session = MybatisManager.getInstance().openSession()) {
			Map<String, Object> map = new HashMap<>();
			map.put("search_option", search_option);
			map.put("keyword", keyword);
			result = session.selectOne("board.search_count", map);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public List<BoardDTO> searchList(String searchOption, String keyword, 
			int start, int end) {
		List<BoardDTO> list = null;
		
		try(SqlSession session = MybatisManager.getInstance().openSession()) {
			Map<String, Object> map = new HashMap<>();
			map.put("search_option", searchOption);
			map.put("keyword", keyword);
			map.put("start", start);
			map.put("end", end);
			System.out.println(map);
			list = session.selectList("board.searchList", map);
			for(BoardDTO dto : list) {
				String writer = dto.getWriter();
				String subject = dto.getSubject();
				switch(searchOption) {
					case "all":
						writer = writer.replace(keyword,
								"<span style='color:red'>" + keyword + "</span>");
						subject = subject.replace(keyword,
								"<span style='color:red'>" + keyword + "</span>");
						break;
					case "writer":
						writer = writer.replace(keyword, 
								"<span style='color:red'>" + keyword + "</span>");
						break;
					case "subject":
						subject = subject.replace(keyword, 
								"<span style='color:red'>" + keyword + "</span>");
						break;
				}
				dto.setWriter(writer);
				dto.setSubject(subject);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public void update(BoardDTO dto) {
		SqlSession session = null;
		try {
			session = MybatisManager.getInstance().openSession();
			session.update("board.update", dto);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) session.close();
		}
	}
	
	public void delete(int num) {
		SqlSession session = null;
		try {
			session = MybatisManager.getInstance().openSession();
			session.delete("board.delete", num);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) session.close();
		}
	}
	
	public List<BoardDTO> list(int pageStart, int pageEnd) {
		List<BoardDTO> list = null;
		SqlSession session = null;
		try {
			session = MybatisManager.getInstance().openSession();
			Map<String, Object> map = new HashMap<>();
			map.put("start", pageStart);
			map.put("end", pageEnd);
			list = session.selectList("board.list", map);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) session.close();
		}
		return list;
	}
	
	public int count() {
		int result = 0;
		try(SqlSession session = MybatisManager.getInstance().openSession()) {
			result = session.selectOne("board.count");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public void insert(BoardDTO dto) {
		SqlSession session = null;
		try {
			session = MybatisManager.getInstance().openSession();
			session.insert("board.insert", dto);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) session.close();
		}
	}
	
	public BoardDTO view(int num) {
		BoardDTO dto = null;
		SqlSession session = null;
		try {
			session = MybatisManager.getInstance().openSession();
			dto = session.selectOne("board.view", num);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) session.close();
		}
		return dto;
	}
	

}

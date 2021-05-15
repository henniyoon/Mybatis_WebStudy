package memo.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import memo.dto.MemoDTO;
import sqlmap.MybatisManager;

public class MemoDAO {
	
	// 메모 목록
	public List<MemoDTO> listMemo(String searchkey, String search) {
		// mybatis framework에 접속하여 sql 구문을 실행시키는 역할을 하는 SqlSession 객체 생성
		SqlSession session = MybatisManager.getInstance().openSession();
		System.out.println(searchkey + "," + search);
		
		List<MemoDTO> list = null;
		if(searchkey.equals("writer_memo")) {
			list = session.selectList("memo.listAll", search);
		} else {
			Map<String, Object> map = new HashMap<>();
			map.put("searchkey", searchkey);
			map.put("search", search);
			System.out.println(map);
			list = session.selectList("memo.list", map);
		}
		return list;
	}
	
	// 메모 추가
	public void insertMemo(MemoDTO dto) {
		SqlSession session = MybatisManager.getInstance().openSession();
		// session.insert("insert id", 입력매개변수);
		session.insert("memo.insert", dto);
		// mybatis는 오토커밋이 되지 않으므로 session.commit()을 해야함
		session.commit();
		session.close();
	}
	
	// 메모 상세뷰
	public MemoDTO viewMemo(int idx) {
		SqlSession session = MybatisManager.getInstance().openSession();
		// 결과 레코드가 한 건이므로 selectOne("select id", 매개변수);
		MemoDTO dto = session.selectOne("memo.view", idx);
		session.close();
		return dto;
	}
	
	// 메모 수정
	public void updateMemo(MemoDTO dto) {
		SqlSession session = MybatisManager.getInstance().openSession();
		session.update("memo.update", dto);
		session.commit();
		session.close();
	}
	
	// 메모 삭제
	public void deleteMemo(int idx) {
		SqlSession session = MybatisManager.getInstance().openSession();
		session.delete("memo.delete", idx);
		session.commit();
		session.close();
	}
	
}

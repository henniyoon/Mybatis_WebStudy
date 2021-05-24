package page;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import sqlmap.MybatisManager;

public class EmpDAO {
	
	public List<EmpDTO> empList(int start, int end) {
		List<EmpDTO> items = new ArrayList<>();
		SqlSession session = MybatisManager.getInstance().openSession();
		Map<String, Object> map = new HashMap<>();
		map.put("start", start);
		map.put("end", end);
		items = session.selectList("emp.empList", map);
		session.close();
		return items;
	}

	public int empCount() {	// 전체 레코드 개수
		int count = 0;
		SqlSession session = MybatisManager.getInstance().openSession();
		count = session.selectOne("emp.empCount");
		session.close();
		return count;
	}
}

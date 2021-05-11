package sqlmap;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisManager {   
	// SqlSessionFactoryBuilder => SqlSessionFactory => SqlSession
	// SqlSession 객체 생성기
	private static SqlSessionFactory instance;
	
	private MybatisManager() { }
	
	public static SqlSessionFactory getInstance() {
		Reader reader = null;
		try {
			reader = Resources.getResourceAsReader("sqlmap/sqlMapConfig.xml");
			instance = new SqlSessionFactoryBuilder().build(reader);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(reader != null) reader.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return instance;
	}    
}	

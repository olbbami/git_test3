package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JDBCUtil {
	/*
	 * JDBC를 좀 더 쉽고 편하게 사용하기 위한 Utillity 클래스 
	 * 
	 * Map<String, Object> selectOne(String sql)
	 * Map<String, Object> selectOne(String sql, List<Object> param)
	 * List<Map<String, Object>> selectList(String sql)
	 * List<Map<String, Object>> selectList(String sql, List<Object> param)
	 * int update(String sql)  // 조건없이 모두다 update할 때 
	 * int update(String sql, List<Object> param)  // ? 가 들어간 sql문은 파라미터가 필요한 데 이때 사용함 
	 * 
	 * 
	 * */
	
	// 싱글톤 패턴 : 인스턴스의 생성을 제한하여 하나의 인스턴스만 사용하는 디자인 패턴 
	
	// 객체를 남용하면 메모리에 심각한 누수가 발생할 수 있으므로 >> 싱글톤 패턴을 활용 
	// JDBCUtil 객체를 만들 수 없게(인스턴스화 할 수 없게) private으로 제한함
	// JDBCUtil 객체를 만들 수는 없지만 사용할 수는 있게 할 것임    
	// 인스턴스를 보관할 변수
	
	private static JDBCUtil instance = null;
	 String url ="jdbc:oracle:thin:@localhost:1521:xe";    // jdbc:oracle:thin:@까지는 약속!  xe도 약속! 
	 String user ="PROJECT_MUSIC";
	 String password ="java"; 
	 
	 Connection conn = null;
	 ResultSet rs = null;
	 PreparedStatement ps = null;
	
	private JDBCUtil() {}  // 기본 생성자를 private으로 해뒀기에 자동적으로 생성이 될 수 없음 
	public static JDBCUtil getInstance() {  // getInstance는 public이므로 바깥에서 사용이 가능 
		if(instance==null) instance = new JDBCUtil();
		return instance;
	}
	
	
	public void insert_data(String sql, List<Object> param) {
		try {
			conn = DriverManager.getConnection(url, user, password);
			ps = conn.prepareStatement(sql);
			
			for(int i=0; i<param.size(); i++) {
				ps.setObject(i+1, param.get(i));
			}
			
			int result = ps.executeUpdate();
			
			if(result==0) {
				System.out.println("업데이트 실패");
			} else {
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbclose();
		}
	}
	
	
	
	public Map<String, Object> selectOne(String sql, List<Object> param){
		
		
		Map<String, Object> row = null;
		
		try {
			conn = DriverManager.getConnection(url, user, password);
			ps = conn.prepareStatement(sql);
			for(int i = 0; i<param.size(); i++) {
				ps.setObject(i+1, param.get(i));
			}
			rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int ColumnCount = rsmd.getColumnCount();
			while(rs.next()) {
				row = new HashMap<>();
				for(int i = 1; i <= ColumnCount; i++) {
					String key = rsmd.getColumnLabel(i);
					Object value = rs.getObject(i);
					row.put(key, value);
				}
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		} finally {
			// 5. ResultSet, Connection, PrepareStatement 닫기 
			if(rs != null) {try{
				rs.close(); // (rs가 null이 아니면)사용을 했으면 반납하라는 뜻. 
				} catch(Exception e) {
					
				}
			}
			if(ps != null) {try{
				ps.close(); // (rs가 null이 아니면)사용을 했으면 반납하라는 뜻. 
			} catch(Exception e) {
				
			}
			}
			

			if(conn != null) {try {conn.close();}catch(Exception e) {}  // 약속된 구문이므로 보통 이렇게 한 줄로 쓰는 편임 
			}

		} 
		return row;
	
		
		
		
		
	}
	
	public Map<String, Object> selectOne(String sql){
	      Map<String, Object> row = null;
	      try {
	         conn = DriverManager.getConnection(url, user, password);
	         ps = conn.prepareStatement(sql);
	         rs = ps.executeQuery();
	         ResultSetMetaData rsmd = rs.getMetaData(); // 컬럼과 값을 리턴할 것 ! 우리는 지금 컬럼명을 모르기 때문에 컬럼명을 알고 싶을때 메타데이터를 씀 !
	         int columnCount = rsmd.getColumnCount();
	         while(rs.next()){ // 결과 테이블이 없으면 row 는 null 형태임
	            row = new HashMap<>();
	            for(int i = 1 ; i<= columnCount; i++) {
	               String key = rsmd.getColumnLabel(i);
	               Object value = rs.getObject(i);
	               row.put(key, value);
	            }
	         }
	         
	      }catch(SQLException e) {
	         e.printStackTrace();
	      }finally {
	         if(rs != null)   try { rs.close(); } catch (Exception e) {}
	         if(ps != null)  try { ps.close(); } catch (Exception e) {}
	         if(conn != null) try { conn.close(); } catch (Exception e) {} 
	      }
	      return row;
	   }
	
	public int update(String sql) {
	
		int result = 0;
		try {
			conn = DriverManager.getConnection(url, user, password);
			ps = conn.prepareStatement(sql);
			result = ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		} finally {
			// 5. ResultSet, Connection, PrepareStatement 닫기 
			if(rs != null) {try{
				rs.close(); // (rs가 null이 아니면)사용을 했으면 반납하라는 뜻. 
				} catch(Exception e) {
					
				}
			}
			if(ps != null) {try{
				ps.close(); // (rs가 null이 아니면)사용을 했으면 반납하라는 뜻. 
			} catch(Exception e) {
				
			}
			}
			

			if(conn != null) {try {conn.close();}catch(Exception e) {}  // 약속된 구문이므로 보통 이렇게 한 줄로 쓰는 편임 
			}

		} 
		return result;
	}
	
	public int update(String sql, List<Object> param) {
		
		int result = 0;
		try {
			conn = DriverManager.getConnection(url, user, password);
			ps = conn.prepareStatement(sql);
			for(int i = 0; i < param.size(); i++) {
				ps.setObject(i+1, param.get(i));
			}
			result = ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		} finally {
			// 5. ResultSet, Connection, PrepareStatement 닫기 
			if(rs != null) {try{
				rs.close(); // (rs가 null이 아니면)사용을 했으면 반납하라는 뜻. 
				} catch(Exception e) {
					
				}
			}
			if(ps != null) {try{
				ps.close(); // (rs가 null이 아니면)사용을 했으면 반납하라는 뜻. 
			} catch(Exception e) {
				
			}
			}
			

			if(conn != null) {try {conn.close();}catch(Exception e) {}  // 약속된 구문이므로 보통 이렇게 한 줄로 쓰는 편임 
			}

		} 
		return result;
	}
	
	public List<Map<String, Object>> selectList(String sql){
		// sql => "SELECT * FROM MEMBER"
		// sql => "SELECT * FROM JAVA_BOARD"
		// sql => "SELECT * FROM JAVA_BOARD WHERE BOARD_NUM > 10"
		List<Map<String, Object>> result = null; 
		
		try {
			conn = DriverManager.getConnection(url, user, password);
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			while(rs.next()) {
				if(result==null) result = new ArrayList<>();
				Map<String, Object> row = new HashMap<>();
				for(int i =1; i <= columnCount; i++) {
					String key = rsmd.getColumnLabel(i);
					Object value = rs.getObject(i);
					row.put(key, value);
				}
				result.add(row);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs != null)try {rs.close();} catch(Exception e) {}
			if(ps != null)try {ps.close();} catch(Exception e) {}
			if(conn != null)try {conn.close();} catch(Exception e) {}
		}
		
		return result;
	}
	
	public List<Map<String, Object>> selectList(String sql, List<Object> param){
		// sql => "SELECT * FROM MEMBER"
		// sql => "SELECT * FROM JAVA_BOARD"
		// sql => "SELECT * FROM JAVA_BOARD WHERE BOARD_NUM > 10"
		List<Map<String, Object>> result = null; 
		
		try {
			conn = DriverManager.getConnection(url, user, password);
			ps = conn.prepareStatement(sql);
			for(int i = 0; i < param.size(); i++) {
				ps.setObject(i+1, param.get(i));
			}
			rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			while(rs.next()) {
				if(result==null) result = new ArrayList<>();
				Map<String, Object> row = new HashMap<>();
				for(int i =1; i <= columnCount; i++) {
					String key = rsmd.getColumnLabel(i);
					Object value = rs.getObject(i);
					row.put(key, value);
				}
				result.add(row);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs != null)try {rs.close();} catch(Exception e) {}
			if(ps != null)try {ps.close();} catch(Exception e) {}
			if(conn != null)try {conn.close();} catch(Exception e) {}
		}
		
		return result;
	}
	
	
	public void dbclose() {
		if(rs != null)try {rs.close();} catch(Exception e) {}
		if(ps != null)try {ps.close();} catch(Exception e) {}
		if(conn != null)try {conn.close();} catch(Exception e) {}
	}
		


}

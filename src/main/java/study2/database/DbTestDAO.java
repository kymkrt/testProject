package study2.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DbTestDAO {
	
	Connection conn = null;
	PreparedStatement pstmt=null;
	ResultSet rs = null;
	
	String sql = "";
	DbTestVO vo = null;
	
	public DbTestDAO() {
		String url = "jdbc:mysql://localhost:3306/javagroup";
		String user = "root";
		String password = "1234";
		
		 try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);
			
		} catch (ClassNotFoundException e) {
			System.out.println("클래스 없음 "+e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("SQL 없음 "+e.getMessage());
			e.printStackTrace();
		}
	}
	public void connClose() {
		if(conn != null) {
			try {
				conn.close();
			} catch (Exception e) {
				System.out.println("conn 안닫힘 "+e.getMessage());
			}
		}
	}
	
	public void pstmtClose() {
		if(pstmt != null) {
			try {
				pstmt.close();
			} catch (Exception e) {
				System.out.println("pstmt 안닫힘 "+e.getMessage());
			}
		}
	}
	
	public void rsClose() {
		if(rs != null) {
			try {
				rs.close();
			} catch (Exception e) {
				System.out.println("rs 안닫힘 "+e.getMessage());
			}finally {
				pstmtClose();
			}
		}
	}
	
	//전체 자료
	public ArrayList<DbTestVO> getDbTestList(String name) {
		ArrayList<DbTestVO> vos = new ArrayList<DbTestVO>();
		
		try {
			if(name.equals("")) {
				sql = "select * from hoewon";
				pstmt = conn.prepareStatement(sql);
			}else {
				sql = "select * from hoewon where name=?";
				pstmt = conn.prepareStatement(sql);
			}
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				vo = new DbTestVO();
				vo.setIdx(rs.getInt("idx"));
				vo.setName(rs.getString("name"));
				vo.setAge(rs.getInt("age"));
				vo.setGender(rs.getString("gender"));
				vo.setAddress(rs.getString("address"));
				
				vos.add(vo);
			}
		}
		catch (SQLException e) {
			System.out.println("SQL 오류 "+e.getMessage());
		}finally {
			rsClose();
		}
		
		return vos;
	}
	
	//자료 추가
	public int setDbTestInput(DbTestVO vo) {
		int res = 0;
		try {
			sql = "insert into hoewon values (default,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getName());
			pstmt.setInt(2, vo.getAge());
			pstmt.setString(3, vo.getGender());
			pstmt.setString(4, vo.getAddress());
			
			res = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("회원등록 오류 "+e.getMessage());
		}finally {
			pstmtClose();
		}
		
		return res;
	}
	
	//자료 삭제
	public int setDbTestDeleteOk(int idx) {
		int res = 0;
		
		try {
			sql = "delete from hoewon where idx=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			res = pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("삭제 실패 "+e.getMessage());
		} finally {
			pstmtClose();
		}
		return res;
	}
	
	
	//자료 수정
	public int setDbTestUpdateOk(DbTestVO vo) {
		int res = 0;
		
		try {
			sql = "update hoewon set name=?, age=?, gender=?, address=? where idx = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getName());
			res = pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("자료 삭제 실패 "+e.getMessage());
		}finally {
			pstmtClose();
		}
		
		return res;
	}
	
	//자료 한개 검색
	public DbTestVO getDbTestSearch(int idx) {
		
		try {
			sql = "select * from hoewon where idx=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			rs = pstmt.executeQuery();
			
			vo = new DbTestVO();
			if(rs.next()) {
				vo.setIdx(rs.getInt("idx"));
				vo.setName(rs.getString("name"));
				vo.setAge(rs.getInt("age"));
				vo.setGender(rs.getString("gender"));
				vo.setAddress(rs.getString("address"));
			}
		} catch (Exception e) {
			System.out.println("자료 1건 검색 실패 "+e.getMessage());
		}finally {
			rsClose();
		}
		
		return vo;
	}
	
	
	
	
}

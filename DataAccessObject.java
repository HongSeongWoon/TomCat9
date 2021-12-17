package services.auth;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.Employee;

class DataAccessObject {
	private PreparedStatement pstmt;
	DataAccessObject(){}
	
	/* Driver Loading & Create Connection 
	 * 	0. Driver Information, url:ip+port+sid, userId, userPassword 
	 * 	1. Driver Loading
	 *  2. Driver Manager를 통한 Connection 생성
	 * */
	Connection getConnection() {
		Connection connection = null;
		String[] url = {"jdbc:oracle:thin:@192.168.0.181:1521:xe", "SW22", "1234"};

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection(url[0], url[1], url[2]);

		}catch(Exception e) {
			e.printStackTrace();
		}

		return connection;
	}

	/* Transaction 상태 변경 */
	void modifyTranStatus(Connection connection, boolean status) {
		try {
			if(connection != null && !connection.isClosed()) {
				connection.setAutoCommit(status);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/* Transaction 처리 :: Commit | Rollback */
	void setTransaction(Connection connection, boolean tran) {

		try {
			if(connection != null && !connection.isClosed()) {
				if(tran) {
					connection.commit();
				}else {
					connection.rollback();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/* Close Connection */
	void closeConnection(Connection connection) {
		try {
			if(!pstmt.isClosed()) pstmt.close();
			if(connection != null && !connection.isClosed()) connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/* convertToBoolean */
	boolean convertToBoolean(int value) {
		return (value > 0)? true : false;
	} 
	
	/* isSeCode */
	boolean isSeCode(Connection connection, Employee emp) {
		ResultSet rs = null;
		boolean result = false;
		String sql = "SELECT COUNT(*) FROM SE WHERE SE_CODE = ?";
		
		try {
			this.pstmt = connection.prepareStatement(sql);
			this.pstmt.setNString(1, emp.getSeCode());
			
			rs = this.pstmt.executeQuery();
			while(rs.next()) {
				result =  this.convertToBoolean(rs.getInt(1));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/* isEmCode && comparePassword */
	boolean isEmployee(Connection connection, Employee emp) {
		ResultSet rs = null;
		boolean result = false;
		String sql = "SELECT COUNT(*) FROM EM WHERE EM_CODE = ? AND EM_PASSWORD = ?";
		
		try {
			this.pstmt = connection.prepareStatement(sql);
			this.pstmt.setNString(1, emp.getEmCode());
			this.pstmt.setNString(2, emp.getEmPassword());
			
			rs = this.pstmt.executeQuery();
			while(rs.next()) {
				result =  this.convertToBoolean(rs.getInt(1));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try { if(!rs.isClosed()) rs.close();} catch (SQLException e) {e.printStackTrace();}
		}
		
		return result;
	}
	
	/* insAccessHistory */
	boolean insAccessHistory(Connection connection, Employee emp) {
		boolean result = false;
		String dml = "INSERT INTO AH(AH_SECODE, AH_EMCODE, AH_ACCESSTIME, AH_ACCESSTYPE) "
				+ "VALUES(?, ?, DEFAULT, ?)";
		
		try {
			this.pstmt = connection.prepareStatement(dml);
			this.pstmt.setNString(1, emp.getSeCode());
			this.pstmt.setNString(2, emp.getEmCode());
			this.pstmt.setInt(3, emp.getAccessType());
			
			result = this.convertToBoolean(this.pstmt.executeUpdate());
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {}
		
		return result;
	}
	
	/* getAccessInfo */
	ArrayList<Employee> getAccessInfo(Connection connection, Employee emp){
		ArrayList<Employee> list = new ArrayList<Employee>();
		ResultSet rs = null;
		/* 상점명(상점코드) 직원명(직원코드) 최근접속기록 */
		String sql = "SELECT SECODE, SENAME, EMCODE, EMNAME, ACCESSTIME FROM DBA1JO.ACCESSINFO "
				+ "WHERE ACCESSTIME = (SELECT TO_CHAR(MAX(AH_ACCESSTIME), 'YYYY-MM-DD HH24:MI:SS') "
				+ "                     FROM DBA1JO.ACCESSHISTORY "
				+ "						WHERE AH_SECODE=? AND AH_EMCODE=?)";
		
		try {
			this.pstmt = connection.prepareStatement(sql);
			this.pstmt.setNString(1, emp.getSeCode());
			this.pstmt.setNString(2, emp.getEmCode());
			
			rs = this.pstmt.executeQuery();
			while(rs.next()) {
				Employee em = new Employee();
				em.setSeCode(rs.getNString("SECODE"));
				em.setSeName(rs.getNString("SENAME"));
				em.setEmCode(rs.getNString("EMCODE"));
				em.setEmName(rs.getNString("EMNAME"));
				em.setDate(rs.getNString("ACCESSTIME"));
				
				list.add(em);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try { if(!rs.isClosed()) rs.close();} catch (SQLException e) {e.printStackTrace();}
		}
		
		return list;
	}
}

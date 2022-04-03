package services.auth;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.Customers;
import beans.Employees;



//import beans.Employee;

class DataAccessObject extends webscafe.DataAccessObject {
	private ResultSet rs;
	
	DataAccessObject(){}
	
	

	
	/* isSeCode */
	boolean isSeCode(Connection connection, Customers cu) {
		ResultSet rs = null;
		boolean result = false;
		String sql = "SELECT COUNT(*) FROM SE1 WHERE SE_CODE = ? ";
		try {
			this.pstmt = connection.prepareStatement(sql);
			this.pstmt.setNString(1, cu.getSeCode());
			
			rs = this.pstmt.executeQuery();
			while(rs.next()) {
				result =  this.convertToBoolean(rs.getInt(1));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/* isSeCode employee*/
	boolean isSeCode(Connection connection, Employees emp) {
		ResultSet rs = null;
		boolean result = false;
		String sql = "SELECT COUNT(*) FROM SE1 WHERE SE_CODE = ? ";
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
	
	/* isCuNum && comparePassword */
	boolean isCustomers(Connection connection, Customers cu) {
		ResultSet rs = null;
		boolean result = false;
		String sql = "SELECT COUNT(*) FROM CU1 WHERE CU_NUM = ? AND CU_PASSWORD = ? ";
		
		try {
			this.pstmt = connection.prepareStatement(sql);
			this.pstmt.setNString(1, cu.getCuNum());
			this.pstmt.setNString(2, cu.getCuPassword());
			
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
		
	/* isCuNum && comparePassword */
	boolean isEmployees(Connection connection, Employees emp) {
		ResultSet rs = null;
		boolean result = false;
		String sql = "SELECT COUNT(*) FROM EM1 WHERE EM_CODE = ? AND EM_PASSWORD = ? ";
		
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
	
	/* insMEMBERLOGIN */
	boolean insMemberlogin(Connection connection, Customers cu) {
		boolean result = false;
		String dml = "INSERT INTO ML1(ML_SECODE, ML_CUNUM, ML_ACCESSTIME, ML_STCODE) "
						+ "	VALUES( ? , ?, DEFAULT, ?)";
		
		try {
			
			
			this.pstmt = connection.prepareStatement(dml);
			this.pstmt.setNString(1, cu.getSeCode());
			this.pstmt.setNString(2, cu.getCuNum());
			this.pstmt.setInt(3, cu.getCuStCode());
			
			result = this.convertToBoolean(this.pstmt.executeUpdate());
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {}
		
		return result;
	}
	
	/* insEmployeeLOGIN */
	boolean insEmployeelogin(Connection connection, Employees emp) {
		boolean result = false;
		String dml = "INSERT INTO EL1(EL_SECODE, EL_EMCODE, EL_ACCESSTIME, EL_STCODE) "
				+ "	VALUES( ? , ?, DEFAULT, ?)";
		
		try {
			
			
			this.pstmt = connection.prepareStatement(dml);
			this.pstmt.setNString(1, emp.getSeCode());
			this.pstmt.setNString(2, emp.getEmCode());
			this.pstmt.setInt(3, emp.getEmStCode());
			
			result = this.convertToBoolean(this.pstmt.executeUpdate());
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {}
		
		return result;
	}
	
	/*직원코드 값중 최대값 조회*/
	void getMaxEmpCode(Connection conn, Employees emp) {
		String query = "SELECT MAX(EM_CODE) AS maxEmpCode FROM EM1";
		try {
			this.pstmt = conn.prepareStatement(query);
			
			this.rs = this.pstmt.executeQuery();
			while(rs.next()) {
				emp.setEmCode((Integer.parseInt(rs.getNString("maxEmpCode"))+1)+"");
			}
		}catch(SQLException e){e.printStackTrace();}
		finally {
			try {if(!rs.isClosed()) rs.close();}catch(SQLException e) {e.printStackTrace();}
		}
	}
	
	/*새로운 관리자 등록 Employ 입력*/
	boolean insEmp(Connection conn, Employees emp) {
		boolean result = false;
		String dml = "INSERT INTO EM1(EM_SECODE, EM_CODE, EM_NAME, EM_PASSWORD) VALUES(?, ?, ?, ?)";
		
		try {
			this.pstmt = conn.prepareStatement(dml);
			this.pstmt.setNString(1, emp.getSeCode());
			this.pstmt.setNString(2, emp.getEmCode());
			this.pstmt.setNString(3, emp.getEmName());
			this.pstmt.setNString(4, emp.getEmPassword());
						
			result = this.convertToBoolean(this.pstmt.executeUpdate());
			
		} catch (SQLException e) {
			e.printStackTrace();
		}//rs이라던지 닫을게 없어서 finally안쓴다.
		return result;
	}
	
	/*회원 등록*/
	boolean insMember(Connection conn, Customers cu) {
		boolean result = false;
		String dml = "INSERT INTO CU1(CU_NUM, CU_NAME, CU_PASSWORD) VALUES(?, ?, ?)";
		
		try {
			this.pstmt = conn.prepareStatement(dml);
			this.pstmt.setNString(1, cu.getCuNum());
			this.pstmt.setNString(2, cu.getCuName());
			this.pstmt.setNString(3, cu.getCuPassword());
							
			result = this.convertToBoolean(this.pstmt.executeUpdate());
			
		} catch (SQLException e) {
			e.printStackTrace();
		}//rs이라던지 닫을게 없어서 finally안쓴다.
		return result;
	}
	
	/*회원 삭제*/
	boolean delMember(Connection conn, Customers cu) {
		boolean result = false;
		String dml = "DELETE FROM CU1 WHERE CU_NUM = ? ";
		
		try {
			this.pstmt = conn.prepareStatement(dml);
			this.pstmt.setNString(1, cu.getCuNum());
		
							
			result = this.convertToBoolean(this.pstmt.executeUpdate());
			
		} catch (SQLException e) {
			e.printStackTrace();
		}//rs이라던지 닫을게 없어서 finally안쓴다.
		return result;
	}
	
	/*회원 수정*/
	boolean modMember(Connection conn, Customers cu) {
		boolean result = false;
		String dml = "UPDATE CU1 SET CU1.CU_NAME = ? ,CU1.CU_PASSWORD = ? WHERE CU1.CU_NUM = ?";
		
		try {
			this.pstmt = conn.prepareStatement(dml);
			this.pstmt.setNString(1, cu.getCuName());
			this.pstmt.setNString(2, cu.getCuPassword());
			this.pstmt.setNString(3, cu.getCuNum());
						
			result = this.convertToBoolean(this.pstmt.executeUpdate());
			
		} catch (SQLException e) {
			e.printStackTrace();
		}//rs이라던지 닫을게 없어서 finally안쓴다.
		return result;
	}
}

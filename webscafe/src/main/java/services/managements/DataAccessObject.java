package services.managements;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.Customers;
import beans.Deskbean;
import beans.Employees;


public class DataAccessObject extends webscafe.DataAccessObject  {
	private ResultSet rs;
	
	/*회원의 해당에약좌석 정보 가저오기*/
	void getDeskInfo(Connection conn, Deskbean dk) {
		String query = "SELECT \"DATE\",  CUNUM, CUNAME, SENAME, DKCODE   FROM DESKINFO "
				+ "WHERE CUNUM =? AND \"DATE\" = (SELECT MAX(\"DATE\") FROM DESKINFO)";
		
		
		try {
			this.pstmt = conn.prepareStatement(query);
			this.pstmt.setNString(1, dk.getCuNum());
		
			this.rs = this.pstmt.executeQuery();
			while(rs.next()) {
				
				
				dk.setDate(rs.getNString("DATE"));
				dk.setCuName(rs.getNString("CUNAME"));
				dk.setSeName(rs.getNString("SENAME"));
				dk.setDkCode(rs.getNString("DKCODE"));
				//System.out.println(db.getDate());
			}
		}catch(SQLException e){e.printStackTrace();}
		finally {
			try {if(!rs.isClosed()) rs.close();}catch(SQLException e) {e.printStackTrace();}
		}
	}
	
	/*좌석예약 insDesk*/
	
	boolean insDesk(Connection connection, Deskbean dk) {
		boolean result = false;
		String dml = "INSERT INTO RE1(RE_CODE, RE_CUNUM, RE_SECODE, RE_DKCODE, RE_DKSTAT) VALUES(DEFAULT, ?, ?, ? , ?)";
		
		try {
			
//			System.out.println(dk.getCuNum());
//			System.out.println(dk.getSeCode());
//			System.out.println(dk.getDkCode());
//			System.out.println(dk.getDkStat());
			this.pstmt = connection.prepareStatement(dml);
			this.pstmt.setNString(1, dk.getCuNum());
			this.pstmt.setNString(2, dk.getSeCode());
			this.pstmt.setNString(3, dk.getDkCode());
			this.pstmt.setInt(4, dk.getDkStat());
			
		
			
			result = this.convertToBoolean(this.pstmt.executeUpdate());
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {}
		
		return result;
	}
	
	/*지정한 회원이름가져오기 by Hong*/
	
	public ArrayList<Customers> getGdoosInfo(Connection connection, Customers cu) {
		ArrayList<Customers> list = new ArrayList<Customers>();
		//System.out.println(goods.getPrCode());

		String sql = "SELECT CU_NAME AS CUNAME FROM CU1 WHERE CU_NUM = ? ";

		try {
			this.pstmt = connection.prepareStatement(sql);
			this.pstmt.setNString(1, cu.getCuNum());
			
			
			rs = this.pstmt.executeQuery();
			while (rs.next()) {
				Customers e = new Customers();
				e.setCuName(rs.getNString("CUNAME"));
				list.add(e);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (!rs.isClosed())
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}	
	
	/*회원리스트*/
	public ArrayList<Customers> getCustomersList(Connection connection) {
		ArrayList<Customers> list = new ArrayList<Customers>();
		ResultSet rs = null;

		String sql = "SELECT CUNUM, CUNAME, MAX(ACCESSTIME) AS ACCESSTIME, STNAME FROM MLACCESSINFO GROUP BY CUNUM, CUNAME,STNAME ORDER BY CUNAME DESC";
		
		try {
			this.pstmt = connection.prepareStatement(sql);

			rs = this.pstmt.executeQuery();
			while (rs.next()) {
				Customers ct = new Customers();
				ct.setCuNum(rs.getNString("CUNUM"));
				ct.setCuName(rs.getNString("CUNAME"));
				ct.setDate(rs.getNString("ACCESSTIME"));
				ct.setCuStName(rs.getNString("STNAME"));
				list.add(ct);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (!rs.isClosed())
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	/*직원리스트*/
	ArrayList<Employees> getEmployeeList(Connection conn, Employees emp) {
		ArrayList<Employees> list = new ArrayList<Employees>();
		String query = "SELECT EM_SECODE AS SECODE, EM_CODE AS EMCODE, EM_NAME AS EMNAME, EM_PASSWORD AS EMPASSWORD FROM EM1 ";
		try {
			this.pstmt = conn.prepareStatement(query);
			

			this.rs = this.pstmt.executeQuery();
			while (rs.next()) {
				Employees e = new Employees();
				e.setSeCode(rs.getNString("SECODE"));
				e.setEmCode(rs.getNString("EMCODE"));
				e.setEmName(rs.getNString("EMNAME"));
				e.setEmPassword(rs.getNString("EMPASSWORD"));
				list.add(e);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (!rs.isClosed())
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
}

package webscafe;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.Customers;
import beans.Employees;



public class DataAccessObject {
	protected PreparedStatement pstmt;

	protected DataAccessObject() {
		this.pstmt = null;
	}

	/*
	 * Driver Loading & Create Connection 0. Driver Information, url:ip+port+sid,
	 * userId, userPassword 1. Driver Loading 2. Driver Manager를 통한 Connection 생성
	 */
	public Connection getConnection() {
		Connection connection = null;
		String[] url = { "jdbc:oracle:thin:@192.168.0.181:1521:xe", "SCDBA", "1234" };//교실
		//집String[] url = { "jdbc:oracle:thin:@192.168.1.139:1521:xe", "SCDBA", "1234" };
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection(url[0], url[1], url[2]);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return connection;
	}

	/* Transaction 상태 변경 */
	public void modifyTranStatus(Connection connection, boolean status) {
		try {
			if (connection != null && !connection.isClosed()) {
				connection.setAutoCommit(status);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/* Transaction 처리 :: Commit | Rollback */
	public void setTransaction(Connection connection, boolean tran) {

		try {
			if (connection != null && !connection.isClosed()) {
				if (tran) {
					connection.commit();
				} else {
					connection.rollback();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/* Close Connection */
	public void closeConnection(Connection connection) {
		try {
			if (!pstmt.isClosed())
				pstmt.close();
			if (connection != null && !connection.isClosed())
				connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/* convertToBoolean */
	protected boolean convertToBoolean(int value) {
		return (value > 0) ? true : false;
	}

	/* getAccessInfo */
	public ArrayList<Customers> getAccessInfo(Connection connection, Customers cu) {
		ArrayList<Customers> list = new ArrayList<Customers>();
		ResultSet rs = null;
		/* 상점명(상점코드) 회원명(직원코드) 최근접속기록 */
		String sql = "SELECT SECODE, SENAME, CUNUM, CUNAME, ACCESSTIME FROM CUACCESSINFO "
				+ "WHERE ACCESSTIME = (SELECT TO_DATE(MAX(ML_ACCESSTIME), 'YYYY-MM-DD HH24:MI:SS') "
				+ "FROM ML1 WHERE ML_SECODE=? AND ML_CUNUM= ? ) ";
		try {
			this.pstmt = connection.prepareStatement(sql);
			this.pstmt.setNString(1, cu.getSeCode());
			this.pstmt.setNString(2, cu.getCuNum());

			rs = this.pstmt.executeQuery();
			while (rs.next()) {
				Customers c = new Customers();
				c.setSeCode(rs.getNString("SECODE"));
				c.setSeName(rs.getNString("SENAME"));
				c.setCuNum(rs.getNString("CUNUM"));
				c.setCuName(rs.getNString("CUNAME"));
				c.setDate(rs.getNString("ACCESSTIME"));

				list.add(c);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {if (!rs.isClosed())	rs.close();} catch (SQLException e) {e.printStackTrace();}
		}

		return list;
	}

	/* getAccessInfo Employees*/
	public ArrayList<Employees> getAccessInfo(Connection connection, Employees emp) {
		ArrayList<Employees> list = new ArrayList<Employees>();
		ResultSet rs = null;
		/* 상점명(상점코드) 회원명(직원코드) 최근접속기록 */
		String sql = "SELECT SECODE, SENAME, EMCODE, EMNAME, ACCESSTIME FROM EMACCESSINFO "
				+ "WHERE ACCESSTIME = (SELECT TO_DATE(MAX(EL_ACCESSTIME), 'YYYY-MM-DD HH24:MI:SS') "
				+ "FROM EL1 WHERE EL_SECODE= ? AND EL_EMCODE= ? ) ";
		try {
			this.pstmt = connection.prepareStatement(sql);
			this.pstmt.setNString(1, emp.getSeCode());
			this.pstmt.setNString(2, emp.getEmCode());

			rs = this.pstmt.executeQuery();
			while (rs.next()) {
				Employees em = new Employees();
				em.setSeCode(rs.getNString("SECODE"));
				em.setSeName(rs.getNString("SENAME"));
				em.setEmCode(rs.getNString("EMCODE"));
				em.setEmName(rs.getNString("EMNAME"));
				em.setEmDate(rs.getNString("ACCESSTIME"));

				list.add(em);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {if (!rs.isClosed())	rs.close();} catch (SQLException e) {e.printStackTrace();}
		}

		return list;
	}	
	

}

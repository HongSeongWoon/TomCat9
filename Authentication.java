package services.auth;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import beans.Action;
import beans.Employee;


public class Authentication {
	private HttpServletRequest req;
	private Employee emp;
	
	public Authentication(HttpServletRequest req) {
		this.req=req;
	}
	public Action backController(int jobCode) {
		Action action = null;
		switch(jobCode) {
		case 1:
			action= this.accessCtl();
			break;
		case -1:
			action= this.accessOutCtl();;
			break;
		default :
		}
		return action;
	}
	private Action accessCtl() {
		Action action = new Action();
		ArrayList<Employee> list = null;
		DataAccessObject dao = null;
		boolean tran = false;
		
		//1. 클라이언트 데이터 --> Employee :: seCode, emCode, emPassword 지금은 알려고 배우는 과정이라 이렇게 한다.
		this.emp = new Employee();
		this.emp.setSeCode(this.req.getParameter("seCode"));
		this.emp.setEmCode(this.req.getParameter("emCode"));
		this.emp.setEmPassword(this.req.getParameter("emPassword"));
		this.emp.setAccessType(9);
		/* 2. DAO 연동*/ 
		dao = new DataAccessObject();
		Connection conn = dao.getConnection();
		dao.modifyTranStatus(conn, false);
		/*   2-1. STORES :: SECODE 존재 여부*/
		 //*   2-2. EMPLOYEE :: EMCODE 존재 여부
		 //*   2-3. EMPLOYEE :: PASSWORD일치 여부 :: RETURN : 1  >> P2-4 
		 //*   2-4. ACCESSHISTORY : INSERT :: RETURN : 1
		 //*   2-5. 정보 취합  --> ARRAYLIST<EMPLOYEE>
		    
		 //*   *** 로그인 성공 :: main.jsp
		 //*       로그인 실패 :: index.html
		 
		if(dao.isSeCode(conn, emp)) {
			if(dao.isEmployee(conn, emp)) {
				if(dao.insAccessHistory(conn, emp)) {
					if((list=dao.getAccessInfo(conn,emp)) != null) {
						tran = true;
						req.setAttribute("accessInfo", list);
					}
				}
			}
		}
		
		action.setPage(tran?"main.jsp":"index.html");
		action.setRedirect(tran?false: true);
		
		dao.setTransaction(conn, tran);
		dao.modifyTranStatus(conn, true);
		dao.closeConnection(conn);
		
		return action;
	}
	private Action accessOutCtl() {
		Action action = new Action();
		DataAccessObject dao = new DataAccessObject();
		boolean tran = false;
		//1. 클라이언트 데이터 --> Employee
		this.emp = new Employee();
		this.emp.setSeCode(this.req.getParameter("seCode"));
		this.emp.setEmCode(this.req.getParameter("emCode"));
		this.emp.setAccessType(-9);
			
		
		//2. DAO 연동
		dao = new DataAccessObject();
		Connection conn = dao.getConnection();
		dao.modifyTranStatus(conn, false);
		
		if(dao.insAccessHistory(conn, emp)) {
			tran = true;
		}
		action.setPage("index.html");
		action.setRedirect(true);
		dao.setTransaction(conn, tran);
		dao.modifyTranStatus(conn, true);
		dao.closeConnection(conn);
		return action;
	}
	
	
}

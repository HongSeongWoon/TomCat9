package services.auth;

import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import beans.Action;
import beans.Customers;
import beans.Employees;



public class Authentication {
	private HttpServletRequest req;
	private HttpSession session;
	private Customers cu;
	private Employees emp;

	
	public Authentication(HttpServletRequest req) {
		this.req=req;
	}
	
	public Action backController(int jobCode) {
		Action action = null;
		switch (jobCode) {
		case 1:
			action = this.accessCtl();
			break;
		case -1:
			action = this.accessOutCtl();
			break;
		case 2:
			action = this.emAccessCtl();
			break;
		case -2:
			action = this.emAccessOutCtl();
			break;
		case 3:
			action = this.getEmpCode();
			break;
		case 4:
			action = this.regEmp();
			break;
		case 14:
			action = this.regMember();
			break;
		case 22:
			action = this.delMember();
			break;
		case 23:
			action = this.modMember();
			break;	
//		case 0:
//			action = this.afterAccess();
//			break;
		default:
		}
		return action;
	}
	
	/*직원로그아웃*/
	private Action emAccessOutCtl() {
		Action action = new Action();
		ArrayList<Employees> list = null;
		DataAccessObject dao = null;
		boolean tran = false;
		
		
		// 1. 클라이언트 데이터 --> employees :: seCode, emCode
		this.emp = new Employees();
		
		this.emp.setSeCode(this.req.getParameter("seCode"));
		this.emp.setEmCode(this.req.getParameter("emCode"));
		this.emp.setEmStCode(-4);
		
		/* 2. DAO 연동 */
		dao = new DataAccessObject();
		Connection conn = dao.getConnection();
		dao.modifyTranStatus(conn, false);
		
		if (dao.insEmployeelogin(conn, emp)) {
					
			tran = true;
			//session.invalidate();
					
		}
				
		action.setPage("main.html");
		action.setRedirect(true);

		dao.setTransaction(conn, tran);
		dao.modifyTranStatus(conn, true);
		dao.closeConnection(conn);

		return action;
	}

	/*회원정보수정 :: 회원이름 회원비밀번호*/
	private Action modMember() {
		Action action = new Action();
		String data = null;
		boolean tran = false;
		
		Customers cu = new Customers();
		cu.setCuNum(this.req.getParameter("cuNum"));
		cu.setCuName(this.req.getParameter("cuName"));
		cu.setCuPassword(this.req.getParameter("cuPassword"));
				
		DataAccessObject dao = new DataAccessObject();
		Connection conn = dao.getConnection();
		dao.modifyTranStatus(conn, false);
		
		if(dao.modMember(conn, cu)) {
			tran = true;
			
		}
		dao.setTransaction(conn, tran);//dml이라서 트랜젝션을 해야한다.
		dao.modifyTranStatus(conn, true);//오토커밋 원상복귀
		dao.closeConnection(conn);
		
		action.setPage("MemberMGM.jsp");
		action.setRedirect(false);
		
		return action;
	}

	/*회원삭제*/
	private Action delMember() {
		Action action = new Action();
		String data = null;
		boolean tran = false;
		
		Customers cu = new Customers();
		cu.setCuNum(this.req.getParameter("cuNum"));
				
		DataAccessObject dao = new DataAccessObject();
		Connection conn = dao.getConnection();
		dao.modifyTranStatus(conn, false);
		
		if(dao.delMember(conn, cu)) {
			tran = true;
			
		}
		dao.setTransaction(conn, tran);//dml이라서 트랜젝션을 해야한다.
		dao.modifyTranStatus(conn, true);//오토커밋 원상복귀
		dao.closeConnection(conn);
		
		action.setPage("MemberMGM.jsp");
		action.setRedirect(false);
		
		return action;
	}

	/*회원등록*/
	private Action regMember() {
		Action action = new Action();
		String data = null;
		boolean tran = false;
		
		Customers cu = new Customers();
		cu.setCuNum(this.req.getParameter("cuNum"));
		//System.out.println(this.req.getParameter("seCode"));
		cu.setCuName(this.req.getParameter("cuName"));
		cu.setCuPassword(this.req.getParameter("cuPassword"));
		
		
		DataAccessObject dao = new DataAccessObject();
		Connection conn = dao.getConnection();
		dao.modifyTranStatus(conn, false);
		
		if(dao.insMember(conn, cu)) {
			tran = true;
			
		}
		dao.setTransaction(conn, tran);//dml이라서 트랜젝션을 해야한다.
		dao.modifyTranStatus(conn, true);//오토커밋 원상복귀
		dao.closeConnection(conn);
		
		action.setPage("MemberMGM.jsp");
		action.setRedirect(false);
		
		return action;
	}

	/*직원등록*/
	private Action regEmp() {
		Action action = new Action();
		String data = null;
		boolean tran = false;
		
		Employees emp = new Employees();
		emp.setSeCode(this.req.getParameter("seCode"));
		//System.out.println(this.req.getParameter("seCode"));
		emp.setEmCode(this.req.getParameter("emCode"));
		emp.setEmName(this.req.getParameter("emName"));
		emp.setEmPassword(this.req.getParameter("emPassword"));
		
		
		DataAccessObject dao = new DataAccessObject();
		Connection conn = dao.getConnection();
		dao.modifyTranStatus(conn, false);
		
		if(dao.insEmp(conn, emp)) {
			tran = true;
			
		}
		dao.setTransaction(conn, tran);//dml이라서 트랜젝션을 해야한다.
		dao.modifyTranStatus(conn, true);//오토커밋 원상복귀
		dao.closeConnection(conn);
		
		action.setPage("EmployList.jsp");
		action.setRedirect(false);
		
		return action;
	}
	
	/*getEmpCode*/
	private Action getEmpCode() {
		Action action = new Action();
		Employees emp = new Employees();
		//System.out.println(this.req.getParameter("seCode"));
				
		DataAccessObject dao = new DataAccessObject();		
		Connection conn = dao.getConnection();
		
		/* dao :: 해당 상점의 직원코드중 최대값 가져오기*/
	    dao.getMaxEmpCode(conn, emp);
	    System.out.println(emp.getEmCode());
		this.req.setAttribute("EmpCode", emp.getEmCode());
		/* dao :: 최대값으로 직원 생성 가져오기 :: 추후 */
		
		dao.closeConnection(conn);
		
		/* 직원 등록 양식 작성 후 Return*/
		
		
		action.setPage("EmployeeREG.jsp");
		action.setRedirect(false);
		
		return action;
	}

	private Action emAccessCtl() {
		Action action = new Action();
		ArrayList<Employees> list = null;
		DataAccessObject dao = null;
		boolean tran = false;
		
		
		// 1. 클라이언트 데이터 --> Employees :: seCode, emCode, emPassword 
		this.emp = new Employees();
		this.emp.setSeCode(this.req.getParameter("seCode"));
		this.emp.setEmCode(this.req.getParameter("emCode"));
		this.emp.setEmPassword(this.req.getParameter("emPassword"));
		this.emp.setEmStCode(4);
		
		/* 2. DAO 연동 */
		dao = new DataAccessObject();
		Connection conn = dao.getConnection();
		dao.modifyTranStatus(conn, false);
		// * 2-1. seCode 존재여부
		// * 2-2. Employees :: emCode 존재 여부
		// * 2-3. Employees 	 :: PASSWORD일치 여부 :: RETURN : 1 >> P2-4
		// * 2-4. MEMBERLOGIN : INSERT :: RETURN : 1
		// * 2-5. 정보 취합 --> ARRAYLIST<Employees>

		// * *** 로그인 성공 :: MAINSTOREMGM.html
		// * 로그인 실패 :: index.html
		
		
		if (dao.isSeCode(conn, emp)) {
			
			if (dao.isEmployees(conn, emp)) {
				
				if (dao.insEmployeelogin(conn, emp)) {
					
					tran = true;
					session = this.req.getSession();
					session.setAttribute("seCode",emp.getSeCode());
					session.setAttribute("emCode",emp.getEmCode());
					if ((list = dao.getAccessInfo(conn, emp)) != null) {
						
						req.setAttribute("accessInfo", list);
						session.setAttribute("accessInfo", list);	
					}
				}
			}
		}
		
		action.setPage(tran ? "MemberMGM.jsp" : "EmployLOGIN.html");
		//action.setPage1("MemberREG.html"); 
		action.setRedirect(tran ? false : true);

		dao.setTransaction(conn, tran);
		dao.modifyTranStatus(conn, true);
		dao.closeConnection(conn);

		return action;
	}

	private Action accessCtl() {
		Action action = new Action();
		ArrayList<Customers> list = null;
		DataAccessObject dao = null;
		boolean tran = false;
		
		
		// 1. 클라이언트 데이터 --> Customers :: cuNum, cuName, cuPassword 
		this.cu = new Customers();
		this.cu.setSeCode(this.req.getParameter("seCode"));
		this.cu.setCuNum(this.req.getParameter("cuNum"));
		this.cu.setCuPassword(this.req.getParameter("cuPassword"));
		this.cu.setCuStCode(3);
		
		/* 2. DAO 연동 */
		dao = new DataAccessObject();
		Connection conn = dao.getConnection();
		dao.modifyTranStatus(conn, false);
		
		// * 2-2. CUSTOMERS :: CUNUM 존재 여부
		// * 2-3. CUSTOMERS 	 :: PASSWORD일치 여부 :: RETURN : 1 >> P2-4
		// * 2-4. MEMBERLOGIN : INSERT :: RETURN : 1
		// * 2-5. 정보 취합 --> ARRAYLIST<Customers>

		// * *** 로그인 성공 :: main.jsp
		// * 로그인 실패 :: index.html
		
		
		if (dao.isSeCode(conn, cu)) {
			
			if (dao.isCustomers(conn, cu)) {
				
				if (dao.insMemberlogin(conn, cu)) {
					
					tran = true;
//					session = this.req.getSession();
//					session.setAttribute("seCode",cu.getSeCode());
//					session.setAttribute("emCode",cu.getCuNum());
					if ((list = dao.getAccessInfo(conn, cu)) != null) {
						
						req.setAttribute("accessInfo", list);
						
					}
				}
			}
		}
		
		action.setPage(tran ? "selectDesk.jsp" : "index.html");
		action.setRedirect(tran ? false : true);

		dao.setTransaction(conn, tran);
		dao.modifyTranStatus(conn, true);
		dao.closeConnection(conn);

		return action;
	}
	
	private Action accessOutCtl() {
		Action action = new Action();
		ArrayList<Customers> list = null;
		DataAccessObject dao = null;
		boolean tran = false;
		
		
		// 1. 클라이언트 데이터 --> Customers :: cuNum, cuNum
		this.cu = new Customers();
		
		this.cu.setSeCode(this.req.getParameter("seCode"));
		this.cu.setCuNum(this.req.getParameter("cuNum"));
		this.cu.setCuStCode(-3);
		
		/* 2. DAO 연동 */
		dao = new DataAccessObject();
		Connection conn = dao.getConnection();
		dao.modifyTranStatus(conn, false);
		
		if (dao.insMemberlogin(conn, cu)) {
					
			tran = true;
			//session.invalidate();
					
		}
				
		action.setPage("main.html");
		action.setRedirect(true);

		dao.setTransaction(conn, tran);
		dao.modifyTranStatus(conn, true);
		dao.closeConnection(conn);

		return action;
	}
}

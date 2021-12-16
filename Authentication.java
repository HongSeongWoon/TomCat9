package services.auth;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import beans.Action;
import beans.Employee;

public class Authentication {
	private HttpServletRequest req;
	private Employee emp;
	private Action action = null;
	public Authentication(HttpServletRequest req) {
		this.req=req;
		this.action = new Action();
	}
	public Action backController(int jobCode) {
		
		switch(jobCode) {
		case 1:
			this.action= this.accessCtl();
			break;
		case -1:
			this.action= this.accessOutCtl();;
			break;
		default :
		}
		return action;
	}
	private Action accessCtl() {
		ArrayList<Employee> list = null;
		//1. 클라이언트 데이터 --> Employee :: seCode, emCode, emPassword 지금은 알려고 배우는 과정이라 이렇게 한다.
		this.emp = new Employee();
		this.emp.setSeCode(this.req.getParameter("seCode"));
		this.emp.setEmCode(this.req.getParameter("emCode"));
		this.emp.setEmPassword(this.req.getParameter("emPassword"));
		
		/* 2. DAO 연동 
		 *   2-1. STORES :: SECODE 존재 여부
		 *   2-2. EMPLOYEE :: EMCODE 존재 여부
		 *   2-3. EMPLOYEE :: PASSWORD일치 여부 :: RETURN : 1  >> P2-4 
		 *   2-4. ACCESSHISTORY : INSERT :: RETURN : 1
		 *   2-5. 정보 취합  --> ARRAYLIST<EMPLOYEE>
		 *   
		 *   *** 로그인 성공 :: main.jsp
		 *       로그인 실패 :: index.html
		 * */
		/*TEST*/
		emp.setEmName("테스트 직원");
		emp.setDate("202112161430");
		list = new ArrayList<Employee>();
		list.add(emp);
		
		req.setAttribute("accessInfo", list.get(0).getEmName() + "님은 " + list.get(0).getDate() + "시각에 로그인 하셨습니다.");
		req.setAttribute("seCode", list.get(0).getSeCode());
		req.setAttribute("emCode", list.get(0).getEmCode());
		
		if(list != null) {
			action.setRedirect(false);
			action.setPage("main.jsp");
		}else {
			action.setRedirect(true);
			action.setPage("index.html");
		}
		
		return action;
	}
	private Action accessOutCtl() {
		ArrayList<Employee> list = null;
		
		list = new ArrayList<Employee>();
		
		
		//1. 클라이언트 데이터 --> Employee
		this.emp = new Employee();
		this.emp.setSeCode(this.req.getParameter("seCode"));
		this.emp.setEmCode(this.req.getParameter("emCode"));
		list.add(emp);
		System.out.println(emp.getSeCode()+":"+emp.getEmCode());
		if(list != null) {
			action.setRedirect(true);
			action.setPage("index.html");
		}else {
			action.setRedirect(false);
			action.setPage("index.html");
		}
		//2. DAO 연동
		
		return action;
	}
}

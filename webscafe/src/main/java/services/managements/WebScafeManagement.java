package services.managements;

import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import beans.Action;
import beans.Customers;
import beans.Deskbean;
import beans.Employees;

public class WebScafeManagement {
	
	private HttpServletRequest req;
	private HttpSession session;
	
	public WebScafeManagement(HttpServletRequest req) {
		this.req = req;
	}
	
	public Action backController(int jobCode) {
		Action action = null;
		switch (jobCode) {
		
		case 5:
			action = getEmpList();
			break;
		case 6:
			action = getMemberList();
			break;
		default:
		}
		return action;
	}
	
	public String backController(String jobCode) {
		String form = null;
		switch(jobCode) {
		case "21":
			form = this.memberInfo();
			break;
		case "30":
			form = this.reserveDesk();
			break;
		case "31":
			form = this.cancleDesk();
			break;
		}
		
		return form;
	}
	/*좌석취소*/	
	private String cancleDesk() {
		String jsonData = null;
		boolean tran = false;
		Deskbean dk = new Deskbean();
		String message = null;
		
		dk.setCuNum(this.req.getParameter("cuNum"));
		dk.setSeCode(this.req.getParameter("seCode"));
		dk.setDkCode(this.req.getParameter("dkCode"));
		dk.setDkStat(2);
		
		DataAccessObject dao = new DataAccessObject();
		Connection conn = dao.getConnection();
		dao.modifyTranStatus(conn, false);
		
		if(dao.insDesk(conn, dk)) {
			tran=true;
			dao.getDeskInfo(conn, dk);
			if(dk.getSeCode()!=null) {
				
				message ="지점 : " + dk.getSeName()+"&"+"성명 : " + dk.getCuName()+"&"+"좌석 : " + dk.getDkCode()+"&"+"예약시간 : "+dk.getDate()+"&예약이 취소되었습니다.";
			}
		}
		
		dao.setTransaction(conn, tran);
		dao.modifyTranStatus(conn, true);				
		dao.closeConnection(conn);
		
		return new Gson().toJson(message);
	}

	/*좌석예약*/
	private String reserveDesk() {
		String jsonData = null;
		boolean tran = false;
		Deskbean dk = new Deskbean();
		String message = null;
		
		dk.setCuNum(this.req.getParameter("cuNum"));
		dk.setSeCode(this.req.getParameter("seCode"));
		dk.setDkCode(this.req.getParameter("dkCode"));
		dk.setDkStat(-2);
		
		DataAccessObject dao = new DataAccessObject();
		Connection conn = dao.getConnection();
		dao.modifyTranStatus(conn, false);
		
		if(dao.insDesk(conn, dk)) {
			tran=true;
			dao.getDeskInfo(conn, dk);
			if(dk.getSeCode()!=null) {
				
				message ="지점 : " + dk.getSeName()+"&"+"성명 : " + dk.getCuName()+"&"+"좌석 : " + dk.getDkCode()+"&"+"예약시간 : "+dk.getDate()+"&예약에 성공하셨습니다.";
			}
		}
		
		dao.setTransaction(conn, tran);
		dao.modifyTranStatus(conn, true);				
		dao.closeConnection(conn);
		
		return new Gson().toJson(message);
	}

	private String memberInfo() {
		String jsonData = null;
		Customers cu = new Customers();
		//System.out.println(this.req.getParameter("prCode"));
		cu.setCuNum(this.req.getParameter("cuNum"));
		
		
		DataAccessObject dao = new DataAccessObject();
		Connection conn = dao.getConnection();
		jsonData = new Gson().toJson(dao.getGdoosInfo(conn, cu));
		//System.out.println(jsonData);
		dao.closeConnection(conn);
		
	
		return jsonData;
	}

	/*getMemberList*/
	private Action getMemberList() {
		Action action = new Action();
		Customers cu = new Customers();
		
		
		DataAccessObject dao = new DataAccessObject();
		Connection conn = dao.getConnection();
		/* 회원리스트 정보*/
		this.req.setAttribute("memberlist", makeHtmlCustomerList(dao.getCustomersList(conn))); 
		/* 로그인 정보 :: accessInfo 
		this.req.setAttribute("accessInfo", dao.getAccessInfo(conn, emp));*/
		/* 이벤트를 발생 시킨 object 이름 
		this.req.setAttribute("objName", "memberManagements");*/
				
		dao.closeConnection(conn);
		
		action.setPage("MemberMGM.jsp");
		action.setRedirect(false);
		return action;
	}
	
	/* GetEmpList */
	private Action getEmpList() {
		Action action = new Action();
				
		Employees emp = new Employees();
		
		
		DataAccessObject dao = new DataAccessObject();
		Connection conn = dao.getConnection();
		/* 직원리스트 정보*/
		this.req.setAttribute("list", makeHtmlFromList(dao.getEmployeeList(conn, emp))); 
		/* 로그인 정보 :: accessInfo */
		this.req.setAttribute("accessInfo", dao.getAccessInfo(conn,emp));
		/* 이벤트를 발생 시킨 object 이름
		this.req.setAttribute("objName", "empManagements");*/
		dao.closeConnection(conn);
		
		action.setPage("EmployList.jsp");
		action.setRedirect(false);
		
		return action;
	}
	
	private String makeHtmlFromList(ArrayList<Employees> list) {
		   
		StringBuffer sb = new StringBuffer();
		sb.append("<table id=\"emptable\">");
		sb.append("<tr>");
		sb.append("<td class=\"empfirstrow\">직원코드</td>");
		sb.append("<td class=\"empfirstrow\">직원성명</td>");
		sb.append("<td class=\"empfirstrow\">비밀번호</td>");
		sb.append("</tr>");
		for(Employees emp:list) {
			sb.append("<tr>");
			sb.append("<td class=\"empContent\">"+ emp.getEmCode() + "</td>");
			sb.append("<td class=\"empContent\">"+ emp.getEmName() + "</td>");
			sb.append("<td class=\"empContent\">"+ emp.getEmPassword() + "</td>");
			sb.append("</tr>");
		}
		
		return sb.toString();
	}
	
	private String makeHtmlCustomerList(ArrayList<Customers> list) {
		 String date, stName;  
		StringBuffer sb = new StringBuffer();
		sb.append("<table id=\"cutable\">");
		sb.append("<tr>");
		sb.append("<td class=\"cufirstrow\">회원코드</td>");
		sb.append("<td class=\"cufirstrow\">회원이름</td>");
		sb.append("<td class=\"cufirstrow\">회원최근접속기록</td>");
		sb.append("<td class=\"cufirstrow\">회원접속상태</td>");
		sb.append("</tr>");
		for(Customers cu:list) {
			sb.append("<tr>");
			sb.append("<td class=\"cuContent\">"+ cu.getCuNum() + "</td>");
			sb.append("<td class=\"cuContent\">"+ cu.getCuName() + "</td>");
			sb.append("<td class=\"cuContent\">"+ (cu.getDate().equals("0")? "접속기록없음" : cu.getDate())  + "</td>");
			sb.append("<td class=\"cuContent\">"+ (cu.getCuStName().equals("0")? "접속기록없음" : cu.getCuStName())+ "</td>");
			
			sb.append("</tr>");
		}
		
		return sb.toString();
	}
	
	
}

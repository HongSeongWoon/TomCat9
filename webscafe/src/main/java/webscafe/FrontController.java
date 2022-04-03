package webscafe;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Action;
import services.auth.Authentication;


@WebServlet({"/Access","/MemberLogin","/EmployeeLogin","/CafeSelect","/EmAccess","/EmployeeReg","/GetEmpCode","/RegEmp"
			,"/MoveRM","/RegMember","/MoveMod","/MoveDel","/MoveInfo","/DelMember","/ModMember"
			,"/AccessOut","/EmAccessOut","/MoveEmpList"})
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public FrontController() {
        super();
       
    }

	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   
    	String jobCode = request.getRequestURI().substring(request.getContextPath().length()+1);
    	
    			if(jobCode.equals("AccessOut")) {
			this.doProcess(request,response);
		}else {
			response.sendRedirect("MEMBERLOGIN.html");
		}
		
    	
    	
	}




protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	request.setCharacterEncoding("UTF-8");
	this.doProcess(request,response);
}
private void doProcess(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	Action action = null;
	String jobCode = req.getRequestURI().substring(req.getContextPath().length()+1);
	System.out.println(jobCode);
	HttpSession session = req.getSession();
	Authentication auth = null;
	
	if (jobCode.equals("MemberLogin")) {
		action = new Action();
		action.setRedirect(true);
		action.setPage("MEMBERLOGIN.html");
	} else if (jobCode.equals("EmployeeLogin")) {
		action = new Action();
		action.setRedirect(true);
		action.setPage("EmployLOGIN.html");
	} else if (jobCode.equals("MoveEmpList")) {
		action = new Action();
		action.setRedirect(true);
		action.setPage("EmployList.jsp");
	}else if (jobCode.equals("CafeSelect")) {
		action = new Action();
		action.setRedirect(true);
		action.setPage("cafeSelect.html");
	}else if(jobCode.equals("Access")){
		auth = new Authentication(req);
		action = auth.backController(1);
	}else if(jobCode.equals("AccessOut")){
		auth = new Authentication(req);
		action = auth.backController(-1);
	}else if(jobCode.equals("EmAccessOut")){
		auth = new Authentication(req);
		action = auth.backController(-2);
	}else if(jobCode.equals("EmAccess")){
		auth = new Authentication(req);
		action = auth.backController(2);
	}else if(jobCode.equals("EmployeeReg")){
		action = new Action();
		action.setRedirect(true);
		action.setPage("EmployeeREG.jsp");
	}else if(jobCode.equals("GetEmpCode")){
		auth = new Authentication(req);
		action = auth.backController(3);
	}else if(jobCode.equals("RegEmp")){
		auth = new Authentication(req);
		action = auth.backController(4);
	}else if (jobCode.equals("MoveRM")) {
		action = new Action();
		action.setRedirect(true);
		action.setPage("MemberREG.jsp");
	}else if (jobCode.equals("MoveMod")) {
		action = new Action();
		action.setRedirect(true);
		action.setPage("MemberMOD.jsp");
	}else if (jobCode.equals("MoveDel")) {
		action = new Action();
		action.setRedirect(true);
		action.setPage("MemberDEL.jsp");
	}else if (jobCode.equals("MoveInfo")) {
		action = new Action();
		action.setRedirect(true);
		action.setPage("MemberMGM.jsp");
	}else if(jobCode.equals("RegMember")){
		auth = new Authentication(req);
		action = auth.backController(14);
	}else if(jobCode.equals("DelMember")){
		auth = new Authentication(req);
		action = auth.backController(22);
	}else if(jobCode.equals("ModMember")){
		auth = new Authentication(req);
		action = auth.backController(23);
	}
	else {
		action = new Action();
		action.setRedirect(true);
		action.setPage("main.html");
	}
	
	if(action.isRedirect()) {
		res.sendRedirect(action.getPage());
	}else {
		RequestDispatcher dp= req.getRequestDispatcher(action.getPage());
		dp.forward(req, res);
	}
	
}
}

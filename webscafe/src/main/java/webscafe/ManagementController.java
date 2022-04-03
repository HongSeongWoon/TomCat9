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
import services.managements.WebScafeManagement;



@WebServlet({"/EmpList","/MlAccessInfo"})
public class ManagementController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public ManagementController() {
        super();
        // TODO Auto-generated constructor stub
    }

	
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		this.doProcess(request, response);
	}

	private void doProcess(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Action action = null;
		String jobCode = req.getRequestURI().substring(req.getContextPath().length()+1);
		System.out.println(jobCode);
		HttpSession session = req.getSession();
		WebScafeManagement wsm = null;
		

		 if(jobCode.equals("EmpList")){
			wsm = new WebScafeManagement(req);
			action = wsm.backController(5);
		}else if(jobCode.equals("MlAccessInfo")){
			wsm = new WebScafeManagement(req);
			action = wsm.backController(6);
		}
		else {
			action = new Action();
			action.setRedirect(true);
			action.setPage("main.html");
		}
		if (action.isRedirect()) {
			res.sendRedirect(action.getPage());
		} else {
			RequestDispatcher dp = req.getRequestDispatcher(action.getPage());
			dp.forward(req, res);
		}

	}

	
}

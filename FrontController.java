package webpos;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Action;
import services.auth.Authentication;


@WebServlet({"/Access","/AccessOut"})
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	public FrontController() {
		super();

	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		this.doProcess(request,response);	

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		this.doProcess(request,response);
	}
	private void doProcess(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Action action = null;
		String jobCode = req.getRequestURI().substring(req.getContextPath().length()+1);

		Authentication auth = null;
		
		if(jobCode.equals("Access")) {
			//Auth :: 1
			auth = new Authentication(req);
			action = auth.backController(1);
			
		}else if(jobCode.equals("AccessOut")){
			//Auth :: -1
			auth = new Authentication(req);
			action =auth.backController(-1);
		}else {

		}

		if(action.isRedirect()) {
			res.sendRedirect(action.getPage());
		}else {
			RequestDispatcher dp= req.getRequestDispatcher(action.getPage());
			dp.forward(req, res);
		}
	}
}

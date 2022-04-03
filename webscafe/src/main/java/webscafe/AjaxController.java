package webscafe;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;


import services.managements.WebScafeManagement;



@WebServlet({"/SearchMember","/ReserveDesk","/CancelDesk"})
public class AjaxController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public AjaxController() {
        super();
       
    }

	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doAjax(request, response);
	}
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		this.doAjax(request, response);
	}
	

    private void doAjax(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
		String ajaxData = null;
		String jobCode = req.getRequestURI().substring(req.getContextPath().length()+1);
		System.out.println(jobCode);
		HttpSession session = req.getSession();
		Gson gson = new Gson();
		WebScafeManagement wsm = null;
		
		if(jobCode.equals("SearchMember")){
			wsm = new WebScafeManagement(req);
			ajaxData = wsm.backController("21");
		}else if(jobCode.equals("ReserveDesk")){
			wsm = new WebScafeManagement(req);
			ajaxData = wsm.backController("30");
		}else if(jobCode.equals("CancelDesk")){
			wsm = new WebScafeManagement(req);
			ajaxData = wsm.backController("31");
		}
		
		
		
		
//		if(session.getAttribute("seCode") != null) {
//			
//		
//		}else {
//			
//		}
		//System.out.println(ajaxData);
		res.setContentType("text/html; charset=utf-8");
		PrintWriter p = res.getWriter();
		p.write(ajaxData);
	}
}
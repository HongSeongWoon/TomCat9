package webpos;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Access")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public FrontController() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Get 방식으로 전송");
		//esponse.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		System.out.println(request.getParameter("mId"));
//		System.out.println(request.getParameter("mPassword"));
		//doGet(request, response);
		// id --  hoonzzang pwd - 1234 --> main.jsp
		//							x --> index.html
		if(request.getParameter("mId").equals("hoonzzang")&&request.getParameter("mPassword").equals("1234")) {
			response.sendRedirect("main.jsp");
		}else {
			response.sendRedirect("index.html");
		}
	}

}

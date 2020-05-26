	package newpackage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int flag=0;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		//make user object
		User userModel = new User(name, email, password);

		//create a database model
		UserDatabase regUser = new UserDatabase(ConnectionPro.getConnection());
		if (regUser.saveUser(userModel)) {
			flag=1;
			 PrintWriter out = response.getWriter();
			 //out.println("User Registration Successfull....");
			 String site = new String("index.jsp");

	  	      response.setStatus(response.SC_MOVED_TEMPORARILY);
	  	      response.setHeader("Location", site);   
			 out.println("<html>\r\n" + 
			 		"<body>\r\n" + 
			 		"<script>\r\n" + 
			 		"  alert(\"Hello\\nHow are you?\");\r\n" + 
			 		"</script>\r\n" + 
			 		"\r\n" + 
			 		"</body>\r\n" + 
			 		"</html>\r\n" 
			 		);
		  // response.sendRedirect("index.jsp");
		} else {
		    String errorMessage = "User Available";
		    HttpSession regSession = request.getSession();
		    regSession.setAttribute("RegError", errorMessage);
		    flag=0;
		   // response.sendRedirect("register.jsp");
		    String site = new String("register.jsp");

  	      response.setStatus(response.SC_MOVED_TEMPORARILY);
  	      response.setHeader("Location", site);   
		}
		    }

}

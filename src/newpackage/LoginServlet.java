package newpackage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		   String logemail = request.getParameter("email");
           String logpass = request.getParameter("password");
           PrintWriter out = response.getWriter();
           Connection con=ConnectionPro.getConnection();
           UserDatabase db =  new UserDatabase(con);
           User user = db.logUser(logemail, logpass);
           
           if(user!=null){
        	   //out.println("user is found");
        	  try {
	        	  String query="select * from user where email!=?";
	        	  PreparedStatement pst = con.prepareStatement(query);
	        	  pst.setString(1,logemail);
	              ResultSet rs = pst.executeQuery();
	             
	            	  out.println("<html>\r\n" + 
	            	  		"<head>\r\n" + 
	            	  		"	<title></title>\r\n" + 
	            	  		"</head>\r\n" + 
	            	  		"<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css\">\r\n" + 
	            	  		"  <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js\"></script>\r\n" + 
	            	  		"  <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js\"></script>\r\n" + 
	            	  		"<body>\r\n" + 
	            	  		"<table class=\"table table-striped\">\r\n" + 
	            	  		"  <thead>\r\n" + 
	            	  		"    <tr>\r\n" + 
	            	  		"      <th scope=\"col\">ID</th>\r\n" + 
	            	  		"      <th scope=\"col\">Name</th>\r\n" + 
	            	  		"      <th scope=\"col\">Email ID</th>\r\n" +  
	            	  		"    </tr>\r\n" + 
	            	  		"  </thead>"+
	            	  		" <tbody>\r\n");
	            	  int i=1;
	            	  while(rs.next())
		              {
	            	  out.println(
	            	  		"    <tr>\r\n" + 
	            	  		"      <th scope=\"row\">"+
	            	  				i+
	            	  				"</th>\r\n"  
	            	  		);
	            	 
	            	  out.println(
	            	  		"<td>"+rs.getString(2)+"</td>\r\n"+
	            			"<td>"+rs.getString(3)+"</td>\r\n"
	            	  		);
	            	  out.println("    </tr>"
          	  				);
	            	  i++;
		              }
	            	  out.println(" </tbody>\r\n" +
	            			  "</table>\r\n" + 
	          	  				"\r\n" +
          	  				"</body>\r\n" + 
          	  				"</html>");
        	  }
        	  catch(Exception e){
                  e.printStackTrace();
              }
              /* HttpSession session = request.getSession();
               session.setAttribute("logUser", user);
               response.sendRedirect("welcome.jsp");*/
           }else{
               //out.println("user not found");
        	 out.println("<html>\r\n" + 
   			 		"<body>\r\n" + 
   			 		"<script>\r\n" + 
   			 		"  alert(\"Please Enter correct Email or Password\");\r\n" + 
   			 		"</script>\r\n" + 
   			 		"\r\n" + 
   			 		"</body>\r\n" + 
   			 		"</html>\r\n" 
   			 		);
        	   String site = new String("index.jsp");

        	      response.setStatus(response.SC_MOVED_TEMPORARILY);
        	      response.setHeader("Location", site);   
           }
	}

}

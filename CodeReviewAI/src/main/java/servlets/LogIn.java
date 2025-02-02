package servlets;


import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;

import database.DataBase;

@WebServlet("/loginServlet")

public class LogIn extends HttpServlet{
	protected void doPost(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException{
		String email=req.getParameter("email");
		String password=req.getParameter("password");
		
		PrintWriter out=resp.getWriter();
		DataBase db=new DataBase();
		boolean user=db.checkUser(email, password);
		if(user) {
			resp.setContentType("text/html");
			out.println(" <script type='text/javascript'>   ");
			out.println("alert('Logged in Successfully!!!!');");
			out.println("window.location.href='home.jsp'");
			out.println("</script>");
			
		}
		else {
			resp.setContentType("text/html");
			out.println("<script type='text/javascript'>");
			out.println("alert('Log in Failed!!!!');");
			out.println("window.location.href='login.html';");
			out.println("</script>");
		}
	}
}

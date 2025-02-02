package servlets;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;

import database.DataBase;

@WebServlet("/signupServlet")


public class SignUp extends HttpServlet{
	protected void doPost(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException{
		String name=req.getParameter("name");
		String email=req.getParameter("email");
		String password=req.getParameter("password");
		String confirmPassword=req.getParameter("confirmPassword");
		PrintWriter out=resp.getWriter();
		
		if(confirmPassword.equals(password)) {
		DataBase db=new DataBase();
		int rs=db.addUser(name, email, password);
		
			if(rs>0) {
				resp.setContentType("text/html");
				out.println("<script type='text/javascript'>");
				out.println("alert('Sign Up Successfully !!!!') ;");
				out.println(" window.location.href='login.html';");
				out.println("</script>");
		
				
			}
			else {
				resp.setContentType("text/html");
				out.println("<script type='text/javascript'>");
				out.println("alert('Sign Up Failed !!!') ;");
				out.println(" window.location.href='signup.html';");
				out.println("</script>");
			}
		}
		else {
			resp.setContentType("text/html");
			out.println("<script type='text/javascript'>");
			out.println("alert('Sign Up Failed !!!') ;");
			out.println(" window.location.href='signup.html';");
			out.println("</script>");
		}
	}
}

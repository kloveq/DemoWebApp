package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import connection.CheckGirl;
import connection.MyConnection;

@WebServlet("/delete-girl")
public class DeleteGirl extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String code = req.getParameter("code");
		req.setAttribute("code", code);
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/view/deleteGirl.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String code = req.getParameter("code");
		boolean result = false;
		
		CheckGirl checkGirl = new CheckGirl();
		MyConnection myConnection = new MyConnection();
		try {
			Connection connection = myConnection.getConnection();
			result = checkGirl.deleteGirl(connection, code);
			
		} catch (ClassNotFoundException e) {
			System.out.println(e.toString());
			result = false;
		} catch (SQLException e) {
			System.out.println(e.toString());
			result = false;
		}
		if(result) {
			resp.sendRedirect("view-girl");
		} else {
			RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/view/deleteGirl.jsp");
			dispatcher.forward(req, resp);
		}
	}
}

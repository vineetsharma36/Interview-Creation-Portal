package com.scaler.ICP;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FetchI
 */
// @WebServlet(name = "fetchi", urlPatterns = { "/fetchi" })
public class FetchI extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FetchI() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
		final String USER = "root";
		final String PASS = "";
		final String DB_URL = "jdbc:mysql://localhost:3306/icp";
		Connection con = null;
		Statement stm = null;
		try {
			response.setContentType("text/html");
			Class.forName(JDBC_DRIVER);
			con = DriverManager.getConnection(DB_URL, USER, PASS);
			stm = con.createStatement();

			String query = "select * from interviewer";
			ResultSet rs = stm.executeQuery(query);
			PrintWriter out = response.getWriter();
			out.println("<html><style>th{background-color: yellow; padding: 10px 15px;} tr:nth-child(odd) {\n"
					+ "  background: grey;\n"
					+ "}</style><body ><h1 style=\"text-align: center; padding:15px; margin-bottom: 25px;\">Interviewers</h1><table border='1' class=\"styled-table\" style=\"margin: auto; margin-top: 30px;\">"
					+ "<tr><th>S. No.</th><th>ID</th><th>Name</th><th>Department</th><th>Email</th><th>Contact</th></tr>");
			while (rs.next()) {
				out.print("<tr><td>"+rs.getInt(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td><td>"+rs.getString(6)+"</td><td>"+rs.getString(4)+"</td><td>"+rs.getString(5)+"</td></tr>"); 
			}
			out.println("</table></body></html>");
			rs.close();
			stm.close();
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

package com.scaler.ICP;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AllInterviews
 */
// @WebServlet(name = "allinterviews", urlPatterns = { "/allinterviews" })
public class AllInterviews extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AllInterviews() {
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

			String query = "SELECT t.id, t.emp_id, i.name, t.cid, c.name, t.date, t.start_time, t.end_time FROM time_slot t JOIN interviewer i ON t.emp_id = i.emp_id JOIN candidate c ON t.cid = c.cid;";
			
			ResultSet rs = stm.executeQuery(query);
			PrintWriter out = response.getWriter();
			
			out.println("<html><style>th{background-color: yellow; padding: 10px 15px;} tr:nth-child(odd) {\n"
					+ "  background: grey;\n"
					+ "}</style><body ><h1 style=\"text-align: center; padding:15px; margin-bottom: 25px;\">All Interviews</h1><table border='1' class=\"styled-table\" style=\"margin: auto; margin-top: 30px;\">"
					+ "<tr><th>S. No.</th><th>Interviewer ID</th><th>Interviewer Name</th><th>Candidate ID</th><th>Candidate Name</th><th>Scheduled Date</th><th>Start Time</th><th>End Time</th></tr>");
			int sno=1;
			while (rs.next()) {

				out.print("<tr><td>"+sno+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td><td>"+rs.getString(4)+"</td><td>"+rs.getString(5)+"</td><td>"+rs.getString(6)+"</td><td>"+rs.getString(7)+"</td><td>"+rs.getString(8)+"</td></tr>");
				sno++;
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

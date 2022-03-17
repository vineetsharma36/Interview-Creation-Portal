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
 * Servlet implementation class UpcomingInterviews
 */
// @WebServlet(name = "upcominginterviews", urlPatterns = { "/upcominginterviews" })
public class UpcomingInterviews extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpcomingInterviews() {
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

			String query = "SELECT t.id, t.emp_id, i.name, t.cid, c.name, t.date, t.start_time, t.end_time, t.meeting_link FROM time_slot t JOIN interviewer i ON t.emp_id = i.emp_id JOIN candidate c ON t.cid = c.cid\n"
					+ "					where ((t.date=CURRENT_DATE and t.start_time>=CURRENT_TIME) or t.date>CURRENT_DATE)\n"
					+ "                    order by t.date , t.start_time, t.end_time;";
			
			
			
			ResultSet rs = stm.executeQuery(query);
			PrintWriter out = response.getWriter();
			
			out.println("<html><style>th{background-color: yellow; padding: 10px 15px;} tr:nth-child(odd) {\n"
					+ " background: grey;\n"
					+ "}</style><body ><h1 style=\"text-align: center; padding:15px; margin-bottom: 25px;\">Upcoming Interviews</h1><table border='1' class=\"styled-table\" style=\"margin: auto; margin-top: 30px;\">"
					+ "<tr><th>S. No.</th><th>Interviewer ID</th><th>Interviewer Name</th><th>Candidate ID</th><th>Candidate Name</th><th>Scheduled Date</th><th>Start Time</th><th>End Time</th><th>Meeting Link</th><th>Actions</th></tr>");
			int sno=1;
			while (rs.next()) {
				String lnk=rs.getString(9);
				if(lnk==null) {
					lnk="";
				}

				out.print("<tr><td>"+sno+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td><td>"+rs.getString(4)+"</td><td>"+rs.getString(5)+"</td><td>"+rs.getString(6)+"</td><td>"+rs.getString(7)+"</td><td>"+rs.getString(8)+"</td><td><a href=\""+lnk+"\">Join Meeting</a></td><td>"
						+ "<a href=\"edit.jsp?emp_id="+rs.getString(2)+"&cid="+rs.getString(4)+"&date="+rs.getString(6)+"&stime="+rs.getString(7)+"&etime="+rs.getString(8)+"&mlink="+lnk+"\" target=\"_blank\">Edit</a>&nbsp;&nbsp;"
						+ "<a href=\"delete.jsp?emp_id="+rs.getString(2)+"&cid="+rs.getString(4)+"&date="+rs.getString(6)+"&stime="+rs.getString(7)+"&etime="+rs.getString(8)+"\">Delete</a></td></tr>");
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

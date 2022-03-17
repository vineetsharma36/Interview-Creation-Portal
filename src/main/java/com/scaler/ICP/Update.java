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
 * Servlet implementation class Update
 */
// @WebServlet(name = "update", urlPatterns = { "/update" })
public class Update extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Update() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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

			String emp_id = request.getParameter("emp_id");
			String cid = request.getParameter("cid");
			String date = request.getParameter("date");
			String stime = request.getParameter("stime");
			String etime = request.getParameter("etime");
			String mlink = request.getParameter("mlink");
			PrintWriter out = response.getWriter();
			boolean flag=true;
			if(emp_id=="" && cid=="" && date=="" && stime=="" && etime=="") {
				flag=false;
				out.println("<h2 style=\"color: red;\">Enter all values!!</h2>");
			}
			else if(emp_id=="" || cid=="") {
				flag=false;
				out.println("<h2 style=\"color: red;\">Interviewer or Applicant ID is missing!!</h2>");
			}
			else if(date=="" || stime=="" || etime=="") {
				flag=false;
				out.println("<h2 style=\"color: red;\">Date or time is missing!!</h2>");
			}
			else if(emp_id.equals(cid)) {
				flag=false;
				out.println("<h2 style=\"color: red;\">Interviewer and Applicant cannot be same</h2>");
			}
			else if(etime.compareTo(stime)<=0) {
				flag=false;
				out.println("<h2 style=\"color: red;\">Meeting End Time cannot be same or less than Meeting Start Time</h2>");
			}
			else {
				// busy
				String query = "(select count(*) from time_slot where (emp_id=\""+emp_id+"\" or cid=\""+cid+"\") and date=\""+date+"\" and ((start_time>=\""+stime+"\" and start_time<=\""+etime+"\") or (end_time>=\""+stime+"\" and end_time<=\""+etime+"\")));";
				ResultSet rs = stm.executeQuery(query);
				rs.next();
				int count= rs.getInt(1);
				if(count>0) {
					flag=false;
					out.println("<h2 style=\"color: red;\">Choose another time that does not clash</h2>");
				}
				
			}
			if(flag) {
				String query = "Update time_slot set date=\""+date+"\" , start_time=\""+stime+"\" , end_time=\""+etime+"\" , meeting_link=\""+mlink+"\" where emp_id=\""+emp_id+"\" and cid=\""+cid+"\"";
				stm.executeUpdate(query);
				response.sendRedirect("http://localhost:8080/Interview_Creation_Portal/upcominginterviews");
				stm.close();
			}
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

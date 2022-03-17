package com.scaler.ICP;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.*;
import javax.servlet.http.*;

import java.io.*;
import java.sql.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletInputStream.*;
import java.io.PrintWriter;

/**
 * Servlet implementation class UploadResume
 */
// @WebServlet(name = "uploadresume", urlPatterns = { "/uploadresume" })
@MultipartConfig(maxFileSize = 16177216)//1.5mb
public class UploadResume extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UploadResume() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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

			String cid = request.getParameter("cid");
//			String rlink = request.getParameter("rlink");
			Part rfile = request.getPart("rfile");
			// String rfile = request.getParameter("rfile");
			
			

			PrintWriter out = response.getWriter();

			if (cid.equals("")) {

				out.println("<h2 style=\"color: red;\">Enter Candidate ID!!</h2>");
			} else if (rfile==null) {

				out.println("<h2 style=\"color: red;\">Upload resume!!</h2>");
			} else {
				String query;
				PreparedStatement ps;
				query = ("Update candidate set resume=? where cid=?");
				ps = con.prepareStatement(query);
				InputStream is = rfile.getInputStream();
				ps.setBlob(1, is);
				ps.setString(2, cid);
				System.out.println(is+""+cid);
				int result=ps.executeUpdate();
				if (result > 0) {
					response.sendRedirect("http://localhost:8080/Interview_Creation_Portal/fetchc");
                } else {
                    response.sendRedirect("index.jsp?message=Some+Error+Occurred");
                }
				stm.close();
			}
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

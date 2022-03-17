<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>

<%
String cid = request.getParameter("cid");

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

	PreparedStatement ps = con.prepareStatement("select resume from candidate where cid=?");
    ps.setString(1, cid);
    ResultSet rs = ps.executeQuery();
    if (rs.next()) {
        Blob blob = rs.getBlob("resume");
        byte byteArray[] = blob.getBytes(1, (int) blob.length());
        response.setContentType("image/gif");
        OutputStream os = response.getOutputStream();
        os.write(byteArray);
        os.flush();
        os.close();
    } else {
        System.out.println("No image found with this id.");
    }
	rs.close();
	stm.close();
	con.close();

} catch (Exception e) {
	e.printStackTrace();
}
%>
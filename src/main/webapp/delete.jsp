<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Delete Interviews</title>
</head>
<body>

	<sql:setDataSource var="snapshot" driver="com.mysql.cj.jdbc.Driver"
		url="jdbc:mysql://localhost/icp" user="root" password="" />

	<sql:update dataSource="${snapshot}" var="count">
         delete from time_slot where 
         emp_id='<%=request.getParameter("emp_id")%>'
         and cid='<%=request.getParameter("cid")%>'
         and date='<%=request.getParameter("date")%>'
         and start_time='<%=request.getParameter("stime")%>'
         and end_time='<%=request.getParameter("etime")%>';
      </sql:update>

	<%
    String redirectURL = "http://localhost:8080/Interview_Creation_Portal/upcominginterviews";
    response.sendRedirect(redirectURL);
    %>


</body>
</html>
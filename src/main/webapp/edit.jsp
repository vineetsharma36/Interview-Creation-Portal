<%@ page import = "java.io.*,java.util.*,java.sql.*"%>
<%@ page import = "javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix = "sql"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Interviews</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<style>
.head {
	text-align: center;
	padding: 15px 10px;
	margin-bottom: 50px;
	padding-top:40px;
}

.focl {
	margin: auto;
	margin-bottom: 60px;
}

</style>
</head>
<body>

 <h1 class="head">Edit Interview</h1>




	<div class="col-lg-5 focl">
		<form action="update" method="post">
			<div class="mb-3">
				<label for="emp_id" class="form-label">Interviewer's ID:</label> <input
					type="text" class="form-control" name="emp_id" value='<%= request.getParameter("emp_id") %>' readonly>

				<div class="mb-3">
					<label for="cid" class="form-label">Candidate's ID:</label> <input
						type="text" class="form-control" name="cid" value='<%= request.getParameter("cid") %>' readonly>
				</div>

				<div class="mb-3">
					<label for="date" class="form-label">Date:</label> <input
						type="date" class="form-control" name="date" value='<%= request.getParameter("date") %>' required>
				</div>

				<div class="mb-3">
					<label class="form-label">Start Time:</label> <input type="time"
						class="form-control" name="stime" value='<%= request.getParameter("stime") %>' required>
				</div>

				<div class="mb-3">
					<label class="form-label">End Time:</label> <input type="time"
						class="form-control" name="etime" value='<%= request.getParameter("etime") %>' required>
				</div>

				<div class="mb-3">
					<label class="form-label">Meeting Link:</label> <input type="text"
						class="form-control" name="mlink" value='<%= request.getParameter("mlink") %>'>
				</div>

				<div class="d-grid gap-2">
					<button type="submit" class="btn btn-primary">Submit</button>
				</div>
		</form>
	</div>

	
 

</body>
</html>
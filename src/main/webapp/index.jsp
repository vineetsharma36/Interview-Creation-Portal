<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Interview Portal</title>
<script
	src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"
	integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"
	integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF"
	crossorigin="anonymous"></script>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<style>
.head {
	text-align: center;
	padding: 15px 0px;
	margin-bottom: 50px;
	padding-top: 40px;
}

.focl {
	margin: auto;
	margin-bottom: 60px;
}

.sihead {
	padding: 20px;
	text-align: center;
	margin-bottom: 15px;
	margin-top: 30px;
}

.btn-green {
	background-color: #00FF00;
}

.btncl {
	padding: 10px;
	margin-top: 12px;
	margin-bottom: 20px;
	margin-left: 40px;
	margin-right: 50px;
	font-size: 18px;
}

.ml {
	padding: 10px;
	margin-top: 12px;
	margin-bottom: 20px;
	margin-left: 70px;
	margin-right: 50px;
	font-size: 18px;
}

.mr {
	padding: 10px;
	margin-top: 12px;
	margin-bottom: 20px;
	margin-left: 40px;
	margin-right: 50px;
	font-size: 18px;
}
</style>

</head>
<body>
	<h1 class="head">Interview Creation Portal</h1>


	<div class="btn-group">
		<form action="allinterviews" method="post">
			<button type="submit" class="btn btn-success ml">Display All
				Scheduled Interviews</button>
		</form>
		<form action="upcominginterviews" method="post">
			<button type="submit" class="btn btn-warning btncl">Upcoming
				Interviews</button>
		</form>
		<form action="fetchi" method="post">
			<button type="submit" class="btn btn-success btncl">Display
				Interviewers</button>
		</form>
		<form action="fetchc" method="post">
			<button type="submit" class="btn btn-warning btncl">Display
				Candidates</button>
		</form>
		<form>
			<button type="button" data-bs-toggle="modal"
				data-bs-target="#myModal" class="btn btn-success mr">Upload
				Resume</button>
		</form>
	</div>


	<!-- Modal -->
	<div class="modal fade" id="myModal" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Upload Resume</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">


					<form action="uploadresume" method="post" enctype="multipart/form-data">
						<div class="mb-3">

							<div class="mb-3">
								<label for="cid" class="form-label">Candidate's ID:</label> <input
									type="text" class="form-control" name="cid" required>
							</div>

							<!-- <div class="mb-3">
								<label for="resume_link" class="form-label">Resume link:</label>
								<input type="text" class="form-control" name="rlink">
							</div>

							<p class="form-contol">or</p> -->
							
							<div class="mb-3">
								<label for="resume_file" class="form-label">Resume:</label> <input type="file"
									class="form-control" name="rfile" required>
							</div>

							<div class="d-grid gap-2">
								<button type="submit" class="btn btn-primary">Submit</button>
							</div>
						</div>
					</form>


				</div>

			</div>
		</div>
	</div>


	<div class="col-lg-5 focl">
		<h3 class="sihead">Schedule Interview</h3>
		<form action="schedule" method="post">
			<div class="mb-3">
				<label for="emp_id" class="form-label">Interviewer's ID:</label> <input
					type="text" class="form-control" name="emp_id" required>

				<div class="mb-3">
					<label for="cid" class="form-label">Candidate's ID:</label> <input
						type="text" class="form-control" name="cid" required>
				</div>

				<div class="mb-3">
					<label for="date" class="form-label">Date:</label> <input
						type="date" class="form-control" name="date" required>
				</div>

				<div class="mb-3">
					<label class="form-label">Start Time:</label> <input type="time"
						class="form-control" name="stime" required>
				</div>

				<div class="mb-3">
					<label class="form-label">End Time:</label> <input type="time"
						class="form-control" name="etime" required>
				</div>

				<div class="mb-3">
					<label class="form-label">Meeting Link:</label> <input type="text"
						class="form-control" name="mlink">
				</div>

				<div class="d-grid gap-2">
					<button type="submit" class="btn btn-primary">Submit</button>
				</div>
		</form>
	</div>



</body>
</html>
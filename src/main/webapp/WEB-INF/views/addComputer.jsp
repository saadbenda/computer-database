<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="css/font-awesome.css" rel="stylesheet" media="screen">
<link href="css/main.css" rel="stylesheet" media="screen">
<link href="css/frontValidation.css" rel="stylesheet" media="screen">

</head>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<a class="navbar-brand" href="${dashboard}"> Application -
				Computer Database </a>
		</div>
	</header>

	<section id="main">
		<div class="container">
			<div class="row">
				<div class="col-xs-8 col-xs-offset-2 box">
					<h1>Add Computer</h1>
					<form action="AddComputerServlet" method="POST" id="addComputer"
						name="addComputer">
						<fieldset>
							<div class="form-group">
								<label for="computerName">Computer name</label> <span
									class="error">This field is required</span> <input type="text"
									class="form-control" id="computerName"
									placeholder="Computer name" name="computerName">
							</div>
							<div class="form-group">
								<label for="introduced">Introduced date</label> <span
									class="error">This field is required</span> <input type="date"
									class="form-control" id="introduced"
									placeholder="Introduced date" name="introduced">
							</div>
							<div class="form-group">
								<label for="discontinued">Discontinued date</label> <span
									class="error">The discontinued date cannot be lower than
									the introduced date</span> <input type="date" class="form-control"
									id="discontinued" placeholder="Discontinued date"
									name="discontinued">

							</div>
							<div class="form-group">
								<label for="companyId">Company</label> <select
									class="form-control" id="companyId" name="companyId">
									<option value="null">Unknown</option>
									<c:forEach items="${companies}" var="company">
										<option value="${company.id}">${company.name}</option>
										
									</c:forEach>

								</select>
							</div>
						</fieldset>
						<div class="actions pull-right">
							<input id="submit" type="submit" value="Add"
								class="btn btn-primary"> or <a href="${dashboard}"
								class="btn btn-default">Cancel</a>
						</div>
					</form>
				</div>
			</div>
		</div>
	</section>
	<script src="js/jquery.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.18.0/moment.min.js"></script>
	<!--<script src="js/frontValidation.js"></script>-->
</body>
</html>
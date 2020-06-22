<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="utf-8">
<!-- Bootstrap -->
<link href="css/bootstrap-theme.min.css" rel="stylesheet" media="screen">
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">

<link href="css/bootstrap.css.map" rel="stylesheet" media="screen">
<link href="css/bootstrap-theme.css.map" rel="stylesheet" media="screen">

<link href="css/bootstrap.css" rel="stylesheet" media="screen">
<link href="css/bootstrap-theme.css" rel="stylesheet" media="screen">


<link href="css/font-awesome.css" rel="stylesheet" media="screen">
<link href="css/font-awesome.min.css" rel="stylesheet" media="screen">


<link href="css/main.css" rel="stylesheet" media="screen">
<link href="css/frontValidation.css" rel="stylesheet" media="screen">

<p>computerAZ ${sessionScope.computerAZ}</p>
<p>computerZA ${sessionScope.computerZA}</p>
<p>companyAZ ${sessionScope.companyAZ}</p>
<p>companyZA ${sessionScope.companyZA}</p>
<p>introL ${sessionScope.introLatest}</p>
<p>introO ${sessionScope.introOldest}</p>
<p>discoL ${sessionScope.discoLatest}</p>
<p>discoO ${sessionScope.discoOldest}</p>

<p>test bool ${sessionScope.companyAZ==false and sessionScope.companyZA}</p>
<p>test bool ${(not sessionScope.companyAZ and not sessionScope.companyZA)?"hey":"hey no"}</p>




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
			<h1 id="homeTitle">${count}ComputersFounded</h1>
			<div id="actions" class="form-horizontal">
				<div class="pull-left">
					<form id="searchForm" action="${dashboard}" method="GET"
						class="form-inline">

						<input type="search" id="searchbox" name="search"
							class="form-control" placeholder="Search name" /> <input
							type="submit" id="searchsubmit" value="Filter by name"
							class="btn btn-primary" />
					</form>
				</div>
				<div class="pull-right">
					<a class="btn btn-success" id="addComputer" href="${addComputer}">Add
						Computer</a> <a class="btn btn-default" id="editComputer"
						onclick="$.fn.toggleEditMode();">Edit</a>
				</div>
			</div>
		</div>

		<form id="deleteForm" action="${dashboard}" method="POST">
			<input type="hidden" name="selection" value="">
		</form>

		<div class="container" style="margin-top: 10px;">
			<table class="table table-striped table-bordered">
				<thead>
					<tr>
						<!-- Variable declarations for passing labels as parameters -->
						<!-- Table header for Computer Name -->

						<th class="editMode" style="width: 60px; height: 22px;"><input
							type="checkbox" id="selectall" /> <span
							style="vertical-align: top;"> - <a href="#"
								id="deleteSelected" onclick="$.fn.deleteSelected();"> <i
									class="fa fa-trash-o fa-lg"></i>
							</a>
						</span></th>

						<th>Computer name
							<div class="btn-group btn-group-sm pull-right" role="group">
								
									<form action="${dashboard}" method="POST">
										<input type="radio" id="computerAZ" name="computer" 
										value="A-Z" class="btn btn-default" 
										onclick="$(this).closest('form').submit();" 
										${(computerAZ)?"checked":""}>
											A-Z								
										<input type="radio" id="computerZA" name="computer" 
										value="Z-A" class="btn btn-default" 
										onclick="$(this).closest('form').submit();" 
										${(computerZA)?"checked":""}>
										Z-A								
										<input type="radio" id="computerNone" name="computer" 
										value="none" onclick="$(this).closest('form').submit();" 
										class="btn btn-default" 
										${(not sessionScope.computerAZ and not sessionScope.computerZA)?"checked":""}>
										None
									</form>
							
							</div>
						</th>

						<th>Introduced date
							<div class="btn-group btn-group-sm pull-right" role="group">
								
								<form action="${dashboard}" method="POST">
										<input type="radio"  name="introduction" 
										value="latest" class="btn btn-default" 
										onclick="$(this).closest('form').submit();" 
										${(sessionScope.introLatest)?"checked":""}>
											Latest								
										<input type="radio" name="introduction" 
										value="oldest" class="btn btn-default" 
										onclick="$(this).closest('form').submit();" 
										${(sessionScope.introOldest)?"checked":""}>
										Oldest							
										<input type="radio"  name="introduction" 
										value="none" onclick="$(this).closest('form').submit();" class="btn btn-default" 
										${(not sessionScope.introLatest and not sessionScope.introOldest)?"checked":""}>
										None
								</form>
								
							</div>
						</th>
						<!-- Table header for Discontinued Date -->
						<th>Discontinued date
							<div class="btn-group btn-group-sm pull-right" role="group">
								
								<form action="${dashboard}" method="POST">
										<input type="radio"  name="discontinued" 
										value="latest" class="btn btn-default" 
										onclick="$(this).closest('form').submit();" 
										${(sessionScope.discoLatest)?"checked":""}>
											Latest								
										<input type="radio" name="discontinued" 
										value="oldest" class="btn btn-default" 
										onclick="$(this).closest('form').submit();" 
										${(sessionScope.discoOldest)?"checked":""}>
										Oldest							
										<input type="radio"  name="discontinued" 
										value="none" onclick="$(this).closest('form').submit();" 
										class="btn btn-default" 
										${(not sessionScope.discoLatest and not sessionScope.discoOldest)?"checked":""}>
										None
								</form>
						
							</div>
						</th>
						<!-- Table header for Company -->
						<th>Company
							<div class="btn-group btn-group-sm pull-right" role="group">
								
								<form action="${dashboard}" method="POST">
										<input type="radio"  name="company" 
										value="A-Z" class="btn btn-default" 
										onclick="$(this).closest('form').submit();" ${(companyAZ)?"checked":""}>
											A-Z								
										<input type="radio"  name="company" 
										value="Z-A" class="btn btn-default" 
										onclick="$(this).closest('form').submit();" ${(companyZA)?"checked":""}>
										Z-A								
										<input type="radio"  name="company" 
										value="none" onclick="$(this).closest('form').submit();" 
										class="btn btn-default" 
										${(not sessionScope.companyAZ and not sessionScope.companyZA)?"checked":""}>
										None
									</form>
								
							</div>
						</th>

					</tr>
				</thead>
				<!-- Browse attribute computers -->
				<tbody id="results">
					<c:forEach items="${computers}" var="computer">
						<tr>
							<td class="editMode"><input type="checkbox" name="cb"
								class="cb" value="${computer.id}"></td>
							<td><a href="${editComputer}" onclick="">${computer.name}</a></td>
							<td>${computer.introduced}</td>
							<td>${computer.discontinued}</td>
							<td>${computer.company.name}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</section>

	<footer class="navbar-fixed-bottom">


		<div class="container text-center">
			<ul class="pagination">
				<li><a href="${dashboard}?page=${pages.first}"
					aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
				</a></li>
				<c:forEach items="${pages}" var="page">
					<li><a href="${dashboard}?page=${page}">${page}</a></li>
				</c:forEach>
				<li><a href="${dashboard}?page=more">...</a></li>
				<li><a href="${dashboard}?page=${pages.last}" aria-label="Next">
						<span aria-hidden="true">&raquo;</span>
				</a></li>
			</ul>
			<div class="btn-group btn-group-sm pull-right" role="group">
				<button type="button" class="btn btn-default">
					<a href="${dashboard}?display=10">10</a>
				</button>
				<button type="button" class="btn btn-default">
					<a href="${dashboard}?display=50">50</a>
				</button>
				<button type="button" class="btn btn-default">
					<a href="${dashboard}?display=100">100</a>
				</button>
			</div>
	</footer>
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/bootstrap.js"></script>
	<script src="js/frontValidation.js"></script>
	<script src="js/dashboard.js"></script>

</body>
</html>
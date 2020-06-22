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
            <a class="navbar-brand" href="${dashboard}"> Application - Computer Database </a>
        </div>
    </header>
    <section id="main">
        <div class="container">
            <div class="row">
                <div class="col-xs-8 col-xs-offset-2 box">
                    <div class="label label-default pull-right">
                        id: ${computer.id}
                    </div>
                    <h1>Edit Computer</h1>

                    <form action="${editComputer}" method="POST">
                        <input type="hidden" value="${computer.id}" id="id" name="computerId"/> 
                        <fieldset>
                            <div class="form-group">
                                <label for="computerName">Computer name</label>
                                <span class="error">This field is required</span>
                                <input type="text" class="form-control" id="computerName" value="${computer.name}" placeholder="Computer name" name="computerName">
                            </div>
                            <div class="form-group">
                                <label for="introduced">Introduced date</label>
                                <span class="error">cannot be before 01/01/1970 or after 19/01/2038 or lower than the discontinued date</span>
                                <input type="date" class="form-control" id="introduced" value="${computer.introduced}" placeholder="Introduced date" name="introduced">
                            </div>
                            <div class="form-group">
                                <label for="discontinued">Discontinued date</label>
                                <span class="error">cannot be before 01/01/1970 or after 19/01/2038 or greater than the introduced date</span>
                                <input type="date" class="form-control" id="discontinued" value="${computer.discontinued}" placeholder="Discontinued date" name="discontinued">
                            </div>
                            <div class="form-group">
                                <label for="companyId">Company</label>
                                <select class="form-control" id="companyId" name="companyId">
									<option value="null" ${computer.company.name=="null"?"":"selected"}>Unknown</option>
									<c:forEach items="${companies}" var="company">
									<option value="${company.id}" ${company.name==computer.company.name?"selected":""}>
									${company.name}</option>
									<label type="hidden" value="${company.name}" name="companyId"></label> 
									</c:forEach>
								</select>
                            </div>            
                        </fieldset>
                        <div class="actions pull-right">
                            <input type="submit" value="Edit" class="btn btn-primary">
                            or
                            <a href="${dashboard}" class="btn btn-default">Cancel</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </section>
    <script src="js/jquery.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.18.0/moment.min.js"></script>
	<script src="js/frontValidation.js"></script>
</body>
</html>
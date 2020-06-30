<!DOCTYPE html>
<html>
<head>
<title><spring:message code="title"/><</title>
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
            <a class="navbar-brand" href="${dashboard}"><spring:message code="home"/></a>
        </div>
    </header>
    <section id="main">
        <div class="container">
            <div class="row">
                <div class="col-xs-8 col-xs-offset-2 box">
                    <div class="label label-default pull-right">
                        id: ${computer.id}
                    </div>
                   <h1><spring:message code="title.editComputer"/></h1>
                    
                    
					<form action="${editComputer}" method="GET" id="langForm">
						<input type="radio" name="lang" value="en"
							class="btn btn-default"
							onclick="$(this).closest('form').submit();"
							${(sessionScope.lang == 'en')?"checked":""}> English
							<img src="img/us.png">
							<input type="radio"
							 name="lang" value="fr"
							class="btn btn-default"
							onclick="$(this).closest('form').submit();"
							${(sessionScope.lang == 'fr')?"checked":""}> Français
							<img src="img/fr.png">
					</form>
                    

                    <form action="${editComputer}" method="POST">
                        <input type="hidden" value="${computer.id}" id="id" name="computerId"/> 
                        <fieldset>
                            <div class="form-group">
                                <label for="computerName"><spring:message code="computerName" /></label>
                                <span class="error"><spring:message code="error.fieldRequired"/></span>
                                <input required type="text" class="form-control" id="computerName" value="${computer.name}" placeholder="Computer name" name="computerName">
                            </div>
                            <div class="form-group">
                                <label for="introduced"><spring:message code="introduced"/></label>
                                <span class="error"><spring:message code="error.fieldRequired"/></span>
                                <input type="date" class="form-control" id="introduced" value="${computer.introduced}" placeholder="Introduced date" name="introduced">
                            </div>
                            <div class="form-group">
                                <label for="discontinued"><spring:message code="discontinued"/></label>
                                <span class="error"><spring:message code="error.introDisco"/></span>
                                <input type="date" class="form-control" id="discontinued" value="${computer.discontinued}" placeholder="Discontinued date" name="discontinued">
                            </div>
                            <div class="form-group">
                                <label for="companyId"><spring:message code="company"/></label>
                                <select class="form-control" id="companyId" name="companyId">
									<option value="null" ${computer.company.name=="null"?"":"selected"}><spring:message code="unknown"/></option>
									<c:forEach items="${companies}" var="company">
									<option value="${company.id}" ${company.name==computer.company.name?"selected":""}>
									${company.name}</option>
									<label type="hidden" value="${company.name}" name="companyId"></label> 
									</c:forEach>
								</select>
                            </div>            
                        </fieldset>
                        <div class="actions pull-right">
                            <input type="submit" value="<spring:message code="add"/>" class="btn btn-primary">
                            <spring:message code="or"/> 
                            <a href="${dashboard}" class="btn btn-default"><spring:message code="cancel"/></a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </section>
    <script src="js/jquery.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.18.0/moment.min.js"></script>
	<!--  <script src="js/frontValidation.js"></script>-->
</body>
</html>
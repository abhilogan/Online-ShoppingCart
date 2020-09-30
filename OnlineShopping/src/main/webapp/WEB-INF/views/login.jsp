<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<spring:url var="css" value="/resources/css"></spring:url>
<spring:url var="js" value="/resources/js"></spring:url>
<spring:url var="images" value="/resources/images"></spring:url>
<c:set var="contextRoot" value="${pageContext.request.contextPath}">
</c:set>

<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>Online Shopping - ${title}</title>

<script>
	window.menu = '${title}';

	window.contextRoot = '${contextRoot}';
</script>
<!-- Bootstrap core CSS -->
<link href="${css}/bootstrap.min.css" rel="stylesheet">

<!-- Adding Glyphicon CSS externally as it is not by default present in bootstrap4 css -->
<link href="${css}/glyphicon.css" rel="stylesheet">

<!-- Adding Panel CSS externally as it is not by default present in bootstrap4 css -->
<link href="${css}/panel.css" rel="stylesheet">

<!-- Theme CSS should be mentioned after core CSS only otherwise it will not work because it will overrirde min css-->
<!-- Bootstrap Readable Theme CSS -->
<link href="${css}/bootstrap-readable-theme.css" rel="stylesheet">

<!-- Bootstrap DataTables CSS -->
<link href="${css}/dataTables.bootstrap4.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="${css}/myapp.css" rel="stylesheet">

</head>

<body>

	<div class="wrapper">

		<!-- Navigation -->
		<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
			<div class="container">
				<!-- Brand and toggle get grouped for better mobile display -->
				<div class="navbar-header">
					<a class="navbar-brand" href="${contextRoot}/home">Online
						Shopping</a>
				</div>
			</div>
		</nav>


		<!-- Page Content -->

		<div class="content">

			<div class="container">

				<c:if test="${not empty message}">

					<div class="row">

						<div class="col-md-12 col-md-offset-3">

							<div class="alert alert-danger">
							
							${message}
							
							</div>

						</div>

					</div>

				</c:if>
				
				
				<!-- logout -->
				
				<c:if test="${not empty logout}">

					<div class="row">

						<div class="col-md-12 col-md-offset-3">

							<div class="alert alert-danger">
							
							${logout}
							
							</div>

						</div>

					</div>

				</c:if>

				<div class="row">

					<div class="col-md-6 offset-md-3">

						<div class="panel panel-primary panel-transparent">

							<div class="panel-heading">
								<h4>Login</h4>
							</div>

							<div class="panel-body panel-transparent">
								<form action="${contextRoot}/login" method="POST"
									class="form-horizontal" id="loginForm">

									<div class="form-group row">
										<label for="username" class="col-md-4 control-label">Email:</label>
										<div class="col-md-8">
											<input type="text" name="username" id="username"
												placeholder="enter email here" class="form-control" />
										</div>
									</div>

									<div class="form-group row">
										<label for="password" class="col-md-4 control-label">Password:</label>
										<div class="col-md-8">
											<input type="password" name="password" id="password"
												placeholder="enter password here" class="form-control" />
										</div>
									</div>

									<div class="form-group row">
										<div class="col-md-8 offset-md-4">
										
											<input type="submit" value="Login" class="btn btn-primary" />
											<input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
										</div>
									</div>

								</form>

							</div>
							<div class="panel-footer panel-transparent">
								<div class="text-right">
									New User - <a href="${contextRoot}/register">Register Here</a>
								</div>
							</div>
						</div>

					</div>

				</div>

			</div>


		</div>

		<!-- Footer come here-->
		<%@include file="./shared/footer.jsp"%>

		<!-- jQuery -->
		<script src="${js}/jquery.min.js"></script>

		<!-- jQuery validate -->
		<script src="${js}/jquery.validate.js"></script>

		<!-- Bootstrap core JavaScript -->
		<script src="${js}/bootstrap.bundle.min.js"></script>

		<!-- Self coded JavaScript -->
		<script src="${js}/myapp.js"></script>

	</div>
</body>

</html>

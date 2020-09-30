<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<spring:url var="css" value="/resources/css" />

<c:set var="contextRoot" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<!-- Bootstrap core CSS -->
<link href="${css}/bootstrap.min.css" rel="stylesheet">

<!-- Adding Glyphicon CSS externally as it is not by default present in bootstrap4 css -->
<link href="${css}/glyphicon.css" rel="stylesheet">

<!-- Adding Panel CSS externally as it is not by default present in bootstrap4 css -->
<link href="${css}/panel.css" rel="stylesheet">

<!-- Theme CSS should be mentioned after core CSS only otherwise it will not work because it will overrirde min css-->
<!-- Bootstrap Readable Theme CSS -->
<link href="${css}/bootstrap-readable-solar-theme.css" rel="stylesheet">

<!-- Bootstrap DataTables CSS -->
<link href="${css}/dataTables.bootstrap4.css" rel="stylesheet">
 
 <!-- Custom styles for this template -->
<link href="${css}/myapp.css" rel="stylesheet">

<title>Online Shopping - FlowException</title>

</head>

<body>
	<div class="se-pre-con"></div>
	
	<div class="wrapper">

		<!-- Navigation -->
		<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
			<div class="container">
				<!-- Brand and toggle get grouped for better mobile display -->
				<div class="navbar-header">
					<a class="navbar-brand" href="${contextRoot}/home">Home</a>
		      </div>
			</div>
		</nav>


		<div class="content">

			<div class="container">

				<div class="row">

					<div class="col-12">


						<div class="jumbotron">

							<h1>Please contact your administrator!</h1>
							<hr />

							<blockquote style="word-wrap: break-word">

								${rootCauseException}</blockquote>

						</div>


					</div>

				</div>

			</div>

		</div>


		<%@include file="../../shared/footer.jsp"%>

	</div>


</body>


</html>
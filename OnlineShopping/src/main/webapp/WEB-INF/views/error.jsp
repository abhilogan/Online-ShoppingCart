<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<spring:url var="css" value="/resources/css"></spring:url>

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
					<a class="navbar-brand" href="${contextRoot}/home">Home</a>
				</div>
			</div>
		</nav>

		<!-- Page Content -->

		<div class="content">

			<div class="container">

				<div class="row">
					<div class="col-12">
						<div class="jumbotron">

							<h1>${errorTitle}</h1>
							<hr>

							<blockquote style="word-wrap: break-word">

								${errorDescription}</blockquote>

						</div>
					</div>
				</div>

			</div>

		</div>

		<!-- Footer come here-->
		<%@include file="./shared/footer.jsp"%>

	</div>
</body>

</html>

<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@include file="../shared/flows-header.jsp"%>

<div class="container">
	<div class="row">

		<!-- column to display the personal details -->
		<div class="col-6">

			<div class="panel panel-primary panel-transparent">
				<div class="panel-heading">
					<h4>Personal Details</h4>
				</div>
				<div class="panel-body panel-transparent">
					<!-- code to display the personal details -->
					<div class="text-center">
					
					 <h4>${registerModel.user.firstName} ${registerModel.user.lastName}</h4>


					<h5>Email : ${registerModel.user.email}</h5>
					<h5>Contact Number: ${registerModel.user.contactNumber}</h5>
					<h5>Role : ${registerModel.user.role}</h5> 
					
					</div>
					
				</div>
				<div class="panel-footer panel-transparent">
					<!-- anchor to move to the edit of personal details -->
					<a href="${flowExecutionUrl}&_eventId_personal"
						class="btn btn-primary">Edit </a>

				</div>
			</div>

		</div>

		<!--  column  to  display the address	-->
		<div class="col-6">

			<div class="panel panel-primary panel-transparent">
				<div class="panel-heading">
					<h4>Billing Address</h4>
				</div>
				<div class="panel-body panel-transparent">
					<!-- code to display the communication address -->
					<div class="text-center">
					
					<h4>${registerModel.billing.addressLineOne}</h4>
					<h5>${registerModel.billing.addressLineTwo}</h5>
					<h5>${registerModel.billing.city} - ${registerModel.billing.postalCode}</h5>
					<h5>${registerModel.billing.state} - ${registerModel.billing.country}</h5>
					
					</div>
					
				</div>
				<div class="panel-footer panel-transparent">
					<!-- anchor to move to the edit of address -->
					<a href="${flowExecutionUrl}&_eventId_billing"
						class="btn btn-primary"> Edit</a>

				</div>
			</div>

		</div>

	</div>

	<!-- to provide the confirm button after displaying the details -->
	<div class="row">
		<div class="col-12 col-sm-offset-4">

			<div class="text-center">

				<!-- anchor to move to the success page -->
				<a href="${flowExecutionUrl}&_eventId_submit"
					class="btn btn-primary">Confirm</a>

			</div>

		</div>
	</div>


</div>


<%@include file="../shared/flows-footer.jsp"%>
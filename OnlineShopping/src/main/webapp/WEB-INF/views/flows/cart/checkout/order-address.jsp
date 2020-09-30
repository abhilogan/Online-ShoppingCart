<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<!-- ..is for coming out of current folder in which file is created -->
<%@include file="../../shared/flows-header.jsp"%>
<div class="container">
	<div class="row">
		<div class="col-md-4">
			<h4 class="modalElement">Select Shipping Address</h4>
			<hr/>
			<div class="row">
				<c:forEach items="${addresses}" var="address">					
					<div class="col-12" style="color:aqua;">
						<h3>${address.addressLineOne}</h3>
						<h3>${address.addressLineTwo}</h3>
						<h4>${address.city} - ${address.state}</h4>
						<h4>${address.country} - ${address.postalCode}</h4>
						<hr/>
						<div class="text-center">
						<!-- anchor to move to the select of shipping address -->
							<a href="${flowExecutionUrl}&_eventId_addressSelection&shippingId=${address.id}"
								 class="btn btn-primary">Select</a>
						</div>												
					</div>
				</c:forEach>			
			</div>
		</div>
		<div class="col-md-8">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h4>Sign Up - Address</h4>
				</div>
				<div class="panel-body">
					<!-- FORM ELEMENTS in BootStrap4-->
					<sf:form method="POST"
							id="billingForm"
							modelAttribute="shipping"><!-- This model is created in checkout-flow.xml -->
						<div class="form-group row">
							<label class="col-form-label col-md-4 formElement" for="addressLineOne">Address Line One:</label>
							<div class="col-md-8">
								<sf:input type="text" path="addressLineOne" id="addressLineOne"
									placeHolder="Enter Address Line One" class="form-control" />
								<sf:errors path="addressLineOne" cssClass="help-block" element="em"/>	
							</div>
						</div>
						<div class="form-group row">
							<label class="col-form-label col-md-4 formElement" for="addressLineTwo">Address Line Two:</label>
							<div class="col-md-8">
								<sf:input type="text" path="addressLineTwo" id="addressLineTwo"
									placeHolder="Enter Address Line Two" class="form-control" />
								<sf:errors path="addressLineTwo" cssClass="help-block" element="em"/>	
							</div>
						</div>
						<div class="form-group row">
							<label class="col-form-label col-md-4 formElement" for="city">City:</label>
							<div class="col-md-8">
								<sf:input type="text" path="city" id="city"
									placeHolder="Enter City Name" class="form-control" />
								<sf:errors path="city" cssClass="help-block" element="em"/>	
							</div>
						</div>
						<div class="form-group row">
							<label class="col-form-label col-md-4 formElement" for="state">State:</label>
							<div class="col-md-8">
								<sf:input type="text" path="state" id="state"
									placeHolder="Enter State Name" class="form-control" />
								<sf:errors path="state" cssClass="help-block" element="em"/>	
							</div>
						</div>
						<div class="form-group row">
							<label class="col-form-label col-md-4 formElement" for="country">Country:</label>
							<div class="col-md-8">
								<sf:input type="text" path="country" id="country"
									placeHolder="Enter Country Name" class="form-control" />
								<sf:errors path="country" cssClass="help-block" element="em"/>	
							</div>
						</div>
						<div class="form-group row">
							<label class="col-form-label col-md-4 formElement" for="postalCode">Postal Code:</label>
							<div class="col-md-8">
								<sf:input type="text" path="postalCode" id="postalCode"
									placeHolder="XXXXXX" class="form-control" />
								<sf:errors path="postalCode" cssClass="help-block" element="em"/>	
							</div>
						</div>
						<div class="form-group row">
							<div class="col-md-8 offset-md-4">
								<!-- It will transist the state from address to payment as eventId is saveAddress which is mentioned in checkout-flow.xml -->
								<button type="submit" name="_eventId_saveAddress" class="btn btn-primary">
										<span class="glyphicon glyphicon-plus"></span> Add Address
								</button>
							</div>
						</div>	
					</sf:form>
				</div>
			</div>
		</div>	
	</div>
</div>
<%@include file="../../shared/flows-footer.jsp"%>
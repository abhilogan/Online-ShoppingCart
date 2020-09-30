<%@include file="../../shared/flows-header.jsp"%>

<div class="container">

	<div class="row">

		<div class="col-md-6">
		
		
		
		</div>

		<div class="col-md-6">
			<section>
				<div class="col-lg-2">
				
				
				</div>
				
				
				<div class="col-lg-8">
					<div class="card">
						<div class="card-header">
							<div class="row">
								<div class="col-md-8">
									<h4 class="font-weight-bolder pt-1 pb-0">Payment Details</h4>
								</div>
								<div class="col-md-4">
									<div class="form-group form-check">
										<label class="form-check-label pt-2"> <input
											class="form-check-input mt-2" type="checkbox">
											Remember me
										</label>
									</div>
								</div>
							</div>
						</div>
						<div class="card-body">
							<form action="#">
								<label for="email" class="font-weight-bolder inputicon">Card
									Number</label>
								<div class="input-group">

									<input type="Number" class="form-control"
										placeholder="Valid Card Number"><span
										class="input-group-addon"><i class="fa fa-lock"></i></span>

								</div>
								<div class="row">
									<div class="col-md-8">

										<div class="container p-0">
											<label for="pwd" class="font-weight-bolder pt-4">EXPIRY
												DATE:</label>
											<div class="row">
												<div class="form-group col-md-3 col-xs-6 col-sm-6">

													<input type="number" class="form-control" placeholder="MM">

												</div>

												<div class="form-group col-md-3 col-xs-6 col-sm-6">

													<input type="number" class="form-control" placeholder="YY">
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-4">
										<div class="form-group">
											<label for="pwd" class="font-weight-bolder pt-4">CV
												CODE:</label> <input type="number" class="form-control"
												placeholder="CVV">
										</div>
									</div>
								</div>
							</form>

						</div>
						<div class="card-footer">
							<button type="button" class="btn btn-primary">
								Final Payment <span class="badge badge-danger ml-1">$4200</span>
							</button>
							
							<a href="${flowExecutionUrl}&_eventId_pay"
								class="btn btn-success float-right text-right">Pay</a>
						</div>
					</div>
				</div>
			</section>

		</div>

	</div>


</div>

<%@include file="../../shared/flows-footer.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

<%@include file="../../shared/flows-header.jsp"%>
<div class="container">
	<div class="alert alert-success">
		<h3 class="text-center">Your Order is Confirmed!!</h3>
	</div>
	<div class="row">
		<div class="col-md-12">
			<div class="row">
				<div class="col-6">
					<h2>Invoice</h2>
				</div>
				<div class="col-6">
					<h3 class="text-right">Order # ${orderDetail.id}</h3>
				</div>	
			</div>
			<hr>
			<div class="row">
				<div class="col-6">
					<address>
						<strong>Billed To:</strong><br> ${orderDetail.user.firstName}
						${orderDetail.user.lastName}<br>
						${orderDetail.billing.addressLineOne}<br>
						${orderDetail.billing.addressLineTwo}<br>
						${orderDetail.billing.city} - ${orderDetail.billing.postalCode}<br>
						${orderDetail.billing.state} - ${orderDetail.billing.country}
					</address>
				</div>
				<div class="col-6 text-right">
					<address>
						<strong>Shipped To:</strong><br>
						${orderDetail.user.firstName} ${orderDetail.user.lastName}<br>
						${orderDetail.shipping.addressLineOne}<br>
						${orderDetail.shipping.addressLineTwo}<br>
						${orderDetail.shipping.city} - ${orderDetail.shipping.postalCode}<br>
						${orderDetail.shipping.state} - ${orderDetail.shipping.country}
					</address>
				</div>
			</div>
			<div class="row">
				<div class="col-6">
					<address>
						<strong>Payment Method:</strong><br> Card Payment <br>
						${orderDetail.user.email}
					</address>
				</div>
				<div class="col-6 text-right">
					<address>
						<strong>Order Date:</strong><br> ${orderDetail.orderDate}<br>
						<br>
					</address>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-md-12">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h3 class="panel-title">
						<strong>Order summary</strong>
					</h3>
				</div>
				<div class="panel-body">
					<div class="table-responsive">
						<table class="table table-striped table-bordered table-condensed">
							<thead>
								<tr>
									<td><strong>Item</strong></td>
									<td class="text-center"><strong>Price</strong></td>
									<td class="text-center"><strong>Quantity</strong></td>
									<td class="text-right"><strong>Totals</strong></td>
								</tr>
							</thead>
							<tbody>
								<!-- foreach ($order->lineItems as $line) or some such thing here -->
								<c:forEach items="${orderDetail.orderItems}" var="orderItem">
									<tr>
										<td>${orderItem.product.name}</td>
										<td class="text-center">&#8377; ${orderItem.buyingPrice}</td>
										<td class="text-center">${orderItem.productCount}</td>
										<td class="text-right">&#8377; ${orderItem.total}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="text-center">
		<a href="${contextRoot}/show/all/products"
			class="btn btn-lg btn-warning">Continue Shopping</a>
	</div>
</div>
<%@include file="../../shared/flows-footer.jsp"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container">

	<div class="row">

		<c:if test="${not empty message}">
			<div class="col-12">

				<div class="alert alert-success alert-dismissible">

					<button type="button" class="close" data-dismiss="alert">&times;</button>

					${message}

				</div>

			</div>
		</c:if>


		<div class="col-md-8 offset-md-2">

			<div class="panel panel-primary panel-transparent">

				<div class="panel-heading">

					<h4>Products Management</h4>

				</div>

				<div class="panel-body">

					<!-- FORM ELEMENTS -->

					<sf:form class="form-horizontal" modelAttribute="product"
						action="${contextRoot}/manage/products" method="POST"
						enctype="multipart/form-data">

						<div class="form-group row">

							<label class="control-label col-md-4 font-weight-bold" for="name">Enter
								Product Name</label>
							<div class="col-md-8">
								<sf:input type="text" path="name" id="name"
									placeholder="Product Name" class="form-control" />
								<sf:errors path="name" cssClass="help-block" element="em" />
							</div>

						</div>

						<div class="form-group row">

							<label class="control-label col-md-4 font-weight-bold" for="brand">Enter
								Brand Name</label>
							<div class="col-md-8">
								<sf:input type="text" path="brand" id="brand"
									placeholder="Brand Name" class="form-control" />
								<sf:errors path="brand" cssClass="help-block" element="em" />
							</div>

						</div>

						<div class="form-group row">

							<label class="control-label col-md-4 font-weight-bold"
								for="description"> Product Description</label>
							<div class="col-md-8">
								<sf:textarea path="description" id="description" rows="4"
									placeholder="Enter your description here!" class="form-control" />
								<sf:errors path="description" cssClass="help-block" element="em" />

							</div>

						</div>

						<div class="form-group row">

							<label class="control-label col-md-4 font-weight-bold" for="unitPrice">Enter
								Unit Price</label>
							<div class="col-md-8">
								<sf:input type="number" path="unitPrice" id="unitPrice"
									placeholder="Unit Price In &#8377" class="form-control" />
								<sf:errors path="unitPrice" cssClass="help-block" element="em" />

							</div>

						</div>

						<div class="form-group row">

							<label class="control-label col-md-4 font-weight-bold" for="file">Enter
								Quantity</label>
							<div class="col-md-8">
								<sf:input type="number" path="quantity" id="quantity"
									class="form-control" />
							</div>

						</div>

						<div class="form-group row">

							<label class="control-label col-md-4 font-weight-bold" for="file">Select
								an Image</label>
							<div class="col-md-8">
								<sf:input type="file" path="file" id="quantity"
									placeholder="Quantity Available" class="form-control" />
								<sf:errors path="file" cssClass="help-block" element="em" />
							</div>

						</div>

						<div class="form-group row">

							<label class="control-label col-md-4 font-weight-bold"
								for="categoryId"> Select Category</label>
							<div class="col-md-8">
								<sf:select class="form-control" id="categoryId"
									path="categoryId" items="${categories}" itemLabel="name"
									itemValue="id" />

								<c:if test="${product.id == 0}">
									<div class="text-right">
										<br />
										<button type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#myCategoryModal"
											class="btn btn-warning btn-sm">Add Category</button>
									</div>
								</c:if>
							</div>

						</div>

						<div class="form-group row">

							<div class="col-md-8 offset-md-4">
								<input type="submit" name="submit" id="submit" value="Submit"
									class="btn btn-primary">

								<!-- Hidden Fields for products -->

								<sf:hidden path="id" />
								<sf:hidden path="code" />
								<sf:hidden path="supplierId" />
								<sf:hidden path="active" />
								<sf:hidden path="purchases" />
								<sf:hidden path="views" />
							</div>

						</div>

					</sf:form>

				</div>

			</div>

		</div>

	</div>

	<!-- bottom manage table -->
	<div class="row">

		<div class="col-12">

			<h3>Available Products</h3>
			<hr>

		</div>

		<div class="col-12">

			<div style="overflow: auto">

				<!-- Products table for Admin -->
				<table id="adminProductsTable"
					class="table table-striped table-bordered">

					<thead>

						<tr>

							<th>Id</th>
							<th>&#160;</th>
							<th>Name</th>
							<th>Brand</th>
							<th>Quantity</th>
							<th>Unit Price</th>
							<th>Active</th>
							<th>Edit</th>

						</tr>

					</thead>


					<tfoot>

						<tr>

							<th>Id</th>
							<th>&#160;</th>
							<th>Name</th>
							<th>Brand</th>
							<th>Quantity</th>
							<th>Unit Price</th>
							<th>Active</th>
							<th>Edit</th>

						</tr>

					</tfoot>

				</table>

			</div>

		</div>

	</div>

	<div class="modal" id="myCategoryModal" role="modal" tabindex="-1">

		<div class="modal-dialog">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h4 class="modal-title">Add New Category</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>

				<!-- Modal body -->
				<div class="modal-body">
					<!-- Category Form -->

					<sf:form id="categoryForm" modelAttribute="category"
						action="${contextRoot}/manage/category" method="POST"
						class="form-horizontal">

						<div class="form-group row">

							<label class="control-label col-md-4 " for="category_name">Category
								Name</label>
							<div class="col-md-8">
								<sf:input type="text" path="name" id="category_name"
									placeholder="Category Name" class="form-control" />
							</div>

						</div>

						<div class="form-group row">

							<label class="control-label col-md-4 " for="category_description">Category
								Description</label>
							<div class="col-md-8">
								<sf:textarea cols="" rows="5" path="description"
									id="category_description" placeholder="Category description"
									class="form-control" />
							</div>

						</div>

						<div class="form-group row">

							<div class="col-md-8 offset-md-4">
								<input type="submit" name="submit" id="submit" value="Add Category"
									class="btn btn-primary">
							</div>

						</div>


					</sf:form>
				</div>

			</div>
		</div>

	</div>

</div>
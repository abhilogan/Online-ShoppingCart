$(function() {
	// solving active menu problem
	switch (menu) {
	case 'About Us':
		$('#about').addClass('active');
		break;
	case 'Contact Us':
		$('#contact').addClass('active');
		break;
	case 'Home':
		$('#homePage').addClass('active');
		break;
	case 'All Products':
		$('#listProducts').addClass('active');
		break;
	case 'Manage Products':
		$('#manageProducts').addClass('active');
		break;
	case 'User Cart':
		$('#userCart').addClass('active');
		break;
	default:
		if (menu == "Home")
			break;
		$('#listProducts').addClass('active');
		$('#a_' + menu).addClass('active');
		break;
	}

	// for handling CSRF token
	var token = $('meta[name="_csrf"]').attr('content');
	var header = $('meta[name="_csrf_header"]').attr('content');
	
	if(token.length > 0 && header.length > 0) {		
		// set the token header for the ajax request
		$(document).ajaxSend(function(e, xhr, options) {			
			xhr.setRequestHeader(header,token);			
		});				
	}
	
	
	// code for jquery dataTables..
	/*
	 * //create a dataSet
	 * 
	 * var products = [
	 * 
	 * ['1','ABC'], ['2','XYZ'], ['3','BHJ'], ['4','KIG'], // remove code and
	 * add json url below ['5','PLF'], ['6','BGT'], ['7','MGI'], ['8','HWV'] ];
	 */

	var $table = $('#productListTable');

	// execute the below code only where we have this table
	if ($table.length) {

		// in replacement to above data..
		var jsonUrl = '';
		if (window.categoryId == '') {
			jsonUrl = window.contextRoot + '/json/data/all/products';
		} else {
			jsonUrl = window.contextRoot + '/json/data/category/'
					+ window.categoryId + '/products';
		}

		// console.log('Inside the table');
		$table
				.DataTable({

					lengthMenu : [ [ 3, 5, 10, -1 ],
							[ '3 Records', '5 Records', '10 Records', 'All ' ] ],

					pageLength : 5,

					/* data:products */// remove and add ajax
					ajax : {
						url : jsonUrl,
						dataSrc : '' // part of json coming in postman
					},
					columns : [
							{
								data : 'code',
								mRender : function(data, type, row) {
									return '<img src="' + window.contextRoot
											+ '/resources/images/' + data
											+ '.jpg" class="dataTableImg"/>';
								}
							},
							{
								data : 'name'
							},
							{
								data : 'brand'
							},
							{
								data : 'unitPrice',
								mRender : function(data, type, row) {
									return '&#8377; ' + data
								}
							},
							{
								data : 'quantity',
								mRender : function(data, type, row) {

									if (data < 1) {
										return '<span style="color:red">Out of Stock!</span>';
									}
									return data;

								}
							},
							{
								data : 'id',
								bSortable : false, // now it will not allow to
													// sort for this column
								mRender : function(data, type, row) {

									var str = '';// it is html entity code
													// for providing space
									str += '<a href="'
											+ window.contextRoot
											+ '/show/'
											+ data
											+ '/product" class="btn btn-primary"><span class="glyphicon glyphicon-eye-open"></span></a> &#160;';

									
									if(userRole == 'ADMIN'){
										
										str += '<a href="'
											+ window.contextRoot
											+ '/manage/'
											+ data
											+ '/product" class="btn btn-warning"><span class="glyphicon glyphicon-pencil"></span></a>';
										
									}
									else{
									
									if (row.quantity < 1) {
										str += '<a href="javascript:void(0)" class="btn btn-success disabled"><span class="glyphicon glyphicon-shopping-cart"></span></a>';
									} else {
										
											str += '<a href="'
												+ window.contextRoot
												+ '/cart/add/'
												+ data
												+ '/product" class="btn btn-success"><span class="glyphicon glyphicon-shopping-cart"></span></a>';
							
										
									} 
									}

									return str;
								}
							}

					]

				});

	}

	// dismissing the alert after 3 seconds
	var $alert = $('.alert');

	if ($alert.length) {

		setTimeout(function() {

			$alert.fadeOut('slow');
		}, 3000)
	}
	
	//---------------------------------------------------------------------//
	
	
	//------------------------------------------------------------//
	// data tables for admin..
	//------------------------------------------------------------//
	
	var $adminProductsTable = $('#adminProductsTable');

	// execute the below code only where we have this table
	if ($adminProductsTable.length) {

		// in replacement to above data..
		var jsonUrl = window.contextRoot +'/json/data/admin/all/products';
		

		// console.log('Inside the table');
		$adminProductsTable
				.DataTable({

					lengthMenu : [ [ 10, 30, 50, -1 ],
							[ '10 Records', '30 Records', '50 Records', 'All ' ] ],

					pageLength : 30,

					/* data:products */// remove and add ajax
					ajax : {
						url : jsonUrl,
						dataSrc : '' // part of json coming in postman
					},
					columns : [
						
							{
								data : 'id'
							},
							{
								data : 'code',
								mRender : function(data, type, row) {
									return '<img src="' + window.contextRoot
											+ '/resources/images/' + data
											+ '.jpg" class="adminDataTableImg"/>';
								}
							},
							
							{
								data : 'name'
							},
							{
								data : 'brand'
							},
							
							{
								data : 'quantity',
								mRender : function(data, type, row) {

									if (data < 1) {
										return '<span style="color:red">Out of Stock!</span>';
									}
									return data;

								}
							},
							{
								data : 'unitPrice',
								mRender : function(data, type, row) {
									return '&#8377; ' + data
								}
							},
							{
								data : 'active',
								mRender: function(data,type,row){
									
									var str = '';
									
									str += '<label class="switch">';
									if(data){
										str += '<input type="checkbox" checked="checked" value="'+row.id+'">';
									}
									else{
										str += '<input type="checkbox" value="'+row.id+'">';
									}
									str += '<div class="slider round"></div></label>';
									
									return str;
								}
							},
							{
								data : 'id',
								bSorteble : false,
								mRender: function(data,type,row){
									var str = '';
									
									str += '<a href="'+window.contextRoot+'/manage/'+data+'/product" class="btn btn-warning">';
									str += '<span class="glyphicon glyphicon-pencil"></span></a>';
									
									return str;
									
								}
							}

					],
				initComplete: function(){
					
					var api = this.api();
					
					api.$('.switch input[type="checkbox"]').on('change',function(){
						
						var checkbox = $(this);
						var checked = checkbox.prop('checked');
						var dMsg = (checked)? 'You want to activate the Product ?':
											  'You want to deactivate the Product ?';
						
						var value = checkbox.prop('value');
						
						bootbox.confirm({
							size:'large',
							title:'Product Activation & Deactivation',
							message:dMsg,
							callback : function(confirmed){
								
								if(confirmed){
									
									console.log(value);
									
									//code after 5.8 video
									
									var activationUrl = window.contextRoot +'/manage/product/' + value + '/activation';
									
									$.post(activationUrl , function(data){
										
										bootbox.alert({
											size: 'medium',
											title: 'Information',
											message:data
										
										});
										
									});
						
								}
								else{
									checkbox.prop('checked',!checked);
								}
								
							}
						});
					
					});
				}

				});
	
	}
	
	//---------------------------------------------------------//
	//End of admin data table
	//---------------------------------------------------------//
	
	//validation code for category
	
	var $categoryForm = $('#categoryForm');
	
	if($categoryForm.length){
		
		$categoryForm.validate({
			
			rules :{
				
				name : {
					required : true,
					minlength:2
				},
				
				description:{
					required: true
				}
				
			},
			messages:{
				
				name :{
					required :'Please add the category name!',
					minlength:'The category name should not be less than 2 characters'
				},
				description:{
					required :'Please add the description for this category!'
					
				}
				
			},
			errorElement: 'em',
			errorPlacement: function(error,element) {
				error.addClass('help-block');
				error.insertAfter(element)
			}
			
			
		});
		
	}
	
	//-----------------------------------------------------
//validation code for login
	
	var $loginForm = $('#loginForm');
	
	if($loginForm.length){
		
		$loginForm.validate({
			
			rules :{
				
				username : {
					required : true,
					email:true
				},
				
				password:{
					required: true
				}
				
			},
			messages:{
				
				username :{
					required :'Please add the user name!',
					email:'Please enter vaild email address'
				},
				password:{
					required :'Please enter valid password!'
					
				}
				
			},
			errorElement: 'em',
			errorPlacement: function(error,element) {
				error.addClass('help-block');
				error.insertAfter(element)
			}
			
			
		});
		
	}
	//------------------------------------------
	
	//handling the refresh cart button
	
	$('button[name="refreshCart"]').click(function(){
		
		//fetch the cart line id
		var cartLineId = $(this).attr('value');
		
		var countElement = $('#count_'+ cartLineId);
		
		var originalCount = countElement.attr('value');
		var currentCount = countElement.val();
		
		// work only when the coun has changed
		
		if(currentCount !== originalCount){
			
			/*console.log("current count : "+ currentCount);
			console.log("original count : "+ originalCount);*/
			
			if(currentCount <1 || currentCount >10){
				
				countElement.val(originalCount);
				bootbox.alert({
					
					size : 'medium',
					title: 'Error',
					message:'Product count should be minimum 1 and maximun 10!!'
					
				});
			}
			else{
				var updateUrl = window.contextRoot + '/cart/'+ cartLineId + '/update?count=' + currentCount;
				
				//forward it to the controller
				window.location.href = updateUrl;
			}
			
		}
		
		
	});
	
	
	
	//-----------------------------------------
	

});

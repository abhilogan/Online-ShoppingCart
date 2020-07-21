$(function(){
	//solving active menu problem
	switch(menu){
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
	default:
		if (menu == "Home")
			break;
		$('#listProducts').addClass('active');
	    $('#a_'+menu).addClass('active');
		break;	
	}
	
	
	//code for jquery dataTables..
	/*//create a dataSet

	var products = [
		
		['1','ABC'],
		['2','XYZ'],
		['3','BHJ'],
		['4','KIG'],  // remove code and add json url below
		['5','PLF'],
		['6','BGT'],
		['7','MGI'],
		['8','HWV']
	];*/
	
	var $table = $('#productListTable');
	
	//execute the below code only where we have this table
	if($table.length) {
		
		//in replacement to above data..
		var jsonUrl = '';
		if(window.categoryId == '')
			{
			jsonUrl = window.contextRoot+'/json/data/all/products';
			}
		else
			{
			jsonUrl = window.contextRoot+'/json/data/category/'+window.categoryId+'/products';
			}
		
		
		
		//console.log('Inside the table');
		$table.DataTable({
			
			lengthMenu:[[3,5,10,-1] , ['3 Records' , '5 Records' , '10 Records' , 'All ']],
			
			pageLength:5,
			
			/*data:products*/ // remove and add ajax
			
			ajax: {
				url: jsonUrl,
				dataSrc: '' //part of json coming in postman
			},
			columns:[
				{
					data:'code',
					mRender : function(data , type, row){
						return '<img src="'+window.contextRoot+'/resources/images/'+data+'.jpg" class="dataTableImg"/>';
					}
				},
				{
					data: 'name'
				},
				{
					data: 'brand'
				},
				{
					data: 'unitPrice',
					mRender : function(data , type, row){
						return '&#8377; ' + data
					}
				},
				{
					data: 'quantity',
					mRender : function(data , type, row){
						
						if(data <1){
							return '<span style="color:red">Out of Stock!</span>';
						}
						return data;
						
					}
				},
				{
					data: 'id',
					bSortable: false, //now it will not allow to sort for this column
					mRender: function(data,type,row){
						
						var str= '';//it is html entity code for providing space								
						str += '<a href="'+window.contextRoot+'/show/'+data+'/product" class="btn btn-primary"><span class="glyphicon glyphicon-eye-open"></span></a> &#160;';
					
						if(row.quantity <1)
						{
							str += '<a href="javascript:void(0)" class="btn btn-success disabled"><span class="glyphicon glyphicon-shopping-cart"></span></a>';
						}
						else
						{
							str += '<a href="'+window.contextRoot+'/cart/add/'+data+'/product" class="btn btn-success"><span class="glyphicon glyphicon-shopping-cart"></span></a>';
						}
								
						return str;
					}
				}
				
			]
			
		});
		
	}
	
	
	
});


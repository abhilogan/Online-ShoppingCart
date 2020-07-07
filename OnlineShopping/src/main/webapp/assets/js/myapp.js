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
	default:
		$('#home').addClass('active');
		break;	
	}
});
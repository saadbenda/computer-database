$(document).ready(function() {
var intro;
var disco;
var before = '1970-01-01 00:00:01+00:00';
var after = '2038-01-19 03:14:07+00:00';
				$('#computerName').on('input', function() {
					var input=$(this);
					var is_name=input.val();
					if(is_name){input.removeClass("invalid").addClass("valid");}
					else{input.removeClass("valid").addClass("invalid");}
				});

				$('#introduced').on('input', function() {
					var input=$(this);
					intro=input.val();
					input.removeClass("invalid").addClass("valid");
					if (intro){
						var dateIntro = moment (intro);
						var dateDisco = moment (disco);
						//console.log("bool "+dateIntro.isBefore(dateDisco));
						if(dateIntro.isAfter(moment.utc(before,'YYYY-MM-DD HH:mm:ssZZ')) && dateIntro.isBefore(moment.utc(after,'YYYY-MM-DD HH:mm:ssZZ'))){
							//console.log("yep intro");
							input.removeClass("invalid").addClass("valid");
						}else{
							//console.log("nope intro")
							input.removeClass("valid").addClass("invalid");
						}
						
					}
					else{
						//console.log("nope intro exter");
						input.removeClass("invalid").addClass("valid");
					}
				});

					$('#discontinued').on('input', function() {
					/*var input=$(this);
					var disco=input.val();
					if(disco){input.removeClass("invalid").addClass("valid");}
					else{input.removeClass("valid").addClass("invalid");}*/
					var input=$(this);
					var disco=input.val();
					/*
					if (disco){
						var dateIntro = moment (intro);
						var dateDisco = moment (disco);
						if(dateDisco.isBefore(dateIntro)){
							input.removeClass("valid").addClass("invalid");
							if(dateDisco.isAfter(moment.utc(before,'YYYY-MM-DD HH:mm:ssZZ'))&&dateDisco.isBefore(moment.utc(after,'YYYY-MM-DD HH:mm:ssZZ'))){
								input.removeClass("invalid").addClass("valid");
							}else{
								input.removeClass("valid").addClass("invalid");
							}

						}
						else{
							input.removeClass("invalid").addClass("valid");
						}
					}else{
						input.removeClass("invalid").addClass("valid");
					}*/
					if (disco){
						var dateIntro = moment (intro);
						var dateDisco = moment (disco);
						if(dateDisco.isAfter(dateIntro) && dateDisco.isAfter(moment.utc(before,'YYYY-MM-DD HH:mm:ssZZ')) && dateDisco.isBefore(moment.utc(after,'YYYY-MM-DD HH:mm:ssZZ'))){
							//console.log("yep");
							input.removeClass("invalid").addClass("valid");
						}else{
							input.removeClass("valid").addClass("invalid");
						}
						
					}
					else{
						//console.log("nope");
						input.removeClass("invalid").addClass("valid");
					}
					
							
				});
		
				$('#contact_email').on('input', function() {
					var input=$(this);
					var re = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
					var is_email=re.test(input.val());
					if(is_email){input.removeClass("invalid").addClass("valid");}
					else{input.removeClass("valid").addClass("invalid");}
				});
				
				<!--Website must be a website -->
				$('#contact_website').on('input', function() {
					var input=$(this);
					if (input.val().substring(0,4)=='www.'){input.val('http://www.'+input.val().substring(4));}
					var re = /(http|ftp|https):\/\/[\w-]+(\.[\w-]+)+([\w.,@?^=%&amp;:\/~+#-]*[\w@?^=%&amp;\/~+#-])?/;
					var is_url=re.test(input.val());
					if(is_url){input.removeClass("invalid").addClass("valid");}
					else{input.removeClass("valid").addClass("invalid");}
				});
				
				<!--Message cannot be blank -->
				$('#contact_message').keyup(function(event) {
					var input=$(this);
					var message=$(this).val();
					console.log(message);
					if(message){input.removeClass("invalid").addClass("valid");}
					else{input.removeClass("valid").addClass("invalid");}	
				});
		
			<!-- After Form Submitted Validation-->
			$("#submit").click(function(event){
				// var form_data=$("#contact").serializeArray();
				// var form_data = ["computerName"]
				var element_array=[{name:"#computerName"},{name:"#introduced"},{name:"#discontinued"}]
				var error_free=true;
				for (var elem in element_array){
					// var element=$("#contact_"+form_data[input]['name']);
					var element = $(element_array[elem]['name']);
					var valid=element.hasClass("valid");
					var error_element=$("span", element.parent());
					if (!valid){error_element.removeClass("error").addClass("error_show"); error_free=false;}
					else{error_element.removeClass("error_show").addClass("error");}
				}
				if (!error_free){
					event.preventDefault(); 
				}
				else{
					alert('No errors: Form will be submitted');
				}
			});
			
			
			
		});
/*-----------------------------------------------------------------------------------
/*
/* Init JS
/*
-----------------------------------------------------------------------------------*/

 jQuery(document).ready(function($) {

/*----------------------------------------------------*/
/* FitText Settings
------------------------------------------------------ */

    setTimeout(function() {
	   $('h1.responsive-headline').fitText(1, { minFontSize: '40px', maxFontSize: '90px' });
	 }, 100);

/*----------------------------------------------------*/
/* Smooth Scrolling
------------------------------------------------------ */

   $('.smoothscroll').on('click',function (e) {
	    e.preventDefault();

	    var target = this.hash,
	    $target = $(target);

	    $('html, body').stop().animate({
	        'scrollTop': $target.offset().top
	    }, 1200, 'swing', function () {
	        window.location.hash = target;
	    });
	});


/*----------------------------------------------------*/
/* Highlight the current section in the navigation bar
------------------------------------------------------*/

	var sections = $("section");
	var navigation_links = $("#nav-wrap a");

	sections.waypoint({

      handler: function(event, direction) {

		   var active_section;

			active_section = $(this);
//alert("direction: [" + direction + "]" + "active_section1: [" + active_section + "]");
			if (direction === "up") active_section = active_section.prev();
//alert("direction: [" + direction + "]" + "active_section2: [" + active_section + "]");
			var active_link = $('#nav-wrap a[href="#' + active_section.attr("id") + '"]');
//alert("direction: [" + direction + "]" + "active_link: [" + active_link + "]");
         navigation_links.parent().removeClass("current");
			active_link.parent().addClass("current");

		},
		offset: '35%'

	});


/*----------------------------------------------------*/
/*	Make sure that #header-background-image height is
/* equal to the browser height.
------------------------------------------------------ */

   $('header').css({ 'height': $(window).height() });
   $(window).on('resize', function() {

        $('header').css({ 'height': $(window).height() });
        $('body').css({ 'width': $(window).width() })
   });


/*----------------------------------------------------*/
/*	Fade In/Out Primary Navigation
------------------------------------------------------*/

   $(window).on('scroll', function() {

		var h = $('header').height();
		var y = $(window).scrollTop();
      var nav = $('#nav-wrap');

	   if ( (y > h*.20) && (y < h) && ($(window).outerWidth() > 768 ) ) {
	      nav.fadeOut('fast');
	   }
      else {
         if (y < h*.20) {
            nav.removeClass('opaque').fadeIn('fast');
         }
         else {
            nav.addClass('opaque').fadeIn('fast');
         }
      }

	});


/*----------------------------------------------------*/
/*	Modal Popup
------------------------------------------------------*/

    $('.item-wrap a').magnificPopup({

       type:'inline',
       fixedContentPos: false,
       removalDelay: 200,
       showCloseBtn: false,
       mainClass: 'mfp-fade'

    });

    $(document).on('click', '.popup-modal-dismiss', function (e) {
    		e.preventDefault();
    		$.magnificPopup.close();
    });


/*----------------------------------------------------*/
/*	Flexslider
/*----------------------------------------------------*/
   $('.flexslider').flexslider({
      namespace: "flex-",
      controlsContainer: ".flex-container",
      animation: 'slide',
      controlNav: true,
      directionNav: false,
      smoothHeight: true,
      slideshowSpeed: 7000,
      animationSpeed: 600,
      randomize: false,
   });

/*----------------------------------------------------*/
/*	contact form
------------------------------------------------------*/

   $('form#contactForm button.submit').click(function() {

      $('#image-loader').fadeIn();

      var firstName = $('#contactForm #firstName').val();
      var lastName = $('#contactForm #lastName').val();
      var email = $('#contactForm #email').val();
      var tel = $('#contactForm #tel').val();
      var mobile = $('#contactForm #mobile').val();
      var password = $('#contactForm #password').val();
      var password2 = $('#contactForm #password2').val();
      var website = $('#contactForm #website').val();

      var data = 'firstName=' + firstName + '&lastName=' + lastName +
               '&email=' + email + '&tel=' + tel + '&mobile=' + mobile +
               '&password=' + password + '&password2=' + password2 +
               '&website=' + website;

      $.ajax({

	      type: "POST",
	      url: "inc/register.php",
	      data: data,
	      success: function(msg) {

            // Message was sent
            if (msg == 'OK') {
              //alert("OK");
               $('#image-loader').fadeOut();
               $('#message-warning').hide();
               $('#contactForm').fadeOut();
               $('#message-success').fadeIn();
            } else {
              //alert("NOT-OK");
               $('#image-loader').fadeOut();
               $('#message-warning').html(msg);
	            $('#message-warning').fadeIn();
            }

	      }

      });
      return false;
   });



   /*----------------------------------------------------*/
   /*	login form
   ------------------------------------------------------*/

      $('form#loginForm button.submit').click(function() {

         $('#image-loader').fadeIn();

         var username = $('#loginForm #username').val();
         var password = $('#loginForm #loginPassword').val();

         var data = 'username=' + username + '&password=' + password;

         $.ajax({

   	      type: "POST",
   	      url: "inc/login.php",
   	      data: data,
   	      success: function(msg) {

               // Message was sent
               if (msg == 'OK') {
                  //alert ("OK");
                  window.location.href = 'cv.php';
                  $('#image-loader').fadeOut();
                  $('#login-message-warning').hide();
                  $('#loginForm').fadeOut();
                  $('#login-message-success').fadeIn();
               } else {
                  //alert ("Login failed. Please try again");
                 //window.location.href = 'index.php#login';
                  $('#image-loader').fadeOut();
                  $('#login-message-warning').html(msg);
   	            $('#login-message-warning').fadeIn();
               }

   	      }

         });
         return false;
      });


     /*	Header form */
        $('form#headerForm button.submit').click(function() {
           $('#image-loader').fadeIn();

           var header_title = $('#headerForm #header_title').val();
           var header_location = $('#headerForm #header_location').val();
           var header_education = $('#headerForm #header_education').val();
           var header_gcse = $('#headerForm #header_gcse').val();
           var header_skills = $('#headerForm #header_skills').val();
           var header_experience = $('#headerForm #header_experience').val();
           var header_preffered_job = $('#headerForm #header_preffered_job').val();
           var header_photo = $('#headerForm #header_photo').val();

           var data = 'header_title=' + header_title
           + '&header_location=' + header_location
           + '&header_education=' + header_education
           + '&header_gcse=' + header_gcse
           + '&header_skills=' + header_skills
           + '&header_experience=' + header_experience
           + '&header_preffered_job=' + header_preffered_job
           + '&header_photo=' + header_photo;

           $.ajax({
     	      type: "POST",
     	      url: "inc/header.php",
     	      data: data,
     	      success: function(msg) {
                 // Message was sent
                 if (msg == 'OK') {
                    //alert ("OK");
                    $('#header-tick').fadeIn();
                    window.location.href = '/negotium/cv.php#header';
                    $('#image-loader').fadeOut();
                    $('#header-message-warning').hide();
                    $('#headerForm').fadeOut();
                    $('#header-message-success').fadeIn();
                 } else {
                    //alert ("Login failed. Please try again");
                   //window.location.href = 'index.php#login';
                    $('#image-loader').fadeOut();
                    $('#header-message-warning').html(msg);
     	            $('#header-message-warning').fadeIn();
                 }}});return false;});




        /*	personal_information form */
           $('form#personal_informationForm button.submit').click(function() {
              $('#image-loader').fadeIn();

              var pi_gender = $('#personal_informationForm #pi_gender').val();
              var pi_birthdate = $('#personal_informationForm #pi_birthdate').val();
              var pi_nationality = $('#personal_informationForm #pi_nationality').val();
              var pi_residence_country = $('#personal_informationForm #pi_residence_country').val();
              var pi_marital_status = $('#personal_informationForm #pi_marital_status').val();
              var pi_dependencies_number = $('#personal_informationForm #pi_dependencies_number').val();

              var data = 'pi_gender=' + pi_gender
              + '&pi_birthdate=' + pi_birthdate
              + '&pi_nationality=' + pi_nationality
              + '&pi_residence_country=' + pi_residence_country
              + '&pi_marital_status=' + pi_marital_status
              + '&pi_dependencies_number=' + pi_dependencies_number;

              $.ajax({
        	      type: "POST",
        	      url: "inc/personal_information.php",
        	      data: data,
        	      success: function(msg) {
                    // Message was sent
                    if (msg == 'OK') {
                       //alert ("OK");
                      $('#personal_information-tick').fadeIn();
                       window.location.href = '/negotium/cv.php#personal_information';
                       $('#image-loader').fadeOut();
                       $('#personal_information-message-warning').hide();
                       $('#personal_informationForm').fadeOut();
                       $('#personal_information-message-success').fadeIn();
                    } else {
                       //alert ("NOT ok");
                      //window.location.href = 'index.php#login';
                       $('#image-loader').fadeOut();
                       $('#personal_information-message-warning').html(msg);
        	            $('#personal_information-message-warning').fadeIn();
                    }}});return false;});



                    /*	contactInformationForm form */
                       $('form#contact_informationForm button.submit').click(function() {
                          $('#image-loader').fadeIn();

                          var ci_email = $('#contact_informationForm #ci_email').val();
                          var ci_mobile = $('#contact_informationForm #ci_mobile').val();
                          var ci_website = $('#contact_informationForm #ci_website').val();

                          var data = 'ci_email=' + ci_email + '&ci_mobile=' + ci_mobile + '&ci_website=' + ci_website;

                          $.ajax({
                    	      type: "POST",
                    	      url: "inc/contact_information.php",
                    	      data: data,
                    	      success: function(msg) {
                                // Message was sent
                                if (msg == 'OK') {
                                   //alert ("OK");
                                  $('#contact_information-tick').fadeIn();
                                   window.location.href = '/negotium/cv.php#contact_information';
                                   $('#image-loader').fadeOut();
                                   $('#contact_information-message-warning').hide();
                                   $('#contact_informationForm').fadeOut();
                                   $('#contact_information-message-success').fadeIn();
                                } else {
                                   //alert ("NOT ok");
                                  //window.location.href = 'index.php#login';
                                   $('#image-loader').fadeOut();
                                   $('#contact_information-message-warning').html(msg);
                    	            $('#contact_information-message-warning').fadeIn();
                                }}});return false;});



                    /*	work experience form */
                       $('form#work_experienceForm button.submit').click(function() {
                          $('#image-loader').fadeIn();

                          var we_from = $('#work_experienceForm #we_from').val();
                          var we_to = $('#work_experienceForm #we_to').val();
                          var we_title = $('#work_experienceForm #we_title').val();
                          var we_employer = $('#work_experienceForm #we_employer').val();
                          var we_description = $('#work_experienceForm #we_description').val();

                          var data = 'we_from=' + we_from +
                          '&we_to=' + we_to +
                          '&we_title=' + we_title +
                          '&we_employer=' + we_employer +
                          '&we_description=' + we_description;

                          $.ajax({
                    	      type: "POST",
                    	      url: "inc/work_experience.php",
                    	      data: data,
                    	      success: function(msg) {
                                // Message was sent
                                if (msg == 'OK') {
                                   //alert ("OK");
                                  $('#work_experience-tick').fadeIn();
                                   window.location.href = '/negotium/cv.php#work_experience';
                                   $('#image-loader').fadeOut();
                                   $('#work_experience-message-warning').hide();
                                   $('#work_experienceForm').fadeOut();
                                   $('#work_experience-message-success').fadeIn();
                                } else {
                                   //alert ("NOT ok");
                                  //window.location.href = 'index.php#login';
                                   $('#image-loader').fadeOut();
                                   $('#work_experience-message-warning').html(msg);
                    	            $('#work_experience-message-warning').fadeIn();
                                }}});return false;});


                  /*	Education form */
                     $('form#educationForm button.submit').click(function() {
                        $('#image-loader').fadeIn();

                        var edu_institution = $('#educationForm #edu_institution').val();
                        var edu_degree = $('#educationForm #edu_degree').val();
                        var edu_major = $('#educationForm #edu_major').val();
                        var edu_completion_date = $('#educationForm #edu_completion_date').val();
                        var edu_country = $('#educationForm #edu_country').val();
                        var edu_city = $('#educationForm #edu_city').val();
                        var edu_grade = $('#educationForm #edu_grade').val();
                        var edu_description = $('#educationForm #edu_description').val();

                        var data = 'edu_institution=' + edu_institution +
                        '&edu_degree=' + edu_degree +
                        '&edu_major=' + edu_major +
                        '&edu_completion_date=' + edu_completion_date +
                        '&edu_country=' + edu_country +
                        '&edu_city=' + edu_city +
                        '&edu_grade=' + edu_grade +
                        '&edu_description=' + edu_description;

                        $.ajax({
                  	      type: "POST",
                  	      url: "inc/education.php",
                  	      data: data,
                  	      success: function(msg) {
                              // Message was sent
                              if (msg == 'OK') {
                                 //alert ("OK");
                                $('#education-tick').fadeIn();
                                 window.location.href = '/negotium/cv.php#education';
                                 $('#image-loader').fadeOut();
                                 $('#education-message-warning').hide();
                                 $('#educationForm').fadeOut();
                                 $('#education-message-success').fadeIn();
                              } else {
                                 //alert ("NOT ok");
                                //window.location.href = 'index.php#login';
                                 $('#image-loader').fadeOut();
                                 $('#education-message-warning').html(msg);
                  	            $('#education-message-warning').fadeIn();
                              }}});return false;});


                  /*	languages form */
                     $('form#languagesForm button.submit').click(function() {

                        $('#languages-image-loader').fadeIn();

                        var lang_language = $('#languagesForm #lang_language').val();
                        var lang_level = $('#languagesForm #lang_level').val();

                        var data = 'lang_language=' + lang_language + '&lang_level=' + lang_level;

                        $.ajax({
                  	      type: "POST",
                  	      url: "inc/language.php",
                  	      data: data,
                  	      success: function(msg) {
                              // Message was sent
                              if (msg == 'OK') {
                                 //alert ("OK");
                                $('#languages-tick').fadeIn();
                                 window.location.href = '/negotium/cv.php#languages';
                                 $('#languages-image-loader').fadeOut();
                                 $('#languages-message-warning').hide();
                                 $('#languagesForm').fadeOut();
                                 $('#languages-message-success').fadeIn();
                              } else {
                                 //alert ("NOT ok");
                                //window.location.href = 'index.php#login';
                                 $('#languages-image-loader').fadeOut();
                                 $('#languages-message-warning').html(msg);
                  	            $('#languages-message-warning').fadeIn();
                              }}});return false;});


              /*	References form */
                 $('form#referenceForm button.submit').click(function() {

                    $('#reference-image-loader').fadeIn();

                    var ref_name = $('#referenceForm #ref_name').val();
                    var ref_title = $('#referenceForm #ref_title').val();
                    var ref_company_name = $('#referenceForm #ref_company_name').val();
                    var ref_tel = $('#referenceForm #ref_tel').val();
                    var ref_email = $('#referenceForm #ref_email').val();

                    var data = 'ref_name=' + ref_name
                    + '&ref_title=' + ref_title
                    + '&ref_company_name=' + ref_company_name
                    + '&ref_tel=' + ref_tel
                    + '&ref_email=' + ref_email;

                    $.ajax({
              	      type: "POST",
              	      url: "inc/reference.php",
              	      data: data,
              	      success: function(msg) {
                          // Message was sent
                          if (msg == 'OK') {
                             //alert ("OK");
                            $('#references-tick').fadeIn();
                             window.location.href = '/negotium/cv.php#references';
                             $('#reference-image-loader').fadeOut();
                             $('#references-message-warning').hide();
                             $('#referenceForm').fadeOut();
                             $('#references-message-success').fadeIn();
                          } else {
                             //alert ("NOT ok");
                            //window.location.href = 'index.php#login';
                             $('#reference-image-loader').fadeOut();
                             $('#references-message-warning').html(msg);
              	            $('#references-message-warning').fadeIn();
                          }}});return false;});




                  /*	Search form */
                     $('form#searchForm button.submit').click(function() {

                        $('#search-image-loader').fadeIn();

                        var job_preference = $('#searchForm #job_preference').val();
                        var minimum_education_level = $('#searchForm #minimum_education_level').val();
                        var gcse = $('#searchForm #gcse').val();
                        var education_qualification = $('#searchForm #education_qualification').val();
                        var professional_qualification = $('#searchForm #professional_qualification').val();
                        var skills = $('#searchForm #skills').val();
                        var experience = $('#searchForm #experience').val();

                        var data = 'job_preference=' + job_preference
                        + '&minimum_education_level=' + minimum_education_level
                        + '&gcse=' + gcse
                        + '&education_qualification=' + education_qualification
                        + '&professional_qualification=' + professional_qualification
                        + '&skills=' + skills
                        + '&experience=' + experience;

                        $.ajax({
                  	      type: "POST",
                  	      url: "inc/search.php",
                  	      data: data,
                  	      success: function(msg) {
                            $('#search-image-loader').fadeOut();
                            $('#search-result-builtin').html(msg);
                            $('#search-result-builtin').fadeIn();
                                 //alert ("OK");
                            window.location.href = 'search.php#search-result-builtin';
                                 //$('#search-image-loader').fadeOut();
                                 //$('#search-message-warning').hide();
                                 //$('#searchForm').fadeOut();
                                 //$('#search-message-success').fadeIn();

                               }});return false;});





});

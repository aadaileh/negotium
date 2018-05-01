<?php

session_start();
error_reporting(E_ALL);
ini_set('display_errors', 0);

//session_start();
//session_unset();
//session_destroy();
//session_write_close();

//echo "<pre>SESSION:";
//print_r($_SESSION);
//echo "</pre>";

?>

<!DOCTYPE html>
<!--[if lt IE 8 ]><html class="no-js ie ie7" lang="en"> <![endif]-->
<!--[if IE 8 ]><html class="no-js ie ie8" lang="en"> <![endif]-->
<!--[if (gte IE 8)|!(IE)]><!--><html class="no-js" lang="en"> <!--<![endif]-->
<head>

   <!--- Basic Page Needs
   ================================================== -->
   <meta charset="utf-8">
	<title>Project NEGOTIUM&reg;</title>
	<meta name="description" content="">
	<meta name="author" content="">

   <!-- Mobile Specific Metas
   ================================================== -->
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">

	<!-- CSS
    ================================================== -->
   <link rel="stylesheet" href="css/default.css">
	<link rel="stylesheet" href="css/layout.css">
   <link rel="stylesheet" href="css/media-queries.css">
   <link rel="stylesheet" href="css/magnific-popup.css">

   <!-- Script
   ================================================== -->
	<script src="js/modernizr.js"></script>

   <!-- Favicons
	================================================== -->
	<link rel="shortcut icon" href="favicon.png" >

</head>

<body>

   <!-- Header
   ================================================== -->
   <header id="home">

      <nav id="nav-wrap">

        <a class="mobile-btn" href="#nav-wrap" title="Show navigation">Show navigation</a>
	      <a class="mobile-btn" href="#" title="Hide navigation">Hide navigation</a>

         <ul id="nav" class="nav">
            <li><a class="smoothscroll" href="#home">Home</a></li>
            <li><a class="smoothscroll" href="#cvheader"><img src="images/green-tick-icon-0.png" height="15px;" width="15px;" id="header-tick" style="display:<?php if($_SESSION["cvId"] != ""){echo "inline;";}else{echo "none;";}?>"> Header</strike></a></li>
	          <li><a class="smoothscroll" href="#personal_information"><img src="images/green-tick-icon-0.png" height="15px;" width="15px;" id="personal_information-tick" style="display:<?php if($_SESSION["personalInformationId"] != ""){echo "inline;";}else{echo "none;";}?>"> Personal Info</a></li>
            <li><a class="smoothscroll" href="#contact_information"><img src="images/green-tick-icon-0.png" height="15px;" width="15px;" id="contact_information-tick" style="display:<?php if($_SESSION["contactInformationId"] != ""){echo "inline;";}else{echo "none;";}?>"> Contact Info</a></li>
            <li><a class="smoothscroll" href="#work_experience"><img src="images/green-tick-icon-0.png" height="15px;" width="15px;" id="work_experience-tick" style="display:<?php if($_SESSION["workExperienceId"] != ""){echo "inline;";}else{echo "none;";}?>"> Experience</a></li>
            <li><a class="smoothscroll" href="#education"><img src="images/green-tick-icon-0.png" height="15px;" width="15px;" id="education-tick" style="display:<?php if($_SESSION["educationsId"] != ""){echo "inline;";}else{echo "none;";}?>"> Education</a></li>
            <li><a class="smoothscroll" href="#languages"><img src="images/green-tick-icon-0.png" height="15px;" width="15px;" id="languages-tick" style="display:<?php if($_SESSION["languagesId"] != ""){echo "inline;";}else{echo "none;";}?>"> Languages</a></li>
            <li><a class="smoothscroll" href="#references"><img src="images/green-tick-icon-0.png" height="15px;" width="15px;" id="references-tick" style="display:<?php if($_SESSION["referencesId"] != ""){echo "inline;";}else{echo "none;";}?>"> References</a></li>
            <li></li>
         </ul> <!-- end #nav -->

      </nav> <!-- end #nav-wrap -->

      <div class="row banner">
         <div class="banner-text">
            <h1 class="responsive-headline">Welcome to Project NEGOTIUM<span style="vertical-align:super;font-size:40pt;">&reg;</span></h1>
            <h3><h3>Hello <?php echo $_SESSION["name"] . " " . $_SESSION["surname"]; ?></h3> Now you can create your Curriculum Vitae. Click <a class="smoothscroll" href="#cvheader">Header</a> to start creating the CV.</h3>
            <hr />
            <ul class="social">
               <li><a href="#"><i class="fa fa-facebook"></i></a></li>
               <li><a href="#"><i class="fa fa-twitter"></i></a></li>
               <li><a href="#"><i class="fa fa-google-plus"></i></a></li>
               <li><a href="#"><i class="fa fa-linkedin"></i></a></li>
               <li><a href="#"><i class="fa fa-instagram"></i></a></li>
               <li><a href="#"><i class="fa fa-dribbble"></i></a></li>
               <li><a href="#"><i class="fa fa-skype"></i></a></li>
            </ul>
         </div>
      </div>

      <p class="scrolldown">
         <a class="smoothscroll" href="#about"><i class="icon-down-circle"></i></a>
      </p>

   </header> <!-- Nix End -->
  <section id="nix"></section>
  <section id="cvheader">

           <div class="row section-head">
              <div class="ten columns header-col">
                 <br/>
                 <h1>Header Information</h1>
                 <p class="lead">Please use this section to enter the CV's header data</p>
              </div>
           </div>

           <div class="row">
              <div class="eight columns">
                 <!-- form -->
                 <form action="" method="post" id="headerForm" name="headerForm">
  					<fieldset>

                    <div>
                       <label for="header_title">Title</label>
                       <input type="text" value="Title" size="35" id="header_title" name="header_title">
                    </div>

                    <div>
  						   <label for="header_location">Location</label>
  						   <input type="text" value="Location" size="35" id="header_location" name="header_location">
                    </div>

                    <div>
  						   <label for="header_education">Education</label>
                 <select id="header_education" name="header_education">
                 <option value="0" selected>Please choose...</option>
                 <option value="1">High school or equivalent</option>
                 <option value="2">Diploma</option>
                 <option value="3" selected>Bachelor's degree</option>
                 <option value="4">Higher diploma</option>
                 <option value="5">Master's degree</option>
                 <option value="6">Doctorate</option>
                 </select>
                    </div>

                    <div>
  						   <label for="header_gcse">GCSE</label>
                 <select id="header_gcse" name="header_gcse">
                 <option value="0" selected>Please choose...</option>
                 <option value="1">Grade 1</option>
                 <option value="2">Grade 2</option>
                 <option value="3" selected>Grade 3</option>
                 <option value="4">Grade 4</option>
                 <option value="5">Grade 5</option>
                 <option value="6">Grade 6</option>
                 <option value="7">Grade 7</option>
                 <option value="8">Grade 8</option>
                 <option value="9">Grade 9</option>
                 </select>
                    </div>

                    <div>
  						   <label for="header_experience">Experience</label>
  						   <input type="text" value="Experience" size="35" id="header_experience" name="header_experience">
                    </div>

                    <div>
  						   <label for="header_preffered_job">Preffered Job</label>
  						   <input type="text" value="Java Developer. ANgularJs Developer. IT Consultant" size="35" id="header_preffered_job" name="header_preffered_job">
                    </div>

                    <div>
                 <label for="header_skills">Skills</label>
                 <input type="text" value="Excell, LabView, Technical drawing" size="35" id="header_skills" name="header_skills">
                    </div>
                    <div>
                       <label for="header_photo">Photo</label>
                       <input type="file" value="" size="35" id="header_photo" name="header_photo">
                    </div>

                    <br>

                    <div>
                       <button class="submit">Submit</button>
                       <span id="header-image-loader">
                          <img alt="" src="images/loader.gif">
                       </span>
                    </div>

  					</fieldset>
  				   </form> <!-- Form End -->

                 <!-- contact-warning -->
                 <div id="header-message-warning" style="margin-left:-25px;">Error while creating CV Header. Try again!</div>
                 <!-- contact-success -->
  				   <div id="header-message-success" style="margin-left: -25px; display:none;">
                    Your CV Header was saved successfully!<br>
  				   </div>

              </div>

        </div>

     </section> <!-- Header Section End-->
  <section id="personal_information">

       <div class="row section-head">

      <div class="ten columns header-col">
         <br/>
         <h1>Personal Information</h1>
         <p class="lead">Please use this section to enter the CV's Personal Information data</p>

      </div>

       </div>

           <div class="row">

              <div class="eight columns">

                 <!-- form -->
                 <form action="" method="post" id="personal_informationForm" name="personal_informationForm">
  					<fieldset>

                    <div>
                       <label for="pi_gender">Gender: </label>
                        <select id="pi_gender" name="pi_gender">
                        <option value="">Please choose...</option>
                        <option value="male" selected>Male</option>
                        <option value="female">Female</option>
                        </select>
                    </div>

                    <div>
        						   <label for="pi_birthdate">Birthdate: </label>
        						   <input type="date" value="1976-04-22" size="35" id="pi_birthdate" name="pi_birthdate">
                    </div>

                    <div>
  						   <label for="pi_nationality">Nationality: </label>
  						   <input type="text" value="Jordanian" size="35" id="pi_nationality" name="pi_nationality">
                    </div>

                    <div>
  						   <label for="pi_residence_country">Residence Country: </label>
  						   <input type="text" value="Germany" size="35" id="pi_residence_country" name="pi_residence_country">
                    </div>

                    <div>
                       <label for="pi_marital_status">Marital Status: </label>
                       <select id="pi_marital_status" name="pi_marital_status">
                       <option value="">Please choose...</option>
                       <option value="married" selected>Married</option>
                       <option value="single">Single</option>
                       <option value="unspecified">Unspecified</option>
                       </select>
                    </div>

                    <div>
                       <label for="pi_dependencies_number">Number of Dependences: </label>
                       <input type="text" value="3" size="35" id="pi_dependencies_number" name="pi_dependencies_number">
                    </div>

                    <div>
                       <button class="submit">Submit</button>
                       <span id="personal_information-image-loader">
                          <img alt="" src="images/loader.gif">
                       </span>
                    </div>

  					</fieldset>
  				   </form> <!-- Form End -->

             <!-- contact-warning -->
             <div id="personal_information-message-warning" style="margin-left:-25px;">Error while creating CV Personal Information. Try again!</div>
             <!-- contact-success -->
         <div id="personal_information-message-success" style="margin-left: -25px; display:none;">
                Your CV Personal Information was saved successfully!<br>
         </div>

              </div>

        </div>

     </section> <!-- Personal Information Section End-->
  <section id="contact_information">

        <div class="row section-head">

       <div class="ten columns header-col">
          <br/>
          <h1>Contact Information</h1>
          <p class="lead">Please use this section to enter the CV's Contact Information data</p>

       </div>

        </div>

          <div class="row">

             <div class="eight columns">

                <!-- form -->
                <form action="" method="post" id="contact_informationForm" name="contact_informationForm">
      			<fieldset>

                   <div>
                      <label for="ci_email">Email: </label>
                      <input type="text" value="ahmed.adaileh@gmail.com" size="35" id="ci_email" name="ci_email">
                   </div>

                   <div>
      				   <label for="ci_mobile">Mobile: </label>
      				   <input type="text" value="0123456789" size="35" id="ci_mobile" name="ci_mobile">
                   </div>

                   <div>
      				   <label for="ci_website">Website: </label>
      				   <input type="text" value="www.mywebsite.com" size="35" id="ci_website" name="ci_website">
                   </div>

                   <div>
                      <button class="submit">Submit</button>
                      <span id="contact_information-image-loader">
                         <img alt="" src="images/loader.gif">
                      </span>
                   </div>

      			</fieldset>
      		   </form> <!-- Form End -->

             <!-- contact-warning -->
             <div id="contact_information-message-warning" style="margin-left:-25px;">Error while creating CV Contact Information. Try again!</div>
             <!-- contact-success -->
         <div id="contact_information-message-success" style="margin-left: -25px; display:none;">
                Your CV Contact Information was saved successfully!<br>
         </div>

             </div>

       </div>

      </section> <!-- Contact Information Section End-->
  <section id="work_experience">

      <div class="row section-head">

     <div class="ten columns header-col">
        <br/>
        <h1>Work Experience</h1>
        <p class="lead">Please use this section to enter the Work Experience data</p>
     </div>
      </div>
             <div class="row">
                <div class="eight columns">
                   <!-- form -->
                   <form action="" method="post" id="work_experienceForm" name="work_experienceForm">
         			<fieldset>

                      <div>
                         <label for="we_from">From: </label>
                         <input type="date" value="2010-01-20" size="35" id="we_from" name="we_from">
                      </div>

                      <div>
         				   <label for="we_to">To: </label>
         				   <input type="date" value="2014-05-31" size="35" id="we_to" name="we_to">
                      </div>

                      <div>
         				   <label for="we_title">Title: </label>
         				   <input type="text" value="IT Concultant" size="35" id="we_title" name="we_title">
                      </div>

                      <div>
         				   <label for="we_employer">Employer: </label>
         				   <input type="text" value="IBM" size="35" id="we_employer" name="we_employer">
                      </div>

                      <div>
                         <label for="we_description">Description:</label>
                         <textarea type="" rows="4" cols="50" id="we_description" name="we_description">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.</textarea>
                      </div>

                      <div>
                         <button class="submit">Submit</button>
                         <span id="work_experience-image-loader">
                            <img alt="" src="images/loader.gif">
                         </span>
                      </div>

         			</fieldset>
         		   </form> <!-- Form End -->

               <!-- contact-warning -->
               <div id="work_experience-message-warning" style="margin-left:-25px;">Error while creating CV Work Experience. Try again!</div>
               <!-- contact-success -->
               <div id="work_experience-message-success" style="margin-left: -25px; display:none;">
                      Your CV Work Experience was saved successfully!<br>
               </div>

                </div>
          </div>
        </section> <!-- work experience Section End-->

     <!-- education Section
    ================================================== -->
  <section id="education">

      <div class="row section-head">

     <div class="ten columns header-col">
        <br/>
        <h1>Education</h1>
        <p class="lead">Please use this section to enter the Education data</p>

     </div>

      </div>
             <div class="row">
                <div class="eight columns">
                   <!-- form -->
                   <form action="" method="post" id="educationForm" name="educationForm">
         			<fieldset>

                      <div>
                         <label for="edu_institution">Institution: </label>
                         <input type="text" value="Kingston University" size="35" id="edu_institution" name="edu_institution">
                      </div>

                      <div>
         				   <label for="edu_degree">Degree: </label>
         				   <input type="text" value="Bachelor in Computer Science Engineering" size="35" id="edu_degree" name="edu_degree">
                      </div>

                      <div>
         				   <label for="edu_major">Major: </label>
         				   <input type="text" value="Network" size="35" id="edu_major" name="edu_major">
                      </div>

                      <div>
         				   <label for="edu_completion_date">Completion Date: </label>
         				   <input type="date" value="2012-10-11" size="35" id="edu_completion_date" name="edu_completion_date">
                      </div>

                      <div>
                         <label for="edu_country">Country: </label>
                         <input type="text" value="United Kingsdom" size="35" id="edu_country" name="edu_country">
                      </div>

                      <div>
                         <label for="edu_city">City: </label>
                         <input type="text" value="London" size="35" id="edu_city" name="edu_city">
                      </div>

                      <div>
                         <label for="edu_grade">Grade: </label>
                         <input type="text" value="2.2" size="35" id="edu_grade" name="edu_grade">
                      </div>

                      <div>
                         <label for="edu_description">Description:</label>
                         <textarea type="" rows="4" cols="50" id="edu_description" name="edu_description">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.</textarea>
                      </div>

                      <div>
                         <button class="submit">Submit</button>
                         <span id="education-image-loader">
                            <img alt="" src="images/loader.gif">
                         </span>
                      </div>

         			</fieldset>
         		   </form> <!-- Form End -->

               <!-- contact-warning -->
               <div id="education-message-warning" style="margin-left:-25px;">Error while creating CV Education. Try again!</div>
               <!-- contact-success -->
               <div id="education-message-success" style="margin-left: -25px; display:none;">
                      Your CV Education was saved successfully!<br>
               </div>

                </div>

          </div>
        </section> <!-- education Section End-->


     <!-- specialties Section
    ================================================== -->
  <section id="languages">
    <div class="row section-head">
      <div class="ten columns header-col">
        <br/>
        <h1>Languages</h1>
        <p class="lead">Please use this section to enter the Languages data</p>
      </div>
    </div>

    <div class="row">
      <div class="eight columns">
        <form action="" method="post" id="languagesForm" name="languagesForm">
          <fieldset>
            <div>
              <label for="lang_language">Language: </label>
              <input type="text" value="Arabic" size="35" id="lang_language" name="lang_language">
            </div>
          <div>

          <div>
            <label for="lang_level">Level: </label>
            <input type="text" value="Experienced" size="35" id="lang_level" name="lang_level">
          </div>

          <div>
            <button class="submit">Submit</button>
            <span id="languages-image-loader">
              <img alt="" src="images/loader.gif">
            </span>
          </div>

          </fieldset>
        </form>


        <!-- contact-warning -->
        <div id="languages-message-warning" style="margin-left:-25px;">Error while creating CV Languages. Try again!</div>
        <!-- contact-success -->
        <div id="languages-message-success" style="margin-left: -25px; display:none;">
               Your CV Languages was saved successfully!<br>
        </div>


      </div>
    </div>
  </section>
  <section id="references">

      <div class="row section-head">

     <div class="ten columns header-col">
        <br/>
        <h1>References</h1>
        <p class="lead">Please use this section to enter the References data</p>

     </div>

      </div>

           <div class="row">

              <div class="eight columns">

                 <!-- form -->
                 <form action="" method="post" id="referenceForm" name="referenceForm">
       			<fieldset>

                    <div>
                       <label for="ref_name">Name: </label>
                       <input type="text" value="Dr. Joseph Mueller" size="35" id="ref_name" name="ref_name">
                    </div>

                    <div>
       				   <label for="ref_title">Title: </label>
       				   <input type="text" value="Director" size="35" id="ref_title" name="ref_title">
                    </div>

                    <div>
       				   <label for="ref_company_name">Company name: </label>
       				   <input type="text" value="ABC Company" size="35" id="ref_company_name" name="ref_company_name">
                    </div>

                    <div>
       				   <label for="ref_tel">Telephone: </label>
       				   <input type="text" value="0123456789" size="35" id="ref_tel" name="ref_tel">
                    </div>

                    <div>
                       <label for="ref_email">Email: </label>
                       <input type="text" value="joseph.mueller@abc.co.uk" size="35" id="ref_email" name="ref_email">
                    </div>

                    <div>
                       <button class="submit">Submit</button>
                       <span id="reference-image-loader">
                          <img alt="" src="images/loader.gif">
                       </span>
                    </div>

       			</fieldset>
       		   </form> <!-- Form End -->

                 <!-- contact-warning -->
                 <div id="references-message-warning" style="margin-left:-25px;"> Error boy</div>
                 <!-- contact-success -->
       		   <div id="references-message-success" style="margin-left:-25px;">
                    Your CV References was saved successfully!<br>
       		   </div>
              </div>
        </div>
      </section> <!-- References Section End-->

      <!-- footer
      ================================================== -->
      <footer>

         <div class="row">

            <div class="twelve columns">

               <ul class="social-links">
                  <li><a href="#"><i class="fa fa-facebook"></i></a></li>
                  <li><a href="#"><i class="fa fa-twitter"></i></a></li>
                  <li><a href="#"><i class="fa fa-google-plus"></i></a></li>
                  <li><a href="#"><i class="fa fa-linkedin"></i></a></li>
                  <li><a href="#"><i class="fa fa-instagram"></i></a></li>
                  <li><a href="#"><i class="fa fa-dribbble"></i></a></li>
                  <li><a href="#"><i class="fa fa-skype"></i></a></li>
               </ul>

               <ul class="copyright">
                  <li>&copy; Copyright 2014 CeeVee</li>
                  <li>Design by <a title="Styleshout" href="http://www.styleshout.com/">Styleshout</a></li>
               </ul>

            </div>

            <div id="go-top"><a class="smoothscroll" title="Back to Top" href="#home"><i class="icon-up-open"></i></a></div>

         </div>

      </footer> <!-- Footer End-->

      <!-- Java Script
      ================================================== -->
      <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
      <script>window.jQuery || document.write('<script src="js/jquery-1.10.2.min.js"><\/script>')</script>
      <script type="text/javascript" src="js/jquery-migrate-1.2.1.min.js"></script>

      <script src="js/jquery.flexslider.js"></script>
      <script src="js/waypoints.js"></script>
      <script src="js/jquery.fittext.js"></script>
      <script src="js/magnific-popup.js"></script>
      <script src="js/init.js"></script>

</body>

</html>

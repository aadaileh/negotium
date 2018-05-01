<?php

session_start();
error_reporting(E_ALL);
ini_set('display_errors', 0);
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


  <style>
  #customers {
      font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
      border-collapse: collapse;
      width: 1000px;
  }

  #customers td, #customers th {
      border: 1px solid #ddd;
      padding: 8px;
  }

  #customers tr:nth-child(even){background-color: #f2f2f2;}

  #customers tr:nth-child(odd){background-color: #eec;}

  #customers tr:hover {background-color: #ddd;}

  #customers th {
      padding-top: 12px;
      padding-bottom: 12px;
      text-align: left;
      background-color: #a24830;
      color: white;
  }
  </style>

</head>

<body>

   <!-- Header
   ================================================== -->
   <header id="home">

      <nav id="nav-wrap">


      </nav> <!-- end #nav-wrap -->

      <div class="row banner">
         <div class="banner-text">
            <h1 class="responsive-headline">PROJECT NEGOTIUM<span style="vertical-align:super;font-size:40pt;">&reg;</span></br>Search Engine</h1>
            <h3><h3>Hello <?php echo $_SESSION["name"] . " " . $_SESSION["surname"]; ?></h3> Now you can search all Curriculum Vitaes. Click <a class="smoothscroll" href="#search_filters">Here</a> to start searching.</h3>
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
  <section id="search_filters">

           <div class="row section-head">
              <div class="ten columns header-col">
                 <br/>
                 <h1>Search Filters</h1>
                 <p class="lead">Please use this section to identify your search criteria</p>
              </div>
           </div>

           <div class="row">
              <div class="eight columns">
                 <!-- form -->
                 <form action="" method="post" id="searchForm" name="searchForm">
  					<fieldset>

                    <div>
                       <label for="job_preference">Job or Sector preference</label>
                       <input type="text" value="Java" size="35" id="job_preference" name="job_preference">
                    </div>

                    <div>
  						   <label for="minimum_education_level">Minimum education level</label>
                  <select id="minimum_education_level" name="minimum_education_level">
                  <option value="" selected>Please choose...</option>
                  <option value="1">High school or equivalent</option>
                  <option value="2">Diploma</option>
                  <option value="3" Selected>Bachelor's degree</option>
                  <option value="4">Higher diploma</option>
                  <option value="5">Master's degree</option>
                  <option value="6">Doctorate</option>
                  </select>
                    </div>

                    <div>
  						   <label for="gcse">Minimum number of GCSE passes</label>
                 <select id="gcse" name="gcse">
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
  						   <label for="education_qualification">Specific educational qualification: </label>
  						   <input type="text" value="Engineering" size="35" id="education_qualification" name="education_qualification">
                    </div>

                    <div>
  						   <label for="professional_qualification">Specific professional qualification: </label>
  						   <input type="text" value="IBM" size="35" id="professional_qualification" name="professional_qualification">
                    </div>

                    <div>
  						   <label for="skills">Specific skills: </label>
  						   <input type="text" value="Excel" size="35" id="skills" name="skills">
                    </div>

                    <div>
  						   <label for="experience">Specific experience: </label>
  						   <input type="text" value="SAP" size="35" id="experience" name="experience">
                    </div>

                    <br>

                    <div>
                       <button class="submit">Search</button>
                       <span id="search-image-loader" style="display:none;">
                          <img alt="" src="images/loader.gif">
                       </span>
                    </div>

  					</fieldset>
  				   </form> <!-- Form End -->

             <!-- contact-warning -->
             <div id="search-message-warning" style="margin-left:-25px;display:none;">Error while searching CV database. Try again!</div>
                 <!-- search results -->
                 <div id="search-result-builtin" style="margin-left:-25px; display:none;">

                       <div class="row section-head">
                      <div class="ten columns header-col">
                         <br/>
                         <h1>Search Results</h1>
                         <p class="lead">Following are the search results...</p>
                      </div>
                       </div>

                       <div class="row">
                          <div class="eight columns">

                            <table id="customers">
                              <tr>
                                <th>Name</th>
                                <th>Contact data</th>
                                <th>Education</th>
                                <th>Experience</th>
                              </tr>
                              <tr>
                                <td>Alfreds Futterkiste</td>
                                <td>Maria Anders</td>
                                <td>Germany</td>
                                <td>Germany</td>
                              </tr>
                              <tr>
                                <td>North/South</td>
                                <td>Simon Crowther</td>
                                <td>UK</td>
                                <td>Germany</td>
                              </tr>
                            </table>

                            <br><br>

                         <!-- contact-warning -->
                         <div id="personal_information-message-warning" style="margin-left:-25px;">Error while creating CV Personal Information. Try again!</div>
                         <!-- contact-success -->
                     <div id="personal_information-message-success" style="margin-left: -25px; display:none;">
                            Your CV Personal Information was saved successfully!<br>
                     </div>

                          </div>

                    </div>
                 </div>

              </div>

        </div>

     </section> <!-- Header Section End-->

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

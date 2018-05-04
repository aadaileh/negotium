<?php

session_start();
error_reporting(E_ALL);
ini_set('display_errors', 0);

$cvid = $_SESSION["cvId"];
$pic = "images/ahmed-adaileh.jpg";

//echo "<pre>";
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
            <li><a class="smoothscroll" href="#about">About</a></li>
            <li><a class="" href="search.php">Search</a></li>
            <li><a class="" href="cv.php#header">My CV</a></li>
            <li><a class="smoothscroll" href="#contact">Register</a></li>
            <li><a class="smoothscroll" href="#login">Login</a></li>
         </ul> <!-- end #nav -->

      </nav> <!-- end #nav-wrap -->

      <div class="row banner">
         <div class="banner-text">
            <h1 class="responsive-headline">Project NEGOTIUM<span style="vertical-align:super;font-size:40pt;">&reg;</span></h1>
            <h3>This Resume platform is designed for both <span>Job-Seekers</span> and <span>Recruitment Agencies</span>. <span>Job-Seekers</span> are welcome
            to use this platform to create and download their <span>Resumes</span>. Also, <span>recruitment agencies</span> are welcome to search
            in the huge CV databases to match employers' needs. More information about the developer of this website can be found
            <a class="smoothscroll" href="#about">about me</a>.<br>Let's <a class="smoothscroll" href="#about">start scrolling</a></h3>
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

   </header> <!-- Header End -->


   <!-- About Section
   ================================================== -->
   <section id="about">

      <div class="row">

         <div class="three columns">

            <img class="profile-pic"  src="<?php echo $pic;?>" alt="Ahmed Al-Adaileh" />

         </div>

         <div class="nine columns main-col">

            <h2>About Me</h2>

            <p>Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam,
            eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam
            voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione
            voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit,
            sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem.
            Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam.
            </p>

            <div class="row">

               <div class="columns contact-details">

                  <h2>Contact Details</h2>
                  <p class="address">
						   <span>Ahmed Al-Adaileh</span><br>
						   <span>Dorstener Str. 113<br>
						         Recklinghausen, GERMANY
                     </span><br>
						   <span>+49 (123)456-7890</span><br>
                     <span>ahmed.adaileh@negotium.co.uk</span>
					   </p>

               </div>

               <div class="columns download">
                  <p>
                     <a href="pdf.php?cvid=<?php echo $cvid;?>" class="button" target="_blank"><i class="fa fa-download"></i>Download Resume</a>
                  </p>
               </div>

            </div> <!-- end row -->

         </div> <!-- end .main-col -->

      </div>

   </section> <!-- About Section End-->


   <!-- Register Section
   ================================================== -->
   <section id="contact">

         <div class="row section-head">
            <div class="two columns header-col">
               <h1><span>Get In Touch.</span></h1>
            </div>
            <div class="ten columns">
                  <p class="lead">Use the following form to register yourself. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate.
                  </p>
            </div>
         </div>

         <div class="row">

            <div class="eight columns">

               <!-- form -->
               <form action="" method="post" id="contactForm" name="contactForm">
					<fieldset>

                  <div>
                     <label for="firstName">First Name <span class="required">*</span></label>
                     <input type="text" value="Ahmed" size="35" id="firstName" name="firstName">
                  </div>

                  <div>
						   <label for="lastName">Last Name <span class="required">*</span></label>
						   <input type="text" value="Adaileh" size="35" id="lastName" name="lastName">
                  </div>

                  <div>
						   <label for="email">Email <span class="required">*</span></label>
						   <input type="text" value="abu@mailinator.com" size="35" id="email" name="email">
                  </div>

                  <div>
						   <label for="password">Password <span class="required">*</span></label>
						   <input type="password" value="pass" size="35" id="password" name="password">
                  </div>

                  <div>
						   <label for="password2">Password (repeat) <span class="required">*</span></label>
						   <input type="password" value="pass" size="35" id="password2" name="password2">
                  </div>

                  <div>
						   <label for="tel">Telephone <span class="required">*</span></label>
						   <input type="text" value="1234567890" size="35" id="tel" name="tel">
                  </div>

                  <div>
                     <label for="mobile">Mobile <span class="required">*</span></label>
                     <input type="text" value="234567890" size="35" id="mobile" name="mobile">
                  </div>

                  <div>
                     <label for="website">Website</label>
                     <input type="text" value="www.mailinator.com" size="35" id="website" name="website">
                  </div>

                  <div>
                     <button class="submit">Submit</button>
                     <span id="image-loader">
                        <img alt="" src="images/loader.gif">
                     </span>
                  </div>

					</fieldset>
				   </form> <!-- Form End -->

               <!-- contact-warning -->
               <div id="message-warning">Error</div>
               <!-- contact-success -->
				   <div id="message-success">
                  <i class="fa fa-check"></i>Your registration was sent, Please check your email for Confirmation<br>
				   </div>

            </div>


            <aside class="four columns footer-widgets">


               <div class="widget widget_tweets">

                  <h4 class="widget-title">Latest Tweets</h4>

                  <ul id="twitter">
                     <li>
                        <span>
                        This is Photoshop's version  of Lorem Ipsum. Proin gravida nibh vel velit auctor aliquet.
                        Aenean sollicitudin, lorem quis bibendum auctor, nisi elit consequat ipsum
                        <a href="#">http://t.co/CGIrdxIlI3</a>
                        </span>
                        <b><a href="#">2 Days Ago</a></b>
                     </li>
                     <li>
                        <span>
                        Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam,
                        eaque ipsa quae ab illo inventore veritatis et quasi
                        <a href="#">http://t.co/CGIrdxIlI3</a>
                        </span>
                        <b><a href="#">3 Days Ago</a></b>
                     </li>
                  </ul>

		         </div>

            </aside>

      </div>

   </section> <!-- Register Section End-->

   <!-- Login Section
   ================================================== -->
   <section id="login">

         <div class="row section-head">
            <div class="ten columns header-col">
               <br/>
               <h1>Login</h1>
               <p class="lead">Please use this section to login</p>
            </div>
         </div>

         <div class="row">
            <div class="eight columns">

               <!-- form -->
               <form action="" method="post" id="loginForm" name="loginForm">
					<fieldset>

                  <div>
                     <label for="username">Username: </label>
                     <input type="text" value="" size="35" id="username" name="username">
                  </div>

                  <div>
						   <label for="loginPassword">Password: </label>
						   <input type="password" value="" size="35" id="loginPassword" name="loginPassword">
                  </div>

                  <div>
                     <button class="submit">Submit</button>
                     <span id="image-loader">
                        <img alt="" src="images/loader.gif">
                     </span>
                  </div>

					</fieldset>
				   </form> <!-- Form End -->

               <!-- contact-warning -->
          <div id="login-message-warning" style="margin-left: -25px;">Login Error</div>
               <!-- contact-success -->
				   <div id="login-message-success" style="margin-left: -25px;">
                  <i class="fa fa-check"></i>Successfully logged in, thank you!<br>
				   </div>

            </div>


            <aside class="four columns footer-widgets">


            </aside>

      </div>

   </section> <!-- Login Section End-->

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

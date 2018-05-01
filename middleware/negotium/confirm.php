<?php

session_start();
error_reporting(E_ALL);
ini_set('display_errors', 0);

if(isset($_GET['code']) && !empty($_GET['code'])) {

  // Add to databases
  $curl = curl_init();
	curl_setopt_array($curl, array(

	CURLOPT_URL => "http://localhost:8080/negotium/api/confirm/" . $_GET["code"],
  CURLOPT_CUSTOMREQUEST => "GET",
	CURLOPT_PORT=>"8080",CURLOPT_RETURNTRANSFER=>true,CURLOPT_ENCODING=>"",CURLOPT_MAXREDIRS=>10,CURLOPT_TIMEOUT=>30,CURLOPT_HTTP_VERSION=>CURL_HTTP_VERSION_1_1,CURLOPT_HTTPHEADER => array("authorization: Basic YXBpdXNlcjpwYXNz","content-type: application/json")
));

	$response = curl_exec($curl);
	$err = curl_error($curl);

	curl_close($curl);

	if ($err) {
	echo "cURL Error #:" . $err;
    $result = "<h3>Registration failed</h3><p>Please try again!!</p>";

	} else {
	//echo "response: " . $response;
	$data = json_decode($response);

//echo "<pre>data:";
//print_r($data);
//echo "</pre>";

if ($data == 1) {
$result = "<h3>Registration confirmed</h3><p>Thank you very much</p><a href=\"index.php#login\">Click here to login</a>";
} else {
    $result = "<h3>Registration failed</h3><p>Please try again!!</p>";
}

	}


} else {
  $result = "<h3>Registration failed</h3><p>Please try again!!</p>";
}

?>
<!DOCTYPE html>
<!--[if lt IE 8 ]><html class="no-js ie ie7" lang="en"> <![endif]-->
<!--[if IE 8 ]><html class="no-js ie ie8" lang="en"> <![endif]-->
<!--[if (gte IE 8)|!(IE)]><!--><html class="no-js" lang="en"> <!--<![endif]-->
<head>

   <!--- Basic Page Needs
   ================================================== -->
   <meta charset="utf-8">
	<title>Ceevee - Free Responsive HTML5/CSS3 Template</title>
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
            <li class="current"><a href="index.html#home">Home</a></li>
            <li><a href="index.html#about">About</a></li>
	          <li><a href="index.html#resume">Resume</a></li>
            <li><a href="index.html#portfolio">Works</a></li>
            <li><a href="index.html#testimonials">Testimonials</a></li>
            <li><a href="index.php#login">Login</a></li>
         </ul> <!-- end #nav -->

      </nav> <!-- end #nav-wrap -->

      <div class="row banner">
         <div class="banner-text">
            <h3><?php echo $result;?></h3>
            <hr />

         </div>
      </div>

   </header> <!-- Header End -->

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

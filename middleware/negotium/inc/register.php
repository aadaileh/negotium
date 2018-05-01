<?php

session_start();
error_reporting(E_ALL);
ini_set('display_errors', 0);

// Replace this with your own email address
$siteOwnersEmail = 'registration@negotium.co.uk';

//print_r($_POST);

if($_POST) {

   $firstName = trim(stripslashes($_POST['firstName']));
   $lastName = trim(stripslashes($_POST['lastName']));
   $email = trim(stripslashes($_POST['email']));
   $password = trim(stripslashes($_POST['password']));
   $password2 = trim(stripslashes($_POST['password2']));
   $tel = trim(stripslashes($_POST['tel']));
   $mobile = trim(stripslashes($_POST['mobile']));
   $website = trim(stripslashes($_POST['website']));

   // Check First Name
	if (strlen($firstName) < 2) {
		$error['firstName'] = "Please enter your first name.";
	}
	// Check Last Name
	if (strlen($lastName) < 2) {
		$error['lastName'] = "Please enter your last name.";
	}
	// Check Email
	if (!preg_match('/^[a-z0-9&\'\.\-_\+]+@[a-z0-9\-]+\.([a-z0-9\-]+\.)*+[a-z]{2}/is', $email)) {
		$error['email'] = "Please enter a valid email address.";
	}
	// Check Telephone / mobile
	if (strlen($tel) < 2 AND strlen($mobile) < 2 ) {
		$error['tel'] = "Please enter your Telephone or mobile";
	}
  //check password2
  if (strlen($password) < 4 OR $password != $password2  ) {
    $error['password'] = "Please check your password. It is either too short or does not match.";
  }

  $uniqid = uniqidReal(35);

  // Add to databases
  $curl = curl_init();
	curl_setopt_array($curl, array(

	CURLOPT_URL => "http://localhost:8080/negotium/api/register",
  CURLOPT_CUSTOMREQUEST => "POST",
  CURLOPT_POSTFIELDS => "{
      \"name\":\"" . $_POST['firstName'] . "\",
      \"surname\":\"" . $_POST['lastName'] . "\",
      \"email\":\"" . $_POST['email'] . "\",
      \"tel\":\"" . $_POST['tel'] . "\",
      \"mobile\":\"" . $_POST['mobile'] . "\",
      \"password\":\"" . $_POST['password'] . "\",
      \"uniqid\":\"" . $uniqid . "\"
    }",
	CURLOPT_PORT=>"8080",
  CURLOPT_RETURNTRANSFER=>true,
  CURLOPT_ENCODING=>"",
  CURLOPT_MAXREDIRS=>10,
  CURLOPT_TIMEOUT=>30,
  CURLOPT_HTTP_VERSION=>CURL_HTTP_VERSION_1_1,
  CURLOPT_HTTPHEADER => array(
    "authorization: Basic YXBpdXNlcjpwYXNz",
    "content-type: application/json")
));

	$response = curl_exec($curl);
	$err = curl_error($curl);

	curl_close($curl);

	if ($err) {
	echo "cURL Error #:" . $err;

	} else {
	//echo "response: " . $response;
	$data = json_decode($response);

//	echo "<pre>data:";
//	print_r($data);
//	echo "</pre>";
	}

   // Subject
	$subject = "Confirm your registration in NEGOTIUM";
	// Confirmation link
	$confirmationLink = "<a href='http://localhost/negotium/confirm.php?code=".$uniqid."'> http://localhost/negotium/confirm.php?code=".$uniqid." </a>";
   // Set Message
   $message = "";
   $message .= "Dear " . $firstName . " " . $lastName . ", <br />";
   $message .= "<br />";
   $message .= "Thank you for registering in NEGORIUM." . "<br />";
   $message .= "<br />";
   $message .= "Please click this " . $confirmationLink . " to confirm your registration." . "<br />";
   $message .= "<br />";
   $message .= "Thank you for chosing our NEGOTIUM Website." . "<br />";
   $message .= "<br />";
   $message .= "Kind regards," . "<br />";
   $message .= "The Admin" . "<br />";

   // Set From: header
   $from =  "NEGOTIUM Registration<" . $siteOwnersEmail . ">";

   // Email Headers
	$headers = "From: " . $from . "\r\n";
	$headers .= "Reply-To: ". $siteOwnersEmail . "\r\n";
 	$headers .= "MIME-Version: 1.0\r\n";
	$headers .= "Content-Type: text/html; charset=ISO-8859-1\r\n";

   if (!isset($error) && !$error) {

      $mail = mail($email, $subject, $message, $headers);

		if ($mail) {
      echo "OK";
    } else {
      echo "Something went wrong. Please try again.";
    }

	} else {

		$response = (isset($error['firstName'])) ? $error['firstName'] . "<br /> \n" : null;
    $response .= (isset($error['lastName'])) ? $error['lastName'] . "<br /> \n" : null;
		$response .= (isset($error['tel'])) ? $error['tel'] . "<br /> \n" : null;
    $response .= (isset($error['email'])) ? $error['email'] . "<br /> \n" : null;
    $response .= (isset($error['password'])) ? $error['password'] . "<br /> \n" : null;
		$response .= (isset($error['message'])) ? $error['message'] . "<br />" : null;

		echo $response;

	}

}


//generate unique
function uniqidReal($lenght = 13) {
  // uniqid gives 13 chars, but you could adjust it to your needs.
  if (function_exists("random_bytes")) {
      $bytes = random_bytes(ceil($lenght / 2));
  } elseif (function_exists("openssl_random_pseudo_bytes")) {
      $bytes = openssl_random_pseudo_bytes(ceil($lenght / 2));
  } else {
      throw new Exception("no cryptographically secure random function available");
  }
  return substr(bin2hex($bytes), 0, $lenght);
}
?>

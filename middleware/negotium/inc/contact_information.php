<?php

session_start();
error_reporting(E_ALL);
ini_set('display_errors', 0);

if($_POST) {

   $ci_email = trim(stripslashes($_POST['ci_email']));
   $ci_mobile = trim(stripslashes($_POST['ci_mobile']));
   $ci_website = trim(stripslashes($_POST['ci_website']));

   $userId = $_SESSION["userId"];
   $cvId = $_SESSION["cvId"];

  if (!isset($error) && !$error) {

    // verify login
    $curl = curl_init();
    curl_setopt_array($curl, array(
    	CURLOPT_URL => "http://localhost:8080/negotium/api/save/cv/contact-information/",
    CURLOPT_CUSTOMREQUEST => "POST",
    CURLOPT_POSTFIELDS => "{
        \"email\":\"" . $ci_email . "\",
        \"mobile\":\"" . $ci_mobile . "\",
        \"website\":\"" . $ci_website . "\",
        \"usersId\":\"" . $userId . "\",
        \"cvId\":\"" . $cvId . "\"
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
      $response = "Create CV Personal Information failed<br>Please try again!!";
  	} else {
  	$data = json_decode($response);
  if ($data != "") {
    $_SESSION["contactInformationId"] = $data->contactInformationId;
    $response = "OK";
  } else {
    $response = "Create CV Contact Information failed<br>Please try again!!";
  }
  }
 }
    echo $response;
}
?>

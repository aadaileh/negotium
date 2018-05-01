<?php

session_start();
error_reporting(E_ALL);
ini_set('display_errors', 0);

if($_POST) {

   $header_title = trim(stripslashes($_POST['header_title']));
   $header_location = trim(stripslashes($_POST['header_location']));
   $header_education = trim(stripslashes($_POST['header_education']));
   $header_gcse = trim(stripslashes($_POST['header_gcse']));
   $header_skills = trim(stripslashes($_POST['header_skills']));
   $header_experience = trim(stripslashes($_POST['header_experience']));
   $header_preffered_job = trim(stripslashes($_POST['header_preffered_job']));
   $header_photo = trim(stripslashes($_POST['header_photo']));

   $userId = $_SESSION["userId"];

  if (!isset($error) && !$error) {

    // verify login
    $curl = curl_init();
    curl_setopt_array($curl, array(
    	CURLOPT_URL => "http://localhost:8080/negotium/api/save/cv/header/",
    CURLOPT_CUSTOMREQUEST => "POST",
    CURLOPT_POSTFIELDS => "{
        \"title\":\"" . $header_title . "\",
        \"location\":\"" . $header_location . "\",
        \"education\":\"" . $header_education . "\",
        \"gcse\":\"" . $header_gcse . "\",
        \"skills\":\"" . $header_skills . "\",
        \"experience\":\"" . $header_experience . "\",
        \"prefferedJob\":\"" . $header_preffered_job . "\",
        \"photo\":\"" . $header_photo . "\",
        \"usersId\":\"" . $userId . "\"
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
      $response = "Create CV header failed<br>Please try again!!";
  	} else {
  	$data = json_decode($response);

  if ($data != "") {
    $_SESSION["cvId"] = $data->cvId;
    $_SESSION["headerId"] = $data->cvId; //cv-id is the same as header-id (no table for header!!)
    $response = "OK";
  } else {
    $response = "Create CV header failed<br>Please try again!!";
  }
  }


 } else {

   $response = (isset($error['username'])) ? $error['username'] . "<br /> \n" : null;
   $response .= (isset($error['password'])) ? $error['password'] . "<br /> \n" : null;

 }

    echo $response;
}
?>

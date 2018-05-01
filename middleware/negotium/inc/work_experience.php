<?php

session_start();
error_reporting(E_ALL);
ini_set('display_errors', 0);

if($_POST) {

   $we_from = trim(stripslashes($_POST['we_from']));
   $we_to = trim(stripslashes($_POST['we_to']));
   $we_title = trim(stripslashes($_POST['we_title']));
   $we_employer = trim(stripslashes($_POST['we_employer']));
   $we_description = trim(stripslashes($_POST['we_description']));

   $userId = $_SESSION["userId"];
   $cvId = $_SESSION["cvId"];

  if (!isset($error) && !$error) {

    // verify login
    $curl = curl_init();
    curl_setopt_array($curl, array(
    	CURLOPT_URL => "http://localhost:8080/negotium/api/save/cv/work-experience/",
    CURLOPT_CUSTOMREQUEST => "POST",
    CURLOPT_POSTFIELDS => "{
        \"from\":\"" . $we_from . "\",
        \"to\":\"" . $we_to . "\",
        \"title\":\"" . $we_title . "\",
        \"employer\":\"" . $we_employer . "\",
        \"description\":\"" . $we_description . "\",
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
      $response = "Create CV (Work Experience) failed<br>Please try again!!";
  	} else {
  	$data = json_decode($response);
  if ($data != "") {
    $_SESSION["workExperienceId"] = $data->workExperienceId;
    $response = "OK";
  } else {
    $response = "Create CV (Work Experience) failed<br>Please try again!!";
  }
  }
 }
    echo $response;
}
?>

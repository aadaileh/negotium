<?php

session_start();
error_reporting(E_ALL);
ini_set('display_errors', 0);

if($_POST) {

   $ref_name = trim(stripslashes($_POST['ref_name']));
   $ref_title = trim(stripslashes($_POST['ref_title']));
   $ref_company_name = trim(stripslashes($_POST['ref_company_name']));
   $ref_tel = trim(stripslashes($_POST['ref_tel']));
   $ref_email = trim(stripslashes($_POST['ref_email']));

   $userId = $_SESSION["userId"];
   $cvId = $_SESSION["cvId"];

  if (!isset($error) && !$error) {

    // verify login
    $curl = curl_init();
    curl_setopt_array($curl, array(
    	CURLOPT_URL => "http://localhost:8080/negotium/api/save/cv/reference/",
    CURLOPT_CUSTOMREQUEST => "POST",
    CURLOPT_POSTFIELDS => "{
        \"name\":\"" . $ref_name . "\",
        \"title\":\"" . $ref_title . "\",
        \"companyName\":\"" . $ref_company_name . "\",
        \"tel\":\"" . $ref_tel . "\",
        \"email\":\"" . $ref_email . "\",
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
      $response = "Create CV (References) failed<br>Please try again!!";
  	} else {
  	$data = json_decode($response);
  if ($data != "") {
    $_SESSION["referencesId"] = $data->referencesId;
    $response = "OK";
  } else {
    $response = "Create CV (References) failed<br>Please try again!!";
  }
  }
 }
    echo $response;
}
?>

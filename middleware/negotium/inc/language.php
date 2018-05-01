<?php

session_start();
error_reporting(E_ALL);
ini_set('display_errors', 0);

if($_POST) {

   $lang_language = trim(stripslashes($_POST['lang_language']));
   $lang_level = trim(stripslashes($_POST['lang_level']));

   $userId = $_SESSION["userId"];
   $cvId = $_SESSION["cvId"];

  if (!isset($error) && !$error) {

    // verify login
    $curl = curl_init();
    curl_setopt_array($curl, array(
    	CURLOPT_URL => "http://localhost:8080/negotium/api/save/cv/language/",
    CURLOPT_CUSTOMREQUEST => "POST",
    CURLOPT_POSTFIELDS => "{
        \"language\":\"" . $lang_language . "\",
        \"level\":\"" . $lang_level . "\",
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
      $response = "Create CV (Languages) failed<br>Please try again!!";
  	} else {
  	$data = json_decode($response);
  if ($data != "") {
    $_SESSION["languagesId"] = $data->languagesId;
    $response = "OK";
  } else {
    $response = "Create CV (Languages) failed<br>Please try again!!";
  }
  }
 }
    echo $response;
}
?>

<?php

session_start();
error_reporting(E_ALL);
ini_set('display_errors', 0);

if($_POST) {

   $edu_institution = trim(stripslashes($_POST['edu_institution']));
   $edu_degree = trim(stripslashes($_POST['edu_degree']));
   $edu_major = trim(stripslashes($_POST['edu_major']));
   $edu_completion_date = trim(stripslashes($_POST['edu_completion_date']));
   $edu_country = trim(stripslashes($_POST['edu_country']));
   $edu_city = trim(stripslashes($_POST['edu_city']));
   $edu_grade = trim(stripslashes($_POST['edu_grade']));
   $edu_description = trim(stripslashes($_POST['edu_description']));

   $userId = $_SESSION["userId"];
   $cvId = $_SESSION["cvId"];

  if (!isset($error) && !$error) {

    // verify login
    $curl = curl_init();
    curl_setopt_array($curl, array(
    	CURLOPT_URL => "http://localhost:8080/negotium/api/save/cv/education/",
    CURLOPT_CUSTOMREQUEST => "POST",
    CURLOPT_POSTFIELDS => "{
        \"institution\":\"" . $edu_institution . "\",
        \"degree\":\"" . $edu_degree . "\",
        \"major\":\"" . $edu_major . "\",
        \"completionDate\":\"" . $edu_completion_date . "\",
        \"country\":\"" . $edu_country . "\",
        \"city\":\"" . $edu_city . "\",
        \"grade\":\"" . $edu_grade . "\",
        \"description\":\"" . $edu_description . "\",
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
      $response = "Create CV (Education) failed<br>Please try again!!";
  	} else {
  	$data = json_decode($response);
  if ($data != "") {
    $_SESSION["educationsId"] = $data->educationsId;
    $response = "OK";
  } else {
    $response = "Create CV (Education) failed<br>Please try again!!";
  }
  }
 }
    echo $response;
}
?>

<?php

session_start();
error_reporting(E_ALL);
ini_set('display_errors', 0);

if($_POST) {

   $pi_gender = trim(stripslashes($_POST['pi_gender']));
   $pi_birthdate = trim(stripslashes($_POST['pi_birthdate']));
   $pi_nationality = trim(stripslashes($_POST['pi_nationality']));
   $pi_residence_country = trim(stripslashes($_POST['pi_residence_country']));
   $pi_marital_status = trim(stripslashes($_POST['pi_marital_status']));
   $pi_dependencies_number = trim(stripslashes($_POST['pi_dependencies_number']));

   $userId = $_SESSION["userId"];
   $cvId = $_SESSION["cvId"];

  if (!isset($error) && !$error) {

    // verify login
    $curl = curl_init();
    curl_setopt_array($curl, array(
    	CURLOPT_URL => "http://localhost:8080/negotium/api/save/cv/personal-information/",
    CURLOPT_CUSTOMREQUEST => "POST",
    CURLOPT_POSTFIELDS => "{
        \"gender\":\"" . $pi_gender . "\",
        \"birthDate\":\"" . $pi_birthdate . "\",
        \"nationality\":\"" . $pi_nationality . "\",
        \"residenceCountry\":\"" . $pi_residence_country . "\",
        \"maritalStatus\":\"" . $pi_marital_status . "\",
        \"dependenciesNumber\":\"" . $pi_dependencies_number . "\",
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
    $_SESSION["personalInformationId"] = $data->personalInformationId;
    $response = "OK";
  } else {
    $response = "Create CV Personal Information failed<br>Please try again!!";
  }
  }
 }
    echo $response;
}
?>

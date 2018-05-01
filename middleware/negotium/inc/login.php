<?php

session_start();
error_reporting(E_ALL);
ini_set('display_errors', 0);

//print_r($_POST);

if($_POST) {

   $username = trim(stripslashes($_POST['username']));
   $password = trim(stripslashes($_POST['password']));

   // Check First Name
	if (strlen($username) < 2) {
		$error['username'] = "Please enter a username.";
	}
	// Check Last Name
	if (strlen($password) < 2) {
		$error['password'] = "Please enter a password.";
	}

  if (!isset($error) && !$error) {

    // verify login
    $curl = curl_init();
    curl_setopt_array($curl, array(
  	CURLOPT_URL => "http://localhost:8080/negotium/api/login/",
    CURLOPT_CUSTOMREQUEST => "POST",
    CURLOPT_POSTFIELDS => "{
        \"username\":\"" . $username . "\",
        \"password\":\"" . $password . "\"
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
      $response = "Login failed<br>Please try again!!";
  	} else {
  	$data = json_decode($response);

    //echo "<pre>data:";
    //print_r($data);
    //echo "</pre>";

  if ($data->name != "") {
    $_SESSION["credentialsId"] = $data->credentialsId;
    $_SESSION["cvId"] = $data->cvId;
    $_SESSION["userId"] = $data->userId;
    $_SESSION["name"] = $data->name;
    $_SESSION["surname"] = $data->surname;
    $_SESSION["contactInformationId"] = $data->contactInformationId;
    $_SESSION["educationsId"] = $data->educationsId;
    $_SESSION["languagesId"] = $data->languagesId;
    $_SESSION["personalInformationId"] = $data->personalInformationId;
    $_SESSION["referencesId"] = $data->referencesId;
    $_SESSION["workExperienceId"] = $data->workExperienceId;

    $response = "OK";
  } else {
    $_SESSION["name"] = '';
    $_SESSION["surname"] = '';
    $response = "Login failed<br>Please try again!!";
  }
  }


 } else {

   $response = (isset($error['username'])) ? $error['username'] . "<br /> \n" : null;
   $response .= (isset($error['password'])) ? $error['password'] . "<br /> \n" : null;

 }

    echo $response;
}
?>

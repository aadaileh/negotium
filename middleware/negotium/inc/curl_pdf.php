<?php

session_start();
error_reporting(E_ALL);
ini_set('display_errors', 1);

if($_GET) {

    $curl = curl_init();
    curl_setopt_array($curl, array(
    CURLOPT_URL => "http://localhost:8080/negotium/api/cv/" . trim(stripslashes($_GET['cvid'])),
    CURLOPT_CUSTOMREQUEST => "GET",
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
      $response = "Search CVs failed<br>Please try again!!";
    } else {
    $data = json_decode($response);

  if ($data != "") {
    $response = "OK";
  } else {
    $response = "Search CVs failed<br>Please try again!!";
  }
  }
}
?>
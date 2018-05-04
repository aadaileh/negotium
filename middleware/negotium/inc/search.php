<?php

session_start();
error_reporting(E_ALL);
ini_set('display_errors', 0);

if($_POST) {

   $job_preference = trim(stripslashes($_POST['job_preference']));
   $minimum_education_level = trim(stripslashes($_POST['minimum_education_level']));
   $gcse = trim(stripslashes($_POST['gcse']));
   $education_qualification = trim(stripslashes($_POST['education_qualification']));
   $professional_qualification = trim(stripslashes($_POST['professional_qualification']));
   $skills = trim(stripslashes($_POST['skills']));
   $experience = trim(stripslashes($_POST['experience']));

  if (!isset($error) && !$error) {

    // verify login
    $curl = curl_init();
    curl_setopt_array($curl, array(
    	CURLOPT_URL => "http://localhost:8080/negotium/api/search/cv/",
    CURLOPT_CUSTOMREQUEST => "POST",
    CURLOPT_POSTFIELDS => "{
        \"jobOrSectorPreference\":\"" . $job_preference . "\",
        \"minimumEducationLevel\":\"" . $minimum_education_level . "\",
        \"minimumNumberOfGCSE\":\"" . $gcse . "\",
        \"educationalQualification\":\"" . $education_qualification . "\",
        \"professionalQualification\":\"" . $professional_qualification . "\",
        \"skills\":\"" . $skills . "\",
        \"experiences\":\"" . $experience . "\"
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
      $response = "Search CVs failed<br>Please try again!!";
  	} else {
  	$data = json_decode($response);

/*
echo "<pre>data:\n";
print_r($data);
echo "</pre>";
*/

  if ($data != "") {
    $response = "OK";
  } else {
    $response = "Search CVs failed<br>Please try again!!";
  }
  }
 }
//    echo $response;
}
?>

<!-- search results -->
<div id="search-result-builtin" style="margin-left:-25px;">

      <div class="row section-head">
     <div class="ten columns header-col">
        <br/>
        <h1>Search Results</h1>
        <p class="lead">Following are the search results...</p>
     </div>
      </div>

      <div class="row">
         <div class="eight columns">

           <table id="customers">
             <tr>
               <th>Name</th>
               <th>Contact data</th>
               <th>Education</th>
               <th>Experience</th>
               <th></th>
             </tr>
<?php

foreach ($data as $k => $v) {
  echo"  <tr>";
  echo"    <td>".$v->name."</td>";
  echo"    <td>".$v->contactData."</td>";
  if($v->education == 1) echo "<td>High school or equivalent</td>";
  if($v->education == 2) echo "<td>Diploma</td>";
  if($v->education == 3) echo "<td>Bachelor's degree</td>";
  if($v->education == 4) echo "<td>Higher diploma</td>";
  if($v->education == 5) echo "<td>Master's degree</td>";
  if($v->education == 6) {echo "<td>Doctorate</td>";}
  echo"    <td>".$v->experience."</td>";
  echo"    <td><a class=\"button\" href=\"pdf.php?cvid=".$v->cvId."\" style=\"background: #525252; padding: 4px 10px;\">Download&nbsp;CV</a></td>";
  echo"  </tr>";
}

?>
           </table>

           <br><br>

        <!-- contact-warning -->
        <div id="personal_information-message-warning" style="margin-left:-25px;">Error while creating CV Personal Information. Try again!</div>
        <!-- contact-success -->
    <div id="personal_information-message-success" style="margin-left: -25px; display:none;">
           Your CV Personal Information was saved successfully!<br>
    </div>

         </div>

   </div>
</div>

<?php
error_reporting(E_ALL);
ini_set('display_errors', 0);

require('fpdf181/fpdf.php');
require('inc/curl_pdf.php');
require('inc/pdf_class.php');

//echo "<pre>data:\n";
//print_r($data);
//echo "</pre>";

//TODO:
//Prepare my cv properly dynamically in db
//link db fields in the fpdf class
//UNIT TESTING
//Selenium IDE


$pdf = new PDF();
$pdf->SetFont('Arial','',14);
$pdf->AddPage();
$pdf->CreateCV($data);
//$pdf->Output('D','curriculum-vitae.pdf');
$pdf->Output();
?>
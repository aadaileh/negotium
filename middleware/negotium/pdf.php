<?php
require('fpdf181/fpdf.php');
require('inc/curl_pdf.php');
require('inc/pdf_class.php');

//echo "<pre>data:\n";
//print_r($data);
//echo "</pre>";

$pdf = new PDF();
$pdf->SetFont('Arial','',14);
$pdf->AddPage();
$pdf->CreateCV($data);
$pdf->Output();
?>
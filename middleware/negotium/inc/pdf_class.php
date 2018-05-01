<?php

class PDF extends FPDF
{

    function CreateCV($data)
    {

        $experience = "18 Years, 10 Months";

        $this->Image('images/ahmed-adaileh.jpg',10,10,50,0,'JPEG');
        $this->SetFont('Arial','B',20);
        $this->Text(80,25,"AHMED AL-ADAILEH");
        
        $this->SetFont('Arial','B',12);
        $this->Text(80,35,"IT Consultant - Web Application");
        
        $this->SetFont('Arial','',10);
        $this->Text(80,40,"at Vaillant Group Business Services");

        $this->SetFont('Arial','B',12);
        $this->Text(80,50,"Location:"); 
        $this->SetFont('Arial','',12);
        $this->Text(105,50,"Germany"); 

        $this->SetFont('Arial','B',12);
        $this->Text(80,57,"Education:");
        $this->SetFont('Arial','',12);
        $this->Text(105,57,"Master's degree, Software Engineering (on-going)");     

        $this->SetFont('Arial','B',12);
        $this->Text(80,63,"Experience:");
        $this->SetFont('Arial','',12);
        $this->Text(105,63,$experience);


    //SECTION: Contact
        $this->SetFont('Arial','B',16);
        $this->Text(10,100,"CONTACT");
        $this->Line(10,105,200,105);

        $this->SetFont('Arial','B',12);
        $this->Text(10,113,"Address:"); 
        $this->SetFont('Arial','',12);
        $this->Text(35,113,"Dorstener Str. 113, 45657, Recklinghausen GERMANY"); 

        $this->SetFont('Arial','B',12);
        $this->Text(10,122,"Telephone:"); 
        $this->SetFont('Arial','',12);
        $this->Text(35,122,"+49 (0) 1766 689 10 78"); 
    
        $this->SetFont('Arial','B',12);
        $this->Text(10,131,"Email:"); 
        $this->SetFont('Arial','',12);
        $this->Text(35,131,"ahmed.adaileh@gmail.com"); 
    
        $this->SetFont('Arial','B',12);
        $this->Text(10,140,"Website:"); 
        $this->SetFont('Arial','',12);
        $this->Text(35,140,"Not available"); 


    //SECTION: Personal Information
        $this->SetFont('Arial','B',16);
        $this->Text(10,165,"PERSONAL INFORMATION");
        $this->Line(10,170,200,170);

        $this->SetFont('Arial','B',12);
        $this->Text(10,179,"Birth date:"); 
        $this->SetFont('Arial','',12);
        $this->Text(65,179,"22/04/1976"); 

        $this->SetFont('Arial','B',12);
        $this->Text(10,188,"Gender:"); 
        $this->SetFont('Arial','',12);
        $this->Text(65,188,"Male"); 
    
        $this->SetFont('Arial','B',12);
        $this->Text(10,197,"Nationality:"); 
        $this->SetFont('Arial','',12);
        $this->Text(65,197,"German"); 
    
        $this->SetFont('Arial','B',12);
        $this->Text(10,205,"Residence country:"); 
        $this->SetFont('Arial','',12);
        $this->Text(65,205,"Germany / United Kingdom"); 
    
        $this->SetFont('Arial','B',12);
        $this->Text(10,212,"Marital Status:"); 
        $this->SetFont('Arial','',12);
        $this->Text(65,212,"Married"); 

        $this->SetFont('Arial','B',12);
        $this->Text(10,219,"Number of dependencies:"); 
        $this->SetFont('Arial','',12);
        $this->Text(65,219,"3");

        $this->SetFont('Arial','B',12);
        $this->Text(10,226,"Driving License/where?:"); 
        $this->SetFont('Arial','',12);
        $this->Text(65,226,"Yes. Jordan, Germany"); 

        $this->AddPage('P', '', 0);

    //SECTION: Experience
        $this->SetFont('Arial','B',16);
        $this->Text(10,20,"WORK EXPERIENCES (" . $experience . ")");
        $this->Line(10,25,200,25);
        
        $x=13;
        $this->SetFont('Arial','',10);
        $this->Text($x,35,"2000");
        $this->Text($x+10,35,"- 2005"); 

        $this->SetFont('Arial','B',14);
        $this->Text($x,41,"IT Consultant - Web Applications"); 

        $this->SetFont('Arial','',10);
        $this->Text($x,47,"at Vaillant Group Business Services");  

        $this->SetFont('Arial','',9);
        $this->SetY(55);
        $this->SetX($x-1);
        $this->MultiCell(0,5,"I obtained the German IT Green Card where I got the chance to continue working with Vignette StoryServer as a platform for the content management system used in this company, one of my tasks was giving the necessary technical education to the other colleagues, enabling them to develop high quality applications. Then I got the chance to be involved in a PHP-MySql-Linux platform based CMS project. Since the company based on SAP, we have managed to get too different systems talking to each other through XML, WSDL and SOAP technologies. I gained more experience with operating systems administration; mainly Linux (SuSe and FreeBSD), also Oracle and MySql databases in administration, development and maintaining terms. Project management and conceptual project source planning is also part of my job. In Vaillant we are serving more than 13 Vaillant daughter companies all over Europe. all systems are based on php-MySql-Apache combination. Our websites are published under http://www.vaillant.com. Currently I am working on a project to manage the whole test results which obtained from LabView based testers from whole Europe. For this I am developing a SOAP-LabView based application. Also I am working directly in MySql optimization to handle a Terabytes data annually.");  

        $this->Line(20,117,180,117);

        $this->SetFont('Arial','',10);
        $this->Text($x,125,"2006");
        $this->Text($x+10,125,"- present"); 

        $this->SetFont('Arial','B',14);
        $this->Text($x,131,"Web Developer - Vignette"); 

        $this->SetFont('Arial','',10);
        $this->Text($x,137,"at ARGES Consulting Co.");  

        $this->SetFont('Arial','',9);
        $this->SetY(145);
        $this->SetX($x-1);
        $this->MultiCell(0,5,"I started getting specialized in programming Internet within this period. My main job was developing content management systems for some newspapers and portals. We used mainly Vignette - Storyserver (TCL/TK)with MSSQL and ORACLE DB. I also gained some experience of project management and documentations. Ref.: 
*) http://www.yenibir.com, [Career website],
*) http://www.hurriyetim.com.tr [Newspaper],
*) http://www.turkcell.com.tr [GSM Company]"); 

    //SECTION: Education
        $this->SetFont('Arial','B',16);
        $this->Text(10,190,"EDUCATIONS");
        $this->Line(10,195,200,195);
        
        $x=13;
        $this->SetFont('Arial','',10);
        $this->Text($x,205,"2017");
        $this->Text($x+10,205,"- 2018");  

        $this->SetFont('Arial','B',14);
        $this->Text($x,211,"Master's degree, Software Engineering"); 

        $this->SetFont('Arial','',10);
        $this->Text($x,217,"at KINGSTON University London");  

        $this->SetFont('Arial','',9);
        $this->SetY(220);
        $this->SetX($x-1);
        $this->MultiCell(0,5,"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.");        
    
        $this->Line(20,235,180,235);

        $this->SetFont('Arial','',10);
        $this->Text($x,245,"1995");
        $this->Text($x+10,245,"- 1999");  

        $this->SetFont('Arial','B',14);
        $this->Text($x,251,"Bachelor's degree, Computer Engineering"); 

        $this->SetFont('Arial','',10);
        $this->Text($x,257,"at Istanbul University");  

        $this->SetFont('Arial','',9);
        $this->SetY(263);
        $this->SetX($x-1);
        $this->MultiCell(0,5,"Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia."); 
    }
}

?>
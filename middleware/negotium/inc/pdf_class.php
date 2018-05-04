<?php
error_reporting(E_ALL);
ini_set('display_errors', 0);

class PDF extends FPDF
{

    function CreateCV($data)
    {

        $this->Image($data->photo,10,10,50,0,'JPEG');
        $this->SetFont('Arial','B',20);
        $this->Text(80,25,$data->name . " " . $data->surname);
        
        $this->SetFont('Arial','B',12);
        $this->Text(80,35,$data->title);
        
        $this->SetFont('Arial','',10);
        $this->Text(80,40,"at " . $data->employer);

        $this->SetFont('Arial','B',12);
        $this->Text(80,50,"Location:"); 
        $this->SetFont('Arial','',12);
        $this->Text(105,50,$data->location); 

        $this->SetFont('Arial','B',12);
        $this->Text(80,57,"Education:");
        $this->SetFont('Arial','',12);
        $this->Text(105,57,$data->degree);     

        $this->SetFont('Arial','B',12);
        $this->Text(80,63,"Experience:");
        $this->SetFont('Arial','',12);
        $this->Text(105,63,$data->experience);


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
        $this->Text(35,122,$data->mobile); 
    
        $this->SetFont('Arial','B',12);
        $this->Text(10,131,"Email:"); 
        $this->SetFont('Arial','',12);
        $this->Text(35,131,$data->email); 
    
        $this->SetFont('Arial','B',12);
        $this->Text(10,140,"Website:"); 
        $this->SetFont('Arial','',12);
        $this->Text(35,140,$data->website); 


    //SECTION: Personal Information
        $this->SetFont('Arial','B',16);
        $this->Text(10,165,"PERSONAL INFORMATION");
        $this->Line(10,170,200,170);

        $this->SetFont('Arial','B',12);
        $this->Text(10,179,"Birth date:"); 
        $this->SetFont('Arial','',12);
        $this->Text(65,179,$data->birthdate); 

        $this->SetFont('Arial','B',12);
        $this->Text(10,188,"Gender:"); 
        $this->SetFont('Arial','',12);
        $this->Text(65,188,$data->gender); 
    
        $this->SetFont('Arial','B',12);
        $this->Text(10,197,"Nationality:"); 
        $this->SetFont('Arial','',12);
        $this->Text(65,197,$data->nationality); 
    
        $this->SetFont('Arial','B',12);
        $this->Text(10,206,"Residence country:"); 
        $this->SetFont('Arial','',12);
        $this->Text(65,206,$data->residenceCountry); 
    
        $this->SetFont('Arial','B',12);
        $this->Text(10,215,"Marital Status:"); 
        $this->SetFont('Arial','',12);
        $this->Text(65,215,$data->maritalStatus); 

        $this->SetFont('Arial','B',12);
        $this->Text(10,224,"Number of dependencies:"); 
        $this->SetFont('Arial','',12);
        $this->Text(65,224,$data->numberOfDependencies);

        $this->SetFont('Arial','B',12);
        $this->Text(10,233,"Driving License/where?:"); 
        $this->SetFont('Arial','',12);
        $this->Text(65,233,"Yes. Jordan, Germany"); 

        $this->AddPage('P', '', 0);

    //SECTION: Experience
        $this->SetFont('Arial','B',16);
        $this->Text(10,20,"WORK EXPERIENCES (" . $data->experience . ")");
        $this->Line(10,25,200,25);
        
        $x=13;
        $this->SetFont('Arial','',10);
        $this->Text($x,35,$data->startDate);
        $this->Text($x+10,35,"- " . $data->endDate); 

        $this->SetFont('Arial','B',14);
        $this->Text($x,41,$data->workTitle); 

        $this->SetFont('Arial','',10);
        $this->Text($x,47,"at " . $data->employer);  

        $this->SetFont('Arial','',9);
        $this->SetY(55);
        $this->SetX($x-1);
        $this->MultiCell(0,5,$data->workDescription);  

    //SECTION: Education
        $this->SetFont('Arial','B',16);
        $this->Text(10,130,"EDUCATION");
        $this->Line(10,135,200,135);
        
        $x=13;
        $this->SetFont('Arial','',10);
        $this->Text($x,145,$data->completionDate);

        $this->SetFont('Arial','B',14);
        $this->Text($x,151,$data->degree); 

        $this->SetFont('Arial','',10);
        $this->Text($x,157,"at " . $data->institution);  

        $this->SetFont('Arial','',9);
        $this->SetY(165);
        $this->SetX($x-1);
        $this->MultiCell(0,5,$data->educationDescription);        
    

    //SECTION: Languages
        $this->SetFont('Arial','B',16);
        $this->Text(10,210,"LANGUAGES");
        $this->Line(10,215,200,215);

        $this->SetFont('Arial','',10);
        $this->Text($x,225,$data->language);

    //SECTION: References
        $this->SetFont('Arial','B',16);
        $this->Text(10,245,"REFERENCES");
        $this->Line(10,250,200,250);


        $this->SetFont('Arial','B',10);
        $this->Text(10,257,"Name:"); 
        $this->SetFont('Arial','',10);
        $this->Text(45,257,$data->referenceName);         

        $this->SetFont('Arial','B',10);
        $this->Text(10,263,"Job title:"); 
        $this->SetFont('Arial','',10);
        $this->Text(45,263, $data->referenceJobTitle);

        $this->SetFont('Arial','B',10);
        $this->Text(10,269,"Company name:"); 
        $this->SetFont('Arial','',10);
        $this->Text(45,269, $data->referenceCompanyName);

        $this->SetFont('Arial','B',10);
        $this->Text(10,275,"Phone:"); 
        $this->SetFont('Arial','',10);
        $this->Text(45,275, $data->referencePhone);

        $this->SetFont('Arial','B',10);
        $this->Text(10,281,"Email:"); 
        $this->SetFont('Arial','',10);
        $this->Text(45,281, $data->referenceEmail);        

    }
}

?>
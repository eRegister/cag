############################################
#
# Script to convert PowerPoint presentations
# to PDF files
# 
echo "DOCX/PPTX to PDF converter"

# Load Office interoperability assemblys
#
$asm = [System.Reflection.Assembly]::Load("Microsoft.Office.Interop.Word, Version=12.0.0.0, Culture=neutral, PublicKeyToken=71e9bce111e9429c")
$asm = [System.Reflection.Assembly]::Load("Microsoft.Office.Interop.PowerPoint, Version=12.0.0.0, Culture=neutral, PublicKeyToken=71e9bce111e9429c")

# Import enum types
#
$wdSaveFormat = "Microsoft.Office.Interop.Word.WdSaveFormat" -as [type]
$wdWindowState = "Microsoft.Office.Interop.Word.WdWindowState" -as [type]
$ppSaveAsFileType = "Microsoft.Office.Interop.PowerPoint.PpSaveAsFileType" -as [type]
$ppWindowState = "Microsoft.Office.Interop.PowerPoint.PpWindowState" -as [type]

# Create PowerPoint and Word application instances
#
$wd = new-object Microsoft.Office.Interop.Word.ApplicationClass
$wd.WindowState = $wdWindowState::wdWindowStateMinimize

$pp = new-object Microsoft.Office.Interop.PowerPoint.ApplicationClass
$pp.Visible = 1
$pp.WindowState = $ppWindowState::ppWindowMinimized

# Get list of DOCX and PPTX files
#
$files = dir ".." -recurse | where { ($_.Extension -eq ".docx" -or $_.Extension -eq ".pptx") -and !$_.Name.StartsWith("~")}

echo ("Found " + $files.Count + " file(s)");

# Open each file and save as PDF
#
foreach ($file in $files) {
	echo ("Processing: " + $file.Name);

	$pdf = [System.IO.Path]::ChangeExtension($file.FullName, ".pdf");

	if ($file.Extension -eq ".docx") {
		$document = $wd.Documents.Open($file.FullName);
        echo ("Word document loaded");
		$document.SaveAs($pdf, $wdSaveFormat::wdFormatPDF);
        echo ("Saved: " + $pdf);
		$document.Close();
	}
	else {
		$presentation = $pp.Presentations.Open($file.FullName);
		echo ("Powerpoint document loaded");
        $presentation.SaveAs($pdf, $ppSaveAsFileType::ppSaveAsPDF);
		echo ("Saved: " + $pdf);
        $presentation.Close();
	}
}

# Close application instances
#
$wd.Quit();
$pp.Quit();
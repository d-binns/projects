Attribute VB_Name = "Module1"
Sub SubmitForm()

If ThisWorkbook.Sheets("Service Selection").Range("I9") Then
    ThisWorkbook.Sheets("Project Initiation Form").Range("N301") = "Asset"
End If




'''''''''''''''''''''''''''''''''''''''''''''''SUBMITTING FORM'''''''''''''''''''''''''''''''''''''



Dim xSht As Worksheet
Dim xFileDlg As FileDialog
Dim xFolder As String
Dim xYesorNo As Integer
Dim xOutlookObj As Object
Dim xEmailObj As Object
Dim xUsedRng As Range
Dim xOption As Integer
Dim phase As Integer
Dim order As Range
 
Set xSht = ThisWorkbook.Sheets("Project Initiation Form")
Set xFileDlg = Application.FileDialog(msoFileDialogFolderPicker)
 


Dim rng As Range
Dim sht As Worksheet
Dim Lastrow As Long


ThisWorkbook.Sheets("Project Initiation Form").Range("E301").Value = Trim(ThisWorkbook.Sheets("Project Initiation Form").Range("E301"))
ThisWorkbook.Sheets("Project Initiation Form").Range("N301").Value = Trim(ThisWorkbook.Sheets("Project Initiation Form").Range("N301"))



If (ThisWorkbook.Sheets("Project Initiation Form").Range("K299") = False) Then

    MsgBox "Please select the Scope of Work before submitting" _
                    & vbCrLf & "Press OK to exit this macro."
    Exit Sub
    End If
ThisWorkbook.Sheets("Service Selection").Range("L2:L18").ClearContents
If ThisWorkbook.Sheets("Project Initiation Form").Range("K299").Value = "COVID-19" Then
    
    UserForm1.Report16.Value = True
    UserForm1.TextBox1.Value = "COVID-19"
'    UserForm1.Show
    UserForm1.CommandButton1.Value = True
Else

UserForm1.Show

End If

If (ThisWorkbook.Sheets("Service Selection").Range("L18") = "") Then
    ThisWorkbook.Sheets("Service Selection").Range("L2:L18").ClearContents
    End
End If

 ThisWorkbook.Sheets("Service Selection").Range("F36") = ThisWorkbook.Sheets("Project Initiation Form").Range("R6")









''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''SAVING FILE'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
'''''''''''''If IsEmpty(ThisWorkbook.Sheets("Project Initiation Form").Range("N73:R73")) Or IsEmpty(ThisWorkbook.Sheets("Project Initiation Form").Range("B82:R86")) _
'''''''''''''Then
'''''''''''''    xOption = MsgBox("Please make sure you have filled out the form before sending", vbDefaultButton1)
'''''''''''''
'''''''''''''
'''''''''''''End If

    Dim sampleArr() As Variant
    Dim I As Integer
    Dim rng2 As Range, cel As Range

    I = 1
    Set rng2 = ThisWorkbook.Sheets("Service Selection").Range("J20:J27") ' Note, this creates a 2 Dimensional array

    sampleArr = rng2 ' Right here, this sets the values in the range to this array.

    For I = LBound(sampleArr) To UBound(sampleArr)

        If sampleArr(I, 1) = "" Then

        Else
        ThisWorkbook.Sheets("Service Selection").Range("J28").Value = ThisWorkbook.Sheets("Service Selection").Range("J28").Value + sampleArr(I, 1)
        ThisWorkbook.Sheets("Service Selection").Range("J28").Value = ThisWorkbook.Sheets("Service Selection").Range("J28").Value + ", "
        End If
        Next I

    If ThisWorkbook.Sheets("Project Initiation Form").Range("N326").Value = "" Then

    Else
        ThisWorkbook.Sheets("Service Selection").Range("J28").Value = ThisWorkbook.Sheets("Service Selection").Range("J28").Value + ThisWorkbook.Sheets("Project Initiation Form").Range("N326").Value + ", "
    End If





    If ThisWorkbook.Sheets("Service Selection").Range("J28").Value = "" Then

    Else
        ThisWorkbook.Sheets("Service Selection").Range("J28").Value = Left(ThisWorkbook.Sheets("Service Selection").Range("J28").Value, Len(ThisWorkbook.Sheets("Service Selection").Range("J28").Value) - 2)
    End If


'xFolder = "C:\Company\Client Team - Documents\form Reference List\Completed forms\" + ThisWorkbook.Sheets("Project Initiation Form").range("N11") + " - " + CStr(ThisWorkbook.Sheets("Project Initiation Form").range("O6").Value) + " - " + CStr(ThisWorkbook.Sheets("Project Initiation Form").range("R6").Value)
xFolder = "C:\Users\" + Environ("username") + "\Company\Client Team - Documents\form Reference List\Completed forms\" + ThisWorkbook.Sheets("Project Initiation Form").Range("N11") + " - " + CStr(ThisWorkbook.Sheets("Project Initiation Form").Range("O6").Value) + " - " + CStr(ThisWorkbook.Sheets("Project Initiation Form").Range("R6").Value) + ".pdf"

'xFolder = "C:\Client Projects\form Reference List\Completed forms\" + ThisWorkbook.Sheets("Project Initiation Form").range("N11") + " - " + CStr(ThisWorkbook.Sheets("Project Initiation Form").range("O6").Value) + " - " + CStr(ThisWorkbook.Sheets("Project Initiation Form").range("R6").Value)
Dim projectfolderlink As String
Dim filelink As String
filelink = "https://companyname.sharepoint.com/sites/Client/Shared Documents/form Reference List/Completed forms/" + ThisWorkbook.Sheets("Project Initiation Form").Range("N11") + " - " + CStr(ThisWorkbook.Sheets("Project Initiation Form").Range("O6").Value) + " - " + CStr(ThisWorkbook.Sheets("Project Initiation Form").Range("R6").Value) + ".pdf"
projectfolderlink = "https://companyname.sharepoint.com/sites/Client/Shared Documents/Client Projects/" + ThisWorkbook.Sheets("Project Initiation Form").Range("N11") + "/" + ThisWorkbook.Sheets("Project Initiation Form").Range("E301")


'''''''''''''
'''''''''''''
''''''''''''''
''Check if file already exist
If Len(Dir(xFolder)) > 0 Then
    xYesorNo = MsgBox(xFolder & " already exists." & vbCrLf & vbCrLf & "Do you want to overwrite it?", _
                      vbYesNo + vbQuestion, "File Exists")
    On Error Resume Next
    If xYesorNo = vbYes Then
        Kill xFolder
    Else
        MsgBox "if you don't overwrite the existing PDF, I can't continue." _
                    & vbCrLf & vbCrLf & "Press OK to exit this macro.", vbCritical, "Exiting Macro"
        Exit Sub
    End If
    If Err.Number <> 0 Then
        MsgBox "Unable to delete existing file.  Please make sure the file is not open or write protected." _
                    & vbCrLf & vbCrLf & "Press OK to exit this macro.", vbCritical, "Unable to Delete File"
        Exit Sub
    End If
End If
'
'Set xUsedRng = xSht.UsedRange
'If Application.WorksheetFunction.CountA(xUsedRng.Cells) <> 0 Then
    'Save as PDF file
'    xSht.ExportAsFixedFormat Type:=xlTypePDF, Filename:=xFolder, Quality:=xlQualityStandard


'Save Active Sheet(s) as PDF
ThisWorkbook.Sheets("Project Initiation Form").ExportAsFixedFormat Type:=xlTypePDF, _
    Filename:=xFolder






''''''''''''''''''''''''''''''''''CREATE COPY FOR EDITING IF NEEDED'''''''''''''''''''''''''''''''''''
  Dim wb As Workbook
  ThisWorkbook.Sheets(Array("Project Initiation Form", "School List", "Price Sheet", "Project Info Sheet")).Copy
  Set wb = ActiveWorkbook
  With wb
    Application.DisplayAlerts = False
    .SaveAs Filename:="C:\Users\" + Environ("username") + "\Company\Client Team - Documents\form Reference List\Revisions\" + ThisWorkbook.Sheets("Project Initiation Form").Range("N11") + " - " + CStr(ThisWorkbook.Sheets("Project Initiation Form").Range("O6").Value) + " - " + CStr(ThisWorkbook.Sheets("Project Initiation Form").Range("R6").Value)

    '.SaveAs Filename:="Company\Client Team - Documents\form Reference List\Completed forms\Revisions\" + ThisWorkbook.Sheets("Project Initiation Form").range("N11") + " - " + CStr(ThisWorkbook.Sheets("Project Initiation Form").range("O6").Value) + " - " + CStr(ThisWorkbook.Sheets("Project Initiation Form").range("R6").Value)
    Application.DisplayAlerts = True
    'Dim I As Long
    Dim xOLE As Object
    On Error Resume Next
    wb.Sheets("Project Initiation Form").Unprotect "protect"
    wb.Sheets("Project Initiation Form").Buttons.Delete
    wb.Sheets("Project Initiation Form").Range("B8") = "form BUDGET REVISION"
    For Each xOLE In wb.OLEObjects
        If TypeName(xOLE.Object) = "CommandButton" Then
            xOLE.Delete
        End If
    Next
    
    
 Dim astrLinks2 As Variant
 
 ' Define variable as an Excel link type.
 astrLinks2 = ActiveWorkbook.LinkSources(Type:=xlLinkTypeExcelLinks)
 
 ' Break the first link in the active workbook.
 ActiveWorkbook.BreakLink _
 Name:=astrLinks2(1), _
 Type:=xlLinkTypeExcelLinks
    
    
    
    .Save
    .Close
  End With








''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''NEW ROW GENERATION'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''

 ThisWorkbook.Sheets("Service Selection").Range("F36") = ThisWorkbook.Sheets("Project Initiation Form").Range("R6")
'Clear filter data

ThisWorkbook.Sheets("Project Info Sheet").ListObjects(1).AutoFilter.ShowAllData



' Sorts the table before adding new column


'Sort Data Adding New Row
Sheets("Project Info Sheet").Range("N1").End(xlDown).Sort Key1:=Sheets("Project Info Sheet").Range("N1"), Order1:=xlAscending, Header:=xlYes


'Generate New Row
Set sht = ThisWorkbook.Sheets("Project Info Sheet")   ' or Thisworkbook.Sheets(1)
Lastrow = sht.Cells(sht.Rows.Count, "A").End(xlUp).Row   'Finds lastrow for column A,update to whichever one you need

Set rng = ThisWorkbook.Sheets("Project Info Sheet").Range("P2:P" & Lastrow)       'Updating column O with formula, change to what you need
rng.Formula = "=COUNTIF('Project Info Sheet'!Q$2:Q2,'Project Info Sheet'!Q2)"          'Simple Formula, could be anything


Set rng = ThisWorkbook.Sheets("Project Info Sheet").Range("N2:N" & Lastrow)       'Updating column C with formula, change to what you need
rng.Formula = "=COUNTA('Project Info Sheet'!A$2:A2)"          'Simple Formula, could be anything




''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''' RECORD DATA INTO SPREADSHEET'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
'To Copy School Name
Sheets("Project Initiation Form").Range("N11").Copy
Sheets("Project Info Sheet").Range("A" & Rows.Count).End(xlUp).Offset(1, 0).PasteSpecial xlPasteValues

'To Copy School Number
Sheets("Project Initiation Form").Range("O6").Copy
Sheets("Project Info Sheet").Range("B" & Rows.Count).End(xlUp).PasteSpecial xlPasteValues

Sheets("Project Initiation Form").Range("O6").Copy
Sheets("Project Info Sheet").Range("Q" & Rows.Count).End(xlUp).PasteSpecial xlPasteValues

'To Copy School Address
Sheets("Project Initiation Form").Range("N300").Copy
Sheets("Project Info Sheet").Range("L" & Rows.Count).End(xlUp).PasteSpecial xlPasteValues

'    'To Copy Condition
'    Sheets("Project Initiation Form").Range("E310").Copy
'    Sheets("Project Info Sheet").Range("D" & Rows.Count).End(xlUp).PasteSpecial xlPasteValues '' FIX

'To Copy Request Source
Sheets("Project Initiation Form").Range("E311").Copy
Sheets("Project Info Sheet").Range("J" & Rows.Count).End(xlUp).PasteSpecial xlPasteValues


'To Copy Pipeline of Job
Sheets("Project Initiation Form").Range("K299").Copy
Sheets("Project Info Sheet").Range("G" & Rows.Count).End(xlUp).PasteSpecial xlPasteValues

'To Copy Type of Job
Sheets("project Initiation Form").Range("E301").Copy
Sheets("Project Info Sheet").Range("U" & Rows.Count).End(xlUp).PasteSpecial xlPasteValues

'CIP Project
Sheets("Project Initiation Form").Range("N301").Copy
Sheets("Project Info Sheet").Range("V" & Rows.Count).End(xlUp).PasteSpecial xlPasteValues


'To Copy Project Type
If ThisWorkbook.Sheets("Service Selection").Range("I10") = True Then
    ThisWorkbook.Sheets("Service Selection").Range("I34").Copy
Else
    Sheets("Project Initiation Form").Range("K299").Copy
End If
Sheets("Project Info Sheet").Range("E" & Rows.Count).End(xlUp).PasteSpecial xlPasteValues


'To Copy Description
Sheets("Project Initiation Form").Range("B317").Copy
Sheets("Project Info Sheet").Range("F" & Rows.Count).End(xlUp).PasteSpecial xlPasteValues

'To Copy Region
Sheets("Project Initiation Form").Range("I299").Copy
Sheets("Project Info Sheet").Range("H" & Rows.Count).End(xlUp).PasteSpecial xlPasteValues

'To Copy Facility Number
Sheets("Project Initiation Form").Range("H299").Copy
Sheets("Project Info Sheet").Range("I" & Rows.Count).End(xlUp).PasteSpecial xlPasteValues

'To Copy Reports Needed
Sheets("Service Selection").Range("J28").Copy
Sheets("Project Info Sheet").Range("D" & Rows.Count).End(xlUp).PasteSpecial xlPasteValues

'To Copy Date
Sheets("Project Initiation Form").Range("E300").Copy
Sheets("Project Info Sheet").Range("K" & Rows.Count).End(xlUp).PasteSpecial xlPasteValues

'Estimated Project Total
Sheets("Project Initiation Form").Range("P366").Copy
Sheets("Project Info Sheet").Range("M" & Rows.Count).End(xlUp).PasteSpecial xlPasteValues

'Sheets("Project Initiation Form").Range("N73").Copy Sheets("Project Info Sheet").Range("A" & Rows.Count).End(xlUp).Offset(1, 0)
'Sheets("Project Initiation Form").Range("O6").Copy Destination:=Sheets("Project Info Sheet").Range("B2: B" & Lastrow)

'To Copy Reports Needed
Sheets("Service Selection").Range("L18").Copy
Sheets("Project Info Sheet").Range("O" & Rows.Count).End(xlUp).PasteSpecial xlPasteValues


'To Copy Phase Number for Consistency
Sheets("Service Selection").Range("f36").Copy
Sheets("Project Info Sheet").Range("C" & Rows.Count).End(xlUp).PasteSpecial xlPasteValues


'To Copy Phase Number for Consistency
Sheets("Service Selection").Range("J32").Copy
Sheets("Project Info Sheet").Range("N" & Rows.Count).End(xlUp).PasteSpecial xlPasteValues

'To Remove the Filters On Project Info Sheet


'To Copy Phase Number for Consistency
'xFolder.Value.Copy
'Sheets("Project Info Sheet").Range("R" & Rows.Count).End(xlUp).Value = xFolder


'To Create Hyperlink to PDF
'Sheets("Project Info Sheet").Range("R" & Rows.Count).End(xlUp).Value = xFolder
   ThisWorkbook.Sheets("Project Info Sheet").Range("R" & Rows.Count).End(xlUp).Hyperlinks.Add Anchor:=ThisWorkbook.Sheets("Project Info Sheet").Range("R" & Rows.Count).End(xlUp), Address:= _
  filelink, TextToDisplay:=filelink

'''''projectfolderlink = "https://companyname.sharepoint.com/sites/Client/Shared Documents/Client Projects/" + ThisWorkbook.Sheets("Project Initiation Form").Range("N11") + "/" + ThisWorkbook.Sheets("Project Initiation Form").Range("e301")


'To Create Hyperlink to Folder
'Sheets("Project Info Sheet").Range("W" & Rows.Count).End(xlUp).Value = xFolder
   ThisWorkbook.Sheets("Project Info Sheet").Range("W" & Rows.Count).End(xlUp).Hyperlinks.Add Anchor:=ThisWorkbook.Sheets("Project Info Sheet").Range("W" & Rows.Count).End(xlUp), Address:= _
  projectfolderlink, TextToDisplay:=projectfolderlink



''''''' SharePoint Full Project Name

ThisWorkbook.Sheets("Service Selection").Range("F45").Copy
ThisWorkbook.Sheets("Project Info Sheet").Range("T" & Rows.Count).End(xlUp).PasteSpecial xlPasteValues


'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''Copy Into Pipeline Sheet'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''




'To Copy Pipeline
Sheets("Service Selection").Range("F39").Copy
Sheets("Service Selection").Range("G39").PasteSpecial xlPasteValues




'Clear filter data

ThisWorkbook.Sheets(Sheets("Service Selection").Range("G39").Value).ListObjects(1).AutoFilter.ShowAllData



' Sorts the table before adding new column


'Sort Data Adding New Row
Sheets(Sheets("Service Selection").Range("G39").Value).Range("M1").End(xlDown).Sort Key1:=Sheets(Sheets("Service Selection").Range("G39").Value).Range("N1"), Order1:=xlAscending, Header:=xlYes


'Generate New Row
Set sht = ThisWorkbook.Sheets(Sheets("Service Selection").Range("G39").Value)   ' or Thisworkbook.Sheets(1)
Lastrow = sht.Cells(sht.Rows.Count, "A").End(xlUp).Row   'Finds lastrow for column A,update to whichever one you need

Set rng = ThisWorkbook.Sheets(Sheets("Service Selection").Range("G39").Value).Range("P2:P" & Lastrow)       'Updating column O with formula, change to what you need
rng.Formula = "=COUNTIF('Project Info Sheet'!P2:P2,'Project Info Sheet'!P2)"          'Simple Formula, could be anything


Set rng = ThisWorkbook.Sheets(Sheets("Service Selection").Range("G39").Value).Range("N2:N" & Lastrow)       'Updating column C with formula, change to what you need
rng.Formula = "=COUNTA('Project Info Sheet'!A$2:A2)"          'Simple Formula, could be anything




Set rng = ThisWorkbook.Sheets("Project Info Sheet").Range("p2:p" & Lastrow)       'Updating column O with formula, change to what you need
rng.Formula = "=COUNTIF('Project Info Sheet'!q$2:q2,'Project Info Sheet'!q2)"          'Simple Formula, could be anything





'To Copy School Name
Sheets("Project Initiation Form").Range("N11").Copy
Sheets(Sheets("Service Selection").Range("G39").Value).Range("A" & Rows.Count).End(xlUp).Offset(1, 0).PasteSpecial xlPasteValues

'To Copy School Number
Sheets("Project Initiation Form").Range("O6").Copy
Sheets(Sheets("Service Selection").Range("G39").Value).Range("B" & Rows.Count).End(xlUp).PasteSpecial xlPasteValues

Sheets("Project Initiation Form").Range("O6").Copy
Sheets(Sheets("Service Selection").Range("G39").Value).Range("Q" & Rows.Count).End(xlUp).PasteSpecial xlPasteValues

'To Copy School Address
Sheets("Project Initiation Form").Range("N300").Copy
Sheets(Sheets("Service Selection").Range("G39").Value).Range("L" & Rows.Count).End(xlUp).PasteSpecial xlPasteValues

'    'To Copy Condition
'    Sheets("Project Initiation Form").Range("E310").Copy
'    Sheets(Sheets("Service Selection").Range("G39").value).Range("D" & Rows.Count).End(xlUp).PasteSpecial xlPasteValues '' FIX

'To Copy Request Source
Sheets("Project Initiation Form").Range("E311").Copy
Sheets(Sheets("Service Selection").Range("G39").Value).Range("J" & Rows.Count).End(xlUp).PasteSpecial xlPasteValues

'To Copy Type of Job
Sheets("project Initiation Form").Range("E301").Copy
Sheets(Sheets("Service Selection").Range("G39").Value).Range("U" & Rows.Count).End(xlUp).PasteSpecial xlPasteValues

'To Copy CIP Project
Sheets("Project Initiation Form").Range("N301").Copy
Sheets(Sheets("Service Selection").Range("G39").Value).Range("V" & Rows.Count).End(xlUp).PasteSpecial xlPasteValues




'To Copy Pipeline of Job
Sheets("Project Initiation Form").Range("K299").Copy
Sheets(Sheets("Service Selection").Range("G39").Value).Range("G" & Rows.Count).End(xlUp).PasteSpecial xlPasteValues


'To Copy Project Type
If ThisWorkbook.Sheets("Service Selection").Range("I10") = True Then
    ThisWorkbook.Sheets("Service Selection").Range("I34").Copy
Else
    Sheets("Project Initiation Form").Range("K299").Copy
End If
Sheets(Sheets("Service Selection").Range("G39").Value).Range("E" & Rows.Count).End(xlUp).PasteSpecial xlPasteValues


'To Copy Description
Sheets("Project Initiation Form").Range("B317").Copy
Sheets(Sheets("Service Selection").Range("G39").Value).Range("F" & Rows.Count).End(xlUp).PasteSpecial xlPasteValues

'To Copy Region
Sheets("Project Initiation Form").Range("I299").Copy
Sheets(Sheets("Service Selection").Range("G39").Value).Range("H" & Rows.Count).End(xlUp).PasteSpecial xlPasteValues

'To Copy Facility Number
Sheets("Project Initiation Form").Range("H299").Copy
Sheets(Sheets("Service Selection").Range("G39").Value).Range("I" & Rows.Count).End(xlUp).PasteSpecial xlPasteValues

'To Copy Reports Needed
Sheets("Service Selection").Range("J28").Copy
Sheets(Sheets("Service Selection").Range("G39").Value).Range("D" & Rows.Count).End(xlUp).PasteSpecial xlPasteValues

'To Copy Date
Sheets("Project Initiation Form").Range("E300").Copy
Sheets(Sheets("Service Selection").Range("G39").Value).Range("K" & Rows.Count).End(xlUp).PasteSpecial xlPasteValues

'Estimated Project Total
Sheets("Project Initiation Form").Range("P366").Copy
Sheets(Sheets("Service Selection").Range("G39").Value).Range("M" & Rows.Count).End(xlUp).PasteSpecial xlPasteValues

'Sheets("Project Initiation Form").Range("N73").Copy Sheets(Sheets("Service Selection").Range("G39").value).Range("A" & Rows.Count).End(xlUp).Offset(1, 0)
'Sheets("Project Initiation Form").Range("O6").Copy Destination:=Sheets(Sheets("Service Selection").Range("G39").value).Range("B2: B" & Lastrow)

'To Copy Reports Needed
Sheets("Service Selection").Range("L18").Copy
Sheets(Sheets("Service Selection").Range("G39").Value).Range("O" & Rows.Count).End(xlUp).PasteSpecial xlPasteValues


'To Copy Phase Number for Consistency
Sheets("Service Selection").Range("f36").Copy
Sheets(Sheets("Service Selection").Range("G39").Value).Range("C" & Rows.Count).End(xlUp).PasteSpecial xlPasteValues


'To Copy Phase Number for Consistency
Sheets("Service Selection").Range("J32").Copy
Sheets(Sheets("Service Selection").Range("G39").Value).Range("N" & Rows.Count).End(xlUp).PasteSpecial xlPasteValues



'To Copy Phase Number for Consistency
'xFolder.Value.Copy
Sheets(Sheets("Service Selection").Range("G39").Value).Range("R" & Rows.Count).End(xlUp).Value = xFolder


'To Create Hyperlink to PDF
'Sheets("Project Info Sheet").Range("R" & Rows.Count).End(xlUp).Value = xFolder
   Sheets(Sheets("Service Selection").Range("G39").Value).Range("R" & Rows.Count).End(xlUp).Hyperlinks.Add Anchor:=Sheets(Sheets("Service Selection").Range("G39").Value).Range("R" & Rows.Count).End(xlUp), Address:= _
filelink, TextToDisplay:=filelink  ' WorksheetFunction.Substitute("https://companyname.sharepoint.com/sites/Client/Shared Documents/form Reference List/Completed forms/" + ThisWorkbook.Sheets("Project Initiation Form").range("N11") + " - " + CStr(ThisWorkbook.Sheets("Project Initiation Form").range("O6").Value) + " - " + CStr(ThisWorkbook.Sheets("Project Initiation Form").range("R6").Value) + ".pdf", " ", "%20") ', TextToDisplay:=xFolder

    
  'To Create Hyperlink to Folder
'Sheets("Project Info Sheet").Range("R" & Rows.Count).End(xlUp).Value = xFolder
   Sheets(Sheets("Service Selection").Range("G39").Value).Range("W" & Rows.Count).End(xlUp).Hyperlinks.Add Anchor:=Sheets(Sheets("Service Selection").Range("G39").Value).Range("W" & Rows.Count).End(xlUp), Address:= _
projectfolderlink, TextToDisplay:=projectfolderlink ' WorksheetFunction.Substitute("https://companyname.sharepoint.com/sites/Client/Shared Documents/form Reference List/Completed forms/" + ThisWorkbook.Sheets("Project Initiation Form").range("N11") + " - " + CStr(ThisWorkbook.Sheets("Project Initiation Form").range("O6").Value) + " - " + CStr(ThisWorkbook.Sheets("Project Initiation Form").range("R6").Value) + ".pdf", " ", "%20") ', TextToDisplay:=xFolder
  
    
    
'xFolder.Path


'SharePoint Full Project Name
ThisWorkbook.Sheets("Service Selection").Range("F45").Copy
ThisWorkbook.Sheets(Sheets("Service Selection").Range("G39").Value).Range("T" & Rows.Count).End(xlUp).PasteSpecial xlPasteValues


''
''








''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''Send Email'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
If ThisWorkbook.Sheets("Service Selection").Range("I10") And (ThisWorkbook.Sheets("Service Selection").Range("I11").Value = False) Then  '''''''''''''''''' Capital Selected'''''''''''''''''''''''''''''''''''''''''

    'Create Outlook email
    Set xOutlookObj = CreateObject("Outlook.Application")
    Set xEmailObj = xOutlookObj.CreateItem(0)
    With xEmailObj
        .Display
        .To = "kchallberg@Client.edu; rmchristlieb1@Client.edu;"
        .CC = "dmccormick@companyname.com; dbinns@companyname.com; rjschleyer@Client.edu"
        .subject = ThisWorkbook.Sheets("Project Initiation Form").Range("N11") + " " + ThisWorkbook.Sheets("Project Initiation Form").Range("N301") + " form " + Format(Now(), "MM.DD.YYYY")
        .Attachments.Add (xFolder) ' + ".pdf"
        .Body = "Please see the attached funding request for " & ThisWorkbook.Sheets("Project Initiation Form").Range("N11") & "." & vbNewLine & vbNewLine & "Thank you," & vbNewLine
        If DisplayEmail = False Then
            '.Send
        End If
    End With

    Set xOutlookObj = CreateObject("Outlook.Application")
    Set xEmailObj = xOutlookObj.CreateItem(0)
    With xEmailObj
        .Display
        .To = "accounting@company.com"
        .CC = "admin@company.com"
        .subject = "New Project for Ajera" + " - " + ThisWorkbook.Sheets("Project Initiation Form").Range("N11")
        .Attachments.Add xFolder '+ ".pdf"
        .Body = "Please see attached form to open project number for " & ThisWorkbook.Sheets("Project Initiation Form").Range("N11") & "." & vbNewLine & vbNewLine & "Thank you," & vbNewLine
        If DisplayEmail = False Then
            '.Send
        End If
    End With
End If

If ThisWorkbook.Sheets("Service Selection").Range("I9") And (ThisWorkbook.Sheets("Service Selection").Range("I11").Value = False) Then '''''''''''''''Asset Selected''''''''''''''''''''''''''''''''''''''''''

    'Create Outlook email
    Set xOutlookObj = CreateObject("Outlook.Application")
    Set xEmailObj = xOutlookObj.CreateItem(0)
    With xEmailObj
        .Display
        .To = " clientname@Client.edu"
        .CC = ""
        .subject = ThisWorkbook.Sheets("Project Initiation Form").Range("N11") + " " + ThisWorkbook.Sheets("Project Initiation Form").Range("N301") + " form " + Format(Now(), "MM.DD.YYYY")
        .Attachments.Add xFolder '+ ".pdf"
        .Body = "Please see the attached funding request for " & ThisWorkbook.Sheets("Project Initiation Form").Range("N11") & "." & vbNewLine & vbNewLine & "Thank you," & vbNewLine
        If DisplayEmail = False Then
            '.Send
        End If
    End With

    Set xOutlookObj = CreateObject("Outlook.Application")
    Set xEmailObj = xOutlookObj.CreateItem(0)
    With xEmailObj
        .Display
        .To = "accounting@company.com"
        .CC = "admin@company.com"
        .subject = "New Project for Ajera" + " - " + ThisWorkbook.Sheets("Project Initiation Form").Range("N11")
        .Attachments.Add xFolder ' + ".pdf"
        .Body = "Please see attached form to open project number for " & ThisWorkbook.Sheets("Project Initiation Form").Range("N11") & "." & vbNewLine & vbNewLine & "Thank you," & vbNewLine
        If DisplayEmail = False Then
            '.Send
        End If
    End With
End If




If ThisWorkbook.Sheets("Service Selection").Range("I11") Then   '''''''''COVID '''''''''''''''


    'Create Outlook email
    Set xOutlookObj = CreateObject("Outlook.Application")
    Set xEmailObj = xOutlookObj.CreateItem(0)
    With xEmailObj
        .Display
        .To = "client@client.com"
        .CC = ""
        .subject = ThisWorkbook.Sheets("Project Initiation Form").Range("N11") + " " + ThisWorkbook.Sheets("Project Initiation Form").Range("N301") + " COVID-19" + " form " + Format(Now(), "MM.DD.YYYY")
        .Attachments.Add xFolder '+ ".pdf"
        .Body = "Please see the attached funding request for " & ThisWorkbook.Sheets("Project Initiation Form").Range("N11") & "." & vbNewLine & vbNewLine & "Thank you," & vbNewLine
        If DisplayEmail = False Then
            '.Send
        End If
    End With

    Set xOutlookObj = CreateObject("Outlook.Application")
    Set xEmailObj = xOutlookObj.CreateItem(0)
    With xEmailObj
        .Display
        .To = "accounting@company.com"
        .CC = "admin@commpany.com"
        .subject = "New Project for Ajera" + " - " + ThisWorkbook.Sheets("Project Initiation Form").Range("N11")
        .Attachments.Add xFolder '+ ".pdf"
        .Body = "Please see attached form to open project number for " & ThisWorkbook.Sheets("Project Initiation Form").Range("N11") & "." & vbNewLine & vbNewLine & "Thank you," & vbNewLine
        If DisplayEmail = False Then
            '.Send
        End If
    End With
End If










 ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''Clear Data For Next Use''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
ThisWorkbook.Sheets("Project Initiation Form").Range("N301:R301").ClearContents
ThisWorkbook.Sheets("Project Initiation Form").Range("N11:R11").ClearContents

ThisWorkbook.Sheets("Project Initiation Form").Range("N310:R310").ClearContents
ThisWorkbook.Sheets("Project Initiation Form").Range("N326:R326").ClearContents
ThisWorkbook.Sheets("Project Initiation Form").Range("E301:J301").ClearContents
ThisWorkbook.Sheets("Project Initiation Form").Range("E310:J310").ClearContents
ThisWorkbook.Sheets("Project Initiation Form").Range("E311:J311").ClearContents


ThisWorkbook.Sheets("Project Initiation Form").Range("B317:R321").ClearContents

'''''''''Cost Sheet group
''ThisWorkbook.Sheets("Project Initiation Form").Range("B348:J359").ClearContents
''ThisWorkbook.Sheets("Project Initiation Form").Range("C360:J365").ClearContents
''ThisWorkbook.Sheets("Project Initiation Form").Range("K360:L365").ClearContents
''ThisWorkbook.Sheets("Project Initiation Form").Range("M348:O365").ClearContents

ThisWorkbook.Sheets("Service Selection").Range("I3:I5") = False

ThisWorkbook.Sheets("Service Selection").Range("f36").ClearContents
ThisWorkbook.Sheets("Service Selection").Range("L2:L18").ClearContents
'''''''''''''''''''''''''''''''''''''''Clear Reports'''''''''''''''''''''''''''''''''
'''''''''''Service Selection group
ThisWorkbook.Sheets("Service Selection").Range("F20") = False
ThisWorkbook.Sheets("Service Selection").Range("F21") = False
ThisWorkbook.Sheets("Service Selection").Range("F22") = False
ThisWorkbook.Sheets("Service Selection").Range("F23") = False
ThisWorkbook.Sheets("Service Selection").Range("F24") = False
ThisWorkbook.Sheets("Service Selection").Range("F25") = False
ThisWorkbook.Sheets("Service Selection").Range("F26") = False
ThisWorkbook.Sheets("Service Selection").Range("F27") = False
ThisWorkbook.Sheets("Service Selection").Range("F28") = False

ThisWorkbook.Sheets("Service Selection").Range("J28") = ""

'''''''''''''''''''''''''''''''''''''''''''''''Clear Pipeline'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
ThisWorkbook.Sheets("Service Selection").Range("I9") = False
ThisWorkbook.Sheets("Service Selection").Range("I10") = False
ThisWorkbook.Sheets("Service Selection").Range("I11") = False

ThisWorkbook.Sheets("Service Selection").Range("F31") = False
ThisWorkbook.Sheets("Service Selection").Range("F32") = False

ThisWorkbook.Save




'Else
'  MsgBox "The active worksheet cannot be blank"
'  Exit Sub
'End If
End Sub





-¨µxÇ>]’'>°ø˘ˆJˆ¶˛ç~]π—réZ÷h¶3BáΩ)>çYûﬂ)mËú>Èª˝–˝¶Ç·◊ùEÌõªp€ç ±nh.,ë#≤GG‚'?°œ‡ÑØ•_Ô˛czÎ¥– õˆíF°"ÓyÌËZ"%òêG}πˆŸçS}ßkÙ¸ß¥$Tw⁄aØizF7ïTO0\S˛π%·Q&∞√;¡?@√Ê◊”L Ñﬂ˘Ó…å¯Æ «ãÈ@
|nñ∆XØhÂg{≥	™˘ôÏc≈Ü/	vAl«‚oh˚àñµÑ)G4¸i9Ñ ì¿ì≥bú∫y˝ÍrªÅvuÚU°·—,∑jrLœ€_.Í¡Wµ©{ÆË/Æ™àO[ 8 4 Àq‹∂ËÓá=ºÆ ¨Å=ªB√æ≈πeî˚ùJﬁys™bÁ¡;Æßùn)~[“ŒIkj?ï©-‘•D[/Jõ!Ö‡4ÄcY`I69Iãä#:ëÎ¬J¸bÁÖ>Êí£b=péÚ≥ÉìHoQ1Û1„É˝`°∞»
òètÏ˙√Ÿ{⁄Æ©äΩõ9Yy0mBàg†
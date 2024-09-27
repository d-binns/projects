Attribute VB_Name = "Module1"
Sub Submit_Expense_Report()
'''''' Updated 7/1/2024

Dim xSht As Worksheet
Dim xFile As String
Dim xYesorNo As Integer
Dim xOutlookObj As Object
Dim xEmailObj As Object
Dim xUsedRng As Range
Dim xFolder As Range
  
 
Set xSht = ThisWorkbook.Sheets("Expense Report")
Set xSht2 = ThisWorkbook.Sheets("ER Page 2")
Set xFileDlg = Application.FileDialog(msoFileDialogFilePicker)
Dim xname As String
Dim cc As String
Dim firstname As String
 

Dim rng As Range
Dim sht As Worksheet
If ThisWorkbook.Sheets("Expense Report").Range("D4") = "" Then
    MsgBox ("Please fill out the date on the Expense Report Page before submitting")
    End
End If
    
If ((ThisWorkbook.Sheets("ER Page 2").Range("H28") > 0) And (ThisWorkbook.Sheets("ER Page 2").Range("D4") = "")) Then
    MsgBox ("Please fill out the date on ER Page 2 before submitting")
    End
End If

If ThisWorkbook.Sheets("Expense Report").Range("J28") < 1 Then
    MsgBox ("Please fill out the Expense Report Form before submitting")
    End
End If


UserForm1.Show
If ThisWorkbook.Sheets("Supervisor List").Range("F2") = "" Then
    MsgBox ("Please enter a supervisor in order to submit your expense report.")
End
End If

With ThisWorkbook.Sheets("Receipt List").ListObjects("ReceiptList")
        If Not .DataBodyRange Is Nothing Then
            .DataBodyRange.Delete
        End If
    End With
    
With ThisWorkbook.Sheets("Receipt List").ListObjects("PDFList")
        If Not .DataBodyRange Is Nothing Then
            .DataBodyRange.Delete
        End If
    End With
ThisWorkbook.Sheets("Receipt List").Visible = False
 Application.DisplayAlerts = False
     ThisWorkbook.Sheets("Pics").Delete
     ThisWorkbook.Sheets.Add(After:=ThisWorkbook.Sheets(ThisWorkbook.Sheets.count)).Name = "Pics"
     ThisWorkbook.Sheets("Pics").Visible = False
  Application.DisplayAlerts = True
ThisWorkbook.Sheets("Expense Report").Select


ThisWorkbook.Sheets("Receipt List").Visible = True
Userform3.Show



    'val = UserForm1.TextBox1.Value
    'ThisWorkbook.Sheets("Supervisor List").Range("F2").Value = val
    xname = ThisWorkbook.Sheets("Supervisor List").Range("G2")
    cc = ThisWorkbook.Sheets("Supervisor List").Range("H2")
    'xname = Application.WorksheetFunction.VLookup("UserForm1.TextBox1.value", ThisWorkbook.Sheets("Supervisor List").Range("A2:C7"), 2)
    firstname = ThisWorkbook.Sheets("Supervisor List").Range("E2").Value

    Dim username As String
    username = ThisWorkbook.Sheets("Expense Report").Range("d3").Value




If ThisWorkbook.Sheets("ER Page 2").Range("H28") > 0 Then ''''' If Use Two Pages

If (ThisWorkbook.Sheets("Receipt List").Range("C1").Value > 1) Then
    ThisWorkbook.Sheets(Array("Expense Report", "ER Page 2", "Pics")).Select
Else
    ThisWorkbook.Sheets(Array("Expense Report", "ER Page 2")).Select
End If

    'Save Active Sheet(s) as PDF
    ActiveSheet.ExportAsFixedFormat Type:=xlTypePDF, _
        Filename:="C:\Users\" + Environ("username") + "\Documents\" + username + " Expense Report " + Format(xSht.Range("D4"), "MM.DD.YY") + " - " + Format(xSht.Range("D5"), "MM.DD.YY") + _
        " & " + Format(xSht2.Range("D4"), "MM.DD.YY") + " - " + Format(xSht2.Range("D5"), "MM.DD.YY") + ".pdf", Quality:=xlQualityStandard, IncludeDocProperties:=True, _
        IgnorePrintAreas:=False

'        '& ThisWorkbook.Sheets("Expense Report").Range("d4") & " - " & ThisWorkbook.Sheets("Expense Report").Range("d5") & " Expense Report.pdf", Quality:=xlQualityStandard, IncludeDocProperties:=True,
        'IgnorePrintAreas:=False, OpenAfterPublish:=True
    
    xFile = "C:\Users\" + Environ("username") + "\Documents\" + username + " Expense Report " + Format(xSht.Range("D4"), "MM.DD.YY") + " - " + Format(xSht.Range("D5"), "MM.DD.YY") + _
        " & " + Format(xSht2.Range("D4"), "MM.DD.YY") + " - " + Format(xSht2.Range("D5"), "MM.DD.YY") + ".pdf"
        
        
    
'    If xFileDlg.Show = True Then
'       xFolder = xFileDlg.SelectedItems(1)
'    Else
'       MsgBox "You must specify a folder to save the PDF into." & vbCrLf & vbCrLf & "Press OK to exit this macro.", vbCritical, "Must Specify Destination Folder"
'       Exit Sub
'
'    End If
        
        

    
    
    
    
    
    
    
    
    
        'Create Outlook email
        Set xOutlookObj = CreateObject("Outlook.Application")
        Set xEmailObj = xOutlookObj.CreateItem(0)
        With xEmailObj
            .Display
            .To = xname
            .cc = "accountassistant@companyname.com;" & cc
            .Subject = "Expense Report for " & xSht.Range("d4") & "-" & xSht.Range("d5") & " and " & xSht2.Range("d4") & " - " & xSht2.Range("d5")
            .Attachments.Add xFile
            .Body = firstname & "," & vbNewLine & vbNewLine & "Please see the attached Expense Report for the weeks " & xSht.Range("d4") & "-" & xSht.Range("d5") _
            & " and " & xSht2.Range("d4") & " - " & xSht2.Range("d5") & "." & vbNewLine & vbNewLine & "Thank you," & vbNewLine
            If DisplayEmail = False Then
                '.Send
            End If
        End With
        
        Kill "C:\Users\" + Environ("username") + "\Documents\" + username + " Expense Report " + Format(xSht.Range("d4"), "MM.DD.YY") + " - " + Format(xSht.Range("d5"), "MM.DD.YY") + _
        " & " + Format(xSht2.Range("d4"), "MM.DD.YY") + " - " + Format(xSht2.Range("d5"), "MM.DD.YY") + ".pdf"
     
Else '''''''''''''''''''''''''''''''''   If Only One Page
If (ThisWorkbook.Sheets("Receipt List").Range("C1").Value > 1) Then
       ThisWorkbook.Sheets(Array("Pics", "Expense Report")).Select
       
Else
    ThisWorkbook.Sheets(Array("Expense Report")).Select
End If

    'Save Active Sheet(s) as PDF
    ActiveSheet.ExportAsFixedFormat Type:=xlTypePDF, _
        Filename:="C:\Users\" + Environ("username") + "\Documents\" + username + " Expense Report " + Format(xSht.Range("d4"), "MM.DD.YY") + " - " + Format(xSht.Range("d5"), "MM.DD.YY") _
        + " .pdf", Quality:=xlQualityStandard, IncludeDocProperties:=True, IgnorePrintAreas:=False, OpenAfterPublish:=False
    
    
    xFile = "C:\Users\" + Environ("username") + "\Documents\" + username + " Expense Report " + Format(xSht.Range("d4"), "MM.DD.YY") + " - " + Format(xSht.Range("d5"), "MM.DD.YY") _
        + " .pdf"
    
        'Create Outlook email
        Set xOutlookObj = CreateObject("Outlook.Application")
        Set xEmailObj = xOutlookObj.CreateItem(0)
        With xEmailObj
            .Display
            .To = xname
            .cc = "accountassistant@companyname.com;" & cc
            .Subject = "Expense Report for " & (ThisWorkbook.Sheets("Expense Report").Range("d4")) & "-" & ThisWorkbook.Sheets("Expense Report").Range("d5")
            .Attachments.Add xFile
            .Body = firstname & "," & vbNewLine & vbNewLine & "Please see the attached Expense Report for the week " & ThisWorkbook.Sheets("Expense Report").Range("d4") & "." & vbNewLine _
            & vbNewLine & "If you have any questions please let me know." & vbNewLine & vbNewLine & "Thank you," & vbNewLine
            If DisplayEmail = False Then
                '.Send
            End If
        End With

        Kill ("C:\Users\" + Environ("username") + "\Documents\" + username + " Expense Report " + Format(xSht.Range("d4"), "MM.DD.YY") + " - " + Format(xSht.Range("d5"), "MM.DD.YY") _
        + " .pdf")
End If

'ThisWorkbook.Sheets("Expense Report").Range("B9:F24").ClearContents
'
'ThisWorkbook.Sheets("Expense Report").Range("G9:K24").ClearContents
'
'ThisWorkbook.Sheets("Expense Report").Range("D4").ClearContents
'
'ThisWorkbook.Sheets("ER Page 2").Range("D4").ClearContents
'
'ThisWorkbook.Sheets("ER Page 2").Range("A9:E24").ClearContents
'
'ThisWorkbook.Sheets("ER Page 2").Range("G9:k24").ClearContents
ThisWorkbook.Sheets("Pics").Visible = True
ThisWorkbook.Sheets("Pics").Pictures.Delete
With ThisWorkbook.Sheets("Receipt List").ListObjects("ReceiptList")
        If Not .DataBodyRange Is Nothing Then
            .DataBodyRange.Delete
        End If
    End With
ThisWorkbook.Sheets("Supervisor List").Range("F2").ClearContents
ThisWorkbook.Sheets("Receipt List").Visible = False
ThisWorkbook.Sheets("Pics").Visible = False
ThisWorkbook.Sheets("Expense Report").Select
 Application.DisplayAlerts = False
     ThisWorkbook.Sheets("Pics").Delete
     ThisWorkbook.Sheets.Add(After:=ThisWorkbook.Sheets(ThisWorkbook.Sheets.count)).Name = "Pics"
    ThisWorkbook.Sheets("Pics").Visible = False
     ThisWorkbook.Sheets("Supervisor List").Visible = False
  Application.DisplayAlerts = True
ThisWorkbook.Sheets("Expense Report").Select
  
ActiveWorkbook.Save


End Sub




                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                
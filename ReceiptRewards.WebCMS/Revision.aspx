<%@ Page Language="C#" AutoEventWireup="true" CodeFile="Revision.aspx.cs" Inherits="Entity_Manager" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head id="Head1" runat="server">
    <!-- Header -->
    <!--#include FILE="Includes/Header.inc" --> 

    <!-- Styles -->
    <link rel="stylesheet" href="Styles/Site.css" type="text/css" />
    <link rel="stylesheet" href="Styles/jquery-ui-1.10.0.min.css" type="text/css" />

    <!-- Script -->
    <script src="Scripts/jquery-1.9.0.js"></script>
    <script src="Scripts/jquery-ui-1.10.0.min.js"></script>
    <script src="Scripts/custom-script.js"></script>
    <script type="text/javascript"> var bigId = 0; bigType = ""; </script>
    
    <!-- Title -->
    <title>Receipt Rewards</title>
</head>
<body>
<div id="MainCenter">
    <!-- Banner -->
    <!--#include FILE="Includes/Banner.inc" -->

    <!-- Main Content -->
    <form id="form1" runat="server">
        <!-- Special Scripts -->
        <asp:Label ID="lblScripts" runat="server"></asp:Label>

        <!-- Title -->
        <asp:Label ID="lblTitle" runat="server" Text="Title" CssClass="pageTitle"></asp:Label>
        <hr class="pageTitle" />

        <!-- Inputs to Edit or Description -->
        <div class="fieldHalfsLeft">
            <asp:Label ID="lblEdit1" Text="Name" runat="server" /><br />
            <asp:TextBox ID="txtEditRevisionNumber" runat="server" style="width: 100%;" /><br />
            <asp:Label ID="lblEdit2" Text="Active" runat="server" />
            <asp:CheckBox ID="chkEditActive" runat="server" style="width: 100%;" /><br />
        </div>
        
        <!-- Right Side Functionalities -->
        <div class="fieldHalfsRight">
            <div style="display: inline-block;">

                <!-- Add Element -->
                <div class="rightOptions dropAdd">
                    Add Question
                </div>

                <!-- Dropdown Fields -->
                <div class="rightOptionsAdd addBox">
                    <h2>Add Question</h2>
                    <asp:Label ID="lblAdd1" Text="Name" runat="server" /><br />
                    <asp:TextBox ID="txtAddQuestionName" style="width: 100%;" runat="server" /><br />
                    <div id="btnAdd"><asp:Button ID="btnAddQuestion" Text="Add Question" onclick="Add_Question_Click" runat="server" /></div>
                </div>

                <!-- Add Question Reference -->
                <div class="rightOptions dropAddCommand">
                    Link Questions
                </div>

                <!-- Dropdown Fields -->
                <div class="rightOptionsAdd addBoxCommand">
                    <h2>Link Questions</h2>
                    <asp:DropDownList ID="ddlAction" runat="server">
                        <asp:ListItem Value="Form" Text="Form">Form</asp:ListItem>
                        <asp:ListItem Value="Automation" Text="Automation">Automation</asp:ListItem>
                    </asp:DropDownList>
                    <asp:Label ID="lblAdd2" Text="Start Question" runat="server" /><br />
                    <asp:TextBox ID="txtLinkQuestionStart" style="width: 100%;" runat="server" /><br />
                    <asp:Label ID="lblAdd3" Text="End Question" runat="server" /><br />
                    <asp:TextBox ID="txtLinkQuestionEnd" style="width: 100%;" runat="server" /><br />
                    <asp:Label ID="lblAdd4" Text="Value / Weight" runat="server" /><br />
                    <asp:TextBox ID="txtLinkValueWeight" style="width: 100%;" runat="server" /><br />
                    <div id="btnAdd"><asp:Button ID="btnLinkQuestion" Text="Link Questions" onclick="Link_Question_Click" runat="server" /></div>
                </div>

                <!-- Delete Element -->
                <div class="rightOptions" onclick='$("#dlgDelete").dialog("open"); $("#hdnDeleteId").val(bigId); $("#hdnDeleteType").val(bigType);'>
                    Delete Revision
                </div>

                <!-- Save Element -->
                <div class="rightOptions" onclick='$("#dlgSave").dialog("open"); $("#hdnSaveId").val(bigId); $("#hdnSaveType").val(bigType);'>
                    Save All Changes
                </div>

                <!-- Perform Automated Test Element -->
                <div class="rightOptions" onclick='$("#dlgAutomatedTest").dialog("open"); $("#hdnAutomatedTestId").val(bigId); $("#hdnAutomatedTestType").val(bigType);'>
                    Perform Automated Test
                </div>

            </div>
        </div>

        <!-- Delete Dialog -->
        <div id="dlgDelete" class="dialog" >
            Are you sure you want to delete?<br />
            <asp:HiddenField ID="hdnDeleteId" runat="server" />
            <asp:HiddenField ID="hdnDeleteType" runat="server" />
            <asp:Button ID="btnDelete" Text="Delete" onclick="Delete_Click" runat="server" />
            <input type="button" value="Cancel" class="btnCancelDialog" />
        </div>

        <!-- Save Dialog -->
        <div id="dlgSave" class="dialog" >
            Are you sure you want to save?<br />
            <asp:HiddenField ID="hdnSaveId" runat="server" />
            <asp:HiddenField ID="hdnSaveType" runat="server" />
            <asp:Button ID="btnSave" Text="Save" onclick="Edit_Click" runat="server" />
            <input type="button" value="Cancel" class="btnCancelDialog" />
        </div>

        <!-- Automated Test Dialog -->
        <div id="dlgAutomatedTest" class="dialog" >
            Are you sure you want to perform an Automated Test?<br />
            <asp:HiddenField ID="hdnAutomatedTestId" runat="server" />
            <asp:HiddenField ID="hdnAutomatedTestType" runat="server" />
            <asp:Button ID="btnAutomatedTest" Text="Perform Test" onclick="Automated_Test_Click" runat="server" />
            <input type="button" value="Cancel" class="btnCancelDialog" />
        </div>

        <!-- Automation List -->
        <br /><hr />
        <p>
            Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque nisl mauris, mollis in pretium non, 
            feugiat ut mi. Etiam facilisis suscipit turpis, vitae rhoncus elit sollicitudin at. Mauris id dui id metus 
            egestas feugiat quis iaculis magna. Pellentesque fringilla venenatis lorem sed porta. Donec feugiat dui 
            sed massa imperdiet ac euismod sapien sodales. Duis in congue purus. Quisque sed tincidunt arcu. 
            Pellentesque enim urna, imperdiet sit amet pretium ut, auctor nec neque.
        </p>

        <!-- Questions List -->
        <h2>Questions</h2>
        <hr />
        <asp:Label ID="lblList" runat="server" Text="Label"></asp:Label><br /><br />
    </form>
</div>
</body>
</html>
<%@ Page Language="C#" AutoEventWireup="true" CodeFile="Question.aspx.cs" Inherits="Entity_Manager" %>
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
            <asp:TextBox ID="txtEditQuestionName" runat="server" style="width: 100%;" /><br />
        </div>
        
        <!-- Right Side Functionalities -->
        <div class="fieldHalfsRight">
            <div style="display: inline-block;">

                <!-- Add Element -->
                <div class="rightOptions dropAddCommand">
                    Add Automation Command Element
                </div>

                <!-- Dropdown Fields -->
                <div class="rightOptionsAdd addBoxCommand">
                    <h2>Add Command Element</h2>
                    <asp:Label ID="lblAdd1" Text="Type" runat="server" /><br />
                    <asp:DropDownList ID="ddlAddCommandType" runat="server" /><br />
                    <asp:Label ID="lblAdd2" Text="Test Data" runat="server" /><br />
                    <asp:TextBox ID="txtAddCommandTestData" style="width: 100%;" runat="server" /><br />
                    <asp:Label ID="lblAdd5" Text="or from existing" runat="server" /><br />
                    <asp:TextBox ID="txtAddCommandExistingId" style="width: 100%;" runat="server" /><br />
                    <div id="btnAdd"><asp:Button ID="btnAddCommand" Text="Add Command Element" onclick="Add_Command_Click" runat="server" /></div>
                </div>

                <!-- Add Element -->
                <div class="rightOptions dropAddForm">
                    Add Form Element
                </div>

                <!-- Dropdown Fields -->
                <div class="rightOptionsAdd addBoxForm">
                    <h2>Add Form Element</h2>
                    <asp:Label ID="lblAdd4" Text="Type" runat="server" /><br />
                    <asp:DropDownList ID="ddlAddFormType" runat="server" /><br />
                    <asp:Label ID="lblAdd6" Text="or from existing" runat="server" /><br />
                    <asp:TextBox ID="txtAddFormExistingId" style="width: 100%;" runat="server" /><br />
                    <div id="btnAdd"><asp:Button ID="btnAddForm" Text="Add Form Element" onclick="Add_Form_Click" runat="server" /></div>
                </div>

                <!-- Delete Element -->
                <div class="rightOptions" onclick='$("#dlgDelete").dialog("open"); $("#hdnDeleteId").val(bigId); $("#hdnDeleteType").val(bigType);'>
                    Delete Question
                </div>

                <!-- Delete Form Flow -->
                <div class="rightOptions" onclick='$("#dlgDelete").dialog("open"); $("#hdnDeleteId").val(formId); $("#hdnDeleteType").val("formFlow");'>
                    Delete All Form Flows 
                </div>

                <!-- Delete Automation Flow -->
                <div class="rightOptions" onclick='$("#dlgDelete").dialog("open"); $("#hdnDeleteId").val(automationId); $("#hdnDeleteType").val("automationFlow");'>
                    Delete All Automation Flows 
                </div>

                <!-- Save Element -->
                <div class="rightOptions" onclick='$("#dlgSave").dialog("open"); $("#hdnSaveId").val(bigId); $("#hdnSaveType").val(bigType);'>
                    Save All Changes
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

        <!-- Order Dialog -->
        <div id="dlgOrder" class="dialog" >
            Are you sure you want to reorder?<br />
            <asp:HiddenField ID="hdnOrderId" runat="server" />
            <asp:HiddenField ID="hdnOrderType" runat="server" />
            <asp:HiddenField ID="hdnOrderDirection" runat="server" />
            <asp:Button ID="btnOrder" Text="Re-Order" onclick="Order_Click" runat="server" />
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
        <div class="fieldHalfsLeft" style="width: 440px;">
            <h2>Automation</h2>
            <hr />
            <asp:Label ID="lblListAutomation" runat="server" Text="Label"></asp:Label><br /><br />
        </div>

        <!-- Form List -->
        <div class="fieldHalfsRight" style="width: 440px;">
            <h2>Form</h2>
            <hr />
            <asp:Label ID="lblListForm" runat="server" Text="Label"></asp:Label><br /><br />
        </div>
    </form>
</div>
</body>
</html>
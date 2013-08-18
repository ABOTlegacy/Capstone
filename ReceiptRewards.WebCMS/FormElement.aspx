<%@ Page Language="C#" AutoEventWireup="true" CodeFile="FormElement.aspx.cs" Inherits="Entity_Manager" %>
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
            <asp:Label ID="lblEdit1" Text="Type" runat="server" /><br />
            <asp:DropDownList ID="ddlEditFormElementType" runat="server" /><br />
        </div>
        
        <!-- Right Side Functionalities -->
        <div class="fieldHalfsRight">
            <div style="display: inline-block; ">

                <!-- Add Element -->
                <div class="rightOptions dropAddAttribute">
                    Add Attribute
                </div>

                <!-- Dropdown Fields -->
                <div class="rightOptionsAdd addBoxAttribute">
                    <h2>Form Element Attribute</h2>
                    <asp:Label ID="lblAdd1" Text="Attribute Type" runat="server" /><br />
                    <asp:DropDownList ID="ddlAddFormElementAttributeType" runat="server" /><br />
                    <asp:Label ID="lblAdd2" Text="Value" runat="server" />
                    <asp:TextBox ID="txtAddFormElementAttributeValue" style="width: 100%;" runat="server" /><br />
                    <div id="btnAdd"><asp:Button ID="btnAdd1" Text="Add Attribute" onclick="Add_Form_Element_Attribute_Click" runat="server" /></div>
                </div>

                <!-- Add Element -->
                <div class="rightOptions dropAddOption">
                    Add Option
                </div>

                <!-- Dropdown Fields -->
                <div class="rightOptionsAdd addBoxOption">
                    <h2>Form Element Option</h2>
                    <asp:Label ID="lblAdd4" Text="Display Text" runat="server" />
                    <asp:TextBox ID="txtAddFormElementOptionDisplayText" style="width: 100%;" runat="server" /><br />
                    <asp:Label ID="lblAdd5" Text="Value" runat="server" />
                    <asp:TextBox ID="txtAddFormElementOptionValue" style="width: 100%;" runat="server" /><br />
                    <div id="btnAdd"><asp:Button ID="btnAdd2" Text="Add Option" onclick="Add_Form_Element_Option_Click" runat="server" /></div>
                </div>

                <!-- Delete Element -->
                <div class="rightOptions" onclick='$("#dlgDelete").dialog("open"); $("#hdnDeleteId").val(bigId); $("#hdnDeleteType").val(bigType);'>
                    Delete Form Element
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

        <!-- Display Attribute List -->
        <br /><hr />
        <h2>List of Attributes</h2>
        <asp:Label ID="lblAttributeList" runat="server" Text="Label"></asp:Label><br /><br />

        <!-- Display Option List -->
        <br /><hr />
        <h2>List of Options</h2>
        <asp:Label ID="lblOptionList" runat="server" Text="Label"></asp:Label><br /><br />
    </form>
</div>
</body>
</html>
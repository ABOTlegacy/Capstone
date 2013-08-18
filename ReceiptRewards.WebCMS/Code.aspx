<%@ Page Language="C#" AutoEventWireup="true" CodeFile="Code.aspx.cs" Inherits="Entity_Manager" %>
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
            <asp:Label ID="lblEdit1" Text="Code" runat="server" /><br />
            <asp:TextBox ID="txtEditCode" runat="server" style="width: 100%;" /><br />
            <asp:Label ID="lblEdit2" Text="Type" runat="server" /><br />
            <asp:TextBox ID="txtEditName" runat="server" style="width: 100%;" /><br />
            <asp:Label ID="lblEdit3" Text="Description" runat="server" /><br />
            <asp:TextBox ID="txtEditDescription" runat="server" TextMode="multiline" Columns="75" Rows="10" style="width: 100%;" /><br />
            <asp:Label ID="lblEdit4" Text="Code Type" runat="server" /><br />
            <asp:TextBox ID="txtEditCodeType" runat="server" style="width: 100%;" ReadOnly="true" /><br />
        </div>
        
        <!-- Right Side Functionalities -->
        <div class="fieldHalfsRight">
            <div style="display: inline-block; ">

                <!-- Delete Element -->
                <div class="rightOptions" onclick='$("#dlgDelete").dialog("open"); $("#hdnDeleteId").val(bigId); $("#hdnDeleteType").val(bigType);'>
                    Delete Code
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
    </form>
</div>
</body>
</html>
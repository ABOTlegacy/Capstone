<%@ Page Language="C#" AutoEventWireup="true" CodeFile="Company_Manager.aspx.cs" Inherits="Entity_Manager" %>
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
    
    <!-- Title -->
    <title>Receipt Rewards</title>
</head>
<body>
<div id="MainCenter">
    <!-- Banner -->
    <!--#include FILE="Includes/Banner.inc" -->

    <!-- Main Content -->
    <form id="form1" runat="server">
        <!-- Survey Group Title -->
        <asp:Label ID="lblTitle" runat="server" Text="Title" CssClass="pageTitle"></asp:Label>
        <hr class="pageTitle" />

        <!-- Inputs to Edit Survey Group -->
        <div class="fieldHalfsLeft">
            <h2>Company Info</h2>
            <div class="smallParagraphs">
                These are all the companies in the system.
                <br /><br />
                Actions include Add, Delete, Edit.
            </div>
        </div>      
        
        <!-- Inputs to Add a Company -->
        <div class="fieldHalfsRight">
            <div style="display: inline-block; ">
                <asp:Panel runat="server" ID="pnlAdd">

                    <!-- Add Company -->
                    <div class="rightOptions dropAdd">
                        Add Company
                    </div>

                    <!-- Dropdown Company Fields -->
                    <div class="rightOptionsAdd addBox">
                        <h2>Create Company</h2>
                        Name:<br />
                        <asp:TextBox name="txtName" ID="txtName" style="width: 100%;" runat="server" /><br />
                        <div id="btnAdd"><asp:Button ID="btnAdd" Text="Add Company" onclick="Add_Click" runat="server" /></div>
                    </div>

                </asp:Panel>
            </div>
        </div>

        <!-- Delete Dialogs -->
        <div id="dlgDelete" class="dialog" >
            Are you sure you want to delete?
            <asp:HiddenField ID="hdnDeleteId" runat="server" />
            <asp:Button ID="btnDelete" Text="Delete" onclick="Delete_Click" runat="server" />
            <input type="button" value="Cancel" class="btnCancelDialog" />
        </div>
        <br /><hr />

        <!-- Display List of Companies -->
        <h2>Company List</h2>
        <asp:Label ID="lblList" runat="server" Text="Label"></asp:Label><br /><br />
    </form>
</div>
</body>
</html>
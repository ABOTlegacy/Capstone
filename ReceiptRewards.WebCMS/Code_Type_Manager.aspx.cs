using ReceiptReward;
using ReceiptRewards.PCL.BusinessLayer;
using ReceiptRewards.PCL.Models;
using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

public partial class Entity_Manager : System.Web.UI.Page {

    protected void Page_Load(object sender, EventArgs e) {
        // Call Methods
        getTitle();
        getList();
    }


    private void getTitle() {
        lblTitle.Text = "Code Type Manager";
    }


    /**
     * Creates a new Code Type
     */
    protected void Add_Click(Object sender, EventArgs e) {
        CodeType codeType = new CodeType();
        codeType.codeTypeId = 0;
        codeType.type = txtType.Text;
        codeType.description = txtDescription.Text;
        CodeTypeImpl.create(codeType);
    }


    /**
     * Deletes a Code Type
     */
    protected void Delete_Click(Object sender, EventArgs e){
        CodeTypeImpl.removeById(int.Parse(hdnDeleteId.Value));
    }


    public void getList() {
        // Instantiate Variables
        string html = "";
        int counter = 0, childrenCount = 0;
        List<CodeType> codeTypes = CodeTypeImpl.getAll();

        // Iterate through list
        foreach (CodeType codeType in codeTypes) {

            // Instantiate Variables
            childrenCount = CodeImpl.getByType(codeType.codeTypeId).Count;

            html += "" +
                "<div style='position: absolute; top: " + (counter * 25) + "px; left: 0px; height: 50px; width: 960px;'>" +
                    "<div class='TableElementDiv' style='width: 200px;'>" + CommonUtils.trim(codeType.type, 15) + "</div>" +
                    "<div class='TableElementDiv' style='width: 50px;'>" + childrenCount + "</div>" +
                    "<div class='TableElementDiv' style='width: 450px;'>" + CommonUtils.trim(codeType.description, 40) + "</div>" +
                    "<div class='TableElementDiv' style='width: 200px;'>" +
                        "<a href='Code_Type.aspx?codeTypeId=" + codeType.codeTypeId + "'>Edit</a> | " +
                        "<a class='isALink' onclick='$(\"#dlgDelete\").dialog(\"open\"); $(\"#hdnDeleteId\").val(\"" + codeType.codeTypeId + "\");'>Delete</a>" +
                    "</div>" +
                "</div>";
            counter++;
        }
        // Add More to the text
        html = "<div id='movingObjectsHolder' style='position: relative; width: 960px; height: " + ((counter - 1) * 25) + "px;'>" + html + "</div>";
        html = "" +
                "<div class='TableElementDiv' style='width: 200px;'><strong>Title</strong></div>" +
                "<div class='TableElementDiv' style='width: 50px;'><strong>#</strong></div>" +
                "<div class='TableElementDiv' style='width: 450px;'><strong>Description</strong></div>" +
                "<div class='TableElementDiv' style='width: 200px;'><strong>Options</strong></div>" + html;
        
        // Display Text
        lblList.Text = html;
    }

}
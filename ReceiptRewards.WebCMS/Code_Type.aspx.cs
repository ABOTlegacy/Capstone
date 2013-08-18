using ReceiptReward;
using ReceiptRewards.PCL.BusinessLayer;
using ReceiptRewards.PCL.Models;
using System;
using System.Collections.Generic;

public partial class Entity_Manager : System.Web.UI.Page {
    // Global Variables
    private int _codeTypeId = 0;
    private CodeType _codeType = null;
    private string _type = "codeType";

    protected void Page_Load(object sender, EventArgs e) {
        
        
        // Call Methods
        getVariables();
        getList();
        if (! this.IsPostBack) {
            getTitle();
        }
    }


    /**
     * Get The Data for the page
     */
    private void getVariables() {
        if (Request.QueryString["codeTypeId"] != null) { this._codeTypeId = int.Parse(Request.QueryString["codeTypeId"]); }
        this._codeType = CodeTypeImpl.getByCodeTypeId(this._codeTypeId);
        lblScripts.Text = "<script type=\"text/javascript\"> var bigId = " + this._codeTypeId + "; bigType = \"" + this._type + "\";</script>";
    }


    /**
     * Get the title of the page
     */
    private void getTitle() {
        lblTitle.Text = this._codeType.type;
        txtEditType.Text = this._codeType.type;
        txtEditDescription.Text = this._codeType.description;
    }


    /**
     * Creates a new element
     */
    protected void Add_Click(Object sender, EventArgs e) {
        Code code = new Code();
        code.code = txtAddCode.Text;
        code.name = txtAddName.Text;
        code.description = txtAddDescription.Text;
        code.codeType = new CodeType();
        code.codeType.codeTypeId = this._codeType.codeTypeId;
        CodeImpl.create(code);
    }


    /**
     * Edit element
     */
    protected void Edit_Click(Object sender, EventArgs e) {
        CodeType codeTypeEdit = new CodeType();
        codeTypeEdit.codeTypeId = this._codeTypeId;
        codeTypeEdit.type = txtEditType.Text;
        codeTypeEdit.description = txtEditDescription.Text;
        CodeTypeImpl.updateById(codeTypeEdit);
    }


    /**
     * Deletes a Code Type or Code
     */
    protected void Delete_Click(Object sender, EventArgs e) {
        if(hdnDeleteType.Value == "codeType") {
            CodeTypeImpl.removeById(int.Parse(hdnDeleteId.Value));
        } else if(hdnDeleteType.Value == "code") {
            CodeImpl.removeById(int.Parse(hdnDeleteId.Value));
        }
    }


    public void getList() {
        // Instantiate Variables
        string html = "";
        int counter = 0;
        List<Code> codes = CodeImpl.getByType(this._codeType.codeTypeId);

        // Iterate through list
        foreach (Code code in codes) {
            html += "" +
                "<div style='position: absolute; top: " + (counter * 25) + "px; left: 0px; height: 50px; width: 960px;'>" +
                    "<div class='TableElementDiv' style='width: 175px;'>" + CommonUtils.trim(code.code, 20) + "</div>" +
                    "<div class='TableElementDiv' style='width: 175px;'>" + CommonUtils.trim(code.name, 20) + "</div>" +
                    "<div class='TableElementDiv' style='width: 300px;'>" + CommonUtils.trim(code.description, 40) + "</div>" +
                    "<div class='TableElementDiv' style='width: 200px;'>" +
                        "<a href='Code.aspx?codeId=" + code.codeId + "'>Edit</a> | " +
                        "<a class='isALink' onclick='$(\"#dlgDelete\").dialog(\"open\"); $(\"#hdnDeleteId\").val(\"" + code.codeId + "\"); $(\"#hdnDeleteType\").val(\"" + "code" + "\");'>Delete</a>" +
                    "</div>" +
                "</div>";
            counter++;
        }
        // Add More to the text
        html = "<div id='movingObjectsHolder' style='position: relative; width: 960px; height: " + ((counter - 1) * 25) + "px;'>" + html + "</div>";
        html = "" +
                "<div class='TableElementDiv' style='width: 175px;'><strong>Code</strong></div>" +
                "<div class='TableElementDiv' style='width: 175px;'><strong>Name</strong></div>" +
                "<div class='TableElementDiv' style='width: 300px;'><strong>Description</strong></div>" +
                "<div class='TableElementDiv' style='width: 200px;'><strong>Options</strong></div>" + html;
        
        // Display Text
        lblList.Text = html;
    }

}
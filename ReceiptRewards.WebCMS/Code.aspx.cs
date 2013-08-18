using ReceiptReward;
using ReceiptRewards.PCL.Models;
using System;

public partial class Entity_Manager : System.Web.UI.Page {
    // Global Variables
    private int _codeId = 0;
    private Code _code = null;
    private string _type = "code";


    protected void Page_Load(object sender, EventArgs e) {
        
        // Call Methods
        getVariables();
        if (! this.IsPostBack) {
            getTitle();
        }
    }


    /**
     * Get The Data for the page
     */
    private void getVariables() {
        if (Request.QueryString["codeId"] != null) { this._codeId = int.Parse(Request.QueryString["codeId"]); }
        this._code = CodeImpl.getByCodeId(this._codeId);
        lblScripts.Text = "<script type=\"text/javascript\"> var bigId = " + this._codeId + "; bigType = \"" + this._type + "\";</script>";
    }


    /**
     * Get the title of the page
     */
    private void getTitle() {
        lblTitle.Text = this._code.code;
        txtEditCode.Text = this._code.code;
        txtEditName.Text = this._code.name;
        txtEditDescription.Text = this._code.description;
        txtEditCodeType.Text = CodeTypeImpl.getByCodeTypeId(this._code.codeType.codeTypeId).type;
    }


    /**
     * Edit element
     */
    protected void Edit_Click(Object sender, EventArgs e) {
        Code codeEdit = new Code();
        codeEdit.codeId = this._codeId;
        codeEdit.code = txtEditCode.Text;
        codeEdit.name = txtEditName.Text;
        codeEdit.description = txtEditDescription.Text;
        CodeImpl.update(codeEdit);
    }


    /**
     * Delete element
     */
    protected void Delete_Click(Object sender, EventArgs e) {
        if(hdnDeleteType.Value == "code") {
            CodeImpl.removeById(int.Parse(hdnDeleteId.Value));
        }
    }

}
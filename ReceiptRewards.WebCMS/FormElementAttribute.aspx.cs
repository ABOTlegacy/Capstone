using ReceiptReward;
using ReceiptRewards.PCL.Models;
using System;
using System.Collections.Generic;
using System.Web.UI.WebControls;

public partial class Entity_Manager : System.Web.UI.Page {
    // Global Variables
    private int _formElementAttributeId = 0;
    private int _revisionId = 0;
    private FormElementAttribute _formElementAttribute = null;
    private FormElement _formElement = null;
    private Revision _revision = null;
    private Survey _survey = null;
    private Company _company = null;
    private string _type = "formElementAttribute";

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
        if (Request.QueryString["formElementAttributeId"] != null) { this._formElementAttributeId = int.Parse(Request.QueryString["formElementAttributeId"]); }
        if (Request.QueryString["revisionId"] != null) { this._revisionId = int.Parse(Request.QueryString["revisionId"]); }
        this._formElementAttribute = FormElementAttributeImpl.getByFormElementAttributeId(this._formElementAttributeId);
        this._formElement = FormElementImpl.getByFormElementId(this._formElementAttribute.formElementId);
        this._revision = RevisionImpl.getByRevisionId(this._revisionId);
        this._survey = SurveyImpl.getBySurveyId(this._revision.surveyId);
        this._company = CompanyImpl.getByCompanyId(this._survey.companyId);
        lblScripts.Text = "<script type=\"text/javascript\"> var bigId = " + this._formElementAttributeId + "; bigType = \"" + this._type + "\";</script>";
    }


    /**
     * Get the title of the page
     */
    private void getTitle() {
        lblTitle.Text = this._company.name + ": " + this._survey.name;
        txtEditFormElementAttributeValue.Text = this._formElementAttribute.value;

        // Populate Dropdowns
        CodeType attributeCodeType = CodeTypeImpl.getByType("FORM_ELEMENT_ATTRIBUTE_TYPE");
        List<Code> attributeCodes = CodeImpl.getByType(attributeCodeType.codeTypeId);
        for (int i = 0; i < attributeCodes.Count; i++) {
            ddlEditFormElementAttributeType.Items.Add(new ListItem(attributeCodes[i].name, attributeCodes[i].codeId + ""));
        }
    }


    /**
     * Edit element
     */
    protected void Edit_Click(Object sender, EventArgs e) {
        FormElementAttribute formElementAttribute = new FormElementAttribute();
        formElementAttribute.formElementAttributeId = this._formElementAttributeId;
        formElementAttribute.code.codeId = int.Parse(ddlEditFormElementAttributeType.Text);
        formElementAttribute.value = txtEditFormElementAttributeValue.Text;
        FormElementAttributeImpl.update(formElementAttribute);
    }


    /**
     * Delete element
     */
    protected void Delete_Click(Object sender, EventArgs e) {
        if (hdnDeleteType.Value == "formElementAttribute") {
            FormElementAttributeImpl.removeByFormElementAttributeId(int.Parse(hdnDeleteId.Value));
        }
    }
}
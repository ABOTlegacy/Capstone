using ReceiptReward;
using ReceiptRewards.PCL.Models;
using System;

public partial class Entity_Manager : System.Web.UI.Page {
    // Global Variables
    private int _formElementOptionId = 0;
    private int _revisionId = 0;
    private FormElementOption _formElementOption = null;
    private FormElement _formElement = null;
    private Revision _revision = null;
    private Survey _survey = null;
    private Company _company = null;
    private string _type = "formElementOption";



    protected void Page_Load(object sender, EventArgs e) {
        // Call Methods
        getVariables();
        if (!this.IsPostBack) {
            getTitle();
        }
    }



    /**
     * Get The Data for the page
     */
    private void getVariables() {
        if (Request.QueryString["formElementOptionId"] != null) { this._formElementOptionId = int.Parse(Request.QueryString["formElementOptionId"]); }
        if (Request.QueryString["revisionId"] != null) { this._revisionId = int.Parse(Request.QueryString["revisionId"]); }
        this._formElementOption = FormElementOptionImpl.getByFormElementOptionId(this._formElementOptionId);
        this._formElement = FormElementImpl.getByFormElementId(this._formElementOption.formElementId);
        this._revision = RevisionImpl.getByRevisionId(this._revisionId, 2);
        this._survey = SurveyImpl.getBySurveyId(this._revision.surveyId);
        this._company = CompanyImpl.getByCompanyId(this._survey.companyId);
        lblScripts.Text = "<script type=\"text/javascript\"> var bigId = " + this._formElementOptionId + "; bigType = \"" + this._type + "\";</script>";
    }



    /**
     * Get the title of the page
     */
    private void getTitle() {
        lblTitle.Text = this._company.name + ": " + this._survey.name;
        txtEditFormElementOptionDisplayText.Text = this._formElementOption.displayText.displayTextTranslation;
        txtEditFormElementOptionValue.Text = this._formElementOption.value;
        hdnDisplayTextId.Value = this._formElementOption.displayText.displayTextId + "";
    }



    /**
     * Edit element
     */
    protected void Edit_Click(Object sender, EventArgs e) {
        FormElementOption formElementOption = new FormElementOption();
        formElementOption.formElementOptionId = this._formElementOptionId;
        formElementOption.value = txtEditFormElementOptionValue.Text;
        DisplayText displayText = new DisplayText();
        displayText.displayTextId = int.Parse(hdnDisplayTextId.Value);
        displayText.displayTextTranslation = txtEditFormElementOptionDisplayText.Text;
        formElementOption.displayText = displayText;
        FormElementOptionImpl.update(formElementOption);
    }



    /**
     * Delete element
     */
    protected void Delete_Click(Object sender, EventArgs e) {
        if (hdnDeleteType.Value == "formElementOption") {
            FormElementOptionImpl.removeByFormElementOptionId(int.Parse(hdnDeleteId.Value));
        }
    }
}
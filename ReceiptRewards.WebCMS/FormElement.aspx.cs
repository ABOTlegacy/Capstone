using ReceiptReward;
using ReceiptRewards.PCL.BusinessLayer;
using ReceiptRewards.PCL.Models;
using System;
using System.Collections.Generic;
using System.Web.UI.WebControls;

public partial class Entity_Manager : System.Web.UI.Page {
    // Global Variables
    private int _formElementId = 0;
    private int _questionId = 0;
    private int _revisionId = 0;
    private FormElement _formElement = null;
    private Revision _revision = null;
    private Survey _survey = null;
    private Company _company = null;
    private string _type = "formElement";


    protected void Page_Load(object sender, EventArgs e) {
        
        // Call Methods
        getVariables();
        if (! this.IsPostBack) {
            getTitle();
            getList();
        }
    }


    /**
     * Get The Data for the page
     */
    private void getVariables() {
        if (Request.QueryString["formElementId"] != null) { this._formElementId = int.Parse(Request.QueryString["formElementId"]); }
        if (Request.QueryString["revisionId"] != null) { this._revisionId = int.Parse(Request.QueryString["revisionId"]); }
        if (Request.QueryString["questionId"] != null) { this._questionId = int.Parse(Request.QueryString["questionId"]); }
        this._formElement = FormElementImpl.getByFormElementId(this._formElementId);
        this._revision = RevisionImpl.getByRevisionId(this._revisionId, 2);
        this._survey = SurveyImpl.getBySurveyId(this._revision.surveyId);
        this._company = CompanyImpl.getByCompanyId(this._survey.companyId);
        lblScripts.Text = "<script type=\"text/javascript\"> var bigId = " + this._formElementId + "; bigType = \"" + this._type + "\";</script>";
    }


    /**
     * Get the title of the page
     */
    private void getTitle() {
        lblTitle.Text = this._company.name + ": " + this._survey.name;

        // Populate Dropdowns
        CodeType formElementCodeType = CodeTypeImpl.getByType("FORM_ELEMENT_TYPE");
        List<Code> formElementCodes = CodeImpl.getByType(formElementCodeType.codeTypeId);
        for (int i = 0; i < formElementCodes.Count; i++) {
            ddlEditFormElementType.Items.Add(new ListItem(formElementCodes[i].name, formElementCodes[i].codeId + ""));
        }
        CodeType formElementAttributeCodeType = CodeTypeImpl.getByType("FORM_ELEMENT_ATTRIBUTE_TYPE");
        List<Code> formElementAttributeCodes = CodeImpl.getByType(formElementAttributeCodeType.codeTypeId);
        for (int i = 0; i < formElementAttributeCodes.Count; i++) {
            ddlAddFormElementAttributeType.Items.Add(new ListItem(formElementAttributeCodes[i].name, formElementAttributeCodes[i].codeId + ""));
        }
        CodeType formElementOptionCodeType = CodeTypeImpl.getByType("FORM_ELEMENT_OPTION_TYPE");
        List<Code> formElementOptionCodes = CodeImpl.getByType(formElementOptionCodeType.codeTypeId);
        for (int i = 0; i < formElementOptionCodes.Count; i++) {
            ddlAddFormElementAttributeType.Items.Add(new ListItem(formElementOptionCodes[i].name, formElementOptionCodes[i].codeId + ""));
        }
    }


    /**
     * Creates a new element
     */
    protected void Add_Form_Element_Attribute_Click(Object sender, EventArgs e) {
        FormElementAttribute formElementAttribute = new FormElementAttribute();
        formElementAttribute.code.codeId = int.Parse(ddlAddFormElementAttributeType.Text);
        formElementAttribute.formElementAttributeId = 0;
        formElementAttribute.formElementId = this._formElementId;
        formElementAttribute.value = txtAddFormElementAttributeValue.Text;
        FormElementAttributeImpl.create(formElementAttribute, this._formElementId);
    }


    /**
     * Creates a new element
     */
    protected void Add_Form_Element_Option_Click(Object sender, EventArgs e) {
        FormElementOption formElementOption = new FormElementOption();
        formElementOption.displayText = new DisplayText();
        formElementOption.displayText.displayTextTranslation = txtAddFormElementOptionDisplayText.Text;
        formElementOption.formElementOptionId = 0;
        formElementOption.formElementId = this._formElementId;
        formElementOption.value = txtAddFormElementOptionValue.Text;
        FormElementOptionImpl.create(formElementOption, this._formElementId);
    }


    /**
     * Edit element
     */
    protected void Edit_Click(Object sender, EventArgs e) {
        FormElement formElement = new FormElement();
        formElement.formElementId = this._formElementId;
        formElement.code.codeId = int.Parse(ddlEditFormElementType.Text);
        FormElementImpl.update(formElement);
    }


    /**
     * Delete element
     */
    protected void Delete_Click(Object sender, EventArgs e) {
        if (hdnDeleteType.Value == "formElement") {
            FormElementImpl.removeByFormElementId(int.Parse(hdnDeleteId.Value));
        } else if (hdnDeleteType.Value == "formElementAttribute") {
            FormElementAttributeImpl.removeByFormElementAttributeId(int.Parse(hdnDeleteId.Value));
        } else if (hdnDeleteType.Value == "formElementOption") {
            FormElementOptionImpl.removeByFormElementOptionId(int.Parse(hdnDeleteId.Value));
        }
    }

    public void getList() {
        getAttributeList();
        getOptionList();
    }

    public void getAttributeList() {
        // Instantiate Variables
        string html = "";
        int counter = 0;
        List<FormElementAttribute> list = this._formElement.formElementAttributes;

        // Iterate through list
        foreach (FormElementAttribute item in list) {

            // HTML
            html += "" +
                "<div style='position: absolute; top: " + (counter * 25) + "px; left: 0px; height: 50px; width: 960px;'>" +
                    "<div class='TableElementDiv' style='width: 200px;'>" + CodeImpl.getByCodeId(item.code.codeId).code + "</div>" +
                    "<div class='TableElementDiv' style='width: 200px;'>" + CommonUtils.trim(item.value, 20) + "</div>" +
                    "<div class='TableElementDiv' style='width: 200px;'>" +
                        "<a href='FormElementAttribute.aspx?formElementAttributeId=" + item.formElementAttributeId + "&revisionId=" + this._revisionId + "'>Edit</a> | " +
                        "<a class='isALink' onclick='$(\"#dlgDelete\").dialog(\"open\"); $(\"#hdnDeleteId\").val(\"" + item.formElementAttributeId + "\"); $(\"#hdnDeleteType\").val(\"" + "formElementAttribute" + "\");'>Delete</a>" +
                    "</div>" +
                "</div>";
            counter++;
        }

        // Add More to the text
        html = "<div id='movingObjectsHolder' style='position: relative; width: 960px; height: " + ((counter - 1) * 25) + "px;'>" + html + "</div>";
        html = "" +
                "<div class='TableElementDiv' style='width: 200px;'><strong>Type</strong></div>" +
                "<div class='TableElementDiv' style='width: 200px;'><strong>Value</strong></div>" +
                "<div class='TableElementDiv' style='width: 200px;'><strong>Options</strong></div>" + html;

        // Display Text
        lblAttributeList.Text = html;
    }


    public void getOptionList() {
        // Instantiate Variables
        string html = "";
        int counter = 0, childrenCount = 0;
        List<FormElementOption> list = this._formElement.formElementOptions;

        // Iterate through list
        foreach (FormElementOption item in list) {

            // Instantiate Variables
            childrenCount = 0; //Survey.containsNumOfQuestionGroups(surveyID);

            html += "" +
                "<div style='position: absolute; top: " + (counter * 25) + "px; left: 0px; height: 50px; width: 960px;'>" +
                    "<div class='TableElementDiv' style='width: 100px;'>" + item.formElementOptionId + "</div>" +
                    "<div class='TableElementDiv' style='width: 200px;'>" + CommonUtils.trim(item.displayText.displayTextTranslation, 20) + "</div>" +
                    "<div class='TableElementDiv' style='width: 200px;'>" + CommonUtils.trim(item.value, 20) + "</div>" +
                    "<div class='TableElementDiv' style='width: 200px;'>" +
                        "<a href='FormElementOption.aspx?formElementOptionId=" + item.formElementOptionId + "&revisionId=" + this._revisionId + "'>Edit</a> | " +
                        "<a class='isALink' onclick='$(\"#dlgDelete\").dialog(\"open\"); $(\"#hdnDeleteId\").val(\"" + item.formElementOptionId + "\"); $(\"#hdnDeleteType\").val(\"" + "formElementOption" + "\");'>Delete</a>" +
                    "</div>" +
                "</div>";
            counter++;
        }
        // Add More to the text
        html = "<div id='movingObjectsHolder' style='position: relative; width: 960px; height: " + ((counter - 1) * 25) + "px;'>" + html + "</div>";
        html = "" +
                "<div class='TableElementDiv' style='width: 100px;'><strong>ID</strong></div>" +
                "<div class='TableElementDiv' style='width: 200px;'><strong>Text</strong></div>" +
                "<div class='TableElementDiv' style='width: 200px;'><strong>Value</strong></div>" +
                "<div class='TableElementDiv' style='width: 200px;'><strong>Options</strong></div>" + html;

        // Display Text
        lblOptionList.Text = html;
    }

}
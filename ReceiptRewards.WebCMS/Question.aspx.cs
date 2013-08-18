using ReceiptReward;
using ReceiptRewards.PCL.Models;
using System;
using System.Collections.Generic;
using System.Web.UI.WebControls;

public partial class Entity_Manager : System.Web.UI.Page {
    // Global Variables
    private int _questionId = 0;
    private int _revisionId = 0;
    private Question _question = null;
    private Revision _revision = null;
    private Survey _survey = null;
    private Company _company = null;
    private string _type = "question";



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
        if (Request.QueryString["questionId"] != null) { this._questionId = int.Parse(Request.QueryString["questionId"]); }
        if (Request.QueryString["revisionId"] != null) { this._revisionId = int.Parse(Request.QueryString["revisionId"]); }
        this._question = QuestionImpl.getByQuestionId(this._questionId, 2);
        this._revision = RevisionImpl.getByRevisionId(this._revisionId, 1);
        this._survey = SurveyImpl.getBySurveyId(this._revision.surveyId);
        this._company = CompanyImpl.getByCompanyId(this._survey.companyId);
        lblScripts.Text = "<script type=\"text/javascript\"> var bigId = " + this._questionId + "; bigType = \"" + this._type + "\";</script>" +
                          "<script type=\"text/javascript\"> var formId = " + this._question.form.formId + "; var automationId = \"" + this._question.automation.automationId + "\";</script>";
    }



    /**
     * Get the title of the page
     */
    private void getTitle() {
        lblTitle.Text = this._company.name + ": " + this._survey.name + " v" + this._revision.revisionNumber;
        txtEditQuestionName.Text = this._question.name;

        // Populate Dropdowns
        CodeType commandCodeType = CodeTypeImpl.getByType("COMMAND_ELEMENT_TYPE");
        List<Code> commandCodes = CodeImpl.getByType(commandCodeType.codeTypeId);
        for(int i = 0; i < commandCodes.Count; i++) {
            ddlAddCommandType.Items.Add(new ListItem(commandCodes[i].name, commandCodes[i].codeId + ""));
        }
        CodeType formCodeType = CodeTypeImpl.getByType("FORM_ELEMENT_TYPE");
        List<Code> formCodes = CodeImpl.getByType(formCodeType.codeTypeId);
        for (int i = 0; i < formCodes.Count; i++) {
            ddlAddFormType.Items.Add(new ListItem(formCodes[i].name, formCodes[i].codeId + ""));
        }
    }



    /**
     * Creates a new element
     */
    protected void Add_Command_Click(Object sender, EventArgs e) {
        if(txtAddCommandExistingId.Text.Trim().Equals("")) {
            CommandElement commandElement = new CommandElement();
            commandElement.code = new Code();
            commandElement.code.codeType = new CodeType();
            commandElement.code.codeType.type = "COMMAND_ELEMENT_TYPE";
            commandElement.code.codeId = int.Parse(ddlAddCommandType.Text);
            commandElement.commandElementId = 0;
            commandElement.testData = txtAddCommandTestData.Text;
            CommandElementImpl.create(commandElement, this._question.automation.automationId);
        } else {
            CommandElementImpl.createFromExisting(int.Parse(txtAddCommandExistingId.Text), this._question.automation.automationId);
        }
    }



    /**
    * Creates a new element
    */
    protected void Add_Form_Click(Object sender, EventArgs e) {
        if(txtAddFormExistingId.Text.Trim().Equals("")) {
            FormElement formElement = new FormElement();
            formElement.code = new Code();
            formElement.code.codeId = int.Parse(ddlAddFormType.Text);
            formElement.formElementId = 0;
            FormElementImpl.create(formElement, this._question.form.formId);
        } else {
            FormElementImpl.createFromExisting(int.Parse(txtAddFormExistingId.Text), this._question.form.formId);
        }
    }



    /**
     * Edit element
     */
    protected void Edit_Click(Object sender, EventArgs e) {
        Question question = new Question();
        question.questionId = int.Parse(hdnSaveId.Value);
        question.name = txtEditQuestionName.Text;
        QuestionImpl.update(question);
    }



    /**
     * Delete element
     */
    protected void Delete_Click(Object sender, EventArgs e) {
        if (hdnDeleteType.Value == "question") {
            QuestionImpl.removeByQuestionId(int.Parse(hdnDeleteId.Value));
        } else if (hdnDeleteType.Value == "commandElement") {
            CommandElementImpl.removeByCommandElementId(int.Parse(hdnDeleteId.Value));
        } else if (hdnDeleteType.Value == "formElement") {
            FormElementImpl.removeByFormElementId(int.Parse(hdnDeleteId.Value));
        } else if (hdnDeleteType.Value == "formFlow") {
            FormFlowImpl.removeByFormId(int.Parse(hdnDeleteId.Value));
        } else if (hdnDeleteType.Value == "automationFlow") {
            AutomationFlowImpl.removeByAutomationId(int.Parse(hdnDeleteId.Value));
        }
    }



    /**
     * Delete element
     */
    protected void Order_Click(Object sender, EventArgs e) {
        // Form Element
        if (hdnOrderType.Value == "formElement") {
            int formElementId = int.Parse(hdnOrderId.Value);
            string direction = hdnOrderDirection.Value;
            
            // Find Form Location
            int locationInArray = 0;
            for(int i = 0; i < this._question.form.formElements.Count; i++) {
                if(this._question.form.formElements[i].formElementId == formElementId) {
                    locationInArray = i;
                }
            }

            // Sort The Array
            FormElement tempA = null;
            FormElement tempB = null;
            if(direction == "Up") {
                tempA = this._question.form.formElements[locationInArray];
                tempB = this._question.form.formElements[locationInArray - 1];
                this._question.form.formElements[locationInArray - 1] = tempA;
                this._question.form.formElements[locationInArray] = tempB;
            } else if(direction == "Down") {
                tempA = this._question.form.formElements[locationInArray];
                tempB = this._question.form.formElements[locationInArray + 1];
                this._question.form.formElements[locationInArray + 1] = tempA;
                this._question.form.formElements[locationInArray] = tempB;
            }
            //FormImpl.updateWeight(this.question.form);

        // Command Element
        } else if (hdnDeleteType.Value == "commandElement") {
            
        }
    }



    public void getList() {
        //lblListAutomation.Text = "Automation - <a href='Automation.aspx?automationId=" + revision.automation.automationId + "'>Edit</a>";
        getAutomationList();
        //lblListForm.Text = "Form - <a href='Form.aspx?formId=" + revision.form.formId + "'>Edit</a>";
        getFormList();
    }



    public void getAutomationList() {
        // Instantiate Variables
        string html = "";
        int counter = 0, childrenCount = 0;

        // Iterate through list
        foreach (CommandElement item in this._question.automation.commandElements) {

            // Instantiate Variables
            childrenCount = 0; //item.commandIdentifiers.Count;

            // HTML
            html += "" +
                "<div style='position: absolute; top: " + (counter * 25) + "px; left: 0px; height: 50px; width: 440px;'>" +
                    "<div class='TableElementDiv' style='width: 50px;'>" + item.commandElementId + "</div>" +
                    "<div class='TableElementDiv' style='width: 150px;'>" + CodeImpl.getByCodeId(item.code.codeId).code + "</div>" +
                    "<div class='TableElementDiv' style='width: 75px;'>" + childrenCount + "</div>" +
                    "<div class='TableElementDiv' style='width: 120px;'>" +
                        "<a href='CommandElement.aspx?commandElementId=" + item.commandElementId + "&revisionId=" + this._revisionId + "&questionId=" + this._question.questionId + "'>Edt</a> | " +
                        "<a class='isALink' onclick='$(\"#dlgDelete\").dialog(\"open\"); $(\"#hdnDeleteId\").val(\"" + item.commandElementId + "\"); $(\"#hdnDeleteType\").val(\"" + "commandElement" + "\");'>Dlt</a>" +
                    "</div>" +
                "</div>";
            counter++;
        }
        // Add More to the text
        html = "<div id='movingObjectsHolder' style='position: relative; width: 440px; height: " + ((counter - 1) * 25) + "px;'>" + html + "</div>";
        html = "" +
                "<div class='TableElementDiv' style='width: 50px;'><strong>ID#</strong></div>" +
                "<div class='TableElementDiv' style='width: 150px;'><strong>Type</strong></div>" +
                "<div class='TableElementDiv' style='width: 75px;'><strong>Idntfs</strong></div>" +
                "<div class='TableElementDiv' style='width: 120px;'><strong>Optns</strong></div>" + html;

        // Display Text
        lblListAutomation.Text = html;
    }



    public void getFormList() {
        // Instantiate Variables
        string html = "";
        int counter = 0, childrenCount = 0;

        // Iterate through list
        foreach (FormElement item in this._question.form.formElements) {

            // Instantiate Variables
            childrenCount = 0; //Survey.containsNumOfQuestionGroups(surveyID);

            html += "" +
                "<div style='position: absolute; top: " + (counter * 25) + "px; left: 0px; height: 50px; width: 440px;'>" +
                    "<div class='TableElementDiv' style='width: 50px;'>" + item.formElementId + "</div>" +
                    "<div class='TableElementDiv' style='width: 150px;'>" + CodeImpl.getByCodeId(item.code.codeId).code + "</div>" +
                    "<div class='TableElementDiv' style='width: 55px;'>" + childrenCount + "</div>" +
                    "<div class='TableElementDiv' style='width: 140px;'>" +
                        "<a href='FormElement.aspx?formElementId=" + item.formElementId + "&revisionId=" + this._revisionId + "&questionId=" + this._question.questionId + "'>Edt</a> | " +
                        "<a class='isALink' onclick='$(\"#dlgDelete\").dialog(\"open\"); $(\"#hdnDeleteId\").val(\"" + item.formElementId + "\"); $(\"#hdnDeleteType\").val(\"" + "formElement" + "\");'>Dlt</a>" +
                        "<a class='isALink' onclick='$(\"#dlgOrder\").dialog(\"open\"); $(\"#hdnOrderId\").val(\"" + item.formElementId + "\"); $(\"#hdnOrderType\").val(\"" + "formElement" + "\"); $(\"#hdnOrderDirection\").val(\"" + "Up" + "\");'>Up</a>" +
                        "<a class='isALink' onclick='$(\"#dlgOrder\").dialog(\"open\"); $(\"#hdnOrderId\").val(\"" + item.formElementId + "\"); $(\"#hdnOrderType\").val(\"" + "formElement" + "\"); $(\"#hdnOrderDirection\").val(\"" + "Down" + "\");'>Down</a>" +
                    "</div>" +
                "</div>";
            counter++;
        }
        // Add More to the text
        html = "<div id='movingObjectsHolder' style='position: relative; width: 440px; height: " + ((counter - 1) * 25) + "px;'>" + html + "</div>";
        html = "" +
                "<div class='TableElementDiv' style='width: 50px;'><strong>ID#</strong></div>" +
                "<div class='TableElementDiv' style='width: 150px;'><strong>Type</strong></div>" +
                "<div class='TableElementDiv' style='width: 55px;'><strong>Idntfs</strong></div>" +
                "<div class='TableElementDiv' style='width: 140px;'><strong>Optns</strong></div>" + html;

        // Display Text
        lblListForm.Text = html;
    }
}
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
    // Global Variables
    private int _commandElementId = 0;
    private int _questionId = 0;
    private int _revisionId = 0;
    private CommandElement _commandElement = null;
    private Automation _automation = null;
    private Question _question = null;
    private Revision _revision = null;
    private Survey _survey = null;
    private Company _company = null;
    private string _type = "commandElement";

    protected void Page_Load(object sender, EventArgs e) {
        
        // Call Methods
        getVariables();
        if (! this.IsPostBack) {
            getTitle();
            getList();
            getRelationList();
        }
    }


    /**
     * Get The Data for the page
     */
    private void getVariables() {
        if (Request.QueryString["commandElementId"] != null) { this._commandElementId = int.Parse(Request.QueryString["commandElementId"]); }
        if (Request.QueryString["questionId"] != null) { this._questionId = int.Parse(Request.QueryString["questionId"]); }
        if (Request.QueryString["revisionId"] != null) { this._revisionId = int.Parse(Request.QueryString["revisionId"]); }
        this._commandElement = CommandElementImpl.getByCommandElementId(this._commandElementId);
        this._revision = RevisionImpl.getByRevisionId(this._revisionId, 2);
        this._survey = SurveyImpl.getBySurveyId(this._revision.surveyId);
        this._company = CompanyImpl.getByCompanyId(this._survey.companyId);
        lblScripts.Text = "<script type=\"text/javascript\"> var bigId = " + this._commandElementId + "; bigType = \"" + this._type + "\";</script>";
    }


    /**
     * Get the title of the page
     */
    private void getTitle() {
        lblTitle.Text = this._company.name + ": " + this._survey.name;
        txtEditTestData.Text = this._commandElement.testData;

        // Populate Dropdowns
        CodeType commandCodeType = CodeTypeImpl.getByType("COMMAND_ELEMENT_TYPE");
        List<Code> commandCodes = CodeImpl.getByType(commandCodeType.codeTypeId);
        for (int i = 0; i < commandCodes.Count; i++) {
            ddlEditCommandType.Items.Add(new ListItem(commandCodes[i].name, commandCodes[i].codeId + ""));
        }
        CodeType identifierCodeType = CodeTypeImpl.getByType("COMMAND_ELEMENT_IDENTIFIER_TYPE");
        List<Code> identifierCodes = CodeImpl.getByType(identifierCodeType.codeTypeId);
        for (int i = 0; i < identifierCodes.Count; i++) {
            ddlAddIdentificationType.Items.Add(new ListItem(identifierCodes[i].name, identifierCodes[i].codeId + ""));
        }
        List<FormElement> formElements = FormImpl.getByQuestionId(this._questionId, 2).formElements;
        for (int i = 0; i < formElements.Count; i++) {
            ddlAddRelation.Items.Add(new ListItem(formElements[i].formElementId + "", formElements[i].formElementId + ""));
        }
    }


    /**
     * Creates a new element
     */
    protected void Add_Identification_Click(Object sender, EventArgs e) {
        CommandIdentifier commandIdentifier = new CommandIdentifier();
        commandIdentifier.code = new Code();
        commandIdentifier.code.codeType = new CodeType();
        commandIdentifier.code.codeType.type = "COMMAND_ELEMENT_IDENTIFIER_TYPE";
        commandIdentifier.code.codeId = int.Parse(ddlAddIdentificationType.Text);
        commandIdentifier.commandElementId = this._commandElementId;
        commandIdentifier.commandIdentifierId = 0;
        commandIdentifier.value = txtAddIdentificationValue.Text;
        CommandIdentifierImpl.create(commandIdentifier, this._commandElementId);
    }



    /**
     * Creates a new element
     */
    protected void Add_Relation_Click(Object sender, EventArgs e) {
        CommandFormElementRelation commandFormElementRelation = new CommandFormElementRelation();
        commandFormElementRelation.commandElementId = this._commandElementId;
        if(txtAddRelation.Text.Trim().Length == 0) {
            commandFormElementRelation.formElementId = int.Parse(ddlAddRelation.SelectedValue);
        } else {
            commandFormElementRelation.formElementId = int.Parse(txtAddRelation.Text);
        }
        CommandFormElementRelationImpl.create(commandFormElementRelation);
    }



    /**
     * Edit element
     */
    protected void Edit_Click(Object sender, EventArgs e) {
        CommandElement commandElement = new CommandElement();
        commandElement.commandElementId = this._commandElementId;
        commandElement.code.codeId = int.Parse(ddlEditCommandType.Text);
        commandElement.testData = txtEditTestData.Text;
        CommandElementImpl.update(commandElement);
    }


    /**
     * Delete element
     */
    protected void Delete_Click(Object sender, EventArgs e) {
        if (hdnDeleteType.Value == "commandElement") {
            CommandElementImpl.removeByCommandElementId(int.Parse(hdnDeleteId.Value));
        } else if (hdnDeleteType.Value == "commandIdentifier") {
            CommandIdentifierImpl.removeByCommandIdentifierId(int.Parse(hdnDeleteId.Value));
        } else if (hdnDeleteType.Value == "commandFormElementRelation") {
            CommandFormElementRelationImpl.removeByCommandFormElementRelationId(int.Parse(hdnDeleteId.Value));
        }
    }


    public void getList() {
        // Instantiate Variables
        string html = "";
        int counter = 0, childrenCount = 0;
        List<CommandIdentifier> list = CommandIdentifierImpl.getByCommandElementId(this._commandElementId);

        // Iterate through list
        foreach (CommandIdentifier item in list) {

            // Instantiate Variables
            childrenCount = 0; //Survey.containsNumOfQuestionGroups(surveyID);

            html += "" +
                "<div style='position: absolute; top: " + (counter * 25) + "px; left: 0px; height: 50px; width: 960px;'>" +
                    "<div class='TableElementDiv' style='width: 200px;'>" + CodeImpl.getByCodeId(item.code.codeId).code + "</div>" +
                    "<div class='TableElementDiv' style='width: 400px;'>" + CommonUtils.trim(item.value, 40) + "</div>" +
                    "<div class='TableElementDiv' style='width: 200px;'>" +
                        "<a href='CommandIdentifier.aspx?commandIdentifierId=" + item.commandIdentifierId + "&revisionId=" + this._revisionId + "'>Edit</a> | " +
                        "<a class='isALink' onclick='$(\"#dlgDelete\").dialog(\"open\"); $(\"#hdnDeleteId\").val(\"" + item.commandIdentifierId + "\"); $(\"#hdnDeleteType\").val(\"" + "commandIdentifier" + "\");'>Delete</a>" +
                    "</div>" +
                "</div>";
            counter++;
        }
        // Add More to the text
        html = "<div id='movingObjectsHolder' style='position: relative; width: 960px; height: " + ((counter - 1) * 25) + "px;'>" + html + "</div>";
        html = "" +
                "<div class='TableElementDiv' style='width: 200px;'><strong>Type</strong></div>" +
                "<div class='TableElementDiv' style='width: 400px;'><strong>Value</strong></div>" +
                "<div class='TableElementDiv' style='width: 200px;'><strong>Options</strong></div>" + html;

        // Display Text
        lblList.Text = html;
    }



    public void getRelationList() {
        // Instantiate Variables
        string html = "";
        int counter = 0, childrenCount = 0;
        List<CommandFormElementRelation> list = CommandFormElementRelationImpl.getByCommandElementId(this._commandElementId);

        // Iterate through list
        foreach (CommandFormElementRelation item in list) {
            html += "" +
                "<div style='position: absolute; top: " + (counter * 25) + "px; left: 0px; height: 50px; width: 960px;'>" +
                    "<div class='TableElementDiv' style='width: 200px;'>" + item.commandFormElementRelationId + "</div>" +
                    "<div class='TableElementDiv' style='width: 200px;'>" + item.formElementId + "</div>" +
                    "<div class='TableElementDiv' style='width: 200px;'>" +
                        "<a class='isALink' onclick='$(\"#dlgDelete\").dialog(\"open\"); $(\"#hdnDeleteId\").val(\"" + item.commandFormElementRelationId + "\"); $(\"#hdnDeleteType\").val(\"" + "commandFormElementRelation" + "\");'>Delete</a>" +
                    "</div>" +
                "</div>";
            counter++;
        }
        // Add More to the text
        html = "<div id='movingObjectsHolder' style='position: relative; width: 960px; height: " + ((counter - 1) * 25) + "px;'>" + html + "</div>";
        html = "" +
                "<div class='TableElementDiv' style='width: 200px;'><strong>Relation ID</strong></div>" +
                "<div class='TableElementDiv' style='width: 200px;'><strong>Form Element ID</strong></div>" +
                "<div class='TableElementDiv' style='width: 200px;'><strong>Options</strong></div>" + html;

        // Display Text
        lblRelationList.Text = html;
    }
}
using ReceiptReward;
using ReceiptRewards.PCL.Models;
using System;
using System.Collections.Generic;
using System.Web.UI.WebControls;

public partial class Entity_Manager : System.Web.UI.Page {
    // Global Variables
    private int _commandIdentifierId = 0;
    private int _revisionId = 0;
    private CommandIdentifier _commandIdentifier = null;
    private CommandElement _commandElement = null;
    private Revision _revision = null;
    private Survey _survey = null;
    private Company _company = null;
    private string _type = "commandIdentifier";

    
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
        if (Request.QueryString["commandElementId"] != null) { this._commandIdentifierId = int.Parse(Request.QueryString["commandIdentifierId"]); }
        if (Request.QueryString["revisionId"] != null) { this._revisionId = int.Parse(Request.QueryString["revisionId"]); }
        this._commandIdentifier = CommandIdentifierImpl.getByCommandIdentifierId(this._commandIdentifierId);
        this._commandElement = CommandElementImpl.getByCommandElementId(this._commandIdentifier.commandElementId);
        this._revision = RevisionImpl.getByRevisionId(this._revisionId);
        this._survey = SurveyImpl.getBySurveyId(this._revision.surveyId);
        this._company = CompanyImpl.getByCompanyId(this._survey.companyId);
        lblScripts.Text = "<script type=\"text/javascript\"> var bigId = " + this._commandIdentifierId + "; bigType = \"" + this._type + "\";</script>";
    }


    /**
     * Get the title of the page
     */
    private void getTitle() {
        lblTitle.Text = this._company.name + ": " + this._survey.name;
        txtEditIdentificationValue.Text = this._commandIdentifier.value;

        // Populate Dropdowns
        CodeType identifierCodeType = CodeTypeImpl.getByType("COMMAND_ELEMENT_IDENTIFIER_TYPE");
        List<Code> identifierCodes = CodeImpl.getByType(identifierCodeType.codeTypeId);
        for (int i = 0; i < identifierCodes.Count; i++) {
            ddlEditIdentificationType.Items.Add(new ListItem(identifierCodes[i].name, identifierCodes[i].codeId + ""));
        }
    }


    /**
     * Edit element
     */
    protected void Edit_Click(Object sender, EventArgs e) {
        CommandIdentifier commandIdentifier = new CommandIdentifier();
        commandIdentifier.commandElementId = this._commandIdentifierId;
        commandIdentifier.code.codeId = int.Parse(ddlEditIdentificationType.Text);
        commandIdentifier.value = txtEditIdentificationValue.Text;
        CommandIdentifierImpl.update(commandIdentifier);
    }


    /**
     * Delete element
     */
    protected void Delete_Click(Object sender, EventArgs e) {
        if (hdnDeleteType.Value == "commandIdentifier") {
            CommandIdentifierImpl.removeByCommandIdentifierId(int.Parse(hdnDeleteId.Value));
        }
    }
}
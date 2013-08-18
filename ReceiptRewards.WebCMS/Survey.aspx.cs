using ReceiptReward;
using ReceiptRewards.PCL.BusinessLayer;
using ReceiptRewards.PCL.Models;
using System;
using System.Collections.Generic;

public partial class Entity_Manager : System.Web.UI.Page {
    // Global Variables
    private int _surveyId = 0;
    private Survey _survey = null;
    private Company _company = null;
    private string _type = "survey";


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
        if (Request.QueryString["surveyId"] != null) { this._surveyId = int.Parse(Request.QueryString["surveyId"]); }
        this._survey = SurveyImpl.getBySurveyId(this._surveyId);
        this._company = CompanyImpl.getByCompanyId(this._survey.companyId);
        lblScripts.Text = "<script type=\"text/javascript\"> var bigId = " + this._surveyId + "; bigType = \"" + this._type + "\";</script>";
    }


    /**
     * Get the title of the page
     */
    private void getTitle() {
        lblTitle.Text = this._company.name + ": " + this._survey.name;
        txtEditName.Text = this._survey.name;
    }


    /**
     * Creates a new element
     */
    protected void Add_Click(Object sender, EventArgs e) {
        Revision revision = new Revision();
        revision.revisionId = 0;
        revision.active = chkAddActive.Checked;
        revision.dateCreated = DateTime.Now.ToUniversalTime();
        revision.question = new Question();
        revision.question.name = "Start Question";
        revision.question.form = new Form();
        revision.question.automation = new Automation();
        revision.revisionNumber = txtAddRevisionNumber.Text;
        revision.surveyId = this._survey.surveyId;
        RevisionImpl.create(revision);
    }



    /**
     * Edit element
     */
    protected void Edit_Click(Object sender, EventArgs e) {
        Survey surveyEdit = new Survey();
        surveyEdit.surveyId = int.Parse(hdnSaveId.Value);
        surveyEdit.name = txtEditName.Text;
        SurveyImpl.update(surveyEdit);
    }



    /**
     * Delete element
     */
    protected void Delete_Click(Object sender, EventArgs e) {
        if (hdnDeleteType.Value == "survey") {
            SurveyImpl.removeBySurveyId(int.Parse(hdnDeleteId.Value));
        } else if (hdnDeleteType.Value == "revision") {
            RevisionImpl.removeByRevisionId(int.Parse(hdnDeleteId.Value));
        }
    }



    public void getList() {
        // Instantiate Variables
        string html = "";
        int counter = 0, childrenCount = 0;
        List<Revision> list = RevisionImpl.getBySurveyId(this._survey.surveyId, 2);

        // Iterate through list
        foreach (Revision item in list) {

            // Instantiate Variables
            childrenCount = 0; //Survey.containsNumOfQuestionGroups(surveyID);

            html += "" +
                "<div style='position: absolute; top: " + (counter * 25) + "px; left: 0px; height: 50px; width: 960px;'>" +
                    "<div class='TableElementDiv' style='width: 200px;'>" + CommonUtils.trim(item.revisionNumber, 20) + "</div>" +
                    "<div class='TableElementDiv' style='width: 100px;'>" + item.active + "</div>" +
                    "<div class='TableElementDiv' style='width: 200px;'>" + item.dateCreated.ToShortDateString() + "</div>" +
                    "<div class='TableElementDiv' style='width: 200px;'>" +
                        "<a href='Revision.aspx?revisionId=" + item.revisionId + "'>Edit</a> | " +
                        "<a class='isALink' onclick='$(\"#dlgDelete\").dialog(\"open\"); $(\"#hdnDeleteId\").val(\"" + item.revisionId + "\"); $(\"#hdnDeleteType\").val(\"" + "revision" + "\");'>Delete</a>" +
                    "</div>" +
                "</div>";
            counter++;
        }
        // Add More to the text
        html = "<div id='movingObjectsHolder' style='position: relative; width: 960px; height: " + ((counter - 1) * 25) + "px;'>" + html + "</div>";
        html = "" +
                "<div class='TableElementDiv' style='width: 200px;'><strong>Revision Number</strong></div>" +
                "<div class='TableElementDiv' style='width: 100px;'><strong>Active</strong></div>" +
                "<div class='TableElementDiv' style='width: 200px;'><strong>Date Created</strong></div>" +
                "<div class='TableElementDiv' style='width: 200px;'><strong>Options</strong></div>" + html;

        // Display Text
        lblList.Text = html;
    }

}
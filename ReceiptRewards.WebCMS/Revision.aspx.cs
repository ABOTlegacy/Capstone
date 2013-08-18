using ReceiptReward;
using ReceiptRewards.PCL.BusinessLayer;
using ReceiptRewards.PCL.Models;
using System;
using System.Collections.Generic;

public partial class Entity_Manager : System.Web.UI.Page {
    // Global Variables
    private int _revisionId = 0;
    private List<Question> _questions = null;
    private Revision _revision = null;
    private Survey _survey = null;
    private Company _company = null;
    private string _type = "revision";


    
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
        if (Request.QueryString["revisionId"] != null) { this._revisionId = int.Parse(Request.QueryString["revisionId"]); }
        this._questions = QuestionImpl.getByRevisionId(this._revisionId, 2);
        this._revision = RevisionImpl.getByRevisionId(this._revisionId, 3);
        this._survey = SurveyImpl.getBySurveyId(this._revision.surveyId);
        this._company = CompanyImpl.getByCompanyId(this._survey.companyId);
        lblScripts.Text = "<script type=\"text/javascript\"> var bigId = " + this._revisionId + "; bigType = \"" + this._type + "\";</script>";
    }



    /**
     * Get the title of the page
     */
    private void getTitle() {
        lblTitle.Text = this._company.name + ": " + this._survey.name + " v" + this._revision.revisionNumber;
        txtEditRevisionNumber.Text = this._revision.revisionNumber;
        chkEditActive.Checked = this._revision.active;
    }



    /**
     * Creates a new element
     */
    protected void Add_Question_Click(Object sender, EventArgs e) {
        Question question = new Question();
        question.name = txtAddQuestionName.Text;
        question.revisionId = this._revisionId;
        question.form = new Form();
        question.automation = new Automation();
        QuestionImpl.create(question);
    }



    /**
     * Links two question elements
     */
    protected void Link_Question_Click(Object sender, EventArgs e) {
        if(ddlAction.SelectedValue.Equals("Form")) {
            Form form = FormImpl.getByQuestionId(int.Parse(txtLinkQuestionStart.Text), 2);
            FormFlow formFlow = new FormFlow();
            formFlow.formId = form.formId;
            formFlow.question = new Question();
            formFlow.question.questionId = int.Parse(txtLinkQuestionEnd.Text);
            formFlow.value = txtLinkValueWeight.Text;
            FormFlowImpl.create(formFlow);
        } else if(ddlAction.SelectedValue.Equals("Automation")) {
            Automation automation = AutomationImpl.getByQuestionId(int.Parse(txtLinkQuestionStart.Text), 2);
            AutomationFlow automationFlow = new AutomationFlow();
            automationFlow.automationId = automation.automationId;
            automationFlow.question = new Question();
            automationFlow.question.questionId = int.Parse(txtLinkQuestionEnd.Text);
            automationFlow.weight = int.Parse(txtLinkValueWeight.Text);
            AutomationFlowImpl.create(automationFlow);
        }
    }



    /**
     * Edit element
     */
    protected void Edit_Click(Object sender, EventArgs e) {
        Revision revision = new Revision();
        revision.revisionId = int.Parse(hdnSaveId.Value);
        revision.active = chkEditActive.Checked;
        revision.revisionNumber = txtEditRevisionNumber.Text;
        RevisionImpl.update(revision);
    }



    /**
     * Delete element
     */
    protected void Delete_Click(Object sender, EventArgs e) {
        if (hdnDeleteType.Value == "revision") {
            RevisionImpl.removeByRevisionId(int.Parse(hdnDeleteId.Value));
        } else if (hdnDeleteType.Value == "question") {
            QuestionImpl.removeByQuestionId(int.Parse(hdnDeleteId.Value));
        }
    }



    /**
     * Automated Test element
     */
    protected void Automated_Test_Click(Object sender, EventArgs e) {
        if (hdnAutomatedTestType.Value == "revision") {
            RevisionImpl.performAutomatedTest(int.Parse(hdnAutomatedTestId.Value));
        }
    }



    /**
     * List
     */
    public void getList() {
        // Instantiate Variables
        string html = "";
        int counter = 0;

        // Iterate through list
        foreach (Question question in this._questions) {
            html += "" +
                "<div style='position: absolute; top: " + (counter * 25) + "px; left: 0px; height: 50px; width: 960px;'>" +
                    "<div class='TableElementDiv' style='width: 25px;'>" + question.questionId + "</div>" +
                    "<div class='TableElementDiv' style='width: 400px;'>" + CommonUtils.trim(question.name, 30) + "</div>" +
                    "<div class='TableElementDiv' style='width: 100px;'>" + string.Join(", ", FormFlowImpl.getByFormId(question.form.formId, 2).ConvertAll(formFlow => (formFlow.question.questionId + "")).ToArray()) + "</div>" +
                    "<div class='TableElementDiv' style='width: 100px;'>" + string.Join(", ", AutomationFlowImpl.getByAutomationId(question.automation.automationId, 2).ConvertAll(automationFlow => (automationFlow.question.questionId + "")).ToArray()) + "</div>" +
                    "<div class='TableElementDiv' style='width: 200px;'>" +
                        "<a href='Question.aspx?questionId=" + question.questionId + "&revisionId=" + question.revisionId + "'>Edit</a> | " +
                        "<a class='isALink' onclick='$(\"#dlgDelete\").dialog(\"open\"); $(\"#hdnDeleteId\").val(\"" + question.questionId + "\"); $(\"#hdnDeleteType\").val(\"" + "question" + "\");'>Delete</a>" +
                    "</div>" +
                "</div>";
            counter++;
        }
        // Add More to the text
        html = "<div id='movingObjectsHolder' style='position: relative; width: 960px; height: " + ((counter - 1) * 25) + "px;'>" + html + "</div>";
        html = "" +
                "<div class='TableElementDiv' style='width: 25px;'><strong>ID</strong></div>" +
                "<div class='TableElementDiv' style='width: 400px;'><strong>Name</strong></div>" +
                "<div class='TableElementDiv' style='width: 100px;'><strong>Frm Ref</strong></div>" +
                "<div class='TableElementDiv' style='width: 100px;'><strong>Cmd Ref</strong></div>" +
                "<div class='TableElementDiv' style='width: 200px;'><strong>Options</strong></div>" + html;

        // Display Text
        lblList.Text = html;
    }

}
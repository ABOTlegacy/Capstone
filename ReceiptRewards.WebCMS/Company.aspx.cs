using ReceiptReward;
using ReceiptRewards.PCL.BusinessLayer;
using ReceiptRewards.PCL.Models;
using System;
using System.Collections.Generic;

public partial class Entity_Manager : System.Web.UI.Page {
    // Global Variables
    private int _companyId = 0;
    private Company _company = null;
    private string _type = "company";

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
        if (Request.QueryString["companyId"] != null) { this._companyId = int.Parse(Request.QueryString["companyId"]); }
        this._company = CompanyImpl.getByCompanyId(this._companyId);
        lblScripts.Text = "<script type=\"text/javascript\"> var bigId = " + this._companyId + "; bigType = \"" + this._type + "\";</script>";
    }


    /**
     * Get the title of the page
     */
    private void getTitle() {
        lblTitle.Text = this._company.name;
        txtEditName.Text = this._company.name;
    }


    /**
     * Creates a new element
     */
    protected void Add_Click(Object sender, EventArgs e) {
        Survey survey = new Survey();
        survey.surveyId = 0;
        survey.companyId = this._companyId;
        survey.name = txtAddName.Text;
        SurveyImpl.create(survey);
    }


    /**
     * Edit element
     */
    protected void Edit_Click(Object sender, EventArgs e) {
        Company company = new Company();
        company.companyId = this._companyId;
        company.name = txtEditName.Text;
        CompanyImpl.update(company);
    }


    /**
     * Delete element
     */
    protected void Delete_Click(Object sender, EventArgs e) {
        if (hdnDeleteType.Value == "company") {
            CompanyImpl.removeByCompanyId(int.Parse(hdnDeleteId.Value));
        } else if (hdnDeleteType.Value == "survey") {
            SurveyImpl.removeBySurveyId(int.Parse(hdnDeleteId.Value));
        }
    }


    public void getList() {
        // Instantiate Variables
        string html = "";
        int counter = 0, childrenCount = 0;
        List<Survey> surveys = SurveyImpl.getByCompanyId(this._company.companyId);

        // Iterate through list
        foreach (Survey survey in surveys) {

            // Instantiate Variables
            childrenCount = 0; //Survey.containsNumOfQuestionGroups(surveyID);

            html += "" +
                "<div style='position: absolute; top: " + (counter * 25) + "px; left: 0px; height: 50px; width: 960px;'>" +
                    "<div class='TableElementDiv' style='width: 400px;'>" + CommonUtils.trim(survey.name, 30) + "</div>" +
                    "<div class='TableElementDiv' style='width: 50px;'>" + childrenCount + "</div>" +
                    "<div class='TableElementDiv' style='width: 200px;'>" +
                        "<a href='Survey.aspx?surveyId=" + survey.surveyId + "'>Edit</a> | " +
                        "<a class='isALink' onclick='$(\"#dlgDelete\").dialog(\"open\"); $(\"#hdnDeleteId\").val(\"" + survey.surveyId + "\"); $(\"#hdnDeleteType\").val(\"" + "survey" + "\");'>Delete</a>" +
                    "</div>" +
                "</div>";
            counter++;
        }
        // Add More to the text
        html = "<div id='movingObjectsHolder' style='position: relative; width: 960px; height: " + ((counter - 1) * 25) + "px;'>" + html + "</div>";
        html = "" +
                "<div class='TableElementDiv' style='width: 400px;'><strong>Name</strong></div>" +
                "<div class='TableElementDiv' style='width: 50px;'><strong>#</strong></div>" +
                "<div class='TableElementDiv' style='width: 200px;'><strong>Options</strong></div>" + html;

        // Display Text
        lblList.Text = html;
    }

}
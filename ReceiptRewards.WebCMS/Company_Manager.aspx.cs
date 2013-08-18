using ReceiptReward;
using ReceiptRewards.PCL.BusinessLayer;
using ReceiptRewards.PCL.Models;
using System;
using System.Collections.Generic;

public partial class Entity_Manager : System.Web.UI.Page {

    protected void Page_Load(object sender, EventArgs e) {
        // Call Methods
        getTitle();
        getList();
    }


    private void getTitle() {
        lblTitle.Text = "Company Manager";
    }


    /**
     * Creates a new Company
     */
    protected void Add_Click(Object sender, EventArgs e) {
        Company company = new Company();
        company.companyId = 0;
        company.name = txtName.Text;
        CompanyImpl.create(company);
    }


    /**
     * Deletes a Company
     */
    protected void Delete_Click(Object sender, EventArgs e){
        CompanyImpl.removeByCompanyId(int.Parse(hdnDeleteId.Value));
    }


    public void getList() {
        // Instantiate Variables
        string html = "";
        int counter = 0, childrenCount = 0;
        List<Company> companies = CompanyImpl.getAll();

        // Iterate through list
        foreach (Company company in companies) {

            // Instantiate Variables
            childrenCount = 0; //Survey.containsNumOfQuestionGroups(surveyID);

            html += "" +
                "<div style='position: absolute; top: " + (counter * 25) + "px; left: 0px; height: 50px; width: 960px;'>" +
                    "<div class='TableElementDiv' style='width: 400px;'>" + CommonUtils.trim(company.name, 30) + "</div>" +
                    "<div class='TableElementDiv' style='width: 50px;'>" + childrenCount + "</div>" +
                    "<div class='TableElementDiv' style='width: 200px;'>" +
                        "<a href='Company.aspx?companyId=" + company.companyId + "'>Edit</a> | " +
                        "<a class='isALink' onclick='$(\"#dlgDelete\").dialog(\"open\"); $(\"#hdnDeleteId\").val(\"" + company.companyId + "\");'>Delete</a>" +
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
using System;
using System.Collections.Generic;
using System.Web;
using System.Configuration;
using System.Data.SqlClient;
using System.Net;
using System.IO;
using Newtonsoft.Json.Linq;
using System.Diagnostics;
using ReceiptRewards.PCL.Models;
using ReceiptRewards.PCL.ServiceAccessLayer.Database.Implementations;
using ReceiptRewards.PCL.BusinessLayer;


namespace ReceiptReward {

    public class AutomationImpl {

        /**
         * Obtains a Entry by Id from the database
         * Calls webservice
         * @return A Survey object
         */
        public static Automation getByAutomationId(int automationId) {
            // Instantiate Variables
            Automation automation = new Automation();

            // Set up Request
            String url = AutomationServiceImpl.GET_BY_AUTOMATION_ID + automationId;
            HttpWebRequest httpWebRequest = (HttpWebRequest) WebRequest.Create(url);
            httpWebRequest.ContentType = "application/json";
            httpWebRequest.Accept = "application/json";
            httpWebRequest.Method = "GET";

            // Get the Response
            HttpWebResponse httpResponse = (HttpWebResponse) httpWebRequest.GetResponse();
            using (StreamReader streamReader = new StreamReader(httpResponse.GetResponseStream())) {
                automation = CommonUtils.deserialize<Automation>(streamReader.ReadToEnd());
            }

            // Return
            return automation;
        }
        public static Automation getByAutomationId(int automationId, int recursiveLevel) {
            // Instantiate Variables
            Automation automation = new Automation();

            // Set up Request
            String url = AutomationServiceImpl.GET_BY_AUTOMATION_ID + automationId + "/" + recursiveLevel;
            HttpWebRequest httpWebRequest = (HttpWebRequest) WebRequest.Create(url);
            httpWebRequest.ContentType = "application/json";
            httpWebRequest.Accept = "application/json";
            httpWebRequest.Method = "GET";

            // Get the Response
            HttpWebResponse httpResponse = (HttpWebResponse) httpWebRequest.GetResponse();
            using (StreamReader streamReader = new StreamReader(httpResponse.GetResponseStream())) {
                automation = CommonUtils.deserialize<Automation>(streamReader.ReadToEnd());
            }

            // Return
            return automation;
        }



        /**
         * Obtains a Entry by Id from the database
         * Calls webservice
         * @return A Survey object
         */
        public static Automation getByQuestionId(int questionId) {
            // Instantiate Variables
            Automation automation = new Automation();

            // Set up Request
            String url = AutomationServiceImpl.GET_BY_QUESTION_ID + questionId;
            HttpWebRequest httpWebRequest = (HttpWebRequest) WebRequest.Create(url);
            httpWebRequest.ContentType = "application/json";
            httpWebRequest.Accept = "application/json";
            httpWebRequest.Method = "GET";

            // Get the Response
            HttpWebResponse httpResponse = (HttpWebResponse) httpWebRequest.GetResponse();
            using (StreamReader streamReader = new StreamReader(httpResponse.GetResponseStream()))
            {
                automation = CommonUtils.deserialize<Automation>(streamReader.ReadToEnd());
            }

            // Return
            return automation;
        }
        public static Automation getByQuestionId(int questionId, int recursiveLevel) {
            // Instantiate Variables
            Automation automation = new Automation();

            // Set up Request
            String url = AutomationServiceImpl.GET_BY_QUESTION_ID + questionId + "/" + recursiveLevel;
            HttpWebRequest httpWebRequest = (HttpWebRequest) WebRequest.Create(url);
            httpWebRequest.ContentType = "application/json";
            httpWebRequest.Accept = "application/json";
            httpWebRequest.Method = "GET";

            // Get the Response
            HttpWebResponse httpResponse = (HttpWebResponse) httpWebRequest.GetResponse();
            using (StreamReader streamReader = new StreamReader(httpResponse.GetResponseStream())) {
                automation = CommonUtils.deserialize<Automation>(streamReader.ReadToEnd());
            }

            // Return
            return automation;
        }



        /**
         * Obtains a Entry by Id from the database
         * Calls webservice
         * @return A Survey object
         */
        public static Automation getByRevisionId(int revisionId) {
            // Instantiate Variables
            Automation automation = new Automation();

            // Set up Request
            String url = AutomationServiceImpl.GET_BY_REVISION_ID + revisionId;
            HttpWebRequest httpWebRequest = (HttpWebRequest) WebRequest.Create(url);
            httpWebRequest.ContentType = "application/json";
            httpWebRequest.Accept = "application/json";
            httpWebRequest.Method = "GET";

            // Get the Response
            HttpWebResponse httpResponse = (HttpWebResponse) httpWebRequest.GetResponse();
            using (StreamReader streamReader = new StreamReader(httpResponse.GetResponseStream())) {
                automation = CommonUtils.deserialize<Automation>(streamReader.ReadToEnd());
            }

            // Return
            return automation;
        }
        public static Automation getByRevisionId(int revisionId, int recursiveLevel) {
            // Instantiate Variables
            Automation automation = new Automation();

            // Set up Request
            String url = AutomationServiceImpl.GET_BY_REVISION_ID + revisionId + "/" + recursiveLevel;
            HttpWebRequest httpWebRequest = (HttpWebRequest) WebRequest.Create(url);
            httpWebRequest.ContentType = "application/json";
            httpWebRequest.Accept = "application/json";
            httpWebRequest.Method = "GET";

            // Get the Response
            HttpWebResponse httpResponse = (HttpWebResponse) httpWebRequest.GetResponse();
            using (StreamReader streamReader = new StreamReader(httpResponse.GetResponseStream()))
            {
                automation = CommonUtils.deserialize<Automation>(streamReader.ReadToEnd());
            }

            // Return
            return automation;
        }



        /**
         * Get the breadcrumbs
         */
        static public string getBreadcrumbs(int id) {
            // Instantiate Variables
            string tempStr = "";
            /*Automation automation = AutomationImpl.getByAutomationId(id);
            Question question = QuestionImpl.getByQuestionId(id);
            Revision revision = RevisionImpl.getById(automation.automationId);
            Survey survey = SurveyImpl.getBySurveyId(revision.surveyId);
            Company company = CompanyImpl.getByCompanyId(survey.companyId);

            // Automation
            tempStr = "<a class='breadcrumbCurrent' href='Automation.aspx?automationId=" + automation.automationId + "'>" + "Automation" + "</a>" + tempStr;

            // Revision
            tempStr = "<a class='breadcrumbCurrent' href='Revision.aspx?revisionId=" + revision.revisionId + "'>" + revision.revisionNumber + "</a>" + tempStr;

            // Survey
            tempStr = "<a class='breadcrumbCurrent' href='Survey.aspx?surveyId=" + survey.surveyId + "'>" + survey.name + "</a> >> " + tempStr;

            // Company
            tempStr = "<a class='breadcrumbNotCurrent' href='Company.aspx?companyId=" + company.companyId + "'>" + company.name + "</a> >> " + tempStr;

            // Homepage
            tempStr = "<a class='breadcrumbNotCurrent' href='Index.aspx'>Home</a> >> " + tempStr;*/

            // Output Links
            return tempStr;
        }
        
    }
}
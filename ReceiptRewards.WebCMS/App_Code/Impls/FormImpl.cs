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
using ReceiptRewards.PCL.BusinessLayer;
using ReceiptRewards.Core.ServiceAccessLayer.Database.Implementations;


namespace ReceiptReward {

    public class FormImpl {
        
        /**
         * Obtains a Entry by Id from the database
         * Calls webservice
         * @return A Survey object
         */
        public static Form getByFormId(int formId) {
            // Instantiate Variables
            Form form = new Form();

            // Set up Request
            String url = FormServiceImpl.GET_BY_FORM_ID + formId;
            HttpWebRequest httpWebRequest = (HttpWebRequest) WebRequest.Create(url);
            httpWebRequest.ContentType = "application/json";
            httpWebRequest.Accept = "application/json";
            httpWebRequest.Method = "GET";

            // Get the Response
            HttpWebResponse httpResponse = (HttpWebResponse) httpWebRequest.GetResponse();
            using (StreamReader streamReader = new StreamReader(httpResponse.GetResponseStream())) {
                form = CommonUtils.deserialize<Form>(streamReader.ReadToEnd());
            }

            // Return
            return form;
        }
        public static Form getByFormId(int formId, int recursiveLevel) {
            // Instantiate Variables
            Form form = new Form();

            // Set up Request
            String url = FormServiceImpl.GET_BY_FORM_ID + formId + "/" + recursiveLevel;
            HttpWebRequest httpWebRequest = (HttpWebRequest) WebRequest.Create(url);
            httpWebRequest.ContentType = "application/json";
            httpWebRequest.Accept = "application/json";
            httpWebRequest.Method = "GET";

            // Get the Response
            HttpWebResponse httpResponse = (HttpWebResponse) httpWebRequest.GetResponse();
            using (StreamReader streamReader = new StreamReader(httpResponse.GetResponseStream())) {
                form = CommonUtils.deserialize<Form>(streamReader.ReadToEnd());
            }

            // Return
            return form;
        }



        /**
         * Obtains a Entry by Id from the database
         * Calls webservice
         * @return A Form object
         */
        public static Form getByQuestionId(int questionId) {
            // Instantiate Variables
            Form form = new Form();

            // Set up Request
            String url = FormServiceImpl.GET_BY_QUESTION_ID + questionId;
            HttpWebRequest httpWebRequest = (HttpWebRequest) WebRequest.Create(url);
            httpWebRequest.ContentType = "application/json";
            httpWebRequest.Accept = "application/json";
            httpWebRequest.Method = "GET";

            // Get the Response
            HttpWebResponse httpResponse = (HttpWebResponse) httpWebRequest.GetResponse();
            using (StreamReader streamReader = new StreamReader(httpResponse.GetResponseStream())) {
                form = CommonUtils.deserialize<Form>(streamReader.ReadToEnd());
            }

            // Return
            return form;
        }
        public static Form getByQuestionId(int questionId, int recursiveLevel) {
            // Instantiate Variables
            Form form = new Form();

            // Set up Request
            String url = FormServiceImpl.GET_BY_QUESTION_ID + questionId + "/" + recursiveLevel;
            HttpWebRequest httpWebRequest = (HttpWebRequest) WebRequest.Create(url);
            httpWebRequest.ContentType = "application/json";
            httpWebRequest.Accept = "application/json";
            httpWebRequest.Method = "GET";

            // Get the Response
            HttpWebResponse httpResponse = (HttpWebResponse) httpWebRequest.GetResponse();
            using (StreamReader streamReader = new StreamReader(httpResponse.GetResponseStream())) {
                form = CommonUtils.deserialize<Form>(streamReader.ReadToEnd());
            }

            // Return
            return form;
        }



        /**
         * Obtains a Entry by Id from the database
         * Calls webservice
         * @return A Survey object
         */
        public static Form getByRevisionId(int revisionId) {
            // Instantiate Variables
            Form form = new Form();

            // Set up Request
            String url = FormServiceImpl.GET_BY_REVISION_ID + revisionId;
            HttpWebRequest httpWebRequest = (HttpWebRequest) WebRequest.Create(url);
            httpWebRequest.ContentType = "application/json";
            httpWebRequest.Accept = "application/json";
            httpWebRequest.Method = "GET";

            // Get the Response
            HttpWebResponse httpResponse = (HttpWebResponse) httpWebRequest.GetResponse();
            using (StreamReader streamReader = new StreamReader(httpResponse.GetResponseStream())) {
                form = CommonUtils.deserialize<Form>(streamReader.ReadToEnd());
            }

            // Return
            return form;
        }
        public static Form getByRevisionId(int revisionId, int recursiveLevel) {
            // Instantiate Variables
            Form form = new Form();

            // Set up Request
            String url = FormServiceImpl.GET_BY_REVISION_ID + revisionId + "/" + recursiveLevel;
            HttpWebRequest httpWebRequest = (HttpWebRequest) WebRequest.Create(url);
            httpWebRequest.ContentType = "application/json";
            httpWebRequest.Accept = "application/json";
            httpWebRequest.Method = "GET";

            // Get the Response
            HttpWebResponse httpResponse = (HttpWebResponse) httpWebRequest.GetResponse();
            using (StreamReader streamReader = new StreamReader(httpResponse.GetResponseStream())) {
                form = CommonUtils.deserialize<Form>(streamReader.ReadToEnd());
            }

            // Return
            return form;
        }



        /**
         * Update entry in the Database
         * Calls webservice
         */
        /**public static void updateWeight(Form form) {
            // Set up Request
            HttpWebRequest httpWebRequest = (HttpWebRequest) WebRequest.Create(FormServiceImpl.UPDATE_WEIGHT);
            httpWebRequest.ContentType = "application/json";
            httpWebRequest.Method = "PUT";

            // Send Request Data
            using (StreamWriter streamWriter = new StreamWriter(httpWebRequest.GetRequestStream())) {
                string json = CommonUtils.serialize(form);
                Debug.WriteLine(json);
                streamWriter.Write(json);
                streamWriter.Flush();
                streamWriter.Close();
            }

            // Get the Response
            HttpWebResponse httpResponse = (HttpWebResponse) httpWebRequest.GetResponse();
        }**/



        /**
         * Get the breadcrumbs
         */
        static public string getBreadcrumbs(int id) {
            // Instantiate Variables
            string tempStr = "";
            /*Form form = FormImpl.getByFormId(id);
            Revision revision = RevisionImpl.getByFormId(form.formId);
            Survey survey = SurveyImpl.getBySurveyId(revision.surveyId);
            Company company = CompanyImpl.getByCompanyId(survey.companyId);

            // Form
            tempStr = "<a class='breadcrumbCurrent' href='Form.aspx?formId=" + form.formId + "'>" + "Form" + "</a>" + tempStr;

            // Revision
            tempStr = "<a class='breadcrumbCurrent' href='Revision.aspx?revisionId=" + revision.revisionId + "'>" + revision.revisionNumber + "</a>" + tempStr;

            // Survey
            tempStr = "<a class='breadcrumbCurrent' href='Survey.aspx?surveyId=" + survey.surveyId + "'>" + survey.name + "</a> >> " + tempStr;

            // Company
            tempStr = "<a class='breadcrumbNotCurrent' href='Company.aspx?companyId=" + company.companyId + "'>" + company.name + "</a> >> " + tempStr;

            // Homepage
            tempStr = "<a class='breadcrumbNotCurrent' href='Index.aspx'>Home</a> >> " + tempStr;
            */
            // Output Links
            return tempStr;
        }

    }
}
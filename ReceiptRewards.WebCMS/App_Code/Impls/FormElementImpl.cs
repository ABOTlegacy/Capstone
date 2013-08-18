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
using ReceiptRewards.PCL.ServiceAccessLayer.Database.Implementations;


namespace ReceiptReward {

    public class FormElementImpl {
        
        /**
         * Obtains a CommandElement by Automation Id from the database
         * Calls webservice
         * @return A CommandElement object
         */
        public static List<FormElement> getByFormId(int formId) {
            // Instantiate Variables
            List<FormElement> formElements = new List<FormElement>();

            // Set up Request
            HttpWebRequest httpWebRequest = (HttpWebRequest) WebRequest.Create(FormElementServiceImpl.GET_BY_FORM_ID + formId);
            httpWebRequest.ContentType = "application/json";
            httpWebRequest.Accept = "application/json";
            httpWebRequest.Method = "GET";

            // Get the Response
            HttpWebResponse httpResponse = (HttpWebResponse) httpWebRequest.GetResponse();
            using (StreamReader streamReader = new StreamReader(httpResponse.GetResponseStream())) {
                formElements = CommonUtils.deserialize<List<FormElement>>(streamReader.ReadToEnd());
            }

            // Return
            return formElements;
        }



        /**
         * Obtains a Element by Id from the database
         * Calls webservice
         * @return A CommandElement object
         */
        public static FormElement getByFormElementId(int formElementId) {
            // Instantiate Variables
            FormElement formElement = new FormElement();

            // Set up Request
            HttpWebRequest httpWebRequest = (HttpWebRequest) WebRequest.Create(FormElementServiceImpl.GET_BY_FORM_ELEMENT_ID + formElementId);
            httpWebRequest.ContentType = "application/json";
            httpWebRequest.Accept = "application/json";
            httpWebRequest.Method = "GET";

            // Get the Response
            HttpWebResponse httpResponse = (HttpWebResponse) httpWebRequest.GetResponse();
            using (StreamReader streamReader = new StreamReader(httpResponse.GetResponseStream())) {
                formElement = CommonUtils.deserialize<FormElement>(streamReader.ReadToEnd());
            }

            // Return
            return formElement;
        }



        /**
         * Create a new entry in the Database
         * Calls webservice
         */
        public static void create(FormElement formElement, int formId) {
            // Set up Request
            HttpWebRequest httpWebRequest = (HttpWebRequest) WebRequest.Create(FormElementServiceImpl.CREATE + formId);
            httpWebRequest.ContentType = "application/json";
            httpWebRequest.Accept = "application/json";
            httpWebRequest.Method = "POST";

            // Send Request Data
            using (StreamWriter streamWriter = new StreamWriter(httpWebRequest.GetRequestStream())) {
                string json = CommonUtils.serialize(formElement);
                Debug.WriteLine(json);
                streamWriter.Write(json);
                streamWriter.Flush();
                streamWriter.Close();
            }

            // Get the Response
            HttpWebResponse httpResponse = (HttpWebResponse) httpWebRequest.GetResponse();
        }
        public static void createFromExisting(int formElementId, int formId) {
            // Set up Request
            HttpWebRequest httpWebRequest = (HttpWebRequest) WebRequest.Create(FormElementServiceImpl.CREATE_FROM_EXISTING + formId + "/" + formElementId);
            httpWebRequest.ContentType = "application/json";
            httpWebRequest.Accept = "application/json";
            httpWebRequest.Method = "POST";

            // Get the Response
            HttpWebResponse httpResponse = (HttpWebResponse) httpWebRequest.GetResponse();
        }



        /**
         * Update entry in the Database
         * Calls webservice
         */
        public static void update(FormElement formElement) {
            // Set up Request
            HttpWebRequest httpWebRequest = (HttpWebRequest) WebRequest.Create(FormElementServiceImpl.UPDATE);
            httpWebRequest.ContentType = "application/json";
            httpWebRequest.Accept = "application/json";
            httpWebRequest.Method = "PUT";

            // Send Request Data
            using (StreamWriter streamWriter = new StreamWriter(httpWebRequest.GetRequestStream())) {
                string json = CommonUtils.serialize(formElement);
                Debug.WriteLine(json);
                streamWriter.Write(json);
                streamWriter.Flush();
                streamWriter.Close();
            }

            // Get the Response
            HttpWebResponse httpResponse = (HttpWebResponse) httpWebRequest.GetResponse();
        }



        /**
         * Removes an entry in the Database by CommandElement Id
         * Calls webservice
         */
        public static void removeByFormElementId(int formElementId) {
            // Set up Request
            HttpWebRequest httpWebRequest = (HttpWebRequest) WebRequest.Create(FormElementServiceImpl.REMOVE_BY_FORM_ELEMENT_ID + formElementId);
            httpWebRequest.ContentType = "application/json";
            httpWebRequest.Accept = "application/json";
            httpWebRequest.Method = "DELETE";

            // Get the Response
            HttpWebResponse httpResponse = (HttpWebResponse) httpWebRequest.GetResponse();
        }



        /**
         * Get the breadcrumbs
         */
        static public string getBreadcrumbs(int id, int id2) {
            // Instantiate Variables
            string tempStr = "";
            FormElement formElement = FormElementImpl.getByFormElementId(id);
            Revision revision = RevisionImpl.getByRevisionId(id2, 1);
            Survey survey = SurveyImpl.getBySurveyId(revision.surveyId);
            Company company= CompanyImpl.getByCompanyId(survey.companyId);

            // CommandElement
            tempStr = "<a class='breadcrumbCurrent' href='FormElement.aspx?formElementId=" + formElement.formElementId + "&revisionId=" + revision.revisionId + "'>" + CodeImpl.getByCodeId(formElement.code.codeId).code + "</a>" + tempStr;

            // Revision
            tempStr = "<a class='breadcrumbNotCurrent' href='Revision.aspx?revisionId=" + revision.revisionId + "'>" + revision.revisionId + "</a> >> " + tempStr;

            // Survey
            tempStr = "<a class='breadcrumbNotCurrent' href='Survey.aspx?surveyId=" + survey.surveyId + "'>" + survey.name + "</a> >> " + tempStr;

            // Company
            tempStr = "<a class='breadcrumbNotCurrent' href='Company.aspx?companyId=" + company.companyId + "'>" + company.name + "</a> >> " + tempStr;

            // Homepage
            tempStr = "<a class='breadcrumbNotCurrent' href='Index.aspx'>Home</a> >> " + tempStr;

            // Output Links
            return tempStr;
        }

    }
}
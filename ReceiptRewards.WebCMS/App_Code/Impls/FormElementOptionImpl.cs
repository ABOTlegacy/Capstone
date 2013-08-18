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

    public class FormElementOptionImpl {
        
        /**
         * Obtains a FormElementAttribute by Id from the database
         * Calls webservice
         * @return A CommandElement object
         */
        public static List<FormElementOption> getByFormElementId(int formElementId) {
            // Instantiate Variables
            List<FormElementOption> formElementOptions = new List<FormElementOption>();

            // Set up Request
            HttpWebRequest httpWebRequest = (HttpWebRequest) WebRequest.Create(FormElementOptionServiceImpl.GET_BY_FORM_ELEMENT_ID + formElementId);
            httpWebRequest.ContentType = "application/json";
            httpWebRequest.Accept = "application/json";
            httpWebRequest.Method = "GET";

            // Get the Response
            HttpWebResponse httpResponse = (HttpWebResponse) httpWebRequest.GetResponse();
            using (StreamReader streamReader = new StreamReader(httpResponse.GetResponseStream())) {
                formElementOptions = CommonUtils.deserialize<List<FormElementOption>>(streamReader.ReadToEnd());
            }

            // Return
            return formElementOptions;
        }



        /**
         * Obtains a FormElementOption by Id from the database
         * Calls webservice
         * @return A CommandElement object
         */
        public static FormElementOption getByFormElementOptionId(int formElementOptionId) {
            // Instantiate Variables
            FormElementOption formElementOption = new FormElementOption();

            // Set up Request
            HttpWebRequest httpWebRequest = (HttpWebRequest) WebRequest.Create(FormElementOptionServiceImpl.GET_BY_FORM_ELEMENT_OPTION_ID + formElementOptionId);
            httpWebRequest.ContentType = "application/json";
            httpWebRequest.Accept = "application/json";
            httpWebRequest.Method = "GET";

            // Get the Response
            HttpWebResponse httpResponse = (HttpWebResponse) httpWebRequest.GetResponse();
            using (StreamReader streamReader = new StreamReader(httpResponse.GetResponseStream())) {
                formElementOption = CommonUtils.deserialize<FormElementOption>(streamReader.ReadToEnd());
            }

            // Return
            return formElementOption;
        }



        /**
         * Create a new entry in the Database
         * Calls webservice
         */
        public static void create(FormElementOption formElementOption, int formElementId) {
            // Set up Request
            HttpWebRequest httpWebRequest = (HttpWebRequest) WebRequest.Create(FormElementOptionServiceImpl.CREATE + formElementId);
            httpWebRequest.ContentType = "application/json";
            httpWebRequest.Accept = "application/json";
            httpWebRequest.Method = "POST";

            // Send Request Data
            using (StreamWriter streamWriter = new StreamWriter(httpWebRequest.GetRequestStream())) {
                string json = CommonUtils.serialize(formElementOption);
                Debug.WriteLine(json);
                streamWriter.Write(json);
                streamWriter.Flush();
                streamWriter.Close();
            }

            // Get the Response
            HttpWebResponse httpResponse = (HttpWebResponse) httpWebRequest.GetResponse();
        }



        /**
         * Update entry in the Database
         * Calls webservice
         */
        public static void update(FormElementOption formElementOption) {
            // Set up Request
            HttpWebRequest httpWebRequest = (HttpWebRequest) WebRequest.Create(FormElementOptionServiceImpl.UPDATE);
            httpWebRequest.ContentType = "application/json";
            httpWebRequest.Accept = "application/json";
            httpWebRequest.Method = "PUT";

            // Send Request Data
            using (StreamWriter streamWriter = new StreamWriter(httpWebRequest.GetRequestStream())) {
                string json = CommonUtils.serialize(formElementOption);
                Debug.WriteLine(json);
                streamWriter.Write(json);
                streamWriter.Flush();
                streamWriter.Close();
            }

            // Get the Response
            HttpWebResponse httpResponse = (HttpWebResponse) httpWebRequest.GetResponse();
        }



        /**
         * Removes an entry in the Database by Id
         * Calls webservice
         */
        public static void removeByFormElementOptionId(int formElementOptionId) {
            // Set up Request
            HttpWebRequest httpWebRequest = (HttpWebRequest) WebRequest.Create(FormElementOptionServiceImpl.REMOVE_BY_FORM_ELEMENT_OPTION_ID + formElementOptionId);
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
            FormElementOption formElementOption = FormElementOptionImpl.getByFormElementOptionId(id);
            FormElement formElement = FormElementImpl.getByFormElementId(formElementOption.formElementId);
            Revision revision = RevisionImpl.getByRevisionId(id2, 1);
            Survey survey = SurveyImpl.getBySurveyId(revision.surveyId);
            Company company = CompanyImpl.getByCompanyId(survey.companyId);

            // FormElementAttribute
            tempStr = "<a class='breadcrumbCurrent' href='FormElementOption.aspx?formElementOptionId=" + formElementOption.formElementOptionId + "&revisionId=" + revision.revisionId + "'>" + formElementOption.displayText.displayTextTranslation + "</a>" + tempStr;

            // FormElement
            tempStr = "<a class='breadcrumbNotCurrent' href='FormElement.aspx?formElementId=" + formElement.formElementId + "&revisionId=" + revision.revisionId + "'>" + formElement.code.code + "</a> >> " + tempStr;

            // Revision
            tempStr = "<a class='breadcrumbNotCurrent' href='Revision.aspx?revisionId=" + revision.revisionId + "'>" + revision.revisionNumber + "</a> >> " + tempStr;

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
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

    public class FormElementAttributeImpl {
        
        /**
         * Obtains a FormElementAttribute by Id from the database
         * Calls webservice
         * @return A CommandElement object
         */
        public static List<FormElementAttribute> getByFormElementId(int formElementId) {
            // Instantiate Variables
            List<FormElementAttribute> formElementAttributes = new List<FormElementAttribute>();

            // Set up Request
            HttpWebRequest httpWebRequest = (HttpWebRequest) WebRequest.Create(FormElementAttributeServiceImpl.GET_BY_FORM_ELEMENT_ID + formElementId);
            httpWebRequest.ContentType = "application/json";
            httpWebRequest.Accept = "application/json";
            httpWebRequest.Method = "GET";

            // Get the Response
            HttpWebResponse httpResponse = (HttpWebResponse) httpWebRequest.GetResponse();
            using (StreamReader streamReader = new StreamReader(httpResponse.GetResponseStream())) {
                formElementAttributes = CommonUtils.deserialize<List<FormElementAttribute>>(streamReader.ReadToEnd());
            }

            // Return
            return formElementAttributes;
        }



        /**
         * Obtains a FormElementAttribute by Id from the database
         * Calls webservice
         * @return A CommandElement object
         */
        public static FormElementAttribute getByFormElementAttributeId(int formElementAttributeId) {
            // Instantiate Variables
            FormElementAttribute formElementAttribute = new FormElementAttribute();

            // Set up Request
            HttpWebRequest httpWebRequest = (HttpWebRequest) WebRequest.Create(FormElementAttributeServiceImpl.GET_BY_FORM_ELEMENT_ATTRIBUTE_ID + formElementAttributeId);
            httpWebRequest.ContentType = "application/json";
            httpWebRequest.Accept = "application/json";
            httpWebRequest.Method = "GET";

            // Get the Response
            HttpWebResponse httpResponse = (HttpWebResponse) httpWebRequest.GetResponse();
            using (StreamReader streamReader = new StreamReader(httpResponse.GetResponseStream())) {
                formElementAttribute = CommonUtils.deserialize<FormElementAttribute>(streamReader.ReadToEnd());
            }

            // Return
            return formElementAttribute;
        }



        /**
         * Create a new entry in the Database
         * Calls webservice
         */
        public static void create(FormElementAttribute formElementAttribute, int formElementId) {
            // Set up Request
            HttpWebRequest httpWebRequest = (HttpWebRequest) WebRequest.Create(FormElementAttributeServiceImpl.CREATE + formElementId);
            httpWebRequest.ContentType = "application/json";
            httpWebRequest.Accept = "application/json";
            httpWebRequest.Method = "POST";

            // Send Request Data
            using (StreamWriter streamWriter = new StreamWriter(httpWebRequest.GetRequestStream())) {
                string json = CommonUtils.serialize(formElementAttribute);
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
        public static void update(FormElementAttribute formElementAttribute) {
            // Set up Request
            HttpWebRequest httpWebRequest = (HttpWebRequest) WebRequest.Create(FormElementAttributeServiceImpl.UPDATE);
            httpWebRequest.ContentType = "application/json";
            httpWebRequest.Accept = "application/json";
            httpWebRequest.Method = "PUT";

            // Send Request Data
            using (StreamWriter streamWriter = new StreamWriter(httpWebRequest.GetRequestStream())) {
                string json = CommonUtils.serialize(formElementAttribute);
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
        public static void removeByFormElementAttributeId(int formElementAttributeId) {
            // Set up Request
            HttpWebRequest httpWebRequest = (HttpWebRequest) WebRequest.Create(FormElementAttributeServiceImpl.REMOVE_BY_FORM_ELEMENT_ATTRIBUTE_ID + formElementAttributeId);
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
            /*FormElementAttribute formElementAttribute = FormElementAttributeImpl.getByFormElementAttributeId(id);
            FormElement formElement = FormElementImpl.getByFormElementId(id);
            Revision revision = RevisionImpl.getByAutomationId(id2);
            Survey survey = SurveyImpl.getBySurveyId(revision.surveyId);
            Company company= CompanyImpl.getByCompanyId(survey.companyId);

            // FormElementAttribute
            tempStr = "<a class='breadcrumbCurrent' href='FormElementAttribute.aspx?formElementAttributeId=" + formElementAttribute.formElementAttributeId + "&revisionId=" + revision.revisionId + "'>" + formElementAttribute.code.codeId + "</a>" + tempStr;

            // FormElement
            tempStr = "<a class='breadcrumbNotCurrent' href='FormElement.aspx?formElementId=" + formElement.formElementId + "&revisionId=" + revision.revisionId + "'>" + CodeImpl.getByCodeId(formElement.code.codeId).code + "</a> >> " + tempStr;

            // Revision
            tempStr = "<a class='breadcrumbNotCurrent' href='Revision.aspx?revisionId=" + revision.revisionId + "'>" + revision.revisionNumber + "</a> >> " + tempStr;

            // Survey
            tempStr = "<a class='breadcrumbNotCurrent' href='Survey.aspx?surveyId=" + survey.surveyId + "'>" + survey.name + "</a> >> " + tempStr;

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
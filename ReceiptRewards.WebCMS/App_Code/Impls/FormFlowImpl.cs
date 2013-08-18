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

    public class FormFlowImpl {

        /**
         * Obtains a list FormFlow in the database
         * Calls webservice
         * @return A list of FormFlow objects
         */
        public static FormFlow getByFormFlowId(int formFlowId) {
            // Instantiate Variables
            FormFlow formFlow = new FormFlow();

            // Set up Request
            String url = FormFlowServiceImpl.GET_BY_FORM_FLOW_ID + formFlowId;
            HttpWebRequest httpWebRequest = (HttpWebRequest) WebRequest.Create(url);
            httpWebRequest.ContentType = "application/json";
            httpWebRequest.Accept = "application/json";
            httpWebRequest.Method = "GET";
            
            // Get the Response
            HttpWebResponse httpResponse = (HttpWebResponse) httpWebRequest.GetResponse();
            using (StreamReader streamReader = new StreamReader(httpResponse.GetResponseStream())) {
                formFlow = CommonUtils.deserialize<FormFlow>(streamReader.ReadToEnd());
            }

            // Return
            return formFlow;
        }
        public static FormFlow getByFormFlowId(int formFlowId, int recursiveLevel) {
            // Instantiate Variables
            FormFlow formFlow = new FormFlow();

            // Set up Request
            String url = FormFlowServiceImpl.GET_BY_FORM_FLOW_ID + formFlowId + "/" + recursiveLevel;
            HttpWebRequest httpWebRequest = (HttpWebRequest) WebRequest.Create(url);
            httpWebRequest.ContentType = "application/json";
            httpWebRequest.Accept = "application/json";
            httpWebRequest.Method = "GET";

            // Get the Response
            HttpWebResponse httpResponse = (HttpWebResponse) httpWebRequest.GetResponse();
            using (StreamReader streamReader = new StreamReader(httpResponse.GetResponseStream())) {
                formFlow = CommonUtils.deserialize<FormFlow>(streamReader.ReadToEnd());
            }

            // Return
            return formFlow;
        }



        /**
         * Obtains a list of FormFlow by type in the database
         * Calls webservice
         * @return A list of FormFlow objects
         */
        public static List<FormFlow> getByFormId(int formId) {
            // Instantiate Variables
            List<FormFlow> formFlows = new List<FormFlow>();

            // Set up Request
            String url = FormFlowServiceImpl.GET_BY_FORM_ID + formId;
            HttpWebRequest httpWebRequest = (HttpWebRequest) WebRequest.Create(url);
            httpWebRequest.ContentType = "application/json";
            httpWebRequest.Accept = "application/json";
            httpWebRequest.Method = "GET";

            // Get the Response
            HttpWebResponse httpResponse = (HttpWebResponse) httpWebRequest.GetResponse();
            using (StreamReader streamReader = new StreamReader(httpResponse.GetResponseStream())) {
                formFlows = CommonUtils.deserialize<List<FormFlow>>(streamReader.ReadToEnd());
            }

            // Return
            return formFlows;
        }
        public static List<FormFlow> getByFormId(int formId, int recursiveLevel) {
            // Instantiate Variables
            List<FormFlow> formFlows = new List<FormFlow>();

            // Set up Request
            String url = FormFlowServiceImpl.GET_BY_FORM_ID + formId + "/" + recursiveLevel;
            HttpWebRequest httpWebRequest = (HttpWebRequest) WebRequest.Create(url);
            httpWebRequest.ContentType = "application/json";
            httpWebRequest.Accept = "application/json";
            httpWebRequest.Method = "GET";

            // Get the Response
            HttpWebResponse httpResponse = (HttpWebResponse) httpWebRequest.GetResponse();
            using (StreamReader streamReader = new StreamReader(httpResponse.GetResponseStream())) {
                formFlows = CommonUtils.deserialize<List<FormFlow>>(streamReader.ReadToEnd());
            }

            // Return
            return formFlows;
        }



        /**
         * Create a new FormFlow entry in the Database
         * Calls webservice
         */
        public static void create(FormFlow formFlow) {
            // Set up Request
            HttpWebRequest httpWebRequest = (HttpWebRequest) WebRequest.Create(FormFlowServiceImpl.CREATE);
            httpWebRequest.ContentType = "application/json";
            httpWebRequest.Accept = "application/json";
            httpWebRequest.Method = "POST";

            // Send Request Data
            using (StreamWriter streamWriter = new StreamWriter(httpWebRequest.GetRequestStream())) {
                string json = CommonUtils.serialize(formFlow);
                Debug.WriteLine(json);
                streamWriter.Write(json);
                streamWriter.Flush();
                streamWriter.Close();
            }

            // Get the Response
            HttpWebResponse httpResponse = (HttpWebResponse) httpWebRequest.GetResponse();
        }



        /**
         * Removes a FormFlow entry in the Database by Id
         * Calls webservice
         */
        public static void removeByFormFlowId(int formFlowId) {
            // Set up Request
            HttpWebRequest httpWebRequest = (HttpWebRequest) WebRequest.Create(FormFlowServiceImpl.DELETE_BY_FORM_FLOW_ID + formFlowId);
            httpWebRequest.ContentType = "application/json";
            httpWebRequest.Accept = "application/json";
            httpWebRequest.Method = "DELETE";

            // Get the Response
            HttpWebResponse httpResponse = (HttpWebResponse) httpWebRequest.GetResponse();
        }



        /**
         * Removes a FormFlow entry in the Database by Id
         * Calls webservice
         */
        public static void removeByFormId(int formId) {
            // Set up Request
            HttpWebRequest httpWebRequest = (HttpWebRequest) WebRequest.Create(FormFlowServiceImpl.DELETE_BY_FORM_ID + formId);
            httpWebRequest.ContentType = "application/json";
            httpWebRequest.Accept = "application/json";
            httpWebRequest.Method = "DELETE";

            // Get the Response
            HttpWebResponse httpResponse = (HttpWebResponse) httpWebRequest.GetResponse();
        }



        /**
         * Removes a AutomationFlow entry in the Database by Id
         * Calls webservice
         */
        public static void removeByQuestionId(int questionId) {
            // Set up Request
            HttpWebRequest httpWebRequest = (HttpWebRequest) WebRequest.Create(AutomationFlowServiceImpl.DELETE_BY_QUESTION_ID + questionId);
            httpWebRequest.ContentType = "application/json";
            httpWebRequest.Accept = "application/json";
            httpWebRequest.Method = "DELETE";

            // Get the Response
            HttpWebResponse httpResponse = (HttpWebResponse) httpWebRequest.GetResponse();
        }



        /**
         * Get the breadcrumbs
         */
        static public string getBreadcrumbs(int id) {
            // Instantiate Variables
            string tempStr = "";
            Code code = CodeImpl.getByCodeId(id);
            CodeType codeType = CodeTypeImpl.getByCodeTypeId(code.codeType.codeTypeId);

            // Output Links
            return tempStr;
        }

    }
}
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

    public class AutomationFlowImpl {

        /**
         * Obtains a list AutomationFlow in the database
         * Calls webservice
         * @return A list of AutomationFlow objects
         */
        public static AutomationFlow getByAutomationFlowId(int automationFlowId) {
            // Instantiate Variables
            AutomationFlow automationFlow = new AutomationFlow();

            // Set up Request
            String url = AutomationFlowServiceImpl.GET_BY_AUTOMATION_FLOW_ID + automationFlowId;
            HttpWebRequest httpWebRequest = (HttpWebRequest) WebRequest.Create(url);
            httpWebRequest.ContentType = "application/json";
            httpWebRequest.Accept = "application/json";
            httpWebRequest.Method = "GET";

            // Get the Response
            HttpWebResponse httpResponse = (HttpWebResponse) httpWebRequest.GetResponse();
            using (StreamReader streamReader = new StreamReader(httpResponse.GetResponseStream())) {
                automationFlow = CommonUtils.deserialize<AutomationFlow>(streamReader.ReadToEnd());
            }

            // Return
            return automationFlow;
        }
        public static AutomationFlow getByAutomationFlowId(int automationFlowId, int recursiveLevel) {
            // Instantiate Variables
            AutomationFlow automationFlow = new AutomationFlow();

            // Set up Request
            String url = AutomationFlowServiceImpl.GET_BY_AUTOMATION_FLOW_ID + automationFlowId + "/" + recursiveLevel;
            HttpWebRequest httpWebRequest = (HttpWebRequest) WebRequest.Create(url);
            httpWebRequest.ContentType = "application/json";
            httpWebRequest.Accept = "application/json";
            httpWebRequest.Method = "GET";
            
            // Get the Response
            HttpWebResponse httpResponse = (HttpWebResponse) httpWebRequest.GetResponse();
            using (StreamReader streamReader = new StreamReader(httpResponse.GetResponseStream())) {
                automationFlow = CommonUtils.deserialize<AutomationFlow>(streamReader.ReadToEnd());
            }

            // Return
            return automationFlow;
        }



        /**
         * Obtains a list of AutomationFlow by type in the database
         * Calls webservice
         * @return A list of AutomationFlow objects
         */
        public static List<AutomationFlow> getByAutomationId(int automationId) {
            // Instantiate Variables
            List<AutomationFlow> automationFlows = new List<AutomationFlow>();

            // Set up Request
            HttpWebRequest httpWebRequest = (HttpWebRequest) WebRequest.Create(AutomationFlowServiceImpl.GET_BY_AUTOMATION_ID + automationId);
            httpWebRequest.ContentType = "application/json";
            httpWebRequest.Accept = "application/json";
            httpWebRequest.Method = "GET";

            // Get the Response
            HttpWebResponse httpResponse = (HttpWebResponse) httpWebRequest.GetResponse();
            using (StreamReader streamReader = new StreamReader(httpResponse.GetResponseStream())) {
                automationFlows = CommonUtils.deserialize<List<AutomationFlow>>(streamReader.ReadToEnd());
            }

            // Return
            return automationFlows;
        }
        public static List<AutomationFlow> getByAutomationId(int automationId, int recursiveLevel) {
            // Instantiate Variables
            List<AutomationFlow> automationFlows = new List<AutomationFlow>();

            // Set up Request
            String url = AutomationFlowServiceImpl.GET_BY_AUTOMATION_ID + automationId + "/" + recursiveLevel;
            HttpWebRequest httpWebRequest = (HttpWebRequest) WebRequest.Create(url);
            httpWebRequest.ContentType = "application/json";
            httpWebRequest.Accept = "application/json";
            httpWebRequest.Method = "GET";

            // Get the Response
            HttpWebResponse httpResponse = (HttpWebResponse) httpWebRequest.GetResponse();
            using (StreamReader streamReader = new StreamReader(httpResponse.GetResponseStream())) {
                automationFlows = CommonUtils.deserialize<List<AutomationFlow>>(streamReader.ReadToEnd());
            }

            // Return
            return automationFlows;
        }



        /**
         * Create a new AutomationFlow entry in the Database
         * Calls webservice
         */
        public static void create(AutomationFlow automationFlow) {
            // Set up Request
            String url = AutomationFlowServiceImpl.CREATE;
            HttpWebRequest httpWebRequest = (HttpWebRequest) WebRequest.Create(url);
            httpWebRequest.ContentType = "application/json";
            httpWebRequest.Accept = "application/json";
            httpWebRequest.Method = "POST";

            // Send Request Data
            using (StreamWriter streamWriter = new StreamWriter(httpWebRequest.GetRequestStream())) {
                string json = CommonUtils.serialize(automationFlow);
                Debug.WriteLine(json);
                streamWriter.Write(json);
                streamWriter.Flush();
                streamWriter.Close();
            }

            // Get the Response
            HttpWebResponse httpResponse = (HttpWebResponse) httpWebRequest.GetResponse();
        }



        /**
         * Removes a AutomationFlow entry in the Database by Id
         * Calls webservice
         */
        public static void removeByAutomationFlowId(int automationFlowId) {
            // Set up Request
            HttpWebRequest httpWebRequest = (HttpWebRequest) WebRequest.Create(AutomationFlowServiceImpl.DELETE_BY_AUTOMATION_FLOW_ID + automationFlowId);
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
        public static void removeByAutomationId(int automationId) {
            // Set up Request
            HttpWebRequest httpWebRequest = (HttpWebRequest) WebRequest.Create(AutomationFlowServiceImpl.DELETE_BY_AUTOMATION_ID + automationId);
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
            
            // Output Links
            return tempStr;
        }

    }
}
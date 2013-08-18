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

    public class CommandElementImpl {
        
        /**
         * Obtains a CommandElement by Automation Id from the database
         * Calls webservice
         * @return A CommandElement object
         */
        public static List<CommandElement> getByAutomationId(int automationId) {
            // Instantiate Variables
            List<CommandElement> commandElements = new List<CommandElement>();

            // Set up Request
            HttpWebRequest httpWebRequest = (HttpWebRequest) WebRequest.Create(CommandElementServiceImpl.GET_BY_AUTOMATION_ID + automationId);
            httpWebRequest.ContentType = "application/json";
            httpWebRequest.Accept = "application/json";
            httpWebRequest.Method = "GET";

            // Get the Response
            HttpWebResponse httpResponse = (HttpWebResponse) httpWebRequest.GetResponse();
            using (StreamReader streamReader = new StreamReader(httpResponse.GetResponseStream())) {
                commandElements = CommonUtils.deserialize<List<CommandElement>>(streamReader.ReadToEnd());
            }

            // Return
            return commandElements;
        }



        /**
         * Obtains a CommandElement by Id from the database
         * Calls webservice
         * @return A CommandElement object
         */
        public static CommandElement getByCommandElementId(int commandElementId) {
            // Instantiate Variables
            CommandElement commandElement = new CommandElement();

            // Set up Request
            HttpWebRequest httpWebRequest = (HttpWebRequest) WebRequest.Create(CommandElementServiceImpl.GET_BY_COMMAND_ELEMENT_ID + commandElementId);
            httpWebRequest.ContentType = "application/json";
            httpWebRequest.Accept = "application/json";
            httpWebRequest.Method = "GET";

            // Get the Response
            HttpWebResponse httpResponse = (HttpWebResponse) httpWebRequest.GetResponse();
            using (StreamReader streamReader = new StreamReader(httpResponse.GetResponseStream())) {
                commandElement = CommonUtils.deserialize<CommandElement>(streamReader.ReadToEnd());
            }

            // Return
            return commandElement;
        }



        /**
         * Create a new entry in the Database
         * Calls webservice
         */
        public static void create(CommandElement commandElement, int automationId) {
            // Set up Request
            HttpWebRequest httpWebRequest = (HttpWebRequest) WebRequest.Create(CommandElementServiceImpl.CREATE + automationId);
            httpWebRequest.ContentType = "application/json";
            httpWebRequest.Accept = "application/json";
            httpWebRequest.Method = "POST";

            // Send Request Data
            using (StreamWriter streamWriter = new StreamWriter(httpWebRequest.GetRequestStream())) {
                string json = CommonUtils.serialize(commandElement);
                Debug.WriteLine(json);
                streamWriter.Write(json);
                streamWriter.Flush();
                streamWriter.Close();
            }

            // Get the Response
            HttpWebResponse httpResponse = (HttpWebResponse) httpWebRequest.GetResponse();
        }
        public static void createFromExisting(int commandElementId, int automationId) {
            // Set up Request
            HttpWebRequest httpWebRequest = (HttpWebRequest) WebRequest.Create(CommandElementServiceImpl.CREATE_FROM_EXISTING + automationId + "/" + commandElementId);
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
        public static void update(CommandElement commandElement) {
            // Set up Request
            HttpWebRequest httpWebRequest = (HttpWebRequest) WebRequest.Create(CommandElementServiceImpl.UPDATE);
            httpWebRequest.ContentType = "application/json";
            httpWebRequest.Accept = "application/json";
            httpWebRequest.Method = "PUT";

            // Send Request Data
            using (StreamWriter streamWriter = new StreamWriter(httpWebRequest.GetRequestStream())) {
                string json = CommonUtils.serialize(commandElement);
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
        public static void removeByCommandElementId(int commandElementId) {
            // Set up Request
            HttpWebRequest httpWebRequest = (HttpWebRequest) WebRequest.Create(CommandElementServiceImpl.REMOVE_BY_COMMAND_ELEMENT_ID + commandElementId);
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
            CommandElement commandElement = CommandElementImpl.getByCommandElementId(id);
            Revision revision = RevisionImpl.getByRevisionId(id2, 1);
            Survey survey = SurveyImpl.getBySurveyId(revision.surveyId);
            Company company= CompanyImpl.getByCompanyId(survey.companyId);

            // CommandElement
            tempStr = "<a class='breadcrumbCurrent' href='CommandElement.aspx?commandElementId=" + commandElement.commandElementId + "&revisionId=" + revision.revisionId + "'>" + CodeImpl.getByCodeId(commandElement.code.codeId).code + "</a>" + tempStr;

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
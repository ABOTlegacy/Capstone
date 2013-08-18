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

    public class CommandIdentifierImpl {
        
        /**
         * Obtains a CommandIdentier by Id from the database
         * Calls webservice
         * @return A CommandElement object
         */
        public static List<CommandIdentifier> getByCommandElementId(int commandElementId) {
            // Instantiate Variables
            List<CommandIdentifier> commandIdentifiers = new List<CommandIdentifier>();

            // Set up Request
            HttpWebRequest httpWebRequest = (HttpWebRequest) WebRequest.Create(CommandIdentifierServiceImpl.GET_BY_COMMAND_ELEMENT_ID + commandElementId);
            httpWebRequest.ContentType = "application/json";
            httpWebRequest.Accept = "application/json";
            httpWebRequest.Method = "GET";

            // Get the Response
            HttpWebResponse httpResponse = (HttpWebResponse) httpWebRequest.GetResponse();
            using (StreamReader streamReader = new StreamReader(httpResponse.GetResponseStream())) {
                commandIdentifiers = CommonUtils.deserialize<List<CommandIdentifier>>(streamReader.ReadToEnd());
            }

            // Return
            return commandIdentifiers;
        }



        /**
         * Obtains a CommandElement by Id from the database
         * Calls webservice
         * @return A CommandElement object
         */
        public static CommandIdentifier getByCommandIdentifierId(int commandIdentifierId) {
            // Instantiate Variables
            CommandIdentifier commandIdentifier = new CommandIdentifier();

            // Set up Request
            HttpWebRequest httpWebRequest = (HttpWebRequest) WebRequest.Create(CommandIdentifierServiceImpl.GET_BY_COMMAND_IDENTIFIER_ID + commandIdentifierId);
            httpWebRequest.ContentType = "application/json";
            httpWebRequest.Accept = "application/json";
            httpWebRequest.Method = "GET";

            // Get the Response
            HttpWebResponse httpResponse = (HttpWebResponse) httpWebRequest.GetResponse();
            using (StreamReader streamReader = new StreamReader(httpResponse.GetResponseStream())) {
                commandIdentifier = CommonUtils.deserialize<CommandIdentifier>(streamReader.ReadToEnd());
            }

            // Return
            return commandIdentifier;
        }



        /**
         * Create a new entry in the Database
         * Calls webservice
         */
        public static void create(CommandIdentifier commandIdentifier, int commandElementId) {
            // Set up Request
            HttpWebRequest httpWebRequest = (HttpWebRequest) WebRequest.Create(CommandIdentifierServiceImpl.CREATE + commandElementId);
            httpWebRequest.ContentType = "application/json";
            httpWebRequest.Accept = "application/json";
            httpWebRequest.Method = "POST";

            // Send Request Data
            using (StreamWriter streamWriter = new StreamWriter(httpWebRequest.GetRequestStream())) {
                string json = CommonUtils.serialize(commandIdentifier);
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
        public static void update(CommandIdentifier commandIdentifier) {
            // Set up Request
            HttpWebRequest httpWebRequest = (HttpWebRequest) WebRequest.Create(CommandIdentifierServiceImpl.UPDATE);
            httpWebRequest.ContentType = "application/json";
            httpWebRequest.Accept = "application/json";
            httpWebRequest.Method = "PUT";

            // Send Request Data
            using (StreamWriter streamWriter = new StreamWriter(httpWebRequest.GetRequestStream())) {
                string json = CommonUtils.serialize(commandIdentifier);
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
        public static void removeByCommandIdentifierId(int commandIdentifierId) {
            // Set up Request
            HttpWebRequest httpWebRequest = (HttpWebRequest) WebRequest.Create(CommandIdentifierServiceImpl.REMOVE_BY_COMMAND_IDENTIFIER_ID + commandIdentifierId);
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
            /*CommandIdentifier commandIdentifier = CommandIdentifierImpl.getByCommandIdentifierId(id);
            CommandElement commandElement = CommandElementImpl.getByCommandElementId(id);
            Revision revision = RevisionImpl.getByAutomationId(id2);
            Survey survey = SurveyImpl.getBySurveyId(revision.surveyId);
            Company company= CompanyImpl.getByCompanyId(survey.companyId);

            // CommandElement
            tempStr = "<a class='breadcrumbCurrent' href='CommandIdentifier.aspx?commandIdentifierId=" + commandIdentifier.commandIdentifierId + "&revisionId=" + revision.revisionId + "'>" + commandIdentifier.code.codeId + "</a>" + tempStr;

            // CommandElement
            tempStr = "<a class='breadcrumbNotCurrent' href='CommandElement.aspx?commandElementId=" + commandElement.commandElementId + "&revisionId=" + revision.revisionId + "'>" + commandElement.code.codeId + "</a>" + tempStr;

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
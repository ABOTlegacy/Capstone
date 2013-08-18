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

    public class RevisionImpl {
        
        /**
         * Obtains a list of all entrys in the database
         * Calls webservice
         * @return A list of surveys objects
         */
        public static List<Revision> getAll() {
            // Instantiate Variables
            List<Revision> revisions = new List<Revision>();

            // Set up Request
            string url = RevisionServiceImpl.GET_ALL;
            HttpWebRequest httpWebRequest = (HttpWebRequest) WebRequest.Create(url);
            httpWebRequest.ContentType = "application/json";
            httpWebRequest.Accept = "application/json";
            httpWebRequest.Method = "GET";

            // Get the Response
            HttpWebResponse httpResponse = (HttpWebResponse) httpWebRequest.GetResponse();
            using (StreamReader streamReader = new StreamReader(httpResponse.GetResponseStream())) {
                revisions = CommonUtils.deserialize<List<Revision>>(streamReader.ReadToEnd());
            }

            // Return
            return revisions;
        }
        public static List<Revision> getAll(int recursiveLevel) {
            // Instantiate Variables
            List<Revision> revisions = new List<Revision>();

            // Set up Request
            string url = RevisionServiceImpl.GET_ALL + recursiveLevel;
            HttpWebRequest httpWebRequest = (HttpWebRequest)WebRequest.Create(url);
            httpWebRequest.ContentType = "application/json";
            httpWebRequest.Accept = "application/json";
            httpWebRequest.Method = "GET";

            // Get the Response
            HttpWebResponse httpResponse = (HttpWebResponse)httpWebRequest.GetResponse();
            using (StreamReader streamReader = new StreamReader(httpResponse.GetResponseStream())) {
                revisions = CommonUtils.deserialize<List<Revision>>(streamReader.ReadToEnd());
            }

            // Return
            return revisions;
        }



        /**
         * Obtains a entry by Company Id from the database
         * Calls webservice
         * @return A Survey object
         */
        public static List<Revision> getBySurveyId(int surveyId) {
            // Instantiate Variables
            List<Revision> revisions = new List<Revision>();

            // Set up Request
            string url = RevisionServiceImpl.GET_BY_SURVEY_ID + surveyId;
            HttpWebRequest httpWebRequest = (HttpWebRequest) WebRequest.Create(url);
            httpWebRequest.ContentType = "application/json";
            httpWebRequest.Accept = "application/json";
            httpWebRequest.Method = "GET";

            // Get the Response
            HttpWebResponse httpResponse = (HttpWebResponse) httpWebRequest.GetResponse();
            using (StreamReader streamReader = new StreamReader(httpResponse.GetResponseStream())) {
                revisions = CommonUtils.deserialize<List<Revision>>(streamReader.ReadToEnd());
            }

            // Return
            return revisions;
        }
        public static List<Revision> getBySurveyId(int surveyId, int recursiveLevel) {
            // Instantiate Variables
            List<Revision> revisions = new List<Revision>();

            // Set up Request
            string url = RevisionServiceImpl.GET_BY_SURVEY_ID + surveyId + "/" + recursiveLevel;
            HttpWebRequest httpWebRequest = (HttpWebRequest) WebRequest.Create(url);
            httpWebRequest.ContentType = "application/json";
            httpWebRequest.Accept = "application/json";
            httpWebRequest.Method = "GET";

            // Get the Response
            HttpWebResponse httpResponse = (HttpWebResponse) httpWebRequest.GetResponse();
            using (StreamReader streamReader = new StreamReader(httpResponse.GetResponseStream())) {
                revisions = CommonUtils.deserialize<List<Revision>>(streamReader.ReadToEnd());
            }

            // Return
            return revisions;
        }



        /**
         * Obtains a Entry by Id from the database
         * Calls webservice
         * @return A Survey object
         */
        public static Revision getByRevisionId(int revisionId) {
            // Instantiate Variables
            Revision revision = new Revision();

            // Set up Request
            string url = SpeedyServiceImpl.GET_BY_REVISION_ID + revisionId;
            HttpWebRequest httpWebRequest = (HttpWebRequest) WebRequest.Create(url);
            httpWebRequest.ContentType = "application/json";
            httpWebRequest.Accept = "application/json";
            httpWebRequest.Method = "GET";

            // Get the Response
            HttpWebResponse httpResponse = (HttpWebResponse) httpWebRequest.GetResponse();
            using (StreamReader streamReader = new StreamReader(httpResponse.GetResponseStream())) {
                revision = AliasHelper.getRevision(CommonUtils.deserialize<R>(streamReader.ReadToEnd()));
            }

            // Return
            return revision;
        }
        public static Revision getByRevisionId(int revisionId, int recursiveLevel) {
            // Instantiate Variables
            Revision revision = new Revision();

            // Set up Request
            string url = RevisionServiceImpl.GET_BY_REVISION_ID + revisionId + "/" + recursiveLevel;
            HttpWebRequest httpWebRequest = (HttpWebRequest) WebRequest.Create(url);
            httpWebRequest.ContentType = "application/json";
            httpWebRequest.Accept = "application/json";
            httpWebRequest.Method = "GET";

            // Get the Response
            HttpWebResponse httpResponse = (HttpWebResponse) httpWebRequest.GetResponse();
            using (StreamReader streamReader = new StreamReader(httpResponse.GetResponseStream())) {
                revision = CommonUtils.deserialize<Revision>(streamReader.ReadToEnd());
            }

            // Return
            return revision;
        }



        /**
         * Obtains a Entry by Id from the database
         * Calls webservice
         * @return A Survey object
         */
        public static Revision getByQuestionId(int questionId) {
            // Instantiate Variables
            Revision revision = new Revision();

            // Set up Request
            string url = RevisionServiceImpl.GET_BY_QUESTION_ID + questionId;
            HttpWebRequest httpWebRequest = (HttpWebRequest) WebRequest.Create(url);
            httpWebRequest.ContentType = "application/json";
            httpWebRequest.Accept = "application/json";
            httpWebRequest.Method = "GET";

            // Get the Response
            HttpWebResponse httpResponse = (HttpWebResponse) httpWebRequest.GetResponse();
            using (StreamReader streamReader = new StreamReader(httpResponse.GetResponseStream())) {
                revision = CommonUtils.deserialize<Revision>(streamReader.ReadToEnd());
            }

            // Return
            return revision;
        }
        public static Revision getByQuestionId(int questionId, int recursiveLevel) {
            // Instantiate Variables
            Revision revision = new Revision();

            // Set up Request
            string url = RevisionServiceImpl.GET_BY_QUESTION_ID + questionId + "/" + recursiveLevel;
            HttpWebRequest httpWebRequest = (HttpWebRequest) WebRequest.Create(url);
            httpWebRequest.ContentType = "application/json";
            httpWebRequest.Accept = "application/json";
            httpWebRequest.Method = "GET";

            // Get the Response
            HttpWebResponse httpResponse = (HttpWebResponse) httpWebRequest.GetResponse();
            using (StreamReader streamReader = new StreamReader(httpResponse.GetResponseStream())) {
                revision = CommonUtils.deserialize<Revision>(streamReader.ReadToEnd());
            }

            // Return
            return revision;
        }



        /**
         * Create a new entry in the Database
         * Calls webservice
         */
        public static void create(Revision revision) {
            // Set up Request
            HttpWebRequest httpWebRequest = (HttpWebRequest) WebRequest.Create(RevisionServiceImpl.CREATE);
            httpWebRequest.ContentType = "application/json";
            httpWebRequest.Accept = "application/json";
            httpWebRequest.Method = "POST";

            // Send Request Data
            using (StreamWriter streamWriter = new StreamWriter(httpWebRequest.GetRequestStream())) {
                string json = CommonUtils.serialize(revision);
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
        public static void update(Revision revision) {
            // Set up Request
            HttpWebRequest httpWebRequest = (HttpWebRequest) WebRequest.Create(RevisionServiceImpl.UPDATE);
            httpWebRequest.ContentType = "application/json";
            httpWebRequest.Accept = "application/json";
            httpWebRequest.Method = "PUT";

            // Send Request Data
            using (StreamWriter streamWriter = new StreamWriter(httpWebRequest.GetRequestStream())) {
                string json = CommonUtils.serialize(revision);
                Debug.WriteLine(json);
                streamWriter.Write(json);
                streamWriter.Flush();
                streamWriter.Close();
            }

            // Get the Response
            HttpWebResponse httpResponse = (HttpWebResponse) httpWebRequest.GetResponse();
        }



        /**
         * Removes an entry in the Database by Company Id
         * Calls webservice
         */
        public static void removeByRevisionId(int revisionId) {
            // Set up Request
            HttpWebRequest httpWebRequest = (HttpWebRequest) WebRequest.Create(RevisionServiceImpl.REMOVE_BY_REVISION_ID + revisionId);
            httpWebRequest.ContentType = "application/json";
            httpWebRequest.Accept = "application/json";
            httpWebRequest.Method = "DELETE";

            // Get the Response
            HttpWebResponse httpResponse = (HttpWebResponse) httpWebRequest.GetResponse();
        }



        /**
         * Removes an entry in the Database by Survey Id
         * Calls webservice
         */
        public static void removeBySurveyId(int surveyId) {
            // Set up Request
            HttpWebRequest httpWebRequest = (HttpWebRequest) WebRequest.Create(RevisionServiceImpl.REMOVE_BY_SURVEY_ID + surveyId);
            httpWebRequest.ContentType = "application/json";
            httpWebRequest.Accept = "application/json";
            httpWebRequest.Method = "DELETE";

            // Get the Response
            HttpWebResponse httpResponse = (HttpWebResponse) httpWebRequest.GetResponse();
        }



        /**
         * Get the breadcrumbs
         */
        static public void performAutomatedTest(int revisionId){
            // Set up Request
            HttpWebRequest httpWebRequest = (HttpWebRequest) WebRequest.Create(RevisionServiceImpl.PERFORM_AUTOMATED_TEST + revisionId);
            httpWebRequest.ContentType = "application/json";
            httpWebRequest.Accept = "application/json";
            httpWebRequest.Method = "GET";

            // Get the Response
            HttpWebResponse httpResponse = (HttpWebResponse) httpWebRequest.GetResponse();
        }



        /**
         * Get the breadcrumbs
         */
        static public string getBreadcrumbs(int id) {
            // Instantiate Variables
            string tempStr = "";
            Revision revision = RevisionImpl.getByRevisionId(id, 3);
            Survey survey = SurveyImpl.getBySurveyId(revision.surveyId);
            Company company = CompanyImpl.getByCompanyId(survey.companyId);

            // Revision
            tempStr = "<a class='breadcrumbCurrent' href='Revision.aspx?revisionId=" + revision.revisionId + "'>" + revision.revisionNumber + "</a>" + tempStr;

            // Survey
            tempStr = "<a class='breadcrumbCurrent' href='Survey.aspx?surveyId=" + survey.surveyId + "'>" + survey.name + "</a> >> " + tempStr;

            // Company
            tempStr = "<a class='breadcrumbNotCurrent' href='Company.aspx?companyId=" + company.companyId + "'>" + company.name + "</a> >> " + tempStr;

            // Homepage
            tempStr = "<a class='breadcrumbNotCurrent' href='Index.aspx'>Home</a> >> " + tempStr;

            // Output Links
            return tempStr;
        }

    }
}
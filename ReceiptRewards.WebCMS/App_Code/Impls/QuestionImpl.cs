using ReceiptRewards.PCL.BusinessLayer;
using ReceiptRewards.PCL.Models;
using ReceiptRewards.PCL.ServiceAccessLayer.Database.Implementations;
using System.Collections.Generic;
using System.Diagnostics;
using System.IO;
using System.Net;

namespace ReceiptReward {

    public class QuestionImpl {

        /**
         * Obtains a list Quesstions in the database
         * Calls webservice
         * @return A list of Question objects
         */
        public static Question getByQuestionId(int questionId) {
            // Instantiate Variables
            Question question = new Question();

            // Set up Request
            string url = QuestionServiceImpl.GET_BY_QUESTION_ID + questionId;
            HttpWebRequest httpWebRequest = (HttpWebRequest) WebRequest.Create(url);
            httpWebRequest.ContentType = "application/json";
            httpWebRequest.Accept = "application/json";
            httpWebRequest.Method = "GET";
            
            // Get the Response
            HttpWebResponse httpResponse = (HttpWebResponse) httpWebRequest.GetResponse();
            using (StreamReader streamReader = new StreamReader(httpResponse.GetResponseStream())) {
                question = CommonUtils.deserialize<Question>(streamReader.ReadToEnd());
            }

            // Return
            return question;
        }
        public static Question getByQuestionId(int questionId, int recursiveLevel) {
            // Instantiate Variables
            Question question = new Question();

            // Set up Request
            string url = QuestionServiceImpl.GET_BY_QUESTION_ID + questionId + "/" + recursiveLevel;
            HttpWebRequest httpWebRequest = (HttpWebRequest) WebRequest.Create(url);
            httpWebRequest.ContentType = "application/json";
            httpWebRequest.Accept = "application/json";
            httpWebRequest.Method = "GET";

            // Get the Response
            HttpWebResponse httpResponse = (HttpWebResponse) httpWebRequest.GetResponse();
            using (StreamReader streamReader = new StreamReader(httpResponse.GetResponseStream())) {
                question = CommonUtils.deserialize<Question>(streamReader.ReadToEnd());
            }

            // Return
            return question;
        }



        /**
         * Obtains a list of Questions by type in the database
         * Calls webservice
         * @return A list of Question objects
         */
        public static List<Question> getByRevisionId(int revisionId) {
            // Instantiate Variables
            List<Question> questions = new List<Question>();

            // Set up Request
            string url = QuestionServiceImpl.GET_BY_REVISION_ID + revisionId;
            HttpWebRequest httpWebRequest = (HttpWebRequest) WebRequest.Create(url);
            httpWebRequest.ContentType = "application/json";
            httpWebRequest.Accept = "application/json";
            httpWebRequest.Method = "GET";

            // Get the Response
            HttpWebResponse httpResponse = (HttpWebResponse) httpWebRequest.GetResponse();
            using (StreamReader streamReader = new StreamReader(httpResponse.GetResponseStream())) {
                questions = CommonUtils.deserialize<List<Question>>(streamReader.ReadToEnd());
            }

            // Return
            return questions;
        }
        public static List<Question> getByRevisionId(int revisionId, int recursiveLevel) {
            // Instantiate Variables
            List<Question> questions = new List<Question>();

            // Set up Request
            string url = QuestionServiceImpl.GET_BY_REVISION_ID + revisionId + "/" + recursiveLevel;
            HttpWebRequest httpWebRequest = (HttpWebRequest) WebRequest.Create(url);
            httpWebRequest.ContentType = "application/json";
            httpWebRequest.Accept = "application/json";
            httpWebRequest.Method = "GET";

            // Get the Response
            HttpWebResponse httpResponse = (HttpWebResponse) httpWebRequest.GetResponse();
            using (StreamReader streamReader = new StreamReader(httpResponse.GetResponseStream())) {
                questions = CommonUtils.deserialize<List<Question>>(streamReader.ReadToEnd());
            }

            // Return
            return questions;
        }



        /**
         * Obtains a list of Questions by type in the database
         * Calls webservice
         * @return A list of Question objects
         */
        public static List<Question> getByFormId(int formId) {
            // Instantiate Variables
            List<Question> questions = new List<Question>();

            // Set up Request
            string url = QuestionServiceImpl.GET_BY_FORM_ID + formId;
            HttpWebRequest httpWebRequest = (HttpWebRequest) WebRequest.Create(url);
            httpWebRequest.ContentType = "application/json";
            httpWebRequest.Accept = "application/json";
            httpWebRequest.Method = "GET";

            // Get the Response
            HttpWebResponse httpResponse = (HttpWebResponse) httpWebRequest.GetResponse();
            using (StreamReader streamReader = new StreamReader(httpResponse.GetResponseStream())) {
                questions = CommonUtils.deserialize<List<Question>>(streamReader.ReadToEnd());
            }

            // Return
            return questions;
        }
        public static List<Question> getByFormId(int formId, int recursiveLevel) {
            // Instantiate Variables
            List<Question> questions = new List<Question>();

            // Set up Request
            string url = QuestionServiceImpl.GET_BY_FORM_ID + formId + "/" + recursiveLevel;
            HttpWebRequest httpWebRequest = (HttpWebRequest) WebRequest.Create(url);
            httpWebRequest.ContentType = "application/json";
            httpWebRequest.Accept = "application/json";
            httpWebRequest.Method = "GET";

            // Get the Response
            HttpWebResponse httpResponse = (HttpWebResponse) httpWebRequest.GetResponse();
            using (StreamReader streamReader = new StreamReader(httpResponse.GetResponseStream())) {
                questions = CommonUtils.deserialize<List<Question>>(streamReader.ReadToEnd());
            }

            // Return
            return questions;
        }



        /**
         * Obtains a list of Questions by type in the database
         * Calls webservice
         * @return A list of Question objects
         */
        public static List<Question> getByAutomationId(int automationId) {
            // Instantiate Variables
            List<Question> questions = new List<Question>();

            // Set up Request
            string url = QuestionServiceImpl.GET_BY_AUTOMATION_ID + automationId;
            HttpWebRequest httpWebRequest = (HttpWebRequest) WebRequest.Create(url);
            httpWebRequest.ContentType = "application/json";
            httpWebRequest.Accept = "application/json";
            httpWebRequest.Method = "GET";

            // Get the Response
            HttpWebResponse httpResponse = (HttpWebResponse) httpWebRequest.GetResponse();
            using (StreamReader streamReader = new StreamReader(httpResponse.GetResponseStream())) {
                questions = CommonUtils.deserialize<List<Question>>(streamReader.ReadToEnd());
            }

            // Return
            return questions;
        }
        public static List<Question> getByAutomationId(int automationId, int recursiveLevel) {
            // Instantiate Variables
            List<Question> questions = new List<Question>();

            // Set up Request
            string url = QuestionServiceImpl.GET_BY_AUTOMATION_ID + automationId + "/" + recursiveLevel;
            HttpWebRequest httpWebRequest = (HttpWebRequest) WebRequest.Create(url);
            httpWebRequest.ContentType = "application/json";
            httpWebRequest.Accept = "application/json";
            httpWebRequest.Method = "GET";

            // Get the Response
            HttpWebResponse httpResponse = (HttpWebResponse) httpWebRequest.GetResponse();
            using (StreamReader streamReader = new StreamReader(httpResponse.GetResponseStream())) {
                questions = CommonUtils.deserialize<List<Question>>(streamReader.ReadToEnd());
            }

            // Return
            return questions;
        }



        /**
         * Create a new Question entry in the Database
         * Calls webservice
         */
        public static void create(Question question) {
            // Set up Request
            HttpWebRequest httpWebRequest = (HttpWebRequest) WebRequest.Create(QuestionServiceImpl.CREATE);
            httpWebRequest.ContentType = "application/json";
            httpWebRequest.Accept = "application/json";
            httpWebRequest.Method = "POST";

            // Send Request Data
            using (StreamWriter streamWriter = new StreamWriter(httpWebRequest.GetRequestStream())) {
                string json = CommonUtils.serialize(question);
                Debug.WriteLine(json);
                streamWriter.Write(json);
                streamWriter.Flush();
                streamWriter.Close();
            }

            // Get the Response
            HttpWebResponse httpResponse = (HttpWebResponse) httpWebRequest.GetResponse();
        }



        /**
         * Update a Question entry in the Database
         * Calls webservice
         */
        public static void update(Question question) {
            // Set up Request
            HttpWebRequest httpWebRequest = (HttpWebRequest) WebRequest.Create(QuestionServiceImpl.UPDATE);
            httpWebRequest.ContentType = "application/json";
            httpWebRequest.Accept = "application/json";
            httpWebRequest.Method = "PUT";

            // Send Request Data
            using (StreamWriter streamWriter = new StreamWriter(httpWebRequest.GetRequestStream())) {
                string json = CommonUtils.serialize(question);
                Debug.WriteLine(json);
                streamWriter.Write(json);
                streamWriter.Flush();
                streamWriter.Close();
            }

            // Get the Response
            HttpWebResponse httpResponse = (HttpWebResponse) httpWebRequest.GetResponse();
        }



        /**
         * Removes a Question entry in the Database by Id
         * Calls webservice
         */
        public static void removeByQuestionId(int questionId) {
            // Set up Request
            HttpWebRequest httpWebRequest = (HttpWebRequest) WebRequest.Create(QuestionServiceImpl.REMOVE_BY_QUESTION_ID + questionId);
            httpWebRequest.ContentType = "application/json";
            httpWebRequest.Accept = "application/json";
            httpWebRequest.Method = "DELETE";

            // Get the Response
            HttpWebResponse httpResponse = (HttpWebResponse) httpWebRequest.GetResponse();
        }



        /**
         * Removes a Question entry in the Database by Id
         * Calls webservice
         */
        public static void removeByFormId(int formId) {
            // Set up Request
            HttpWebRequest httpWebRequest = (HttpWebRequest) WebRequest.Create(QuestionServiceImpl.REMOVE_BY_FORM_ID + formId);
            httpWebRequest.ContentType = "application/json";
            httpWebRequest.Accept = "application/json";
            httpWebRequest.Method = "DELETE";

            // Get the Response
            HttpWebResponse httpResponse = (HttpWebResponse) httpWebRequest.GetResponse();
        }



        /**
         * Removes a Question entry in the Database by Id
         * Calls webservice
         */
        public static void removeByAutomationId(int automationId) {
            // Set up Request
            HttpWebRequest httpWebRequest = (HttpWebRequest) WebRequest.Create(QuestionServiceImpl.REMOVE_BY_AUTOMATION_ID + automationId);
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
            Question question = QuestionImpl.getByQuestionId(id, 1);
            Revision revision = RevisionImpl.getByRevisionId(id2, 1);
            Survey survey = SurveyImpl.getBySurveyId(revision.surveyId);
            Company company = CompanyImpl.getByCompanyId(survey.companyId);

            // Question
            tempStr = "<a class='breadcrumbCurrent' href='Question.aspx?questionId=" + question.questionId + "&revisionId=" + revision.revisionId + "'>" + question.name + "</a>" + tempStr;

            // Revision
            tempStr = "<a class='breadcrumbCurrent' href='Revision.aspx?revisionId=" + revision.revisionId + "'>" + revision.revisionNumber + "</a> >> " + tempStr;

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
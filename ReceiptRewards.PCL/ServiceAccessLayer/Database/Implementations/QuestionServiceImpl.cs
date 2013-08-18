using System;
using System.Collections.Generic;
using System.Net;
using System.IO;
using Newtonsoft.Json.Linq;
using System.Diagnostics;
using ReceiptRewards.PCL.Models;

namespace ReceiptRewards.PCL.ServiceAccessLayer.Database.Implementations {

    public class QuestionServiceImpl {
        // Base URL
        private const string BASE_URL = "http://140.104.69.94:8080/ReceiptRewards_DataAccessLayer";
        private const string DIRECTORY = QuestionServiceImpl.BASE_URL + "/Question";

        // GET Methods
        public const string GET_BY_QUESTION_ID = QuestionServiceImpl.DIRECTORY + "/ById/Question/";
        public const string GET_BY_REVISION_ID = QuestionServiceImpl.DIRECTORY + "/ById/Revision/";
        public const string GET_BY_FORM_ID = QuestionServiceImpl.DIRECTORY + "/ById/Form/";
        public const string GET_BY_AUTOMATION_ID = QuestionServiceImpl.DIRECTORY + "/ById/Automation/";

        // POST Methods
        public const string CREATE = QuestionServiceImpl.DIRECTORY + "/Create/";

        // UPDATE Methods
        public const string UPDATE = QuestionServiceImpl.DIRECTORY + "/Update/";

        // DELETE Methods
        public const string REMOVE_BY_QUESTION_ID = QuestionServiceImpl.DIRECTORY + "/ById/Question/";
        public const string REMOVE_BY_AUTOMATION_ID = QuestionServiceImpl.DIRECTORY + "/ById/Automation/";
        public const string REMOVE_BY_FORM_ID = QuestionServiceImpl.DIRECTORY + "/ById/Form/";
    }
}
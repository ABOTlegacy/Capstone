using System;
using System.Collections.Generic;
using System.Net;
using System.IO;
using Newtonsoft.Json.Linq;
using System.Diagnostics;
using ReceiptRewards.PCL.Models;

namespace ReceiptRewards.PCL.ServiceAccessLayer.Database.Implementations {

    public class RevisionServiceImpl {
        // Base URL
        private const string BASE_URL = "http://140.104.69.94:8080/ReceiptRewards_DataAccessLayer";
        private const string DIRECTORY = RevisionServiceImpl.BASE_URL + "/Revision";

        // GET Methods
        public const string GET_ALL = RevisionServiceImpl.DIRECTORY + "/All/";
        public const string GET_BY_ACTIVE_SURVEY_ID = RevisionServiceImpl.DIRECTORY + "/ById/Active/";
        public const string GET_BY_REVISION_ID = RevisionServiceImpl.DIRECTORY + "/ById/Revision/";
        public const string GET_BY_SURVEY_ID = RevisionServiceImpl.DIRECTORY + "/ById/Survey/";
        public const string GET_BY_QUESTION_ID = RevisionServiceImpl.DIRECTORY + "/ById/Question/";

        // POST Methods
        public const string CREATE = RevisionServiceImpl.DIRECTORY + "/Create/";

        // UPDATE Methods
        public const string UPDATE = RevisionServiceImpl.DIRECTORY + "/Update/";

        // DELETE Methods
        public const string REMOVE_BY_REVISION_ID = RevisionServiceImpl.DIRECTORY + "/ById/Revision/";
        public const string REMOVE_BY_SURVEY_ID = RevisionServiceImpl.DIRECTORY + "/ById/Survey/";

        // RUN Methods
        public const string PERFORM_AUTOMATED_TEST = RevisionServiceImpl.BASE_URL + "/Automation/Action/Test/Revision/";
    }
}
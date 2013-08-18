using System;
using System.Collections.Generic;
using System.Net;
using System.IO;
using Newtonsoft.Json.Linq;
using System.Diagnostics;
using ReceiptRewards.PCL.Models;

namespace ReceiptRewards.PCL.ServiceAccessLayer.Database.Implementations {

    public class SubmissionServiceImpl {
        // Base URL
        private const string BASE_URL = "http://140.104.69.94:8080/ReceiptRewards_DataAccessLayer";
        private const string DIRECTORY = SubmissionServiceImpl.BASE_URL + "/Submission";

        // GET Methods
        public const string GET_BY_SUBMISSION_ID = SubmissionServiceImpl.DIRECTORY + "/ById/Submission/";
        public const string GET_BY_FORM_ID = SubmissionServiceImpl.DIRECTORY + "/ById/Form/";

        // POST Methods
        public const string CREATE = SubmissionServiceImpl.DIRECTORY + "/Create/";

        // UPDATE Methods
        public const string UPDATE = SubmissionServiceImpl.DIRECTORY + "/Update/";

        // DELETE Methods
        public const string REMOVE_BY_SUBMISSION_ID = SubmissionServiceImpl.DIRECTORY + "/ById/Submission/";
        public const string REMOVE_BY_FORM_ID = SubmissionServiceImpl.DIRECTORY + "/ById/Form/";

        // ACTION Methods
        public const string ACTION_BY_SUBBMISSION = SubmissionServiceImpl.DIRECTORY + "/Action/Submission/";
    }
}
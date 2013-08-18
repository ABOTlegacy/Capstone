using System;
using System.Collections.Generic;
using System.Net;
using System.IO;
using Newtonsoft.Json.Linq;
using System.Diagnostics;
using ReceiptRewards.PCL.Models;

namespace ReceiptRewards.PCL.ServiceAccessLayer.Database.Implementations {

    public class SubmissionAnswerServiceImpl {
        // Base URL
        private const string BASE_URL = "http://140.104.69.94:8080/ReceiptRewards_DataAccessLayer";
        private const string DIRECTORY = SubmissionAnswerServiceImpl.BASE_URL + "/SubmissionAnswer";

        // GET Methods
        public const string GET_BY_ANSWER_ID = SubmissionAnswerServiceImpl.DIRECTORY + "/ById/SubmissionAnswer/";
        public const string GET_BY_SUBMISSION_ID = SubmissionAnswerServiceImpl.DIRECTORY + "/ById/Submission/";

        // POST Methods
        public const string CREATE = SubmissionAnswerServiceImpl.DIRECTORY + "/Create/";

        // UPDATE Methods
        public const string UPDATE = SubmissionAnswerServiceImpl.DIRECTORY + "/Update/";

        // DELETE Methods
        public const string REMOVE_BY_SUBMISSION_ANSWER_ID = SubmissionAnswerServiceImpl.DIRECTORY + "/ById/SubmissionAnswer/";
        public const string REMOVE_BY_SUBMISSION_ID = SubmissionAnswerServiceImpl.DIRECTORY + "/ById/Submission/";
        public const string REMOVE_BY_FORM_ELEMENT_ID = SubmissionAnswerServiceImpl.DIRECTORY + "/ById/FormElement/";
    }
}
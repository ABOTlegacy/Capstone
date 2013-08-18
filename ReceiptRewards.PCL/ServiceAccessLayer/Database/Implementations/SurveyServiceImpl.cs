using System;
using System.Collections.Generic;
using System.Net;
using System.IO;
using Newtonsoft.Json.Linq;
using System.Diagnostics;
using ReceiptRewards.PCL.Models;

namespace ReceiptRewards.PCL.ServiceAccessLayer.Database.Implementations {

    public class SurveyServiceImpl {
        // Base URL
        private const string BASE_URL = "http://140.104.69.94:8080/ReceiptRewards_DataAccessLayer";
        private const string DIRECTORY = SurveyServiceImpl.BASE_URL + "/Survey";

        // GET Methods
        public const string GET_ALL = SurveyServiceImpl.DIRECTORY + "/All";
        public const string GET_BY_COMPANY_ID = SurveyServiceImpl.DIRECTORY + "/ById/Company/";
        public const string GET_BY_SURVEY_ID = SurveyServiceImpl.DIRECTORY + "/ById/Survey/";

        // POST Methods
        public const string CREATE = SurveyServiceImpl.DIRECTORY + "/Create/";

        // UPDATE Methods
        public const string UPDATE = SurveyServiceImpl.DIRECTORY + "/Update/";

        // DELETE Methods
        public const string REMOVE_BY_COMPANY_ID = SurveyServiceImpl.DIRECTORY + "/ById/Company/";
        public const string REMOVE_BY_SURVEY_ID = SurveyServiceImpl.DIRECTORY + "/ById/Survey/";
    }
}
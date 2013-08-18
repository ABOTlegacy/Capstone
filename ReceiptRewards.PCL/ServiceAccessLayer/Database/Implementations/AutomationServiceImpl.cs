using System;
using System.Collections.Generic;
using System.Net;
using System.IO;
using Newtonsoft.Json.Linq;
using System.Diagnostics;
using ReceiptRewards.PCL.Models;

namespace ReceiptRewards.PCL.ServiceAccessLayer.Database.Implementations {

    public class AutomationServiceImpl {
        // Base URL
        private const string BASE_URL = "http://140.104.69.94:8080/ReceiptRewards_DataAccessLayer";
        private const string DIRECTORY = AutomationServiceImpl.BASE_URL + "/Automation";

        // GET Methods
        public const string GET_BY_AUTOMATION_ID = AutomationServiceImpl.DIRECTORY + "/ById/Automation/";
        public const string GET_BY_QUESTION_ID = AutomationServiceImpl.DIRECTORY + "/ById/Question/";
        public const string GET_BY_REVISION_ID = AutomationServiceImpl.DIRECTORY + "/ById/Revision/";
        

        // CREATE Methods
        public const string CREATE = AutomationServiceImpl.DIRECTORY + "/Create/";

        // DELETE Methods
        public const string DELETE_BY_AUTOMATION_ID = AutomationServiceImpl.DIRECTORY + "/ById/Automation/";
        public const string DELETE_BY_QUESTION_ID = AutomationServiceImpl.DIRECTORY + "/ById/Question/";
        public const string DELETE_BY_REVISION_ID = AutomationServiceImpl.DIRECTORY + "/ById/Revision/";
    }
}
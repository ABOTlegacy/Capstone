using System;
using System.Collections.Generic;
using System.Net;
using System.IO;
using Newtonsoft.Json.Linq;
using System.Diagnostics;
using ReceiptRewards.PCL.Models;

namespace ReceiptRewards.PCL.ServiceAccessLayer.Database.Implementations {

    public class AutomationFlowServiceImpl {
        // Base URL
        private const string BASE_URL = "http://140.104.69.94:8080/ReceiptRewards_DataAccessLayer";
        private const string DIRECTORY = AutomationFlowServiceImpl.BASE_URL + "/AutomationFlow";

        // GET Methods
        public const string GET_BY_AUTOMATION_FLOW_ID = AutomationFlowServiceImpl.DIRECTORY + "/ById/AutomationFlow/";
        public const string GET_BY_AUTOMATION_ID = AutomationFlowServiceImpl.DIRECTORY + "/ById/Automation/";

        // CREATE Methods
        public const string CREATE = AutomationFlowServiceImpl.DIRECTORY + "/Create/";

        // DELETE Methods
        public const string DELETE_BY_AUTOMATION_FLOW_ID = AutomationFlowServiceImpl.DIRECTORY + "/ById/AutomationFlow/";
        public const string DELETE_BY_AUTOMATION_ID = AutomationFlowServiceImpl.DIRECTORY + "/ById/Automation/";
        public const string DELETE_BY_QUESTION_ID = AutomationFlowServiceImpl.DIRECTORY + "/ById/Question/";
    }
}
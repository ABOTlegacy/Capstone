using System;
using System.Collections.Generic;
using System.Net;
using System.IO;
using Newtonsoft.Json.Linq;
using System.Diagnostics;
using ReceiptRewards.PCL.Models;

namespace ReceiptRewards.PCL.ServiceAccessLayer.Database.Implementations {

    public class FormFlowServiceImpl {
        // Base URL
        private const string BASE_URL = "http://140.104.69.94:8080/ReceiptRewards_DataAccessLayer";
        private const string DIRECTORY = FormFlowServiceImpl.BASE_URL + "/FormFlow";

        // GET Methods
        public const string GET_BY_FORM_FLOW_ID = FormFlowServiceImpl.DIRECTORY + "/ById/FormFlow/";
        public const string GET_BY_FORM_ID = FormFlowServiceImpl.DIRECTORY + "/ById/Form/";

        // CREATE Methods
        public const string CREATE = FormFlowServiceImpl.DIRECTORY + "/Create/";

        // DELETE Methods
        public const string DELETE_BY_FORM_FLOW_ID = FormFlowServiceImpl.DIRECTORY + "/ById/FormFlow/";
        public const string DELETE_BY_FORM_ID = FormFlowServiceImpl.DIRECTORY + "/ById/Form/";
        public const string DELETE_BY_QUESTION_ID = FormFlowServiceImpl.DIRECTORY + "/ById/Question/";
    }
}
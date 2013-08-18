using System;
using System.Collections.Generic;
using System.Net;
using System.IO;
using Newtonsoft.Json.Linq;
using System.Diagnostics;
using ReceiptRewards.PCL.Models;

namespace ReceiptRewards.Core.ServiceAccessLayer.Database.Implementations {

    public class FormServiceImpl {
        // Base URL
        private const string BASE_URL = "http://140.104.69.94:8080/ReceiptRewards_DataAccessLayer";
        private const string DIRECTORY = FormServiceImpl.BASE_URL + "/Form";

        // GET Methods
        public const string GET_BY_FORM_ID = FormServiceImpl.DIRECTORY + "/ById/Form/";
        public const string GET_BY_QUESTION_ID = FormServiceImpl.DIRECTORY + "/ById/Question/";
        public const string GET_BY_REVISION_ID = FormServiceImpl.DIRECTORY + "/ById/Revision/";
    }
}
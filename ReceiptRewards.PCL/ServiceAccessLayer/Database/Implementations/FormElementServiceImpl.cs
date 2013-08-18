using System;
using System.Collections.Generic;
using System.Net;
using System.IO;
using Newtonsoft.Json.Linq;
using System.Diagnostics;
using ReceiptRewards.PCL.Models;

namespace ReceiptRewards.PCL.ServiceAccessLayer.Database.Implementations {

    public class FormElementServiceImpl {
        // Base URL
        private const string BASE_URL = "http://140.104.69.94:8080/ReceiptRewards_DataAccessLayer";
        private const string DIRECTORY = FormElementServiceImpl.BASE_URL + "/FormElement";

        // GET Methods
        public const string GET_BY_FORM_ELEMENT_ID = FormElementServiceImpl.DIRECTORY + "/ById/FormElement/";
        public const string GET_BY_FORM_ID = FormElementServiceImpl.DIRECTORY + "/ById/Form/";

        // POST Methods
        public const string CREATE = FormElementServiceImpl.DIRECTORY + "/Create/";
        public const string CREATE_FROM_EXISTING = FormElementServiceImpl.DIRECTORY + "/Create/";

        // UPDATE Methods
        public const string UPDATE = FormElementServiceImpl.DIRECTORY + "/Update/";

        // DELETE Methods
        public const string REMOVE_BY_FORM_ELEMENT_ID = FormElementServiceImpl.DIRECTORY + "/ById/FormElement";
    }
}
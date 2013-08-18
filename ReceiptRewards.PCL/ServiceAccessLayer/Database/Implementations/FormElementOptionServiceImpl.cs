using System;
using System.Collections.Generic;
using System.Net;
using System.IO;
using Newtonsoft.Json.Linq;
using System.Diagnostics;
using ReceiptRewards.PCL.Models;

namespace ReceiptRewards.PCL.ServiceAccessLayer.Database.Implementations {
    public class FormElementOptionServiceImpl {
        // Base URL
        private const string BASE_URL = "http://140.104.69.94:8080/ReceiptRewards_DataAccessLayer";
        private const string DIRECTORY = FormElementOptionServiceImpl.BASE_URL + "/FormElementOption";

        // GET Methods
        public const string GET_BY_FORM_ELEMENT_OPTION_ID = FormElementOptionServiceImpl.DIRECTORY + "/ById/FormElementOption/";
        public const string GET_BY_FORM_ELEMENT_ID = FormElementOptionServiceImpl.DIRECTORY + "/ById/FormElement/";

        // POST Methods
        public const string CREATE = FormElementOptionServiceImpl.DIRECTORY + "/Create/";

        // UPDATE Methods
        public const string UPDATE = FormElementOptionServiceImpl.DIRECTORY + "/Update/";

        // DELETE Methods
        public const string REMOVE_BY_FORM_ELEMENT_OPTION_ID = FormElementOptionServiceImpl.DIRECTORY + "/ById/FormElementOption/";
    }
}
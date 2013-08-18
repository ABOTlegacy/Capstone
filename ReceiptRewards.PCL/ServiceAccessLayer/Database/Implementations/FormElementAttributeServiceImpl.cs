using System;
using System.Collections.Generic;
using System.Net;
using System.IO;
using Newtonsoft.Json.Linq;
using System.Diagnostics;
using ReceiptRewards.PCL.Models;


namespace ReceiptRewards.Core.ServiceAccessLayer.Database.Implementations {

    public class FormElementAttributeServiceImpl {
        // Base URL
        private const string BASE_URL = "http://140.104.69.94:8080/ReceiptRewards_DataAccessLayer";
        private const string DIRECTORY = FormElementAttributeServiceImpl.BASE_URL + "/FormElementAttribute";

        // GET Methods
        public const string GET_BY_FORM_ELEMENT_ATTRIBUTE_ID = FormElementAttributeServiceImpl.DIRECTORY + "/ById/FormElementAttribute/";
        public const string GET_BY_FORM_ELEMENT_ID = FormElementAttributeServiceImpl.DIRECTORY + "/ById/FormElement/";

        // POST Methods
        public const string CREATE = FormElementAttributeServiceImpl.DIRECTORY + "/Create/";

        // UPDATE Methods
        public const string UPDATE = FormElementAttributeServiceImpl.DIRECTORY + "/Update/";

        // DELETE Methods
        public const string REMOVE_BY_FORM_ELEMENT_ATTRIBUTE_ID = FormElementAttributeServiceImpl.DIRECTORY + "/ById/FormElementAttribute";
    }
}
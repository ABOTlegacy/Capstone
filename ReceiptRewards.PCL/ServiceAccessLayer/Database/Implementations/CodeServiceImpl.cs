using System;
using System.Collections.Generic;
using System.Net;
using System.IO;
using Newtonsoft.Json.Linq;
using System.Diagnostics;
using ReceiptRewards.PCL.Models;

namespace ReceiptRewards.PCL.ServiceAccessLayer.Database.Implementations {

    public class CodeServiceImpl {
        // Base URL
        private const string BASE_URL = "http://140.104.69.94:8080/ReceiptRewards_DataAccessLayer";
        private const string DIRECTORY= CodeServiceImpl.BASE_URL + "/Code";

        // GET Methods
        public const string GET_ALL = CodeServiceImpl.DIRECTORY + "/All/";
        public const string GET_BY_CODE_TYPE_ID = CodeServiceImpl.DIRECTORY + "/ById/CodeType/";
        public const string GET_BY_CODE_ID = CodeServiceImpl.DIRECTORY + "/ById/Code/";
        public const string GET_BY_CODE_CODE = CodeServiceImpl.DIRECTORY + "/ByCode/Code/";
        
        // POST Methods
        public const string CREATE = CodeServiceImpl.DIRECTORY + "/Create/";

        // UPDATE Methods
        public const string UPDATE = CodeServiceImpl.DIRECTORY + "/Update/";

        // DELETE Methods
        public const string REMOVE_BY_CODE_ID = CodeServiceImpl.DIRECTORY + "/ById/Code/";
        public const string REMOVE_BY_CODE_CODE = CodeServiceImpl.DIRECTORY + "/ByCode/Code/";
    }
}
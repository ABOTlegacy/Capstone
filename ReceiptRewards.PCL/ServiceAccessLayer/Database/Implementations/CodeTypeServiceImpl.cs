using System;
using System.Collections.Generic;
using System.Net;
using System.IO;
using Newtonsoft.Json.Linq;
using System.Diagnostics;
using ReceiptRewards.PCL.Models;

namespace ReceiptRewards.PCL.ServiceAccessLayer.Database.Implementations {

    public class CodeTypeServiceImpl {
        // Base URL
        private const string BASE_URL = "http://140.104.69.94:8080/ReceiptRewards_DataAccessLayer";
        private const string DIRECTORY= CodeTypeServiceImpl.BASE_URL + "/CodeType";

        // GET Methods
        public const string GET_ALL = CodeTypeServiceImpl.DIRECTORY + "/All/";
        public const string GET_BY_CODE_TYPE_ID = CodeTypeServiceImpl.DIRECTORY + "/ById/CodeType/";
        public const string GET_BY_CODE_TYPE_TYPE = CodeTypeServiceImpl.DIRECTORY + "/ByType/CodeType/";
        
        // POST Methods
        public const string CREATE = CodeTypeServiceImpl.DIRECTORY + "/Create/";

        // PUT Methods
        public const string UPDATE = CodeTypeServiceImpl.DIRECTORY + "/Update/";

        // DELETE Methods
        public const string REMOVE_BY_CODE_TYPE_ID = CodeTypeServiceImpl.DIRECTORY + "/ById/CodeType/";
    }
}
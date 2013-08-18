using System;
using System.Collections.Generic;
using System.Net;
using System.IO;
using Newtonsoft.Json.Linq;
using System.Diagnostics;
using ReceiptRewards.PCL.Models;

namespace ReceiptRewards.PCL.ServiceAccessLayer.Database.Implementations {

    public class CompanyServiceImpl {
        // Base URL
        private const string BASE_URL = "http://140.104.69.94:8080/ReceiptRewards_DataAccessLayer";
        private const string DIRECTORY = CompanyServiceImpl.BASE_URL + "/Company";

        // GET Methods
        public const string GET_ALL = CompanyServiceImpl.DIRECTORY + "/All/";
        public const string GET_BY_COMPANY_ID = CompanyServiceImpl.DIRECTORY + "/ById/Company/";

        // POST Methods
        public const string CREATE = CompanyServiceImpl.DIRECTORY + "/Create/";

        // UPDATE Methods
        public const string UPDATE = CompanyServiceImpl.DIRECTORY + "/Update/";

        // DELETE Methods
        public const string REMOVE_BY_COMPANY_ID = CompanyServiceImpl.DIRECTORY + "/ById/Company/";
    }
}
using System.Collections.Generic;
using System.Net;
using System.IO;
using Newtonsoft.Json.Linq;
using ReceiptRewards.PCL.Models;

namespace ReceiptRewards.PCL.ServiceAccessLayer.Proxy.Implementations {

    public class CompanyServiceImpl {
        // Base URL
        private const string BASE_URL = "http://140.104.69.94:8080/rp";
        private const string DIRECTORY = CompanyServiceImpl.BASE_URL + "/Company";

        // GET Methods
        public const string GET_ALL = CompanyServiceImpl.DIRECTORY + "/All";
        public const string GET_BY_ID = CompanyServiceImpl.DIRECTORY + "/ById/";
    }
}
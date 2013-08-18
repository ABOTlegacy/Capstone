using System.Collections.Generic;
using System.Net;
using System.IO;
using Newtonsoft.Json.Linq;
using ReceiptRewards.PCL.Models;

namespace ReceiptRewards.PCL.ServiceAccessLayer.Proxy.Implementations {

    public class RevisionServiceImpl {
        // Base URL
        private const string BASE_URL = "http://140.104.69.94:8080/rp";
        private const string DIRECTORY = RevisionServiceImpl.BASE_URL + "/Revision";

        // GET Methods
        public const string GET_BY_REVISION_ID = RevisionServiceImpl.DIRECTORY + "/FormOnly/ById/Revision/";
        public const string GET_BY_SURVEY_ID = RevisionServiceImpl.DIRECTORY + "/FormOnly/ById/Survey/";
        public const string GET_BY_ACTIVE_SURVEY_ID = RevisionServiceImpl.DIRECTORY + "/FormOnly/ById/Active/";
    }
}
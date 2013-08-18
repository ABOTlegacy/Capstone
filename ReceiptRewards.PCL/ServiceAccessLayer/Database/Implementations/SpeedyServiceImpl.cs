using System;
using System.Collections.Generic;
using System.Net;
using System.IO;
using Newtonsoft.Json.Linq;
using System.Diagnostics;
using ReceiptRewards.PCL.Models;

namespace ReceiptRewards.PCL.ServiceAccessLayer.Database.Implementations {

    public class SpeedyServiceImpl {
        // Base URL
        private const string BASE_URL = "http://140.104.69.94:8080/ReceiptRewards_DataAccessLayer";
        private const string DIRECTORY = SpeedyServiceImpl.BASE_URL + "/Speedy";

        // GET Methods
        public const string GET_BY_ACTIVE_SURVEY_ID = SpeedyServiceImpl.DIRECTORY + "/FormOnly/ById/Active/";
        public const string GET_BY_SURVEY_ID = SpeedyServiceImpl.DIRECTORY + "/FormOnly/ById/Survey/";
        public const string GET_BY_REVISION_ID = SpeedyServiceImpl.DIRECTORY + "/FormOnly/ById/Revision/";
    }
}
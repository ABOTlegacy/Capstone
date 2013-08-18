using System;
using System.Collections.Generic;
using System.Net;
using System.IO;
using Newtonsoft.Json.Linq;
using System.Diagnostics;
using ReceiptRewards.PCL.Models;

namespace ReceiptRewards.PCL.ServiceAccessLayer.Database.Implementations {

    public class CommandElementServiceImpl {
        // Base URL
        private const string BASE_URL = "http://140.104.69.94:8080/ReceiptRewards_DataAccessLayer";
        private const string DIRECTORY = CommandElementServiceImpl.BASE_URL + "/CommandElement";

        // GET Methods
        public const string GET_BY_COMMAND_ELEMENT_ID = CommandElementServiceImpl.DIRECTORY + "/ById/CommandElement/";
        public const string GET_BY_AUTOMATION_ID = CommandElementServiceImpl.DIRECTORY + "/ById/Automation/";        

        // POST Methods
        public const string CREATE = CommandElementServiceImpl.DIRECTORY + "/Create/";
        public const string CREATE_FROM_EXISTING = CommandElementServiceImpl.DIRECTORY + "/Create/";

        // UPDATE Methods
        public const string UPDATE = CommandElementServiceImpl.DIRECTORY + "/Update/";

        // DELETE Methods
        public const string REMOVE_BY_COMMAND_ELEMENT_ID = CommandElementServiceImpl.DIRECTORY + "/ById/CommandElement/";
    }
}
using System;
using System.Collections.Generic;
using System.Net;
using System.IO;
using Newtonsoft.Json.Linq;
using System.Diagnostics;
using ReceiptRewards.PCL.Models;

namespace ReceiptRewards.PCL.ServiceAccessLayer.Database.Implementations
{
    public class CommandIdentifierServiceImpl {
        // Base URL
        private const string BASE_URL = "http://140.104.69.94:8080/ReceiptRewards_DataAccessLayer";
        private const string DIRECTORY = CommandIdentifierServiceImpl.BASE_URL + "/CommandIdentifier";

        // GET Methods
        public const string GET_BY_COMMAND_IDENTIFIER_ID = CommandIdentifierServiceImpl.DIRECTORY + "/ById/CommandIdentifier/";
        public const string GET_BY_COMMAND_ELEMENT_ID = CommandIdentifierServiceImpl.DIRECTORY + "/ById/CommandElement/";
        

        // POST Methods
        public const string CREATE = CommandIdentifierServiceImpl.DIRECTORY + "/Create/";

        // UPDATE Methods
        public const string UPDATE = CommandIdentifierServiceImpl.DIRECTORY + "/Update/";

        // DELETE Methods
        public const string REMOVE_BY_COMMAND_IDENTIFIER_ID = CommandIdentifierServiceImpl.DIRECTORY + "/ById/CommandIdentifier/";
        public const string REMOVE_BY_COMMAND_ELEMENT_ID = CommandIdentifierServiceImpl.DIRECTORY + "/ById/CommandElement/";
    }
}
using System;
using System.Collections.Generic;
using System.Net;
using System.IO;
using Newtonsoft.Json.Linq;
using System.Diagnostics;
using ReceiptRewards.PCL.Models;


namespace ReceiptRewards.Core.ServiceAccessLayer.Database.Implementations {

    public class CommandFormElementRelationServiceImpl {
        // Base URL
        private const string BASE_URL = "http://140.104.69.94:8080/ReceiptRewards_DataAccessLayer";
        private const string DIRECTORY = CommandFormElementRelationServiceImpl.BASE_URL + "/CommandFormElementRelation";

        // GET Methods
        public const string GET_BY_COMMAND_ELEMENT_ID = CommandFormElementRelationServiceImpl.DIRECTORY + "/ById/CommandElement/";
        public const string GET_BY_FORM_ELEMENT_ID = CommandFormElementRelationServiceImpl.DIRECTORY + "/ById/FormElement/";

        // POST Methods
        public const string CREATE = CommandFormElementRelationServiceImpl.DIRECTORY + "/Create/";

        // DELETE Methods
        public const string REMOVE_BY_COMMAND_FORM_ELEMENT_RELATION_ID = CommandFormElementRelationServiceImpl.DIRECTORY + "/ById/CommandFormElementRelation/";
        public const string REMOVE_BY_COMMAND_ELEMENT_ID = CommandFormElementRelationServiceImpl.DIRECTORY + "/ById/CommandElement/";
        public const string REMOVE_BY_FORM_ELEMENT_ID = CommandFormElementRelationServiceImpl.DIRECTORY + "/ById/FormElement/";
    }
}
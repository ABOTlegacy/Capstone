using System;
using System.Collections.Generic;
using System.Web;
using System.Configuration;
using System.Data.SqlClient;
using System.Net;
using System.IO;
using Newtonsoft.Json.Linq;
using System.Diagnostics;
using ReceiptRewards.PCL.BusinessLayer;
using ReceiptRewards.PCL.Models;
using ReceiptRewards.Core.ServiceAccessLayer.Database.Implementations;


namespace ReceiptReward {

    public class CommandFormElementRelationImpl {
        
        /**
         * Obtains a CommqneFormElementRelation by Id from the database
         * Calls webservice
         * @return A CommandElement object
         */
        public static List<CommandFormElementRelation> getByCommandElementId(int commandElementId) {
            // Instantiate Variables
            List<CommandFormElementRelation> commandFormElementRelations = new List<CommandFormElementRelation>();

            // Set up Request
            HttpWebRequest httpWebRequest = (HttpWebRequest) WebRequest.Create(CommandFormElementRelationServiceImpl.GET_BY_COMMAND_ELEMENT_ID + commandElementId);
            httpWebRequest.ContentType = "application/json";
            httpWebRequest.Accept = "application/json";
            httpWebRequest.Method = "GET";

            // Get the Response
            HttpWebResponse httpResponse = (HttpWebResponse) httpWebRequest.GetResponse();
            using (StreamReader streamReader = new StreamReader(httpResponse.GetResponseStream())) {
                commandFormElementRelations = CommonUtils.deserialize<List<CommandFormElementRelation>>(streamReader.ReadToEnd());
            }

            // Return
            return commandFormElementRelations;
        }



        /**
         * Obtains a CommqneFormElementRelation by Id from the database
         * Calls webservice
         * @return A CommandElement object
         */
        public static List<CommandFormElementRelation> getByFormElementId(int formElementId) {
            // Instantiate Variables
            List<CommandFormElementRelation> commandFormElementRelations = new List<CommandFormElementRelation>();

            // Set up Request
            HttpWebRequest httpWebRequest = (HttpWebRequest) WebRequest.Create(CommandFormElementRelationServiceImpl.GET_BY_FORM_ELEMENT_ID + formElementId);
            httpWebRequest.ContentType = "application/json";
            httpWebRequest.Accept = "application/json";
            httpWebRequest.Method = "GET";

            // Get the Response
            HttpWebResponse httpResponse = (HttpWebResponse) httpWebRequest.GetResponse();
            using (StreamReader streamReader = new StreamReader(httpResponse.GetResponseStream())) {
                commandFormElementRelations = CommonUtils.deserialize<List<CommandFormElementRelation>>(streamReader.ReadToEnd());
            }

            // Return
            return commandFormElementRelations;
        }



        /**
         * Create a new entry in the Database
         * Calls webservice
         */
        public static void create(CommandFormElementRelation commandFormElementRelation) {
            // Set up Request
            HttpWebRequest httpWebRequest = (HttpWebRequest) WebRequest.Create(CommandFormElementRelationServiceImpl.CREATE);
            httpWebRequest.ContentType = "application/json";
            httpWebRequest.Accept = "application/json";
            httpWebRequest.Method = "POST";

            // Send Request Data
            using (StreamWriter streamWriter = new StreamWriter(httpWebRequest.GetRequestStream())) {
                string json = CommonUtils.serialize(commandFormElementRelation);
                Debug.WriteLine(json);
                streamWriter.Write(json);
                streamWriter.Flush();
                streamWriter.Close();
            }

            // Get the Response
            HttpWebResponse httpResponse = (HttpWebResponse) httpWebRequest.GetResponse();
        }



        /**
         * Removes an entry in the Database by Id
         * Calls webservice
         */
        public static void removeByCommandFormElementRelationId(int commandFormElementRelationId) {
            // Set up Request
            HttpWebRequest httpWebRequest = (HttpWebRequest) WebRequest.Create(CommandFormElementRelationServiceImpl.REMOVE_BY_COMMAND_FORM_ELEMENT_RELATION_ID + commandFormElementRelationId);
            httpWebRequest.ContentType = "application/json";
            httpWebRequest.Accept = "application/json";
            httpWebRequest.Method = "DELETE";

            // Get the Response
            HttpWebResponse httpResponse = (HttpWebResponse) httpWebRequest.GetResponse();
        }



        /**
         * Removes an entry in the Database by Id
         * Calls webservice
         */
        public static void removeByCommandElementId(int commandElementId) {
            // Set up Request
            HttpWebRequest httpWebRequest = (HttpWebRequest) WebRequest.Create(CommandFormElementRelationServiceImpl.REMOVE_BY_COMMAND_ELEMENT_ID + commandElementId);
            httpWebRequest.ContentType = "application/json";
            httpWebRequest.Accept = "application/json";
            httpWebRequest.Method = "DELETE";

            // Get the Response
            HttpWebResponse httpResponse = (HttpWebResponse) httpWebRequest.GetResponse();
        }



        /**
         * Removes an entry in the Database by Id
         * Calls webservice
         */
        public static void removeByFormElementId(int formElementId) {
            // Set up Request
            HttpWebRequest httpWebRequest = (HttpWebRequest) WebRequest.Create(CommandFormElementRelationServiceImpl.REMOVE_BY_FORM_ELEMENT_ID + formElementId);
            httpWebRequest.ContentType = "application/json";
            httpWebRequest.Accept = "application/json";
            httpWebRequest.Method = "DELETE";

            // Get the Response
            HttpWebResponse httpResponse = (HttpWebResponse) httpWebRequest.GetResponse();
        }
    }
}
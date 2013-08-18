using System;
using System.Collections.Generic;
using System.Web;
using System.Configuration;
using System.Data.SqlClient;
using System.Net;
using System.IO;
using Newtonsoft.Json.Linq;
using System.Diagnostics;
using ReceiptRewards.PCL.Models;
using ReceiptRewards.PCL.BusinessLayer;
using ReceiptRewards.PCL.ServiceAccessLayer.Database.Implementations;


namespace ReceiptReward {

    public class CodeTypeImpl {
        
        /**
         * Obtains a list of all CodeTypes in the database
         * Calls webservice
         * @return A list of CodeType objects
         */
        public static List<CodeType> getAll() {
            // Instantiate Variables
            List<CodeType> codeTypes = new List<CodeType>();

            // Set up Request
            HttpWebRequest httpWebRequest = (HttpWebRequest) WebRequest.Create(CodeTypeServiceImpl.GET_ALL);
            httpWebRequest.ContentType = "application/json";
            httpWebRequest.Accept = "application/json";
            httpWebRequest.Method = "GET";
            
            // Get the Response
            HttpWebResponse httpResponse = (HttpWebResponse) httpWebRequest.GetResponse();
            using (StreamReader streamReader = new StreamReader(httpResponse.GetResponseStream())) {
                codeTypes = CommonUtils.deserialize<List<CodeType>>(streamReader.ReadToEnd());
            }

            // Return
            return codeTypes;
        }



        /**
         * Obtains a CodeType by Id from the database
         * Calls webservice
         * @return A CodeType object
         */
        public static CodeType getByCodeTypeId(int codeTypeId) {
            // Instantiate Variables
            CodeType codeType = new CodeType();

            // Set up Request
            HttpWebRequest httpWebRequest = (HttpWebRequest) WebRequest.Create(CodeTypeServiceImpl.GET_BY_CODE_TYPE_ID + codeTypeId);
            httpWebRequest.ContentType = "application/json";
            httpWebRequest.Accept = "application/json";
            httpWebRequest.Method = "GET";

            // Get the Response
            HttpWebResponse httpResponse = (HttpWebResponse) httpWebRequest.GetResponse();
            using (StreamReader streamReader = new StreamReader(httpResponse.GetResponseStream())) {
                codeType = CommonUtils.deserialize<CodeType>(streamReader.ReadToEnd());
            }

            // Return
            return codeType;
        }



        /**
         * Obtains a CodeType by Type from the database
         * Calls webservice
         * @return A CodeType object
         */
        public static CodeType getByType(string type) {
            // Instantiate Variables
            CodeType codeType = new CodeType();

            // Set up Request
            HttpWebRequest httpWebRequest = (HttpWebRequest) WebRequest.Create(CodeTypeServiceImpl.GET_BY_CODE_TYPE_TYPE + type);
            httpWebRequest.ContentType = "application/json";
            httpWebRequest.Accept = "application/json";
            httpWebRequest.Method = "GET";

            // Get the Response
            HttpWebResponse httpResponse = (HttpWebResponse) httpWebRequest.GetResponse();
            using (StreamReader streamReader = new StreamReader(httpResponse.GetResponseStream())) {
                codeType = CommonUtils.deserialize<CodeType>(streamReader.ReadToEnd());
            }

            // Return
            return codeType;
        }



        /**
         * Create a new CodeType entry in the Database
         * Calls webservice
         */
        public static void create(CodeType codeType) {
            // Set up Request
            HttpWebRequest httpWebRequest = (HttpWebRequest) WebRequest.Create(CodeTypeServiceImpl.CREATE);
            httpWebRequest.ContentType = "application/json";
            httpWebRequest.Accept = "application/json";
            httpWebRequest.Method = "POST";

            // Send Request Data
            using (StreamWriter streamWriter = new StreamWriter(httpWebRequest.GetRequestStream())) {
                string json = CommonUtils.serialize(codeType);
                Debug.WriteLine(json);
                streamWriter.Write(json);
                streamWriter.Flush();
                streamWriter.Close();
            }

            // Get the Response
            HttpWebResponse httpResponse = (HttpWebResponse) httpWebRequest.GetResponse();
        }



        /**
         * Update a CodeType entry in the Database
         * Calls webservice
         */
        public static void updateById(CodeType codeType) {
            // Set up Request
            HttpWebRequest httpWebRequest = (HttpWebRequest) WebRequest.Create(CodeTypeServiceImpl.UPDATE);
            httpWebRequest.ContentType = "application/json";
            httpWebRequest.Accept = "application/json";
            httpWebRequest.Method = "PUT";

            // Send Request Data
            using (StreamWriter streamWriter = new StreamWriter(httpWebRequest.GetRequestStream())) {
                string json = CommonUtils.serialize(codeType);
                Debug.WriteLine(json);
                streamWriter.Write(json);
                streamWriter.Flush();
                streamWriter.Close();
            }

            // Get the Response
            HttpWebResponse httpResponse = (HttpWebResponse) httpWebRequest.GetResponse();
        }



        /**
         * Removes a CodeType entry in the Database by Id
         * Calls webservice
         */
        public static void removeById(int codeTypeId) {
            // Set up Request
            HttpWebRequest httpWebRequest = (HttpWebRequest) WebRequest.Create(CodeTypeServiceImpl.REMOVE_BY_CODE_TYPE_ID + codeTypeId);
            httpWebRequest.ContentType = "application/json";
            httpWebRequest.Accept = "application/json";
            httpWebRequest.Method = "DELETE";

            // Get the Response
            HttpWebResponse httpResponse = (HttpWebResponse) httpWebRequest.GetResponse();
        }



        /**
         * Get the breadcrumbs for a question group
         */
        static public string getBreadcrumbs(int id) {
            // Instantiate Variables
            string tempStr = "";

            // Code Type
            tempStr = "<a class='breadcrumbCurrent' href='Code_Type.aspx?codeTypeId=" + id + "'>" + CodeTypeImpl.getByCodeTypeId(id).type + "</a>" + tempStr;

            // Homepage
            tempStr = "<a class='breadcrumbNotCurrent' href='Index.aspx'>Home</a> >> " + tempStr;

            // Output Links
            return tempStr;
        }

    }
}
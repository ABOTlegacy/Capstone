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

    public class CodeImpl {

        /**
         * Obtains a list of all Codes in the database
         * Calls webservice
         * @return A list of Code objects
         */
        public static List<Code> getAll() {
            // Instantiate Variables
            List<Code> codes = new List<Code>();

            // Set up Request
            HttpWebRequest httpWebRequest = (HttpWebRequest) WebRequest.Create(CodeServiceImpl.GET_ALL);
            httpWebRequest.ContentType = "application/json";
            httpWebRequest.Accept = "application/json";
            httpWebRequest.Method = "GET";
            
            // Get the Response
            HttpWebResponse httpResponse = (HttpWebResponse) httpWebRequest.GetResponse();
            using (StreamReader streamReader = new StreamReader(httpResponse.GetResponseStream())) {
                codes = CommonUtils.deserialize<List<Code>>(streamReader.ReadToEnd());
            }

            // Return
            return codes;
        }



        /**
         * Obtains a list of Codes by type in the database
         * Calls webservice
         * @return A list of Code objects
         */
        public static List<Code> getByType(int codeTypeId) {
            // Instantiate Variables
            List<Code> codes = new List<Code>();

            // Set up Request
            HttpWebRequest httpWebRequest = (HttpWebRequest) WebRequest.Create(CodeServiceImpl.GET_BY_CODE_TYPE_ID + codeTypeId);
            httpWebRequest.ContentType = "application/json";
            httpWebRequest.Accept = "application/json";
            httpWebRequest.Method = "GET";

            // Get the Response
            HttpWebResponse httpResponse = (HttpWebResponse) httpWebRequest.GetResponse();
            using (StreamReader streamReader = new StreamReader(httpResponse.GetResponseStream())) {
                codes = CommonUtils.deserialize<List<Code>>(streamReader.ReadToEnd());
            }

            // Return
            return codes;
        }



        /**
         * Obtains a Code by Id from the database
         * Calls webservice
         * @return A Code object
         */
        public static Code getByCodeId(int codeId) {
            // Instantiate Variables
            Code code = new Code();

            // Set up Request
            HttpWebRequest httpWebRequest = (HttpWebRequest) WebRequest.Create(CodeServiceImpl.GET_BY_CODE_ID + codeId);
            httpWebRequest.ContentType = "application/json";
            httpWebRequest.Accept = "application/json";
            httpWebRequest.Method = "GET";

            // Get the Response
            HttpWebResponse httpResponse = (HttpWebResponse) httpWebRequest.GetResponse();
            using (StreamReader streamReader = new StreamReader(httpResponse.GetResponseStream())) {
                code = CommonUtils.deserialize<Code>(streamReader.ReadToEnd());
            }

            // Return
            return code;
        }



        /**
         * Obtains a Code by Code from the database
         * Calls webservice
         * @return A Code object
         */
        public static Code getByCode(string codeStr) {
            // Instantiate Variables
            Code code = new Code();

            // Set up Request
            HttpWebRequest httpWebRequest = (HttpWebRequest) WebRequest.Create(CodeServiceImpl.GET_BY_CODE_CODE + codeStr);
            httpWebRequest.ContentType = "application/json";
            httpWebRequest.Accept = "application/json";
            httpWebRequest.Method = "GET";

            // Get the Response
            HttpWebResponse httpResponse = (HttpWebResponse) httpWebRequest.GetResponse();
            using (StreamReader streamReader = new StreamReader(httpResponse.GetResponseStream())) {
                code = CommonUtils.deserialize<Code>(streamReader.ReadToEnd());
            }

            // Return
            return code;
        }



        /**
         * Create a new Code entry in the Database
         * Calls webservice
         */
        public static void create(Code code) {
            // Set up Request
            HttpWebRequest httpWebRequest = (HttpWebRequest) WebRequest.Create(CodeServiceImpl.CREATE);
            httpWebRequest.ContentType = "application/json";
            httpWebRequest.Accept = "application/json";
            httpWebRequest.Method = "POST";

            // Send Request Data
            using (StreamWriter streamWriter = new StreamWriter(httpWebRequest.GetRequestStream())) {
                string json = CommonUtils.serialize(code);
                Debug.WriteLine(json);
                streamWriter.Write(json);
                streamWriter.Flush();
                streamWriter.Close();
            }

            // Get the Response
            HttpWebResponse httpResponse = (HttpWebResponse) httpWebRequest.GetResponse();
        }



        /**
         * Update entry in the Database
         * Calls webservice
         */
        public static void update(Code code) {
            // Set up Request
            HttpWebRequest httpWebRequest = (HttpWebRequest) WebRequest.Create(CodeServiceImpl.UPDATE);
            httpWebRequest.ContentType = "application/json";
            httpWebRequest.Accept = "application/json";
            httpWebRequest.Method = "PUT";

            // Send Request Data
            using (StreamWriter streamWriter = new StreamWriter(httpWebRequest.GetRequestStream())) {
                string json = CommonUtils.serialize(code);
                Debug.WriteLine(json);
                streamWriter.Write(json);
                streamWriter.Flush();
                streamWriter.Close();
            }

            // Get the Response
            HttpWebResponse httpResponse = (HttpWebResponse) httpWebRequest.GetResponse();
        }



        /**
         * Removes a Code entry in the Database by Id
         * Calls webservice
         */
        public static void removeById(int codeId) {
            // Set up Request
            HttpWebRequest httpWebRequest = (HttpWebRequest) WebRequest.Create(CodeServiceImpl.REMOVE_BY_CODE_ID + codeId);
            httpWebRequest.ContentType = "application/json";
            httpWebRequest.Accept = "application/json";
            httpWebRequest.Method = "DELETE";

            // Get the Response
            HttpWebResponse httpResponse = (HttpWebResponse) httpWebRequest.GetResponse();
        }


        /**
         * Get the breadcrumbs
         */
        static public string getBreadcrumbs(int id) {
            // Instantiate Variables
            string tempStr = "";
            Code code = CodeImpl.getByCodeId(id);
            CodeType codeType = CodeTypeImpl.getByCodeTypeId(code.codeType.codeTypeId);

            // Code Type
            tempStr = "<a class='breadcrumbCurrent' href='Code.aspx?codeId=" + code.codeId + "'>" + code.code + "</a>" + tempStr;

            // Code Type
            tempStr = "<a class='breadcrumbNotCurrent' href='Code_Type.aspx?codeTypeId=" + codeType.codeTypeId + "'>" + codeType.type + "</a> >> " + tempStr;

            // Homepage
            tempStr = "<a class='breadcrumbNotCurrent' href='Index.aspx'>Home</a> >> " + tempStr;

            // Output Links
            return tempStr;
        }

    }
}
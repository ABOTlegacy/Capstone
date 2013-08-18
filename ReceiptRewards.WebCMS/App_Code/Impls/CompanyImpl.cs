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

    public class CompanyImpl {
        
        /**
         * Obtains a list of all companies in the database
         * Calls webservice
         * @return A list of Company objects
         */
        public static List<Company> getAll() {
            // Instantiate Variables
            List<Company> companies = new List<Company>();

            // Set up Request
            HttpWebRequest httpWebRequest = (HttpWebRequest) WebRequest.Create(CompanyServiceImpl.GET_ALL);
            httpWebRequest.ContentType = "application/json";
            httpWebRequest.Accept = "application/json";
            httpWebRequest.Method = "GET";

            // Get the Response
            HttpWebResponse httpResponse = (HttpWebResponse) httpWebRequest.GetResponse();
            using (StreamReader streamReader = new StreamReader(httpResponse.GetResponseStream())) {
                companies = CommonUtils.deserialize<List<Company>>(streamReader.ReadToEnd());
            }

            // Return
            return companies;
        }



        /**
         * Obtains a Company by Id from the database
         * Calls webservice
         * @return A Company object
         */
        public static Company getByCompanyId(int companyId) {
            // Instantiate Variables
            Company company = new Company();

            // Set up Request
            HttpWebRequest httpWebRequest = (HttpWebRequest) WebRequest.Create(CompanyServiceImpl.GET_BY_COMPANY_ID + companyId);
            httpWebRequest.ContentType = "application/json";
            httpWebRequest.Accept = "application/json";
            httpWebRequest.Method = "GET";

            // Get the Response
            HttpWebResponse httpResponse = (HttpWebResponse) httpWebRequest.GetResponse();
            using (StreamReader streamReader = new StreamReader(httpResponse.GetResponseStream())) {
                company = CommonUtils.deserialize<Company>(streamReader.ReadToEnd());
            }

            // Return
            return company;
        }



        /**
         * Create a new entry in the Database
         * Calls webservice
         */
        public static void create(Company company) {
            // Set up Request
            HttpWebRequest httpWebRequest = (HttpWebRequest) WebRequest.Create(CompanyServiceImpl.CREATE);
            httpWebRequest.ContentType = "application/json";
            httpWebRequest.Accept = "application/json";
            httpWebRequest.Method = "POST";

            // Send Request Data
            using (StreamWriter streamWriter = new StreamWriter(httpWebRequest.GetRequestStream())) {
                string json = CommonUtils.serialize(company);
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
        public static void update(Company company) {
            // Set up Request
            HttpWebRequest httpWebRequest = (HttpWebRequest) WebRequest.Create(CompanyServiceImpl.UPDATE);
            httpWebRequest.ContentType = "application/json";
            httpWebRequest.Accept = "application/json";
            httpWebRequest.Method = "PUT";

            // Send Request Data
            using (StreamWriter streamWriter = new StreamWriter(httpWebRequest.GetRequestStream())) {
                string json = CommonUtils.serialize(company);
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
        public static void removeByCompanyId(int companyId) {
            // Set up Request
            HttpWebRequest httpWebRequest = (HttpWebRequest) WebRequest.Create(CompanyServiceImpl.REMOVE_BY_COMPANY_ID + companyId);
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
            Company company = CompanyImpl.getByCompanyId(id);

            // Code Type
            tempStr = "<a class='breadcrumbCurrent' href='Company.aspx?companyId=" + company.companyId + "'>" + company.name + "</a>" + tempStr;

            // Homepage
            tempStr = "<a class='breadcrumbNotCurrent' href='Index.aspx'>Home</a> >> " + tempStr;

            // Output Links
            return tempStr;
        }
    }
}
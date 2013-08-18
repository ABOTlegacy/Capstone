using Cirrious.CrossCore.Platform;
using Cirrious.MvvmCross.Plugins.Json;
using Newtonsoft.Json.Converters;
using System;

namespace ReceiptRewards.PCL.BusinessLayer {

    public class CommonUtils {
        private static readonly IMvxJsonConverter _jsonConverter = new MvxJsonConverter();


        /**
         * Simple method to easily trim the length of a string
         * @param str is the string to be trimmed
         * @param num is the number of spot where it should be trimmed at
         * @return string of the final string
         */
        public static string trim(string str, int num) {
            if(str == null) {
                return "";
            }
            if (str.Length > num) {
                return str.Substring(0, num);
            } else {
                return str;
            }
        }


        public static T deserialize<T>(string responseBody) {
            var toReturn = _jsonConverter.DeserializeObject<T>(responseBody);
            return toReturn;
        }

        public static string serialize(Object responseBody) {
            var toReturn = _jsonConverter.SerializeObject(responseBody);
            return toReturn;
        }

    }
}
using System;
using System.IO;
using System.Net;
using Cirrious.CrossCore;
using Cirrious.CrossCore.Platform;
using Cirrious.MvvmCross.Plugins.Json;
using System.Text;

namespace ReceiptRewards.PCL.BusinessLayer {
    public class SimpleRestService {
        private readonly IMvxJsonConverter _jsonConverter = new MvxJsonConverter();

        public SimpleRestService(IMvxJsonConverter jsonConverter) {
            _jsonConverter = jsonConverter;
        }

        public SimpleRestService() {
            // TODO: Complete member initialization
        }

        public void MakeRequest<T>(string requestUrl, string verb, Action<T> successAction, Action<Exception> errorAction) {
            var request = (HttpWebRequest) WebRequest.Create(requestUrl);
            request.Method = verb;
            request.Accept = "application/json";

            MakeRequest(
               request,
               (response) => {
                   MvxTrace.Trace("fefe");
                   if (successAction != null) {
                       T toReturn;
                       try {
                           toReturn = CommonUtils.deserialize<T>(response);
                       } catch (Exception ex) {
                           errorAction(ex);
                           return;
                       }
                       successAction(toReturn);
                   }
               },
               (error) => {
                   if (errorAction != null) {
                       errorAction(error);
                   }
               }
            );
        }

        private void MakeRequest(HttpWebRequest request, Action<string> successAction, Action<Exception> errorAction) {
            request.BeginGetResponse(token => {
                try {
                    using (var response = request.EndGetResponse(token)) {
                        using (var stream = response.GetResponseStream()) {
                            var reader = new StreamReader(stream);
                            successAction(reader.ReadToEnd());
                        }
                    }
                } catch (WebException ex) {
                    Mvx.Error("ERROR: '{0}' when making {1} request to {2}", ex.Message, request.Method, request.RequestUri.AbsoluteUri);
                    errorAction(ex);
                }
            }, null);
        }

        






        public void MakeRequestObj<T>(string url, string verb, Object obj, Action<T> onSuccess, Action<Exception> onError) {
            // Request
            var request = (HttpWebRequest) WebRequest.Create(url);
            request.ContentType = "application/json";
            request.Accept = "application/json";
            request.Method = verb;

            // Serialize Object to JSON
            string paramsFormatted = CommonUtils.serialize(obj);

            // The Request
            request.BeginGetRequestStream(ar => {

                // Sends the Data As the Object
                using (Stream postStream = request.EndGetRequestStream(ar)) {
                    Byte[] byteArray = Encoding.UTF8.GetBytes(paramsFormatted);
                    postStream.Write(byteArray, 0, byteArray.Length);
                    postStream.Flush();
                }

                // Get the Response
                request.BeginGetResponse(token => {
                    try {
                        using (var response = request.EndGetResponse(token)) {
                            using (var reader = new StreamReader(response.GetResponseStream())) {
                                String s = reader.ReadToEnd();
                                onSuccess(CommonUtils.deserialize<T>(s));
                            }
                        }
                    } catch (WebException ex) {
                        onError(ex);
                    }
                }, request);
            }, request);
        }
    }
}

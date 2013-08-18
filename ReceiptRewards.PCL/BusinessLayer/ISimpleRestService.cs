using System;

namespace ReceiptRewards.PCL.BusinessLayer {
    public interface ISimpleRestService {
        void MakeRequest<T>(string requestUrl, string verb, Action<T> successAction, Action<Exception> errorAction);
    }
}
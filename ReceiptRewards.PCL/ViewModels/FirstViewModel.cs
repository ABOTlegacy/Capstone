using Cirrious.CrossCore.Platform;
using Cirrious.MvvmCross.ViewModels;
using ReceiptRewards.PCL.BusinessLayer;
using ReceiptRewards.PCL.Models;
using System;
using System.Collections.Generic;

namespace ReceiptRewards.PCL.ViewModels {
    public class FirstViewModel {
		private string _hello = "";
        private readonly SimpleRestService _simpleRestService = new SimpleRestService();
        
        public string Hello {
            get {
                string address = string.Format("http://140.104.69.94:8080/rp/Company/All");
                _simpleRestService.MakeRequest<List<Company>>(address, "GET", result => _hello = result.Count + "!", error => _hello = error.Message);
                return _hello;
            }
			set { _hello = value; }
		}

        public void AsyncYeah(Action<String> action) {
            string address = string.Format("http://140.104.69.94:8080/rp/Company/All");
            _simpleRestService.MakeRequest<List<Company>>(address, "GET", result => {_hello = result.Count + "!"; action(_hello);}, error => _hello = error.Message);
        }
    }
}

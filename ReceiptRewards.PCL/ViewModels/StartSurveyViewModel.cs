using Cirrious.CrossCore.Platform;
using Cirrious.MvvmCross.ViewModels;
using ReceiptRewards.PCL.BusinessLayer;
using ReceiptRewards.PCL.Models;
using ReceiptRewards.PCL.ServiceAccessLayer.Proxy.Implementations;
using System;
using System.Collections.Generic;

namespace ReceiptRewards.PCL.ViewModels {
    public class StartSurveyViewModel {

        // Instantiate Variables
        private String _keyword = "";
		private List<Company> _companies = new List<Company>();
        private Action<List<Company>> _searchAction = null; // The Binding UI Action Method
        private readonly SimpleRestService _simpleRestService = new SimpleRestService();
        

        // Getter / Setter of Companies
        public List<Company> companies {
            get { return this._companies; }
            set { this._companies = value; }
		}


        // Getter / Setter of Keyword
        public String keyword {
            get { return this._keyword; }
            set {
                this._keyword = value;
                this.asyncSearch();
            }
        }


        // Getter / Setter of Keyword
        public Action<List<Company>> searchAction {
            get { return this._searchAction; }
            set { this._searchAction = value; }
        }


        // Call The Async Action
        private void asyncSearch() {
            _simpleRestService.MakeRequest<List<Company>>(string.Format("http://140.104.69.94:8080/rp/Company/All"), "GET", result => { this._companies = result; this._searchAction(this._companies); }, error => this._companies = null);
        }
    }
}

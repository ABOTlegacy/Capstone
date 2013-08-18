using Cirrious.CrossCore.Platform;
using Cirrious.MvvmCross.ViewModels;
using ReceiptRewards.PCL.BusinessLayer;
using ReceiptRewards.PCL.Models;
using ReceiptRewards.PCL.ServiceAccessLayer.Database.Implementations;
using System;
using System.Collections.Generic;
using System.Diagnostics;

namespace ReceiptRewards.PCL.ViewModels {
    public class BrowseCompanyViewModel {

        /** 
         * Instantiate Variables
         */

        // The List Objects
        private List<Company> _companies = new List<Company>();

        // The Binding UI Action Method
        private Action<List<Company>> _action = null;

        // The Rest Method
        private readonly SimpleRestService _simpleRestService = new SimpleRestService();
        
        

        // Getter / Setter of Companies
        public List<Company> companies {
            get { return _companies; }
            set { this._companies = value; }
		}



        // Getter / Setter of Action
        public Action<List<Company>> action {
            get { return this._action; }
            set {
                this._action = value;
                this.asyncAction();
            }
        }



        // Call The Async Action
        public void asyncAction() {
            _simpleRestService.MakeRequest<List<Company>>(
                CompanyServiceImpl.GET_ALL, 
                "GET", result => { 
                    this._companies = result; 
                    this._action(this._companies); 
                }, 
                error => {
                    this._companies = null;
                }
            );
        }



        // Refresh the View
        public void refresh() {
            this.asyncAction();
        }
    }
}

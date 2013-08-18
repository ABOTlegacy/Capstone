using Cirrious.CrossCore.Platform;
using Cirrious.MvvmCross.ViewModels;
using ReceiptRewards.PCL.BusinessLayer;
using ReceiptRewards.PCL.Models;
using ReceiptRewards.PCL.ServiceAccessLayer.Database.Implementations;
using System;
using System.Collections.Generic;

namespace ReceiptRewards.PCL.ViewModels {
    public class BrowseSurveyViewModel {

        /** 
         * Instantiate Variables
         */

        // The List Objects
        private List<Survey> _surveys = new List<Survey>();

        // The Binding UI Action Method
        private Action<List<Survey>> _action = null;

        // The Rest Method
        private readonly SimpleRestService _simpleRestService = new SimpleRestService();



        // Getter / Setter of Surveys
        public List<Survey> surveys {
            get { return this._surveys; }
            set { // Make Call to Update UI
                this._surveys = value;
            }
        }



        // Getter / Setter of Action
        public Action<List<Survey>> action {
            get { return this._action; }
            set {
                this._action = value;
                this.asyncAction();
            }
        }



        // Call The Async Action
        private void asyncAction() {
            _simpleRestService.MakeRequest<List<Survey>>(
                SurveyServiceImpl.GET_ALL, 
                "GET", 
                result => { 
                    this._surveys = result; 
                    this._action(this._surveys); 
                }, 
                error => {
                    this._surveys = null;
                }
            );
        }



        // Refresh the View
        public void refresh() {
            this.asyncAction();
        }
    }
}

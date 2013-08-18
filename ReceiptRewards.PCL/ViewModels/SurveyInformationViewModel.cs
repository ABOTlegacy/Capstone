using Cirrious.CrossCore.Platform;
using Cirrious.MvvmCross.ViewModels;
using ReceiptRewards.PCL.BusinessLayer;
using ReceiptRewards.PCL.Models;
using ReceiptRewards.PCL.ServiceAccessLayer.Database.Implementations;
using ReceiptRewards.PCL.ServiceAccessLayer.Proxy.Implementations;
using System;
using System.Collections.Generic;

namespace ReceiptRewards.PCL.ViewModels {
    public class SurveyInformationViewModel {

        // Instantiate Variables
        private int _companyId = 0;
		private Survey _survey = new Survey();
        private Action<Survey> _action = null;
        private readonly SimpleRestService _simpleRestService = new SimpleRestService();



        // Getter / Setter of Survey Id
        public int companyId {
            get { return this._companyId; }
            set { this._companyId = value; }
        }



        // Getter / Setter of Survey
        public Survey survey {
            get { return this._survey; }
            set { this._survey = value; }
		}



        // Getter / Setter of Action
        public Action<Survey> action {
            get { return this._action; }
            set { this._action = value; }
        }



        // Call The Async Action
        public void refresh() {
            _simpleRestService.MakeRequest<List<Survey>>(
                string.Format(SurveyServiceImpl.GET_BY_COMPANY_ID + this._companyId), 
                "GET", 
                result => { 
                    this._survey = result[0]; 
                    this._action(this._survey); 
                }, 
                error => { 
                    /* @TODO: CAll Error Method (this._actionError) */ 
                }
            );
        }
    }
}

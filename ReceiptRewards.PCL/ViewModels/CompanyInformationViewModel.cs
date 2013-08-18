using Cirrious.CrossCore.Platform;
using Cirrious.MvvmCross.ViewModels;
using ReceiptRewards.PCL.BusinessLayer;
using ReceiptRewards.PCL.Models;
using ReceiptRewards.PCL.ServiceAccessLayer.Proxy.Implementations;
using System;
using System.Collections.Generic;

namespace ReceiptRewards.PCL.ViewModels {
    public class CompanyInformationViewModel {

        // Instantiate Variables
        private int _companyId = 0;
        private string _logo = ""; // String of URL
		private Company _company = new Company();
        private Action<Company> _actionCompany = null;
        private Action<string> _actionLogo = null;
        private readonly SimpleRestService _simpleRestService = new SimpleRestService();



        // Getter / Setter of Company Id
        public int companyId {
            get { return this._companyId; }
            set { this._companyId = value; }
        }



        // Getter / Setter of Logo Url
        public string logo {
            get { return this._logo; }
            set { this._logo = value; }
        }



        // Getter / Setter of Comapny
        public Company company {
            get { return this._company; }
            set { this._company = value; }
		}



        // Getter / Setter of Action for Logo
        public Action<string> actionLogo {
            get { return this._actionLogo; }
            set { this._actionLogo = value; }
        }



        // Getter / Setter of Action for Company
        public Action<Company> actionCompany {
            get { return this._actionCompany; }
            set { this._actionCompany = value; }
        }



        // Call The Async Action
        public void refreshLogo() {
            _simpleRestService.MakeRequest<string>(
                string.Format(CompanyServiceImpl.GET_BY_ID + this._companyId), 
                "GET", 
                result => { 
                    this._logo = result; 
                    this._actionLogo(this._logo); 
                }, 
                error => { 
                    /* @TODO: CAll Error Method (this._actionLogoError) */ 
                }
            );
        }



        // Call The Async Action
        public void refreshCompany() {
            _simpleRestService.MakeRequest<List<Company>>(
                string.Format(CompanyServiceImpl.GET_BY_ID + this._companyId), 
                "GET", 
                result => { 
                    this._company = result[0]; 
                    this._actionCompany(this._company); 
                }, 
                error => { 
                    /* @TODO: CAll Error Method (this._actionCompanyError) */ 
                }
            );
        }
    }
}

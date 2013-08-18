using System.Collections.Generic;

namespace ReceiptRewards.PCL.Models {

    public class Company {

        private int _companyId;
        private string _name;
        private List<Survey> _surveys;


        public int companyId {
            get { return this._companyId; }
            set { this._companyId = value; }
        }

        public string name {
            get { return this._name; }
            set { this._name = value; }
        }

        public List<Survey> surveys {
            get { return this._surveys; }
            set { this._surveys = value; }
        }
    }
}
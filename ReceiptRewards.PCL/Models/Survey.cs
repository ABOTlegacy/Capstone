using System.Collections.Generic;

namespace ReceiptRewards.PCL.Models {

    public class Survey {

        private int _surveyId;
        private int _companyId;
        private string _name;
        private List<Revision> _revisions;

        public int surveyId {
            get { return _surveyId; }
            set { _surveyId = value; }
        }

        public int companyId {
            get { return _companyId; }
            set { _companyId = value; }
        }

        public string name {
            get { return _name; }
            set { _name = value; }
        }

        public List<Revision> revisions {
            get { return _revisions; }
            set { _revisions = value; }
        }
    }
}
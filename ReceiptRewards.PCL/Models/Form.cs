using System.Collections.Generic;

namespace ReceiptRewards.PCL.Models {

    public class Form {

        private int _formId;
        private List<FormElement> _formElements;
        private List<FormFlow> _formFlows;

        public int formId {
            get { return _formId; }
            set { _formId = value; }
        }

        public List<FormElement> formElements {
            get { return _formElements; }
            set { _formElements = value; }
        }

        public List<FormFlow> formFlows {
            get { return _formFlows; }
            set { _formFlows = value; }
        }
    }
}
using System.Collections.Generic;

namespace ReceiptRewards.PCL.Models {

    public class FormFlow {

        private int _formFlowId;
        private int _formId;
        private Question _question;
        private string _value;


        public int formFlowId {
            get { return _formFlowId; }
            set { _formFlowId = value; }
        }
        
        public int formId {
            get { return _formId; }
            set { _formId = value; }
        }

        public Question question {
            get { return _question; }
            set { _question = value; }
        }

        public string value {
            get { return _value; }
            set { _value = value; }
        }

    }
}
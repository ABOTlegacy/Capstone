using System;

namespace ReceiptRewards.PCL.Models {

    public class Question {

        private int _questionId;
        private int _revisionId;
        private string _name;
        private Form _form;
        private Automation _automation;



        public int questionId {
            get { return _questionId; }
            set { _questionId = value; }
        }

        public int revisionId {
            get { return _revisionId; }
            set { _revisionId = value; }
        }

        public string name {
            get { return _name; }
            set { _name = value; }
        }

        public Form form {
            get { return _form; }
            set { _form = value; }
        }

        public Automation automation {
            get { return _automation; }
            set { _automation = value; }
        }
    }
}
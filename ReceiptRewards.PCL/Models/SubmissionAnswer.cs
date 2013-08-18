using System;
using System.Collections.Generic;

namespace ReceiptRewards.PCL.Models {

    public class SubmissionAnswer {

        private int _submissionAnswerId;
        private int _submissionId;
        private int _questionId;
        private int _formElementId;
        private string _value;


        public int submissionAnswerId {
            get { return _submissionAnswerId; }
            set { _submissionAnswerId = value; }
        }

        public int submissionId {
            get { return _submissionId; }
            set { _submissionId = value; }
        }

        public int questionId {
            get { return _questionId; }
            set { _questionId = value; }
        }

        public int formElementId {
            get { return _formElementId; }
            set { _formElementId = value; }
        }

        public string value {
            get { return _value; }
            set { _value = value; }
        }

    }
}
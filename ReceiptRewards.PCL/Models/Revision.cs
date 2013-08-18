using System;

namespace ReceiptRewards.PCL.Models {

    public class Revision {

        private int _revisionId;
        private int _surveyId;
        private bool _active;
        private DateTime _dateCreated;
        private string _revisionNumber;
        private Question _question;
        private Reward _reward;


        public int revisionId {
            get { return _revisionId; }
            set { _revisionId = value; }
        }

        public int surveyId {
            get { return _surveyId; }
            set { _surveyId = value; }
        }

        public bool active {
            get { return _active; }
            set { _active = value; }
        }

        public DateTime dateCreated {
            get { return _dateCreated; }
            set { _dateCreated = value; }
        }

        public string revisionNumber {
            get { return _revisionNumber; }
            set { _revisionNumber = value; }
        }

        public Question question {
            get { return _question; }
            set { _question = value; }
        }

        public Reward reward {
            get { return _reward; }
            set { _reward = value; }
        }
    }
}
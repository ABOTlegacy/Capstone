using System;
using System.Collections.Generic;

namespace ReceiptRewards.PCL.Models {

    public class Submission {

        private int _submissionId;
        private int _revisionId;
        private DateTime _dateStarted;
        private DateTime _dateCompleted;
        private List<SubmissionAnswer> _submissionAnswers = new List<SubmissionAnswer>();
        private SubmissionReward _submissionReward;


        public int submissionId {
            get { return _submissionId; }
            set { _submissionId = value; }
        }

        public int revisionId {
            get { return _revisionId; }
            set { _revisionId = value; }
        }

        public DateTime dateStarted {
            get { return _dateStarted; }
            set { _dateStarted = value; }
        }

        public DateTime dateCompleted {
            get { return _dateCompleted; }
            set { _dateCompleted = value; }
        }

        public List<SubmissionAnswer> submissionAnswers {
            get { return _submissionAnswers; }
            set { _submissionAnswers = value; }
        }

        public SubmissionReward submissionReward{
            get { return _submissionReward; }
            set { _submissionReward = value; }
        }
    }
}
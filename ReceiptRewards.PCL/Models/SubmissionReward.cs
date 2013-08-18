using System;
using System.Collections.Generic;

namespace ReceiptRewards.PCL.Models {

    public class SubmissionReward {

        private int _submissionRewardId;
        private Reward _reward;
        private string _value;
        private bool _redeemed;


        public int submissionRewardId {
            get { return _submissionRewardId; }
            set { _submissionRewardId = value; }
        }

        public Reward reward {
            get { return _reward; }
            set { _reward = value; }
        }

        public string value {
            get { return _value; }
            set { _value = value; }
        }

        public bool redeemed {
            get { return _redeemed; }
            set { _redeemed = value; }
        }

    }
}
using System;

namespace ReceiptRewards.PCL.Models {

    public class Reward {

        private int _rewardId;
        private Code _code;
        private Form _form;


        public int rewardId {
            get { return _rewardId; }
            set { _rewardId = value; }
        }

        public Code code {
            get { return _code; }
            set { _code = value; }
        }

        public Form form {
            get { return _form; }
            set { _form = value; }
        }

    }
}
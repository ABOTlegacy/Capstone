using System.Collections.Generic;

namespace ReceiptRewards.PCL.Models {

    public class AutomationFlow {

        private int _automationFlowId;
        private int _automationId;
        private Question _question;
        private int _weight;


        public int automationFlowId {
            get { return _automationFlowId; }
            set { _automationFlowId = value; }
        }
        
        public int automationId {
            get { return _automationId; }
            set { _automationId = value; }
        }

        public Question question{
            get { return _question; }
            set { _question = value; }
        }

        public int weight {
            get { return _weight; }
            set { _weight = value; }
        }

    }
}
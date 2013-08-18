using System.Collections.Generic;

namespace ReceiptRewards.PCL.Models {

    public class Automation {

        private int _automationId;
        private List<CommandElement> _commandElements;
        private List<AutomationFlow> _automationFlows;


        public int automationId {
            get { return _automationId; }
            set { _automationId = value; }
        }

        public List<CommandElement> commandElements {
            get { return _commandElements; }
            set { _commandElements = value; }
        }

        public List<AutomationFlow> automationFlows {
            get { return _automationFlows; }
            set { _automationFlows = value; }
        }

    }
}
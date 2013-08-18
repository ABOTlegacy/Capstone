using System.Collections.Generic;

namespace ReceiptRewards.PCL.Models {

    public class CommandElement {

        private int _commandElementId;
        private Code _code;
        private List<CommandIdentifier> _commandIdentifiers;
        private List<CommandFormElementRelation> _formElements;
        private string _testData;


        public int commandElementId {
            get { return _commandElementId; }
            set { _commandElementId = value; }
        }

        public Code code {
            get { return _code; }
            set { _code = value; }
        }

        public List<CommandIdentifier> commandIdentifiers {
            get { return _commandIdentifiers; }
            set { _commandIdentifiers = value; }
        }

        public List<CommandFormElementRelation> formElements {
            get { return _formElements; }
            set { _formElements = value; }
        }

        public string testData {
            get { return _testData; }
            set { _testData = value; }
        }

    }
}
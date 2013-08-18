
namespace ReceiptRewards.PCL.Models {

    public class CommandIdentifier {

        private int _commandIdentifierId;
        private int _commandElementId;
        private Code _code;
        private string _value;


        public int commandIdentifierId {
            get { return _commandIdentifierId; }
            set { _commandIdentifierId = value; }
        }

        public int commandElementId {
            get { return _commandElementId; }
            set { _commandElementId = value; }
        }

        public Code code {
            get { return _code; }
            set { _code = value; }
        }

        public string value {
            get { return _value; }
            set { _value = value; }
        }

    }
}
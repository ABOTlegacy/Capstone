
namespace ReceiptRewards.PCL.Models {

    public class CodeType {
        private int _codeTypeId;
        private string _type;
        private string _description;

        public int codeTypeId {
            get { return _codeTypeId; }
            set { _codeTypeId = value; }
        }

        public string type {
            get { return _type; }
            set { _type = value; }
        }

        public string description {
            get { return _description; }
            set { _description = value; }
        }
    }
}
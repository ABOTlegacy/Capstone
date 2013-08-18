
namespace ReceiptRewards.PCL.Models {

    public class Code {

        private int _codeId;
        private string _code;
        private string _name;
        private string _description;
        private CodeType _codeType;


        public int codeId {
            get { return _codeId; }
            set { _codeId = value; }
        }

        public string code {
            get { return _code; }
            set { _code = value; }
        }

        public string name {
            get { return _name; }
            set { _name = value; }
        }

        public string description {
            get { return _description; }
            set { _description = value; }
        }

        public CodeType codeType {
            get { return _codeType; }
            set { _codeType = value; }
        }

    }
}
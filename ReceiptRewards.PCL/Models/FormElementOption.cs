
namespace ReceiptRewards.PCL.Models {

    public class FormElementOption {

        private int _formElementOptionId;
        private int _formElementId;
        private DisplayText _displayText;
        private string _value;


        public int formElementOptionId {
            get { return _formElementOptionId; }
            set { _formElementOptionId = value; }
        }

        public int formElementId {
            get { return _formElementId; }
            set { _formElementId = value; }
        }

        public DisplayText displayText {
            get { return _displayText; }
            set { _displayText = value; }
        }

        public string value {
            get { return _value; }
            set { _value = value; }
        }
    }
}
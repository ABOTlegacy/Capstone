
namespace ReceiptRewards.PCL.Models {

    public class DisplayTextTranslation {

        private int _displayTextTranslationId;
        private int _displayTranslationId;
        private int _displayTextId;
        private Code _code;
        private string _value;


        public int displayTextTranslationId {
            get { return _displayTextTranslationId; }
            set { _displayTextTranslationId = value; }
        }

        public int displayTranslationId {
            get { return _displayTranslationId; }
            set { _displayTranslationId = value; }
        }

        public int displayTextId {
            get { return _displayTextId; }
            set { _displayTextId = value; }
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
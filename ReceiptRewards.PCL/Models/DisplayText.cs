using System;
using System.Collections.Generic;

namespace ReceiptRewards.PCL.Models {

    public class DisplayText {

        private int _displayTextId;
        private string _displayTextTranslation;
        private List<DisplayTextTranslation> _displayTextTranslations;


        public int displayTextId {
            get { return _displayTextId; }
            set { _displayTextId = value; }
        }

        public string displayTextTranslation {
            get { return _displayTextTranslation; }
            set { _displayTextTranslation = value; }
        }

        public List<DisplayTextTranslation> displayTextTranslations {
            get { return _displayTextTranslations; }
            set { _displayTextTranslations = value; }
        }

    }
}
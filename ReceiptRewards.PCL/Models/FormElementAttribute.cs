using System;

namespace ReceiptRewards.PCL.Models
{

    public class FormElementAttribute {

        private int _formElementAttributeId;
        private int _formElementId;
        private Code _code;
        private string _value;


        public int formElementAttributeId {
            get { return _formElementAttributeId; }
            set { _formElementAttributeId = value; }
        }

        public int formElementId {
            get { return _formElementId; }
            set { _formElementId = value; }
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
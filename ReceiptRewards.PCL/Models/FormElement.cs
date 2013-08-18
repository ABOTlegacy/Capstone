using System;
using System.Collections.Generic;

namespace ReceiptRewards.PCL.Models
{

    public class FormElement {

        private int _formElementId;
        private Code _code;
        private List<FormElementOption> _formElementOptions;
        private List<FormElementAttribute> _formElementAttributes;


        public int formElementId {
            get { return _formElementId; }
            set { _formElementId = value; }
        }

        public Code code {
            get { return _code; }
            set { _code = value; }
        }

        public List<FormElementOption> formElementOptions {
            get { return _formElementOptions; }
            set { _formElementOptions = value; }
        }

        public List<FormElementAttribute> formElementAttributes {
            get { return _formElementAttributes; }
            set { _formElementAttributes = value; }
        }

    }
}
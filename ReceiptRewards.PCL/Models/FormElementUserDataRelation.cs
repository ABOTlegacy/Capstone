using System.Collections.Generic;

namespace ReceiptRewards.PCL.Models {

    public class FormElementUserDataRelation {

        private int _formElementUserDataRelationId;
        private int _formElementId;
        private int _userDataId;


        public int formElementUserDataRelationId {
            get { return _formElementUserDataRelationId; }
            set { _formElementUserDataRelationId = value; }
        }

        public int formElementId {
            get { return _formElementId; }
            set { _formElementId = value; }
        }

        public int userDataId {
            get { return _userDataId; }
            set { _userDataId = value; }
        }

    }
}
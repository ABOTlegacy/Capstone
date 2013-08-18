using System;

namespace ReceiptRewards.PCL.Models {

    public class CommandFormElementRelation {

        private int _commandFormElementRelationId;
        private int _commandElementId;
        private int _formElementId;


        public int commandFormElementRelationId {
            get { return _commandFormElementRelationId; }
            set { _commandFormElementRelationId = value; }
        }

        public int commandElementId {
            get { return _commandElementId; }
            set { _commandElementId = value; }
        }

        public int formElementId {
            get { return _formElementId; }
            set { _formElementId = value; }
        }
    }
}
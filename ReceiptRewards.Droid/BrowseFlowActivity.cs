using Android.App;
using Android.OS;

namespace ReceiptRewards.Droid2 {

    [Activity(Label = "Receipt Rewards")]
    public class BrowseFlowActivity : DrawerTabFragmentActivity {
        
        
        /**
         * Activity Constructor
         */
        protected override void OnCreate(Bundle bundle) {
            // Call The Super Constructor
            base.OnCreate(bundle);

            // Add the Fragments for this View
            this._tabsAdapter.AddTab(this._tabHost.NewTabSpec("simple").SetIndicator("Browse Companys"), Java.Lang.Class.FromType(typeof(BrowseCompanyFragment)), null);
            this._tabsAdapter.AddTab(this._tabHost.NewTabSpec("contacts").SetIndicator("Contact Us"), Java.Lang.Class.FromType(typeof(ContactUsFragment)), null);
        }
    }
}


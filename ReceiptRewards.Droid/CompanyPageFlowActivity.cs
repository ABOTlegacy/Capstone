using Android.App;
using Android.OS;

namespace ReceiptRewards.Droid2 {

    [Activity(Label = "Receipt Rewards")]
    public class CompanyPageFlowActivity : DrawerTabFragmentActivity {
        
        
        /**
         * Activity Constructor
         */
        protected override void OnCreate(Bundle bundle) {
            // Call The Super Constructor
            base.OnCreate(bundle);

            // Add the Fragments for this View
            this._tabsAdapter.AddTab(this._tabHost.NewTabSpec("simple").SetIndicator("Company"), Java.Lang.Class.FromType(typeof(CompanyInformationFragment)), null);
            this._tabsAdapter.AddTab(this._tabHost.NewTabSpec("simple").SetIndicator("Survey"), Java.Lang.Class.FromType(typeof(SurveyInformationFragment)), null);
        }
    }
}


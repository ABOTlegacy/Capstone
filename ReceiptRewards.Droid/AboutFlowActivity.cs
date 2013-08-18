using Android.App;
using Android.Content;
using Android.Graphics;
using Android.OS;
using Android.Support.V4.View;
using Android.Support.V4.Widget;
using Android.Views;
using Android.Widget;

namespace ReceiptRewards.Droid2 {

    [Activity(Label = "Receipt Rewards", Icon = "@drawable/icon")]
    public class AboutFlowActivity : DrawerTabFragmentActivity {
        
        
        /**
         * Activity Constructor
         */
        protected override void OnCreate(Bundle bundle) {
            // Call The Super Constructor
            base.OnCreate(bundle);

            // Add the Fragments for this View
            this._tabsAdapter.AddTab(this._tabHost.NewTabSpec("simple").SetIndicator("About"), Java.Lang.Class.FromType(typeof(AboutFragment)), null);
            this._tabsAdapter.AddTab(this._tabHost.NewTabSpec("contacts").SetIndicator("Suggestions"), Java.Lang.Class.FromType(typeof(ContactUsFragment)), null);
        }
    }
}


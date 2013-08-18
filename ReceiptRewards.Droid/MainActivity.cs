using Android.App;
using Android.Content;
using Android.Graphics;
using Android.OS;
using Android.Support.V4.View;
using Android.Support.V4.Widget;
using Android.Views;
using Android.Widget;

namespace ReceiptRewards.Droid2 {

    [Activity(Label = "Receipt Rewards", MainLauncher = true, Icon = "@drawable/icon")]
    public class MainActivity : DrawerTabFragmentActivity {
        
        
        /**
         * Activity Constructor
         */
        protected override void OnCreate(Bundle bundle) {
            // Call The Super Constructor
            base.OnCreate(bundle);

            // Add the Fragments for this View
            this._tabsAdapter.AddTab(this._tabHost.NewTabSpec("Quick-Start").SetIndicator("Custom"), Java.Lang.Class.FromType(typeof(StartSearchFragment)), null);
        }
    }
}


using Android.App;
using Android.OS;
using Android.Views;
using Android.Widget;

namespace ReceiptRewards.Droid2 {

    public class SurveySuccessFragment : Android.Support.V4.App.Fragment {

        public override View OnCreateView(LayoutInflater p0, ViewGroup p1, Bundle p2) {
            // Instantiate Variables
            Activity activity = this.Activity;

            // Create a Linear Layout
            LinearLayout llAboutUs = new LinearLayout(p0.Context);
            // Add Button
            TextView tv = new TextView(p0.Context);
            tv.Text = "HELLO WORLD: SUCCESS PAGE";
            llAboutUs.AddView(tv);

            return llAboutUs;
        }
    }
}
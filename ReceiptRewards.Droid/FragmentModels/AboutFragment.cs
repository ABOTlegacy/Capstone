using Android.App;
using Android.OS;
using Android.Views;
using Android.Widget;

namespace ReceiptRewards.Droid2 {

    public class AboutFragment : Android.Support.V4.App.Fragment {

        public override View OnCreateView(LayoutInflater p0, ViewGroup p1, Bundle p2) {
            // Instantiate Variables
            Activity activity = this.Activity;

            // Create a Linear Layout
            LinearLayout llAboutUs = new LinearLayout(p0.Context);
            // Add Button
            TextView tv = new TextView(p0.Context);
            tv.Text = "" +
                        "About Page\n\n" +
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed sit amet magna non neque ullamcorper blandit quis quis mauris. Suspendisse potenti. Duis eros ligula, interdum in viverra nec, facilisis sit amet mi. Aliquam adipiscing ipsum turpis, in imperdiet orci vehicula a. Cras lorem orci, mollis sit amet tortor ac, dignissim luctus est. Nam commodo nunc at risus elementum congue. Morbi et pellentesque velit. Ut id consectetur enim.\n\n" +
                        "Morbi lacinia mi quis risus lacinia, quis porttitor nibh consectetur. Mauris dictum nec magna sed accumsan. Fusce et enim sed ante ullamcorper iaculis at vel purus. Vivamus mollis porttitor dictum. Cras aliquam vulputate velit, vitae fringilla mi tristique sit amet. Sed facilisis nunc eget tincidunt ultrices. Sed lacinia elementum facilisis.\n\n" +
                        "";
            llAboutUs.AddView(tv);

            return llAboutUs;
        }
    }
}
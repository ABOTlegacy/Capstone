using Android.App;
using Android.OS;
using Android.Views;
using Android.Widget;

namespace ReceiptRewards.Droid2 {

    public class DefaultFragment : Android.Support.V4.App.Fragment
    {
        public static string ArgPlanetNumber = "planet_number";

        public override View OnCreateView(LayoutInflater p0, ViewGroup p1, Bundle p2) {
            var rootView = p0.Inflate(Resource.Layout.FragmentPlanet, p1, false);
            //var imageId = Resources.GetIdentifier("dk.ostebaronen.drawersample:drawable/venus", null, null);
            //rootView.FindViewById<ImageView>(Resource.Id.image).SetImageResource(imageId);
            return rootView;
        }
    }
}
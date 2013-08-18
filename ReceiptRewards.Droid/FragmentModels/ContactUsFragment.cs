using Android.App;
using Android.Graphics;
using Android.OS;
using Android.Views;
using Android.Widget;

namespace ReceiptRewards.Droid2 {

    public class ContactUsFragment : Android.Support.V4.App.Fragment {

        public override View OnCreateView(LayoutInflater p0, ViewGroup p1, Bundle p2) {
            // Instantiate Variables
            Activity activity = this.Activity;

            // Create a Linear Layout
            LinearLayout llContactUs = new LinearLayout(p0.Context);
            llContactUs.SetBackgroundColor(Color.Purple);
            llContactUs.SetGravity(GravityFlags.Left);
            
            // Add Text
            TextView tv = new TextView(p0.Context);
            tv.Text = "" +
                        "Suggestion\n\n" +
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed sit amet magna non neque ullamcorper blandit quis quis mauris." +
                        "";
            llContactUs.AddView(tv);

            // Add Name Field
            TextView lblName = new TextView(p0.Context);
            lblName.Text = "Name:";
            llContactUs.AddView(lblName);
            EditText txtName = new EditText(p0.Context);
            txtName.SetMinWidth(250);
            txtName.SetBackgroundColor(Color.Red);
            llContactUs.AddView(txtName);

            // Add Comment Field
            TextView lblComment = new TextView(p0.Context);
            lblComment.Text = "Message:";
            llContactUs.AddView(lblComment);
            EditText txtComment = new EditText(p0.Context);
            txtComment.SetMinWidth(250);
            txtComment.SetMinHeight(50);
            txtComment.SetBackgroundColor(Color.PowderBlue);
            llContactUs.AddView(txtComment);

            // Return
            return llContactUs;
        }
    }
}
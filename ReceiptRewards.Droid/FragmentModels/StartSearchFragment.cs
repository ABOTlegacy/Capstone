using Android.App;
using Android.OS;
using Android.Views;
using Android.Widget;

namespace ReceiptRewards.Droid2 {

    public class StartSearchFragment : Android.Support.V4.App.Fragment {
        ListView lvResults = null;
        EditText txtKeyword = null;
        Button btnSearch = null;

        public override View OnCreateView(LayoutInflater p0, ViewGroup p1, Bundle p2) {
            // Create a Linear Layout
            LinearLayout llStartSurvey = new LinearLayout(p0.Context);

            // Add Textbox
            txtKeyword = new EditText(p0.Context);
            txtKeyword.Id = 123;
            txtKeyword.Text = "Enter Company Name";
            llStartSurvey.AddView(txtKeyword);

            // Add Button
            btnSearch = new Button(p0.Context);
            btnSearch.Id = 124;
            btnSearch.Text = "Search";
            llStartSurvey.AddView(btnSearch);

            // Add ListView
            lvResults = new ListView(p0.Context);
            lvResults.Id = 125;
            llStartSurvey.AddView(lvResults);

            // Set the Linear Layout as our Content View
            return llStartSurvey;
        }
    }
}
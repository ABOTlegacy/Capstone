using Android.App;
using Android.Content;
using Android.OS;
using Android.Views;
using Android.Widget;
using ReceiptRewards.PCL.Models;
using ReceiptRewards.PCL.ViewModels;
using System;
using System.Collections.Generic;
using System.Drawing;
using System.Threading;

namespace ReceiptRewards.Droid2 {

    public class BrowseCompanyFragment : Android.Support.V4.App.Fragment {
        // Global Variables
        BrowseCompanyViewModel _viewModel = new BrowseCompanyViewModel();
        ListView _lv;
        LinearLayout _ll;
        Context _context;
        Activity _activity;
        BroseSurveyListAdapter _listAdapter;
        
        public override View OnCreateView(LayoutInflater p0, ViewGroup p1, Bundle p2) {
            // Instantiate Variables
            this._activity = this.Activity;
            this._context = p0.Context;

            // Creat Linear Layout
            this._ll = new LinearLayout(this._context);
            this._ll.Orientation = Orientation.Vertical;
            this._ll.Id = 5892;
            
            // Textbox
            TextView tvLoading = new TextView(this._context);
            tvLoading.Text = "Loading...";
            tvLoading.Id = 4562;
            this._ll.AddView(tvLoading);

            // Create a List View
            this._listAdapter = new BroseSurveyListAdapter(this, this._context);
            this._lv = new ListView(this._context);
            this._lv.Adapter = this._listAdapter;
            this._lv.ItemClick += (object sender, Android.Widget.AdapterView.ItemClickEventArgs e) => {
                //Get our item from the list adapter
                Company company = this._listAdapter.items[e.Position];

                // Go To Company Page
                Intent intent = new Intent(this._activity, typeof(CompanyPageFlowActivity));
                intent.PutExtra("companyId", company.companyId + "");
                this._activity.StartActivity(intent);
            };
            this._ll.AddView(this._lv);

            // Start the Threading
            ThreadPool.QueueUserWorkItem(o => this.slowMethod(this._activity));

            // Return View
            return this._ll;
        }




        /********************** Event Handlers *************************/
        // http://docs.xamarin.com/guides/android/advanced_topics/writing_responsive_applications
        private void slowMethod(Activity pi) {
            // How Long the Thread Should Sleep For Before Checking
            Thread.Sleep(5000);
            
            // Set The Action
            this._viewModel.action = (value => {
                pi.RunOnUiThread(() => {
                    TextView tvLoading = pi.FindViewById<TextView>(4562);
                    tvLoading.Text = "";
                    tvLoading.Visibility = ViewStates.Gone;

                    this._listAdapter.items = value;
                    this._listAdapter.NotifyDataSetChanged();

                    

                    /*foreach(Company company in value) {
                        // Creates the Layout Holder of the Text
                        LinearLayout listItem = new LinearLayout(this._context);
                        listItem.Orientation = Orientation.Vertical;

                        // Add the Company Name
                        TextView tv2 = new TextView(this._context);
                        tv2.Text = company.name;
                        tv2.SetTextColor(Android.Graphics.Color.AliceBlue);
                        listItem.AddView(tv2);
                        //this._ll.AddView(tv2);
                        
                        // Category Name
                        /*TextView tv3 = new TextView(this._context);
                        tv3.Text = "category";
                        tv3.SetTextColor(Android.Graphics.Color.White);
                        listItem.AddView(tv3);*/

                        // Add View to List
                        //this._lv.AddView(listItem);
                   // }
                });
            });
        }
    }





    /**
     * The Browse Company Adapter
     */
    public class BroseSurveyListAdapter : BaseAdapter {
        Android.Support.V4.App.Fragment fragment;
        Context context;
        public List<Company> items;

        public BroseSurveyListAdapter(Android.Support.V4.App.Fragment fragment, Context context) : base() {
            this.fragment = fragment;
            this.context = context;

            //For demo purposes we hard code some data here
            this.items = new List<Company>();
        }

        public override int Count {
            get { return items.Count; }
        }

        public override Java.Lang.Object GetItem(int position) {
            return position;
        }

        public override long GetItemId(int position) {
            return position;
        }

        public override View GetView(int position, View convertView, ViewGroup parent) {
            //Get our object for this position
            var item = items[position];

            //Try to reuse convertView if it's not  null, otherwise inflate it from our item layout
            // This gives us some performance gains by not always inflating a new view
            // This will sound familiar to MonoTouch developers with UITableViewCell.DequeueReusableCell()
            var view = new LinearLayout(this.context);
            view.Orientation = Orientation.Vertical;

            //Find references to each subview in the list item's view
            //var imageItem = view.FindViewById(Resource.id.imageItem) as ImageView;
            TextView textTop = new TextView(this.context);
            TextView textBottom = new TextView(this.context);

            //Assign this item's values to the various subviews
            //imageItem.SetImageResource(item.Image);
            textTop.SetText(item.name, TextView.BufferType.Normal);
            textBottom.SetText(item.companyId + "", TextView.BufferType.Normal);

            // Add to view
            view.AddView(textTop);
            view.AddView(textBottom);

            //Finally return the view
            return view;
        }

        public Company GetItemAtPosition(int position) {
            return items[position];
        }
    }
}
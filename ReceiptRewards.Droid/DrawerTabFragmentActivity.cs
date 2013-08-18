using Android.App;
using Android.Content;
using Android.Content.Res;
using Android.Support.V4.Widget;
using Android.Views;
using Android.Widget;
using Android.OS;
using Android.Support.V4.View;
using Android.Support.V4.App;
using System;
using System.Collections.Generic;
using Java.Lang;
using LegacyBar.Library.BarActions;
using LegacyBar.Library.BarBase;

namespace ReceiptRewards.Droid2 {

    [Activity(Label = "@string/fragment_tabs_pager")]
    [IntentFilter(new[] { Intent.ActionMain }, Categories = new[] { "mono.support4demo.sample" })]
    public class DrawerTabFragmentActivity : FragmentActivity {

        // Global Variables
        protected DrawerLayout _drawer;
        protected TabHost _tabHost;
        protected ViewPager _viewPager;
        protected TabsAdapter _tabsAdapter;



        /**
         * Constructor Method
         */
        protected override void OnCreate(Bundle savedInstanceState) {
            // Implement the Super Constructor
            base.OnCreate(savedInstanceState);

            // Removes the Title Window Bar
            this.RequestWindowFeature(WindowFeatures.NoTitle);

            // Set the Layout View
            SetContentView(Resource.Layout.FragmentTabsPager);

            // Define Variables
            this._tabHost = FindViewById<TabHost>(Android.Resource.Id.TabHost);
            this._tabHost.Setup();
            this._viewPager = FindViewById<ViewPager>(Resource.Id.pager);
            this._tabsAdapter = new TabsAdapter(this, this._tabHost, this._viewPager);

            // Sets the Pager to the first tab once first started
            if (savedInstanceState != null) {
                this._tabHost.SetCurrentTabByTag(savedInstanceState.GetString("tab"));
            }

            // Set up the Action Bar
            this.setUpActionBar();

            // Set up the Drawer
            this._drawer = new DrawerModel().generate(this);
        }

        /**
         * In Charge of the Action Bar Button Actions
         */
        public override bool OnOptionsItemSelected(IMenuItem item) {
            switch (item.ItemId) {
                /*case Resource.Id.action_websearch: {
                        var intent = new Intent(Intent.ActionWebSearch);
                        intent.PutExtra(SearchManager.Query, ActionBar.Title);

                        if ((intent.ResolveActivity(PackageManager)) != null) {
                            StartActivity(intent);
                        } else {
                            Toast.MakeText(this, Resource.String.app_not_available, ToastLength.Long).Show();
                        }
                        return true;
                    }*/
                /*case Resource.Id.action_slidingpane: {
                        var intent = new Intent(this, typeof(SlidingPaneLayoutActivity));
                        intent.AddFlags(ActivityFlags.ClearTop);
                        StartActivity(intent);
                        return true;
                    }*/
                default: { // Else It will be drawer 
                    // @TODO: Should just make drawer an icon and then that would be great
                    // @TODO: Also put logic for when the icon is clicked and the drawer is already opened. It should then close it.
                    this._drawer.OpenDrawer(Resource.Id.left_drawer);
                    return true;
                    //return base.OnOptionsItemSelected(item);
                }
            }
        }



        /**
         * Pretty Much Sets the Title and the Icon to clickable.
         */
        private void setUpActionBar() {
            LegacyBar.Library.Bar.LegacyBar legacyBar = FindViewById<LegacyBar.Library.Bar.LegacyBar>(Resource.Id.actionbar);
            legacyBar.SetHomeLogo(Resource.Drawable.icon);
            legacyBar.Title = "Receipt Rewards (Alpha)";

            ImageButton btnDrawer = new ImageButton(this);
            //btnDrawer.
            //legacyBar.AddView(



            // Set up Title
            //this.ActionBar.Title = "Receipt Rewards";

            // Configure Action Bar Buttons
            //this.ActionBar.SetDisplayHomeAsUpEnabled(true);
            //this.ActionBar.SetHomeButtonEnabled(true);
        }


        protected override void OnSaveInstanceState(Bundle outState) {
            base.OnSaveInstanceState(outState);
            outState.PutString("tab", this._tabHost.CurrentTabTag);
        }





    }
}


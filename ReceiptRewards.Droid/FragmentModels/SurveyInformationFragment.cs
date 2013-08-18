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

    public class SurveyInformationFragment : Android.Support.V4.App.Fragment {
        // Global Variables
        SurveyInformationViewModel _viewModel = new SurveyInformationViewModel();
        LinearLayout _llMain;
        Context _context;
        Activity _activity;
        
        public override View OnCreateView(LayoutInflater p0, ViewGroup p1, Bundle p2) {
            // Instantiate Variables
            this._activity = this.Activity;
            this._context = p0.Context;

            // Set The State
            string companyId = this._activity.Intent.GetStringExtra("companyId");
            if(companyId == null || companyId.Length == 0) { // If Null Return Home Screen
                Intent intent = new Intent(this._activity, typeof(MainActivity));
                this._activity.StartActivity(intent);
            } else { // Set the Company Id in View Model
                this._viewModel.companyId = int.Parse(companyId);
            }

            // Creat Linear Layout
            this._llMain = new LinearLayout(this._context);
            this._llMain.Orientation = Orientation.Vertical;
            this._llMain.Id = 5892;
            
            // Textbox
            TextView tv1 = new TextView(this._context);
            tv1.Text = "Loading...";
            tv1.Id = 4562;
            this._llMain.AddView(tv1);

            // Start the Threading
            ThreadPool.QueueUserWorkItem(o => this.slowMethod(this._activity));

            // Return View
            return this._llMain;
        }




        /********************** Event Handlers *************************/
        // http://docs.xamarin.com/guides/android/advanced_topics/writing_responsive_applications
        private void slowMethod(Activity pi) {
            // How Long the Thread Should Sleep For Before Checking
            Thread.Sleep(5000);
            
            // Set The Action
            this._viewModel.action = (value => {
                pi.RunOnUiThread(() => {
                    TextView tv1 = pi.FindViewById<TextView>(4562);
                    tv1.Text = "";
                    tv1.Visibility = ViewStates.Gone;

                    // Name
                    LinearLayout llName = new LinearLayout(this._context);
                    TextView lblName = new TextView(this._context);
                    lblName.Text = "Category: ";
                    llName.AddView(lblName);
                    TextView txtName = new TextView(this._context);
                    txtName.Text = this._viewModel.survey.name;
                    llName.AddView(txtName);
                    this._llMain.AddView(llName);

                    // Category
                    LinearLayout llCategory = new LinearLayout(this._context);
                    TextView lblCategory = new TextView(this._context);
                    lblCategory.Text = "Category: ";
                    llCategory.AddView(lblCategory);
                    TextView txtCategory = new TextView(this._context);
                    txtCategory.Text = "@TODO: Category";
                    llCategory.AddView(txtCategory);
                    this._llMain.AddView(llCategory);

                    // Id
                    LinearLayout llId = new LinearLayout(this._context);
                    TextView lblId = new TextView(this._context);
                    lblId.Text = "Category: ";
                    llId.AddView(lblId);
                    TextView txtId = new TextView(this._context);
                    txtId.Text = this._viewModel.survey.companyId + "";
                    llId.AddView(txtId);
                    this._llMain.AddView(llId);


                    // Start Survey
                    LinearLayout llStartSurvey = new LinearLayout(this._context);
                    Button btnStartSurvey = new Button(this._context);
                    btnStartSurvey.Text = "Start Survey";
                    btnStartSurvey.Click += new EventHandler((object o, EventArgs e) => {
                        Intent intent = new Intent(this._activity, typeof(SurveyPageFlowActivity));
                        intent.PutExtra("surveyId", this._viewModel.survey.surveyId + "");
                        intent.PutExtra("companyId", this._viewModel.survey.companyId + "");
                        this._activity.StartActivity(intent);
                    });
                    llStartSurvey.AddView(btnStartSurvey);
                    this._llMain.AddView(llStartSurvey);
                });
            });

            // Refresh
            this._viewModel.refresh();
        }
    }
}
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

    public class SurveyFormFragment : Android.Support.V4.App.Fragment {
        // Global Variables
        SurveyFormViewModel _viewModel = new SurveyFormViewModel();
        LinearLayout _llMain;
        Context _context;
        Activity _activity;
        List<View> _formElementCollection = new List<View>();
        
        public override View OnCreateView(LayoutInflater p0, ViewGroup p1, Bundle p2) {
            // Instantiate Variables
            this._activity = this.Activity;
            this._context = p0.Context;

            // Set The State
            string surveyId = this._activity.Intent.GetStringExtra("surveyId");
            string companyId = this._activity.Intent.GetStringExtra("companyId");
            if ((surveyId == null || surveyId.Length == 0) && (companyId == null || companyId.Length == 0)) { // If Null Return Home Screen
                Intent intent = new Intent(this._activity, typeof(MainActivity));
                this._activity.StartActivity(intent);
            } else { // Set the Company Id in View Model
                this._viewModel.surveyId = int.Parse(surveyId);
                this._viewModel.companyId = int.Parse(companyId);
            }

            // Creat Linear Layout
            this._llMain = new LinearLayout(this._context);
            this._llMain.Orientation = Orientation.Vertical;
            this._llMain.Id = 5892;
            
            // Textbox
            TextView lblLoading = new TextView(this._context);
            lblLoading.Text = "Loading...";
            lblLoading.Id = 4562;
            this._llMain.AddView(lblLoading);

            // Start the Threading
            ThreadPool.QueueUserWorkItem(o => this.slowMethod(this._activity));

            // Return View
            return this._llMain;
        }




        /********************** Event Handlers *************************/
        // http://docs.xamarin.com/guides/android/advanced_topics/writing_responsive_applications
        private void slowMethod(Activity pi) {
            // Set The Action
            this._viewModel.actionRefreshUI = () => {
                pi.RunOnUiThread(() => {
                    // Turn Off Loading
                    TextView lblLoading = pi.FindViewById<TextView>(4562);
                    lblLoading.Text = "";
                    lblLoading.Visibility = ViewStates.Gone;

                    // Cycle through all the form elements of the form
                    foreach (FormElement formElement in this._viewModel.currentQuestion.form.formElements) {
                        // Variables
                        View element;
                        View elementHolder;
                        bool neededOnSubmit = false; // @TODO: Temporarily Needed Variable, replace with a property of the formElement

                        // @TODO: Need to have it pass the name of the code instead of having to rely on the actual codeId
                        if (formElement.code.codeId == 20) { // Label
                            elementHolder = new LinearLayout(this._context);
                            element = new TextView(this._context);
                            ((TextView) element).Id = formElement.formElementId;
                            ((TextView) element).Text = formElement.formElementOptions[0].displayText.displayTextTranslation + "";
                        } else if (formElement.code.codeId == 2) { // Textbox
                            elementHolder = new LinearLayout(this._context);
                            element = new EditText(this._context);
                            ((EditText) element).Id = formElement.formElementId;
                            ((EditText) element).Text = ""; // @TODO: May need to add logic for default value
                            ((EditText) element).SetWidth(200);
                            neededOnSubmit = true;
                        } else if (formElement.code.codeId == 22) { // Divider
                            elementHolder = new LinearLayout(this._context);
                            element = new TextView(this._context);
                            ((TextView) element).Id = formElement.formElementId;
                            ((TextView) element).Text = "_________________";
                        } else {
                            elementHolder = new LinearLayout(this._context);
                            element = new TextView(this._context);
                            ((TextView) element).Id = formElement.formElementId;
                            ((TextView) element).Text = "Not Found";
                        }

                        // Needed to Be Added on Submit
                        if (neededOnSubmit) {
                            this._formElementCollection.Add(element);
                        }

                        // Add Element To the Element Holder
                        ((LinearLayout) elementHolder).AddView(((TextView) element));

                        // Add The Element Holder to the View
                        this._llMain.AddView(((LinearLayout) elementHolder));
                    }

                    // Complete Survey Button
                    LinearLayout llSubmitSurvey = new LinearLayout(this._context);
                    Button btnSubmitSurvey = new Button(this._context);
                    btnSubmitSurvey.Text = "Submit Survey";
                    btnSubmitSurvey.Click += new EventHandler((object o, EventArgs e) => {
                        // Save all the answers upon submit
                        foreach (View element in this._formElementCollection) {
                            // Form Answer
                            SubmissionAnswer formAnswer = new SubmissionAnswer();
                            formAnswer.formElementId = element.Id;

                            // Textbox
                            if(element is TextView) {
                                formAnswer.value = ((TextView) element).Text;
                            }

                            // Add Answer
                            this._viewModel.submission.submissionAnswers.Add(formAnswer);
                        }
                        this._viewModel.submit();
                    });
                    llSubmitSurvey.AddView(btnSubmitSurvey);
                    this._llMain.AddView(llSubmitSurvey);
                });
            };

            // Set The Submit Action
            this._viewModel.actionSubmit = (value => {
                pi.RunOnUiThread(() => {
                    Intent intent = new Intent(this._activity, typeof(SurveySuccessFlowActivity));
                    intent.PutExtra("surveyId", this._viewModel.survey.surveyId + "");
                    intent.PutExtra("companyId", this._viewModel.survey.companyId + "");
                    this._activity.StartActivity(intent);
                });
            });

            // Set The Submit Action
            this._viewModel.actionError = (value => {
                pi.RunOnUiThread(() => {
                    TextView lblLoading = pi.FindViewById<TextView>(4562);
                    lblLoading.Text = value;
                    lblLoading.Visibility = ViewStates.Visible;
                });
            });




            // How Long the Thread Should Sleep For Before Checking
            Thread.Sleep(5000);

            // Refresh
            this._viewModel.refresh();
        }
    }
}
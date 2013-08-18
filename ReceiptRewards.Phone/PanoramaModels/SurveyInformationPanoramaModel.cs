using Microsoft.Phone.Controls;
using Microsoft.Phone.Shell;
using ReceiptRewards.PCL.Models;
using ReceiptRewards.PCL.ViewModels;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Media.Imaging;

namespace ReceiptRewards.Phone.PanoramaModels {

    public class SurveyInformationPanoramaModel {

        // Instantiate Global Variables
        private static SurveyInformationViewModel _viewModel = new SurveyInformationViewModel();
        private static PhoneApplicationPage _page = null;



        /**
         * Browse All Surveys Panorama Item
         */
        public static PivotItem generate(PhoneApplicationPage page) {
            // Initialize Variables
            String title = "Survey";
            PivotItem pi = new PivotItem();
            SurveyInformationPanoramaModel._page = page;

            // Set State Values
            SurveyInformationPanoramaModel._viewModel.companyId = (int) PhoneApplicationService.Current.State["companyId"];

            // Create Panorama Item
            pi.Header = title;
            pi.Loaded += SurveyInformationPanoramaModel.getSurvey;
            pi.Width = 460;

            // New Stack Panel
            StackPanel sp = new StackPanel();
            sp.Name = "spFormContainer";

            // Add Progress Bar
            ProgressBar pbLoading = new ProgressBar();
            pbLoading.Name = "pbLoading";
            pbLoading.Visibility = Visibility.Visible;
            pbLoading.IsIndeterminate = true;
            sp.Children.Add(pbLoading);

            // Set Content
            pi.Content = sp;
            return pi;
        }


        /********************** Event Handlers *************************/
        private static void btnStartSurvey(Object sender, RoutedEventArgs e) {
            
            SurveyInformationPanoramaModel._page.NavigationService.Navigate(new Uri("/SurveyPage.xaml", UriKind.Relative));
        }
        private static void getSurvey(Object sender, RoutedEventArgs e) {
            // Get The Panorama Item for Global View and Manipulation Control
            PivotItem pi = ((PivotItem) sender);

            // Start the Progress Bar
            Deployment.Current.Dispatcher.BeginInvoke(() => {
                ProgressBar pbLoading = (ProgressBar) pi.FindName("pbLoading");
                pbLoading.Visibility = Visibility.Visible;
            });

            // Call Thread
            ThreadPool.QueueUserWorkItem(o => SurveyInformationPanoramaModel.slowMethod(pi));
        }


        private static void slowMethod(PivotItem pi) {
            // Set The Action
            SurveyInformationPanoramaModel._viewModel.action = (value => {

                // Sends Request to the UI Thread To Update
                Deployment.Current.Dispatcher.BeginInvoke(() => {

                    // Progress Bar
                    ProgressBar pbLoading = (ProgressBar)pi.FindName("pbLoading");
                    pbLoading.Visibility = Visibility.Collapsed;

                    // Get StackPanel
                    StackPanel sp = (StackPanel)pi.FindName("spFormContainer");

                    // Add Survey ID
                    TextBlock txtSurveyId = new TextBlock();
                    txtSurveyId.Text = "Survey ID #: " + SurveyInformationPanoramaModel._viewModel.survey.surveyId;
                    txtSurveyId.Name = "txtSurveyId";
                    sp.Children.Add(txtSurveyId);

                    // Add Category
                    TextBlock tbCategory = new TextBlock();
                    //tbCategory.Text = "@TODO: Reward or Contest";
                    tbCategory.Text = "";
                    sp.Children.Add(tbCategory);

                    // Add Specification
                    TextBlock tbSpecifications = new TextBlock();
                    tbSpecifications.Text = "Specifications";
                    sp.Children.Add(tbSpecifications);

                    // Add Button to Start Survey
                    TextBlock lblButtonStartSurvey = new TextBlock();
                    lblButtonStartSurvey.Text = "Start Survey";
                    Button btnStartSurvey = new Button();
                    btnStartSurvey.Click += SurveyInformationPanoramaModel.btnStartSurvey;
                    btnStartSurvey.Content = lblButtonStartSurvey;
                    sp.Children.Add(btnStartSurvey);

                    // Set the Survey State
                    PhoneApplicationService.Current.State["surveyId"] = SurveyInformationPanoramaModel._viewModel.survey.surveyId;
                });
            });

            // Refresh First Time
            SurveyInformationPanoramaModel._viewModel.refresh();

            // How Long the Thread Should Sleep For Before Checking
            Thread.Sleep(5000);
        }
    }
}

using Microsoft.Phone.Controls;
using ReceiptRewards.PCL.Models;
using ReceiptRewards.PCL.ViewModels;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using System.Windows;
using System.Windows.Controls;

namespace ReceiptRewards.Phone.PanoramaModels {

    public class BrowseSurveyPanoramaModel {

        // Instantiate Global Variables
        private static BrowseSurveyViewModel _viewModel = new BrowseSurveyViewModel();
        private static PhoneApplicationPage page = null;



        /**
         * Browse All Surveys Panorama Item
         */
        public static PanoramaItem generate(PhoneApplicationPage page) {
            // Initialize Variables
            PanoramaItem pi = new PanoramaItem();
            BrowseSurveyPanoramaModel.page = page;
            String title = "Survey";
            
            // Create Panorama Item
            pi.Header = title;
            pi.Orientation = System.Windows.Controls.Orientation.Vertical;
            pi.Loaded += BrowseSurveyPanoramaModel.getAllSurveys;

            // Add Stack Panel
            StackPanel sp = new StackPanel();

            // Add Progress Bar
            ProgressBar pbLoading = new ProgressBar();
            pbLoading.Name = "pbBrowseSurveyLoading";
            pbLoading.Visibility = Visibility.Visible;
            pbLoading.IsIndeterminate = true;
            sp.Children.Add(pbLoading);

            // Add List Box
            ListBox lbAllSurveys = new ListBox();
            lbAllSurveys.Name = "lbBrowseSurvey";
            sp.Children.Add(lbAllSurveys);

            // Add Content
            pi.Content = sp;
            return pi;
        }


        /********************** Event Handlers *************************/
        public static void getAllSurveys(Object sender, RoutedEventArgs e) {
            // Get The Panorama Item for Global View and Manipulation Control
            PanoramaItem pi = ((PanoramaItem) sender);

            // Update the Loading
            ProgressBar pbLoading = (ProgressBar) pi.FindName("pbBrowseSurveyLoading");
            pbLoading.Visibility = Visibility.Visible;

            // Call Thread
            ThreadPool.QueueUserWorkItem(o => BrowseSurveyPanoramaModel.slowMethod(pi));
        }


        private static void slowMethod(PanoramaItem pi) {
            // Refresh List
            BrowseSurveyPanoramaModel._viewModel.action = (value => {

                // Sends Request to the UI Thread To Update
                Deployment.Current.Dispatcher.BeginInvoke(() => {

                    // Remove the Loading
                    ProgressBar pbLoading = (ProgressBar) pi.FindName("pbBrowseSurveyLoading");
                    pbLoading.Visibility = Visibility.Collapsed;

                    // Update The List Box (Could be Moved Into New Method)
                    ListBox lbAllSurveys = (ListBox) pi.FindName("lbBrowseSurvey");
                    foreach (Survey survey in value) {
                        lbAllSurveys.Items.Add(ListBoxItemModel.generateSurvey(survey));
                    }
                });
            });

            // How Long the Thread Should Sleep For Before Checking
            Thread.Sleep(5000);
        }
    }
}

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

    public class StartSurveyPanoramaModel {

        // Instantiate Global Variables
        private static StartSurveyViewModel _viewModel = new StartSurveyViewModel();
        private static PhoneApplicationPage _page = null;



        /**
        * Start Survey Panorama
        */
        public static PanoramaItem generate() {
            // Initialize Variables
            PanoramaItem pi = new PanoramaItem();
            String title = "Start Survey";
            StartSurveyPanoramaModel.initiateVariables(pi);

            // Panorama Properties
            pi.Header = title;
            pi.Orientation = System.Windows.Controls.Orientation.Vertical;

            // Create a Stack Panel
            StackPanel sp = new StackPanel();

            // Add Textbox
            TextBox tbxSearch = new TextBox();
            tbxSearch.Height = 74;
            tbxSearch.Text = "Search";
            tbxSearch.Name = "tbxSearch";
            sp.Children.Add(tbxSearch);

            // Add Button
            TextBlock txtSearchLabel = new TextBlock();
            txtSearchLabel.Text = "Search";
            Button btnSearch = new Button();
            btnSearch.Content = txtSearchLabel;
            btnSearch.Click += StartSurveyPanoramaModel.searchForCompanies;
            sp.Children.Add(btnSearch);

            // Add List Box
            ListBox lbAllSurveys = new ListBox();
            lbAllSurveys.Name = "lbAllSurveys";
            sp.Children.Add(lbAllSurveys);

            // Add Stackpanel and Return Panorama Item
            pi.Content = sp;
            return pi;
        }




        /**
         * Set BrowserModel Variables
         */
        private static void initiateVariables(PanoramaItem pi) {
            
            // Set the Search Action
            StartSurveyPanoramaModel._viewModel.searchAction = value => {

                // Sends Request to the UI Thread To Update
                Deployment.Current.Dispatcher.BeginInvoke(() => {

                    // Update The List Box (Could be Moved Into New Method)
                    ListBox xlbAllSurveys = (ListBox)pi.FindName("lbAllSurveys");
                    foreach (Company company in value) {
                        xlbAllSurveys.Items.Add(ListBoxItemModel.generateCompany(company));
                    }
                });
            };
        }



        /********************** Event Handlers *************************/
        private static void searchForCompanies(Object sender, RoutedEventArgs e) {
            // Get The Panorama Item for Global View and Manipulation Control
            PanoramaItem pi = ((PanoramaItem)((StackPanel)((Button)sender).Parent).Parent);

            // Call Thread
            ThreadPool.QueueUserWorkItem(o => StartSurveyPanoramaModel.slowMethod(pi));
        }

        private static void slowMethod(PanoramaItem pi) {
            // Initialize Variables
            string keyword = "";

            // Get the Text
            //TextBox tbxSearch = (TextBox)pi.FindName("tbxSearch");
            //keyword = tbxSearch.Text;

            // Update the Keyword in the View Model
            StartSurveyPanoramaModel._viewModel.keyword = keyword;

            // Have the Thread Sleep
            Thread.Sleep(5000);
        }
    }
}

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

namespace ReceiptRewards.Phone.PanoramaModels {

    public class SurveySuccessPanoramaModel {

        // Instantiate Global Variables
        //private static BrowseCompanyViewModel _viewModel = new BrowseCompanyViewModel();
        private static PhoneApplicationPage _page = null;



        /**
         * Survey Success Panorama Item
         */
        public static PivotItem generate(PhoneApplicationPage page) {
            // Initialize Variables
            String title = "Success";
            PivotItem pi = new PivotItem();
            SurveySuccessPanoramaModel._page = page;

            // Create Panorama Item
            pi.Header = title;
            pi.Width = 480;
            //pi.Loaded += BrowseCompanyPanoramaModel.getAllCompany;

            // Add Stack Panel
            StackPanel sp = new StackPanel();

            // Add List Box
            TextBlock lblSuccess = new TextBlock();
            lblSuccess.Text = "Hello Success.";
            sp.Children.Add(lblSuccess);

            // Add Content
            pi.Content = sp;
            return pi;
        }



        /********************** Event Handlers *************************/
        /*
        private static void getAllCompany(Object sender, RoutedEventArgs e) {
            // Get The Panorama Item for Global View and Manipulation Control
            PanoramaItem pi = ((PanoramaItem) sender);

            // Update the Loading
            ProgressBar pbLoading = (ProgressBar)pi.FindName("pbBrowseCompanyLoading");
            pbLoading.Visibility = Visibility.Visible;

            // Call Thread
            ThreadPool.QueueUserWorkItem(o => BrowseCompanyPanoramaModel.slowMethod(pi));
        }
        */
        
        /*
        private static void slowMethod(PanoramaItem pi) {
            // How Long the Thread Should Sleep For Before Checking
            Thread.Sleep(5000);

            // Set The Action
            BrowseCompanyPanoramaModel._viewModel.action = (value => {

                // Sends Request to the UI Thread To Update
                Deployment.Current.Dispatcher.BeginInvoke(() => {

                    // Remove the Loading
                    ProgressBar pbLoading = (ProgressBar) pi.FindName("pbBrowseCompanyLoading");
                    pbLoading.Visibility = Visibility.Collapsed;

                    // Update The List Box (Could be Moved Into New Method)
                    ListBox lbAllCompany = (ListBox) pi.FindName("lbBrowseCompany");
                    foreach (Company company in value) {
                        ListBoxItem lbi = ListBoxItemModel.generateCompany(company);
                        lbi.Tap += (o, k) => {
                            PhoneApplicationService.Current.State["companyName"] = company.name;
                            PhoneApplicationService.Current.State["companyId"] = company.companyId;
                            BrowseCompanyPanoramaModel._page.NavigationService.Navigate(new Uri("/CompanyPage.xaml", UriKind.Relative)); 
                        };
                        lbAllCompany.Items.Add(lbi);
                    }
                });
            });
        }
         * */
    }
}

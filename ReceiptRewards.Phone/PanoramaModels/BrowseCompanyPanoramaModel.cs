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

    public class BrowseCompanyPanoramaModel {

        // Instantiate Global Variables
        private static BrowseCompanyViewModel _viewModel = new BrowseCompanyViewModel();
        private static PhoneApplicationPage _page = null;



        /**
         * Browse All Surveys Panorama Item
         */
        public static PivotItem generate(PhoneApplicationPage page) {
            // Initialize Variables
            String title = "Company";
            PivotItem pi = new PivotItem();
            BrowseCompanyPanoramaModel._page = page;

            // Create Panorama Item
            pi.Header = title;
            pi.Loaded += BrowseCompanyPanoramaModel.getAllCompany;
            pi.Width = 460;

            // Add Stack Panel
            StackPanel sp = new StackPanel();

            // Add Progress Bar
            ProgressBar pbLoading = new ProgressBar();
            pbLoading.Name = "pbLoading";
            pbLoading.Visibility = Visibility.Visible;
            pbLoading.IsIndeterminate = true;
            sp.Children.Add(pbLoading);

            // Add List Box
            ListBox lbAllCompany = new ListBox();
            lbAllCompany.Name = "lbBrowseCompany";
            sp.Children.Add(lbAllCompany);

            // Scroll View
            ScrollViewer scrollView = new ScrollViewer();
            scrollView.Content = sp;

            // Add Content
            pi.Content = scrollView;
            return pi;
        }



        /********************** Event Handlers *************************/
        private static void getAllCompany(Object sender, RoutedEventArgs e) {
            // Get The Panorama Item for Global View and Manipulation Control
            PivotItem pi = ((PivotItem) sender);

            // Set the Progress Bar
            Deployment.Current.Dispatcher.BeginInvoke(() => {
                ProgressBar pbLoading = (ProgressBar) pi.FindName("pbLoading");
                pbLoading.Visibility = Visibility.Visible;
            });

            // Call Thread
            ThreadPool.QueueUserWorkItem(o => BrowseCompanyPanoramaModel.slowMethod(pi));
        }

        

        private static void slowMethod(PivotItem pi) {
            // Set The Action
            BrowseCompanyPanoramaModel._viewModel.action = (value => {

                // Sends Request to the UI Thread To Update
                Deployment.Current.Dispatcher.BeginInvoke(() => {

                    // Remove the Loading
                    ProgressBar pbLoading = (ProgressBar) pi.FindName("pbLoading");
                    pbLoading.Visibility = Visibility.Collapsed;

                    // Update The List Box (Could be Moved Into New Method)
                    ListBox lbAllCompany = (ListBox) pi.FindName("lbBrowseCompany");
                    lbAllCompany.Items.Clear();
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

            // How Long the Thread Should Sleep For Before Checking
            Thread.Sleep(5000);
        }
    }
}

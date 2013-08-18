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

    public class CompanyInformationPanoramaModel {

        // Instantiate Global Variables
        private static CompanyInformationViewModel _viewModel = new CompanyInformationViewModel();
        private static PhoneApplicationPage _page = null;



        /**
         * Browse All Surveys Panorama Item
         */
        public static PivotItem generate(PhoneApplicationPage page) {
            // Initialize Variables
            String title = "Company";
            PivotItem pi = new PivotItem();
            CompanyInformationPanoramaModel._page = page;

            // Create Panorama Item
            pi.Header = title;
            pi.Loaded += CompanyInformationPanoramaModel.getCompany;
            pi.Width = 460;

            // New Stack Panel
            StackPanel sp = new StackPanel();

            // Add Large Picture
            Image image = new Image();
            //BitmapImage bitmapImage = new BitmapImage(new Uri("http://farm6.staticflickr.com/5019/5579233891_394b7153b1_o.jpg", UriKind.Absolute));
            BitmapImage bitmapImage = new BitmapImage(new Uri("http://www.westcoast-air.com/wp-content/uploads/iStock_000003332296XSmall.jpg", UriKind.Absolute));
            image.Source = bitmapImage;
            image.Height = double.NaN;
            image.Width = double.NaN;
            image.Name = "imgBanner";
            sp.Children.Add(image);

            // Add Category
            TextBlock tbCategory = new TextBlock();
            //tbCategory.Text = "@TODO: Category Text";
            tbCategory.Text = "";
            tbCategory.Name = "tbCategory";
            sp.Children.Add(tbCategory);

            // Set Content
            pi.Content = sp;
            return pi;
        }


        /********************** Event Handlers *************************/
        private static void getCompany(Object sender, RoutedEventArgs e) {
            // Get The Panorama Item for Global View and Manipulation Control
            PivotItem pi = ((PivotItem) sender);

            // Call Thread
            ThreadPool.QueueUserWorkItem(o => CompanyInformationPanoramaModel.slowMethod(pi));
        }


        private static void slowMethod(PivotItem pi) {
            // Set The View Model Properties
            CompanyInformationPanoramaModel._viewModel.companyId = (int) PhoneApplicationService.Current.State["companyId"];
            CompanyInformationPanoramaModel._viewModel.actionCompany = (value => {

                // Sends Request to the UI Thread To Update
                Deployment.Current.Dispatcher.BeginInvoke(() => {

                    // Update The List Box (Could be Moved Into New Method)
                    // @TODO
                });
            });

            // Call The Refresh
            CompanyInformationPanoramaModel._viewModel.refreshCompany();

            // How Long the Thread Should Sleep For Before Checking
            Thread.Sleep(5000);
        }
    }
}

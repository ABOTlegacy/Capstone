using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Animation;
using System.Windows.Shapes;
using Microsoft.Phone.Controls;
using ReceiptRewards.PCL.ViewModels;
using System.Windows.Media.Imaging;
using System.Threading;
using ReceiptRewards.Phone.PanoramaModels;
using System.Windows.Navigation;
using Microsoft.Phone.Shell;

namespace ReceiptRewards.Phone {
    public partial class CompanyPage : PhoneApplicationPage {

        /**
         * Constructor
         */
        public CompanyPage() {
            // Initialize
            InitializeComponent();

            // Set Title of Panorama
            CompanyPivot.Title = PhoneApplicationService.Current.State["companyName"];
            
            // Set the Background Image
            BitmapImage bitmapImage = new BitmapImage(new Uri("Resources/Images/SingleScreens/SingleScreen.jpg", UriKind.Relative));
            ImageBrush imageBrush = new ImageBrush();
            imageBrush.ImageSource = bitmapImage;
            CompanyPivot.Background = imageBrush;

            // Clear Items in Panorama
            CompanyPivot.Items.Clear();
            
            // Dynamically Add Panorama Items
            CompanyPivot.Items.Add(CompanyInformationPanoramaModel.generate(this));
            CompanyPivot.Items.Add(SurveyInformationPanoramaModel.generate(this));
        }


        /**
         * Load data for the ViewModel Items
         */
        private void BrowsePage_Loaded(object sender, RoutedEventArgs e) {
            if (! App.ViewModel.IsDataLoaded) {

            }
        }


    }
}
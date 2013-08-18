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

namespace ReceiptRewards.Phone
{
    public partial class MainPage : PhoneApplicationPage {
        FirstViewModel viewModel = new FirstViewModel();


        /**
         * Constructor
         */
        public MainPage() {
            // Initialize
            InitializeComponent();

            // Set Title of Panorama
            MainPanorama.Title = "Reciept Rewards";
            
            // Set the Background Image
            BitmapImage bitmapImage = new BitmapImage(new Uri("Resources/Images/Backgrounds/PanoramaBackground.jpg", UriKind.Relative));
            ImageBrush imageBrush = new ImageBrush();
            imageBrush.ImageSource = bitmapImage;
            MainPanorama.Background = imageBrush;

            // Clear Items in Panorama
            MainPanorama.Items.Clear();
            
            // Dynamically Add Panorama Items
            MainPanorama.Items.Add(StartSurveyPanoramaModel.generate());
            MainPanorama.Items.Add(DashboardPanoramaModel.generate(this));
        }


        /**
         * Load data for the ViewModel Items
         */
        private void MainPage_Loaded(object sender, RoutedEventArgs e) {
            if (! App.ViewModel.IsDataLoaded) {
                App.ViewModel.LoadData();
            }
        }


    }
}
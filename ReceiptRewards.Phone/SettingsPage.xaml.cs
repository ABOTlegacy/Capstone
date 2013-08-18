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

namespace ReceiptRewards.Phone {
    public partial class SettingsPage : PhoneApplicationPage {

        /**
         * Constructor
         */
        public SettingsPage() {
            // Initialize
            InitializeComponent();

            // Set Title of Panorama
            SettingsPanorama.Title = "@TODO: Settings";
            
            // Set the Background Image
            BitmapImage bitmapImage = new BitmapImage(new Uri("Resources/Images/Backgrounds/PanoramaBackground.jpg", UriKind.Relative));
            ImageBrush imageBrush = new ImageBrush();
            imageBrush.ImageSource = bitmapImage;
            SettingsPanorama.Background = imageBrush;

            // Clear Items in Panorama
            SettingsPanorama.Items.Clear();
            
            // Dynamically Add Panorama Items
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
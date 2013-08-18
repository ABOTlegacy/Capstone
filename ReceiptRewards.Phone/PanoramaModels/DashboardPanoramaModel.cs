using Microsoft.Phone.Controls;
using ReceiptRewards.PCL.ViewModels;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Navigation;

namespace ReceiptRewards.Phone.PanoramaModels {

    public class DashboardPanoramaModel {

        // Instantiate Global Variables
        private static PhoneApplicationPage page = null;



        /**
         * Dashboard Item
         */
        public static PanoramaItem generate(PhoneApplicationPage page) {
            // Initialize Variables
            String title = "Dashboard";
            String[] links = { "Browse", "Search", "About", "Settings" };
            DashboardPanoramaModel.page = page;

            // Create Panorama Item
            PanoramaItem pi = new PanoramaItem();
            pi.Header = title;
            pi.Orientation = System.Windows.Controls.Orientation.Vertical;

            // Add All The Links
            ListBox lb = new ListBox();
            for (int i = 0; i < links.Length; i++) {
                ListBoxItem lbi = new ListBoxItem();
                TextBlock tb = new TextBlock();
                tb.Text = links[i];
                tb.Height = 64;
                tb.FontSize = 32;
                lbi.Content = tb;

                // Navigation Logic
                if (links[i] == "Browse") {
                    lbi.Tap += (o, k) => { DashboardPanoramaModel.page.NavigationService.Navigate(new Uri("/BrowsePage.xaml", UriKind.Relative)); };
                } else if (links[i] == "Search") {
                    lbi.Tap += (o, k) => { DashboardPanoramaModel.page.NavigationService.Navigate(new Uri("/SearchPage.xaml", UriKind.Relative)); };
                }
                lb.Items.Add(lbi);
            }
            pi.Content = lb;
            return pi;
        }
    }
}

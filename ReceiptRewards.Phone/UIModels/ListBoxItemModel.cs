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

    public class ListBoxItemModel {

        // Instantiate Global Variables
        //@TODO



        /**
         * Generates A Company List Box Item
         */
        public static ListBoxItem generateCompany(Company company) {
            ListBoxItem lbi = new ListBoxItem();
            TextBlock tb = new TextBlock();
            tb.Text = company.name;
            tb.Height = 64;
            tb.FontSize = 32;
            lbi.Content = tb;
            return lbi;
        }



        /**
         * Generates A Survey List Box Item
         */
        public static ListBoxItem generateSurvey(Survey survey) {
            ListBoxItem lbi = new ListBoxItem();
            TextBlock tb = new TextBlock();
            tb.Text = survey.name;
            tb.Height = 64;
            tb.FontSize = 32;
            lbi.Content = tb;
            return lbi;
        }
    }
}

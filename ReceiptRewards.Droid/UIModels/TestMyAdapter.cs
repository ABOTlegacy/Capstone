using Android.App;
using Android.Content.Res;
using Android.OS;
using Android.Support.V4.App;
using Android.Support.V4.Widget;
using Android.Views;
using Android.Widget;
using System;

namespace ReceiptRewards.Droid2 {

    protected class TestMyAdapter : FragmentPagerAdapter
    {
        public TestMyAdapter(Android.Support.V4.App.FragmentManager fm)
            : base(fm)
        {
        }

        public override int Count
        {
            get
            {
                return NUM_ITEMS;
            }
        }

        public override Android.App.Fragment GetItem(int position)
        {
            return new TestArrayListFragment(position);
        }


    }
}
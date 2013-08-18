using Android.App;
using Android.Content.Res;
using Android.OS;
using Android.Support.V4.Widget;
using Android.Views;
using Android.Widget;
using System;

namespace ReceiptRewards.Droid2 {

    protected class TestArrayListFragment : ListFragment
    {
        int num;

        public TestArrayListFragment()
        {
        }

        public TestArrayListFragment(int num)
        {
            var args = new Bundle();
            args.PutInt("num", num);
            Arguments = args;
        }

        public override void OnCreate(Bundle p0)
        {
            base.OnCreate(p0);

            num = Arguments != null ? Arguments.GetInt("num") : 1;
        }

        public override View OnCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
        {
            var v = inflater.Inflate(Resource.Layout.fragment_pager_list, container, false);
            var tv = v.FindViewById<TextView>(Resource.Id.text);
            tv.Text = "Fragment #" + num;
            return v;
        }

        public override void OnActivityCreated(Bundle p0)
        {
            base.OnActivityCreated(p0);

            ListAdapter = new ArrayAdapter<string>(Activity, Android.Resource.Layout.SimpleListItem1, Cheeses.cheeseStrings);
        }

        public override void OnListItemClick(ListView l, View v, int position, long id)
        {
            Console.WriteLine("Item clicked: " + id);
        }
    }
}
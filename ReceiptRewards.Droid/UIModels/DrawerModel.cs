using Android.App;
using Android.Content;
using Android.OS;
using Android.Support.V4.App;
using Android.Support.V4.Widget;
using Android.Views;
using Android.Widget;

namespace ReceiptRewards.Droid2 {

    public class DrawerModel {
        // Global Variables
        private Activity activity;
        private DrawerLayout _drawer;
        private ListView _drawerList;
        private string[] _drawerLinks;



        /**
         * Generates the Drawer for the View
         * @param activity: The Activity Object that is making the call, that the drawer to be placed in.
         * @return: DrawerLayout that was created
         */
        public DrawerLayout generate(Activity activity) {
            // Set Local Variables
            this.activity = activity;

            _drawerLinks = this.activity.Resources.GetStringArray(Resource.Array.LinkArray);
            _drawer = this.activity.FindViewById<DrawerLayout>(Resource.Id.drawer_layout);
            _drawerList = this.activity.FindViewById<ListView>(Resource.Id.left_drawer);
            _drawer.SetDrawerShadow(Resource.Drawable.drawer_shadow_dark, (int)GravityFlags.Left);
            _drawerList.Adapter = new ArrayAdapter<string>(this.activity, Resource.Layout.DrawerListItem, this._drawerLinks);
            _drawerList.ItemClick += (sender, args) => SelectItem(args.Position);

            DrawerAdapter drawerToggle = new DrawerAdapter(this.activity, _drawer,
                                                            Resource.Drawable.ic_drawer_light,
                                                            Resource.String.DrawerOpen,
                                                            Resource.String.DrawerClose);
            _drawer.SetDrawerListener(drawerToggle);

            // Return
            return this._drawer;
        }



        /**
         * Drawer Select Item
         * @param position: The Number in the list that was selected. Used to determine what that item is and load corresponding view
         */
        private void SelectItem(int position) {
            // Search View
            if (this._drawerLinks[position] == "Search") {
                //this.activity.FragmentManager.BeginTransaction().Replace(Resource.Id.content_frame, new StartSearchFragment()).Commit();

            // About View
            } else if (this._drawerLinks[position] == "About") {
                Intent intent = new Intent(this.activity, typeof(AboutFlowActivity));
                this.activity.StartActivity(intent);

            } else if (this._drawerLinks[position] == "Browse") {
                Intent intent = new Intent(this.activity, typeof(BrowseFlowActivity));
                this.activity.StartActivity(intent);

            // Default View
            } else {
                /*var fragment = new DefaultFragment();
                var arguments = new Bundle();
                arguments.PutInt(DefaultFragment.ArgPlanetNumber, position);
                fragment.Arguments = arguments;
                this.activity.FragmentManager.BeginTransaction().Replace(Resource.Id.content_frame, fragment).Commit();
                _drawerList.SetItemChecked(position, true);*/
            }

            // Close the Drawer
            // @TODO: Have the drawer close, but only if the state of the drawer is not already open. Just a little hack for not pushing the icon.
            //_drawer.CloseDrawer(Resource.Id.left_drawer);
        }
    }
}
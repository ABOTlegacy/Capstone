package receiptrewards.droid2;


public class DrawerAdapter
	extends android.support.v4.app.ActionBarDrawerToggle
	implements
		mono.android.IGCUserPeer
{
	static final String __md_methods;
	static {
		__md_methods = 
			"n_onDrawerClosed:(Landroid/view/View;)V:GetOnDrawerClosed_Landroid_view_View_Handler\n" +
			"";
		mono.android.Runtime.register ("ReceiptRewards.Droid2.DrawerAdapter, ReceiptRewards.Droid2, Version=1.0.0.0, Culture=neutral, PublicKeyToken=null", DrawerAdapter.class, __md_methods);
	}


	public DrawerAdapter (android.app.Activity p0, android.support.v4.widget.DrawerLayout p1, int p2, int p3, int p4)
	{
		super (p0, p1, p2, p3, p4);
		if (getClass () == DrawerAdapter.class)
			mono.android.TypeManager.Activate ("ReceiptRewards.Droid2.DrawerAdapter, ReceiptRewards.Droid2, Version=1.0.0.0, Culture=neutral, PublicKeyToken=null", "Android.App.Activity, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=84e04ff9cfb79065:Android.Support.V4.Widget.DrawerLayout, Mono.Android.Support.v4, Version=0.0.0.0, Culture=neutral, PublicKeyToken=84e04ff9cfb79065:System.Int32, mscorlib, Version=2.0.5.0, Culture=neutral, PublicKeyToken=7cec85d7bea7798e:System.Int32, mscorlib, Version=2.0.5.0, Culture=neutral, PublicKeyToken=7cec85d7bea7798e:System.Int32, mscorlib, Version=2.0.5.0, Culture=neutral, PublicKeyToken=7cec85d7bea7798e", this, new java.lang.Object[] { p0, p1, p2, p3, p4 });
	}


	public void onDrawerClosed (android.view.View p0)
	{
		n_onDrawerClosed (p0);
	}

	private native void n_onDrawerClosed (android.view.View p0);

	java.util.ArrayList refList;
	public void monodroidAddReference (java.lang.Object obj)
	{
		if (refList == null)
			refList = new java.util.ArrayList ();
		refList.add (obj);
	}

	public void monodroidClearReferences ()
	{
		if (refList != null)
			refList.clear ();
	}
}

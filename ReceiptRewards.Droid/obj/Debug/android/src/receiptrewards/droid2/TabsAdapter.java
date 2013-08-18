package receiptrewards.droid2;


public class TabsAdapter
	extends android.support.v4.app.FragmentPagerAdapter
	implements
		mono.android.IGCUserPeer,
		android.widget.TabHost.OnTabChangeListener,
		android.support.v4.view.ViewPager.OnPageChangeListener
{
	static final String __md_methods;
	static {
		__md_methods = 
			"n_getCount:()I:GetGetCountHandler\n" +
			"n_getItem:(I)Landroid/support/v4/app/Fragment;:GetGetItem_IHandler\n" +
			"n_onTabChanged:(Ljava/lang/String;)V:GetOnTabChanged_Ljava_lang_String_Handler:Android.Widget.TabHost/IOnTabChangeListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n" +
			"n_onPageScrollStateChanged:(I)V:GetOnPageScrollStateChanged_IHandler:Android.Support.V4.View.ViewPager/IOnPageChangeListenerInvoker, Mono.Android.Support.v4\n" +
			"n_onPageScrolled:(IFI)V:GetOnPageScrolled_IFIHandler:Android.Support.V4.View.ViewPager/IOnPageChangeListenerInvoker, Mono.Android.Support.v4\n" +
			"n_onPageSelected:(I)V:GetOnPageSelected_IHandler:Android.Support.V4.View.ViewPager/IOnPageChangeListenerInvoker, Mono.Android.Support.v4\n" +
			"";
		mono.android.Runtime.register ("ReceiptRewards.Droid2.TabsAdapter, ReceiptRewards.Droid2, Version=1.0.0.0, Culture=neutral, PublicKeyToken=null", TabsAdapter.class, __md_methods);
	}


	public TabsAdapter (android.support.v4.app.FragmentManager p0)
	{
		super (p0);
		if (getClass () == TabsAdapter.class)
			mono.android.TypeManager.Activate ("ReceiptRewards.Droid2.TabsAdapter, ReceiptRewards.Droid2, Version=1.0.0.0, Culture=neutral, PublicKeyToken=null", "Android.Support.V4.App.FragmentManager, Mono.Android.Support.v4, Version=0.0.0.0, Culture=neutral, PublicKeyToken=84e04ff9cfb79065", this, new java.lang.Object[] { p0 });
	}


	public int getCount ()
	{
		return n_getCount ();
	}

	private native int n_getCount ();


	public android.support.v4.app.Fragment getItem (int p0)
	{
		return n_getItem (p0);
	}

	private native android.support.v4.app.Fragment n_getItem (int p0);


	public void onTabChanged (java.lang.String p0)
	{
		n_onTabChanged (p0);
	}

	private native void n_onTabChanged (java.lang.String p0);


	public void onPageScrollStateChanged (int p0)
	{
		n_onPageScrollStateChanged (p0);
	}

	private native void n_onPageScrollStateChanged (int p0);


	public void onPageScrolled (int p0, float p1, int p2)
	{
		n_onPageScrolled (p0, p1, p2);
	}

	private native void n_onPageScrolled (int p0, float p1, int p2);


	public void onPageSelected (int p0)
	{
		n_onPageSelected (p0);
	}

	private native void n_onPageSelected (int p0);

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

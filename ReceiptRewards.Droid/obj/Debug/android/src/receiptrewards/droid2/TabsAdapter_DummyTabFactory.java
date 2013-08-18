package receiptrewards.droid2;


public class TabsAdapter_DummyTabFactory
	extends java.lang.Object
	implements
		mono.android.IGCUserPeer,
		android.widget.TabHost.TabContentFactory
{
	static final String __md_methods;
	static {
		__md_methods = 
			"n_createTabContent:(Ljava/lang/String;)Landroid/view/View;:GetCreateTabContent_Ljava_lang_String_Handler:Android.Widget.TabHost/ITabContentFactoryInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n" +
			"";
		mono.android.Runtime.register ("ReceiptRewards.Droid2.TabsAdapter/DummyTabFactory, ReceiptRewards.Droid2, Version=1.0.0.0, Culture=neutral, PublicKeyToken=null", TabsAdapter_DummyTabFactory.class, __md_methods);
	}


	public TabsAdapter_DummyTabFactory ()
	{
		super ();
		if (getClass () == TabsAdapter_DummyTabFactory.class)
			mono.android.TypeManager.Activate ("ReceiptRewards.Droid2.TabsAdapter/DummyTabFactory, ReceiptRewards.Droid2, Version=1.0.0.0, Culture=neutral, PublicKeyToken=null", "", this, new java.lang.Object[] {  });
	}

	public TabsAdapter_DummyTabFactory (android.content.Context p0)
	{
		super ();
		if (getClass () == TabsAdapter_DummyTabFactory.class)
			mono.android.TypeManager.Activate ("ReceiptRewards.Droid2.TabsAdapter/DummyTabFactory, ReceiptRewards.Droid2, Version=1.0.0.0, Culture=neutral, PublicKeyToken=null", "Android.Content.Context, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=84e04ff9cfb79065", this, new java.lang.Object[] { p0 });
	}


	public android.view.View createTabContent (java.lang.String p0)
	{
		return n_createTabContent (p0);
	}

	private native android.view.View n_createTabContent (java.lang.String p0);

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

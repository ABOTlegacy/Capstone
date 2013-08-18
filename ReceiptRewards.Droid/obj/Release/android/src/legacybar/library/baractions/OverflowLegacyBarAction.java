package legacybar.library.baractions;


public class OverflowLegacyBarAction
	extends legacybar.library.baractions.LegacyBarAction
	implements
		mono.android.IGCUserPeer,
		android.widget.AdapterView.OnItemSelectedListener
{
	static final String __md_methods;
	static {
		__md_methods = 
			"n_onItemSelected:(Landroid/widget/AdapterView;Landroid/view/View;IJ)V:GetOnItemSelected_Landroid_widget_AdapterView_Landroid_view_View_IJHandler:Android.Widget.AdapterView/IOnItemSelectedListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n" +
			"n_onNothingSelected:(Landroid/widget/AdapterView;)V:GetOnNothingSelected_Landroid_widget_AdapterView_Handler:Android.Widget.AdapterView/IOnItemSelectedListenerInvoker, Mono.Android, Version=0.0.0.0, Culture=neutral, PublicKeyToken=null\n" +
			"";
		mono.android.Runtime.register ("LegacyBar.Library.BarActions.OverflowLegacyBarAction, LegacyBar.Library, Version=1.0.0.0, Culture=neutral, PublicKeyToken=null", OverflowLegacyBarAction.class, __md_methods);
	}


	public OverflowLegacyBarAction ()
	{
		super ();
		if (getClass () == OverflowLegacyBarAction.class)
			mono.android.TypeManager.Activate ("LegacyBar.Library.BarActions.OverflowLegacyBarAction, LegacyBar.Library, Version=1.0.0.0, Culture=neutral, PublicKeyToken=null", "", this, new java.lang.Object[] {  });
	}


	public void onItemSelected (android.widget.AdapterView p0, android.view.View p1, int p2, long p3)
	{
		n_onItemSelected (p0, p1, p2, p3);
	}

	private native void n_onItemSelected (android.widget.AdapterView p0, android.view.View p1, int p2, long p3);


	public void onNothingSelected (android.widget.AdapterView p0)
	{
		n_onNothingSelected (p0);
	}

	private native void n_onNothingSelected (android.widget.AdapterView p0);

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

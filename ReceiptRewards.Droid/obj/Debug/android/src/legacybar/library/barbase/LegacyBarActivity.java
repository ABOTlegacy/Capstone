package legacybar.library.barbase;


public class LegacyBarActivity
	extends android.app.Activity
	implements
		mono.android.IGCUserPeer
{
	static final String __md_methods;
	static {
		__md_methods = 
			"n_onPrepareOptionsMenu:(Landroid/view/Menu;)Z:GetOnPrepareOptionsMenu_Landroid_view_Menu_Handler\n" +
			"n_onCreateOptionsMenu:(Landroid/view/Menu;)Z:GetOnCreateOptionsMenu_Landroid_view_Menu_Handler\n" +
			"";
		mono.android.Runtime.register ("LegacyBar.Library.BarBase.LegacyBarActivity, LegacyBar.Library, Version=1.0.0.0, Culture=neutral, PublicKeyToken=null", LegacyBarActivity.class, __md_methods);
	}


	public LegacyBarActivity ()
	{
		super ();
		if (getClass () == LegacyBarActivity.class)
			mono.android.TypeManager.Activate ("LegacyBar.Library.BarBase.LegacyBarActivity, LegacyBar.Library, Version=1.0.0.0, Culture=neutral, PublicKeyToken=null", "", this, new java.lang.Object[] {  });
	}


	public boolean onPrepareOptionsMenu (android.view.Menu p0)
	{
		return n_onPrepareOptionsMenu (p0);
	}

	private native boolean n_onPrepareOptionsMenu (android.view.Menu p0);


	public boolean onCreateOptionsMenu (android.view.Menu p0)
	{
		return n_onCreateOptionsMenu (p0);
	}

	private native boolean n_onCreateOptionsMenu (android.view.Menu p0);

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

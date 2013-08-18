package legacybar.library.baractions;


public abstract class LegacyBarAction
	extends java.lang.Object
	implements
		mono.android.IGCUserPeer
{
	static final String __md_methods;
	static {
		__md_methods = 
			"";
		mono.android.Runtime.register ("LegacyBar.Library.BarActions.LegacyBarAction, LegacyBar.Library, Version=1.0.0.0, Culture=neutral, PublicKeyToken=null", LegacyBarAction.class, __md_methods);
	}


	public LegacyBarAction ()
	{
		super ();
		if (getClass () == LegacyBarAction.class)
			mono.android.TypeManager.Activate ("LegacyBar.Library.BarActions.LegacyBarAction, LegacyBar.Library, Version=1.0.0.0, Culture=neutral, PublicKeyToken=null", "", this, new java.lang.Object[] {  });
	}

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

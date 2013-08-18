package legacybar.library.baractions;


public class ActionLegacyBarAction
	extends legacybar.library.baractions.LegacyBarAction
	implements
		mono.android.IGCUserPeer
{
	static final String __md_methods;
	static {
		__md_methods = 
			"";
		mono.android.Runtime.register ("LegacyBar.Library.BarActions.ActionLegacyBarAction, LegacyBar.Library, Version=1.0.0.0, Culture=neutral, PublicKeyToken=null", ActionLegacyBarAction.class, __md_methods);
	}


	public ActionLegacyBarAction ()
	{
		super ();
		if (getClass () == ActionLegacyBarAction.class)
			mono.android.TypeManager.Activate ("LegacyBar.Library.BarActions.ActionLegacyBarAction, LegacyBar.Library, Version=1.0.0.0, Culture=neutral, PublicKeyToken=null", "", this, new java.lang.Object[] {  });
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

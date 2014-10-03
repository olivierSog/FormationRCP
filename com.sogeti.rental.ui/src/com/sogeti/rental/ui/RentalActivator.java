package com.sogeti.rental.ui;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;

/**
 * The activator class controls the plug-in life cycle
 */
public class RentalActivator extends AbstractUIPlugin implements RentalUIConstants{

	// The plug-in ID
	public static final String PLUGIN_ID = "com.sogeti.rental.ui"; //$NON-NLS-1$

	// The shared instance
	private static RentalActivator plugin;
	
	/**
	 * The constructor
	 */
	public RentalActivator() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		
		readViewsExtensions();
	}

	private void readViewsExtensions() {
		// Direct access
		IExtensionRegistry reg = Platform.getExtensionRegistry();
		for (IConfigurationElement e : reg.getConfigurationElementsFor("org.eclipse.ui.views"))
		{
//			System.out.println("Found this element : " + e.getName());
			if (e.getName().equals("view"))
				System.out.println("Plugin : " + e.getNamespaceIdentifier() + "\t\tVue : " + e.getAttribute("name"));
		}
		
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static RentalActivator getDefault() {
		return plugin;
	}

	
	protected void initializeImageRegistry(ImageRegistry reg) {
		Bundle b = FrameworkUtil.getBundle(getClass());
		
		reg.put(ICON_CUSTOMER, ImageDescriptor.createFromURL(b.getEntry(ICON_CUSTOMER)));
		reg.put(ICON_AGENCY, ImageDescriptor.createFromURL(b.getEntry(ICON_AGENCY)));
		reg.put(ICON_RENTALS, ImageDescriptor.createFromURL(b.getEntry(ICON_RENTALS)));
		reg.put(ICON_RENTALOBJECTS, ImageDescriptor.createFromURL(b.getEntry(ICON_RENTALOBJECTS)));
	}
	
}

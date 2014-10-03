package com.sogeti.rental.ui;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.viewers.IColorProvider;
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
	
	// Map of possible color providers (read in extensions)
	private Map<String, PaletteDescriptor> paletteManager = new HashMap<String, PaletteDescriptor>();
	
	
	public Map<String, PaletteDescriptor> getPaletteManager() {
		return paletteManager;
	}

	public void setPaletteManager(Map<String, PaletteDescriptor> paletteManager) {
		this.paletteManager = paletteManager;
	}

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
		
		readPalette();
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

	private void readPalette() {
		// lit toutes les palettes
		IExtensionRegistry reg = Platform.getExtensionRegistry();
		try {
			for (IConfigurationElement e : reg.getConfigurationElementsFor("com.sogeti.rental.ui.palette"))
			{
				PaletteDescriptor p = new PaletteDescriptor();
				p.setName(e.getAttribute("name"));
				p.setId(e.getAttribute("id"));
				p.setCp((IColorProvider)e.createExecutableExtension("class"));
				
				paletteManager.put(e.getAttribute("id"), p);
			}
		} catch (CoreException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
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

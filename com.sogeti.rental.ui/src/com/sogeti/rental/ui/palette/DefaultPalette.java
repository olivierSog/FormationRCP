package com.sogeti.rental.ui.palette;

import org.eclipse.jface.resource.ColorRegistry;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;

import com.opcoach.training.rental.Customer;
import com.opcoach.training.rental.Rental;
import com.opcoach.training.rental.RentalAgency;
import com.opcoach.training.rental.RentalObject;
import com.sogeti.rental.ui.RentalActivator;
import com.sogeti.rental.ui.RentalUIConstants;

public class DefaultPalette implements IColorProvider, RentalUIConstants {

	public DefaultPalette() {
		// TODO Auto-generated constructor stub
	}


	@Override
	public Color getForeground(Object element) {
		if (element instanceof Customer)
			
			return getAColor(RentalActivator.getDefault().getPreferenceStore().getString(PREF_CUSTOMER_COLOR));
		
			//return Display.getCurrent().getSystemColor(SWT.COLOR_DARK_BLUE);
		else if (element instanceof RentalAgency)
			return Display.getCurrent().getSystemColor(SWT.COLOR_DARK_MAGENTA);
		else if (element instanceof RentalObject)
			//return Display.getCurrent().getSystemColor(SWT.COLOR_DARK_YELLOW);
			return getAColor(RentalActivator.getDefault().getPreferenceStore().getString(PREF_AGENCY_COLOR));
		else if (element instanceof Rental)
			//return Display.getCurrent().getSystemColor(SWT.COLOR_CYAN);
			return getAColor(RentalActivator.getDefault().getPreferenceStore().getString(PREF_RENTALS_COLOR));
		
		return null;
	}

	private Color getAColor(String rgbKey) {
		// p81
		ColorRegistry colorRegistry = JFaceResources.getColorRegistry();
		
		Color col = colorRegistry.get(rgbKey);
		if (col == null)
		{
			colorRegistry.put(rgbKey, StringConverter.asRGB(rgbKey));
			col = colorRegistry.get(rgbKey);
		}
		return col;
	}
	
	@Override
	public Color getBackground(Object element) {
		if (element instanceof RentalAgency)
			return Display.getCurrent().getSystemColor(SWT.COLOR_GREEN);
		
		return null;
	}
}

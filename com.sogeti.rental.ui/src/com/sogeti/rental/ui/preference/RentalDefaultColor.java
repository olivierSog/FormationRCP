package com.sogeti.rental.ui.preference;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

import com.sogeti.rental.ui.RentalActivator;
import com.sogeti.rental.ui.RentalUIConstants;

public class RentalDefaultColor extends AbstractPreferenceInitializer implements RentalUIConstants {

	public RentalDefaultColor() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initializeDefaultPreferences() {
		
		IPreferenceStore store = RentalActivator.getDefault().getPreferenceStore();
		
		Color c = Display.getCurrent().getSystemColor(SWT.COLOR_BLUE);
		
		store.setDefault(PREF_CUSTOMER_COLOR, StringConverter.asString(c.getRGB()));
		store.setDefault(PREF_RENTALS_COLOR, StringConverter.asString(new RGB(255, 0, 0)));
		store.setDefault(PREF_AGENCY_COLOR, StringConverter.asString(new RGB(255, 0, 0)));
		
		// Initialize the store
		store.setDefault(PREF_PALETTE, "com.sogeti.rental.ui.palette.DefaultPalette");
	}

}

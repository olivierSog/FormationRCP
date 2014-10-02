package com.sogeti.rental.ui.preference;

import org.eclipse.jface.preference.ColorFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.sogeti.rental.ui.RentalActivator;
import com.sogeti.rental.ui.RentalUIConstants;

public class RentalColorPrefPage extends FieldEditorPreferencePage implements
		IWorkbenchPreferencePage, RentalUIConstants {

	public RentalColorPrefPage() {
		super(GRID);
		setPreferenceStore(RentalActivator.getDefault().getPreferenceStore());
		setDescription("A demonstration of a preference page implementation");
	}


	@Override
	public void init(IWorkbench workbench) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void createFieldEditors() {
		addField(new ColorFieldEditor(PREF_CUSTOMER_COLOR, "Customer", getFieldEditorParent()));
		addField(new ColorFieldEditor(PREF_RENTALS_COLOR, "Rentals", getFieldEditorParent()));
		addField(new ColorFieldEditor(PREF_AGENCY_COLOR, "Agency", getFieldEditorParent()));
		
	}

}

package com.sogeti.rental.ui.preference;

import java.util.Map;

import org.eclipse.jface.preference.ComboFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.sogeti.rental.ui.PaletteDescriptor;
import com.sogeti.rental.ui.RentalActivator;
import com.sogeti.rental.ui.RentalUIConstants;

// Be careful to change in the wizard
public class PalettePreferencePage extends FieldEditorPreferencePage implements
		IWorkbenchPreferencePage, RentalUIConstants {

	public PalettePreferencePage() {
		super(GRID);
		setPreferenceStore(RentalActivator.getDefault().getPreferenceStore());
		setDescription("A demonstration of a Palette preference page implementation");
	}

// delete constructors with params

	@Override
	protected void createFieldEditors() {
//		addField(new ColorFieldEditor(PREF_CUSTOMER_COLOR, "Customer", getFieldEditorParent()));
		// Extract the double String array for name and color provider 
		Map<String, PaletteDescriptor> palettes = RentalActivator.getDefault().getPaletteManager();
		
		String[][] comboValues = new String[palettes.size()][2];
		int i = 0;
		for (PaletteDescriptor p : palettes.values())
		{
			comboValues[i][0] = p.getName();	// display name
			comboValues[i][1] = p.getId();		// return value if selected
			i++;
		}
		
		addField(new ComboFieldEditor(PREF_PALETTE, "Palette couleur", comboValues, getFieldEditorParent()));
	}
	
	@Override
	public void init(IWorkbench workbench) {
		// TODO Auto-generated method stub

	}



}

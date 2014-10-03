package com.sogeti.rental.ui.palette;

import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;

public class JaunePalette implements IColorProvider {

	public JaunePalette() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Color getForeground(Object element) {
		
		return Display.getCurrent().getSystemColor(SWT.COLOR_YELLOW);
	}

	@Override
	public Color getBackground(Object element) {
		// TODO Auto-generated method stub
		return null;
	}

}

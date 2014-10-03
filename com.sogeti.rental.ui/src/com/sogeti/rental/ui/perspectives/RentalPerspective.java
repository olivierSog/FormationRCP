package com.sogeti.rental.ui.perspectives;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class RentalPerspective implements IPerspectiveFactory {

	public static final String PERSPECTIVE_ID = "com.sogeti.rental.ui.perspectives.RentalPerspective";
	
	/**
	 * Creates the initial layout for a page.
	 */
	public void createInitialLayout(IPageLayout layout) {
		// Allow to allow the views in the perspective
		layout.setEditorAreaVisible(false);
		String editorArea = layout.getEditorArea();
		addFastViews(layout);
		addViewShortcuts(layout);
		addPerspectiveShortcuts(layout);
		layout.addView("com.sogeti.rental.ui.rentalPropertyView", IPageLayout.TOP, 0.5f, IPageLayout.ID_EDITOR_AREA);
		layout.addView("com.sogeti.rental.ui.rentalAgencyView", IPageLayout.BOTTOM, 0.2f, "com.sogeti.rental.ui.rentalPropertyView");
		layout.addView("com.sogeti.rental.ui.rentalCustomer", IPageLayout.RIGHT, 0.5f, "com.sogeti.rental.ui.rentalAgencyView");
	}

	/**
	 * Add fast views to the perspective.
	 */
	private void addFastViews(IPageLayout layout) {
	}

	/**
	 * Add view shortcuts to the perspective.
	 */
	private void addViewShortcuts(IPageLayout layout) {
	}

	/**
	 * Add perspective shortcuts to the perspective.
	 */
	private void addPerspectiveShortcuts(IPageLayout layout) {
	}

}

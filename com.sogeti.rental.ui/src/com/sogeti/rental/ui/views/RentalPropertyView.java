package com.sogeti.rental.ui.views;


import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.part.ViewPart;

import com.opcoach.training.rental.Rental;
import com.sogeti.rental.core.RentalCoreActivator;
import org.eclipse.wb.swt.SWTResourceManager;

public class RentalPropertyView extends ViewPart {

	private Label rentalObjectLabel;
	private Label lblRentalDateFrom;
	private Label lblDu;
	private Label lblRentalDateTo;
	private Label lblAu;
	private Label lblJohnWayne;
	
	private void setRental(Rental r) {
		rentalObjectLabel.setText(r.getRentedObject().getName());
		lblRentalDateFrom.setText(r.getStartDate().toString());
		lblRentalDateTo.setText(r.getEndDate().toString());
		lblJohnWayne.setText(r.getCustomer().getDisplayName());
	}
	
	public RentalPropertyView() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPartControl(Composite parent) {
		// TODO Auto-generated method stub
		parent.setLayout(new GridLayout(2, false));
		
		Group infoGroup = new Group(parent, SWT.NONE);
		infoGroup.setText("Informations");
		infoGroup.setLayout(new GridLayout(3, false));
		
		rentalObjectLabel = new Label(infoGroup, SWT.NONE);
		GridData gd = new GridData();
		gd.horizontalSpan = 3;
		gd.horizontalAlignment = SWT.FILL;
		rentalObjectLabel.setLayoutData(gd);
		
		Label lblLou = new Label(infoGroup, SWT.NONE);
		lblLou.setText("Lou\u00E9 \u00E0 :");
		new Label(infoGroup, SWT.NONE);
		
		lblJohnWayne = new Label(infoGroup, SWT.NONE);
		//lblJohnWayne.setText("John Wayne");
		
		Group grpDatesDeLocation = new Group(parent, SWT.NONE);
		grpDatesDeLocation.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		grpDatesDeLocation.setText("Dates de location");
		grpDatesDeLocation.setLayout(new GridLayout(2, false));
		
		lblDu = new Label(grpDatesDeLocation, SWT.NONE);
		lblDu.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblDu.setText("du :");
		
		lblRentalDateFrom = new Label(grpDatesDeLocation, SWT.NONE);
		//lblRentalDateFrom.setText("15/03/2011");
		
		lblAu = new Label(grpDatesDeLocation, SWT.NONE);
		lblAu.setText("au :");
		lblAu.setBounds(0, 0, 20, 15);
		
		lblRentalDateTo = new Label(grpDatesDeLocation, SWT.NONE);
		lblRentalDateTo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		lblRentalDateTo.setBounds(0, 0, 58, 15);
		
		setRental(RentalCoreActivator.getAgency().getRentals().get(0));
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

}

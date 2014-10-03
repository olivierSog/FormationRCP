package com.sogeti.rental.ui.views;


import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DragSource;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.DragSourceListener;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.wb.swt.SWTResourceManager;

import com.opcoach.training.rental.Rental;
import com.sogeti.rental.core.RentalCoreActivator;

public class RentalPropertyView extends ViewPart implements ISelectionListener{

	private Label rentalObjectLabel;
	private Label lblRentalDateFrom;
	private Label lblFrom;
	private Label lblRentalDateTo;
	private Label lblTo;
	private Label lblCustomer;
	
	private void setRental(Rental r) {
		rentalObjectLabel.setText(r.getRentedObject().getName());
		lblRentalDateFrom.setText(r.getStartDate().toString());
		lblRentalDateTo.setText(r.getEndDate().toString());
		lblCustomer.setText(r.getCustomer().getDisplayName());
	}
	
	public RentalPropertyView() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPartControl(Composite parent) {
		// Allow to create the view
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
		
		
		lblCustomer = new Label(infoGroup, SWT.NONE);
		//lblJohnWayne.setText("John Wayne");
		
		Group grpDatesDeLocation = new Group(parent, SWT.NONE);
		grpDatesDeLocation.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		grpDatesDeLocation.setText("Dates de location");
		grpDatesDeLocation.setLayout(new GridLayout(2, false));
		
		lblFrom = new Label(grpDatesDeLocation, SWT.NONE);
		lblFrom.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblFrom.setText("de :");
		
		lblRentalDateFrom = new Label(grpDatesDeLocation, SWT.NONE);
		//lblRentalDateFrom.setText("15/03/2011");
		
		lblTo = new Label(grpDatesDeLocation, SWT.NONE);
		lblTo.setForeground(SWTResourceManager.getColor(51, 204, 102));
		lblTo.setText("\u00E0 :"); // à
		lblTo.setBounds(0, 0, 20, 15);
		
		lblRentalDateTo = new Label(grpDatesDeLocation, SWT.NONE);
		lblRentalDateTo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		lblRentalDateTo.setBounds(0, 0, 58, 15);
		
		// Retrieve from model
		setRental(RentalCoreActivator.getAgency().getRentals().get(1));
		
		// Drag label(s)  
		setLabelAsDragSource(rentalObjectLabel);
		setLabelAsDragSource(lblCustomer);
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(IViewSite site) throws PartInitException {
		// TODO Auto-generated method stub
		super.init(site);
		site.getPage().addSelectionListener(this);
	}
	
	@Override
	public void dispose() {
		getSite().getPage().removeSelectionListener(this);
		super.dispose();
	}
	
	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		if ( selection instanceof IStructuredSelection)
		{
			Object selected = ((IStructuredSelection) selection).getFirstElement();
			
			if (selected instanceof Rental) {
				setRental((Rental)selected);
			}
			System.out.println("Object selection : " + selected);
		}
		
	}
	
	public void setLabelAsDragSource(final Label label) {
		DragSource source = new DragSource(label, DND.DROP_MOVE | DND.DROP_COPY);
		source.setTransfer(new Transfer[] { TextTransfer.getInstance() });
		
		source.addDragListener(new DragSourceListener() {
			
			@Override
			public void dragStart(DragSourceEvent event) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void dragSetData(DragSourceEvent event) {
				if (TextTransfer.getInstance().isSupportedType(event.dataType)) {
					event.data = label.getText();
				}
				
			}
			
			@Override
			public void dragFinished(DragSourceEvent event) {
				// TODO Auto-generated method stub
				
			}
		});
	}

}

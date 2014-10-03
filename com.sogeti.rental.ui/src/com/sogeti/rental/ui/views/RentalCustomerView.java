package com.sogeti.rental.ui.views;

import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.ViewPart;

import com.opcoach.training.rental.Customer;

public class RentalCustomerView extends ViewPart implements ISelectionListener {

	private Label lblCustomer;

	private void setCustomer(Customer r) {
		lblCustomer.setText(r.getDisplayName());
	}

	public RentalCustomerView() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPartControl(Composite parent) {
		// Allow view creation
		lblCustomer = new Label(parent, SWT.NONE);
		lblCustomer.setText("un texte bidon........");  // to initialize
		// lblCustomer.setText("exo 055");

		// setCustomer(RentalCoreActivator.getAgency().getCustomers().get(1));
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(IViewSite site) throws PartInitException {
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

		if (selection.isEmpty())
			return;

		if (selection instanceof IStructuredSelection)
		{
			Object selected = ((IStructuredSelection) selection).getFirstElement();

			/*
			 * if (selected instanceof Rental) { setRental((Rental)selected); }
			 * System.out.println("Object selection : " + selected);
			 */
			Customer c = (Customer) Platform.getAdapterManager().getAdapter(selected, Customer.class);

			if (c != null)
				setCustomer(c);
		}

	}

}

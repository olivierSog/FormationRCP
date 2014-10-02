package com.sogeti.rental.ui.views;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.ViewPart;

import com.opcoach.training.rental.RentalAgency;
import com.sogeti.rental.core.RentalCoreActivator;
import com.sogeti.rental.ui.RentalActivator;

public class RentalAgencyView extends ViewPart implements IPropertyChangeListener {

	private TreeViewer tv;

	@Override
	public void init(IViewSite site) throws PartInitException {
		// TODO Auto-generated method stub
		super.init(site);
		RentalActivator.getDefault().getPreferenceStore().addPropertyChangeListener(this);
		
	}
	
	@Override
	public void dispose() {
		RentalActivator.getDefault().getPreferenceStore().removePropertyChangeListener(this);
		super.dispose();
	}
	
	
	public RentalAgencyView() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPartControl(Composite parent) {
		tv = new TreeViewer(parent);
		RentalProvider provider = new RentalProvider();
		tv.setContentProvider(provider);
		tv.setLabelProvider(provider);
		
		Collection<RentalAgency> agencies = new ArrayList<RentalAgency>();
		agencies.add(RentalCoreActivator.getAgency());
		tv.setInput(agencies);
		
		// Push Tree Viewer
		getSite().setSelectionProvider(tv);
		
		MenuManager menuManager = new MenuManager();
        Menu menu = menuManager.createContextMenu(tv.getControl());
        tv.getControl().setMenu(menu);
        getSite().registerContextMenu(menuManager, tv);

	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

	@Override
	public void propertyChange(PropertyChangeEvent event) {
		tv.refresh();
		
	}

	
}

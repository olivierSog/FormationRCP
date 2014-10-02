package com.sogeti.rental.ui.views;

import java.util.Collection;

import org.eclipse.jface.resource.ColorRegistry;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

import com.opcoach.training.rental.Customer;
import com.opcoach.training.rental.Rental;
import com.opcoach.training.rental.RentalAgency;
import com.opcoach.training.rental.RentalObject;
import com.sogeti.rental.ui.RentalActivator;
import com.sogeti.rental.ui.RentalUIConstants;

public class RentalProvider extends LabelProvider implements ITreeContentProvider, RentalUIConstants, IColorProvider
{


	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object[] getElements(Object inputElement) {
		if (inputElement instanceof Collection<?>)
			return ((Collection<?>) inputElement).toArray();
  
		return null;
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		// TODO Auto-generated method stub
		if (parentElement instanceof RentalAgency) {
			RentalAgency a = (RentalAgency) parentElement;
			return new Node[] { new Node(CUSTOMER_NODE, a), new Node(LOCATION_NODE, a), new Node(OBJECTS_NODE, a) };
		}
		else if (parentElement instanceof Node)
			return ((Node) parentElement).getChildren();
		return null;
	}

	@Override
	public Object getParent(Object element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		// TODO Auto-generated method stub
		return (element instanceof Node || element instanceof RentalAgency);
	}

	@Override
	public String getText(Object element) {
		if (element instanceof RentalAgency)
			return ((RentalAgency) element).getName();
		else if (element instanceof Customer)
			return ((Customer) element).getDisplayName();
		else if (element instanceof RentalObject)
			return ((RentalObject) element).getName();
		
		return super.getText(element); // call toString
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
	

	@Override
	public Image getImage(Object element) {
		if (element instanceof RentalAgency)
			return RentalActivator.getDefault().getImageRegistry().get(ICON_AGENCY);
		else if (element instanceof Customer)
			return RentalActivator.getDefault().getImageRegistry().get(ICON_CUSTOMER);
		else if (element instanceof RentalObject)
			return RentalActivator.getDefault().getImageRegistry().get(ICON_RENTALOBJECTS);
		else if (element instanceof Rental)
			return RentalActivator.getDefault().getImageRegistry().get(ICON_RENTALS);
		
		return null;
	}
}



class Node implements RentalUIConstants
{

	private String label;
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((a == null) ? 0 : a.hashCode());
		result = prime * result + ((label == null) ? 0 : label.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Node other = (Node) obj;
		if (a == null) {
			if (other.a != null)
				return false;
		} else if (!a.equals(other.a))
			return false;
		if (label == null) {
			if (other.label != null)
				return false;
		} else if (!label.equals(other.label))
			return false;
		return true;
	}

	private RentalAgency a;
	
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	
	public RentalAgency getA() {
		return a;
	}
	public void setA(RentalAgency a) {
		this.a = a;
	}
	
	public Node(String label, RentalAgency a) {
		super();
		this.label = label;
		this.a = a;
	}
	
	@Override
	public String toString() {
		return label;
	}

    public Object[] getChildren() {
    	if (label.equals(CUSTOMER_NODE))
    		return a.getCustomers().toArray();
    	else if (label.equals(LOCATION_NODE))
    		return a.getRentals().toArray();
    	else if (label.equals(OBJECTS_NODE))
    		return a.getObjectsToRent().toArray();
    	
    	return null;
    }
}
package com.sogeti.rental.ui.views;

import java.util.Collection;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.Viewer;

import com.opcoach.training.rental.Customer;
import com.opcoach.training.rental.RentalAgency;
import com.opcoach.training.rental.RentalObject;
import com.sogeti.rental.ui.RentalUIConstants;

public class RentalProvider extends LabelProvider implements ITreeContentProvider, RentalUIConstants
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
}



class Node implements RentalUIConstants
{

	private String label;
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
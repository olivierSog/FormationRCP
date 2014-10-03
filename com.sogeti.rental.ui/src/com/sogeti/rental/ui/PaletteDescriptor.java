package com.sogeti.rental.ui;

import org.eclipse.jface.viewers.IColorProvider;

public class PaletteDescriptor {

	private String id, name;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	private IColorProvider cp;

	public IColorProvider getCp() {
		return cp;
	}

	public void setCp(IColorProvider cp) {
		this.cp = cp;
	}
}

package com.application.utility;

public enum GenderEnum {
	
	MEN("Men","Men"), WOMEN("Women","Men");
	
	private String label;
	private String value;
	
	private GenderEnum(String label, String value) {
		this.label = label;
		this.value = value;
	}

	public String getLabel() {
		return label;
	}

	public String getValue() {
		return value;
	}

}

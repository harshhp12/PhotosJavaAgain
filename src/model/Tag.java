package model;

import java.io.Serializable;

public class Tag implements Serializable{
	/**
	 * 	 Generated serialVersionUID
	 */
	private static final long serialVersionUID = 6050594006566353687L;
	private String name, value;
	
	public Tag(String name, String value) {
		this.name = name;
		this.value = value;
	}
	
	public String returnName() {
		return name;
	}
	
	public String returnValue() {
		return value;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
	public String toString() {
		return name + ": " + value;
	}
}

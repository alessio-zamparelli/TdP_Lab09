package it.polito.tdp.borders.model;

import java.util.List;

public class Country implements Comparable<Country>{

	private int code;
	private String ab;
	private String fullName;
//	private List<Country> confinanti;

	public Country(int code, String ab, String fullName) {
		super();
		this.code = code;
		this.ab = ab;
		this.fullName = fullName;
	}

	public int getCode() {
		return code;
	}

	public String getAb() {
		return ab;
	}

	public String getFullName() {
		return fullName;
	}

	public Country identity() {
		return this;
	}
	
//	public List<Country> getConfinanti(){
//		return this.confinanti;
//	}

	@Override
	public String toString() {
		return "Country [code=" + code + ", ab=" + ab + ", fullName=" + fullName + "]";
	}

	@Override
	public int compareTo(Country o) {
		return this.getAb().compareTo(o.getAb());
	}
	
}

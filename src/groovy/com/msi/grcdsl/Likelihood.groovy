package com.msi.grcdsl

enum Likelihood {
	
	HIGH(0,"HIGH"), MEDIUM(1,"MEDIUM"), LOW(2,"LOW")
	
	int value
	String name
	
	Likelihood(int value, String name) {
		this.value = value
		this.name = name
	}
	
	String toString() {
		return this.name
	}
	
	

}

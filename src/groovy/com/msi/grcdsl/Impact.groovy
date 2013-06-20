package com.msi.grcdsl

enum Impact {
	
	HIGH(0,"HIGH"), MEDIUM(1,"MEDIUM"), LOW(2,"LOW")
	
	int value
	String name
	
	Impact(int value, String name) {
		this.value = value
		this.name = name
	}
	
	String toString() {
		return this.name
	}


}

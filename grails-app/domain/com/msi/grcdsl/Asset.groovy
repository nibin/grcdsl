package com.msi.grcdsl

class Asset {
	
	String name
	int value
	
	static hasMany = [threats: Threat]

    static constraints = {
		name nullable: false
		value min: 0
    }
	
}

package com.msi.grcdsl

import org.hibernate.type.EnumType;

class Vuln {

	String shortDescription
	String exposedRisk
	Impact impact
	
    static constraints = {
		shortDescription nullable:false
		exposedRisk nullable:false 
		impact type: EnumType
    }
}

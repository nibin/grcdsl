package com.msi.grcdsl

import org.hibernate.type.EnumType;

class Vuln {

	String shortDescription
	String longDescription
	Impact impact
	
    static constraints = {
		shortDescription nullable:false
		longDescription nullable:false 
		impact type: EnumType
    }
}

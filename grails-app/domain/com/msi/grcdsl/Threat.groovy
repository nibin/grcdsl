package com.msi.grcdsl

class Threat {
	
	String shortDescription
	String longDescription
	Likelihood likelihood
	
	static hasMany = [vulns: Vuln]

	
    static constraints = {
    }
}

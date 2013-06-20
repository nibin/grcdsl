package com.msi.grcdsl

class Threat {
	
	String shortDescription
	String threatType
	Likelihood likelihood
	
	static hasMany = [vulns: Vuln]

	
    static constraints = {
    }
}

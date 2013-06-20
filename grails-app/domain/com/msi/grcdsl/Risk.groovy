package com.msi.grcdsl

class Risk {
	
	String description
	Audit audit
	Asset asset
	Threat threat
	Vuln vuln
	RiskType riskType
	double cost

    static constraints = {
		description nullable: false
    }
}

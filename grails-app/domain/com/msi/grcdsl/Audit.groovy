package com.msi.grcdsl

class Audit {
	
	String scope
	String objective
	String status
	
	static hasMany = [
		assets: Asset,
		findings: Finding,
		
	]

    static constraints = {
		scope nullable: false
		objective nullable: false
		status nullable: true
    }
}

package com.msi.grcdsl

class Compliance {
	
	
	static hasMany = [
		auditedAssets: Asset,
		failedAssets: Asset
	]

    static constraints = {
    	
	}
}

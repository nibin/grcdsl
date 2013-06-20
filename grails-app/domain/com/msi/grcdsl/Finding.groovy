package com.msi.grcdsl

class Finding {

	Asset asset
	Vuln vuln

	static belongsTo = [audit: Audit]
}

package com.msi.grcdsl

enum CurrencyUnit {
	
	dollars('dollars', 1),
	rupees('rupees', 60),
	yen('yen', 95),
	euro('euro', 0.747),
	pound('pound', 0.64)
	
	String abbr
	double multiplier
	
	CurrencyUnit(abbr, multiplier) {
		this.abbr = abbr
		this.multiplier = multiplier
	}
	
	String toString() {
		abbr
	}

}

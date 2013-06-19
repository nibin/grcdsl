package com.msi.grcdsl


class GrcBuilder {

	Object targetDomain
	String property

	String sayHello() {
		return "hello"
	}

	GrcBuilder check(Domains domain) {
		if(domain == Domains.compliance) {
			targetDomain = new Compliance()
			log.info("Selected operating model is ${domain.name()}")
		}
		return this;
	}
	
	GrcBuilder show(String property) {
		
	}

	GrcBuilder of(List list) {
		log.info("Operating on a list of size: ${list.size()}")
		return this
	}
	
	Object to(Object t) {
		t = targetDomain
	}
}

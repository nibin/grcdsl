package com.msi.grcdsl

import java.util.Map;

class ConsoleService extends org.grails.plugins.console.ConsoleService {

	@Override
	Object eval(String code, Map bindingValues) {
		//log.info("Call intercepted by ext class for binding values");
		return super.eval(code, bindingValues);
	}
}

package com.msi.grcdsl

import org.codehaus.groovy.control.CompilerConfiguration
import org.codehaus.groovy.control.customizers.ImportCustomizer

class ConsoleService extends org.grails.plugins.console.ConsoleService {

	@Override
	Object eval(String code, Map bindingValues) {

		def g = new GrcDslCommon()
		bindingValues.assets = Asset.list()
		bindingValues.g = g
		bindingValues.sayHello = g.&sayHello
		bindingValues.check = g.&check
		bindingValues.of  = g.&of
		bindingValues.leftShift = g.&leftShift

		def domainMap = Domains.values().collectEntries {
			[(it.name()): it]
		}
		bindingValues.putAll(domainMap)

		def conf= new CompilerConfiguration()
		//conf.scriptBaseClass = GrciCommonFunctions

		def imports = new ImportCustomizer()
		//imports.addStaticStar(com.msi.grcdsl)
		imports.addStarImport("com.msi.grcdsl")
		conf.addCompilationCustomizers(imports)


		createShell(bindingValues, conf).evaluate code
	}

	private GroovyShell createShell(Map bindingValues, conf) {
		bindingValues.ctx = grailsApplication.mainContext
		bindingValues.grailsApplication = grailsApplication
		bindingValues.config = grailsApplication.config
		bindingValues.log = log
		new GroovyShell(grailsApplication.classLoader, new Binding(bindingValues), conf)
	}

	enum Domains {

		compliance
	}
	//abstract
	class GrcDslCommon
	//extends Script
	{
		Object targetDomain

		String sayHello() {
			return "hello"
		}

		GrcDslCommon check(Domains domain) {
			if(domain == Domains.compliance) {
				targetDomain = new Compliance()
				log.info("Selected operating model is ${domain.name()}")
			}
			return this;
		}

		GrcDslCommon of(List list) {
			log.info("Operating on a list of size: ${list.size()}")
			return this
		}

		Object leftShift(GrcDslCommon g) {
			return g.targetDomain
		}
	}
}

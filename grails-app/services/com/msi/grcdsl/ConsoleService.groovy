package com.msi.grcdsl

import static org.codehaus.groovy.syntax.Types.*

import org.codehaus.groovy.control.CompilerConfiguration
import org.codehaus.groovy.control.customizers.ImportCustomizer
import org.codehaus.groovy.control.customizers.SecureASTCustomizer
import org.codehaus.groovy.control.customizers.SecureASTCustomizer.ExpressionChecker

class ConsoleService extends org.grails.plugins.console.ConsoleService {

	@Override
	Object eval(String code, Map bindingValues) {
		
		bindingValues.assets = Asset.list()
		bindingValues.audit = new Audit()

		//assignBuilder(bindingValues)
		
		assignMetaClasses()


		def notificationMaps = NotificationType.values().collectEntries {
			[(it.name()): it]
		}
		bindingValues.putAll(notificationMaps)
		
		def domainMap = Domains.values().collectEntries {
			[(it.name()): it]
		}
		bindingValues.putAll(domainMap)


		def conf = prepareCompilerConf()
		//GroovyClassLoader loader = new GroovyClassLoader(this.class.classLoader)

		createShell(bindingValues, conf).evaluate code
	}

	private GroovyShell createShell(Map bindingValues, conf) {
		bindingValues.ctx = grailsApplication.mainContext
		bindingValues.grailsApplication = grailsApplication
		bindingValues.config = grailsApplication.config
		bindingValues.log = log
		new GroovyShell(grailsApplication.classLoader, new Binding(bindingValues), conf)
	}
	
	def assignMetaClasses() {
		Integer.metaClass.getDollar = { ->
			new Currency(delegate, CurrencyUnit.dollars)			
		}
		
		Integer.metaClass.getRupees = { ->
			new Currency(delegate, CurrencyUnit.rupees)			
		}
		
		Integer.metaClass.getYen = { ->
			new Currency(delegate, CurrencyUnit.yen)
		}
		
		Integer.metaClass.getEuro = { ->
			new Currency(delegate, CurrencyUnit.euro)
		}
		
		Integer.metaClass.getPound = { ->
			new Currency(delegate, CurrencyUnit.pound)
		}
		
	}

	def prepareCompilerConf() {
		def imports = new ImportCustomizer()
		imports.addStarImport("com.msi.grcdsl")
		imports.addStarImport("grails.converters")

		/*		
		 def exprClosure = { expr -> 
		 return true 
		 } as ExpressionChecker
		 */ 

		//final ImportCustomizer imports = new ImportCustomizer().addStaticStars('java.lang.Math') // add static import of java.lang.Math
		final SecureASTCustomizer secure = new SecureASTCustomizer()
		secure.with {
			closuresAllowed = true
			methodDefinitionAllowed = true

			//expressionCheckers = [exprClosure]

			importsWhitelist = []
			staticImportsWhitelist = []
			staticStarImportsWhitelist = ["com.msi.grcdsl"]
			tokensWhitelist = [
				PLUS,
				MINUS,
				MULTIPLY,
				DIVIDE,
				MOD,
				POWER,
				PLUS_PLUS,
				MINUS_MINUS,
				COMPARE_EQUAL,
				COMPARE_NOT_EQUAL,
				COMPARE_LESS_THAN,
				COMPARE_LESS_THAN_EQUAL,
				COMPARE_GREATER_THAN,
				COMPARE_GREATER_THAN_EQUAL,
			].asImmutable()

			constantTypesClassesWhiteList = [
				Integer,
				Float,
				Long,
				Double,
				BigDecimal,
				Integer.TYPE,
				Long.TYPE,
				Float.TYPE,
				Double.TYPE
			].asImmutable()

			receiversClassesWhiteList = [
				Math,
				Integer,
				Float,
				Double,
				Long,
				BigDecimal,
				Object
			].asImmutable()
		}

		CompilerConfiguration config = new CompilerConfiguration()
		config.addCompilationCustomizers(imports)
		config.setScriptBaseClass("com.msi.grcdsl.GrcDslCommService")
		return config
	}

	def assignBuilder(def bindingValues) {

		def g = new GrcBuilder()
		bindingValues.builder = g
		bindingValues.check = g.&check
		bindingValues.of = g.&of
	}

}

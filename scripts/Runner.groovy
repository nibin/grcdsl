import org.codehaus.groovy.control.CompilerConfiguration
import org.codehaus.groovy.control.customizers.ImportCustomizer
import org.codehaus.groovy.control.customizers.SecureASTCustomizer

import static org.codehaus.groovy.syntax.Types.*
import org.codehaus.groovy.control.customizers.SecureASTCustomizer.ExpressionChecker

def imports = new ImportCustomizer()
imports.addStaticStar(Direction.name)

def exprClosure = { expr -> 
	//if(expr instanceof MethodCallExpression)
   	return true
} as ExpressionChecker

//final ImportCustomizer imports = new ImportCustomizer().addStaticStars('java.lang.Math') // add static import of java.lang.Math
final SecureASTCustomizer secure = new SecureASTCustomizer()
secure.with {
	closuresAllowed = true
	methodDefinitionAllowed = true

	expressionCheckers = [
		exprClosure
	]
	
	importsWhitelist = []
	staticImportsWhitelist = ['Direction']
	staticStarImportsWhitelist = [] 
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
GroovyClassLoader loader = new GroovyClassLoader(this.class.classLoader)

//loader.rootLoader.addURL(new URL("file://RobotBaseScriptClass.groovy"))

def robot = new Robot()
def binding = new Binding([
	robot: robot
	//move: robot.&move,
	//at: robot.&at
	
])


def sh = new GroovyShell(loader, binding, config)
config.setScriptBaseClass("RobotBaseScriptClass")
sh.evaluate(new File("command.groovy"))
//sh.evaluate("robot.move LEFT")
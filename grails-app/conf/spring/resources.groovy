import org.grails.plugins.console.ConsoleService;

import com.msi.grcdsl.AuditService;
import com.msi.grcdsl.ConsoleService

// Place your Spring DSL code here
beans = {
	
	/*
	consoleConsoleService(ConsoleService) {
		
	}
	
	consoleService(ExtConsoleService) {
		consoleConsoleService = ref("consoleConsoleService")
	}
	*/
	/*
	consoleService(ConsoleService) {
		//grailsApplication = ref("grailsApplication")
	}
	*/
	
	auditService(AuditService) {
		random = new Random()
	}
	
}

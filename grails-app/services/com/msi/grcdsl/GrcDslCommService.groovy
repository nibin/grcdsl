package com.msi.grcdsl

import java.util.List;

abstract class GrcDslCommService extends Script {
	
	String sayHelloNow() {
		return "hello from common func"
	}
	
	void set(Map m, Object obj) {
		obj.properties = m
	}
	
	def start(Object obj) {
						
		[on: { l ->
				if(obj instanceof Audit) {
					def audit = this.binding.audit
					log.info("In on closure for audit. ${audit}")
					log.info("Operating on a list of ${l.size()}")
					def auditService = this.binding.ctx.auditService
					auditService.startAudit(audit, l)
				} else {
					log.warn("Unrecognized grammer")
				}
			}
		]
		
	}
}

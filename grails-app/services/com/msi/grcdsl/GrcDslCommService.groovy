package com.msi.grcdsl

import grails.converters.JSON



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
					def auditService = this.binding.ctx.auditService
					auditService.startAudit(audit, l)
				} else {
					log.warn("Unrecognized grammer")
				}
			}
		]
	}

	def create(Domains domain) {
		[from: { finding ->
				if(finding instanceof Finding &&
				domain == Domains.risk) {
					def riskService = this.binding.ctx.riskService
					def risk = riskService.createRisk(finding)
					return risk
				} else {
					log.warn("Unrecognized grammer")
				}
			}
		]
	}

	def select(List list) {
		[where: { param ->
				[gt: { value ->
						return list.findAll { it->
							if(it[param] > value.getValue())  return true
							else false
						}
					},lt: { value ->
						return list.findAll { it->
							if(it[param] < value.getValue())  return true
							else false
						}
					}]
			}
		]
	}

	def send(NotificationType type) {
		[of: { items ->
				[to: { rcpt ->
						def b = items as JSON
						def mailService = this.binding.ctx.mailService
						mailService.sendMail {
							to "${rcpt}"
							from "grcdsl@metricstream.com"
							subject "Message from GRCDSL"
							body "${b}"
						}
						return "Sent mail"
					}]
			}]
	}
}

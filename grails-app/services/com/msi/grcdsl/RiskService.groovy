package com.msi.grcdsl

class RiskService {
	
	//row likelihood
	//column impact
	def riskMatrix = [
			[RiskType.HIGH, RiskType.MEDIUM, RiskType.LOW],
			[RiskType.MEDIUM, RiskType.MEDIUM, RiskType.LOW],
			[RiskType.LOW, RiskType.LOW, RiskType.LOW]		
		]
	def costMatrix = [10000, 1000, 100]

    def createRisk(Finding finding) {
		
		def threatIds = finding.asset.threats.id
		def vulnId = finding.vuln.id
		
		Threat exposedThreat = getThreat(threatIds, vulnId)		
		if(exposedThreat != null) {
			Vuln exploitedVuln = finding.vuln
			def risk = new Risk()
			risk.description = exploitedVuln.exposedRisk
			risk.audit = finding.audit
			risk.asset = finding.asset
			risk.threat = exposedThreat
			risk.vuln = exploitedVuln
			risk.riskType =  riskMatrix[exposedThreat.likelihood.value][exploitedVuln.impact.value]
			risk.cost = costMatrix[risk.riskType.value]
			risk.save(failOnError: true, flush: true)
			return risk
		} else {
			return null
		}
    }

	def getThreat(def threatIds, def vulnId) {
		
		def c = Threat.createCriteria()
		
		def result = c.list(max: 1) {
			'in'('id', threatIds)
			vulns {
				eq('id',vulnId)
			}
		}
		
		if(result.size() > 0) {
			return result.get(0);
		} else {
			return null
		}
	}
}

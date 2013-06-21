package com.msi.grcdsl

import org.springframework.dao.DuplicateKeyException

class AuditService {
	
	def random

	def startAudit(Audit audit, ArrayList assets) {

		assets.each {
			audit.addToAssets(it)
			audit.save(failOnError: true, flush: true)
		}
		audit.status = "started"
		audit.save(failOnError: true, flush: true)
		
		createRandomFindings(audit, assets, 10)
		
		audit.status = "failed"
		audit.save(failOnError: true, flush: true)
	}

	def createRandomFindings(def audit, List assets, int count) {
		
		//get all vulns
		def vulns = Vuln.list()
		
		def res = []
		
		(1..count).each {
			
			def l = [:]
			def k = assets.get(random.nextInt(assets.size()))
			def v = vulns.get(random.nextInt(vulns.size()))
			l[k] = v
			
			res << l
		}
		
		//log.info("Created random asset-vulns = ${res}")
		
		def resSet = res as Set //removes duplicates
		
		//log.info("After removing duplicates = ${resSet}")
		
		resSet.each {item ->
			item.each { asset, vuln ->
				
				def finding = new Finding()
				finding.asset = asset
				finding.vuln = vuln
				
				audit.addToFindings(finding)				
				audit.save(failOnError: true, flush: true)			
			}
		}		
		
	}
	
	public void setJavaUtilRandom(Random random) {
		this.random = random
	}
}

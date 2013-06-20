import com.msi.grcdsl.*
import java.util.Random

 def bootstrap() {
        def asset1 = new Asset(name: "asset1", value: 10);
        asset1.save(failOnError: true)
        
        def asset2 = new Asset(name: "asset2", value: 20);
        asset2.save(failOnError: true)
        
        def threat1 = new Threat(shortDescription: "threat1", longDescription: "threat1",
            likelihood: Likelihood.HIGH);
        threat1.save(failOnError: true, flush: true)
        
        def threat2 = new Threat(shortDescription: "threat2", longDescription: "threat2",
            likelihood: Likelihood.MEDIUM)
        threat2.save(failOnError: true, flush: true)
        
        def threat3 = new Threat(shortDescription: "threat3", longDescription: "threat3",
            likelihood: Likelihood.LOW)
        threat3.save(failOnError: true, flush: true)
        
        asset1.addToThreats(threat1)
        asset1.addToThreats(threat2)
        asset1.addToThreats(threat3)
        asset1.save()
        
        asset2.addToThreats(threat1)
        asset2.addToThreats(threat2)
        asset2.addToThreats(threat3)
        asset2.save()
        
        def vuln1 = new Vuln(shortDescription: "vuln1", longDescription: "vuln1",
            impact: Impact.HIGH)
        vuln1.save(failOnError: true, flush: true)
        
        def vuln2 = new Vuln(shortDescription: "vuln2", longDescription: "vuln2",
            impact: Impact.MEDIUM)
        vuln2.save(failOnError: true, flush: true)
        
        def vuln3 = new Vuln(shortDescription: "vuln3", longDescription: "vuln3",
            impact: Impact.LOW)
        vuln3.save(failOnError: true, flush: true)
        
        threat1.addToVulns(vuln1)
        threat1.addToVulns(vuln2)
        threat1.addToVulns(vuln3)
        threat1.save(failOnError: true, flush: true)
        
        threat2.addToVulns(vuln1)
        threat2.addToVulns(vuln2)
        threat2.save(failOnError: true, flush: true)
        
        threat3.addToVulns(vuln2)
        threat3.addToVulns(vuln3)
        threat3.save(failOnError: true, flush: true)
  }
    

    def startAudit(Audit audit, List assets) {
      
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
          def random = new Random()
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
   
   
   //bootstrap()
   //def audit = new Audit(scope: "network", objective: "compliance")
   //def assets = Asset.list()
   
   //startAudit(audit, assets)
   
   /*
   def audit = Audit.get(2)
   audit.findings.each { f ->
       def assetThreats = f.asset.threats.id 
       def vuln = f.vuln
       
       
       println "--------"
       println assetThreats
       println assetThreats as Set      
   }
   
   */
  
   def threat = Threat.get(1)
   def vuln = Vuln.get(1)
   
   //def t = Threat.findAll("from Threat as t where t.vulns = ?", [vuln])
   //print t.dump()
   
def c = Threat.createCriteria()

def result = c.list {
    eq('id', 2L)
    vulns {
        eq('id',3L)
    }
}
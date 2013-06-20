import com.msi.grcdsl.Asset
import com.msi.grcdsl.Impact;
import com.msi.grcdsl.Likelihood
import com.msi.grcdsl.Threat
import com.msi.grcdsl.Vuln

class BootStrap {

    def init = { servletContext ->
		
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
	
    def destroy = {
    
		
	}
}

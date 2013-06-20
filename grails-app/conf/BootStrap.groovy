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
		
		def asset3 = new Asset(name: "asset3", value: 30);
		asset3.save(failOnError: true)
		
		def threat1 = new Threat(shortDescription: "Intruder", threatType: "Human factor internal threat",
			likelihood: Likelihood.HIGH);
		threat1.save(failOnError: true, flush: true)
		
		def threat2 = new Threat(shortDescription: "Hacker", threatType: "Human factor external threat",
			likelihood: Likelihood.MEDIUM)
		threat2.save(failOnError: true, flush: true)
		
		def threat3 = new Threat(shortDescription: "Current employee", threatType: "Human factor internal threat",
			likelihood: Likelihood.LOW)
		threat3.save(failOnError: true, flush: true)
		
		def threat4 = new Threat(shortDescription: "Virus", threatType: "Malicious external threat",
			likelihood: Likelihood.HIGH)
		threat4.save(failOnError: true, flush: true)
		
		asset1.addToThreats(threat1)
		asset1.addToThreats(threat2)
		asset1.addToThreats(threat3)
		asset1.save()
		
		asset2.addToThreats(threat1)
		asset2.addToThreats(threat2)
		asset2.addToThreats(threat3)
		asset2.save()
		
		asset3.addToThreats(threat1)
		asset3.addToThreats(threat2)
		asset3.addToThreats(threat3)
		asset3.addToThreats(threat4)
		asset3.save()
		
		def vuln1 = new Vuln(shortDescription: "No security guard or controlled entrance", exposedRisk: "Theft",
			impact: Impact.HIGH)
		vuln1.save(failOnError: true, flush: true)
		
		def vuln2 = new Vuln(shortDescription: "Misconfigured firewall", exposedRisk: "Stolen credit card information",
			impact: Impact.MEDIUM)
		vuln2.save(failOnError: true, flush: true)
		
		def vuln21 = new Vuln(shortDescription: "Unpatched system", exposedRisk: "Steal confidential informations",
			impact: Impact.MEDIUM)
		vuln21.save(failOnError: true, flush: true)
		
		def vuln3 = new Vuln(shortDescription: "Poor accountability", exposedRisk: "Loss of integrity",
			impact: Impact.LOW)
		vuln3.save(failOnError: true, flush: true)
		
		def vuln31 = new Vuln(shortDescription: "no audit policy", exposedRisk: "altered data",
			impact: Impact.LOW)
		vuln31.save(failOnError: true, flush: true)
		
		def vuln4 = new Vuln(shortDescription: "Out-of-date antivirus software", exposedRisk: "Virus infection and loss of productivity",
			impact: Impact.LOW)
		vuln4.save(failOnError: true, flush: true)
		
		def vuln41 = new Vuln(shortDescription: "Lack of antivirus softwares", exposedRisk: "Virus infection and loss of productivity",
			impact: Impact.LOW)
		vuln41.save(failOnError: true, flush: true)
		
		threat1.addToVulns(vuln1)
		threat1.save(failOnError: true, flush: true)
		
		threat2.addToVulns(vuln2)
		threat2.addToVulns(vuln21)
		threat2.save(failOnError: true, flush: true)
		
		threat3.addToVulns(vuln3)
		threat3.addToVulns(vuln31)
		threat3.save(failOnError: true, flush: true)
		
		threat4.addToVulns(vuln4)
		threat4.addToVulns(vuln41)
		threat4.save(failOnError: true, flush: true)
					
		
    }
	
    def destroy = {
    
		
	}
}

public class Command {
	
	  public static void main(String[] args) {
	  
		//Step 1: Plan the audit
		  Audit audit = new Audit();
		audit.setScope("Network");
		audit.setObjective("compliance");
		
		//Step 2: Get the list of assets
		List<Asset> assets = Asset.list();
		//System.out.println(assets.size());
		
		AuditService auditService = new AuditService();
		auditService.setJavaUtilRandom(new java.util.Random());
		
		RiskService riskService = new RiskService();
		
		
		//Step 3: Start Audit
		auditService.startAudit(audit, assets);
		
		//Step 4: if audit has findings,then
		// 		  create risks
		
		List<Risk> risks = new ArrayList<Risk>();
		if(audit.getStatus().equalsIgnoreCase("failed")) {
			System.out.println("Oh yeah!. Audit failed");
			  
		  for(Finding finding: audit.getFindings()) {
			  
			Risk risk = riskService.createRisk(finding);
			if(risk != null) {
				risks.add(risk);
			}
		  
		  }
		  
		}
		
		System.out.println(String.format("Raised a total of %d risks", risks.size()));
		
		//Step 5: Find high risks where loss is more than 3000 dollars
		List<Risk> highRisks = new ArrayList<Risk>();
		for(Risk risk: risks) {
		  if(risk.getCost() > 3000) {
			  highRisks.add(risk);
		  }
		}
		
		System.out.println(String.format("Total high valued risks : %d", highRisks.size()));
		
		//Step 6: Notify stakeholders
		
	  }
	
	}
	
	
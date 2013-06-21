import com.msi.grcdsl.*

class Command {

    
    public static void main(String[] args) {
    
        Audit audit = new Audit();
        audit.setScope("Network");
        audit.setObjective("compliance");
        
        List<Asset> assets = Asset.list();
        
        System.out.println(audit.dump();        
        
    
    }

}
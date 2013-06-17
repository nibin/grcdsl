import com.msi.grcdsl.Asset

class BootStrap {

    def init = { servletContext ->
		
		def asset1 = new Asset(name: "asset1", value: 10);
		asset1.save(flushOnError: true)
		
		def asset2 = new Asset(name: "asset2", value: 20);
		asset2.save(flushOnError: true)
		
		
    }
	
    def destroy = {
    
		
	}
}

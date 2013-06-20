abstract class RobotBaseScriptClass extends groovy.lang.Script {
	
	/*
	void move(Direction dir) {
		def robot = this.binding.robot
		robot.move dir
	}
	*/
	
	def move(Direction dir) {
		[ at: { it ->
			def robot = this.binding.robot
			robot.at it
			return robot
		}]

		def robot = this.binding.robot
		robot.move dir
		
		//return robot
	}

/*
	def at(String speed) {
		def robot = this.binding.robot
		robot.at speed
		return robot	
	} */
}
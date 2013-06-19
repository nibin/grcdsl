abstract class RobotBaseScriptClass extends groovy.lang.Script {
	
	void move(Direction dir) {
		def robot = this.binding.robot
		robot.move dir
	}
}
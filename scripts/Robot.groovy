class Robot {
	
	Robot move(Direction dir) {
		println("Moving to dir: ${dir.name()}")
		return this
	}

	Robot at(String speed) {
		println("Moving at a speed of ${speed}")
		return this
	}

}

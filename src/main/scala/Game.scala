case class Game(
	name: String,
	platform: String,
	scores: List[Int]
){
	override def toString() = "('$name', '$platfrom', ARRAY[${scores.toString}])"	
}

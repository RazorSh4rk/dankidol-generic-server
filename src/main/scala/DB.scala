import Game._
import Implicits._
import java.sql.{Connection, DriverManager, ResultSet, Array}

object DB {

	Class.forName("org.postgresql.Driver")

	val conn_str = "jdbc:postgresql://localhost:5432/postgres?user=postgres&password=password"

	val INSERT = "insert into Games (name, platform, scores) values (?, ?, ?)"
	var UPDATE = "update Games set scores = (select scores from Games where name=?) || ? where name=?"
	val GET = "select scores from Games where name=?"


	val update = (game: String, score: Int) => {
		val conn = DriverManager.getConnection(conn_str)
		var i = -1
		try {
			val stm = conn.prepareStatement(UPDATE)

			stm.setString(1, game)
			stm.setInt(2, score)
			stm.setString(3, game)

			i = stm.executeUpdate
		} finally { conn.close }

		i
	}

	val insert = (game: Game) => {
		val conn = DriverManager.getConnection(conn_str)
		var i = -1
		try {
			val stm = conn.prepareStatement(INSERT)
			val arr = conn.createArrayOf("integer", game.scores.toArray)

			stm.setString(1, game.name)
			stm.setString(2, game.platform)
			stm.setArray(3, arr)

			i = stm.executeUpdate
		} finally { conn.close }

		i
	}

	val get = (game: String) => {
		val conn = DriverManager.getConnection(conn_str)
		var results: List[Game] = List()
		try {
			val stm = conn.prepareStatement(GET)
			stm.setString(1, game)
			val rs = stm.executeQuery
			
			results = rs.toStream.toList.map(el => {
				Game(
					name = el.getString("name"),
					platform = el.getString("platform"),
					scores = el.getArray("scores").asInstanceOf[List[Int]]
				)
			})
		} finally { conn.close }

		results
	}

}

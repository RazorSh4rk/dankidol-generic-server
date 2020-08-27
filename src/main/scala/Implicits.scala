import java.sql.ResultSet

object Implicits {

    implicit class ResultSetStream(resultSet: ResultSet) {

        def toStream: Stream[ResultSet] = {
            new Iterator[ResultSet] {
                def hasNext = resultSet.next()

                def next() = resultSet
            }.toStream
        }
    }
}

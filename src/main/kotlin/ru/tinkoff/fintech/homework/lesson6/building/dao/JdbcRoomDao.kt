package ru.tinkoff.fintech.homework.lesson6.building.dao

import org.springframework.context.annotation.Profile
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.simple.SimpleJdbcInsert
import org.springframework.stereotype.Component
import ru.tinkoff.fintech.homework.lesson6.building.entities.Room


@Component
@Profile("jdbc")
class JdbcRoomDao(
    val jdbcTemplate: JdbcTemplate
) : RoomDao {
    override fun getRooms(): Map<Long, Room> {
        val row = jdbcTemplate.queryForRowSet("select * from rooms")
        val rooms = mutableMapOf<Long, Room>()

        while (row.next()) {
            val id = row.getLong("id")
            val name = row.getString("name")!!
            val countOfPeople = row.getInt("count_of_people")
            rooms[id] = Room(id, name, countOfPeople)
        }

        return rooms
    }

    override fun getRoom(roomId: Long): Room? {
        val row = jdbcTemplate.queryForRowSet("select * from rooms where id=?", roomId)
        row.next()
        val id = row.getLong("id")
        val name = row.getString("name")!!
        val countOfPeople = row.getInt("count_of_people")

        return Room(id, name, countOfPeople)
    }

    override fun addRoom(roomForAdd: Room): Long {
        val simpleJdbcInsert = SimpleJdbcInsert(jdbcTemplate).withTableName("rooms").usingGeneratedKeyColumns("id")
        val params = MapSqlParameterSource()
            .addValue("name", roomForAdd.name)
            .addValue("count_of_people", roomForAdd.countOfPeople)
        val id = simpleJdbcInsert.executeAndReturnKey(params) as Long
        return id
    }

    override fun updateRoom(id: Long, room: Room) {
        jdbcTemplate.update(
            "update rooms set name=?, count_of_people=? where id=?",
            room.name,
            room.countOfPeople,
            id
        )
    }
}
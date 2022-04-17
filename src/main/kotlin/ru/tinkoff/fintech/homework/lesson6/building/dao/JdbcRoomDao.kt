package ru.tinkoff.fintech.homework.lesson6.building.dao

import org.springframework.context.annotation.Profile
import org.springframework.jdbc.core.DataClassRowMapper
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.simple.SimpleJdbcInsert
import org.springframework.stereotype.Component
import ru.tinkoff.fintech.homework.lesson6.building.entities.Room
import java.util.function.Function
import java.util.stream.Collectors


@Component
@Profile("jdbc")
class JdbcRoomDao(
    private val jdbcTemplate: JdbcTemplate
) : RoomDao {
    private val roomRowMapper = DataClassRowMapper(Room::class.java)

    override fun getRooms(): Map<Long, Room> {
        val resultStream = jdbcTemplate.queryForStream(GET_ALL_ROOMS_QUERY, roomRowMapper)

        return resultStream.collect(
            Collectors.toMap(
                { it.id },
                Function.identity()
            )
        )
    }

    override fun getRoom(roomId: Long): Room? {
        val row = jdbcTemplate.queryForRowSet(GET_ROOM_QUERY, roomId)
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

    override fun updateRoom(room: Room) {
        jdbcTemplate.update(UPDATE_ROOM_QUERY, room.name, room.countOfPeople, room.id)
    }

    private val GET_ALL_ROOMS_QUERY = "select * from rooms"
    private val GET_ROOM_QUERY = "select * from rooms where id = ?";
    private val UPDATE_ROOM_QUERY = "update rooms set name = ?, count_of_people = ? where id = ?"
}
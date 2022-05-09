package ru.tinkoff.fintech.homework.lesson6.building.dao.room

import org.springframework.context.annotation.Profile
import org.springframework.jdbc.core.DataClassRowMapper
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Component
import ru.tinkoff.fintech.homework.lesson6.building.entities.Room


@Component
@Profile("jdbc")
class JdbcRoomDao(
    private val jdbcTemplate: JdbcTemplate
) : RoomDao {
    private val roomRowMapper = DataClassRowMapper(Room::class.java)

    override fun getRooms(): List<Room> {
        return jdbcTemplate.query(GET_ALL_ROOMS_QUERY, roomRowMapper)
    }

    override fun getRoom(roomId: Long): Room? {
        return jdbcTemplate.query(GET_ROOM_QUERY, roomRowMapper, roomId).firstOrNull()
    }

    override fun addRoom(newRoom: Room): Long {
        return jdbcTemplate.queryForObject(ADD_ROOM_QUERY, Long::class.java, newRoom.name, newRoom.countOfPeople)
            ?: throw IllegalStateException("Не удалось добавить комнату")
    }

    override fun updateRoom(room: Room) {
        jdbcTemplate.update(UPDATE_ROOM_QUERY, room.name, room.countOfPeople, room.id)
    }

    private val GET_ALL_ROOMS_QUERY = "select * from rooms"
    private val GET_ROOM_QUERY = "select * from rooms where id = ?";
    private val ADD_ROOM_QUERY = "insert into rooms (name, count_of_people) values (?, ?) returning id"
    private val UPDATE_ROOM_QUERY = "update rooms set name = ?, count_of_people = ? where id = ?"
}
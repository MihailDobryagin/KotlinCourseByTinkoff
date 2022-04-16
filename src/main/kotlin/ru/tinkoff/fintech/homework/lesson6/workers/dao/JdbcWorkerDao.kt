package ru.tinkoff.fintech.homework.lesson6.workers.dao

import org.springframework.context.annotation.Profile
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.simple.SimpleJdbcInsert
import org.springframework.stereotype.Component
import ru.tinkoff.fintech.homework.lesson6.workers.entities.Worker

@Component
@Profile("jdbc")
class JdbcWorkerDao(
    val jdbcTemplate: JdbcTemplate
) : WorkerDao {
    override fun getWorkers(): Map<Long, Worker> {
        val row = jdbcTemplate.queryForRowSet("select * from workers")
        val workers = mutableMapOf<Long, Worker>()

        while (row.next()) {
            val id = row.getLong("id")
            val name = row.getString("name")!!
            val roomId = row.getLong("room_id")
            workers[id] = Worker(id, name, roomId)
        }

        return workers
    }

    override fun getWorker(workerId: Long): Worker? {
        val row = jdbcTemplate.queryForRowSet("select * from workers where id=?", workerId)
        row.next()
        val id = row.getLong("id")
        val name = row.getString("name")!!
        val roomId = row.getLong("room_id")

        return Worker(id, name, roomId)
    }

    override fun addWorker(workerForAdd: Worker): Long {
        val simpleJdbcInsert = SimpleJdbcInsert(jdbcTemplate).withTableName("workers").usingGeneratedKeyColumns("id")
        val params = MapSqlParameterSource()
            .addValue("name", workerForAdd.name)
            .addValue("room_id", workerForAdd.roomId)
        val id = simpleJdbcInsert.executeAndReturnKey(params) as Long
        return id
    }

    override fun updateWorker(id: Long, worker: Worker) {
        jdbcTemplate.update(
            "update workers set name=?, roomId=? where id=?",
            worker.name,
            worker.roomId,
            id
        )
    }
}
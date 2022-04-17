package ru.tinkoff.fintech.homework.lesson6.workers.dao

import org.springframework.context.annotation.Profile
import org.springframework.jdbc.core.DataClassRowMapper
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.simple.SimpleJdbcInsert
import org.springframework.stereotype.Component
import ru.tinkoff.fintech.homework.lesson6.workers.entities.Worker
import java.util.function.Function
import java.util.stream.Collectors

@Component
@Profile("jdbc")
class JdbcWorkerDao(
    private val jdbcTemplate: JdbcTemplate
) : WorkerDao {
    private val workerRowMapper = DataClassRowMapper(Worker::class.java)

    override fun getWorkers(): Map<Long, Worker> {
        val resultStream = jdbcTemplate.queryForStream(GET_ALL_WORKERS_QUERY, workerRowMapper)

        return resultStream.collect(
            Collectors.toMap(
                { it.id },
                Function.identity()
            )
        )
    }

    override fun getWorker(workerId: Long): Worker? {
        val row = jdbcTemplate.queryForRowSet(GET_WORKER_QUERY, workerId)
        row.next()
        val id = row.getLong("id")
        val name = row.getString("name")!!
        val roomId = row.getLong("room_id")

        return Worker(id, name, roomId)
    }

    override fun addWorker(newWorker: Worker): Long {
        val simpleJdbcInsert = SimpleJdbcInsert(jdbcTemplate).withTableName("workers").usingGeneratedKeyColumns("id")
        val params = MapSqlParameterSource()
            .addValue("name", newWorker.name)
            .addValue("room_id", newWorker.roomId)
        val id = simpleJdbcInsert.executeAndReturnKey(params) as Long
        return id
    }

    override fun updateWorker(worker: Worker) {
        jdbcTemplate.update(UPDATE_WORKER_QUERY, worker.name, worker.roomId, worker.id)
    }

    private val GET_ALL_WORKERS_QUERY = "select * from workers"
    private val GET_WORKER_QUERY = "select * from workers where id = ?"
    private val UPDATE_WORKER_QUERY = "update workers set name = ?, roomId = ? where id = ?"
}
package ru.tinkoff.fintech.homework.lesson6.workers.dao

import org.springframework.context.annotation.Profile
import org.springframework.jdbc.core.DataClassRowMapper
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.simple.SimpleJdbcInsert
import org.springframework.stereotype.Component
import ru.tinkoff.fintech.homework.lesson6.workers.entities.Worker

@Component
@Profile("jdbc")
class JdbcWorkerDao(
    private val jdbcTemplate: JdbcTemplate
) : WorkerDao {
    private val workerRowMapper = DataClassRowMapper(Worker::class.java)

    override fun getWorkers(): List<Worker> {
        return jdbcTemplate.query(GET_ALL_WORKERS_QUERY, workerRowMapper)
    }

    override fun getWorker(workerId: Long): Worker? {
        return jdbcTemplate
            .query(GET_WORKER_QUERY, workerRowMapper, workerId)
            .firstOrNull()
    }

    override fun addWorker(newWorker: Worker): Long {
        return jdbcTemplate.queryForObject(ADD_WORKER_QUERY, Long::class.java, newWorker.name, newWorker.roomId) ?: throw IllegalStateException("Не удалось добавить работника")
    }

    override fun updateWorker(worker: Worker) {
        jdbcTemplate.update(UPDATE_WORKER_QUERY, worker.name, worker.roomId, worker.id)
    }

    private val GET_ALL_WORKERS_QUERY = "select * from workers"
    private val GET_WORKER_QUERY = "select * from workers where id = ?"
    private val ADD_WORKER_QUERY = "insert into workers (name, room_id) values (?, ?) returning id"
    private val UPDATE_WORKER_QUERY = "update workers set name = ?, roomId = ? where id = ?"
}
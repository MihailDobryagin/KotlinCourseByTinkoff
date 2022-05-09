package ru.tinkoff.fintech.homework.lesson6.workers.dao.moveReq

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Profile
import org.springframework.jdbc.core.DataClassRowMapper
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Component
import ru.tinkoff.fintech.homework.lesson6.utils.MoveWorkerReqStatus
import ru.tinkoff.fintech.homework.lesson6.workers.entities.MoveWorkerRequest

@Component
@Profile("jdbc")
class JdbcMoveWorkerReqDao(
    @Value("\${myapplication.db.move-worker-req.table-name}") private val tableName: String,
    private val jdbcTemplate: JdbcTemplate
) : MoveWorkerReqDao {

    private val moveWorkerReqRowMapper = DataClassRowMapper(MoveWorkerRequest::class.java)

    override fun getReq(id: Long): MoveWorkerRequest? {
        return jdbcTemplate
            .query(GET_QUERY, moveWorkerReqRowMapper, id)
            .firstOrNull()
    }

    override fun addReq(newRequest: MoveWorkerRequest): Long {
        return jdbcTemplate.queryForObject(ADD_QUERY, Long::class.java, newRequest.status.name, newRequest.message)
    }

    override fun updateReq(request: MoveWorkerRequest) {
        jdbcTemplate.update(UPDATE_QUERY, request.status.name, request.message, request.workerId, request.id)
    }

    override fun getStatus(id: Long): MoveWorkerReqStatus? {
        return jdbcTemplate.queryForObject(GET_STATUS_QUERY, MoveWorkerReqStatus::class.java, id)
    }

    override fun changeStatus(id: Long, newStatus: MoveWorkerReqStatus) {
        jdbcTemplate.update(UPDATE_STATUS_QUERY, newStatus.name, id)
    }

    override fun changeToFailed(id: Long, message: String) {
        jdbcTemplate.update(FAIL_REQ_QUERY, message, id)
    }

    override fun getWorkerId(id: Long): Long? {
        return jdbcTemplate.queryForObject(GET_WORKER_ID_QUERY, Long::class.java, id)
    }

    private val GET_QUERY = "select * from $tableName where id = ?"
    private val ADD_QUERY = "insert into $tableName (status, message) values (?, ?) returning id"
    private val UPDATE_QUERY = "update $tableName set status = ?, message = ?, worker_id = ? where id = ?"
    private val GET_STATUS_QUERY = "select status from $tableName where id = ?"
    private val UPDATE_STATUS_QUERY = "update $tableName set status = ? where id = ?"
    private val FAIL_REQ_QUERY =
        "update $tableName set status = ${MoveWorkerReqStatus.FAILED}, message = ? where id = ?"
    private val GET_WORKER_ID_QUERY = "select worker_id from $tableName where id = ?"
}
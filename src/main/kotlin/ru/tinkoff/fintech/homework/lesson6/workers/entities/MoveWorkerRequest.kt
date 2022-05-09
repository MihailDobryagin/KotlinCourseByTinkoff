package ru.tinkoff.fintech.homework.lesson6.workers.entities

import org.hibernate.annotations.Type
import ru.tinkoff.fintech.homework.lesson6.utils.MoveWorkerReqStatus
import javax.persistence.*

@Entity
@Table(name = "move_worker_requests")
data class MoveWorkerRequest(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @Column(columnDefinition = "varchar")
    val status: MoveWorkerReqStatus = MoveWorkerReqStatus.NEW,
    val message: String = "",
    val workerId: Long? = null,
)
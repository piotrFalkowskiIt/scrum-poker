package uk.co.objectivity.scrum.poker.task

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.data.repository.CrudRepository
import uk.co.objectivity.scrum.poker.session.ScrumSession
import java.util.UUID
import javax.persistence.Entity
import javax.persistence.OneToMany
import javax.persistence.ManyToOne
import javax.persistence.JoinColumn
import javax.persistence.Id
import javax.persistence.GeneratedValue

@Entity class TaskEntity(
        val title: String,
        @OneToMany(mappedBy = "task") val estimations: MutableList<TaskEstimationEntity> = mutableListOf(),
        @ManyToOne @JoinColumn(name = "session_id") @JsonIgnore var session: ScrumSession,
        @Id @GeneratedValue val id: UUID
)
internal interface TaskRepository : CrudRepository<TaskEntity, UUID>

@Entity class TaskEstimationEntity(
        val estimationValue: Int,
        val userId: Long,
        @ManyToOne @JoinColumn(name = "task_id") @JsonIgnore var task: TaskEntity,
        @Id @GeneratedValue val id: UUID
)
internal interface TaskEstimationRepository : CrudRepository<TaskEstimationEntity, Long>

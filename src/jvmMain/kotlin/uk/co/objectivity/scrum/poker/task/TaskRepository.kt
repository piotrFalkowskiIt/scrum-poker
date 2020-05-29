package uk.co.objectivity.scrum.poker.task

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.data.repository.CrudRepository
import uk.co.objectivity.scrum.poker.session.ScrumSession
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToMany

@Entity
class TaskEstimation(
        val value: Int,
        @ManyToOne @JoinColumn(name = "task_id") @JsonIgnore var task: Task? = null,
        @Id @GeneratedValue val id: Long? = null
)

@Entity
class Task(
        val title: String,
        @OneToMany(mappedBy = "task") val estimations: MutableList<TaskEstimation> = mutableListOf(),
        @ManyToOne @JoinColumn(name = "session_id") @JsonIgnore var session: ScrumSession? = null,
        @Id @GeneratedValue val id: Long? = null
)

internal interface TaskRepository : CrudRepository<Task, Long>
internal interface TaskEstimationRepository : CrudRepository<TaskEstimation, Long>

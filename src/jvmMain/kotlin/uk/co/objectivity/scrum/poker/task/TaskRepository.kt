package uk.co.objectivity.scrum.poker.task

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.data.repository.CrudRepository
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
        @Id @GeneratedValue val id: Long? = null
)

interface TaskRepository : CrudRepository<Task, Long>
interface TaskEstimationRepository : CrudRepository<TaskEstimation, Long>
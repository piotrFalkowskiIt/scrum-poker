package uk.co.objectivity.scrum.poker.task

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.data.repository.CrudRepository
import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToOne
import javax.persistence.OneToMany

@Entity
class TaskEstimation(
        val value: Int,
        @ManyToOne(fetch = FetchType.LAZY) @JsonIgnore val task: Task? = null,
        @Id @GeneratedValue val id: Long? = null
)

@Entity
class Task(
        val title: String,
        @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true ) val estimations: MutableList<TaskEstimation> = mutableListOf(),
        @Id @GeneratedValue val id: Long? = null
)

internal interface TaskRepository : CrudRepository<Task, Long>
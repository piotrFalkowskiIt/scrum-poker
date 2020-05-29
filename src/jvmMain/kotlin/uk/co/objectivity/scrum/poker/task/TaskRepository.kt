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
        var value: Int,
        @ManyToOne(fetch = FetchType.LAZY) @JsonIgnore var task: Task? = null,
        @Id @GeneratedValue var id: Long? = null
)

@Entity
class Task(
        var title: String,
        @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true ) var estimations: MutableList<TaskEstimation> = mutableListOf(),
        @Id @GeneratedValue var id: Long? = null
)

interface TaskRepository : CrudRepository<Task, Long>
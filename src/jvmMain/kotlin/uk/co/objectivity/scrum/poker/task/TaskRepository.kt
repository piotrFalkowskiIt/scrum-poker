package uk.co.objectivity.scrum.poker.task

import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID
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
        @ManyToOne(fetch = FetchType.LAZY) var task: Task,
        @Id @GeneratedValue var id: UUID
)

@Entity
class Task(
        var title: String,
        @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true ) var estimations: MutableList<TaskEstimation> = mutableListOf(),
        @Id @GeneratedValue var id: UUID
)

interface TaskRepository : JpaRepository<Task, Long>
package uk.co.objectivity.scrum.poker.session

import org.springframework.data.jpa.repository.JpaRepository
import uk.co.objectivity.scrum.poker.task.Task
import java.time.Instant
import java.util.UUID
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.OneToMany

@Entity class ScrumSession (
        @Id val id: UUID,
        val name: String,
        val created: Instant,
        @OneToMany(mappedBy = "session") val tasks: MutableList<Task> = mutableListOf()
)

internal interface SessionRepository : JpaRepository<ScrumSession, UUID>

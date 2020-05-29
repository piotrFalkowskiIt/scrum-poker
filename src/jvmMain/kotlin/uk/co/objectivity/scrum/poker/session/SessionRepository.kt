package uk.co.objectivity.scrum.poker.session

import org.springframework.data.jpa.repository.JpaRepository
import java.time.Instant
import java.util.UUID
import javax.persistence.Entity
import javax.persistence.Id

@Entity class ScrumSession (
        @Id val id: UUID,
        val name: String,
        val created: Instant
)

internal interface SessionRepository : JpaRepository<ScrumSession, UUID>

package uk.co.objectivity.scrum.poker.user

import org.springframework.data.jpa.repository.JpaRepository
import uk.co.objectivity.scrum.poker.session.ScrumSession
import javax.persistence.*

@Entity data class User(
        @Id
        @GeneratedValue val id: Long,
        val userName: String,
        @ManyToOne(fetch = FetchType.LAZY) val session: ScrumSession
)

internal interface UserRepository : JpaRepository<User, Long>

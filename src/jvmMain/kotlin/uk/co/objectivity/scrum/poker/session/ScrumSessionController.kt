package uk.co.objectivity.scrum.poker.session

import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import java.util.UUID
import java.util.UUID.randomUUID

@RestController
class ScrumSessionController (
        val sessionRepository: SessionRepository) {

    @PostMapping("/session")
    fun createSession() : SessionInfo {
        val session = ScrumSession(randomUUID())
        return sessionRepository.save(session).mapToInfo()
    }

    @GetMapping("/session/{id}")
    fun testGetSession(@PathVariable("id") sessionId: UUID) : ScrumSession {
        return ScrumSession(randomUUID())
        //return sessionRepository.findById(sessionId).map { SessionInfo(this.id) }.orElseGet { "Session not found!" }
    }

}

sealed class SessionResponse
data class SessionInfo (val id: UUID) : SessionResponse()
data class SessionError (val message: String) : SessionResponse()

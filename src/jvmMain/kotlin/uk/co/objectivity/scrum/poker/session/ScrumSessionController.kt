package uk.co.objectivity.scrum.poker.session

import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
class ScrumSessionController (
        val sessionRepository: SessionRepository) {

    @PostMapping("/session")
    fun createSession() : ScrumSession {
        val sessionId = UUID.randomUUID()
        val session = ScrumSession(sessionId)
        return sessionRepository.save(session)
    }

    @GetMapping("/session/{id}")
    fun testGetSession(@PathVariable("id") sessionId: UUID) : ScrumSession {
        return ScrumSession(UUID.randomUUID())
        //return sessionRepository.findById(sessionId).orElseGet { "Session not found!" }
    }

}
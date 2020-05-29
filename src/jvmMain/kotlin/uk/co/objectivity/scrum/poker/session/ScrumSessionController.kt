package uk.co.objectivity.scrum.poker.session

import org.springframework.web.bind.annotation.*
import java.time.Clock
import java.time.Instant
import java.util.UUID
import java.util.UUID.randomUUID

internal class NewSessionRequest (val name: String)

internal sealed class Response {
    class SessionInfo (val id: UUID, val name: String, val created: Instant) : Response()
    class SessionError (val message: String = "Unexpected error") : Response()
}

private val NOT_FOUND_RESPONSE = Response.SessionError("Session not found")

@RequestMapping("/api/session")
@RestController
private class ScrumSessionController (
        val sessionRepository: SessionRepository) {

    @PostMapping
    private fun createSession(@RequestBody sessionData: NewSessionRequest) : Response {
        return sessionRepository.save(newSession(sessionData.name)).mapToInfo()
    }

    @GetMapping("/{id}")
    private fun getSessionInfo(@PathVariable("id") sessionId: UUID) : Response {
        return sessionRepository.findById(sessionId)
                .map{ it.mapToInfo() }
                .orElseGet{ NOT_FOUND_RESPONSE }
    }

}

private fun newSession(sessionName: String) : ScrumSession {
    return ScrumSession(randomUUID(), sessionName, Clock.systemUTC().instant())
}

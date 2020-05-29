package uk.co.objectivity.scrum.poker.session

import org.springframework.web.bind.annotation.*
import java.util.UUID
import java.util.UUID.randomUUID

internal class NewSessionRequest (val name: String)

internal sealed class Response {
    class SessionInfo (val id: UUID, val name: String) : Response()
    class SessionError (val message: String) : Response()
}

private val NOT_FOUND_RESPONSE = Response.SessionError("Session not found")


@RestController
private class ScrumSessionController (
        val sessionRepository: SessionRepository) {

    @PostMapping("/api/session")
    private fun createSession(@RequestBody sessionData: NewSessionRequest) : Response {
        return sessionRepository.save(newSession(sessionData)).mapToInfo()
    }

    @GetMapping("/api/session/{id}")
    private fun getSession(@PathVariable("id") sessionId: UUID) : Response {
        return sessionRepository.findById(sessionId)
                .map{ it.mapToInfo() }
                .orElseGet{ NOT_FOUND_RESPONSE }
    }

}

private fun newSession(sessionData: NewSessionRequest) : ScrumSession {
    return ScrumSession(randomUUID(), sessionData.name)
}

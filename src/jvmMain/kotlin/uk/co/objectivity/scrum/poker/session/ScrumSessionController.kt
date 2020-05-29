package uk.co.objectivity.scrum.poker.session

import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import java.util.UUID
import java.util.UUID.randomUUID


sealed class Response {
    data class SessionInfo (val id: UUID) : Response()
    data class SessionError (val message: String) : Response()
}

private val NOT_FOUND_RESPONSE = Response.SessionError("Session not found")


@RestController
private class ScrumSessionController (
        val sessionRepository: SessionRepository) {

    @PostMapping("/session")
    private fun createSession() : Response {
        return sessionRepository.save(newSession()).mapToInfo()
    }

    @GetMapping("/session/{id}")
    private fun getSession(@PathVariable("id") sessionId: UUID) : Response {
        return sessionRepository.findById(sessionId)
                .map { it.mapToInfo() }
                .orElseGet{ NOT_FOUND_RESPONSE }
    }

}

private fun newSession() : ScrumSession {
    return ScrumSession(randomUUID())
}

package uk.co.objectivity.scrum.poker.user

import org.springframework.web.bind.annotation.*
import uk.co.objectivity.scrum.poker.session.SessionRepository
import java.util.*

internal sealed class UserResponse {
    class User(val id: Long, val userName: String) : UserResponse()
    class Error(val message: String = "Unexpected error") : UserResponse()
}

data class UserRequest(val userName: String, val sessionId: String)

@RestController
private class UserController(
        val userRepository: UserRepository,
        val sesssionRepository: SessionRepository) {

    @GetMapping("/api/users/{id}")
    fun getUser(@PathVariable("id") userId: Long): UserResponse {
        return userRepository.findById(userId)
                .map { it.toResponse() }
                .orElse(UserResponse.Error("not found"))
    }

    @PostMapping("/api/users")
    fun createUser(@RequestBody request: UserRequest): UserResponse {
        return sesssionRepository.findById(UUID.fromString(request.sessionId))
                .map { userRepository.save(request.toEntity(it)) }
                .map { it.toResponse() }
                .orElse(UserResponse.Error("not found"))
    }
}

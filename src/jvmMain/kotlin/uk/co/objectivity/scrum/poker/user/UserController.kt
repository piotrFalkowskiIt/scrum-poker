package uk.co.objectivity.scrum.poker.user

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
private class UserController(
        val userRepository: UserRepository) {

    @GetMapping("/user/{id}")
    fun getUser(@PathVariable("id") userId: Long) : Optional<User> {
        return userRepository.findById(userId)
    }
}
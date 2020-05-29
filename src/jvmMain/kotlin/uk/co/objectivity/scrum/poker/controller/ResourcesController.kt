package uk.co.objectivity.scrum.poker.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController("/")
class ResourcesController {
    @GetMapping("test")
    fun getAny(): String {
        return "Test"
    }
}

package uk.co.objectivity.scrum.poker.task

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import uk.co.objectivity.scrum.poker.session.SessionRepository
import java.util.*

@RequestMapping("/api/task")
@RestController
private class TaskController(val taskRepository: TaskRepository,
                             val taskEstimationRepository: TaskEstimationRepository,
                             val sessionRepository: SessionRepository) {

    @GetMapping("/{id}")
    fun getTaskSummary(@PathVariable id: Long) = taskRepository.findById(id)

    @PostMapping("/{sessionId}")
    fun createTask(@PathVariable sessionId: UUID, @RequestBody task: Task) {
        sessionRepository.findById(sessionId).ifPresent {
            task.session = it
            taskRepository.save(task)
        }
    }

    @GetMapping("/{id}/estimation")
    fun getTaskEstimations(@PathVariable id: Long): MutableList<TaskEstimation> =
            taskRepository.findById(id).map(Task::estimations).orElse(mutableListOf())

    @PostMapping("/{id}/estimation")
    fun addTaskEstimation(@PathVariable id: Long, @RequestBody taskEstimation: TaskEstimation) {
        taskRepository.findById(id).ifPresent {
            taskEstimation.task = it
            taskEstimationRepository.save(taskEstimation)
        }
    }

}


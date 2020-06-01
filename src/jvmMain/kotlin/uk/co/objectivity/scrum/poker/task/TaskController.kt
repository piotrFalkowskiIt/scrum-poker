package uk.co.objectivity.scrum.poker.task

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import uk.co.objectivity.scrum.poker.session.SessionRepository
import java.util.UUID

@RequestMapping("/api/task")
@RestController
private class TaskController(val taskRepository: TaskRepository,
                             val taskEstimationRepository: TaskEstimationRepository,
                             val sessionRepository: SessionRepository) {

    @GetMapping("/{id}")
    private fun getTask(@PathVariable("id") taskId: UUID): TaskResponse =
            taskRepository.findById(taskId)
                    .map { it.toResponse() }
                    .orElse(TaskResponse.Error("Task not found!"))

    @PostMapping
    private fun createTask(@RequestBody request: NewTaskRequest): TaskResponse =
            sessionRepository.findById(UUID.fromString(request.sessionId))
                    .map { taskRepository.save(request.toEntity(it)) }
                    .map { it.toResponse() }
                    .orElse(TaskResponse.Error("Session not found!"))

    @PostMapping("/{id}/estimation")
    private fun createTaskEstimation(@PathVariable("id") taskId: UUID, @RequestBody request: NewTaskEstimationRequest) : TaskEstimationResponse =
            taskRepository.findById(taskId)
                    .map { taskEstimationRepository.save(request.toEntity(it, request.userId)) }
                    .map { it.toResponse() }
                    .orElse(TaskEstimationResponse.Error("Task not found!"))

}

internal sealed class TaskResponse {
    class Task(val id: UUID, val title: String, val estimations: List<TaskEstimationResponse>) : TaskResponse()
    class Error(val error: String = "Unexpected error occurred") : TaskResponse()
}

internal sealed class TaskEstimationResponse {
    class TaskEstimation(val taskId: UUID, val estimationValue: Int, val userId: Long) : TaskEstimationResponse()
    class Error(val error: String = "Unexpected error occurred") : TaskEstimationResponse()
}

internal class NewTaskRequest(val sessionId: String, val taskTitle: String)

internal class NewTaskEstimationRequest(val userId: Long, val estimationValue: Int)


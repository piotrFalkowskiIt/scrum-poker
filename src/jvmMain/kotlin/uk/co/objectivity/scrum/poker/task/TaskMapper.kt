package uk.co.objectivity.scrum.poker.task

import uk.co.objectivity.scrum.poker.session.ScrumSession
import java.util.UUID

internal fun TaskEntity.toResponse() : TaskResponse =
        TaskResponse.Task(this.id, this.title, this.estimations.map { it.toResponse() })

internal fun TaskEstimationEntity.toResponse() : TaskEstimationResponse =
        TaskEstimationResponse.TaskEstimation(this.task.id, this.estimationValue, this.userId)

internal fun NewTaskRequest.toEntity(session: ScrumSession) =
        TaskEntity(
                id = UUID.randomUUID(),
                title = this.taskTitle,
                session = session
        )

internal fun NewTaskEstimationRequest.toEntity(task: TaskEntity, userId: Long) =
        TaskEstimationEntity(
                id = UUID.randomUUID(),
                estimationValue = this.estimationValue,
                task = task,
                userId = userId
        )
package uk.co.objectivity.scrum.poker.user

import uk.co.objectivity.scrum.poker.session.ScrumSession

internal fun User.toResponse(): UserResponse {
    return UserResponse.User(this.id, this.userName)
}

internal fun UserRequest.toEntity(session: ScrumSession): User {
    return User(id = 0, userName = this.userName, session = session)
}

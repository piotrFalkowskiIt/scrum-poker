package uk.co.objectivity.scrum.poker.session

internal fun ScrumSession.mapToInfo(): Response {
    return Response.SessionInfo(this.id)
}

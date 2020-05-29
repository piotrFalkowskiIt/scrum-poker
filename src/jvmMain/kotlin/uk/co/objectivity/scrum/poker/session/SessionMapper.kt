package uk.co.objectivity.scrum.poker.session

fun ScrumSession.mapToInfo(): Response {
    return Response.SessionInfo(this.id)
}

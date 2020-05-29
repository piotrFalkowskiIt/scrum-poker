package uk.co.objectivity.scrum.poker.session

fun ScrumSession.mapToInfo(): SessionInfo {
    return SessionInfo(this.id)
}

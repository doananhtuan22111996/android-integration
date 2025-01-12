package vn.root.app.pages.draws.face

sealed class Face {
    data object Happy : Face()
    data object Sad : Face()
}

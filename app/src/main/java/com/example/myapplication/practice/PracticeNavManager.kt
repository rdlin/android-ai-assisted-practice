package com.example.myapplication.practice

class PracticeNavManager(
    initialRoutePath: String = PracticeRoute.Launcher.path,
    private val backStack: MutableList<String> = mutableListOf(initialRoutePath),
) {
    val currentRoute: PracticeRoute
        get() = PracticeRoute.fromPath(backStack.lastOrNull() ?: PracticeRoute.Launcher.path)

    val canNavigateBack: Boolean
        get() = backStack.size > 1

    fun navigateTo(route: PracticeRoute) {
        if (currentRoute.path != route.path) {
            backStack.add(route.path)
        }
    }

    fun navigateBack(): Boolean {
        if (!canNavigateBack) return false
        backStack.removeAt(backStack.lastIndex)
        return true
    }

    fun snapshot(): List<String> = backStack.toList()

    companion object {
        fun fromSnapshot(snapshot: List<String>): PracticeNavManager {
            val sanitized = snapshot.ifEmpty { listOf(PracticeRoute.Launcher.path) }.toMutableList()
            return PracticeNavManager(
                initialRoutePath = sanitized.first(),
                backStack = sanitized,
            )
        }
    }
}

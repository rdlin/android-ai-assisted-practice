# Android AI-Assisted Coding Practice

This project is a small Jetpack Compose practice harness for one-hour Android coding interview drills. It starts intentionally light so each scenario can be implemented from a clean baseline while still encouraging modern Android habits.

The setup is meant to feel like an interview starter zip: you get a running app, explicit navigation, seed data, partial feature slices, and a few tests. The timed exercise is to extend the existing code rather than create the app from an empty project.

## Baseline

- Kotlin and Jetpack Compose UI
- Material 3 components
- A tiny in-app scenario browser in `MainActivity`
- A lightweight route/back-stack manager in `app/src/main/java/com/example/myapplication/practice`
- Explicit feature gates for the three practice problems
- Starter domain models, fake repositories, seed data, and partial screens for each problem
- Scenario metadata in `app/src/main/java/com/example/myapplication/practice`
- Unit tests in `app/src/test/java/com/example/myapplication/practice`

## Recommended Interview Workflow

1. Spend 5 minutes restating the requirements and choosing the smallest architecture that can hold the feature.
2. Ask AI for a concise implementation plan and file map.
3. Identify what starter code already exists and what should be preserved.
4. Implement one vertical slice first: ViewModel state, one interaction, and one test.
5. Add persistence and edge states once the happy path works.
6. Reserve the final 10 minutes for tests, cleanup, and a short explanation of tradeoffs.

## Modern Android Practices To Practice

- Compose screens should be stateless where practical, taking state and event lambdas from a ViewModel.
- Keep domain models separate from persistence or API DTOs when the mapping adds clarity.
- Put business rules in plain Kotlin classes or repository methods that can be unit tested.
- Prefer immutable UI state data classes and sealed types for loading, content, empty, and error states.
- Use Room or DataStore for real persistence in the scenario implementation.
- Use fake repositories or fake data sources in tests instead of relying on Android framework classes.

## Scenarios

The problem prompts and companion hint files live inside each feature scaffold:

- `app/src/main/java/com/example/myapplication/practice/fieldnotes/problem.md`
- `app/src/main/java/com/example/myapplication/practice/fieldnotes/hints.md`
- `app/src/main/java/com/example/myapplication/practice/calendar/problem.md`
- `app/src/main/java/com/example/myapplication/practice/calendar/hints.md`
- `app/src/main/java/com/example/myapplication/practice/expenses/problem.md`
- `app/src/main/java/com/example/myapplication/practice/expenses/hints.md`

Use the `*-problem.md` files during timed practice. Open the matching `*-hints.md` file afterward, or only if you get blocked.

Run checks with:

```bash
./gradlew test
```

# Codebase Guide

## What Matters

- Entry point: `MainActivity.kt` launches straight into the field notes exercise.
- Main screen: `FieldNotesPracticeScreen.kt` renders the current Compose list UI.
- State owner: none yet; the composable reads from the repository directly.
- Data source: `InMemoryFieldNotesRepository` returns seeded notes from `FieldNotesStarterData`.
- Tests: `FieldNotesRepositoryTest.kt` covers the starter seed repository.
- Test run note: `./gradlew test` could not run here because no Java runtime was available.

## Important Files

- `app/src/main/java/com/example/myapplication/MainActivity.kt`: app launch path and screen host.
- `app/src/main/java/com/example/myapplication/practice/fieldnotes/FieldNotesPracticeScreen.kt`: main Compose UI to edit.
- `app/src/main/java/com/example/myapplication/practice/fieldnotes/FieldNote.kt`: note model, repository interface, in-memory repository, seed data.
- `app/src/main/java/com/example/myapplication/practice/fieldnotes/problem.md`: exercise requirements and acceptance criteria.
- `app/src/test/java/com/example/myapplication/practice/fieldnotes/FieldNotesRepositoryTest.kt`: existing test style for field notes.

## Current Flow

- `MainActivity` creates the Compose content and calls `FieldNotesPracticeScreen`.
- `FieldNotesPracticeScreen` creates `InMemoryFieldNotesRepository` with `remember`.
- The screen calls `repository.loadNotes()` during composition.
- Notes are passed into `LazyColumn.items`.
- `FieldNoteRow` renders each note's title, description, status, priority, room, and age.

## Risks To Preserve Or Watch

- Keep the app launching directly into `FieldNotesPracticeScreen`.
- Preserve the seeded notes while changing storage or state handling.
- Avoid leaving data reads directly inside composition once notes can change.
- `updatedMinutesAgo` is display data, not durable persisted time.
- Chip click handlers are currently no-ops, so any new behavior needs clear intent.

## Suggested Inspection Order

1. `app/src/main/java/com/example/myapplication/practice/fieldnotes/problem.md`
2. `app/src/main/java/com/example/myapplication/MainActivity.kt`
3. `app/src/main/java/com/example/myapplication/practice/fieldnotes/FieldNotesPracticeScreen.kt`
4. `app/src/main/java/com/example/myapplication/practice/fieldnotes/FieldNote.kt`
5. `app/src/test/java/com/example/myapplication/practice/fieldnotes/FieldNotesRepositoryTest.kt`

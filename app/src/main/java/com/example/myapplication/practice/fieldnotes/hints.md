# Scenario 01 Hints: Offline Field Notes

## Architecture Suggestions

- Treat `FieldNotesPracticeScreen` as starter UI, then introduce a ViewModel instead of reading from the repository directly in the composable.
- Keep `FieldNotesRepository` as the seam between UI state and persistence.
- Compose screen with state passed in and event callbacks passed out.
- ViewModel exposing immutable UI state.
- Repository interface hiding the storage details.
- Room database for persistence.
- Plain Kotlin tests for search/filter/sort behavior.

## AI Practice Goals

- Ask AI to propose a minimal file structure before coding.
- Ask AI to identify which starter files should be preserved and which should be replaced.
- Ask AI to generate the Room entity, DAO, and repository, then review the generated API names.
- Ask AI for edge cases after the first implementation.
- Ask AI to write tests for the business rules, not only Compose UI.

## Stretch Goals

- Add a detail screen with simple navigation.
- Add priority chips and quick filters.
- Add an undo action after delete.
- Add a fake sync status field without implementing networking.

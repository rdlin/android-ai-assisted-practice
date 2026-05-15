# Implementation Tracking

## Current Goal

Persist seeded field notes with Room, keep the repository boundary clear, and validate sorted/filterable note data before adding CRUD screens.

## Phase Plan

| Phase | Status | Scope | Files | Validation | Notes |
| --- | --- | --- | --- | --- | --- |
| 1 | done | Add Room setup and persisted note schema | `app/build.gradle.kts`, `gradle.properties`, `gradle/libs.versions.toml`, `database/FieldNoteEntity.kt`, `database/FieldNotesDao.kt`, `database/FieldNotesDatabase.kt` | `./gradlew test` passed with `JAVA_HOME` set | Persists current model only; chips are status/priority/room, not tags |
| 2 | done | Seed starter notes once and load persisted notes through repository | `FieldNote.kt`, `MainActivity.kt`, `ui/FieldNotesPracticeScreen.kt`, `data/`, `presentation/` | `./gradlew test` passed with `JAVA_HOME` set | UI now reads immutable ViewModel state |
| 3 | done | Add last-updated sorting and status filter logic | `presentation/FieldNotesFilters.kt`, `FieldNotesUiState.kt`, `FieldNotesViewModel.kt` | `./gradlew test` passed with `JAVA_HOME` set | Pure logic; default filter keeps current UI behavior |
| 4 | done | Add focused repository/use-case test | `FieldNotesFiltersTest.kt` | `./gradlew test` passed with `JAVA_HOME` set | Covers visible sorting/filtering behavior |
| 5 | done | P1: Add note flow | `ui/`, `presentation/`, `data/`, `database/` | `./gradlew test` passed with `JAVA_HOME` set | Separate screen; smallest slice saves required fields |
| 6 | done | P1: Edit note flow | `ui/`, `presentation/`, `data/`, `database/` | `./gradlew test` passed with `JAVA_HOME` set | Reuses add form and preserves status |
| 7 | done | P1: Delete with confirmation | `ui/`, `presentation/`, `data/`, `database/` | `./gradlew test` passed with `JAVA_HOME` set | Confirm before removing persisted note |
| 8 | done | Differentiate status, priority, and room chips visually | `FieldNoteRow.kt` | `./gradlew test` passed with `JAVA_HOME` set | Same-looking chips are now labeled/colored by meaning |
| 9 | done | Stretch: add naive search controls | `FieldNotesControls.kt`, `FieldNotesUiState.kt`, `FieldNotesEvent.kt`, `FieldNotesViewModel.kt`, `FieldNotesFilters.kt`, `FieldNotesFiltersTest.kt` | `./gradlew test` passed with `JAVA_HOME` set | Searches title, description, and room |
| 10 | todo | Clarify or add real tags only if requirement changes | `FieldNote.kt`, `FieldNoteEntity.kt`, DAO/schema files | model/schema test | Current prompt does not define user tags |

## File / Package Organization

- UI files: `practice/fieldnotes/ui/` for new screens and reusable composables; existing `FieldNotesPracticeScreen.kt` can move there when touched.
- ViewModel/state files: `practice/fieldnotes/presentation/` for ViewModel, UI state, events, filters, and form state.
- Data/repository files: `practice/fieldnotes/data/` for repository implementations and mappers that are not Room-specific.
- Database/entity/DAO files: `practice/fieldnotes/database/` for Room database, entities, and DAOs.
- Test files: mirror the production package under `app/src/test/java/.../practice/fieldnotes/`.
- Avoid placing: new Room, repository, ViewModel, or UI helper files directly in the feature root package.
- Progress Log: keep planning/status notes in `practice/fieldnotes/documents/`, not beside Kotlin source files.

## Current Phase

- Phase: 10
- Goal: Clarify or add real tags only if requirement changes.
- In scope:
  - Document or implement user-defined tags only after the requirement is explicit.
- Out of scope:
  - Adding schema fields without a clarified product need.
  - Remote sync or complex tag normalization.
- Stop after:
  - Requirements are clarified or this phase is skipped.

## Progress Log

- Not started: tracker created from `TASK_RANKING.md`.
- Phase 1 done: added Room/KSP setup, entity, DAO, database, and domain/entity mapping.
- Validation update: Java is available at `/opt/homebrew/opt/openjdk@17`; tests need `JAVA_HOME` set in this shell.
- Validation passed: `JAVA_HOME=/opt/homebrew/opt/openjdk@17 PATH=/opt/homebrew/opt/openjdk@17/bin:$PATH ./gradlew test`.
- Phase 2 done: wired Room DAO through `RoomFieldNotesRepository`, `FieldNotesViewModel`, immutable `FieldNotesUiState`, and Compose collection.
- Phase 2 validation passed: `JAVA_HOME=/opt/homebrew/opt/openjdk@17 PATH=/opt/homebrew/opt/openjdk@17/bin:$PATH ./gradlew test`.
- Phase 2 review cleanup: switched Compose to `collectAsStateWithLifecycle` and removed the production in-memory repository so Room is the app repository implementation.
- Phase 2 DI cleanup: added Hilt, moved Room/repository/ViewModel construction into injected modules, and removed the manual ViewModel factory.
- Phase 2 DI validation passed: `JAVA_HOME=/opt/homebrew/opt/openjdk@17 PATH=/opt/homebrew/opt/openjdk@17/bin:$PATH ./gradlew test`.
- Phase 3 done: added pure last-updated sorting/status filter logic and applied it in ViewModel with default `All`.
- Phase 3 validation passed: `JAVA_HOME=/opt/homebrew/opt/openjdk@17 PATH=/opt/homebrew/opt/openjdk@17/bin:$PATH ./gradlew test`.
- Phase 4 adjustment: added visible status and sort controls so sorting/filtering can be manually verified in the app.
- Phase 4 done: added focused sorting/filtering tests.
- Phase 4 validation passed: `JAVA_HOME=/opt/homebrew/opt/openjdk@17 PATH=/opt/homebrew/opt/openjdk@17/bin:$PATH ./gradlew test`.
- One-time seed task: expanded starter data to 10 notes for LazyColumn/manual filter testing and updated tests to avoid depending on seed dataset size.
- One-time seed validation passed: `JAVA_HOME=/opt/homebrew/opt/openjdk@17 PATH=/opt/homebrew/opt/openjdk@17/bin:$PATH ./gradlew test`.
- Planning update: moved add, edit, and delete into the next P1 phases before visual chip polish/search stretch.
- Phase 5 done: added separate-screen add flow, draft state, DAO/repository save operation, and list return after save/cancel.
- Phase 5 validation passed: `JAVA_HOME=/opt/homebrew/opt/openjdk@17 PATH=/opt/homebrew/opt/openjdk@17/bin:$PATH ./gradlew test`.
- Organization cleanup: moved UI actions behind sealed `FieldNotesEvent` and a single ViewModel `onEvent` entry point.
- Phase 6 done: added row edit action, reused note form for edit, preserved existing note status, and saved updates through repository upsert.
- Phase 6 validation passed: `JAVA_HOME=/opt/homebrew/opt/openjdk@17 PATH=/opt/homebrew/opt/openjdk@17/bin:$PATH ./gradlew test`.
- Phase 7 done: added ID-based delete events, confirmation dialog, DAO/repository delete operation, and row delete action.
- Phase 7 validation passed: `JAVA_HOME=/opt/homebrew/opt/openjdk@17 PATH=/opt/homebrew/opt/openjdk@17/bin:$PATH ./gradlew test`.
- Phase 8 done: status, priority, and room chips now use explicit labels and distinct color treatment.
- Seed top-up update: starter data is inserted when DB count is below the current starter count; existing rows are not overwritten because seed insert still uses `IGNORE`.
- Phase 8 validation passed: `JAVA_HOME=/opt/homebrew/opt/openjdk@17 PATH=/opt/homebrew/opt/openjdk@17/bin:$PATH ./gradlew test`.
- Phase 9 done: added a list search field, immutable query state, sealed search event, and naive case-insensitive matching across title, description, and room.
- Phase 9 validation passed: `JAVA_HOME=/opt/homebrew/opt/openjdk@17 PATH=/opt/homebrew/opt/openjdk@17/bin:$PATH ./gradlew test`.

## Risks / Edge Cases

- Seed data must not duplicate after restart.
- Seed data should appear on a fresh persistent store.
- Null room/location must round-trip cleanly.
- Status, priority, and room chips should remain visually distinct as row actions grow.
- Current chips are derived from `status`, `priority`, and `room`; there is no separate user-defined `tags` field in the prompt/model.
- Last-updated sorting should be deterministic enough for tests.
- Room dependency/compiler setup may take more time than expected.

## Deferred / Follow-up

- Mark open/complete action.
- Search and filter UI if not reached as stretch.
- Real user-defined tags: add only if clarified as a requirement; likely needs either a simple serialized column or a separate tag table.
- Empty states and persistence error UI.
- Rotation-safe form state.

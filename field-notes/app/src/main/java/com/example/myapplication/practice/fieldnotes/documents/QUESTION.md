# Question

Build on the existing Offline Field Notes starter app so field technicians can create, manage, search, filter, and persist incident notes on a single device without relying on a backend.

## Given

- The app launches directly into `FieldNotesPracticeScreen`.
- Seeded notes currently come from an in-memory repository.
- Existing files include `MainActivity.kt`, `FieldNotesPracticeScreen.kt`, `FieldNote.kt`, and `FieldNotesRepositoryTest`.
- Notes have title, description, priority, status, optional room/location, and update timing.
- The experience should work offline and survive process death/app restarts.
- No real backend is required.

## Clarifying Questions

- None remaining.

## Assumptions

- Seeded notes are inserted only once when persistence starts empty.
- Search is case-insensitive, partial-match, and intentionally naive.
- Search checks title, description, and room/location.
- Notes sort by last updated first.
- Add and edit use a separate screen.
- Persistence uses Room over SQLite.

## Success Criteria

- Users can add notes with title, description, priority, and optional room/location.
- Users can edit, delete with confirmation, and mark notes open or complete.
- Search and status filters work together.
- Notes persist after closing and reopening the app.
- In-progress form input survives rotation.
- Empty notes, empty search results, and persistence errors show distinct UI states.
- At least one repository or use-case test covers sorting and filtering.
- The repository/data boundary remains clear enough to swap local storage later.
- The app still launches directly into the Field Notes exercise screen.

# Scenario 01: Offline Field Notes

## Prompt

We're going to build a small Android app for field technicians who inspect equipment in buildings with unreliable connectivity. The technician needs to create incident notes quickly, mark them complete, search previous notes, and trust that everything survives process death and app restarts.

Starter code is already included in this standalone project. It renders seeded notes from an in-memory repository and launches directly into the exercise screen. Your task is to iterate on that baseline, not start from a blank app.

Focus on a single-device offline experience. You do not need a real backend.

## Starter Code Available

- Project directory: `field-notes`
- App entry point: `MainActivity.kt`
- Screen: `practice/fieldnotes/FieldNotesPracticeScreen.kt`
- Models and seed repository: `practice/fieldnotes/FieldNote.kt`
- Existing starter test: `FieldNotesRepositoryTest`

## Required Features

- Preserve the existing seeded-note list while you refactor toward a real implementation.
- Add a note with title, description, priority, and optional room/location.
- Edit an existing note.
- Mark a note open or complete.
- Delete a note with a confirmation step.
- Search across title, description, and room/location.
- Filter by open, complete, or all.
- Persist all notes across app restarts.
- Show the right UI for empty search results, no notes yet, and persistence errors.

## Acceptance Criteria

- Notes remain after closing and reopening the app.
- Search and filters can be combined.
- The UI does not lose in-progress form fields on rotation.
- At least one repository or use-case test covers sorting and filtering.
- The code has a clear place to swap local storage for a remote sync source later.
- The standalone Field Notes app still launches directly into the exercise screen.

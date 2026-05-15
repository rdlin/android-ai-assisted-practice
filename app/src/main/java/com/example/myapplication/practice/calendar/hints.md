# Scenario 02 Hints: Calendar Day Planner

## Architecture Suggestions

- Treat `CalendarRules` as starter business logic, then expand it instead of burying time-window rules in the composable.
- Keep `CalendarRepository` as the boundary for replacing seed data with persistence.
- Introduce a ViewModel with immutable UI state for selected date, events, form fields, conflicts, and save status.
- Keep time calculations in plain Kotlin so they can be unit tested without Android dependencies.
- Consider representing day slots in minutes since midnight internally, even if the UI displays `HH:mm`.

## AI Practice Goals

- Ask AI to inspect the starter `TimeRange` overlap behavior and identify edge cases.
- Ask AI to generate tests for back-to-back events, contained overlaps, enclosing overlaps, and invalid ranges.
- Ask AI to propose a minimal event editor state model.
- Ask AI to critique whether the available-slot finder belongs in a repository, use case, or ViewModel.

## Stretch Goals

- Add a visual hour-by-hour timeline.
- Add recurring event preview without full recurrence support.
- Add event color categories.
- Add a "find focus time" action for 30, 60, and 90 minute blocks.

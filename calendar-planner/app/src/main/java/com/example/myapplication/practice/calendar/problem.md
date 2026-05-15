# Scenario 02: Calendar Day Planner

## Prompt

We're going to build a calendar day planner for a mobile productivity app. Users need to review a day schedule, create or edit events, and avoid accidental overlaps while planning focused work around meetings.

Starter code is already included in this standalone project. It renders a seeded day schedule, includes a small time-range model, and launches directly into the exercise screen. Your task is to extend the baseline into a real scheduling feature.

## Starter Code Available

- Project directory: `calendar-planner`
- App entry point: `MainActivity.kt`
- Screen: `practice/calendar/CalendarPlannerPracticeScreen.kt`
- Models, seed repository, and time rules: `practice/calendar/CalendarEvent.kt`
- Existing starter test: `CalendarRulesTest`

## Required Features

- Preserve the existing seeded day timeline while adding create and edit flows.
- Each event has title, optional location, attendees, start time, and end time.
- Validate that end time is after start time.
- Detect overlapping events before saving and show a clear warning.
- Add an available-slot finder for a requested duration.
- Persist events across app restarts.
- Preserve in-progress form state across rotation.

## Acceptance Criteria

- Events render sorted by start time.
- A candidate event from 10:00 to 10:45 conflicts with the seeded 09:30 to 10:15 event.
- Back-to-back events are allowed.
- Invalid time ranges are rejected before save.
- Available-slot tests cover at least morning, midday, and no-slot cases.
- The standalone Calendar Planner app still launches directly into the exercise screen.

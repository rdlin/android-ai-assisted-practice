# Android AI-Assisted Practice

This repo contains three independent Android starter projects for one-hour AI-assisted coding practice. Each top-level project directory is meant to feel like a downloadable interview zip: a running Compose app, seed data, partial feature code, a prompt file, and a few tests.

## Projects

- `field-notes`: offline CRUD, search/filter behavior, local persistence boundaries
- `calendar-planner`: calendar time ranges, overlap detection, scheduling validation
- `receipt-review`: parsed receipt review, editable form state, validation, upload retry states

Each project has its own:

- `settings.gradle.kts`
- Gradle wrapper
- `app` module
- app name
- Android `applicationId`
- README

To import a project in Android Studio, choose **Open** and select one of the project directories directly, for example `field-notes`, not the repo root.

Each project keeps its prompt next to the app code:

- `problem.md`: interview-facing prompt, starter code map, required features, acceptance criteria

Hints are intentionally outside the Android projects so they are not imported with the starter apps:

- `hints/field-notes-hints.md`
- `hints/calendar-planner-hints.md`
- `hints/receipt-review-hints.md`

## Recommended Workflow

1. Open one project directory in Android Studio.
2. Read that feature's `problem.md`.
3. Spend 5 minutes identifying existing starter code and what should be preserved.
4. Use AI to propose a small implementation plan and file map.
5. Implement one vertical slice first: UI state, one interaction, and one test.
6. Add persistence and edge cases after the happy path works.
7. Open the matching hint file only if you get blocked or after your timed attempt.
8. Reserve the final 10 minutes for tests, cleanup, and a short tradeoff explanation.

## Running Tests

From the repo root, run each project separately:

```bash
cd field-notes && ./gradlew test
cd ../calendar-planner && ./gradlew test
cd ../receipt-review && ./gradlew test
```

Or open a single project directory and run:

```bash
./gradlew test
```

If running from a fresh clone outside Android Studio, create a local `local.properties` in the project you are testing with your Android SDK path, for example:

```properties
sdk.dir=/Users/you/Library/Android/sdk
```

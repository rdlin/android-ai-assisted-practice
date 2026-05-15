# Android AI-Assisted Practice

This repo contains three standalone Android starter projects for one-hour AI-assisted coding practice. Each directory is meant to feel like a downloadable interview zip: a running Compose app, seed data, partial feature code, prompt files, hint files, and a few tests.

## Projects

- `field-notes`: offline CRUD, search/filter behavior, local persistence boundaries
- `calendar-planner`: calendar time ranges, overlap detection, scheduling validation
- `receipt-review`: parsed receipt review, editable form state, validation, upload retry states

Each project keeps its prompt next to the app code:

- `problem.md`: interview-facing prompt, starter code map, required features, acceptance criteria
- `hints.md`: architecture suggestions, AI practice goals, stretch goals

## Recommended Workflow

1. Open one project directory in Android Studio.
2. Read that feature's `problem.md`.
3. Spend 5 minutes identifying existing starter code and what should be preserved.
4. Use AI to propose a small implementation plan and file map.
5. Implement one vertical slice first: UI state, one interaction, and one test.
6. Add persistence and edge cases after the happy path works.
7. Reserve the final 10 minutes for tests, cleanup, and a short tradeoff explanation.

## Running Tests

From the repo root:

```bash
cd field-notes && ./gradlew test
cd ../calendar-planner && ./gradlew test
cd ../receipt-review && ./gradlew test
```

Or run a single project directly from its own directory:

```bash
./gradlew test
```

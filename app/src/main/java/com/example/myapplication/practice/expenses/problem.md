# Scenario 03: Receipt Review

## Prompt

We're going to build an expense receipt review flow for a mobile finance app. A user captures a receipt, the app extracts likely expense fields, and the user reviews, fixes, and submits the expense. The core challenge is managing an editable form, validation, draft persistence, and upload retry states.

Starter code is already included in the project. It parses a seeded receipt into a draft, validates required fields, and has a route wired through the practice launcher. Your task is to turn that baseline into a robust review workflow.

## Starter Code Available

- Route: `PracticeRoute.ReceiptReview`
- Feature gate: `PracticeFeatureGate`
- Screen: `practice/expenses/ReceiptReviewPracticeScreen.kt`
- Models, fake parser, validation helper, and seed receipt: `practice/expenses/ReceiptExpense.kt`
- Existing starter test: `ExpenseValidatorTest`

## Required Features

- Preserve the fake parser but make parsed fields editable.
- Validate merchant, total, date, and category before submission.
- Model draft, ready, uploading, failed, and submitted states.
- Save local drafts so edits survive app restart.
- Add an idempotent fake upload flow that can fail and retry.
- Show clear UI for validation errors and upload failure.
- Keep the route integrated with the practice launcher.

## Acceptance Criteria

- A valid parsed receipt starts in a ready-to-submit state.
- Clearing merchant or setting total to zero blocks submission with a visible error.
- Failed upload keeps the edited draft intact.
- Retry after failure does not create duplicate submissions.
- Validation and upload state transitions are covered by unit tests.
- Existing navigation from the practice launcher still works.

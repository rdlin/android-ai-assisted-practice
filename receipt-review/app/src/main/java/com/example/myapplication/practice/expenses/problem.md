# Scenario 03: Receipt Review

## Prompt

We're going to build an expense receipt review flow for a mobile finance app. A user captures a receipt, the app extracts likely expense fields, and the user reviews, fixes, and submits the expense. The core challenge is managing an editable form, validation, draft persistence, and upload retry states.

Starter code is already included in this standalone project. It parses a seeded receipt into a draft, validates required fields, and launches directly into the exercise screen. Your task is to turn that baseline into a robust review workflow.

## Starter Code Available

- Project directory: `receipt-review`
- App entry point: `MainActivity.kt`
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
- Keep the standalone app entry point wired to the review screen.

## Acceptance Criteria

- A valid parsed receipt starts in a ready-to-submit state.
- Clearing merchant or setting total to zero blocks submission with a visible error.
- Failed upload keeps the edited draft intact.
- Retry after failure does not create duplicate submissions.
- Validation and upload state transitions are covered by unit tests.
- The standalone Receipt Review app still launches directly into the exercise screen.

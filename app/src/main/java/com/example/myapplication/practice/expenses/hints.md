# Scenario 03 Hints: Receipt Review

## Architecture Suggestions

- Treat `FakeReceiptParser` as an OCR/API boundary and keep it replaceable.
- Move form state and submission state out of the composable and into a ViewModel.
- Model submit behavior as a small state machine rather than a collection of booleans.
- Use a repository boundary for draft persistence and upload attempts.
- Use idempotency keys for fake upload retries so retry behavior is testable.

## AI Practice Goals

- Ask AI to design the submit state machine before editing UI.
- Ask AI to generate validation tests before implementing form interactions.
- Ask AI to identify which values should be parsed data, editable draft state, and server submission metadata.
- Ask AI to review whether failure handling preserves user edits.

## Stretch Goals

- Add a category picker.
- Add a receipt image placeholder panel.
- Add optimistic submitted state with later reconciliation.
- Add multiple validation severities: blocking errors and non-blocking warnings.

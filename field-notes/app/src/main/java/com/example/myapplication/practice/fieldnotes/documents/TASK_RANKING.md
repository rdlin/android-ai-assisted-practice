# Task Ranking

| Task | Priority | Depends On | Value | Effort | Risk | Fit 50m? | 50m Scope | Justification |
| --- | --- | --- | --- | --- | --- | --- | --- | --- |
| Room persistence with one-time seed data | P0 | none | high | M | med | yes | Add entity/DAO/database, seed only when empty, render persisted notes sorted last updated first | Core offline promise and foundation for all write flows |
| Repository boundary and sorting/filtering logic | P0 | Room persistence | high | S | low | yes | Keep repository as UI-facing boundary; add pure logic for last-updated sort and status filter | Improves correctness and keeps storage swappable |
| Repository/use-case test | P0 | Repository boundary | high | S | low | yes | Test last-updated sort plus open/complete/all filtering | Directly satisfies acceptance criteria and is fast to verify |
| Search and status filters | P1 | Repository boundary | high | S | low | maybe | Add naive case-insensitive query over title, description, room plus all/open/complete filter | High user value, but depends on stable list state |
| Mark note open or complete | P1 | Room persistence | med | S | low | maybe | Toggle status from a row action and update timestamp | Useful technician workflow with small surface area |
| Empty states | P1 | Search and status filters | med | S | low | maybe | Distinguish no notes from no matching search/filter results | Important correctness polish after filtering exists |
| Add note screen | P2 | Room persistence | high | M | med | maybe | Separate screen with title, description, priority, optional room; save to Room | High value but navigation/form scope can crowd out persistence |
| Edit note screen | P2 | Add note screen | high | M | med | no | Reuse form for existing note and update timestamp on save | Depends on add flow and needs careful state handling |
| Delete with confirmation | P2 | Room persistence | med | S | low | maybe | Confirm before deleting one persisted note | Valuable but less foundational than persistence/search |
| Rotation-safe in-progress form | P2 | Add note screen | high | M | med | no | Preserve add/edit draft through rotation | Important, but only after form flow exists |
| Persistence error UI | P2 | Room persistence | med | M | med | no | Surface a simple error state from repository/ViewModel | Hard to exercise well in a short slot |

## Recommended path:

- First: Room persistence with one-time seed data, repository boundary, and a focused sorting/filtering test.
- Stretch: naive search plus status filters, then mark open/complete if time remains.
- Defer: separate add/edit screens, rotation-safe form state, delete confirmation, and persistence error UI.

## Avoid in this session:

- Full CRUD plus persistence in one pass: too much surface area for 50 minutes.
- Polished multi-screen navigation before persistence: add/edit belongs on a separate screen, but storage correctness comes first.
- Complex search or SQL FTS: naive partial matching is enough for the stated requirement.
- Remote sync abstractions: the prompt only asks for a clear local boundary.
- Broad UI redesign: preserve the starter shape and spend time on observable behavior.

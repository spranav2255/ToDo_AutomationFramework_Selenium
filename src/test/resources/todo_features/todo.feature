Feature: ToDo Management

Scenario: Edit an existing todo item
  Given User is logged in and on Dashboard
  And User adds a new todo with title "Buy groceries"
  When User edits todo "Buy groceries" to "Buy vegetables"
  Then Todo with title "Buy vegetables" should be visible

Scenario: Delete a todo item
  Given User is logged in and on Dashboard
  And User adds a new todo with title "Buy vegetables"
  When User deletes todo with title "Buy vegetables"
  Then Todo with title "Buy vegetables" should not be visible

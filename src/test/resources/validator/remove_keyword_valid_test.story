Narrative:
In order to have accurate search results about my business idea
As a user
I want to change keywords on the keyword list

Scenario: User removes a keyword from the keyword list
Given A keywords list
When User selects a word <word> to remove from the list
Then Keyword list no longer have it

Examples:
|word|
|Dog|
|Cat|

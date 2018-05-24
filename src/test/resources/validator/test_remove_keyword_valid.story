Narrative:
In order to have accurate search results about my business idea
As a user
I want to change keywords on the keyword list

Scenario: User removes a keyword from the keyword list
Given Keywords list
And User wants to remove a word from the list
When User selects a word <word>
And User removes it
Then Keyword list no longer have it

Examples:
|word|
|Dog|
|Cat|

Narrative:
In order to have accurate search results about my business idea
As a user
I want to change keywords on the keyword list

Scenario: User removes a keyword from an empty keywords list
Given An empty keywords list
And User wants to remove a word from the list
When User selects a word <word>
And User removes it
Then Error message <message>

Examples:
|word|message|
|Dog|The keyword list is empty|
|Cat|The keyword list is empty|

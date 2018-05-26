Narrative:
In order to have accurate search results about my business idea
As a user
I want to change keywords on the keyword list

Scenario: User removes a keyword from an empty keywords list
Given An empty keywords list
When User selects a word <word> to remove from the list
Then User gets an error message <message>

Examples:
|word|message|
|Dog|The keyword list is empty|
|Cat|The keyword list is empty|

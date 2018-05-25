Narrative:
In order to have accurate search results about my business idea
As a user
I want to change keywords on the keyword list

Scenario: User removes a keyword that does not exist
Given A keywords list
When User selects a word <word> to remove from the list
Then User gets an error message <message>

Examples:
|word|message|
|Rabbit|This keyword does not exist in the list|
|Horse|This keyword does not exist in the list|

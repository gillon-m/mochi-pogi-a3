Narrative:
In order to have accurate search results about my business idea
As a user
I want to change the order of the keywords

Scenario: User changes the weight of a keyword to the same value as other keyword's value
Given User has keywords
And User wants to change the weight
And User selects one keyword to change its weight
And The new weight is <weight>
When User sets the new weight
Then User gets an error message <message>

Examples:
|weight|message|
|-1|The minimum weight for a keyword is 1|
|11|The maximum weight for a keyword is 10|
|9|Each keyword must have a different weight|

Narrative:
In order to have accurate search results about my business idea
As a user
I want to change keywords on the keyword list
					 
Scenario:  User injects a new keyword into the keyword list
Given Keywords list
And User wants to inject a word to the list
And The word is <word> with a weight of <weight>
When User injects it
Then The word is added to the list

Examples:
|word|weight|
|Rabbit|3|
|Horse|4|

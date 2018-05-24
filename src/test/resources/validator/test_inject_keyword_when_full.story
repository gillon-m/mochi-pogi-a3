Narrative:
In order to have accurate search results about my business idea
As a user
I want to change keywords on the keyword list
					 
Scenario:  User injects a new keyword into the keyword list
Given A full keywords list
And User wants to inject a word to the list
And The word is <word> with a weight of <weight>
When User injects it
Then Error message <message>

Examples:
|word|weight|message|
|Sheep|5|You can only have up to 5 keywords|
|Dog|1|You can only have up to 5 keywords|

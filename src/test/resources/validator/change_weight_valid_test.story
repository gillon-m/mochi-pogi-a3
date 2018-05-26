Narrative:
In order to have accurate search results about my business idea
As a user
I want to change the order of the keywords
					 
Scenario:  User changes the weight of a keyword
Given User has keywords
And User wants to change the weight
And User selects one keyword to change its weight
And The new weight is <weight>
When User sets the new weight
Then The weight of the keyword changes

Examples:
|weight|
|5|
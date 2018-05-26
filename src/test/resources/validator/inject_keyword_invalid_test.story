Narrative:
In order to have accurate search results about my business idea
As a user
I want to change keywords on the keyword list
					 
Scenario:  User injects an existing keyword
Given A keywords list
When User injects a word <word> with a weight of <weight>
Then User gets an error message <message>

Examples:
|word|weight|message|
|Rabbit|9|Each keyword must have a different weight|
|Rabbit|-1|The minimum weight for a keyword is 1|
|Rabbit|11|The maximum weight for a keyword is 10|
|Dog|1|This keyword is already in the list|
|Cat|1|This keyword is already in the list|
|$$$Shop|1|Keyword cannot contain numbers or symbols|
|$2Shop|1|Keyword cannot contain numbers or symbols|
|123Shop|1|Keyword cannot contain numbers or symbols|
|Dog|9|This keyword is already in the list|
|Dog|-1|This keyword is already in the list|
|Dog|11|This keyword is already in the list|
|$$$Shop|9|Keyword cannot contain numbers or symbols|
|$$$Shop|-1|Keyword cannot contain numbers or symbols|
|$$$Shop|11|Keyword cannot contain numbers or symbols|

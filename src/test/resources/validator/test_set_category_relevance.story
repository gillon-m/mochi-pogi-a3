Narrative:
In order have related business categories
As a user
I want to change the relevance of categories given to me
					 
Scenario:  User changes the relevance of different categories
Given that the user is given a cluster of categories: <c1>, <c2>
When the user sets the relevance of <c1> to <c1relevance>
Then the relevance of <c1> changes to <c1setRelevance>
And the relevance of <c2> remains as the <defaultRelevance>

Examples:
|c1|c2|c1relevance|c1setRelevance|defaultRelevance|
|Dog|Cat|0.2|0.2|0.5|
|Dog|Cat|-0.23|0.0|0.5|
|Dog|Cat|1.2|1.0|0.5|
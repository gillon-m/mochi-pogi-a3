Narrative:
In order have related business categories
As a user
I want to change the relevance of categories given to me
					 
Scenario:  User changes the relevance of different categories
Given that the user is given a cluster of categories: <c1>
When the user sets the relevance of <category> to <relevance>
Then the relevance of <category> changes to <setRelevance>

Examples:
|c1|category|relevance|setRelevance|
|Dog|Dog|0.2|0.2|
|Dog|Dog|-0.23|0.0|
|Dog|Dog|1.2|1.0|
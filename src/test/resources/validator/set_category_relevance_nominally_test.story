Narrative:
In order to prioritize related business categories
As a user
I want to change the relevance of categories given to me
					 
Scenario: User changes the nominal relevance of different categories
Given that the user is given a cluster with the category: <category>
When the user sets the relevance of category to <nominalRelevance>
Then the relevance of the category changes to <relevance>

Examples:
|category|nominalRelevance|relevance|
Dog|NOT_RELEVANT|0.0|
Cat|WEAK_RELEVANT|0.2|
Walking|RELEVANT|0.5|
Yoga|VERY_RELEVANT|0.8|
Social|THE_SAME|1.0|
Because I’m not supposed to spend too much time on this task,
I have kept the solution simple, not written too much, but, yet the design has strong foundation.
This is a summary of what I have assumed from the exercise and what has been implemented:
1. I have taken the channels : Kids and News to not have any rewards associated with them
( I took the word n/a, in the exercise description, in the rewards service table for these two
channels to mean no reward).
2. The architecture that I have used is MVP, so that it is easier to test.
3. I have used interfaces ( e.g. for the activity , we present instead, to the presenter, an activityView),
since, this is more fully testable, easily extensible, complies with best object-oriented practices ( SOLID ).
Explanation: If we pass an instance of the activity through to the presenter, then the presenter then  knows
about the activity, then if the activity is complicated, and if we write a test for the presenter,
then we have to write a mock version of the activity, which is difficult to do, so, we give the presenter an interface,
then the presenter doesn’t know who implements the interface, and doesn’t care , and the test doesn’t care either,
but the test can control and examine the view, to check if certain things have passed/failed.
4. This architecture facilitates TDD. Why because in TDD we follow 3 steps,
1) Write a failing test,
2) write code to pass that test,
3) refactor. Since, we use interfaces, which is our contract, the interfaces don’t change.
The contract , of how to use the code doesn’t change. Hence, it won’t break the tests,
when we refactor the code. The internals of the code is hidden from the tests.
5. I have used the principles of singularity of responsibility and  integration segregation ( SOLID )
and so kept the services separate.
6. We inject dependencies into the Presenter. Dependency Injection is a form of Dependency Inversion ( SOLID ).
7. The presenter could have used a map, to mock, the service returning the my channel subscriptions
to a list of rewards, but, this wouldn’t be correct. The rewards list is returned from the reward service.
8. We can now fully test this , as unit tests, on the JVM, without the need to use a device/emulator.
We can test both the reward service, to check our reward list, if they contain rewards, or if they don’t contain rewards, as well as, the Eligibility Service, to check the reward service returns the correct data, given the account number.  We mock, the my channel subscriptions list as well as the rewards list, returned from the reward service. We mock, all the different values and exceptions returned from the Eligibility Service.
9. Using Mockito for mock testing.
10. The tests covered are:
Scenario 1, customer eligible, my channel subscription is rewardable, hence return rewards list;
Scenario 2, customer not eligible, my channel subscription is rewardable, hence do not return rewards list;
Scenario 3, customer eligible, my channel subscription is not-rewardable, hence do not return rewards list;
Scenario 4, Technical failure exception , hence do not return rewards list;
Scenario 5, Invalid account number exception, hence do not return rewards list; and notify the client that
the customer no. is invalid.
11. In the test script, we have taken common code and placed it in the junit setup function.
12. We can use the code coverage to analyse what has been covered, and it shows both
the Presenter and Services as 100%.

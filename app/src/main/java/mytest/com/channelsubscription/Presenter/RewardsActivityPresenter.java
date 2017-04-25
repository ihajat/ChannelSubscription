package mytest.com.channelsubscription.Presenter;

import java.util.Collections;
import java.util.List;

import mytest.com.channelsubscription.View.RewardsActivityView;
import mytest.com.channelsubscription.services.EligibilityService;
import mytest.com.channelsubscription.services.InvalidAccountNumberException;
import mytest.com.channelsubscription.services.RewardsService;
import mytest.com.channelsubscription.services.TechnicalFailureException;

/**
 * Created by iqbalhajat on 22/04/2017.
 */

public class RewardsActivityPresenter {
    private RewardsActivityView view;
    private RewardsService rewardsService;
    private EligibilityService eligibilityService;
    private static final boolean invalid_account_number = false;
    private static final boolean valid_account_number = true;

    /*
    Constructor with view interfaces and services injected
     */
    public RewardsActivityPresenter(RewardsActivityView view, RewardsService rewardsService, EligibilityService eligibilityService) {
        this.view = view;
        this.rewardsService = rewardsService;
        this.eligibilityService = eligibilityService;
    }

    /*
    function: returnRewards, calls both services (eligibilityService & rewardsService)
    and calls the view interface functions (displayRewards & displayNoRewards)
     */
    public void returnRewards(String account_no, List<String> channel_subscriptions){

        /*
        return values for the eligibility Service
        1: true = valid account no.
        2: false = invalid account no.
        3: TechnicalFailureException
        4: InvalidAccountNumberException

        convert to int values for convenience of using switch
         */

        int eligibility = 0;
        try {
            eligibility = eligibilityService.getEligibility(account_no)? 1 : 2;
        } catch (TechnicalFailureException e) {
            eligibility = 3;
        } catch (InvalidAccountNumberException e) {
            eligibility = 4;
        }

        switch (eligibility) {
            case 1:
                List<String> rewardList = rewardsService.getRewards(account_no,channel_subscriptions);
                if(rewardList.isEmpty()){
                    view.displayNoRewards(rewardList,valid_account_number);
                } else {
                    view.displayRewards(rewardList);
                }
                break;
            case 2: case 3:
                view.displayNoRewards(Collections.EMPTY_LIST,valid_account_number);
                break;
            case 4:
                view.displayNoRewards(Collections.EMPTY_LIST,invalid_account_number);
                break;
        }
    }
}

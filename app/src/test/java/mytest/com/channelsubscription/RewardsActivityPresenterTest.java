package mytest.com.channelsubscription;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import mytest.com.channelsubscription.Presenter.RewardsActivityPresenter;
import mytest.com.channelsubscription.View.RewardsActivityView;
import mytest.com.channelsubscription.services.EligibilityService;
import mytest.com.channelsubscription.services.InvalidAccountNumberException;
import mytest.com.channelsubscription.services.RewardsService;
import mytest.com.channelsubscription.services.TechnicalFailureException;

/**
 * Created by iqbalhajat on 22/04/2017.
 *
 *
 */

@RunWith(MockitoJUnitRunner.class)
public class RewardsActivityPresenterTest {

    @Mock
    RewardsActivityView view;
    @Mock
    RewardsService rewardsService;
    @Mock
    EligibilityService eligibilityService;

    private RewardsActivityPresenter rewardsActivityPresenter;
    private String account_no = "account_no";
    private List<String> MANY_REWARDABLE_CHANNELS = Arrays.asList(new String("SPORTS"),new String("MUSIC"),new String("MOVIES"));
    private List<String> MANY_REWARDS = Arrays.asList(new String("CHAMPIONS_LEAGUE_FINAL_TICKET"), new String("KARAOKE_PRO_MICROPHONE"), new String("PIRATES_OF_THE_CARIBBEAN_COLLECTION"));
    private List<String> NO_REWARDABLE_CHANNELS = Arrays.asList(new String("KIDS"),new String("NEWS"));
    private List<String> NO_REWARDS =  Collections.EMPTY_LIST;
    private static final boolean invalid_account_number = false;
    private static final boolean valid_account_number = true;

    @Before
    public void setUp() throws Exception {
        rewardsActivityPresenter = new RewardsActivityPresenter(view,rewardsService,eligibilityService);
    }

    @After
    public void tearDown() throws Exception {

    }

    //Scenario 1, customer eligible, my channel subscription is rewarded, hence return rewards list
    @Test
    public void returnRewardsForMyChannelSubscriptionListWithCustomerEligible() throws TechnicalFailureException, InvalidAccountNumberException {

        Mockito.when(eligibilityService.getEligibility(account_no)).thenReturn(valid_account_number);
        Mockito.when(rewardsService.getRewards(account_no, MANY_REWARDABLE_CHANNELS)).thenReturn(MANY_REWARDS);

        rewardsActivityPresenter.returnRewards(account_no, MANY_REWARDABLE_CHANNELS);

        Mockito.verify(view).displayRewards(MANY_REWARDS);
    }
    //Scenario 2, customer not eligible, my channel subscription is rewarded, hence do not return rewards list
    @Test
    public void returnNoRewardsForMyChannelSubscriptionListWithCustomerNotEligible() throws TechnicalFailureException, InvalidAccountNumberException {

        Mockito.when(eligibilityService.getEligibility(account_no)).thenReturn(invalid_account_number);

        rewardsActivityPresenter.returnRewards(account_no, MANY_REWARDABLE_CHANNELS);

        Mockito.verify(view).displayNoRewards(NO_REWARDS,valid_account_number);
    }
    //Scenario 3, customer eligible, my channel subscription is not-rewarded, hence do not return rewards list
    @Test
    public void returnNoRewardsForMyNoRewardChannelSubscriptionListWithCustomerEligible() throws TechnicalFailureException, InvalidAccountNumberException {

        Mockito.when(eligibilityService.getEligibility(account_no)).thenReturn(valid_account_number);

        rewardsActivityPresenter.returnRewards(account_no, NO_REWARDABLE_CHANNELS);

        Mockito.verify(view).displayNoRewards(NO_REWARDS,valid_account_number);
    }
    //Scenario 4, Technical failure exception , hence do not return rewards list
    @Test
    public void returnNoRewardsForTechnicalFailureException() throws TechnicalFailureException, InvalidAccountNumberException {

        Mockito.when(eligibilityService.getEligibility(account_no)).thenThrow(new TechnicalFailureException("Technical Failure Exception"));

        rewardsActivityPresenter.returnRewards(account_no, NO_REWARDABLE_CHANNELS);

        Mockito.verify(view).displayNoRewards(NO_REWARDS,valid_account_number);
    }
    //Scenario 5, Invalid account number exception, hence do not return rewards list
    @Test
    public void returnNoRewardsForInvalidAccountNumberException() throws TechnicalFailureException, InvalidAccountNumberException {

        Mockito.when(eligibilityService.getEligibility(account_no)).thenThrow(new InvalidAccountNumberException("Invalid Account Number Exception"));

        rewardsActivityPresenter.returnRewards(account_no, NO_REWARDABLE_CHANNELS);

        Mockito.verify(view).displayNoRewards(NO_REWARDS,invalid_account_number);
    }
}
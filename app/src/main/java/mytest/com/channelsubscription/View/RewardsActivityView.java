package mytest.com.channelsubscription.View;

import java.util.List;

/**
 * Created by iqbalhajat on 22/04/2017.
 */

public interface RewardsActivityView {

    void displayRewards(List<String> mychannelsubscriptionlist);
    void displayNoRewards(List<String> mychannelsubscriptionlist,boolean account_no_valid);

}

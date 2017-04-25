package mytest.com.channelsubscription.services;

import java.util.List;

/**
 * Created by iqbalhajat on 22/04/2017.
 */

public interface RewardsService {
    List<String> getRewards(String account_no, List<String> channel_subscriptions);
}

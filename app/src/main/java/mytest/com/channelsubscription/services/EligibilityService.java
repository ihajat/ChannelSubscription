package mytest.com.channelsubscription.services;

/**
 * Created by iqbalhajat on 22/04/2017.
 */

public interface EligibilityService {
    boolean getEligibility(String account_no) throws TechnicalFailureException,InvalidAccountNumberException ;
}

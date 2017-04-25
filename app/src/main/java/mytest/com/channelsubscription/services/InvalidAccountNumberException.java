package mytest.com.channelsubscription.services;

/**
 * Created by iqbalhajat on 23/04/2017.
 */

public class InvalidAccountNumberException extends Exception{
    public InvalidAccountNumberException(String s){
        super(s);
    }
}

package mytest.com.channelsubscription.View;

import android.app.Activity;
import android.os.Bundle;

import java.util.List;

import mytest.com.channelsubscription.Presenter.RewardsActivityPresenter;
import mytest.com.channelsubscription.R;

public class RewardsActivity extends Activity implements RewardsActivityView {

    private RewardsActivityPresenter rewardsActivityPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rewards);

        rewardsActivityPresenter = new RewardsActivityPresenter(this,null,null);

    }

    @Override
    public void displayRewards(List<String> mychannelsubscriptionlist) {

    }

    @Override
    public void displayNoRewards(List<String> mychannelsubscriptionlist,boolean account_no_valid) {

    }
}

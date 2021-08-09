package com.example.greetmeet;

import android.content.Intent;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.twitter.sdk.android.core.*;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

public class MainActivity extends AppCompatActivity {
    TwitterLoginButton logIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TwitterAuthConfig authConfig =  new TwitterAuthConfig(
                getString(R.string.com_twitter_sdk_android_CONSUMER_KEY),
                getString(R.string.com_twitter_sdk_android_CONSUMER_SECRET));

        TwitterConfig twitterConfig = new TwitterConfig.Builder(this)
                .twitterAuthConfig(authConfig)
                .build();

        Twitter.initialize(twitterConfig);
        Twitter.initialize(this);
        setContentView(R.layout.activity_main);
        logIn=(TwitterLoginButton) findViewById(R.id.login_button);
        logIn.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                TwitterSession session = TwitterCore.getInstance().getSessionManager().getActiveSession();
                TwitterAuthToken authToken=session.getAuthToken();
                String token = authToken.token;
                String secret= authToken.secret;
                lg(session);


            }

            @Override
            public void failure(TwitterException exception) {
                Toast.makeText(MainActivity.this,"authentication fail",Toast.LENGTH_SHORT).show();

            }
        });
    }
    //Method login assign Username
    public void lg (TwitterSession session){
        String userName= session.getUserName();
        Intent intent=new Intent(MainActivity.this,HomePage.class);
        intent.putExtra("username",userName);
        startActivity(intent);

    }
    //pass the activity result to login button
    protected void onActivityResult(int requestcode, int resultcode, Intent data){
     super.onActivityResult(requestcode, resultcode, data);
     logIn.onActivityResult(requestcode, resultcode, data);

    }
}
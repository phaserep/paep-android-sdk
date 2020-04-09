package test.paep.project;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import kr.co.paep.paepsdk.PaepAdView;

public class MainActivity extends AppCompatActivity {

    private PaepAdView adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adView = findViewById(R.id.adView);
        adView.setClientId("PAD-cbkceefyahdg"); //할당받은 광고단위(clientID) 작성
        adView.setAdHandler(new Handler() {
            public void handleMessage(Message message) {
                try {
                    switch (message.what) {
                        case PaepAdView.AD_SUCCEED:
                            Log.d("paepsdk", "Receive Ad " + (String) message.obj);
                            break;
                        case PaepAdView.AD_ERROR:
                            Log.d("paepsdk", "Receive Ad Failed " + (String) message.obj);
                            break;
                        case PaepAdView.AD_CLICK:
                            Log.d("paepsdk", "Ad Clicked " + (String) message.obj);
                            break;
                        case PaepAdView.AD_FAILED:
                            Log.d("paepsdk", "BANNER Failed " + (String) message.obj);
                            break;
                    }
                } catch (Exception e) {}
            }
        });

        adView.setAdQuery();
    }
}

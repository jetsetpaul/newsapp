package samcorp.newsapp;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by pauljoiner on 7/19/16.
 */
public class PlaceholderAuthenticatorService extends Service {
    private PlaceholderAuthenticator mAuthenticator;

    @Override
    public void onCreate() {
        mAuthenticator = new PlaceholderAuthenticator(this);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mAuthenticator.getIBinder();
    }
}

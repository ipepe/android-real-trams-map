package pl.ipepe.android.real_trams_map;

import android.webkit.GeolocationPermissions;
import android.webkit.WebChromeClient;

/**
 * Created by patrykptasinski on 24/06/16.
 */
public class MyWebChromeClient extends WebChromeClient {
    public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
        callback.invoke(origin, true, false);
    }
}

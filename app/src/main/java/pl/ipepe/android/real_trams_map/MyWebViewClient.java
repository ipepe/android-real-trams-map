package pl.ipepe.android.real_trams_map;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.IOException;

/**
 * Created by patrykptasinski on 24/06/16.
 */
public class MyWebViewClient extends WebViewClient {
    Context context;

    public MyWebViewClient(Context context){
        super();
        this.context = context;
    }

    @Override
    public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
        WebResourceResponse response = null;
        if(url != null && url.startsWith("https://trams.ipepe.pl/tiles/")) {
            String assetPath = url.replace("https://trams.ipepe.pl/", "www/");
            try {
                response = new WebResourceResponse("image/png", "UTF8", context.getAssets().open(assetPath));
            } catch (IOException e) {
                Log.e("intercept", url);
                e.printStackTrace(); // Failed to load asset file
                response = super.shouldInterceptRequest(view, url);
            }
        }
        return response;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView webView, String url){
        if (url.equals(MainActivity.ABOUT_URL) || url.contains(MainActivity.CONTACT_URL)
                || url.equals(MainActivity.MAP_URL)) {
            return super.shouldOverrideUrlLoading(webView, url);
        } else {
            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            context.startActivity(i);
            return true;
        }
    }

}

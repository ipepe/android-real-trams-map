package pl.ipepe.android.real_trams_map;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;

import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity {
    WebView webView;
    int lastChosenMenu;

    public static final String MAP_URL = "https://trams.ipepe.pl/api/v1/trams";
    public static final String ABOUT_URL = "https://trams.ipepe.pl/api/v1/trams/about";
    public static final String CONTACT_URL = "https://ipepe.pl/contact.html?app=trams.android.ipepe.pl";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        webView = new WebView(this);
        setContentView(webView);

        webView.setWebViewClient(new MyWebViewClient(this));
        webView.setWebChromeClient(new MyWebChromeClient());
        WebSettings webSettings = webView.getSettings();
//        webSettings.setDomStorageEnabled(true);
        webSettings.setJavaScriptEnabled(true);
//        webView.loadUrl("file:///android_asset/www/index.html");
        webView.loadUrl(MAP_URL);
        lastChosenMenu = R.id.map;

    }

    @Override
    public void onPause(){
        super.onPause();
        webView.loadUrl("about:blank");
    }

    @Override
    public void onResume(){
        super.onResume();
        try {
            if (lastChosenMenu == R.id.map)
                webView.loadUrl(MAP_URL);
        }catch(NullPointerException ex){
            ex.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId()) {
            case R.id.map:
                webView.loadUrl(MAP_URL);
                return true;
            case R.id.contact:
                webView.loadUrl(CONTACT_URL);
                return true;
            case R.id.about:
                webView.loadUrl(ABOUT_URL);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

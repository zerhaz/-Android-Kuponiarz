package pl.zerhaz.kuponiarz;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;


public class CouponsActivity extends AppCompatActivity{

    WebView webview;
    ProgressBar progressbar;
    SwipeRefreshLayout refresh;

    //TODO
    //insert URL to service with coupons
    String URL = " ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupons);
        refresh = this.findViewById(R.id.swipeRefresh);
        webview = (WebView) findViewById(R.id.webview);
        progressbar = (ProgressBar) findViewById(R.id.progressbar);

        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setDomStorageEnabled(true);
        webview.getSettings().setBuiltInZoomControls(true);
        webview.getSettings().setDisplayZoomControls(false);

        webview.loadUrl(URL);

        webview.setWebViewClient(new WebViewClient() {

            @Override
            public void onReceivedError(WebView webview, int errorCode, String description, String failingUrl){
                try {
                    webview.stopLoading();
                } catch (Exception e) {
                }
                try {
                    webview.clearView();
                } catch (Exception e) {
                }
                if (webview.canGoBack()) {
                    webview.goBack();
                }
                webview.loadUrl("file:///android_asset/404.html");
                super.onReceivedError(webview, errorCode, description, failingUrl);
            }

            public void onPageFinished(WebView view, String url) {
                progressbar.setVisibility(View.GONE);
            }

        });

        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh () {

                if (webview.getUrl() != null) {
                    webview.loadUrl(URL);
                }
                refresh.setRefreshing(false);

            }
        });


    }

}



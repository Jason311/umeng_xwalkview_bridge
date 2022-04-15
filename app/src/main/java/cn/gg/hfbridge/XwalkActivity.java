package cn.gg.hfbridge;

import android.app.Activity;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.ValueCallback;
import android.webkit.WebResourceResponse;

import com.umeng.analytics.MobclickAgent;

import org.xwalk.core.ClientCertRequest;
import org.xwalk.core.XWalkHttpAuthHandler;
import org.xwalk.core.XWalkJavascriptResult;
import org.xwalk.core.XWalkPreferences;
import org.xwalk.core.XWalkResourceClient;
import org.xwalk.core.XWalkSettings;
import org.xwalk.core.XWalkUIClient;
import org.xwalk.core.XWalkView;
import org.xwalk.core.XWalkWebResourceRequest;
import org.xwalk.core.XWalkWebResourceResponse;

import java.io.InputStream;
import java.util.Map;

public class XwalkActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.umeng_example_analytics_xwebview);
        XWalkView webView = findViewById(R.id.webview);
        //添加对javascript支持
        XWalkPreferences.setValue("enable-javascript", true);
        //开启调式,支持谷歌浏览器调式
        XWalkPreferences.setValue(XWalkPreferences.REMOTE_DEBUGGING, true);
        //置是否允许通过file url加载的Javascript可以访问其他的源,包括其他的文件和http,https等其他的源
        XWalkPreferences.setValue(XWalkPreferences.ALLOW_UNIVERSAL_ACCESS_FROM_FILE, true);
        //JAVASCRIPT_CAN_OPEN_WINDOW
        XWalkPreferences.setValue(XWalkPreferences.JAVASCRIPT_CAN_OPEN_WINDOW, true);
        // enable multiple windows.
        XWalkPreferences.setValue(XWalkPreferences.SUPPORT_MULTIPLE_WINDOWS, true);
        webView.load("file:///android_asset/demo.html", null);
        webView.setResourceClient(new XWalkResourceClient(webView) {

            @Override
            public boolean shouldOverrideUrlLoading(XWalkView view, String url) {
                try {
                    Log.d("UMHybrid", "shouldOverrideUrlLoading url:" + url);
                    String decodedURL = java.net.URLDecoder.decode(url, "UTF-8");
                    Log.d("UMHybrid", "shouldOverrideUrlLoading decodedURL:" + decodedURL);
                    UMXWalkHybrid.getInstance(XwalkActivity.this).execute(decodedURL, view);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return false;
            }

            @Override
            public XWalkWebResourceResponse shouldInterceptLoadRequest(XWalkView view, XWalkWebResourceRequest request) {
                Log.d("UMHybrid", "shouldInterceptLoadRequest url:" + request.getUrl());
                return super.shouldInterceptLoadRequest(view, request);
            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
        Log.i("mob", "--Webview-onPause-----");
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
        Log.i("mob", "--Webview-onResume-----");
    }

//    class MyWebviewClient extends XWalkResourceClient {
//
//        public MyWebviewClient(XWalkView view) {
//            super(view);
//        }
//
//        @Override
//        public void onPageFinished(WebView view, String url) {
//            view.loadUrl("javascript:setWebViewFlag()");
//            if (url != null && url.endsWith("/index.html")) {
//                MobclickAgent.onPageStart("index.html");
//            }
//        }
//
//        @Override
//        public boolean shouldOverrideUrlLoading(WebView view, String url) {
//            try {
//                Log.d("UMHybrid", "shouldOverrideUrlLoading url:" + url);
//                String decodedURL = java.net.URLDecoder.decode(url, "UTF-8");
//                Log.d("UMHybrid", "shouldOverrideUrlLoading decodedURL:" + decodedURL);
//                UMHybrid.getInstance(XwalkActivity.this).execute(decodedURL, view);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            return false;
//        }
//    }
}

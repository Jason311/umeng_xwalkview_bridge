package cn.gg.hfbridge;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.annotation.Nullable;
import androidx.core.app.ComponentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends ComponentActivity {
    RecyclerView recyclerView;
    WebView webview_header;
    LinearLayoutManager linearLayoutManager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);
        recyclerView = findViewById(R.id.rv_ac);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        List<String> list = new ArrayList();
        for (int i =0 ;i<30;i++){
            list.add(i+"-----");
        }
        MyQuickAdapter adapter = new MyQuickAdapter(R.layout.item_rv_qc,list);
        View headerView = View.inflate(RecyclerViewActivity.this,R.layout.header_rv_view,null);
         webview_header = headerView.findViewById(R.id.webview_header);
        initweb();
        webview_header.loadUrl("https://www.baidu.com");
        adapter.addHeaderView(headerView);
        recyclerView.setAdapter(adapter);
//        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
//            @Override
//            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//                //              headerView占用一个位置，从1开始，上移一个全部评论的位置
//                recyclerView.scrollToPosition(0);
//                linearLayoutManager.scrollToPositionWithOffset(0, 0);
//            }
//        });
//        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                //              headerView占用一个位置，从1开始，上移一个全部评论的位置
////                recyclerView.scrollToPosition(0);
////                linearLayoutManager.scrollToPositionWithOffset(0, 0);
//            }
//        });
    }
    public void initweb(){
        WebSettings settings = webview_header.getSettings();
        settings.setSupportZoom(true);          //支持缩放
        settings.setJavaScriptEnabled(true);    //启用JS脚本
        settings.setUseWideViewPort(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setLoadWithOverviewMode(true);
        settings.setAllowFileAccess(true);// 设置允许访问文件数据
        settings.setNeedInitialFocus(false);
        webview_header.getSettings().setBlockNetworkImage(false);
        webview_header.getSettings().setDomStorageEnabled(false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webview_header.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        webview_header.setDrawingCacheEnabled(true);
        webview_header.buildDrawingCache();
        webview_header.buildLayer();
        settings.setPluginState(WebSettings.PluginState.ON);
        String userAgent = settings.getUserAgentString();
        settings.setUserAgentString(userAgent + " scienceChina/" + BuildConfig.VERSION_NAME);
        webview_header.getSettings().setDefaultTextEncodingName("utf-8");

    }
}

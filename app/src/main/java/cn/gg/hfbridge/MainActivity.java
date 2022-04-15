package cn.gg.hfbridge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.umeng.commonsdk.UMConfigure;

public class MainActivity extends AppCompatActivity {
    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        UMConfigure.setLogEnabled(true);
    }
    public void onClick(View v) {
        int id = v.getId();
        Intent in = null;
        if (id == R.id.normal) {
            startActivity(new Intent(mContext, WebviewAnalytic.class));
        } else if (id == R.id.game) {

            startActivity(new Intent(mContext, RecyclerViewActivity.class));
        } else if (id == R.id.dplus) {
            startActivity(new Intent(mContext, XwalkActivity.class));
        }

    }

}

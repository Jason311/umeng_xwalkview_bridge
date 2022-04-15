package cn.gg.hfbridge;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class MyQuickAdapter extends BaseQuickAdapter<String , BaseViewHolder> {
    public MyQuickAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    public MyQuickAdapter(@Nullable List<String> data) {
        super(data);
    }

    public MyQuickAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

    }
}

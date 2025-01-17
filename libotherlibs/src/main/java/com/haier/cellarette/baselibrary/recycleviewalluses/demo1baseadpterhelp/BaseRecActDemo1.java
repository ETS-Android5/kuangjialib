package com.haier.cellarette.baselibrary.recycleviewalluses.demo1baseadpterhelp;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.haier.cellarette.baselibrary.R;
import com.haier.cellarette.baselibrary.switchbutton.SwitchButtonK;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.ArrayList;
import java.util.List;

public class BaseRecActDemo1 extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private BaseRecActDemo1Adapter mAdapter;
    private List<BaseRecActDemo1Bean> mList;
    private SwitchButtonK switchButton;
    private MaterialSpinner spinner;


    public static List<BaseRecActDemo1Bean> getSampleData(int lenth) {
        List<BaseRecActDemo1Bean> list = new ArrayList<>();
        for (int i = 0; i < lenth; i++) {
            BaseRecActDemo1Bean status = new BaseRecActDemo1Bean();
            status.setUserName("Chad" + i);
            status.setCreatedAt("04/05/" + i);
            status.setRetweet(i % 2 == 0);
            status.setUserAvatar("https://avatars1.githubusercontent.com/u/7698209?v=3&s=460");
            status.setText("BaseRecyclerViewAdpaterHelper https://www.recyclerview.org");
            list.add(status);
        }
        return list;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycleviewallsuses_demo1);
        findview();
        initMenu();
        onclicklistener();
        donetwork();
    }

    private void donetwork() {
        mList = new ArrayList<>();
        mList = getSampleData(100);
        mAdapter.setNewData(mList);
        mAdapter.notifyDataSetChanged();

    }

    private void onclicklistener() {
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                //item click
                ToastUtils.showLong(position + "item click");
            }
        });
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                BaseRecActDemo1Bean addressBean = mList.get(position);
                int i = view.getId();
                if (i == R.id.brademo1_img) {
                    ToastUtils.showLong(addressBean.getUserAvatar() + "    " + position);
                } else if (i == R.id.brademo1_tweetName) {
                    ToastUtils.showLong(addressBean.getUserName() + position);
                } else if (i == R.id.brademo1_tweetText) {
                    ToastUtils.showLong(addressBean.getText() + position);
                } else {
                }
            }
        });
        mAdapter.setOnItemChildLongClickListener(new BaseQuickAdapter.OnItemChildLongClickListener() {
            @Override
            public boolean onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {
                BaseRecActDemo1Bean addressBean = mList.get(position);
                int i = view.getId();
                if (i == R.id.brademo1_img) {
                    ToastUtils.showLong(addressBean.getUserAvatar() + "长按    " + position);
                } else if (i == R.id.brademo1_tweetName) {
                    ToastUtils.showLong(addressBean.getUserName() + position);
                } else if (i == R.id.brademo1_tweetText) {
                    ToastUtils.showLong(addressBean.getText() + position);
                } else {
                }
                return true;
            }
        });
    }

    private void findview() {
        mRecyclerView = findViewById(R.id.rvlist);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new BaseRecActDemo1Adapter(R.layout.activity_recycleviewalluses_demo1_item);
        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        mAdapter.setNotDoAnimationCount(3);// mFirstPageItemCount
        mRecyclerView.setAdapter(mAdapter);
    }

    private void initMenu() {
        spinner = findViewById(R.id.spinner);
        spinner.setItems("AlphaIn", "ScaleIn", "SlideInBottom", "SlideInLeft", "SlideInRight", "Custom");
        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                switch (position) {
                    case 0:
                        mAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
                        break;
                    case 1:
                        mAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
                        break;
                    case 2:
                        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM);
                        break;
                    case 3:
                        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
                        break;
                    case 4:
                        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_RIGHT);
                        break;
                    case 5:
                        mAdapter.openLoadAnimation(new CustomAnimation());
                        break;
                    default:
                        break;
                }
                mRecyclerView.setAdapter(mAdapter);
            }
        });
        mAdapter.isFirstOnly(false);//init firstOnly state
        switchButton = findViewById(R.id.switch_button1);
        switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(final CompoundButton buttonView, final boolean isChecked) {
                if (isChecked) {
                    mAdapter.isFirstOnly(true);
                } else {
                    mAdapter.isFirstOnly(false);
                }
                mAdapter.notifyDataSetChanged();
            }
        });

    }
}

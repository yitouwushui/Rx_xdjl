package cn.ecar.insurance.mvvm.view.act.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


import com.orhanobut.logger.Logger;
import com.trello.rxlifecycle.LifecycleTransformer;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import javax.annotation.Nonnull;

import cn.ecar.insurance.R;
import cn.ecar.insurance.config.XdConfig;
import cn.ecar.insurance.dao.bean.Bank;
import cn.ecar.insurance.databinding.ActivityListViewBinding;
import cn.ecar.insurance.mvvm.base.BaseBindingActivity;

/**
 * 选择银行，会员单位等
 *
 * @author ding
 */
public class ChoiceActivity extends BaseBindingActivity<ActivityListViewBinding> {

    ArrayList<?> bankList;
    Class<?> cls;

    @Override
    public void getBundleExtras(Bundle extras) {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            Bundle bundle1 = bundle.getBundle(XdConfig.EXTRA_BUNDLE);
            cls = (Class<?>) bundle1.get(XdConfig.EXTRA_CLASS_VALUE);
            bankList = bundle.getParcelableArrayList(XdConfig.EXTRA_ARRAY_VALUE);

            try {
                Logger.i(cls != null ? cls.getName() : "c is null");
                Logger.i(bankList != null ? "大小"+ bankList.size() : "bankList is null");
                for (int i = 0; i < bankList.size(); i++) {
                    Object obj = bankList.get(i);
                    Method getFuc = cls.getMethod("getSelectCode");
                    Method setFuc = cls.getMethod("setBankName",String.class);
                    Method toStringFunc = cls.getMethod("toString");
                    //取得方法后即可通过invoke方法调用，传入被调用方法所在类的对象和实参,对象可以通过cls.newInstance取得
                    setFuc.invoke(obj, "反射修改");
                    Logger.i(getFuc.invoke(obj).toString());
                    Logger.i(toStringFunc.invoke(obj).toString());
                    Logger.i("-------");
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_list_view;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void destroyView() {

    }

//    TitleView titleView;
//    ListView listView;
//    ArrayList<ChoiceBean> data = new ArrayList<>();
//    String title;
//    ChoiceAdapter choiceAdapter;
//    String action;
//    LoginDialog.MyBinder myBinder;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_list_view);
//
//        init();
//    }
//
//
//    private void init() {
//        titleView = (TitleView) findViewById(R.id.view_title);
//        listView = (ListView) findViewById(R.id.listView);
//
//        Intent intent = getIntent();
//        Bundle bundle = intent.getExtras();
//        title = intent.getStringExtra(App.EXTRA_DATA_STRING_TITLE);
//        action = intent.getAction();
//        myBinder = (LoginDialog.MyBinder) bundle.getSerializable(App.EXTRA_DATA_OBJECT);
//
//        data = (ArrayList<ChoiceBean>) bundle.get(App.EXTRA_DATA_LIST);
//        titleView.setTitle(title);
//        choiceAdapter = new ChoiceAdapter(this, data);
//        listView.setAdapter(choiceAdapter);
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                if (action != null && action.equals(App.INTENT_LOGIN_MEMBER)) {
//                    myBinder.getLoginDialog().setMembers(position);
//                }
//                Intent resultIntent = new Intent();
//                resultIntent.putExtra(App.EXTRA_DATA_INT, position);
//                resultIntent.putExtra(App.EXTRA_DATA_STRING, data.get(position).getName());
//                resultIntent.putExtra(App.EXTRA_DATA_STRING_PARAM, data.get(position).getSelectCode());
//                setResult(1, resultIntent);
//                finish();
//            }
//        });
//
//    }
}

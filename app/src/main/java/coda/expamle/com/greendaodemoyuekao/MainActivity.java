package coda.expamle.com.greendaodemoyuekao;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.Telephony;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import coda.expamle.com.greendaodemoyuekao.dao.BeanDao;
import coda.expamle.com.greendaodemoyuekao.dao.DaoMaster;
import coda.expamle.com.greendaodemoyuekao.dao.DaoSession;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
   private List<Bean> list=new ArrayList<>();

    private Button add;
    private ListView listview;
    private BeanDao beanDao;
    private MyAdapters adapters;
private int a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        GreenDaoHelper daoHelper = new GreenDaoHelper(MainActivity.this);
        SQLiteDatabase sqLiteDatabase = daoHelper.getRead();
        DaoMaster daoMaster = new DaoMaster(sqLiteDatabase);
        DaoSession daoSession = daoMaster.newSession();
        beanDao = daoSession.getBeanDao();

        query();
        adapters = new MyAdapters(MainActivity.this,list);
        listview.setAdapter(adapters);
        adapters.notifyDataSetChanged();

    }
private  void insert(Bean bean){
    GreenDaoHelper daoHelper = new GreenDaoHelper(MainActivity.this);
    SQLiteDatabase sqLiteDatabase = daoHelper.getRead();
    DaoMaster daoMaster = new DaoMaster(sqLiteDatabase);
    DaoSession daoSession = daoMaster.newSession();
    beanDao = daoSession.getBeanDao();
    beanDao.insert(bean);

}

private  void query(){
        list.clear();
        GreenDaoHelper daoHelper = new GreenDaoHelper(MainActivity.this);

        SQLiteDatabase sqLiteDatabase = daoHelper.getRead();

        DaoMaster daoMaster = new DaoMaster(sqLiteDatabase);

        DaoSession daoSession = daoMaster.newSession();

        beanDao = daoSession.getBeanDao();
        List<Bean> beanlist = beanDao.queryBuilder().build().list();
        list.addAll(beanlist);
    }
    private  void updata(Bean bean){
        GreenDaoHelper daoHelper = new GreenDaoHelper(MainActivity.this);
        SQLiteDatabase sqLiteDatabase = daoHelper.getRead();
        DaoMaster daoMaster = new DaoMaster(sqLiteDatabase);
        DaoSession daoSession = daoMaster.newSession();
        beanDao = daoSession.getBeanDao();
        beanDao.update(bean);
    }
    private void initView() {
        add = (Button) findViewById(R.id.add);
        listview = (ListView) findViewById(R.id.listview);
        add.setOnClickListener(this);
        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(final AdapterView<?> parent, View view, final int position, long id) {
                a=position;
                View viewp = View.inflate(MainActivity.this, R.layout.popup, null);
                final PopupWindow popup = new PopupWindow(viewp, 100, 100);
                popup.setOutsideTouchable(true);
                popup.setBackgroundDrawable(new ColorDrawable(Color.BLACK));
                popup.showAsDropDown(view);
                Button xiugai = (Button) viewp.findViewById(R.id.xiugai);
                Button shanchu = (Button) viewp.findViewById(R.id.shanch);
                shanchu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bean bean1 = beanDao.queryBuilder().where(BeanDao.Properties.Name.eq(list.get(position).getName())).build().unique();
                          if (bean1!=null){
                              beanDao.delete(bean1);
                          }
                      list.clear();
                        query();
                        adapters.notifyDataSetChanged();
                        popup.dismiss();
                    }
                });
                xiugai.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bean bean = list.get(position);
                        Intent intent1 = new Intent(MainActivity.this, Main3Activity.class);
                        intent1.putExtra("bean",bean);
                        startActivityForResult(intent1,1000);
                        popup.dismiss();

                    }
                });

                return true;
            }
        });
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add:
                Intent intent=new Intent(MainActivity.this,Main2Activity.class);
                startActivityForResult(intent,100);
                System.out.println("sssssssssssss");
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode){
            case 100:
             Bean bean1 = (Bean) data.getSerializableExtra("bean");
            insert(bean1);
                query();
                adapters.notifyDataSetChanged();
                break;
            case 1000:
               Bean key = (Bean) data.getSerializableExtra("key");
                updata(key);
                query();
                adapters.notifyDataSetChanged();
                break;


        }
    }
}

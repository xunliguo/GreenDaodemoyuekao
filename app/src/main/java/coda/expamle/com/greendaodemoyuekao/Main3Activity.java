package coda.expamle.com.greendaodemoyuekao;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import coda.expamle.com.greendaodemoyuekao.dao.BeanDao;
import coda.expamle.com.greendaodemoyuekao.dao.DaoMaster;
import coda.expamle.com.greendaodemoyuekao.dao.DaoSession;

import static android.R.id.list;

public class Main3Activity extends AppCompatActivity implements View.OnClickListener {

    private EditText name;
    private EditText banshe;
    private Button queding;
    private BeanDao beanDao;
    private Bean beanq;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Intent intent = getIntent();
        beanq = (Bean) intent.getExtras().get("bean");
        initView();
    }

    private void initView() {
        name = (EditText) findViewById(R.id.name);
        banshe = (EditText) findViewById(R.id.banshe);
        queding = (Button) findViewById(R.id.queding);

        queding.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.queding:
submit();
                break;
        }
    }

    private void submit() {
        // validate
        String nameString = name.getText().toString().trim();
        if (TextUtils.isEmpty(nameString)) {
            Toast.makeText(this, "书名", Toast.LENGTH_SHORT).show();
            return;
        }

        String bansheString = banshe.getText().toString().trim();
        if (TextUtils.isEmpty(bansheString)) {
            Toast.makeText(this, "板摄", Toast.LENGTH_SHORT).show();
            return;
        }
         beanq.setName(nameString);
         beanq.setBanshe(bansheString);
        Intent intent=new Intent();
        intent.putExtra("key",beanq);
        setResult(1000,intent);
        finish();


    }

}

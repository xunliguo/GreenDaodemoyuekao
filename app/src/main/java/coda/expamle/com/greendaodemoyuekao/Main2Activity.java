package coda.expamle.com.greendaodemoyuekao;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.Serializable;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {

    private EditText name;
    private EditText banshe;
    private Button queding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
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
       Bean bean=new Bean();
        bean.setName(nameString);
        bean.setBanshe(bansheString);
        Intent intent=new Intent();
        intent.putExtra("bean",  bean);
        setResult(100,intent);
        finish();


    }
}

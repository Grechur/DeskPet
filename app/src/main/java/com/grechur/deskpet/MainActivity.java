package com.grechur.deskpet;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.grechur.deskpet.utils.LiveDataBus;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import io.objectbox.Box;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Box box;
    TextView text;
    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        box = MyApplication.getBoxStore().boxFor(Student.class);
        // Example of a call to a native method
        final TextView tv = (TextView) findViewById(R.id.sample_text);
//        tv.setText(stringFromJNI());
        String content = String.format(getResources().getString(R.string.sign_success), 20+"");
        tv.setText(Html.fromHtml(content));

        text = findViewById(R.id.text);

        tv.setOnClickListener(this);
        text.setOnClickListener(this);
        LiveDataBus.get().with("bye",String.class)
                .observe(this, new Observer<String>() {
                    @Override
                    public void onChanged(String s) {
                        Toast.makeText(MainActivity.this,s,Toast.LENGTH_SHORT).show();
                    }
                });
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.sample_text:
                LiveDataBus.get().with("bye").setValue("save data");
                Student student = new Student();
                student.name = "张三";
                student.age = 20;
                box.put(student);

                Log.e("TAG"," view"+getResources().getResourceName(v.getId()));
                Log.e("TAG"," view"+getResources().getResourceEntryName(v.getId()));
                Log.e("TAG"," view"+getResources().getResourcePackageName(v.getId()));
                Log.e("TAG"," view"+getResources().getResourceTypeName(v.getId()));
                break;
            case R.id.text:
                List<Student> list = box.getAll();
                for (Student student1 : list) {
                    text.setText(student1.name);
                }
                startActivity(new Intent(this,SecondActivity.class));
                break;
        }
    }
}

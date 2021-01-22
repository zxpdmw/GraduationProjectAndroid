package top.zxpdmw.graduationproject.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import top.zxpdmw.graduationproject.R;


public class LoginActivity extends AppCompatActivity {
    private TextView newUser;
    private Button login;
    private EditText name;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        name=(EditText)findViewById(R.id.name);
        password=(EditText)findViewById(R.id.password);
        newUser=(TextView)findViewById(R.id.news);
        newUser.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);

        });

        login=(Button)findViewById(R.id.login_button);
        login.setOnClickListener(v -> {
            if (name.getText().toString().equals("zwy")&&password.getText().toString().equals("123")){
                Toast toast = new Toast(LoginActivity.this);
                toast.setText("登陆成功");
                toast.show();
                Intent intent = new Intent(LoginActivity.this, SystemMainActivity.class);
                startActivity(intent);

            }else {
                Toast toast = new Toast(LoginActivity.this);
                toast.setText("账号或者密码错误,请重新输入!");
                toast.show();
            }
        });



    }
}
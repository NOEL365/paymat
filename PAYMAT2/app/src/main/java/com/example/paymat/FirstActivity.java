package com.example.paymat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class FirstActivity extends AppCompatActivity {
    CardView bankcardId, matreg,matcul,about;
    ImageView account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        bankcardId = findViewById(R.id.bankcardId);
        bankcardId = findViewById(R.id.bankcardId);
        matreg = findViewById(R.id.matreg);
        matcul=findViewById(R.id.matcul);
        account=findViewById(R.id.account);
        about=findViewById(R.id.about);
        matreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FirstActivity.this, Registration.class);
                startActivity(intent);
                ;
            }
        });
        bankcardId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View View) {
                Intent intent = new Intent(FirstActivity.this, ImagesActivity.class);
                startActivity(intent);
            }
        });
        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(FirstActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        matcul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/search?q=matatu+culture+nairobi&source=lnms&tbm=isch&sa=X&ved=0ahUKEwi91drZ07_jAhXCxYUKHRVnDbkQ_AUIESgB&biw=1366&bih=654"));
                startActivity(intent);
            }
        });
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent  intent=new Intent(FirstActivity.this,about.class);
                startActivity(intent);
            }
        });
    }
}

package com.example.carwash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.carwash.Room.Database;
import com.example.carwash.Room.Sity;
import com.example.carwash.Room.SityDao;

import java.util.List;

public class setSityActivity extends AppCompatActivity {
    EditText sity;
    TextView button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Database db = App.getInstance().getDatabase(); // получение базы
        SityDao sityDao = db.sityDao(); // из Database объекта получаем Dao
        List<Sity> sitys = sityDao.getAll();
        if (sitys.size() != 0) {
            Intent intent = new Intent(setSityActivity.this, MainActivity.class);
            startActivity(intent);
        }
        setContentView(R.layout.activity_set_sity);
        sity = findViewById(R.id.set_sity_text);
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sity.getText().toString().length() > 1) {
                    sityDao.deleteAll();

                    Sity sityObj = new Sity(); // создание OBJ

                    sityObj.sityName = sity.getText().toString(); // заполнение полей

                    sityDao.insert(sityObj);

                    Intent intent = new Intent(setSityActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(setSityActivity.this, "Введите название города", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
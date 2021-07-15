package sg.edu.rp.c346.id20047401.npdsongs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RadioGroup rgStars;
    EditText etTitle, etsingers, etYear;
    Button btnInsert,btnShowList;
    ArrayList<Song> al;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rgStars = findViewById(R.id.radioUpdateStars);
        etTitle = findViewById(R.id.etSongTitle);
        etsingers = findViewById(R.id.etSinger);
        etYear = findViewById(R.id.etYear);
        btnInsert = findViewById(R.id.buttonUpdate);
        btnShowList = findViewById(R.id.buttonDelete);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = etTitle.getText().toString();
                String singers = etsingers.getText().toString();
                int year = Integer.parseInt(etYear.getText().toString());
                int stars = getStars();
                DBHelper dbh = new DBHelper(MainActivity.this);
                long inserted_id = dbh.insertSong(title,singers,year,stars);

                if (inserted_id != -1){
                    Toast.makeText(MainActivity.this, "Insert successful",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnShowList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SecondActivity.class);
                startActivity(intent);
            }
        });

    }
    private int getStars() {
        int stars = 1;
        if(rgStars.getCheckedRadioButtonId() == R.id.rb1){
            stars = 1;
        } else if (rgStars.getCheckedRadioButtonId() == R.id.rb2){
            stars = 2;
        } else if (rgStars.getCheckedRadioButtonId() == R.id.rb3){
            stars = 3;
        } else if (rgStars.getCheckedRadioButtonId() == R.id.rb4){
            stars = 4;
        } else if (rgStars.getCheckedRadioButtonId() == R.id.rb5){
            stars = 5;
        }
        return stars;
    }
}
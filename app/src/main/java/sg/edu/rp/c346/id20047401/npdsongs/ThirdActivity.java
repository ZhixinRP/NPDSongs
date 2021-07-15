package sg.edu.rp.c346.id20047401.npdsongs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class ThirdActivity extends AppCompatActivity {

    EditText etID,etUpdateSongTitle,etUpdateSinger, etUpdateYear;
    RadioButton rb1,rb2,rb3,rb4,rb5;
    RadioGroup rgUpdateStars;
    Song song;
    Button btnUpdate,btnDelete,btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        etID = findViewById(R.id.etUpdateID);
        etUpdateSongTitle = findViewById(R.id.etUpdateSongTitle);
        etUpdateSinger = findViewById(R.id.etUpdateSinger);
        etUpdateYear = findViewById(R.id.etUpdateYear);
        btnCancel = findViewById(R.id.buttonCancel);
        btnUpdate= findViewById(R.id.buttonUpdate);
        btnDelete = findViewById(R.id.buttonDelete);

        rgUpdateStars = findViewById(R.id.radioUpdateStars);
        rb1 = findViewById(R.id.rb1);
        rb2 = findViewById(R.id.rb2);
        rb3 = findViewById(R.id.rb3);
        rb4 = findViewById(R.id.rb4);
        rb5 = findViewById(R.id.rb5);

        etID.setEnabled(false);

        Intent i = getIntent();
        song = (Song) i.getSerializableExtra("song");
        etID.setText(String.valueOf(song.get_id()));
        etUpdateSongTitle.setText(song.getTitle());
        etUpdateSinger.setText(song.getSingers());
        etUpdateYear.setText(String.valueOf(song.getYear()));
        int stars = song.getStar();
        if (stars == 1) {
            rb1.setChecked(true);
        } else if (stars == 2) {
            rb2.setChecked(true);
        } else if (stars == 3) {
            rb3.setChecked(true);
        } else if (stars == 4) {
            rb4.setChecked(true);
        } else {
            rb5.setChecked(true);
        }

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                song.setTitle(etUpdateSongTitle.getText().toString());
                song.setSingers(etUpdateSinger.getText().toString());
                song.setYear(Integer.parseInt(etUpdateYear.getText().toString()));
                song.setStars(getStars());
                DBHelper dbh = new DBHelper(ThirdActivity.this);
                dbh.updateSong(song);
                setResult(RESULT_OK);
                finish();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ThirdActivity.this);
                dbh.deleteSong(song.get_id());
                setResult(RESULT_OK);
                finish();
            }
        });
    }
    private int getStars() {
        int stars = 1;
        if(rgUpdateStars.getCheckedRadioButtonId() == R.id.rb1){
            stars = 1;
        } else if (rgUpdateStars.getCheckedRadioButtonId() == R.id.rb2){
            stars = 2;
        } else if (rgUpdateStars.getCheckedRadioButtonId() == R.id.rb3){
            stars = 3;
        } else if (rgUpdateStars.getCheckedRadioButtonId() == R.id.rb4){
            stars = 4;
        } else if (rgUpdateStars.getCheckedRadioButtonId() == R.id.rb5){
            stars = 5;
        }
        return stars;
    }
}
package sg.edu.rp.c346.id20047401.npdsongs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    ListView lv;
    Button btnFilter;
    ArrayAdapter<Song> aa;
    ArrayList<Song> al;
    boolean clicked = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        lv = findViewById(R.id.lv);
        btnFilter = findViewById(R.id.buttonFilter);

        al = new ArrayList<Song>();
        aa = new ArrayAdapter<Song>(this, android.R.layout.simple_list_item_1, al);
        lv.setAdapter(aa);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int
                    position, long identity) {
                Song song = al.get(position);
                Intent i = new Intent(SecondActivity.this,
                        ThirdActivity.class);
                i.putExtra("song", song);
                startActivity(i);
            }
        });

        btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(SecondActivity.this);
                al.clear();
                if (clicked) {
                    clicked = false;
                    al.addAll(dbh.getAllSongsByStars(5));
                } else {
                    clicked = true;
                    al.addAll(dbh.getAllSongs());
                }
                aa.notifyDataSetChanged();
                dbh.close();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        DBHelper dbh = new DBHelper(SecondActivity.this);
        al.clear();
        al.addAll(dbh.getAllSongs());
        aa.notifyDataSetChanged();
        dbh.close();
    }
}
package ans.org.quranapprecyclerview;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import ans.org.quranapprecyclerview.R.raw;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class MainActivity extends AppCompatActivity {



    List<Friend> friendsList = new ArrayList<>();
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Ayah> ayahArrayList = new ArrayList<>();

    public void readExcelFile() throws IOException {
        BufferedReader bReader;
        InputStream iStream = getBaseContext().getResources().openRawResource(raw.ayahs);
        bReader = new BufferedReader(new InputStreamReader(iStream, StandardCharsets.UTF_8));
        String line;

        bReader.readLine(); // skip the header

        while ((line = bReader.readLine()) != null) {
            String[] row = line.split(",");
            int number = Integer.parseInt(row[0]);
            String arabic_text = row[1];
            int numberInSurah = Integer.parseInt(row[2]);
            int page = Integer.parseInt(row[3]);
            String surah_name = row[4];
            String surah_name_english = row[5];
            String surah_name_english_translation = row[6];
            String revelationType = row[7];
            String urdu_translation = row[8];
            String urdu_tafseer = row[9];
            String english_translation = row[10];
            String english_tafseer = row[11];
            String sindhi_translation = row[12];
            String sindhi_tafseer = row[13];
            String hindi_translation = row[14];
            String hindi_tafseer = row[15];
            String pushto_translation = row[16];
            String pushto_tafseer = row[17];

            // create an object for each row
            Ayah ayah = new Ayah(number, arabic_text, numberInSurah, page, surah_name, surah_name_english, surah_name_english_translation, revelationType, urdu_translation, urdu_tafseer, english_translation, english_tafseer, sindhi_translation, sindhi_tafseer, hindi_translation, hindi_tafseer, pushto_translation, pushto_tafseer);

            // add the object to the list
            ayahArrayList.add(ayah);
        }
        bReader.close();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            readExcelFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Friend f0 = new Friend(1,"Asad", 1980, "Giglgit",R.drawable.d);
        Friend f1 = new Friend(2,"Zubair", 1981, "Lahore",R.drawable.c);
        Friend f2 = new Friend(3,"Musa", 1980, "Quetta",R.drawable.b);
        Friend f3 = new Friend(4,"Nadeem",1987,"Peshawar",R.drawable.a);
        Friend f4 = new Friend(5,"Shahid", 1980, "Karachi",R.drawable.c);
        Friend f5 = new Friend(6,"Zia",1987,"Faisalabad ",R.drawable.a);
        Friend f6 = new Friend(7,"Badar", 1980, "Rawalpindi",R.drawable.d);
        Friend f7 = new Friend(8,"Hashim",1997,"Karachi",R.drawable.b);
        Friend f8 = new Friend(9,"Salman",1980,"Quetta",R.drawable.c);
        Friend f9 = new Friend(10,"Rizwan",1982,"Kasur",R.drawable.d);
        Friend f10 = new Friend(11,"Junaid",1977,"Islamabad",R.drawable.a);
        Friend f11 = new Friend(12,"Waseem",1967,"Rawalpindi",R.drawable.b);

        friendsList.addAll(Arrays.asList(new Friend[]{f0,f1,f2,f3,f4,f5,f6,f7,f8,f9,f10,f11}));
        recyclerView = findViewById(R.id.recylerViewStudent);


        recyclerView.setHasFixedSize(true);

        //LinearLayoutManager GridLayoutManager
        layoutManager = new LinearLayoutManager(MainActivity.this);
//        layoutManager = new LinearLayoutManager(MainActivity.this,
//                LinearLayoutManager.HORIZONTAL,
//                true);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new myRecyclerViewAdapter(friendsList) ;
        recyclerView.setAdapter(adapter);
        //adapter.notifyDataSetChanged();


    }
}

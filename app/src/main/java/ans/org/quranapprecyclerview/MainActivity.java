package ans.org.quranapprecyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.android.material.textfield.TextInputEditText;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import ans.org.quranapprecyclerview.R.raw;

public class MainActivity extends AppCompatActivity {



    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    List<Ayah> ayahArrayList = new ArrayList<>();
    ImageButton githubButton;


    Button searchByParaButton;
    Button searchBySurahButton;

    EditText ayahNumberIP;
    EditText surahNumberIP;
    EditText paraNumberIP;


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
        // read excel file
        try {
            readExcelFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // setup github website button
        githubButton = findViewById(R.id.githubImgBtn);

        // setup image
        githubButton.setImageResource(R.drawable.icons8_github);

        // add click listener to github button, that'll redirect to github website
        githubButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/mohdanss/RecyclerViewQuranApp.git"));
                startActivity(intent);
            }
        });

        // everything about recycler view
        recyclerView = findViewById(R.id.recylerViewAyahs);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new myRecyclerViewAdapter(ayahArrayList) ;
        recyclerView.setAdapter(adapter);

        // everything about searching
        searchByParaButton = findViewById(R.id.searchByParah);
        searchBySurahButton = findViewById(R.id.searchBySurah);

        ayahNumberIP = findViewById(R.id.ayahNumberIP);
        surahNumberIP = findViewById(R.id.surahNumberIP);
        paraNumberIP = findViewById(R.id.parahNumberIP);

        // search by para button
        searchByParaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String paraNumber = paraNumberIP.getText().toString();
                if (paraNumber.isEmpty()) {
                    paraNumberIP.setError("Please enter a number");
                    paraNumberIP.requestFocus();
                    return;
                }
                int paraNumberInt = Integer.parseInt(paraNumber);
                if (paraNumberInt < 1 || paraNumberInt > 30) {
                    paraNumberIP.setError("Please enter a number between 1 and 30");
                    paraNumberIP.requestFocus();
                    return;
                }
                List<Ayah> ayahs = new ArrayList<>();
                for (Ayah ayah : ayahArrayList) {
                    if (ayah.page >= (paraNumberInt - 1) * 8 + 1 && ayah.page <= paraNumberInt * 8) {
                        ayahs.add(ayah);
                    }
                }
                adapter = new myRecyclerViewAdapter(ayahs);
                recyclerView.setAdapter(adapter);
            }
        });

        // search by surah button
        searchBySurahButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String surahNumber = surahNumberIP.getText().toString();
                if (surahNumber.isEmpty()) {
                    surahNumberIP.setError("Please enter a number");
                    surahNumberIP.requestFocus();
                    return;
                }
                int surahNumberInt = Integer.parseInt(surahNumber);
                if (surahNumberInt < 1 || surahNumberInt > 114) {
                    surahNumberIP.setError("Please enter a number between 1 and 114");
                    surahNumberIP.requestFocus();
                    return;
                }
                List<Ayah> ayahs = new ArrayList<>();
                for (Ayah ayah : ayahArrayList) {
                    if (ayah.numberInSurah == surahNumberInt) {
                        ayahs.add(ayah);
                    }
                }
                adapter = new myRecyclerViewAdapter(ayahs);
                recyclerView.setAdapter(adapter);
            }
        });
    }
}

package ans.org.quranapprecyclerview;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Helper {
    public static ArrayList<Ayah> ayahArrayList = new ArrayList<>();
    //    read the excel file, into the arraylist of Ayah
    // header of excel fil is
    //    number	text	numberInSurah	page	surah_name	englishName	englishNameTranslation	revelationType	UrduTranslation	UrduTafseer	EnglishTranslation	Englishtafseer	SindhiTranslation	SindhiTafseer	HindiTranslation	HindiTafseer	PushtoTransation	PushtoTafseer
    /*
    public int number;
    public String arabic_text;
    public int numberInSurah;
    public int page;
    public String surah_name;
    public String surah_name_english;
    public String surah_name_english_translation;
    public String revelationType;
    public String urdu_translation;
    public String urdu_tafseer;
    public String english_translation;
    public String english_tafseer;
    public String sindhi_translation;
    public String sindhi_tafseer;
    public String hindi_translation;
    public String hindi_tafseer;
    public String pushto_translation;
    public String pushto_tafseer;
     */
    public void readExcelFile() throws IOException {
        // read the excel file
        // create the ayah object
        // add the ayah object to the arraylist
        FileWriter filew = new FileWriter("fff.xlsx");
        FileReader fileReader = new FileReader("ayahs.xlsx");
        // read the file
        // create the ayah object
        // add the ayah object to the arraylist
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line = bufferedReader.readLine();
        System.out.println("line = " + line);
        while ((line = bufferedReader.readLine()) != null) {
            String[] ayahData = line.split("\t");
            Ayah ayah = new Ayah(Integer.parseInt(ayahData[0]), ayahData[1], Integer.parseInt(ayahData[2]), Integer.parseInt(ayahData[3]), ayahData[4], ayahData[5], ayahData[6], ayahData[7], ayahData[8], ayahData[9], ayahData[10], ayahData[11], ayahData[12], ayahData[13], ayahData[14], ayahData[15], ayahData[16], ayahData[17]);
            ayahArrayList.add(ayah);
        }
    }
}

package ans.org.quranapprecyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


class myRecyclerViewAdapter extends RecyclerView.Adapter<myRecyclerViewAdapter.MyVH> {

    List<Ayah> ayahList;
    public myRecyclerViewAdapter(List<Ayah> friendsList) {
        this.ayahList = friendsList;
    }

    @NonNull
    @Override
    public myRecyclerViewAdapter.MyVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_item, parent, false);
        return new MyVH(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull myRecyclerViewAdapter.MyVH holder, int position) {
        holder.data = ayahList.get(position);
        // only show 50 characters
        String arabicText = holder.data.arabic_text.length() > 60 ? holder.data.arabic_text.substring(0, 60) + "..." : holder.data.arabic_text;

        holder.textViewArabicText.setText(arabicText);
        String ayahNumber = "آیت " + holder.data.numberInSurah;
        holder.textViewAyahNumber.setText(ayahNumber);
        holder.textViewSurahName.setText(holder.data.surah_name);
    }

    @Override
    public int getItemCount() {
        return ayahList.size();
    }


    public class MyVH extends RecyclerView.ViewHolder {
        TextView textViewArabicText;
        TextView textViewSurahName;
        TextView textViewAyahNumber;
        Ayah data;
        public MyVH(@NonNull View itemView) {
            super(itemView);
            textViewArabicText = itemView.findViewById(R.id.textViewArabicText);
            textViewSurahName = itemView.findViewById(R.id.textViewSurahName);
            textViewAyahNumber = itemView.findViewById(R.id.textViewAyahNumber);
        }
    }
}
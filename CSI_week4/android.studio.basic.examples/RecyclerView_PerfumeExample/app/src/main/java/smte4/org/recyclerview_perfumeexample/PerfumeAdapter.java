package smte4.org.recyclerview_perfumeexample;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class PerfumeAdapter extends RecyclerView.Adapter<PerfumeAdapter.ViewHolder> {
    List<Perfume> perfumes;

    public PerfumeAdapter(List<Perfume> perfumes) {
        this.perfumes = perfumes;
    }

    @Override
    public PerfumeAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.perfumelistitem, viewGroup, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(PerfumeAdapter.ViewHolder viewHolder, int index) {
        Perfume perfume = perfumes.get(index);
        viewHolder.name.setText(perfume.name);
        viewHolder.price.setText(perfume.price.toString());
    }

    @Override
    public int getItemCount() {
        return perfumes.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name;
        TextView price;

        ViewHolder(View viewGroup) {
            super(viewGroup);
            name = (TextView) viewGroup.findViewById(R.id.textViewName);
            price = (TextView) viewGroup.findViewById(R.id.textViewPrice);

            name.setOnClickListener(this);
            price.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Toast toast = Toast.makeText(view.getContext(), "Clicked on " + name.getText().toString(),Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}

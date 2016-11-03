package smte4.org.recyclerview_perfumeexample;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);

        List<Perfume> perfumes = getDummyPerfumes();
        PerfumeAdapter perfumeAdapter = new PerfumeAdapter(perfumes);
        recyclerView.setAdapter(perfumeAdapter);
    }

    private List<Perfume> getDummyPerfumes() {
        List<Perfume> perfumes = new ArrayList<Perfume>();
        for(int i = 0; i < 25; i++) {
            Integer price = 45 + i*20;
            perfumes.add(new Perfume("Chanel no. " + i, price));
        }

        return perfumes;
    }
}

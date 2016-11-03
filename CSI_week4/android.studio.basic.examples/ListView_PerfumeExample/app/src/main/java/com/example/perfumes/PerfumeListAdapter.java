package com.example.perfumes;

import java.util.List;

import com.example.perfumes.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

@SuppressLint("InflateParams")  // See: https://code.google.com/p/android-developer-preview/issues/detail?id=1203
public class PerfumeListAdapter extends ArrayAdapter<Perfume> {
	private Context context;
	private List<Perfume> perfumes;
	
	public PerfumeListAdapter(Context context, List<Perfume> perfumes) {
		super(context, R.layout.main, perfumes);
		this.perfumes = perfumes;
		this.context = context;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Perfume requestedPerfume = perfumes.get(position);	
		
		View perfumeView = convertView;
		
		if(perfumeView == null) {
			LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			perfumeView  = inflater.inflate(R.layout.perfumelistitem, null);
		}
		
		TextView nameView = (TextView) perfumeView.findViewById(R.id.textViewName);
		nameView.setText(requestedPerfume.name);
		
		TextView priceView = (TextView) perfumeView.findViewById(R.id.textViewPrice);
		priceView.setText(String.format("$ %d,-",requestedPerfume.price));
		
		return perfumeView;
	}
}

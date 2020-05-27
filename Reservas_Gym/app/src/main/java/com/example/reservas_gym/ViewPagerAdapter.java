package com.example.reservas_gym;

import android.content.Context;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

public class ViewPagerAdapter extends PagerAdapter {
    private List<ViewPagerModel> models;
    private LayoutInflater layoutInflater;
    private Context context;


    public ViewPagerAdapter(List<ViewPagerModel> models, Context context) {
        this.models = models;
        this.context = context;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.page_viewer_items_layout, container, false);
        ImageView imageView;
        TextView studentName, reservationTime;
        imageView = view.findViewById(R.id.studentImage);
        studentName = view.findViewById(R.id.studentIdTxt);
        reservationTime = view.findViewById(R.id.resTimeTxt);
        imageView.setImageResource(models.get(position).getStudentImage());
        studentName.setText(models.get(position).getStudentId());
        reservationTime.setText(models.get(position).getReservationTime());
        container.addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return models.size();
    }
}

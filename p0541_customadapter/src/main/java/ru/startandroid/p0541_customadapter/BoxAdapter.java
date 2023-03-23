package ru.startandroid.p0541_customadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class BoxAdapter extends BaseAdapter {

     Context cx;
     LayoutInflater lvInf;
     List<Product> objects;

    public BoxAdapter(Context cx, List<Product> objects) {
        this.cx = cx;
        this.lvInf = (LayoutInflater) cx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.objects = objects;
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Object getItem(int i) {
        return objects.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View view1 = view;
        if (view1 == null) {
            view1 = lvInf.inflate(R.layout.item, viewGroup, false);
        }

        Product p = getProduct(i);

        ((TextView) view1.findViewById(R.id.tvDescr)).setText(p.name);
        ((TextView) view1.findViewById(R.id.tvPrice)).setText(p.price + "");
        ((ImageView) view1.findViewById(R.id.ivImage)).setImageResource(p.image);

        CheckBox chBox = (CheckBox) view1.findViewById(R.id.cbBox);
        chBox.setOnCheckedChangeListener(checkedChangeListener);

        chBox.setTag(i);
        chBox.setChecked(p.box);

        return view1;
    }


   protected Product getProduct(int position) {
        return ((Product) getItem(position));
    }

    List<Product> getBox() {
        List<Product> box = new ArrayList<>();
        for (Product p : objects) {
            if (p.box) {
                box.add(p);
            }
        }
        return box;
    }

   CompoundButton.OnCheckedChangeListener checkedChangeListener = new CompoundButton.OnCheckedChangeListener() {
       @Override
       public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
           getProduct((Integer) compoundButton.getTag()).box = b;
       }
   };
}

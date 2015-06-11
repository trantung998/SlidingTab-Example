package slidingtab.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import tienlenmiennam.slidingtag_example.R;

/**
 * Created by Tran on 6/1/2015.
 */

public class MyRecycleViewAdapter extends RecyclerView.Adapter<MyRecycleViewAdapter.ViewHolder> {
//    private List<ViewHolder> mDataSet;
    private List<Person> persons;

    public MyRecycleViewAdapter()
    {
        initializeData();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Person h = persons.get(position);
        holder.mAvartar.setImageResource(h.photoId);
        holder.mRole.setText(h.name);
        holder.mName.setText(h.name);
    }

    @Override
    public int getItemCount() {
        return persons.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mAvartar;
        TextView mName;
        TextView mRole;

        // each data item is just a string in this case
        public ViewHolder(View v) {
            super(v);
            mAvartar = (ImageView)v.findViewById(R.id.image);
            mName    = (TextView)v.findViewById(R.id.txt_name);
            mRole    = (TextView)v.findViewById(R.id.txt_role);

        }
    }

    class Person {
        String name;
        String age;
        int photoId;

        Person(String name, String age, int photoId) {
            this.name = name;
            this.age = age;
            this.photoId = photoId;
        }
    }

// This method creates an ArrayList that has three Person objects
// Checkout the project associated with this tutorial on Github if
// you want to use the same images.
    private void initializeData(){
        persons = new ArrayList<>();
        persons.add(new Person("Emma Wilson", "23 years old", R.mipmap.adore_icon));
        persons.add(new Person("Lavery Maiss", "25 years old", R.mipmap.afterboomicon));
        persons.add(new Person("Lillie Watts", "35 years old", R.mipmap.beatbrickicon));
        persons.add(new Person("Lillie Watts", "35 years old", R.mipmap.beatbrickicon));
        persons.add(new Person("Lillie Watts", "35 years old", R.mipmap.beatbrickicon));
        persons.add(new Person("Lillie Watts", "35 years old", R.mipmap.beatbrickicon));

    }
}

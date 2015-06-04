package slidingtab.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import tung.slidingtag_example.R;

/**
 * Created by Tran on 6/4/2015.
 */
public class MainDrawerAdapter extends RecyclerView.Adapter<MainDrawerAdapter.ViewHolder> {

    // Declaring Variable to Understand which View is being worked on
    private static final int TYPE_HEADER = 0;
    // IF the view under inflation and population is header or Item
    private static final int TYPE_ITEM = 1;

    private String mNavTitles[]; // String Array to store the passed titles Value from MainActivity.java
    private int mIcons[];       // Int Array to store the passed icons resource value from MainActivity.java

    private String mName;        //String Resource for header View Name
    private int mProfile;        //int Resource for header view profile picture
    private String mEmail;       //String Resource for header view email

    private Context mContext;
    public MainDrawerAdapter(String[] itemName, int[] itemIcon, String name, String detail, int profile, Context c) {
        mNavTitles = itemName;
        mIcons = itemIcon;
        mName = name;
        mProfile = profile;
        mEmail = detail;
        mContext = c;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        if(viewType == TYPE_HEADER) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.header, viewGroup, false);
            return new ViewHolder(v, viewType, mContext);
        }
        else{
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.drawer_item, viewGroup, false);
            return new ViewHolder(v, viewType, mContext);
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        if(viewHolder.holderType == TYPE_ITEM){
            viewHolder.imageView.setImageResource(mIcons[i - 1]);
            viewHolder.textView.setText(mNavTitles[i - 1]);
        }
        else{
            viewHolder.profile.setImageResource(mProfile);           // Similarly we set the resources for header view
            viewHolder.name.setText(mName);
            viewHolder.email.setText(mEmail);
        }
    }

    @Override
    public int getItemCount() {
        return mNavTitles.length + 1;
    }

    @Override
    public int getItemViewType(int position){
        if(position == 0){
            return TYPE_HEADER;
        }else{
            return TYPE_ITEM;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        int holderType;

        TextView textView;
        ImageView imageView;

        ImageView profile;
        TextView email;
        TextView name;
        Context mContext;
        public ViewHolder(View view, int type, Context c) {
            super(view);
            mContext = c;

            holderType = type;
            if(type == TYPE_ITEM){
                textView = (TextView)view.findViewById(R.id.rowText);
                imageView = (ImageView)view.findViewById(R.id.rowIcon);
                itemView.setClickable(true);
                itemView.setOnClickListener(this);
            }
            else{
                profile = (ImageView)view.findViewById(R.id.circleView);
                email = (TextView)view.findViewById(R.id.email);
                name  = (TextView)view.findViewById(R.id.name);
            }
        }

        @Override
        public void onClick(View v) {

        }
    }
}

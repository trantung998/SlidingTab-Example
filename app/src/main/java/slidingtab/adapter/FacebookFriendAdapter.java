package slidingtab.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.login.widget.ProfilePictureView;

import java.util.List;

import facebook.FriendInfo;
import tienlenmiennam.slidingtag_example.R;

/**
 * Created by user on 6/10/2015.
 */
public class FacebookFriendAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    List<FriendInfo> mFriendInfoList;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ProfilePictureView mAvartar;
        TextView mName;
        TextView mAge;

        // each data item is just a string in this case
        public ViewHolder(View v) {
            super(v);
            mAvartar = (ProfilePictureView)v.findViewById(R.id.profilePicture);
            mName    = (TextView)v.findViewById(R.id.txt_name);
            mAge    = (TextView)v.findViewById(R.id.txt_age);

        }
    }
}

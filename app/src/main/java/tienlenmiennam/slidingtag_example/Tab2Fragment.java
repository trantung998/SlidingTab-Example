package tienlenmiennam.slidingtag_example;

/**
 * Created by Tran on 6/1/2015.
 */

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.login.widget.ProfilePictureView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Edwin on 15/02/2015.
 */
public class Tab2Fragment extends Fragment {
    CallbackManager mCallbackManager;
    ProfileTracker profileTracker;
    private ProfilePictureView profilePictureView;
    private TextView greeting;

    boolean isInited = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.tab2,container,false);
        //Check internet connection

        //
        greeting = (TextView) v.findViewById(R.id.textView);
        profilePictureView = (ProfilePictureView)v.findViewById(R.id.profilePicture);
        LoginButton loginButton = (LoginButton)v.findViewById(R.id.login_button);
        loginButton.setFragment(this);

        mCallbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(mCallbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        Log.d("Facebook", "Login Success");
                        Log.d("Facebook", loginResult.getAccessToken().toString());
                        updateUI();
                    }

                    @Override
                    public void onCancel() {
                        // App code
//                        showAlert();
                        updateUI();
                        Log.d("Facebook", "Cancel Login");
                    }

                    @Override
                    public void onError(FacebookException exception) {
//                        showAlert();
                        updateUI();
                    }
                    private void showAlert() {
                        new AlertDialog.Builder(getActivity())
                                .setTitle(R.string.cancelled)
                                .setMessage(R.string.permission_not_granted)
                                .setPositiveButton(R.string.ok, null)
                                .show();
                    }
                });

        profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {
                isInited = false;
                updateUI();
            }
        };
        return v;
    }

    boolean checkInternet() {
        final ConnectivityManager conMgr = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo activeNetwork = conMgr.getActiveNetworkInfo();
        if (activeNetwork != null && activeNetwork.isConnected()) {
            // notify user you are online
            return true;
        } else {
            // notify user you are not online
            Toast.makeText(getActivity(),"No internet connection",Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    @Override
    public void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void updateUI() {
        if(!checkInternet()) return;
        boolean enableButtons = AccessToken.getCurrentAccessToken() != null;

        Profile profile = Profile.getCurrentProfile();
//        Log.d("profile", profile.getFirstName());
        if (enableButtons && profile != null && !isInited) {
            profilePictureView.setProfileId(profile.getId());
            greeting.setText(getString(R.string.hello_user, profile.getFirstName()));
            Log.d("profile picture uri", profile.getProfilePictureUri(75, 75).toString());
            Tab1Fragment.getFriends(0);
            isInited = true;
        } else {
            isInited = false;
            Log.d("UpdateUI" , "profile null");
            profilePictureView.setProfileId(null);
            greeting.setText(null);
            Tab1Fragment.clearList();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        AppEventsLogger.activateApp(getActivity());
        updateUI();
    }

    @Override
    public void onPause() {
        super.onPause();

        // Call the 'deactivateApp' method to log an app event for use in analytics and advertising
        // reporting.  Do so in the onPause methods of the primary Activities that an app may be
        // launched into.
        AppEventsLogger.deactivateApp(getActivity());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        profileTracker.stopTracking();
    }

    public static void request(
//            int offset, int limit
    ) {
//        GraphRequest request = GraphRequest.newMeRequest(
//                AccessToken.getCurrentAccessToken(),
//                new GraphRequest.GraphJSONObjectCallback() {
//                    @Override
//                    public void onCompleted(
//                            JSONObject object,
//                            GraphResponse response) {
//                        // Application code
//                        Log.d("Json",object.toString());
//                    }
//                });
//        Bundle parameters = new Bundle();
//        parameters.putString("fields", "id,name,link");
//        request.setParameters(parameters);
//        request.executeAsync();


//        GraphRequest request1 = GraphRequest.newMeRequest(
//                AccessToken.getCurrentAccessToken(),
//                new GraphRequest.GraphJSONObjectCallback() {
//                    @Override
//                    public void onCompleted(
//                            JSONObject object,
//                            GraphResponse response) {
//                        // Application code
////                        Log.d("Json",object.toString());
//                        Log.d("Json",object.optJSONObject("friends").optJSONObject("paging").optString("next"));
//                    }
//                });
//        Bundle parameters1 = new Bundle();
//        parameters1.putString("fields", "friends");
//        request1.setParameters(parameters1);
//        request1.executeAsync();


        GraphRequest request2 = GraphRequest.newMyFriendsRequest(
                AccessToken.getCurrentAccessToken(),
                new GraphRequest.GraphJSONArrayCallback() {
                    @Override
                    public void onCompleted(JSONArray jsonArray, GraphResponse graphResponse) {
                        // Application code
                        Log.d("Friends",jsonArray.toString());
                        Log.d("graphResponse",graphResponse.toString());
                        Tab1Fragment.updateFriendList(jsonArray);
                    }
                }
        );
        Bundle parameters1 = new Bundle(3);
        parameters1.putString("fields", "name");
//        parameters1.putInt("offset", offset);
//        parameters1.putInt("limit", limit);
        request2.setParameters(parameters1);
        Log.e("Path:", request2.getParameters().toString());
        request2.executeAsync();
    }

//    void requser1(String nextPage) {
//        GraphRequest request1 = GraphRequest.newMeRequest(
//                AccessToken.getCurrentAccessToken(),
//                new GraphRequest.GraphJSONObjectCallback() {
//                    @Override
//                    public void onCompleted(
//                            JSONObject object,
//                            GraphResponse response) {
//                        // Application code
////                        Log.d("Json",object.toString());
//                        Log.d("Json", object.optJSONObject("friends").optJSONObject("paging").optString("next"));
//                    }
//                });
//        Bundle parameters1 = new Bundle();
//        parameters1.putString("fields", "friends");
//        request1.setParameters(parameters1);
//        request1.executeAsync();
//    }

}

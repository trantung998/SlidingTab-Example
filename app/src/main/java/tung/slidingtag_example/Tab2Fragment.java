package tung.slidingtag_example;

/**
 * Created by Tran on 6/1/2015.
 */
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

/**
 * Created by Edwin on 15/02/2015.
 */
public class Tab2Fragment extends Fragment {

    LoginButton mLoginButton;
    CallbackManager mCallbackManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d("Facebook", "Login Success");
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getActivity());
        mCallbackManager = CallbackManager.Factory.create();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.tab2,container,false);

        mLoginButton = (LoginButton) v.findViewById(R.id.login_button);
        mLoginButton.setReadPermissions("user_friends");
        mLoginButton.setFragment(this);

        mLoginButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
                Log.d("Facebook", "Login Success");
            }

            @Override
            public void onCancel() {
                // App code
                Log.d("Facebook","Cancel Login");
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
                Log.d("Facebook","Login  Fail");
            }
        });
        return v;
    }
}

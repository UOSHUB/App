package baxzel.uoshub;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity{
    private EditText mId, mPassword;
    private Button mLoginButton;
    private RequestQueue mRequestQueue;
    private static final String URL = "https://www.uoshub.com/api/login/";
    private StringRequest mStringRequest;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);

        mId = (EditText) findViewById(R.id.student_id);
        mPassword = (EditText) findViewById(R.id.password);
        mLoginButton = (Button) findViewById(R.id.login_button);
        mRequestQueue = Volley.newRequestQueue(this);

        findViewById(R.id.student_id).setOnFocusChangeListener(new View.OnFocusChangeListener(){
            public void onFocusChange(View v, boolean hasFocus){
                String StringId = mId.getText().toString();

                if(!hasFocus){
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }

                if(TextUtils.isEmpty(StringId) && hasFocus)
                    findViewById(R.id.student_id).setBackgroundResource(R.drawable.rounded_textview);
                else if(TextUtils.isEmpty(StringId) && !hasFocus)
                    findViewById(R.id.student_id).setBackgroundResource(R.drawable.rounded_textview_alt);
            }
        });

        findViewById(R.id.password).setOnFocusChangeListener(new View.OnFocusChangeListener(){
            public void onFocusChange(View v, boolean hasFocus){
                String StringPassword = mPassword.getText().toString();

                if(!hasFocus){
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }

                if(TextUtils.isEmpty(StringPassword) && hasFocus)
                    findViewById(R.id.password).setBackgroundResource(R.drawable.rounded_textview);
                else if(TextUtils.isEmpty(StringPassword) && !hasFocus)
                    findViewById(R.id.password).setBackgroundResource(R.drawable.rounded_textview_alt);
            }
        });

        CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));

        mLoginButton.setOnClickListener(new View.OnClickListener(){
            String theStringId = mId.getText().toString();
            String theStringPassword = mPassword.getText().toString();
            public void onClick(View v){
                Log.i("Volley", "Sending Request");
                mStringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>(){
                    public void onResponse(String response){
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    }
                }
                , new Response.ErrorListener(){
                    public void onErrorResponse(VolleyError error){
                        Log.d("JSON error", error.toString());
                    }
                }){
                    protected Map<String, String> getParams() throws AuthFailureError{
                        HashMap<String, String> mHashMap = new HashMap<>();
                        mHashMap.put("sid", mId.getText().toString());
                        mHashMap.put("pin", mPassword.getText().toString());
                        return mHashMap;
                    }
                };
                mRequestQueue.add(mStringRequest);
            }
        });
    }
}
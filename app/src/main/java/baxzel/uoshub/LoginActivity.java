package baxzel.uoshub;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity{
    private EditText mId, mPassword;
    private Button mLoginButton;
    public static RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    String URL = Declutterer.URLHolder("Login");
    Context mContext;
    private SharedPreferences loginPreferences;
    private SharedPreferences.Editor loginPrefsEditor;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mId = (EditText) findViewById(R.id.student_id);
        mPassword = (EditText) findViewById(R.id.password);
        mLoginButton = (Button) findViewById(R.id.login_button);
        mContext = getApplicationContext();
        loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        loginPrefsEditor = loginPreferences.edit();

        mId.setText(loginPreferences.getString("username", ""));
        mPassword.setText(loginPreferences.getString("password", ""));

        if(mRequestQueue == null)
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

        Declutterer.Cookier();

        mLoginButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                String theStringId = mId.getText().toString();
                String theStringPassword = mPassword.getText().toString();
                String errors = "";
                boolean sendRequestU = false;
                boolean sendRequestP = false;

                loginPrefsEditor.putString("username", theStringId);
                loginPrefsEditor.putString("password", theStringPassword);
                loginPrefsEditor.commit();

                if(theStringId.startsWith("u") || theStringId.startsWith("U")){
                    if(theStringId.length() == 9){
                        boolean allNums = true;
                        for(int i = 1; i < 9; i++)
                            if(!Character.isDigit(theStringId.charAt(i))){
                                allNums = false;
                                break;
                            }
                        if(allNums)
                            sendRequestU = true;
                        else
                            errors += "ID must have u followed by 8 digits\n";
                    } else
                        errors += "ID must be 9 character long\n";
                } else
                    errors += "ID must start with \'u\'\n";

                if(theStringPassword.length() > 0)
                    sendRequestP = true;
                else
                    errors += "Password cannot be null\n";

                if(!errors.equals(""))
                    Toast.makeText(mContext, errors.substring(0,errors.length()-1), Toast.LENGTH_LONG).show();

                if(sendRequestU && sendRequestP){
                    Log.i("Volley", "Sending Request");

                mStringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>(){
                    public void onResponse(String response) {
                        Log.i("response 1", response);
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    }
                }, new Response.ErrorListener(){
                    public void onErrorResponse(VolleyError error){
                        Log.d("JSON error", error.toString() + "");
                        if(error.toString().contains("Unable to resolve host"))
                            Toast.makeText(mContext, "You must be connected to the Internet", Toast.LENGTH_LONG).show();
                        if(error.toString().contains("com.android.volley.ServerError"))
                            Toast.makeText(mContext, "Wrong ID and Password combination", Toast.LENGTH_LONG).show();
                        if(error.toString().contains("com.android.volley.TimeoutError"))
                            Toast.makeText(mContext, "Timeout error; Please try again", Toast.LENGTH_LONG).show();
                    }
                }) {
                    protected Map<String, String> getParams() throws AuthFailureError{
                        HashMap<String, String> mHashMap = new HashMap<>();
                        mHashMap.put("sid", mId.getText().toString());
                        mHashMap.put("pin", mPassword.getText().toString());
                        return mHashMap;
                    }
                };
                mRequestQueue.add(mStringRequest);
                }
            }
        });
    }
}
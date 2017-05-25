package pkru.sarak.jeerapong.mypkru;

import android.content.Intent;
import android.graphics.Canvas;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    //Explicit
    private EditText userEditText, passwordEditText;
    private TextView textView;
    private Button button;
    private String userString, passwordString;
    private String[] loginStrings;
    private String[] columnStrings = new String[]{"id", "Name", "User", "Password", "Image"};
    private boolean aBoolean = true;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initial Viwe
        initialViwe();

        // Controller
        controller();

    } // Main Method

    private void controller() {
        textView.setOnClickListener(this);
        button.setOnClickListener(this);
    }

    private void initialViwe() {
        userEditText = (EditText) findViewById(R.id.edtUser);
        passwordEditText = (EditText) findViewById(R.id.edtPassword);
        textView = (TextView) findViewById(R.id.txtNewReginter);
        button = (Button) findViewById(R.id.btnLogin);

    }


    @Override
    public void onClick(View v) {
        //For VTextViwe
        if (v == textView) {
            //Intent to NewRegister
            Intent intent = new Intent(MainActivity.this, NewRegisterActivity.class);
            startActivity(intent);
        }

        //For Button
        if (v == button){
            userString = userEditText.getText().toString().trim();
            passwordString = passwordEditText.getText().toString().trim();
            //Have Space
            if (userString.equals("") || passwordString.equals("")) {
                MyAlert myAlert = new MyAlert(this);
                myAlert.myDialog(getResources().getString(R.string.titleHaveSpace),
                        getResources().getString(R.string.messageHaveSpace));
            } else {
                //No Space
                checkUserAnPass();
            }
        }
    }

    private void checkUserAnPass() {

        String urlPHP = "http://swiftcodingthai.com/pkru/GetUserMorn.php";

        try {

            GetAlldata getAlldata = new GetAlldata(this);
            getAlldata.execute(urlPHP);

            String strJSON = getAlldata.get();
            Log.d("25MayV1", "e JSON ==> " + strJSON);

            JSONArray jsonArray = new JSONArray(strJSON);
            loginStrings = new String[columnStrings.length];

            for (int i=0;i<jsonArray.length();i++) {

                JSONObject jsonObject = jsonArray.getJSONObject(i);
                if (userString.equals(jsonObject.getString(columnStrings[2]))) {
                    aBoolean = false;
                    for (int i1=0;i1<columnStrings.length;i++) {
                        loginStrings[i1] = jsonObject.getString(columnStrings[i1]);
                        Log.d("25MayV2", "loginString(" + i1 + ")==>" + loginStrings[i1]);

                    }   //For 2
                }   //If

            }   // for1

            //Check User And Password
            if (aBoolean) {
                //User False
                MyAlert myAlert = new MyAlert(this);
                myAlert.myDialog(getResources().getString(R.string.titleFalse),
                        getResources().getString(R.string.messageFalse));
            } else if (passwordString.equals(loginStrings[3])) {
                // Password True
                Toast.makeText(MainActivity.this,
                        "Welcom "+ loginStrings[1], Toast.LENGTH_SHORT).show();
            } else {
                // Password False
                MyAlert myAlert = new MyAlert(this);
                myAlert.myDialog(getResources().getString(R.string.titleFalse),
                        getResources().getString(R.string.messageFalse));
            }

        } catch (Exception e) {
            Log.d("25MayV1", "e checkUser ==> " + e.toString());
        }


    }
} // Main Class

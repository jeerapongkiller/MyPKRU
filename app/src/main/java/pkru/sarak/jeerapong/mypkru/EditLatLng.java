package pkru.sarak.jeerapong.mypkru;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

/**
 * Created by mjlab on 26/5/2560.
 */

public class EditLatLng extends AsyncTask<String, Void, String>{
    private Context context;

    public EditLatLng(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... params) {

        try {

            OkHttpClient okHttpClient = new OkHttpClient();
            RequestBody requestBody = new FormEncodingBuilder()
                    .add("isAdd", "Ture")
                    .add("id", params[0])
                    .add("Lat", params[1])
                    .add("Lng", params[2])
                    .build();
            Request.Builder builder = new Request.Builder();
            Request request = builder.url(params[3]).post(requestBody).build();
            Response response = okHttpClient.newCall(request).execute();
            return response.body().string();



        } catch (Exception e) {
            Log.d("226MayV2", "e doin ==> " + e.toString());
            return null;
        }


    }
}

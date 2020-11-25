package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;
import com.example.androidjokelibrary.JokeActivity;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;
import java.io.IOException;

public class JokeEndpointService extends AsyncTask<Context, Void, String> {
  private static MyApi myApiService = null;
  private Context context;
  private String JOKE_KEY = "joke_key";

  @Override
  protected String doInBackground(Context... params) {
    if(myApiService == null) {
      MyApi.Builder builder = new
          MyApi.Builder(AndroidHttp.newCompatibleTransport(),
          new AndroidJsonFactory(), null)
          .setRootUrl("http://10.0.2.2:8080/_ah/api/")
          .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
            @Override                         public void initialize(AbstractGoogleClientRequest<?>
                abstractGoogleClientRequest) throws IOException {
              abstractGoogleClientRequest.setDisableGZipContent(true);
            }
          });
      myApiService = builder.build();
    }

    context = params[0];


    try {
      return myApiService.sayJoke().execute().getData();
    } catch (IOException e) {
      return e.getMessage();
    }
  }

  @Override
  protected void onPostExecute(String result) {
        //Intent intent = new Intent(context, JokeActivity.class);
        //intent.putExtra(JOKE_KEY, result);
        //context.startActivity(intent);

    Toast.makeText(context, result, Toast.LENGTH_LONG).show();
  }
}

package com.example.electronic_grade_book_client_student;

import android.content.Intent;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.springframework.http.HttpAuthentication;
import org.springframework.http.HttpBasicAuthentication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {

    protected static final String TAG = MainActivity.class.getSimpleName();

    private EditText login;
    private EditText password;
    private Button singInButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        login =  findViewById(R.id.login);
        password =  findViewById(R.id.password);
        singInButton =  findViewById(R.id.signInButton);

        singInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new FetchSecuredResourceTask().execute();
            }
        });

    }

    private void validate(String userName, String userPassword){
        if(userName.equals("user")&&userPassword.equals("password")){
            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
            startActivity(intent);
        }else {
            System.out.println("blaaaaaaaaaaaaaaaaaaaad-------------");
        }
    }


    private void displayResponse(Message response) {
        Toast.makeText(this, response.getText(), Toast.LENGTH_LONG).show();
    }

    // ***************************************
    // Private classes
    // ***************************************
    private class FetchSecuredResourceTask extends AsyncTask<Void, Void, Message> {

        private String username;

        private String password;

        @Override
        protected void onPreExecute() {
//            showLoadingProgressDialog();

            // build the message object
            EditText editText = (EditText) findViewById(R.id.login);
            this.username = editText.getText().toString();

            editText = (EditText) findViewById(R.id.password);
            this.password = editText.getText().toString();
        }

        @Override
        protected Message doInBackground(Void... params) {
            final String url = getString(R.string.URL )+"/student";

            // Populate the HTTP Basic Authentitcation header with the username and password
            HttpAuthentication authHeader = new HttpBasicAuthentication(username, password);
            System.out.println(username+": user");
            System.out.println(password+": pass");
            HttpHeaders requestHeaders = new HttpHeaders();

            requestHeaders.setAuthorization(authHeader);
            requestHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

            // Create a new RestTemplate instance
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

            try {
                // Make the network request
                Log.d(TAG, url);
                ResponseEntity<Student> response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(requestHeaders), Student.class);
//                ResponseEntity<Object[]> responseEntity = restTemplate.getForEntity(url, Object[].class);
//                Object[] objects = responseEntity.getBody();
//                MediaType contentType = responseEntity.getHeaders().getContentType();
//                HttpStatus statusCode = responseEntity.getStatusCode();
//
//                for (Object student: objects)
//                System.out.println(student.toString());

                System.out.println(response.toString());

                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
//                return response.getBody();
                return new Message();
            } catch (HttpClientErrorException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new Message(0, e.getStatusText(), e.getLocalizedMessage());
            } catch (ResourceAccessException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
                return new Message(0, e.getClass().getSimpleName(), e.getLocalizedMessage());
            }
        }

        @Override
        protected void onPostExecute(Message result) {
//            dismissProgressDialog();
            displayResponse(result);
        }

    }



}

package com.example.electronic_grade_book_client_student;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.electronic_grade_book_client_student.config.ConfigClass;
import com.example.electronic_grade_book_client_student.model.Student;
import org.springframework.http.HttpAuthentication;
import org.springframework.http.HttpBasicAuthentication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import java.util.Collections;

public class LoginActivity extends AppCompatActivity {

    private Button singInButton;
    private TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        singInButton = findViewById(R.id.signInButton);

        singInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new FetchSecuredResourceTask().execute();
            }
        });
    }

    private void displayResponse(String response) {
        Toast.makeText(this, response, Toast.LENGTH_LONG).show();
    }

    private class FetchSecuredResourceTask extends AsyncTask<Void, Void, String> {

        private String username;
        private String password;

        @Override
        protected void onPreExecute() {

            EditText editText = findViewById(R.id.login);
            this.username = editText.getText().toString();
            editText = findViewById(R.id.password);
            this.password = editText.getText().toString();
        }

        @Override
        protected String doInBackground(Void... params) {
            final String url = getString(R.string.URL) + "/student";

            HttpAuthentication authHeader = new HttpBasicAuthentication(username, password);
            HttpHeaders requestHeaders = new HttpHeaders();

            ConfigClass.setUser(username);
            ConfigClass.setPassword(password);

            requestHeaders.setAuthorization(authHeader);
            requestHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

            System.out.println("auth: "+ authHeader);
            System.out.println("request: "+requestHeaders);

            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

            if(this.username.equals("")){
                return "Pole login nie może być puste";
            }
            if(this.password.equals("")){
                return "Hasło nie może być puste";
            }

            try {
                restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(requestHeaders), Student[].class);
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                return "Zalogowano";
            } catch (HttpClientErrorException e) {
                return "Niepoprawny login lub hasło";
            } catch (Exception e) {
                return "Nieoczekiwany błąd";
            }
        }

        @Override
        protected void onPostExecute(String result) {
            displayResponse(result);
        }
    }
}

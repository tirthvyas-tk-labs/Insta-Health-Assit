package com.tklabs.instahealthassit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;



/*



Copyright 2020 Tirth Vyas

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.


*/





public class MainActivity extends AppCompatActivity {

    GoogleApiClient mGoogleApiClient;




    TextView userEmail;



    FirebaseUser user;

    Button button;
    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthListener;

    Button License;








    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


      setContentView(R.layout.activity_main);


      userEmail = findViewById(R.id.textView);




      License = findViewById(R.id.button_lic);

      License.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {

              Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/tirthvyas-tk-labs/Insta-Health-Assit/blob/master/LICENSE"));
              startActivity(browserIntent);

          }
      });





      mAuth = FirebaseAuth.getInstance();
      user = mAuth.getCurrentUser();

      userEmail.setText( user.getDisplayName());









        ImageView button1 = findViewById(R.id.button_up_med);



        Glide.with(this)
                .load(R.drawable.fire_upload_button)
                .into(button1);


        ImageView gifview = findViewById(R.id.the_header_gif);

        Glide.with(this)
                .load(R.drawable.the_ulti_gif)
                .into(gifview);



        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();



        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                       // Toast.makeText(g_authen.this, "Something went wrong !", Toast.LENGTH_SHORT).show();
                    }
                })
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();




        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent fire_up = new Intent(getApplicationContext(),fire_upload.class);
                startActivity(fire_up);
            }
        });




        gifview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Toast.makeText(MainActivity.this, user.getEmail() ,Toast.LENGTH_SHORT).show();

            }
        });








        button = (Button) findViewById(R.id.button7);
        mAuth = FirebaseAuth.getInstance();



        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                mAuth.signOut();

                Auth.GoogleSignInApi.signOut(mGoogleApiClient);







                Toast.makeText(MainActivity.this, "Successfully Signed Out !",Toast.LENGTH_SHORT).show();


                Intent fire_out = new Intent(getApplicationContext(),g_authen.class);
                startActivity(fire_out);



            }
        });












    }







    @Override
    public void onBackPressed(){
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);

        Toast.makeText(MainActivity.this, "Stay Healthy !", Toast.LENGTH_LONG).show();

    }




}

package p4n.in.regapp;
//created by p4n (pankaj chouhan)
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener{
    private Button buttonRegister;
    private EditText editTextEmail;
    private  EditText editTextPassword;
    private TextView textViewSignup;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseApp.initializeApp(this);
        firebaseAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);
        buttonRegister = (Button)findViewById(R.id.buttonSignup);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        textViewSignup =(TextView)findViewById(R.id.textview_signin);

        buttonRegister.setOnClickListener(this);
        textViewSignup.setOnClickListener(this);


    }

    private void registerUser(){

        String email = editTextEmail.getText().toString().trim();
        String password = editTextEmail.getText().toString().trim();

        if(TextUtils.isEmpty(email)){

                //email is empty
            Toast.makeText(this,"Plz enter the email",Toast.LENGTH_LONG).show();;
            return;
        }
        if(TextUtils.isEmpty(password)){

            //email is empty
            Toast.makeText(this,"Plz enter the password",Toast.LENGTH_LONG).show();;
            return;
        }

        //if validations are ok
        // we will show the progress bar


        progressDialog.setMessage("Registering User...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            //user is successfully registered and logged in
                            // we will start the profile activity here
                           Toast.makeText(MainActivity.this,"Registered Successfully",Toast.LENGTH_LONG).show();
                           progressDialog.cancel();
                        }else{
                            Toast.makeText(MainActivity.this,"Could not Redister.Please try again",Toast.LENGTH_LONG).show();


                        }
                    }
                });
    }


    @Override
    public void onClick(View view) {

        if(view==buttonRegister){

            registerUser();
        }
        if(view==textViewSignup){

            //open activity

        }
    }
}

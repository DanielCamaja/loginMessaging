package firebaseconection.com.firebaseconection;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;



public class Main2Activity extends AppCompatActivity {

    DatabaseReference myRef,refChat,refProfile;
    View layout;
    TextView profile_name,chatName, chatMessage, chatName_own, chatMessage_own;

    //AUTH FABEBOOK
    private FirebaseAuth mAuth;
    FirebaseUser currentUser;
    de.hdodenhof.circleimageview.CircleImageView profile_picture, mini_picture,  mini_picture_own;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();


        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        // write user data
        refProfile = database.getReference("Profile");
        refProfile.child(currentUser.getUid()).child("Displayname").setValue(currentUser.getDisplayName());
        refProfile.child(currentUser.getUid()).child("Email").setValue(currentUser.getEmail());
        refProfile.child(currentUser.getUid()).child("PhotoUrl").setValue(currentUser.getPhotoUrl().toString());
        refProfile.child(currentUser.getUid()).child("PhoneNumber").setValue(currentUser.getPhoneNumber());
    }

    public void salir(View view) {
        FirebaseAuth.getInstance().signOut();
        AccessToken.setCurrentAccessToken(null);
        Main2Activity.this.startActivity(new Intent(Main2Activity.this,MainActivity.class));

    }
}

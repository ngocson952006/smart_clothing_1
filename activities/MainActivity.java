package com.example.ngocsonit.smartclothing.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ngocsonit.smartclothing.R;
import com.example.ngocsonit.smartclothing.notifications.dialog.DialogAction;
import com.example.ngocsonit.smartclothing.notifications.dialog.DialogFactory;
import com.example.ngocsonit.smartclothing.ui.custom_fragments.SlidingBarColorTabsFragment;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, GoogleApiClient.OnConnectionFailedListener {
    // TAG
    private static final String TAG = MainActivity.class.getSimpleName();
    // list request codes
    private static final int SIGN_IN_REQUEST_CODE = 1;
    public static final int PICK_IMAGE_FROM_GALLERY_REQUEST_CODE = 2;

    private GoogleApiClient googleApiClient; // GoogleApiClient to use Google Services : Sign in , Location , Cloud ...

    // UI View components

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // floating action button
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                AlertDialog alertDialog = DialogFactory.newInstance(MainActivity.this, DialogAction.NEW_ITEM_CHOOSER);
                alertDialog.show();
            }

        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // START [Sign in config]
        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        // END [Sign in config]

        // build GoogleApiClient with access to the Google Sign-In API and the
        // options specified by googleSigninOptions.
        this.googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* Fragment Activity */, this /*OnConnectionFailedListener*/)
                .addApi(Auth.GOOGLE_SIGN_IN_API, googleSignInOptions)
                .build();


        // BEGIN_INCLUDE (setup_tabs)
        if (savedInstanceState == null) {
            android.support.v4.app.FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
            SlidingBarColorTabsFragment slidingBarColorTabsFragment = new SlidingBarColorTabsFragment();
            fragmentTransaction.replace(R.id.clothes_manage_content_fragment, slidingBarColorTabsFragment);
            fragmentTransaction.commit();

        }
        // BEGIN_INCLUDE (setup_tabs)
    }


    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        return super.onCreateView(name, context, attrs);
    }

    @Override
    protected void onStart() {
        super.onStart();
        this.googleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        this.googleApiClient
                .disconnect();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        } else if (id == R.id.nav_sign_in_sign_out) {
            this.signIn();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // make sure we have valid arguments
        if (resultCode == RESULT_OK && data != null) {
            switch (requestCode) {
                case SIGN_IN_REQUEST_CODE:
                    // get result from data
                    GoogleSignInResult googleSignInResult = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
                    updateNavigationUserSignIn(googleSignInResult);
                    break;
            }
        }
    }

    /**
     * method : start a google sign in task
     * for user sign in their google account
     */
    private void signIn() {
        // make sure that Google Api Client is valid
        if (this.googleApiClient == null) {
            throw new NullPointerException("Google Api Client is not built.[Error : Null GoogleApiClient]");
        }
        Intent googleSignIntent = Auth.GoogleSignInApi.getSignInIntent(this.googleApiClient);
        this.startActivityForResult(googleSignIntent, SIGN_IN_REQUEST_CODE);
    }

    private void updateNavigationUserSignIn(GoogleSignInResult googleSignInResult) {
        Log.d(TAG, "Update navigation user sign in result. [Result Success State ] = " + googleSignInResult.isSuccess()); // show debug result

        final ImageView userIconNavigationImageView = (ImageView) this.findViewById(R.id.imageViewUserProfile);
        final TextView userNameNavigationTextView = (TextView) this.findViewById(R.id.textViewUserName);
        final TextView userEmailAddressNavigationTextView = (TextView) this.findViewById(R.id.textViewUseEmailAddress);
        // in case , user signed in unsuccessfully
        if (!googleSignInResult.isSuccess()) {
            userIconNavigationImageView.setImageResource(R.drawable.ic_account_box_24dp);
            userNameNavigationTextView.setText(R.string.default_sign_out_information);
            userEmailAddressNavigationTextView.setText(R.string.default_sign_out_information);
        } else { // otherwise
            // to be coding here ....
        }

        // set value
    }
}

package edu.upc.eetac.dsa.apartmentshare;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import edu.upc.eetac.dsa.apartmentshare.client.ApartmentshareClient;

public class RegisterActivity extends AppCompatActivity {


    /**
     * Id to identity READ_CONTACTS permission request.
     */

    /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     */
    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "foo@example.com:hello", "bar@example.com:world"
    };
    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    private UserLoginTask mAuthTask = null;

    // UI references.
    private AutoCompleteTextView mUsername_registerView;
    private AutoCompleteTextView mFullname_registerView;
    private AutoCompleteTextView mEmail_registerView;
    private AutoCompleteTextView mPhone_registerView;
    private EditText mPassword_registerView;
    private View mProgressView;
    private View mLoginFormView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        // Set up the login form.
        mUsername_registerView = (AutoCompleteTextView) findViewById(R.id.username_register);
        mFullname_registerView = (AutoCompleteTextView) findViewById(R.id.fullname_register);
        mEmail_registerView = (AutoCompleteTextView) findViewById(R.id.email_register);
        mPhone_registerView = (AutoCompleteTextView) findViewById(R.id.phone_register);

        mPassword_registerView = (EditText) findViewById(R.id.password_register);
        mPassword_registerView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button_register);
        mEmailSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        mLoginFormView = findViewById(R.id.login_form_register);
        mProgressView = findViewById(R.id.login_progress_register);
    }


    /**
     * Callback received when a permissions request has been completed.


     /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {
        if (mAuthTask != null) {
            return;
        }

        // Reset errors.
        mUsername_registerView.setError(null);
        mPassword_registerView.setError(null);

        // Store values at the time of the login attempt.
        String username = mUsername_registerView.getText().toString();
        String password = mPassword_registerView.getText().toString();
        String fullname = mFullname_registerView.getText().toString();
        String email = mEmail_registerView.getText().toString();
        String phone = mPhone_registerView.getText().toString();


        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPassword_registerView.setError(getString(R.string.error_invalid_password));
            focusView = mPassword_registerView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(username)) {
            mUsername_registerView.setError(getString(R.string.error_field_required));
            focusView = mUsername_registerView;
            cancel = true;
        } else if (!isUsernameValid(username)) {
            mUsername_registerView.setError(getString(R.string.error_invalid_email));
            focusView = mUsername_registerView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);
            mAuthTask = new UserLoginTask(username, password, fullname, email, phone);
            mAuthTask.execute((Void) null);
        }
    }

    private boolean isUsernameValid(String username) {
        //TODO: Replace this with your own logic
        //return username.contains();
        return true;

    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        //return password.length() > 4;
        return true;
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }


    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        private final String mUsername;
        private final String mPassword;
        private final String mFullname;
        private final String mEmail;
        private final String mPhone;

        UserLoginTask(String username, String password, String fullname, String email, String phone) {
            mUsername = username;
            mPassword = password;
            mFullname= fullname;
            mEmail= email;
            mPhone=phone;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            ApartmentshareClient client = ApartmentshareClient.getInstance();
            boolean result = client.register(mUsername, mPassword, mFullname, mEmail, mPhone);
            // TODO: register the new account here.
            return result;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;
            showProgress(false);

            if (success) {
                startActivity(new Intent(RegisterActivity.this,HomeActivity.class));
//               finish();
            } else {

                mPassword_registerView.setError(getString(R.string.error_incorrect_password));
                mPassword_registerView.requestFocus();
//
//                mUsername.setError(getString(R.string.error_incorrect_password));
//                mUsername.requestFocus();
            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
            showProgress(false);
        }
    }
}

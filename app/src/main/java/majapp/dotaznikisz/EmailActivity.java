package majapp.dotaznikisz;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import static android.widget.Toast.LENGTH_LONG;

public class EmailActivity extends AppCompatActivity {

    private String email = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);

        Intent intent = getIntent();
        PrefillEmailText(intent);
    }

    private void PrefillEmailText(Intent intent)
    {
        EditText emailMessageEditText = findViewById(R.id.emailMessageEditText);
        String name = intent.getStringExtra("name");
        String surname = intent.getStringExtra(MainActivity.SURNAME_KEY);
        email = intent.getStringExtra(MainActivity.EMAIL_KEY);

        emailMessageEditText.setText("Meno: " + name + "\nPriezvisko: " + surname + "\n");
        emailMessageEditText.setSelection(emailMessageEditText.getText().length());
    }

    public void sendEmailButton_Click(View view)
    {
        if(email != null)
        {
            SendEmail();
        }
    }

    private void SendEmail()
    {
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setTitle("Prebieha odosielanie emailu");
        dialog.setMessage("Prosím počkajte");
        dialog.show();

        final EditText emailEditText = findViewById(R.id.emailMessageEditText);

        Context context = this;
        CharSequence text = "Boli zadané nesprávne emailové údaje!";
        int duration = LENGTH_LONG;
        final Toast toast = Toast.makeText(context, text, duration);

        Thread sender = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    EmailSender sender = new EmailSender();

                    sender.sendMail("Subjekt emailovej správy",
                            emailEditText.getText().toString(),
                            "ehealttp4@gmail.com",
                            email);
                    dialog.dismiss();
                } catch (javax.mail.AuthenticationFailedException e) {
                    Log.e("mylog", "Error: " + e.getMessage());
                    dialog.dismiss();
                    toast.show();
                }
                catch (Exception e) {
                    Log.e("mylog", "Error: " + e.getMessage());
                    dialog.dismiss();
                }
            }
        });
        sender.start();
    }

    private void SendEmailImplicit()
    {
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"motii131@gmail.com"});
        i.putExtra(Intent.EXTRA_SUBJECT, "subject of email");
        i.putExtra(Intent.EXTRA_TEXT   , "body of email");
        try {
            startActivity(Intent.createChooser(i, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }
    }
}

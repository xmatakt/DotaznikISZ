package majapp.dotaznikisz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class EmailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);

        Intent intent = getIntent();
        PrefillEmailText(intent);
    }

    private void PrefillEmailText(Intent intent)
    {
        EditText emailEditText = findViewById(R.id.emailMessageEditText);
        String name = intent.getStringExtra("name");
        String surname = intent.getStringExtra(MainActivity.SURNAME_KEY);
        String email = intent.getStringExtra(MainActivity.EMAIL_KEY);

        emailEditText.setText("Meno: " + name + "\nPriezvisko: " + surname + "\nOdpovede zasielať na: " + email + "\n");
        emailEditText.setSelection(emailEditText.getText().length());
    }

    public void sendEmailButton_Click(View view)
    {

    }
}

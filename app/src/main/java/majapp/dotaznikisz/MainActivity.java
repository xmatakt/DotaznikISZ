package majapp.dotaznikisz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public static String NAME_KEY = "name";
    public static String SURNAME_KEY = "surname";
    public static String EMAIL_KEY = "email";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SetViewsText();
    }

    private void SetViewsText()
    {
        EditText name = findViewById(R.id.nameEditText);
        name.setText(getString(R.string.emailHint));

        EditText surname = findViewById(R.id.surnameEditText);
        surname.setText("Matak");
    }

    public void nextButton_Click(View view)
    {
        Intent emailIntent = new Intent(this, EmailActivity.class);
        emailIntent.putExtra(NAME_KEY, ((EditText)findViewById(R.id.nameEditText)).getText().toString());
        emailIntent.putExtra(SURNAME_KEY, ((EditText)findViewById(R.id.surnameEditText)).getText().toString());
        emailIntent.putExtra(EMAIL_KEY, ((EditText)findViewById(R.id.emailEditText)).getText().toString());
        this.startActivity(emailIntent);
    }
}

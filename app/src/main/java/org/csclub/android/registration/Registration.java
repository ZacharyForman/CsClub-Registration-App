package org.csclub.android.registration;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Allow us to make registrations easily.
 */
public class Registration extends Activity {

  private static final String TAG = "Registration";

  private EditText nameField;
  private EditText emailField;
  private EditText studentIdField;
  private EditText memberField;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_registration);

    nameField = (EditText) findViewById(R.id.name_field);
    emailField = (EditText) findViewById(R.id.email_field);
    studentIdField = (EditText) findViewById(R.id.id_field);
    memberField = (EditText) findViewById(R.id.num_field);

    findViewById(R.id.but_submit).setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {
        submit();
      }

    });
  }

  /**
   * Adds current data to the csv.
   */
  private void submit() {
    String record = makeRecord();
    nameField.getText().clear();
    emailField.getText().clear();
    studentIdField.getText().clear();
    memberField.getText().clear();
    try {
      File path = new File(Environment.getExternalStorageDirectory(), "members.csv");
      FileWriter fileWriter = new FileWriter(path, true);
      fileWriter.write(record);
      fileWriter.close();
      Toast.makeText(this, "Successfully added member", Toast.LENGTH_LONG).show();
    } catch (IOException exception) {
      Log.e(TAG, "Had a problem...", exception);
    }
  }

  /**
   * Constructs a record from the activity's fields.
   */
  private String makeRecord() {
    return String.format("%s,%s,%s,%s\n",
        nameField.getText(),
        emailField.getText(),
        studentIdField.getText(),
        memberField.getText());
  }

}

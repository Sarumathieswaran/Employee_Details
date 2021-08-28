package com.example.employee_details;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    EditText ed1, ed2, ed3, ed4;
    Button bt1;
    DatabaseReference databaseReference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseReference = FirebaseDatabase.getInstance().getReference("employee");

        ed1 = findViewById(R.id.ed1);
        ed2 = findViewById(R.id.ed2);
        ed3 = findViewById(R.id.ed3);
        ed4 = findViewById(R.id.ed4);
        bt1=findViewById(R.id.bt1);

        bt1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                AddEmployeeDetails();

               // Intent intent=new Intent(MainActivity.this,showEmployeeDetails.class);
               // startActivity(intent);


            }
        });
        if(ed1.length()==0
        )
        {
            ed1.setError("Enter employee name");
        }
        if(ed2.length()==0)
        {
            ed2.setError("Enter employee Address");
        }
        if(ed3.length()==0)
        {
            ed3.setError("Enter gender");
        }
        if(ed4.length()==0)
        {
            ed4.setError("join date");
        }

    }


    private void AddEmployeeDetails () {
        String name = ed1.getText().toString();
        String address = ed2.getText().toString();
        String gender = ed3.getText().toString();
        String joinDate = ed4.getText().toString();
        String id=databaseReference.push().getKey();
        employee employee = new employee(id,name, address, gender, joinDate);
        assert id!=null;
        databaseReference.child(id).setValue(employee);
        Toast.makeText(this, "added", Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(MainActivity.this,showEmployeeDetails.class);
        startActivity(intent);



    }
}

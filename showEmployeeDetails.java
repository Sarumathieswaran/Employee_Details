package com.example.employee_details;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.List;

public class showEmployeeDetails extends AppCompatActivity {
    private ListView Listview;
    List<employee> employeelist;


    DatabaseReference databaseReference;
    //FirebaseDatabase firebaseDatabase;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_employee_details);
        Listview=findViewById(R.id.Listview);

        employeelist =new ArrayList<>();

        databaseReference=FirebaseDatabase.getInstance().getReference("employee");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                employeelist.clear();

                for (DataSnapshot employeeDataSnap : dataSnapshot.getChildren())
                {

                    employee employee = employeeDataSnap.getValue(employee.class);
                    //System.out.println("Employe Name"+employee.name);
                    employeelist.add(employee);
                }
                EmployeeAdapter adapter=new EmployeeAdapter(showEmployeeDetails.this,employeelist);
                Listview.setAdapter(adapter);
            }
            @Override

            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        Listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                employee employee = employeelist.get(position);
                showUpdateDialog(employee.getId(),employee.getName());

                return false;
            }
        });
    }

    private void showUpdateDialog(final String id, String name){

        final AlertDialog.Builder mDialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View mDialogView = inflater.inflate(R.layout.update_dialog, null);

        mDialog.setView(mDialogView);

        //create views refernces
        final EditText ed1 = mDialogView.findViewById(R.id.ed1);
        final EditText ed2= mDialogView.findViewById(R.id.ed2);
        final EditText ed3= mDialogView.findViewById(R.id.ed3);
        final EditText ed4= mDialogView.findViewById(R.id.ed4);

        Button bt1 = mDialogView.findViewById(R.id.bt1);
        Button bt2 = mDialogView.findViewById(R.id.bt2);

        mDialog.setTitle("Updating " + name +" record");

        final AlertDialog alertDialog = mDialog.create();
        alertDialog.show();

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String newName = ed1.getText().toString();
                String newAddress = ed2.getText().toString();
                String newGender= ed3.getText().toString();
                String newJoinDate= ed4.getText().toString();

                updateData(id,newName,newAddress,newGender,newJoinDate);

                Toast.makeText(showEmployeeDetails.this, "Record Updated", Toast.LENGTH_SHORT).show();
                alertDialog.dismiss();
            }

        });

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteRecord(id);

                alertDialog.dismiss();
            }
        });
    }


    private void showToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void deleteRecord(String id){

        DatabaseReference DbRef = FirebaseDatabase.getInstance().getReference("employee").child(id);


        Task<Void> mTask = DbRef.removeValue();
        mTask.addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                showToast("Deleted");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                showToast("Error deleting record");
            }
        });
    }

    private void updateData(String id,String name, String address, String gender,String joinDate){


        DatabaseReference DbRef = FirebaseDatabase.getInstance().getReference("employee").child(id);
        employee employee= new employee(id,name, address,gender,joinDate);
        DbRef.setValue(employee);
    }

}

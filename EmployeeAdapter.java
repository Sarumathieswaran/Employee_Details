package com.example.employee_details;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import androidx.annotation.NonNull;

import com.google.firebase.database.annotations.Nullable;

import java.util.List;


public class EmployeeAdapter  extends ArrayAdapter <employee>
{
       private Activity mContext;
       List<employee> employeelist;

       public EmployeeAdapter(Activity mContext, List<employee> employeelist)
       {
           super(mContext,R.layout.activity_employee_design,employeelist);
           this.mContext=mContext;
           this.employeelist=employeelist;

       }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        LayoutInflater inflater=mContext.getLayoutInflater();

        View listItemView=inflater.inflate(R.layout.activity_employee_design,null,true);

        TextView name=listItemView.findViewById(R.id.name);

        TextView address=listItemView.findViewById(R.id.address);

        TextView gender=listItemView.findViewById(R.id.gender);

        TextView joinDate=listItemView.findViewById(R.id.joinDate);


           employee employee=employeelist.get(position);

           name.setText(employee.getName());
           address.setText(employee.getAddress());
           gender.setText(employee.getGender());
           joinDate.setText(employee.getJoinDate());
           return listItemView;
    }


}














































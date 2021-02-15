package com.example.admin.navidemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class UserAdapter extends  RecyclerView.Adapter<UserAdapter.ViewHolder>{
    private List<Usser>list_data;
    String num,mob;

    public UserAdapter(List<Usser> list_data) {
        this.list_data = list_data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_itemm,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Usser listData=list_data.get(position);
        holder.txtname.setText(listData.getName());
        holder.txtmobile.setText(listData.getMobile());
        holder.txttimeslot.setText(listData.getTimeslot());
        holder.txtadate.setText(listData.getAppointmentdate());
        holder.txttype.setText(listData.getApptype());


        holder.b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nm=holder.txtname.getText().toString();
                String nme=holder.txtmobile.getText().toString();
                try {


                    SmsManager sm = SmsManager.getDefault();
                    sm.sendTextMessage(nme,null,"Dear"+nm+" Appointment has benn accepted",null,null);
                    //PendingIntent pi=PendingIntent.getActivity(context,0,Intent,0);
                    // sm.sendTextMessage(ac,null,"accepted",pi,null);
                    Toast.makeText(view.getContext(), " Successful  ", Toast.LENGTH_SHORT).show();
                }
                catch (Exception t)
                {
                    Toast.makeText(view.getContext(), "Unsuccessful", Toast.LENGTH_SHORT).show();
                }

            }


        });
        holder.b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nm=holder.txtname.getText().toString();
                String nme=holder.txtmobile.getText().toString();

                try {


                    SmsManager sm = SmsManager.getDefault();
                    sm.sendTextMessage(nme,null,"Dear"+ nm+" Appointment have not accepted",null,null);
                    //PendingIntent pi=PendingIntent.getActivity(context,0,Intent,0);
                    // sm.sendTextMessage(ac,null,"accepted",pi,null);
                    Toast.makeText(view.getContext(), " Successful  ", Toast.LENGTH_SHORT).show();
                }
                catch (Exception t)
                {
                    Toast.makeText(view.getContext(), "Unsuccessful", Toast.LENGTH_SHORT).show();
                }

            }

        });
    }

    @Override
    public int getItemCount() {
        return list_data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txtname,txtmobile,txttimeslot,txtadate,txttype;
        Button b1, b2,b3;
        String s_mobile,s_name;
        public ViewHolder(final View itemView) {
            super(itemView);
            txtname=(TextView)itemView.findViewById(R.id.txt_name);
            txtmobile=(TextView)itemView.findViewById(R.id.txt_moviename);
            txttimeslot=(TextView)itemView.findViewById(R.id.txt_timeslot);
            txtadate=(TextView)itemView.findViewById(R.id.txt_appointmentdate);
            txttype=(TextView)itemView.findViewById(R.id.txt_atype);
              b1=(Button)itemView.findViewById(R.id.sb1);
              b2 = (Button)itemView.findViewById(R.id.sb2);
              b3 = (Button)itemView.findViewById(R.id.sb3);
            s_mobile=txtmobile.getText().toString().trim();
            s_name=txtname.getText().toString().trim();


            

        }
    }
    public void sendSms()
    {


        try {


            SmsManager sm = SmsManager.getDefault();
            sm.sendTextMessage("8079078027",null,"Dear Customer Appointment has benn accepted",null,null);
            //PendingIntent pi=PendingIntent.getActivity(context,0,Intent,0);
            // sm.sendTextMessage(ac,null,"accepted",pi,null);

        }
        catch (Exception t)
        {

        }

    }


}
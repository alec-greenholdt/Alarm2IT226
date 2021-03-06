import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.app.AlarmManager;
import android.app.PendingIntent;
public class OneTimeActivity extends AppCompatActivity {

    private int days, hrs, mins;
    private String msg = "";
    private String location = "";
    private AlarmManager alarm_manager;
    private PendingIntent pending_intent;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_time);
        alarm_manager = (AlarmManager) getSystemService(ALARM_SERVICE);
        this.context = this;
    }


    public void btnSetOnClicked(View view){
        EditText daysText = (EditText) findViewById(R.id.txtDays);
        EditText hoursText = (EditText) findViewById(R.id.txtHours);
        EditText minutesText = (EditText) findViewById(R.id.txtMinutes);
        EditText messageText = (EditText) findViewById(R.id.txtMessage);
        EditText locationText = (EditText) findViewById(R.id.txtLocation);
        if(daysText.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), getString(R.string.one_time_daysEmpty), Toast.LENGTH_LONG).show();
            return;
        }
        if(hoursText.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), getString(R.string.one_time_hrsEmpty), Toast.LENGTH_LONG).show();
            return;
        }
        if(minutesText.getText().toString().isEmpty()){
            Toast.makeText(getApplicationContext(), getString(R.string.one_time_minEmpty), Toast.LENGTH_LONG).show();
            return;
        }
        days = Integer.parseInt(daysText.getText().toString());
        hrs = Integer.parseInt(hoursText.getText().toString());
        mins = Integer.parseInt(minutesText.getText().toString());
        if(days==0 && hrs==0 && mins==0){
            Toast.makeText(getApplicationContext(), getString(R.string.one_time_minTime), Toast.LENGTH_LONG).show();
            return;
        }
        int addHours = 0;
        while(mins>59){
            addHours++;
            mins -= 60;
        }
        hrs += addHours;
        int addDays = 0;
        while(hrs>23){
            addDays++;
            hrs -= 24;
        }
        days += addDays;
        msg = messageText.getText().toString();
        location = locationText.getText().toString();
        if(msg.isEmpty()){
            msg = "Alarm triggered!";
        }
        int minuteMilli = mins * 60000;
        int hourMilli = hrs * 3600000;
        int dayMilli = days * 86400000;
        int totMilli = minuteMilli+hourMilli+dayMilli;
        Intent my_intent = new Intent(this, AlarmReceiver.class);
        my_intent.putExtra("MESSAGE", msg);
        my_intent.putExtra("LOCATION", location);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, my_intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarm_manager.setExact(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime()+totMilli, pendingIntent);
        finish();
        disMessage();
    }
}
package slidenerd.vivz.experiments;

import de.greenrobot.event.EventBus;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class TestEventBus extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test_event_bus);
		EventBus.getDefault().register(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.test_event_bus, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		EventBus.getDefault().unregister(this);
	}

	public void startTask(View view) {
		IdClockTask idClockTask = new IdClockTask();
		idClockTask.execute();
	}

	public void onEvent(DownloadedEvent downloadEvent) {
		Log.d("VIVZ", Thread.currentThread().getName());
		Toast.makeText(this, downloadEvent.getData(), Toast.LENGTH_LONG).show();
	}
}

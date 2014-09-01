package slidenerd.vivz.experiments;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import de.greenrobot.event.EventBus;
import android.os.AsyncTask;

public class IdClockTask extends AsyncTask<Void, Void, String> {

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
	}

	@Override
	protected String doInBackground(Void... params) {
		// TODO Auto-generated method stub
		String urlParameters = "port%5B%5D=all&protocol-http=true&anonymity-low=true&anonymity-medium=true&anonymity-high=true&connection-low=true&connection-medium=true&connection-high=true&speed-low=true&speed-medium=true&speed-high=true&order=desc&by=updated";
		String data = executePost(
				"http://www.idcloak.com/proxylist/proxy-list.html",
				urlParameters);
		return data;
	}

	public static String executePost(String targetURL, String urlParameters) {
		URL url;
		HttpURLConnection connection = null;
		try {
			// Create connection
			url = new URL(targetURL);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");

			connection.setRequestProperty("Content-Length",
					"" + Integer.toString(urlParameters.getBytes().length));
			connection.setRequestProperty("Content-Language", "en-US");
			connection
					.setRequestProperty(
							"User-Agent",
							"Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2");
			connection.setUseCaches(false);
			connection.setDoOutput(true);

			DataOutputStream wr = new DataOutputStream(
					connection.getOutputStream());
			wr.writeBytes(urlParameters);
			wr.flush();
			wr.close();
			connection.connect();
			System.out.println("response code " + connection.getResponseCode());
			// Get Response
			InputStream is = connection.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(is));
			String line;
			StringBuffer response = new StringBuffer();
			while ((line = rd.readLine()) != null) {
				response.append(line);
				response.append('\r');
			}
			rd.close();
			System.out.println(response);
			return response.toString();

		} catch (Exception e) {

			e.printStackTrace();
			return null;

		} finally {

			if (connection != null) {
				connection.disconnect();
			}
		}
	}

	@Override
	protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
		EventBus.getDefault().post(new DownloadedEvent(result));
		
	}
}

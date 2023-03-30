import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import org.json.*;

//useful dont delete
public class DBTest {

    public String makeGETRequest(String urlName){
        BufferedReader rd = null;
        StringBuilder sb = null;
        String line = null;
        try {
            URL url = new URL(urlName);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            sb = new StringBuilder();
            while ((line = rd.readLine()) != null)
            {
                sb.append(line + '\n');
            }
            conn.disconnect();
            return sb.toString();
        }
        catch (MalformedURLException e){
            e.printStackTrace();
        }
        catch (ProtocolException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return "";

    }

    public String[] parseJSON(String jsonString,int parameter){
        String [] list = new String[3];

        try {
            JSONArray array = new JSONArray(jsonString);
            for (int i = 0; i < array.length(); i++)
            {
                JSONObject curObject = array.getJSONObject(i);
                if(curObject.getInt("idcurrentValues")==parameter)
                {
                    list[0]= String.valueOf(curObject.getInt("idcurrentValues"));
                    list[1]= String.valueOf(curObject.getInt("newValue"));
                    list[2]=curObject.getString("units");
                }
                // System.out.println("The coach for the " + curObject.getString("Date") + " session is " + curObject.getString("Coach"));

            }
        }
        catch (JSONException e){
            e.printStackTrace();
        }
        return list;
    }


    public static void main(String[] args) {
        DBTest rc = new DBTest();
        String response = rc.makeGETRequest("https://studev.groept.be/api/a22ib2b06/allvalues" );
        System.out.println(response);
        rc.parseJSON(response,3);
}
}
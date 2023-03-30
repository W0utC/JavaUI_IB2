//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.time.LocalTime;
import java.util.LinkedList;
import java.time.LocalDate;
import java.util.Queue;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DB {
    private LinkedList<Double> hisTem; //historical temperature
    private LinkedList<Integer> hisCon; //historical counter
    private final int dequeMaxSize = 6;
    private double maxTem=27;
    private double minTem=8;
    private int maxPpl=20;
    private JSONArray array;
    private double lastTemp;
    private int lastCount;
    public DB() {
        hisCon= new LinkedList<>();
        hisTem= new LinkedList<>();
        lastTemp=0;
        lastCount=0;
    for(int i=0;i<dequeMaxSize;i++){
            hisTem.add((double) 0);
            hisCon.add(0);
        }
    }

    public  void storeTem(double temp){
        LocalTime now=LocalTime.now();
        if (isNtc()){
            //if(now.getSecond()==0){
                if(hisTem.size()>=dequeMaxSize){
                    hisTem.removeLast();
                }
                hisTem.addFirst(temp);
            //}
        }
    }

    public LinkedList<Double> getHisTem() {
        return hisTem;
    }

    public LinkedList<Integer> getHisCon() {
        return hisCon;
    }

    public void storeCon(int con){
        //LocalTime now=LocalTime.now();
        if(!isNtc()){
            //if(now.getSecond()==0) {
                if (hisCon.size() >= dequeMaxSize) {
                    hisCon.removeLast();
                }
                hisCon.addFirst(con);
            //}
        }
    }

    public double averageTem(){
        double total=0;
        double count=0;
        for(int i=0;i<dequeMaxSize;++i){
            if(hisTem.get(i)!=0.0)
            {total+= hisTem.get(i);
            count++;}
        }
        double avg = total/count;
        return avg;
    }

    public int getDequeMaxSize() {
        return dequeMaxSize;
    }

    public String makeGETRequest(String urlName) {
        BufferedReader rd = null;
        StringBuilder sb = null;
        String line = null;

        try {
            URL url = new URL(urlName);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            sb = new StringBuilder();

            while((line = rd.readLine()) != null) {
                sb.append(line + "\n");
            }

            conn.disconnect();
            return sb.toString();
        } catch (MalformedURLException var7) {
            var7.printStackTrace();
        } catch (ProtocolException var8) {
            var8.printStackTrace();
        } catch (IOException var9) {
            var9.printStackTrace();
        }

        return "";
    }

    public String[] parseJSON(String jsonString, int parameter) {
        String[] list = new String[4];

        try {
            JSONArray array = new JSONArray(jsonString);

            for(int i = 0; i < array.length(); ++i) {
                JSONObject curObject = array.getJSONObject(i);
                if (curObject.getInt("idcurrentValues") == parameter) {
                    list[0] = String.valueOf(curObject.getInt("idcurrentValues"));
                    list[1] = String.valueOf(curObject.getInt("newValue"));
                    list[2] = curObject.getString("units");
                    list[3] = curObject.getString("idSensor");
                }
            }
        } catch (JSONException var7) {
            var7.printStackTrace();
        }

        return list;
    }



    public void setLastTemp() {

            try {
                //JSONArray array = new JSONArray(this.makeGETRequest("https://studev.groept.be/api/a22ib2b06/getLastLineCurrentValues"));
                //if(isNtc()){
                JSONObject curObject = array.getJSONObject(0);
                 lastTemp=curObject.getDouble("newValue");
             }
                catch (JSONException var3) {
                var3.printStackTrace();
                //return 0.0;
            }

    }

    public double getLastTemp() {
        return lastTemp;
    }

    public int getLastCount() {
        return lastCount;
    }

    public void setLastCounter() {
            try {
                //JSONArray array = new JSONArray(this.makeGETRequest("https://studev.groept.be/api/a22ib2b06/getLastLineCurrentValues"));
                //if(isNtc()){
                    JSONObject curObject = array.getJSONObject(0);
                    lastCount=curObject.getInt("newValue");
                //}
               // return curObject.getInt("newValue");
            } catch (JSONException var3) {
                var3.printStackTrace();
                //return 0;
            }
    }

    public boolean isNtc() {
        try {
             array = new JSONArray(this.makeGETRequest("https://studev.groept.be/api/a22ib2b06/getLastLineCurrentValues"));
            JSONObject curObject = array.getJSONObject(0);
            return curObject.getString("idSensor").equals("ntc");
        } catch (JSONException var3) {
            var3.printStackTrace();
            return false;
        }
    }

//    public static void main(String[] args) throws InterruptedException {
//        DBTest rc = new DBTest();
//        String response = rc.makeGETRequest("https://studev.groept.be/api/a22ib2b06/getLastLineCurrentValues");
//        System.out.println(response);
//    }

    public char checkBound(){
        if(isNtc()){
        if(getLastTemp()>=maxTem){
            return 'h';
        }
        else if(getLastTemp()<=minTem){
            return  'l';
        }}
        if(!isNtc()){
         if(getLastCount()>maxPpl){
            return 'c';
        }}

            return 'n';
    }
}

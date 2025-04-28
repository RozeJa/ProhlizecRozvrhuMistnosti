package cz.uhk.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class URLLoader {

    private static URLLoader instance;

    public static URLLoader getInstace() {
        if (instance != null) {
            return instance;
        }

        instance = new URLLoader("https://stag-demo.uhk.cz/ws/services/rest2/", new GsonBuilder());
        return instance;
    }

    private URLLoader(String url, GsonBuilder builder) {
        this.url = url;
        this.builder = builder;
    }


    // ##################################################


    private String url;
    private GsonBuilder builder;

    public <O> O nactiDataZJsonZdroje(String endpoint, Class<O> clazz) {
        String json = nactiZdrojJakoText(endpoint);

        Gson gson = builder.create();
        O object = gson.fromJson(json, clazz);
        return object;
    }
 
    private InputStreamReader nactiZdroj(String endpoint) {
        URL location = null;
        try {
            location = new URL(url + endpoint);
        } catch (MalformedURLException e) {
            System.out.println(String.format("Chyba při vytváření URL: %s", url + endpoint));
            e.printStackTrace();
            return null;
        }

        InputStreamReader isr = null;
        try {
            URLConnection connection = location.openConnection();

            connection.connect();
            isr = new InputStreamReader(connection.getInputStream());           
        } catch (IOException e) {
            System.out.println("Chyba při komunikaci s endpointem");
            e.printStackTrace();
            return null;
        }

        return isr;
    }

    private String nactiZdrojJakoText(String endpoint) {

        try (BufferedReader reader = new BufferedReader(nactiZdroj(endpoint))) {
            String ln;
            StringBuilder sb = new StringBuilder();
            while ((ln = reader.readLine()) != null) {
                sb.append(ln);
            }
            
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}

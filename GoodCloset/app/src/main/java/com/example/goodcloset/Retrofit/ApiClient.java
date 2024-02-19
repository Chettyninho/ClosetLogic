package com.example.goodcloset.Retrofit;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    //gestiona retrofit
//patron singleton
    private static ApiClient instance = null;
    private static Retrofit retrofitClient = null;
    private static ApiService apiService = null;
    private ApiClient(){
        OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();

        OkHttpClient okHttpClient = okHttpBuilder.build();
        retrofitClient =  new Retrofit.Builder()
                .baseUrl("http://192.168.1.106:8086")//link de la url(seria el puerto de nuestro locahost o algo asi) que es tu ip
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        apiService =   retrofitClient.create(ApiService.class);
    }

//instancia del metodo de creacion de la apiClient
    static public ApiClient getInstance(){
        if(instance==null){
            instance = new ApiClient();
        }
        return instance;
    }

    public  ApiService getApiService(){
        return apiService;
    }


        // prueba para las fotos outft
       /* private static final String TAG = "ApiClient";

        public static List<YourImageModel> fetchImageListFromAPI(String urlString) {
            List<YourImageModel> imageList = new ArrayList<>();

            try {
                URL url = new URL(urlString);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");

                int responseCode = urlConnection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    String inputLine;
                    StringBuilder response = new StringBuilder();

                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();

                    JSONArray jsonArray = new JSONArray(response.toString());
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        byte[] fotoArriba = jsonObject.getString("foto_arriba").getBytes();
                        byte[] fotoMedio = jsonObject.getString("foto_medio").getBytes();
                        byte[] fotoAbajo = jsonObject.getString("foto_abajo").getBytes();

                        YourImageModel imageModel = new YourImageModel(fotoArriba, fotoMedio, fotoAbajo);
                        imageList.add(imageModel);
                    }
                } else {
                    Log.e(TAG, "Error en la respuesta de la API. Código de respuesta: " + responseCode);
                }
                urlConnection.disconnect();
            } catch (IOException | JSONException e) {
                Log.e(TAG, "Error al obtener la lista de imágenes desde la API: " + e.getMessage());
                e.printStackTrace();
            }

            return imageList;
        }*/
    }



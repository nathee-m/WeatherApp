package com.natalia.myapplication;

import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ListFragment extends Fragment {

    private static final String TAG = "ListFragment";

    DadosTempo dados;
    TextView cidade;
    TextView tempAgora;
    ImageView weatherImage;

    public ListFragment() {

    }

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.list_fragment, container, false);

        mRecyclerView = (RecyclerView) v.findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        cidade = v.findViewById(R.id.cityText);
        tempAgora = v.findViewById(R.id.tempNow);
        weatherImage = v.findViewById(R.id.imageNow);

        dados = new DadosTempo();

        Retrofit client = new Retrofit.Builder()
                .baseUrl("https://api.hgbrasil.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiTempo httpRequest = client.create(ApiTempo.class);
        Call<ApiPojo> call = httpRequest.getInfTempo();

        call.enqueue(callback);

        return v;
    }

    private Callback<ApiPojo> callback = new Callback<ApiPojo>() {
        @Override
        public void onResponse(Call<ApiPojo> call, Response<ApiPojo> response) {
            if (response.body() != null) {
                dados.setCidade(response.body().getResults().getCityName());
                dados.setTempAgora(String.valueOf(response.body().getResults().getTemp()));
                dados.setLista(response.body().getResults().getForecast());

                cidade.setText(dados.getCidade());
                tempAgora.setText(dados.getTempAgora() +"°");

                String currentCondition = response.body().getResults().getConditionSlug();
                int imageResource = getWeatherImage(currentCondition);
                dados.setImage(currentCondition);
                ImageView weatherImage = getView().findViewById(R.id.imageNow);
                weatherImage.setImageResource(imageResource);

                mAdapter = new ListAdapter(dados.getLista());
                mRecyclerView.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
            } else {
                Log.e(TAG, "Resposta vazia");
            }
        }

        private int getWeatherImage(String conditionSlug) {
            switch (conditionSlug) {
                case "storm": return R.drawable.storm;
                case "snow": return R.drawable.snow;
                case "hail": return R.drawable.hail;
                case "rain": return R.drawable.rain;
                case "fog": return R.drawable.fog;
                case "clear_day": return R.drawable.clear_day;
                case "clear_night": return R.drawable.clear_night;
                case "cloud": return R.drawable.cloud;
                case "cloudly_day": return R.drawable.cloudly_day;
                case "cloudly_night": return R.drawable.cloudly_night;
                default: return R.drawable.cloud;
            }
        }

        @Override
        public void onFailure(Call<ApiPojo> call, Throwable t) {
            Log.e(TAG, "Falha no Retrofit: " + t.toString());
        }
    };
}
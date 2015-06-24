package sokohuru.muchbeer.king.sokohuru.Fragment;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.text.ParseException;
import java.util.ArrayList;

import sokohuru.muchbeer.king.sokohuru.R;


import static sokohuru.muchbeer.king.sokohuru.extras.Keys.EndpointBoxOffice.*;


import sokohuru.muchbeer.king.sokohuru.Sokoni.Soko;
import sokohuru.muchbeer.king.sokohuru.loggin.L;
import sokohuru.muchbeer.king.sokohuru.network.VolleySingleton;


public class FragmentSoko extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    public static final String URL_SOKO = "http://sokouhuru.com/ccm/uchaguzi.json";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ImageLoader imageLoader;
    private RequestQueue requestQueue;
    private ArrayList<Soko> listMovies = new ArrayList<>();

    private RecyclerView listSokoni;
    private RecyclerView.LayoutManager sLayoutManager;

   // private OnFragmentInteractionListener mListener;
    private VolleySingleton volleySingleton;

    private AdapterSoko adapterSoko;
    private TextView mTextError;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentSoko.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentSoko newInstance(String param1, String param2) {
        FragmentSoko fragment = new FragmentSoko();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public FragmentSoko() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

        }

            volleySingleton = VolleySingleton.getsInstance();
            requestQueue = volleySingleton.getRequestQueue();

         // sendJsonRequest();


    }

    public void sendJsonRequest() {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,
                URL_SOKO,
                null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        //  parseJSONResponse(jsonObject);
                        // L.t(getActivity(), jsonObject.toString());
                        listMovies = parseJSONResponse(jsonObject);
                        adapterSoko.setSokoList(listMovies);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.e("Error: ", error.getMessage());
                        L.t(getActivity(),error.toString());
                        //if any error occurs in the network operations, show the TextView that contains the error message
                        mTextError.setVisibility(View.VISIBLE);
                        if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                            mTextError.setText(R.string.error_timeout);

                        } else if (error instanceof AuthFailureError) {
                            mTextError.setText(R.string.error_auth_failure);
                            //TODO
                        } else if (error instanceof ServerError) {
                            mTextError.setText(R.string.error_auth_failure);
                            //TODO
                        } else if (error instanceof NetworkError) {
                            mTextError.setText(R.string.error_network);
                            //TODO
                        } else if (error instanceof ParseError) {
                            mTextError.setText(R.string.error_parser);
                            //TODO
                        }
                    }
                });
        requestQueue.add(request);
    }
private ArrayList<Soko> parseJSONResponse(JSONObject response) {
    ArrayList<Soko> listMovies = new ArrayList<>();

      if (response == null || response.length() == 0) {
            L.t(getActivity(), "Refresh data");
    }
     else   if (response != null && response.length() > 0) {



    try {

        StringBuilder data = new StringBuilder();
        JSONArray arrayMoview = response.getJSONArray(KEY_SOKO);

        for (int i = 0; i < arrayMoview.length(); i++) {
            JSONObject currentMarket = arrayMoview.getJSONObject(i);
            String title = currentMarket.getString(KEY_TITLE);
            String imaging = currentMarket.getString(KEY_IMAGE);
           // int releaseYear = currentMarket.getInt(KEY_YEAR);
            String rating = currentMarket.getString(KEY_RATING);
            String genre = currentMarket.getString(KEY_GENRE);


            data.append(title + "\n");

            Soko sokoni = new Soko();
            // sokoni.setId(id);
            sokoni.setTitle(title);
            sokoni.setImage(imaging);
         //   sokoni.setRating(rating);
            sokoni.setGenre(genre);
          //  sokoni.setReleaseYear(releaseYear);

                  /*
                   Release year has someKind of problem try observe that has you move forward
                   sokoni.setReleaseYear(releaseYear);
                    */

            listMovies.add(sokoni);
        }


        // L.t(getActivity(), data.toString());

        L.T(getActivity(), listMovies.toString());

    } catch (JSONException e) {
        L.t(getActivity(), e.toString());
    }

  }

    return listMovies;
}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_soko, container, false);

        mTextError = (TextView) view.findViewById(R.id.textVolleyError);

        listSokoni = (RecyclerView) view.findViewById(R.id.listSokoni);
        listSokoni.setLayoutManager(new LinearLayoutManager(getActivity()));

        adapterSoko = new AdapterSoko(getActivity());
        listSokoni.setAdapter(adapterSoko);
        sendJsonRequest();
                return view;
    }






    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */


}

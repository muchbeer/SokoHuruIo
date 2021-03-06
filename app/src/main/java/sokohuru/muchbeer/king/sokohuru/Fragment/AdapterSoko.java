package sokohuru.muchbeer.king.sokohuru.Fragment;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;

import java.util.ArrayList;

import sokohuru.muchbeer.king.sokohuru.R;
import sokohuru.muchbeer.king.sokohuru.Sokoni.Soko;
import sokohuru.muchbeer.king.sokohuru.loggin.L;
import sokohuru.muchbeer.king.sokohuru.network.VolleySingleton;

/**
 * Created by muchbeer on 6/19/2015.
 */
public class AdapterSoko extends RecyclerView.Adapter<AdapterSoko.ViewHolderSokoni> {


    private final LayoutInflater layoutInflater;
    private final VolleySingleton volleySingleTon;
    private final ImageLoader imageLoader;

    private ArrayList<Soko> slistSokoni = new ArrayList<>();
    private Context context;

    public AdapterSoko(Context context) {
        layoutInflater = LayoutInflater.from(context);
        volleySingleTon = VolleySingleton.getsInstance();
        imageLoader = volleySingleTon.getImageLoader();

       // L.t(context, "message");
    }

    public void setSokoList(ArrayList<Soko> listSokoni) {
        this.slistSokoni = listSokoni;
        notifyItemRangeChanged(0, listSokoni.size());
    }

    @Override
    public AdapterSoko.ViewHolderSokoni onCreateViewHolder(ViewGroup parent, int viewType) {

       View view =  layoutInflater.inflate(R.layout.fragment_soko_lookfeel, parent, false);
        ViewHolderSokoni viewHolderSokoni = new ViewHolderSokoni(view);
        return viewHolderSokoni;
    }

    @Override
    public void onBindViewHolder(final ViewHolderSokoni holder, int position) {
        Soko currentItem = slistSokoni.get(position);

        holder.sokoTitle.setText(currentItem.getTitle());
        holder.sokoGenre.setText(currentItem.getGenre());

  }

    @Override
    public int getItemCount() {
        return slistSokoni.size();
    }

   public static class ViewHolderSokoni extends RecyclerView.ViewHolder {

        public ImageView sokoThumbnail;
        public TextView sokoTitle;
        public TextView sokoGenre;

        public ViewHolderSokoni(View itemView) {
            super(itemView);
                
            sokoThumbnail = (ImageView) itemView.findViewById(R.id.sokoThumbnail);
            sokoTitle = (TextView) itemView.findViewById(R.id.sokoTitle);
            sokoGenre = (TextView) itemView.findViewById(R.id.sokoRating);

        }
    }
}

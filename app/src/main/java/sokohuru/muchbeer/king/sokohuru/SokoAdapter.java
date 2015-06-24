package sokohuru.muchbeer.king.sokohuru;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

/**
 * Created by muchbeer on 5/17/2015.
 */
public class SokoAdapter extends RecyclerView.Adapter<SokoAdapter.MyViewHolder>{

    private LayoutInflater inflater;
    List<Information> data = Collections.emptyList();
    private Context context;
    private ClickListener clickListener;

    public SokoAdapter(Context context, List<Information> data)  {
        this.context=context;
     inflater =  LayoutInflater.from(context);
        this.data = data;
      
  }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View view = inflater.inflate(R.layout.custom_row, viewGroup, false);
        MyViewHolder holder = new MyViewHolder(view);
        Log.d("SOKONI","OnCreateHolder called ");
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder viewHolder,  int position) {
       Information current = data.get(position);
        Log.d("SOKONI","OnBindViewHolder called "+position);
        viewHolder.title.setText(current.title);
        viewHolder.icon.setImageResource(current.iconId);

      
    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener=clickListener;

    }

    @Override
    public int getItemCount() {

        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView title;
        ImageView icon;
        public MyViewHolder(View itemView) {
            super(itemView);


            title= (TextView) itemView.findViewById(R.id.listText);
            icon=(ImageView) itemView.findViewById(R.id.listIcon);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
                if(clickListener!=null) {
                    clickListener.itemClicked(view, getPosition());
                }

           // Toast.makeText(context, "Click at Position "+ getPosition(), Toast.LENGTH_LONG).show();
            context.startActivity(new Intent(context, SubActivity.class));
        }
    }
    public interface ClickListener{
        public  void itemClicked(View view, int position);


    }
}

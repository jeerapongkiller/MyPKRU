package pkru.sarak.jeerapong.mypkru;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by mjlab on 26/5/2560.
 */

public class FriendAdapter extends BaseAdapter{

    private Context context;
    private String[] nameStrings, imageStrings;

    public FriendAdapter(Context context,
                         String[] nameStrings,
                         String[] imageStrings) {
        this.context = context;
        this.nameStrings = nameStrings;
        this.imageStrings = imageStrings;
    }

    @Override
    public int getCount() {
        return nameStrings.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = layoutInflater.inflate(R.layout.friend_listview, parent, false);

        //Initial View
        TextView textView = (TextView) view.findViewById(R.id.txtNameFriend);
        ImageView imageView = (ImageView) view.findViewById(R.id.imvHumen);

        //Show View
        textView.setText(nameStrings[position]);
        Picasso.with(context).load(imageStrings[position]).into(imageView);


        return view;
    }
}   // Main Class

package petlocator.petlocator;

/**
 * Created by Max on 4/25/2016.
 *
 * Used by NearbyPets to manage the custom ListView entries
 */
        import android.app.Activity;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ArrayAdapter;
        import android.widget.ImageView;
        import android.widget.TextView;

        import java.util.ArrayList;

public class CustomListAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private ArrayList<String> itemname;
    private ArrayList<Integer> imgid;
    private ArrayList<String> description;

    public CustomListAdapter(Activity context, ArrayList<String> itemname, ArrayList<Integer> imgid, ArrayList<String> description) {
        super(context, R.layout.image_list, itemname);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.itemname=itemname;
        this.imgid=imgid;
        this.description = description;
    }

    public View getView(int position,View view,ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.image_list, null,true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.item);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        TextView extratxt = (TextView) rowView.findViewById(R.id.textView1);

        txtTitle.setText(itemname.get(position));
        imageView.setImageResource(imgid.get(position));
        extratxt.setText(description.get(position));
        return rowView;

    };
}
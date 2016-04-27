package petlocator.petlocator;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Marissa on 4/27/2016.
 * This class handles ListViews containing information from lost/found pet reports. Each row
 * consists of a field header and the field description.
 */
public class PetReportListAdapter extends ArrayAdapter<String> {
    private final Activity context;
    private final String[] fields;
    private final String[] descriptions;

    public PetReportListAdapter(Activity context, String[] fields, String[] descriptions) {
        super(context, R.layout.report_info, fields);
        // TODO Auto-generated constructor stub

        this.context = context;
        this.fields = fields;
        this.descriptions = descriptions;
    }

    public View getView(int position,View view,ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.report_info, null,true);

        TextView fieldHeader = (TextView) rowView.findViewById(R.id.fieldName);
        TextView fieldDescription = (TextView) rowView.findViewById(R.id.fieldDescription);

        fieldHeader.setText(fields[position] + ":");
        fieldDescription.setText(descriptions[position]);
        return rowView;

    };
}

package via.vik.mydrawer.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

import via.vik.mydrawer.R;

/**
 * Created by MR__PN on 11-10-2017.
 */
public class CustomAdapterChoice extends RecyclerView.Adapter<CustomAdapterChoice.ViewHolder> {
    private static final String TAG = "CustomAdapter";

    private ArrayList<String> mDataSet;

    /**
     * Provide a reference to the type of views that you are using (custom ViewHolder)
     */
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView textView;
        private final ImageButton btn_accept,btn_reject;

        public ViewHolder(View v) {
            super(v);
            // Define click listener for the ViewHolder's View.

            textView = (TextView) v.findViewById(R.id.user_name);
            btn_accept=(ImageButton) v.findViewById(R.id.accept);
            btn_reject=(ImageButton) v.findViewById(R.id.reject);

            btn_reject.setOnClickListener(this);
            btn_accept.setOnClickListener(this);

        }

        public TextView getTextView() {
            return textView;
        }

        @Override
        public void onClick(View v) {

            if (v.getId() == R.id.accept){
                Log.e(TAG, "Element " + getAdapterPosition() + " accepted.");

            }
            else if (v.getId() ==R.id.reject) {
                Log.e(TAG, "Element " + getAdapterPosition() + " Rejected.");
            }
        }
    }

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param dataSet String[] containing the data to populate views to be used by RecyclerView.
     */
    public CustomAdapterChoice(ArrayList<String> dataSet) {
        mDataSet = dataSet;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view.
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.text_row_choice_item, viewGroup, false);

        return new ViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        Log.d(TAG, "Element " + position + " set.");

        // Get element from your dataset at this position and replace the contents of the view
        // with that element
        viewHolder.getTextView().setText(mDataSet.get(position));
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataSet.size();
    }
}

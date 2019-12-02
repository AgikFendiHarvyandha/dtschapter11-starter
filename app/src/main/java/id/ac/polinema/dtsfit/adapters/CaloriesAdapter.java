package id.ac.polinema.dtsfit.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import id.ac.polinema.dtsfit.R;
import id.ac.polinema.dtsfit.models.Calory;

public class CaloriesAdapter extends RecyclerView.Adapter<CaloriesAdapter.ViewHolder> {

    private Context context;
    private List<Calory> calories;
    private OnCaloryClickedListener listener;

    public CaloriesAdapter(Context context, OnCaloryClickedListener listener) {
        this.context = context;
        this.listener = listener;
    }

    public void setCalories(List<Calory> calories) {
        this.calories = calories;
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_calory, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Calory calory = calories.get(position);
        // TODO: Bind object calory dengan holder
        holder.foodText.setText(calory.getFood());
        holder.caloryText.setText(String.valueOf(calory.getCalory()));

        holder.bind(calory, listener);
    }

    @Override
    public int getItemCount() {
        return (calories != null) ? calories.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView foodText;
        TextView caloryText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            foodText = itemView.findViewById(R.id.tv_food);
            caloryText = itemView.findViewById(R.id.tv_calory);
        }

        public void bind(final Calory calory, final OnCaloryClickedListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onCaloryClicked(calory);
                }
            });
        }
    }

    public interface OnCaloryClickedListener {
        void onCaloryClicked(Calory calory);
    }
}

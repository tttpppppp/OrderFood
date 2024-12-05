package com.example.projecttest.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.projecttest.R;
import com.example.projecttest.model.HistoryOrder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {
    private Context mContext;
    private List<HistoryOrder> historyOrders;

    public HistoryAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setData(List<HistoryOrder> historyOrders) {
        this.historyOrders = historyOrders;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_history, parent, false);
        return new HistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        HistoryOrder historyOrder = historyOrders.get(position);
        if (historyOrder == null) {
            return;
        }

        holder.txtOrderId.setText("Mã đơn hàng: " + "#" + historyOrder.getOrderId());
        holder.txtOrderDate.setText("Ngày đặt: " + historyOrder.getOrderDate());
        holder.txtRestaurantName.setText("Nhà hàng: " + historyOrder.getRestaurantName());
        holder.txtUsername.setText("Người nhận: " + historyOrder.getUsername());
        holder.txtAddress.setText("Địa chỉ: " + historyOrder.getAddress());
        holder.txtTotalPrice.setText("Tổng giá: " + historyOrder.getTotalPrice());
    }
    private String formatDate(String orderDate) {
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        try {
            return outputFormat.format(inputFormat.parse(orderDate));
        } catch (ParseException e) {
            e.printStackTrace();
            return orderDate; // Return the original string if parsing fails
        }
    }

    @Override
    public int getItemCount() {
        return historyOrders != null ? historyOrders.size() : 0;
    }

    public static class HistoryViewHolder extends RecyclerView.ViewHolder {
        private TextView txtOrderId, txtOrderDate, txtRestaurantName, txtUsername, txtAddress, txtTotalPrice;

        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            // Initialize the TextViews
            txtOrderId = itemView.findViewById(R.id.txtOrderId);
            txtOrderDate = itemView.findViewById(R.id.txtOrderDate);
            txtRestaurantName = itemView.findViewById(R.id.txtNameRes);
            txtUsername = itemView.findViewById(R.id.txtUsername);
            txtAddress = itemView.findViewById(R.id.txtAddress);
            txtTotalPrice = itemView.findViewById(R.id.txtTotal);
        }
    }
}

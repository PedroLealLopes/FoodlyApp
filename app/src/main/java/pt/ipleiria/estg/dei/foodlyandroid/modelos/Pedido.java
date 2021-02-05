package pt.ipleiria.estg.dei.foodlyandroid.modelos;

public class Pedido {
    private int orderId, userId;
    private String date;

    public Pedido(int orderId, String date, int userId) {
        this.orderId = orderId;
        this.date = date;
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "orderId=" + orderId +
                ", userId=" + userId +
                ", date='" + date + '\'' +
                '}';
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

package bean;

public class allorder {
        private long Id;			//ID序列号
        private long orderId;		//订单号表序列号
        private long BookNo;		//图书表序列号
        private int Amount;			//订货数量

        public void allorder() {
                Id = 0;
                orderId = 0;
                BookNo = 0;
                Amount = 0;
        }
        public long getId() {
                return Id;
        }
        public void setId(long newId) {
                this.Id = newId;
        }
        public long getOrderId() {
                return orderId;
        }
        public void setOrderId(long orderId) {
                this.orderId = orderId;
        }
        public long getBookNo() {
                return BookNo;
        }
        public void setBookNo(long newBookNo) {
                this.BookNo = newBookNo;
        }
        public int getAmount() {
                return Amount;
        }
        public void setAmount(int newAmount) {
                this.Amount = newAmount;
        }
}

package bean;

public class allorder {
        private long Id;			//ID���к�
        private long orderId;		//�����ű����к�
        private long BookNo;		//ͼ������к�
        private int Amount;			//��������

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

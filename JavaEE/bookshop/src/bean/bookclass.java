package bean;
//import com.mysql.cj.x.protobuf.MysqlxDatatypes.Scalar.String;
import java.lang.String;
//ͼ�����
public class bookclass {
        private int Id;			    //ID���к�
        private String ClassName;	//ͼ�����
        public bookclass() {
                Id = 0;
                ClassName =null;
              }
        public bookclass(int newId, String classname2) {
                Id = newId;
                ClassName = classname2;
              }
        public int getId() {
                return Id;
              }
        public void setId (int newId) {
                this.Id = newId;
              }
        public String getClassName() {
                return ClassName;
             }
        public void setClassName(String newname) {
                this.ClassName = newname;
            }
}

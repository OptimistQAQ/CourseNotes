package service;

//import java.lang.Exception
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.lang.String;
import com.mysql.cj.x.protobuf.MysqlxDatatypes.Scalar.StringOrBuilder;

import bean.user;
import util.*;
public class manage_login extends DataBase {
        private java.lang.String username;	//��¼�û���
        private java.lang.String passwd;		//��¼����
        private boolean isadmin;	//�Ƿ����Ա��¼
        private long userid=0;		//�û�ID��
        public manage_login() throws Exception{
            super();
            username = "";
            passwd = "";
            isadmin = false;
         }
         public java.lang.String getUsername() {
                 return username;
         }
         public void setUsername(java.lang.String newusername) {
                 username = newusername;
         }
         public java.lang.String getPasswd() {
                 return passwd;
         }
         public void setPasswd(java.lang.String newpasswd) {
                 passwd = newpasswd;
         }
         public boolean getIsadmin() {
                 return isadmin;
         }
         public void setIsadmin(boolean newIsadmin) {
                 isadmin = newIsadmin;
         }
         public long getUserid() {
                 return userid;
         }
         public void setUserid (long uid) {
                 userid = uid;
         }
        /**
         * ��ò�ѯ�û���Ϣ��sql���
         * @return
         */
         public java.lang.String getSql() {
            if (isadmin) {
                    sqlStr = "select * from bookadmin where adminuser = '" +
                    dataFormat.toSql(username) + "' and adminpass = '" +
                    dataFormat.toSql(passwd) + "'";
            }else {
                    sqlStr = "select * from shop_user where username = '" +
                        username + "' and password = '" + passwd + "'";
            }
            return sqlStr;
        }
        /**
         * ִ�в�ѯ
         * @return
         * @throws java.lang.Exception
         */
        public boolean excute() throws Exception 
		{
		      
                boolean flag = false;
                DataBase db = new DataBase();
                Connection conn=db.connect();
                Statement stmt = conn.createStatement();               
                rs = stmt.executeQuery(getSql());
                if (rs.next()){
                        if (!isadmin)
                        {
                          userid = rs.getLong("id");
                        }
                        flag = true;
                }
                
                rs.close();
                return flag;
		}
				
}


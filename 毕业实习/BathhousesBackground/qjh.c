#include <stdio.h>
#include "sqlite3.h"
#include "qjh.h"
#include <gtk/gtk.h>

GtkWidget *label_yzm_old;
GtkWidget* money;

//回调函数区
void window_admin_to_admin_zc(GtkButton* button, gpointer user_data)
{
	gtk_widget_show_all(window_admin_zc);
	gtk_entry_set_text(GTK_ENTRY(e_admin_id), "");
	gtk_entry_set_text(GTK_ENTRY(e_admin_pwd), "");
	gtk_entry_set_text(GTK_ENTRY(e_admin_yzm), "");
	gtk_widget_hide_all(window_admin);
}

void window_main_to_window_admin(GtkButton* button, gpointer user_data)
{
	gtk_widget_show_all(window_admin);
	gtk_entry_set_text(GTK_ENTRY(e_admin_id), "");
	gtk_entry_set_text(GTK_ENTRY(e_admin_pwd), "");
	gtk_entry_set_text(GTK_ENTRY(e_admin_yzm), "");
	gtk_widget_hide_all(window_main);
}

void window_main_to_window_user(GtkButton* button, gpointer user_data)
{
	gtk_widget_show_all(window_user);
	gtk_entry_set_text(GTK_ENTRY(e_admin_id), "");
	gtk_entry_set_text(GTK_ENTRY(e_admin_pwd), "");
	gtk_entry_set_text(GTK_ENTRY(e_admin_yzm), "");
	gtk_widget_hide_all(window_main);
}

void window_user_to_window_main(GtkButton* button, gpointer user_data)
{
	gtk_widget_show_all(window_main);
	gtk_widget_hide_all(window_user);
}

void window_admin_to_window_main(GtkButton * button ,gpointer user_data)
{
	gtk_widget_show_all(window_main);
	
	gtk_widget_hide_all(window_admin);
}

void window_admin_search_to_window_admin(GtkWidget* button, gpointer user_data) {
	gtk_widget_show_all(window_admin);
	
	gtk_widget_hide_all(window_admin_search);
}

void admin_zc_to_window_admin(GtkWidget* button, gpointer user_data)
{
	gtk_widget_show_all(window_admin);
	gtk_entry_set_text(GTK_ENTRY(e_admin_zc_id), "");
	gtk_entry_set_text(GTK_ENTRY(e_admin_zc_pwd), "");
	gtk_entry_set_text(GTK_ENTRY(e_admin_zc_pwd_again), "");
	gtk_widget_hide_all(window_admin_zc);
}

//工具函数
void do_clean(GtkButton*button,gpointer user_data)
{
	//清空entry，生成新的验证码设置上去
	gtk_entry_set_text(GTK_ENTRY(e_admin_id),"");
	gtk_entry_set_text(GTK_ENTRY(e_admin_pwd),"");
	gtk_entry_set_text(GTK_ENTRY(e_admin_yzm),"");
	//生成随机的验证码之后，设置到label上
	char haha[5] = "";
	srand(time(NULL));
	int i = 0;//在c99可以
	for (i = 0; i < 4; ++i)
	{
		int a = rand()%3;
		if(a==0)
			haha[i] = rand()%26+'a';
		else if(a==1)
			haha[i] = rand()%26+'A';
		else
			haha[i] = rand()%10+'0';
		
	}
	char yzm[100] = "";
	sprintf(yzm,"<span foreground='black' font_desc='20'>%s</span>",haha);
	gtk_label_set_markup(GTK_LABEL(label_yzm_old),yzm);
}

//跳转回调函数

//数据库操作回调函数
void window_admin_do_zc(GtkWidget* button, gpointer user_data)
{
	//打开数据库
	// 获取你操作需要的数据
	// 组合一个查询的sql语句
	// 发给数据库
	// 如果查询到了-return
	// 如果没有查到就插入
	// 组合一个insert的sql语句
	// 使用写操作发给数据库
	// 关闭数据库
	sqlite3* db;
	sqlite3_open("mz.db",&db);
	const char * id = gtk_entry_get_text(GTK_ENTRY(e_admin_zc_id));
	const char * pwd = gtk_entry_get_text(GTK_ENTRY(e_admin_zc_pwd));
	char sql1[100] = "";
	sprintf(sql1,"select * from admin where id = \'%s\';",id);
	char ** table = NULL;
	int r = 0, c = 0;
	char *errmsg = NULL;
	sqlite3_get_table(db,sql1,&table,&r,&c,&errmsg);
	if(r!=0)
	{
		gtk_entry_set_text(GTK_ENTRY(e_admin_zc_id),"您的id已经注册，请更换id");
		sqlite3_close(db);
		return;
	}
	char sql2[100] = "";
	sprintf(sql2,"insert into admin values(\'%s\',\'%s\');",id,pwd);
	errmsg = NULL;
	sqlite3_exec(db,sql2,NULL,NULL,&errmsg);
	if(errmsg!=NULL)
		printf("管理员注册:%s\n", errmsg);
	else{
		gtk_entry_set_text(GTK_ENTRY(e_admin_zc_id),"管理员注册成功！！！");
		gtk_widget_show_all(window_admin);
		gtk_widget_hide_all(window_admin_zc);
		sqlite3_close(db);
	}
	return;
}

void window_admin_do_login(GtkWidget* button, gpointer user_data)
{
	sqlite3* db;
	sqlite3_open("mz.db", &db);
	const char *admin_id = gtk_entry_get_text(GTK_ENTRY(e_admin_id));
	const char *admin_pwd = gtk_entry_get_text(GTK_ENTRY(e_admin_pwd));
	char sql1[100] = "";
	sprintf(sql1, "select * from admin where id = \'%s\'", admin_id);
	char **table = NULL;
	int r = 0, c = 0;
	char *errmsg = NULL;
	sqlite3_get_table(db, sql1, &table, &r, &c, &errmsg);
	if (r!=0)
	{
		gtk_widget_show_all(window_admin_search);
		gtk_widget_hide_all(window_admin);
		gtk_entry_set_text(GTK_ENTRY(e_admin_id), "登录成功g");
		sqlite3_close(db);
		return;
	}
	else {
		return;
	}
}

void user_select_all(GtkWidget* button, gpointer user_data)
{
	sqlite3* db;
	sqlite3_open("mz.db", &db);
	const char * name = gtk_entry_get_text(GTK_ENTRY(entry_id));
	char sql4[100] = "";
	sprintf(sql4, "select rmb from user where username = \'%s\';", name);
	char **table = NULL;
	int r=0, c=0;
	char *errmsg = NULL;
	sqlite3_get_table(db, sql4, &table, &r, &c, &errmsg);
	printf("%d\n", r);
	if (r!=0)
	{
		char yzm[100] = "";
		sprintf(yzm,"<span foreground='black' font_desc='20'>%s</span>",table[1]);
		gtk_label_set_markup(GTK_LABEL(money),yzm);
		// gtk_label_set_text(GTK_LABEL(money), table[1]);
		sqlite3_close(db);
		return;
	}
	else{
		return;
	}
}

//用户充值
void user_desposit(GtkWidget* button, gpointer user_data)
{

	int rmb = 10;
	sqlite3 * db;
    sqlite3_open("mz.db",&db);
    const char * name = gtk_entry_get_text(GTK_ENTRY(entry_id));
    char ** table = NULL;
	int r = 0, c = 0;
	char *errmsg = NULL;

    char sqlDesposit[100] ="";
    char sqlSearch[100] ="";

    sprintf(sqlSearch,"select rmb from user where username = \'%s\';", name);
    sqlite3_get_table(db,sqlSearch,&table,&r,&c,&errmsg);
    if (r!=0)
    {
    	int ye = atoi(table[1]);
	    int a = ye+rmb;
	    sprintf(sqlDesposit,"update user set rmb = \'%d\' where username = \'%s\';",a, name);
	    sqlite3_exec(db,sqlDesposit,NULL,NULL,&errmsg);
	    sqlite3_free_table(table);
	    sqlite3_close(db);
	    return;
    }
    else{
    	return;
    }
}

//用户刷卡
void user_shuaka(GtkWidget* button, gpointer user_data)
{
	int rmb = 10;
	sqlite3 * db;
    sqlite3_open("mz.db",&db);
    const char * name = gtk_entry_get_text(GTK_ENTRY(entry_id));
    char ** table = NULL;
	int r = 0, c = 0;
	char *errmsg = NULL;

    char sqlDesposit[100] ="";
    char sqlSearch[100] ="";

    sprintf(sqlSearch,"select rmb from user where username = \'%s\';", name);
    sqlite3_get_table(db,sqlSearch,&table,&r,&c,&errmsg);
    if (r!=0)
    {
    	int ye = atoi(table[1]);
    	if ((ye-10)>=0)
    	{
    		int a = ye-rmb;
		    sprintf(sqlDesposit,"update user set rmb = \'%d\' where username = \'%s\';",a, name);
		    sqlite3_exec(db,sqlDesposit,NULL,NULL,&errmsg);
    	}
	    sqlite3_free_table(table);
	    sqlite3_close(db);
	    return;
    }
    else{
    	return;
    }
}

//界面初始化区
/*
用户界面
 */
void zt_window_user_init()
{
	window_user = gtk_window_new(GTK_WINDOW_TOPLEVEL);
	gtk_widget_set_size_request(window_user,1080,600);

	gtk_window_set_title(GTK_WINDOW(window_user),"用户登录");
	chang_background(window_user,1080,600,"./1.jpg");
	GtkWidget* table = gtk_table_new(4,4,TRUE);
	gtk_container_add(GTK_CONTAINER(window_user),table);

	GtkWidget* label_tel_user = gtk_label_new("用户名：");
	gtk_table_attach_defaults(GTK_TABLE(table),label_tel_user,0,1,0,1);
	gtk_label_set_markup(GTK_LABEL(label_tel_user), "<span foreground='black' font_desc='20'>用户名：</span>");


	GtkWidget* label_rfidcard = gtk_label_new("卡号：");
	gtk_table_attach_defaults(GTK_TABLE(table),label_rfidcard,0,1,1,2);
	gtk_label_set_markup(GTK_LABEL(label_rfidcard), "<span foreground='black' font_desc='20'>卡号：</span>");



	entry_id = gtk_entry_new();
	gtk_table_attach_defaults(GTK_TABLE(table),entry_id,1,3,0,1);

	entry_rfidcard = gtk_entry_new();
	gtk_table_attach_defaults(GTK_TABLE(table),entry_rfidcard,1,3,1,2);

	GtkWidget* label_money = gtk_label_new("余额：");
	gtk_table_attach_defaults(GTK_TABLE(table),label_money,0,1,2,3);
	gtk_label_set_markup(GTK_LABEL(label_money), "<span foreground='black' font_desc='20'>余额：</span>");


	money = gtk_label_new("这里显示余额");
	gtk_table_attach_defaults(GTK_TABLE(table),money,1,2,2,3);
	gtk_label_set_markup(GTK_LABEL(money), "<span foreground='black' font_desc='20'>这里显示余额</span>");



	GtkWidget* button_login_user = gtk_button_new_with_label("查询");
	gtk_table_attach_defaults(GTK_TABLE(table),button_login_user,0,1,3,4);
	gtk_button_set_relief(GTK_BUTTON(button_login_user),GTK_RELIEF_NONE);


	GtkWidget* button_back_user = gtk_button_new_with_label("返回");
	gtk_table_attach_defaults(GTK_TABLE(table),button_back_user,1,2,3,4);
	gtk_button_set_relief(GTK_BUTTON(button_back_user),GTK_RELIEF_NONE);


	GtkWidget* button_shua = gtk_button_new_with_label("刷卡");
	gtk_table_attach_defaults(GTK_TABLE(table),button_shua,2,3,3,4);
	gtk_button_set_relief(GTK_BUTTON(button_shua),GTK_RELIEF_NONE);


	GtkWidget* button_cz = gtk_button_new_with_label("充值");
	gtk_table_attach_defaults(GTK_TABLE(table),button_cz,3,4,3,4);
	gtk_button_set_relief(GTK_BUTTON(button_cz),GTK_RELIEF_NONE);


	g_signal_connect(button_back_user, "pressed", G_CALLBACK(window_user_to_window_main), NULL);
	g_signal_connect(button_login_user, "pressed", G_CALLBACK(user_select_all), NULL);
	g_signal_connect(button_cz, "pressed", G_CALLBACK(user_desposit), NULL);
	g_signal_connect(button_shua, "pressed", G_CALLBACK(user_shuaka), NULL);
}

/*
主窗口界面
 */
void zt_window_main_init()
{
	window_main = gtk_window_new(GTK_WINDOW_TOPLEVEL);
	gtk_widget_set_size_request(window_main,1080,600);

	gtk_window_set_title(GTK_WINDOW(window_main),"澡堂管理系统");
	chang_background(window_main,1080,600,"./1.jpg");
	GtkWidget* table = gtk_table_new(8,7,TRUE);
	gtk_container_add(GTK_CONTAINER(window_main),table);

	GtkWidget* label_title = gtk_label_new("澡堂管理系统");
	gtk_table_attach_defaults(GTK_TABLE(table),label_title,0,7,1,3);
	gtk_label_set_markup(GTK_LABEL(label_title), "<span foreground='white' font_desc='48'>澡堂管理系统</span>");

	GtkWidget* button_admin = gtk_button_new_with_label("管理员");
	gtk_table_attach_defaults(GTK_TABLE(table),button_admin,3,4,4,5);
	gtk_label_set_markup(GTK_LABEL(button_admin), "<span foreground='white' font_desc='50'>管理员</span>");
	gtk_button_set_relief(GTK_BUTTON(button_admin),GTK_RELIEF_NONE);


	GtkWidget* button_user = gtk_button_new_with_label("用户");
	gtk_table_attach_defaults(GTK_TABLE(table),button_user,3,4,6,7);
	gtk_label_set_markup(GTK_LABEL(button_user), "<span foreground='white' font_desc='50'>用户</span>");
	gtk_button_set_relief(GTK_BUTTON(button_user),GTK_RELIEF_NONE);


	g_signal_connect(button_admin,"pressed",G_CALLBACK(window_main_to_window_admin),NULL);
	g_signal_connect(button_user, "pressed", G_CALLBACK(window_main_to_window_user), NULL);
}

/*
管理员登录界面
 */
void zt_admin_window_main_init()
{
	window_admin = gtk_window_new(GTK_WINDOW_TOPLEVEL);
	gtk_widget_set_size_request(window_admin, 1080, 600);

	gtk_window_set_title(GTK_WINDOW(window_admin),"澡堂管理系统");
	chang_background(window_admin,1080,600,"./1.jpg");
	GtkWidget* table = gtk_table_new(5,4,TRUE);
	gtk_container_add(GTK_CONTAINER(window_admin),table);

	GtkWidget* label_title = gtk_label_new("澡堂管理系统");
	gtk_table_attach_defaults(GTK_TABLE(table),label_title,0,4,0,1);
	gtk_label_set_markup(GTK_LABEL(label_title), "<span foreground='black' font_desc='32'>澡堂管理系统</span>");

	GtkWidget* label_id = gtk_label_new("管理员id：");
	gtk_table_attach_defaults(GTK_TABLE(table),label_id,0,1,1,2);
	gtk_label_set_markup(GTK_LABEL(label_id), "<span foreground='black' font_desc='20'>管理员id：</span>");

	GtkWidget* label_pwd = gtk_label_new("密码：");
	gtk_table_attach_defaults(GTK_TABLE(table),label_pwd,0,1,2,3);
	gtk_label_set_markup(GTK_LABEL(label_pwd), "<span foreground='black' font_desc='20'>密码：</span>");

	GtkWidget* label_yzm = gtk_label_new("验证码：");
	gtk_table_attach_defaults(GTK_TABLE(table),label_yzm,0,1,3,4);
	gtk_label_set_markup(GTK_LABEL(label_yzm), "<span foreground='black' font_desc='20'>验证码：</span>");

	label_yzm_old = gtk_label_new("");
	//生成随机的验证码之后，设置到label上
	char haha[5] = "";
	srand(time(NULL));
	int i = 0;//在c99可以
	for (i = 0; i < 4; ++i)
	{
		int a = rand()%3;
		if(a==0)
			haha[i] = rand()%26+'a';
		else if(a==1)
			haha[i] = rand()%26+'A';
		else
			haha[i] = rand()%10+'0';
		
	}
	char yzm[100] = "";
	sprintf(yzm,"<span foreground='black' font_desc='20'>%s</span>",haha);
	gtk_label_set_markup(GTK_LABEL(label_yzm_old),yzm);
	
	gtk_table_attach_defaults(GTK_TABLE(table),label_yzm_old,3,4,3,4);

	e_admin_id = gtk_entry_new();
	gtk_table_attach_defaults(GTK_TABLE(table), e_admin_id, 1,3,1,2);
	e_admin_pwd = gtk_entry_new();
	gtk_table_attach_defaults(GTK_TABLE(table), e_admin_pwd, 1,3,2,3);
	e_admin_yzm = gtk_entry_new();
	gtk_table_attach_defaults(GTK_TABLE(table), e_admin_yzm, 1,3,3,4);



	GtkWidget* button_sk = gtk_button_new_with_label("登录");
	gtk_table_attach_defaults(GTK_TABLE(table),button_sk,0,1,4,5);
	gtk_button_set_relief(GTK_BUTTON(button_sk),GTK_RELIEF_NONE);

	GtkWidget* button_zc = gtk_button_new_with_label("注册");
	gtk_table_attach_defaults(GTK_TABLE(table),button_zc,1,2,4,5);
	gtk_button_set_relief(GTK_BUTTON(button_zc),GTK_RELIEF_NONE);

	GtkWidget* button_sx = gtk_button_new_with_label("刷新");
	gtk_table_attach_defaults(GTK_TABLE(table),button_sx,2,3,4,5);
	gtk_button_set_relief(GTK_BUTTON(button_sx),GTK_RELIEF_NONE);

	GtkWidget* button_fh = gtk_button_new_with_label("返回");
	gtk_table_attach_defaults(GTK_TABLE(table),button_fh,3,4,4,5);
	gtk_button_set_relief(GTK_BUTTON(button_fh),GTK_RELIEF_NONE);


	g_signal_connect(button_sk, "pressed", G_CALLBACK(window_admin_do_login), NULL);
	g_signal_connect(button_fh, "pressed", G_CALLBACK(window_admin_to_window_main), NULL);
	g_signal_connect(button_zc,"pressed",G_CALLBACK(window_admin_to_admin_zc),NULL);
	g_signal_connect(button_sx,"pressed",G_CALLBACK(do_clean),NULL);
}

/*
管理员注册界面
 */
void zt_admin_zc()
{
	window_admin_zc = gtk_window_new(GTK_WINDOW_TOPLEVEL);
	gtk_widget_set_size_request(window_admin_zc, 1080, 600);

	gtk_window_set_title(GTK_WINDOW(window_admin_zc),"澡堂管理系统");
	chang_background(window_admin_zc,1080,600,"./1.jpg");
	GtkWidget* table = gtk_table_new(5,3,TRUE);
	gtk_container_add(GTK_CONTAINER(window_admin_zc),table);

	GtkWidget* label_title = gtk_label_new("澡堂管理系统");
	gtk_table_attach_defaults(GTK_TABLE(table),label_title,0,3,0,1);
	gtk_label_set_markup(GTK_LABEL(label_title), "<span foreground='black' font_desc='32'>澡堂管理系统</span>");

	GtkWidget* label_id = gtk_label_new("请输入id：");
	gtk_table_attach_defaults(GTK_TABLE(table),label_id,0,1,1,2);
	gtk_label_set_markup(GTK_LABEL(label_id), "<span foreground='black' font_desc='20'>请输入id：</span>");

	GtkWidget* label_pwd = gtk_label_new("请输入密码：");
	gtk_table_attach_defaults(GTK_TABLE(table),label_pwd,0,1,2,3);
	gtk_label_set_markup(GTK_LABEL(label_pwd), "<span foreground='black' font_desc='20'>请输入密码：</span>");

	GtkWidget* label_pwd_again = gtk_label_new("再次输入密码：");
	gtk_table_attach_defaults(GTK_TABLE(table),label_pwd_again,0,1,3,4);
	gtk_label_set_markup(GTK_LABEL(label_pwd_again), "<span foreground='black' font_desc='20'>再次输入密码：</span>");

	
	
	e_admin_zc_id = gtk_entry_new();
	gtk_table_attach_defaults(GTK_TABLE(table), e_admin_zc_id, 1,2,1,2);
	e_admin_zc_pwd = gtk_entry_new();
	gtk_table_attach_defaults(GTK_TABLE(table), e_admin_zc_pwd, 1,2,2,3);
	e_admin_zc_pwd_again = gtk_entry_new();
	gtk_table_attach_defaults(GTK_TABLE(table), e_admin_zc_pwd_again, 1,2,3,4);

	GtkWidget* button_zc = gtk_button_new_with_label("注册");
	gtk_table_attach_defaults(GTK_TABLE(table),button_zc,0,1,4,5);
	gtk_button_set_relief(GTK_BUTTON(button_zc),GTK_RELIEF_NONE);

	GtkWidget* button_fh = gtk_button_new_with_label("返回");
	gtk_table_attach_defaults(GTK_TABLE(table),button_fh,1,2,4,5);
	gtk_button_set_relief(GTK_BUTTON(button_fh),GTK_RELIEF_NONE);


	g_signal_connect(button_zc, "pressed", G_CALLBACK(window_admin_do_zc), NULL);
	g_signal_connect(button_fh, "pressed", G_CALLBACK(admin_zc_to_window_admin), NULL);
}

//管理员查询(增删改查)
void zt_window_admin_search_init()
{
	window_admin_search = gtk_window_new(GTK_WINDOW_TOPLEVEL);
	gtk_widget_set_size_request(window_admin_search,1080,600);

	gtk_window_set_title(GTK_WINDOW(window_admin_search),"管理员界面");
	
	chang_background(window_admin_search,1080,600,"./1.jpg");

	GtkWidget* table = gtk_table_new(4,5,TRUE);
	gtk_container_add(GTK_CONTAINER(window_admin_search), table);

	GtkWidget* scrolled_window = gtk_scrolled_window_new(NULL, NULL);
	gtk_table_attach_defaults(GTK_TABLE(table), scrolled_window, 0, 4, 0, 4);
	//设置滚动条
	gtk_scrolled_window_set_policy(GTK_SCROLLED_WINDOW(scrolled_window),
					GTK_POLICY_AUTOMATIC, GTK_POLICY_AUTOMATIC);

	gchar *titles[4] = { "用户名", "卡号" ,"余额", "vip" };
	GtkWidget *clist = gtk_clist_new_with_titles(4, titles);

	sqlite3* db;
	sqlite3_open("mz.db", &db);
	char sql1[100] = "";
	sprintf(sql1,"select * from user;");
	char **user_table = NULL;
	int r=0, c=0;
	char *errmsg=NULL;
	sqlite3_get_table(db, sql1, &user_table, &r, &c, &errmsg);
	int i = 0,k = 0;
	char *haha[4] ;
	for (i = 0; i < r+1; ++i)
	{
		for (k = 0; k < c; ++k)
		{
			haha[k] = user_table[i*c+k];
		}
		gtk_clist_append((GtkCList *)clist, haha);
	}
	sqlite3_close(db);

	gtk_scrolled_window_add_with_viewport(GTK_SCROLLED_WINDOW(scrolled_window), clist); 
	gtk_clist_set_column_justification(GTK_CLIST(clist), 0, GTK_JUSTIFY_CENTER);
	gtk_clist_set_column_justification(GTK_CLIST(clist), 1, GTK_JUSTIFY_CENTER);
	gtk_clist_set_column_justification(GTK_CLIST(clist), 2, GTK_JUSTIFY_CENTER);
	gtk_clist_set_column_justification(GTK_CLIST(clist), 3, GTK_JUSTIFY_CENTER);
	gtk_clist_set_column_width(GTK_CLIST(clist), 0, 200);
	gtk_clist_set_column_width(GTK_CLIST(clist), 1, 200);
	gtk_clist_set_column_width(GTK_CLIST(clist), 2, 200);
	gtk_clist_set_column_width(GTK_CLIST(clist), 3, 200);


	GtkWidget* button_in = gtk_button_new_with_label("增加");
	gtk_table_attach_defaults(GTK_TABLE(table),button_in,4,5,0,1);
	gtk_button_set_relief(GTK_BUTTON(button_in),GTK_RELIEF_NONE);


	GtkWidget* button_del = gtk_button_new_with_label("删除");
	gtk_table_attach_defaults(GTK_TABLE(table),button_del,4,5,1,2);
	gtk_button_set_relief(GTK_BUTTON(button_del),GTK_RELIEF_NONE);


	GtkWidget* button_mod = gtk_button_new_with_label("修改");
	gtk_table_attach_defaults(GTK_TABLE(table),button_mod,4,5,2,3);
	gtk_button_set_relief(GTK_BUTTON(button_mod),GTK_RELIEF_NONE);


	GtkWidget* button_look = gtk_button_new_with_label("返回");
	gtk_table_attach_defaults(GTK_TABLE(table),button_look,4,5,3,4);
	gtk_button_set_relief(GTK_BUTTON(button_look),GTK_RELIEF_NONE);

	g_signal_connect(button_look, "pressed", G_CALLBACK(window_admin_search_to_window_admin), NULL);
}

//数据库初始化区
void my_sql_init() 
{
	sqlite3 *db;
	sqlite3_open("mz.db",&db);
	char *sql1 = "create table admin (id text,password text);";
	char *sql2 = "create table user (username text,rfidid text,rmb int, vip int);";
	char *sql3 = "create table list (rfid text,time time, money int);";
	char * errmsg = NULL;
	sqlite3_exec(db,sql1,NULL,NULL,&errmsg);
	sqlite3_exec(db,sql2,NULL,NULL,&errmsg);
	sqlite3_exec(db,sql3,NULL,NULL,&errmsg);
	sqlite3_close(db);
	return;
}
#ifndef __QJH_H_
#define __QJH_H_ 
#include <gtk/gtk.h>

GtkWidget* window_main;
GtkWidget* c1;
GtkWidget* c2;
GtkWidget* c3;
GtkWidget* c4;

GtkWidget* window_admin_search;

//管理员界面
GtkWidget* window_admin;
GtkWidget* e_admin_id;
GtkWidget* e_admin_pwd;
GtkWidget* e_admin_yzm;

//管理员注册界面
GtkWidget* window_admin_zc;
GtkWidget* e_admin_zc_id;
GtkWidget* e_admin_zc_pwd;
GtkWidget* e_admin_zc_pwd_again;

//用户界面
GtkWidget* window_user;
GtkWidget* entry_id;
GtkWidget* entry_rfidcard;

//其他函数区




//回调函数区


//跳转回调函数




//数据库操作回调函数




//界面初始化区
extern void zt_window_main_init();
extern void zt_admin_window_main_init();
extern void zt_admin_zc();
extern void zt_window_admin_search_init();
extern void zt_window_user_init();



//数据库初始化区

extern void my_sql_init();
//extern表示声明为外部可用
#endif
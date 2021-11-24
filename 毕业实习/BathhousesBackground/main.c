#include <stdio.h>
#include "sqlite3.h"
#include "qjh.h"
#include <gtk/gtk.h>

void chang_background(GtkWidget *widget, int w, int h, const gchar *path)
{
	gtk_widget_set_app_paintable(widget, TRUE);		//允许窗口可以绘图
	gtk_widget_realize(widget);	
 
	/* 更改背景图时，图片会重叠
	 * 这时要手动调用下面的函数，让窗口绘图区域失效，产生窗口重绘制事件（即 expose 事件）。
	 */
	gtk_widget_queue_draw(widget);
 
	GdkPixbuf *src_pixbuf = gdk_pixbuf_new_from_file(path, NULL);	// 创建图片资源对象
	// w, h是指定图片的宽度和高度
	GdkPixbuf *dst_pixbuf = gdk_pixbuf_scale_simple(src_pixbuf, w, h, GDK_INTERP_BILINEAR);
 
	GdkPixmap *pixmap = NULL;
 
	/* 创建pixmap图像; 
	 * NULL：不需要蒙版; 
	 * 123： 0~255，透明到不透明
	 */
	gdk_pixbuf_render_pixmap_and_mask(dst_pixbuf, &pixmap, NULL, 128);
	// 通过pixmap给widget设置一张背景图，最后一个参数必须为: FASLE
	gdk_window_set_back_pixmap(widget->window, pixmap, FALSE);
 
	// 释放资源
	g_object_unref(src_pixbuf);
	g_object_unref(dst_pixbuf);
	g_object_unref(pixmap);
}

int main(int argc, char *argv[])
{
	/* code */
	//数据库初始化
	gtk_init(&argc, &argv);
	my_sql_init();
	zt_window_main_init();

	zt_admin_window_main_init();
	zt_window_admin_search_init();

	zt_admin_zc();

	zt_window_user_init();

	g_signal_connect(window_main,"destroy",G_CALLBACK(gtk_main_quit),NULL);
	gtk_widget_show_all(window_main);
	gtk_main();


	return 0;
}
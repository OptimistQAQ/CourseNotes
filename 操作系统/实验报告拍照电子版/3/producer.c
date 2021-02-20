#include<stdio.h>
#include<pthread.h>
#define MAX 10
pthread_mutex_t the_mutex;
pthread_cond_t conc,conp;
int buffer=0;

void *producer(void *ptr){
    int i;
    for(i=1;i<=MAX;i++){
        pthread_mutex_lock(&the_mutex);
        while(buffer !=0) pthread_cond_wait(&conp,&the_mutex);
        printf("producer produce item %d\n",i);
        buffer=i;
        pthread_cond_signal(&conc);
        pthread_mutex_unlock(&the_mutex);
    }
    pthread_exit(0);
}

void *consumer(void *ptr){
    int i;
    for(i=1;i<=MAX;i++){
        pthread_mutex_lock(&the_mutex);
        while(buffer==0)pthread_cond_wait(&conc,&the_mutex);
        printf("consumer consume item %d\n",i);
        buffer=0;
        pthread_cond_signal(&conp);
        pthread_mutex_unlock(&the_mutex);
    }
    pthread_exit(0);
}

   int main(int argc,char *argv[]){
    pthread_t pro,con;
    pthread_mutex_init(&the_mutex,0);
    pthread_cond_init(&conc,0);
    pthread_cond_init(&conp,0);
    pthread_create(&con,0,consumer,0);
    pthread_create(&pro,0,producer,0);
    pthread_join(pro,0);
    pthread_join(con,0);
    pthread_cond_destroy(&conc);
    pthread_cond_destroy(&conp);
    pthread_mutex_destroy(&the_mutex);
    return 0;
   }

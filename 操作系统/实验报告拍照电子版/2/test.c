#include<stdio.h>
#include<sys/types.h>
#include<unistd.h>
#include<sys/wait.h>
#include<stdlib.h>
#include<signal.h>

int main()
{
    pid_t pid1, pid2;
    pid1=fork();
    if (pid1<0)
    {
        printf("No.1 Error!");
    }
    else if(pid1 == 0){
        printf("This is the No.1 child process");
    }
    else{
        pid2=fork();
        if(pid2<0){
            printf("No.2 Error!");
        }
        else if(pid2==0){
            printf("This is the No.2 child process");
        }
        else {
            printf("This is the parent process");
        }
    }
    printf("fork end.\n\n");
    exit(0);
    return 0;
}
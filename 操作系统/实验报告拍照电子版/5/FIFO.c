//FILENAME: FIFO.cpp
#include<stdio.h>

int main()
{
    int memory[3]={-1,-1,-1}; //-1 means no page in this memory page
    int page[12]={1,2,5,1,3,2,5,4,1,4,5,2};//the test pages
    int pm=0; //pointer of memory
    int count=0;//missing page count
    int replace[12];
    int pr=0;

    printf("###########FIFO############\n");
    //search begin
    for (int i=0;i<12;i++)
    {
        //check if there is page[i] in memory
        int exist = 0;
        for (int j=0;j<3;j++)
        {
            if (page[i]==memory[j])
            {
                exist=1;
                break;
            }
        }
        //not exist , replace this memory page
        if (exist==0)
        {
            if(memory[pm]!=-1)
            {
                replace[pr]=memory[pm];
                pr++;
            }

            count++;
            memory[pm]=page[i];
            pm++;
            if (pm==3) pm=0;
        }
        //output
        printf("%d :  [", page[i]);
        for(int j=0;j<3;j++)
        {
            if (memory[j]==-1) printf("*  ");
            else printf("%d   ", memory[j]);
        }
        printf("   ]\n");
    }
    //output

    printf("######################\n");
    printf("the lack page count = %d\n", count);

    printf("repalce pages are  : ");
    for (int i=0;i<pr;i++)
    {
        printf("%d  ", replace[i]);
    }
    printf("\n");

    printf("the rate of page lack is %lf", count/12.0*100);
    printf("%%\n");

    return 0;
}


//FILENAME: LRU.cpp
#include<stdio.h>

int main()
{
    int memory[3]={-1,-1,-1};
    int page[12]={1,2,5,1,3,2,5,4,1,4,5,2};//the test pages
    int count=0;//lack page count
    int replace[12]; //replace page record
    int pr=0;    //pointer of replace

    printf("##############LRU##############\n");
    //search begin
    for (int i=0;i<12;i++)
    {
        //there are 3 memory pages in memory ,so if i<3,just put it in memory
        if(i<3)
        {
            memory[i]=page[i];
            count++;
        }
        else
        {
            //check if this page is in memory already
            int exist=0;
            for(int j=0;j<3;j++)
            {
                if(page[i]==memory[j])
                {
                    exist=1;
                    break;
                }
            }

            if (exist==0)
            {
                //begin to choose a memory page to replace
                int last=0;
                int ok[3];
                for (int j=0;j<3;j++) ok[j]=0;

                //check from i step -1 till 0
                for(int j=i;j>=0;j--)
                {
                    for(int k=0;k<3;k++)
                    {
                        if (page[j]==memory[k])
                        {
                            ok[k]=1;
                            last++;
                            break;
                        }
                    }
                    if (last==2)break;
                }
                //check which ok ==false
                for (int j=0;j<3;j++)
                {
                    if (ok[j]==0)
                    {
                        //replace this memory[j]
                        count++;
                        replace[pr]=memory[j];
                        pr++;
                        memory[j]=page[i];
                        break;
                    }
                }

            }


        }

        //output
        printf("%d : [ ", page[i]);
        for(int j=0;j<3;j++)
        {
            if (memory[j]==-1) printf("*  ");
            else printf("%d     ", memory[j]);
        }
        printf("]\n");
    }

    //out put
    printf("#####################");
    printf("the lack page count = %d\n", count);
    printf("repalce pages are : ");
    for (int i=0;i<pr;i++)
    {
        printf("%d  ", replace[i]);
    }
    printf("\n");
    printf("the rate of page lack is %lf", count/12.0*100);
    printf("\n");
    return 0;
}


//3.7 ���POS���Ʒ�ϵͳ���Ѷ�ϵ����3���� ��ά 11703060119 
//������������
//	ĳ��͵�һ������3����ʳƷ�����ϣ���ʳ��СʳƷ�����һ��POS���Ʒ�ϵͳ���Կ�͵��������Ϣ���й���
//������Ҫ��
//��1������Ա���ܣ�
//��ʳƷ��Ϣ���й�����ӡ���ѯ���޸ģ�ɾ�������̡��ܹ���ʳƷ���ж��ֲ�ѯ��
//���ۣ�¼��˿�һ�ι������Ϣ�����㹺����ܽ����������㣬���������ϸ�˵���
//�ṩ����ͳ�ƹ��ܣ������ָ�����ڡ�ָ������ʳƷ��ָ������ʳƷ�������������ܶ����ͳ�ƣ�����һ���ĸ�ʽ������ʾ��
//��2�����Ҫ��
//	����Աͨ�������¼ϵͳ������ϵͳ�����ʳƷ���ۡ�
#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<windows.h>
#include<conio.h>


struct food
{
    char genre[9];   //���
	char name[20];  //���� 
	int  price;   //�۸� 
	struct food *next;
};


int flag1=0;   //��ǩ   �����ж����������Ƿ���ȷ   Ȼ�������һ�������� 
char password[14];   //����װ�û���������� 
char t[5][5]={"���","�޸�","ɾ��","����"};
char t1[8][10]={"�׷�","����","������","��ʳ","����","����","����","��ɫɰ��"};
struct food *head=NULL,*tail=NULL;   //��ò�Ʒ�����������ͷ��β 
struct food *salehead=NULL;   //��˵������ͷ 
void gotoxy(int x,int y);  //�ƶ����λ�� 
void show1();   //��¼���� 
void fillpassword();     //��д���� 
void pass();   //��֤ 
void show2(); //������ 
struct food *getnode();  //��ô洢�ռ� 
void getfood();   //��ò�Ʒ���� 
void add();   //���  
void revise();  //�޸� 
void amputate();  //ɾ�� 
void store();   //����  
void sale1();    //���1 
void sale2(int index1);   //���2
void sale3();     //���3 
void release();    //�ͷ��ڴ� 









void setColor(unsigned short ForeColor,unsigned short BackGroundColor)//������ɫ+������ɫ
{
	HANDLE hCon=GetStdHandle(STD_OUTPUT_HANDLE);//��ȡ��ǰ���ھ��
	SetConsoleTextAttribute(hCon,(ForeColor%16)|(BackGroundColor%16*16));//������ɫ
}





void gotoxy(int x,int y)  //������ƶ���ĳ��λ�� 
{
	HANDLE handle=GetStdHandle(STD_OUTPUT_HANDLE);
	COORD pos;
	pos.X=x;
	pos.Y=y;
	SetConsoleCursorPosition(handle,pos);
}





void fillpassword()   //��д���� 
{
    int i=0;
	char ch;
	memset(password,0,14);
	while(i<13)
	{
	  ch=getch();   //getch();   ���������û�����ĵ����ַ�    ���Ҳ���ʾ�����������Ļ��    ��Ҫͷ�ļ�  #include<conio.h> 
	  
	  if(ch=='\r')   //  ���  ����enter��   ��������  
	  {
	  	password[i]='\0';
	  	break;
	  }
	     
	  if(ch=='\b')    //���  ����ɾ����  �ͽ���  ����ɾ�� 
	  {
	  printf("\b \b");
	  i--;
	  password[i]=' ';
      }
	  else
	  {
	    password[i]=ch;       //���������������char������ 
		printf("*");        // ���� ����Ļ����ʾ һ���Ǻ� 
		i++; 
	  }
	    	
	}	
 } 
 
 
 
 
 
void pass()   //�ж��û����������Ƿ�ƥ�� 
 {
    if(strcmp(password,"123456")==0)
      flag1=1;
    else
      flag1=0;	
 }
 
 
 
 
 
 
 
 void show1()
{
	int i=5;
	printf("\t\t\t\t*****************************************\n");
	printf("\t\t\t\t*                                       *\n");
	printf("\t\t\t\t*                                       *\n");
	printf("\t\t\t\t*                                       *\n");
	printf("\t\t\t\t*   ��  �룺                            *\n");
	printf("\t\t\t\t* ��123456��                            *\n");
	printf("\t\t\t\t*                                       *\n");
	printf("\t\t\t\t*                                       *\n");
	printf("\t\t\t\t*****************************************\n");
	gotoxy(46,4);   //�������Ľ���   Ȼ��  ������ƶ�������������ʵ�λ��   ��Ȼ��������ʱ�����ڽ�������ʾ  ������ 
	fillpassword();   //�����������뺯�� 
	pass();    //�����ж����������Ƿ���ȷ����    
	
	if(flag1==1)    //�����ж�   Ȼ��������������һ������ 
	{ 
	   system("cls");
	   getfood();   //�������������ȷ  �͵����������  ��ò˵�   �������� 
	   show2();     //  ��ʾϵͳ������ 
	}
	
	 
	if(flag1==0)
	  { 
	    
	    for(;i>=0;i--)    //��������������  ����� �������� 
	    { 
		   gotoxy(36,2);
		   printf("��֤��ݴ��󣡣������ԣ�����%d��",i);
	       Sleep(1000);  
	    }
		system("cls");
	  	show1();
	  }		
}






void show2()    //������ 
{
	 int i;
	 char choice;
	 int index=0;
	 while(1)
	 {
	   system("cls");// ��ʾ���� 
	 setColor(5,0);
	 printf("\t\t\t____________________________________________________________________\n\n"); 
	 printf("\t\t\t                    ��ӭ����POS���Ʒ�ϵͳ                           \n");
	 printf("\t\t\t         ��w:�����ƶ�     s:�����ƶ�    Enter:���룩                \n\n");
	 for(i=0;i<5;i++)
	 {
	 	if(i==index)
	 	setColor(7,0);
	    printf("\t\t\t                            %s                                    \n",t[i]);
	    setColor(5,0);
     }
	 printf("\t\t\t(������ w:���� s:���� d:����ѡ���Ʒ Enter:���� ѡ�����������������ESC�ɻ�÷�Ʊ)\n\n");
	 
	 
	 	choice=getch();           //���� w  �����ƶ�   s  �����ƶ� 
	 	if(choice=='w'&&index!=0)
	 	  index--;
	 	if(choice=='s'&&index<=2)
		   index++;
		if(choice=='\r')     //����enter��  ����ѡ�� 
		  break;  
		if(choice==27)    // ����esc��   �˳�ϵͳ 
		{   
		   release();
		   exit(0); 
		}
		    
}

	 switch(index)
	 {
	 	case 0:
	 		    add();   //��� 
	 		  break;
	    case 1:  
		        revise(); //�޸�
		       break;
		case 2:
			    amputate(); //ɾ��   
			    break;
		
		case 3:
			   sale1();    //��� 
			   break;
	 }
}






struct food *getnode()   //��ô洢�ռ� 
{
	 struct food *p;
	 p=(struct food*)malloc(sizeof(struct food));
	 return p;
}







void getfood()   //��ò�Ʒ��Ϣ 
{
	FILE *fp;
	struct food *p; 
	if((fp=fopen("food.txt","r"))==NULL)
	{
		printf("fail to open the file");
		exit(0);
	}
	while(!feof(fp))
	{
	
	  p=getnode();
	  fscanf(fp,"%s",p->genre);       //���ļ���  ������Ϣ    Ȼ����û��ͷ�ӵ��β�巨��������  ���������ʹͬ���Ʒ������һ��  
	  fscanf(fp,"%s",p->name);
	  fscanf(fp,"%d",&p->price);
	   p->next=head;
	   head=p;
		  
      }
}







void add()    //��Ӳ�Ʒ 
{
	struct food *p;
	struct food *before;
	char choice;
	while(1)    //����whileѭ�����ж���ظ����� 
	{
	  p=head;
	  before=head;
	  struct food *t=getnode();
	system("cls");
	printf("\t\t\t���                   ���ƣ�                      �۸�              \n");
	gotoxy(30,0);
	fflush(stdin);
	gets(t->genre);
	gotoxy(54,0);
	gets(t->name);
	gotoxy(82,0);
	scanf("%d",&t->price);
    while(p!=NULL)
     {
     	if(strcmp(p->genre,t->genre)==0)
     	    break;
     	  before=p;
     	  p=p->next;
     
	 }
	    if(p!=NULL)
	    {
	    before->next=t;
	    t->next=p;
    	printf("\t\t\t\t\t\t ��ӳɹ�!!!!!\n");
        }
      printf("\t\t\t\t\t\t  ������Enter��       �˳���Esc�� ");
      choice=getch();
	  if(choice==27)   //  �������esc��   �ȵ��� store()����  ������еĲ������б���  Ȼ��ص������� show2();   
	  {                //  �������enter��  ���ͷ��ʼ  ������Ӳ��� 
	  	 store();
	  	 show2();
	     break; 
	  }
	    
   }
}







void  revise()   //�޸ĺ��� 
{
	int index=0;
	char t[20];
	char choice;
	struct food *p=head; 
	while(1)   //whileѭ�������ظ��޸Ĳ��� 
	{
	 memset(t,0,20);
	 p=head;
	 index=0; 
	system("cls");
	printf("\t\t\t���ƣ�                           \n");
	gotoxy(30,0);
	fflush(stdin); 
	gets(t);
	system("cls");
	while(p!=NULL)
	{
		if(strcmp(p->name,t)==0)
		  break;
		p=p->next;
	}
	if(p==NULL)
	 printf("\t\t\t\tû���ҵ��ò�Ʒ!\n");
	else
	{
		
	  while(1)
	  { 
	    system("cls");
	     printf("\t\t\t\t���%s\n",p->genre);
	     printf("\t\t\t\t���ƣ�%s\n",p->name);
	     printf("\t\t\t\t�۸�%d\n",p->price);
	     gotoxy(26,index);
	     printf("->");
	     choice=getch();    //���� w  �������Ʋ���   s  �������Ʋ���   enter�� ѡ��һ������޸� 
	    if(choice=='w'&&index!=0)
	       index--;
	    if(choice=='s'&&index<=1)
	       index++;
	    if(choice=='\r')
	       break;
      }
      switch(index)
       {
       	  case 0:
       	  	    system("cls");
       	  	    memset(p->genre,0,9);
       	  	    printf("\t\t\t\t���%s\n",p->genre);
	            printf("\t\t\t\t���ƣ�%s\n",p->name);
	            printf("\t\t\t\t�۸�%d\n",p->price);
       	  	    gotoxy(38,index);
       	  	    gets(p->genre);
       	  	    break;
       	  case 1:
       	  	    system("cls");
       	   	    memset(p->name,0,20);
       	   	    printf("\t\t\t\t���%s\n",p->genre);
	            printf("\t\t\t\t���ƣ�%s\n",p->name);
	            printf("\t\t\t\t�۸�%d\n",p->price);
       	  	    gotoxy(38,index);
       	  	    gets(p->name);
       	   	    break;
       	  case 2:
       	  	    system("cls");
       	  	    p->price=0;
       	  	    printf("\t\t\t\t���%s\n",p->genre);
	            printf("\t\t\t\t���ƣ�%s\n",p->name);
	            printf("\t\t\t\t�۸�%d\n",p->price);
       	  	    gotoxy(38,index);
       	  	    scanf("%d",&p->price);
       	  	    break;
	   }
	} 
	printf("\t\t\t\t\t\t  ������Enter��       �˳���Esc�� ");
      choice=getch();
	  if(choice==27)
	  {
	  	 store();
	  	 show2();
	     break; 
	  }
       
  }
}









void amputate()    //ɾ�� 
{
	char t[20];
	struct food *p;
	struct food *before;
	struct food *temp;
	char choice;
	while(1)
	{
	 p=head;
	 before=head;
	 memset(t,0,20);
	system("cls"); 
	printf("\t\t\t���ƣ�                           \n");
	gotoxy(30,0);
	fflush(stdin);
	gets(t);
	while(p!=NULL)
	{
		
		if(strcmp(p->name,t)==0)
		  break;
		before=p; 
	    p=p->next;
	}
	if(p==NULL)
	 printf("\t\t\t\tû���ҵ��ò�Ʒ!\n");
	else 
	   {
	   	 system("cls");
	   	 printf("\t\t\t\t\t\t���%s\n",p->genre);
	     printf("\t\t\t\t\t\t���ƣ�%s\n",p->name);
	     printf("\t\t\t\t\t\t�۸�%d\n",p->price);
	     printf("\t\t\t\t\t\t    �Ƿ�ɾ��\n");
	     printf("\t\t\t\t\t\t 1.ȷ��    0.ȡ��\n");
	     choice=getch();
	     if(choice=='1')
	     {
	         temp=p->next;
			 before->next=temp;
			 free(p);
			 system("cls");
			 printf("\t\t\t\t\t\tɾ���ɹ�������");	
		 }
	   }
	   printf("\t\t\t\t\t  ������Enter��       �˳���Esc�� ");
      choice=getch();
	  if(choice==27)
	  {
	  	 store();
	  	 show2();
	     break; 
	  } 
	  
    }
}






void store()   //���� 
{
	FILE *fp;
	struct food *p=head;
		if((fp=fopen("food.txt","w"))==NULL)
	{
		printf("fail to open the file");
		exit(0);
	}
	while(p!=NULL)
	{
		fprintf(fp,"%s ",p->genre);
		fprintf(fp,"%s ",p->name);
		fprintf(fp,"%d\n",p->price);
		p=p->next;
	}
	fclose(fp);
	
 } 
 
 
 
 
 
 
 
 
 
void sale1()
{ 
	int i;
	int count=0;  
 	int index1=0;
 	char choice;
 	struct food *p=head;
 	
 	while(1)
 	{
	   setColor(5,0);
	   system("cls");
	   p=head;
	   count=0;
	 for(i=0;i<8;i++)
	 {
	 	
	 	if(i==index1)
	 	setColor(7,0); 
	   printf("\t\t%s\n",t1[i]);
	      setColor(5,0);	
	 }  
	   
	  while(p!=NULL)
	  {
	  	if(strcmp(t1[index1],p->genre)==0)
	  	   {
	  	   	gotoxy(30,count);
	  	 	printf("%s\t%10d",p->name,p->price);
	  	 	count++;
		   }
		   p=p->next;
	  }
	      choice=getch();     //���� w  ����    s����   d��  �����ƶ�ѡ��  ��Ʒ 
	  if(choice=='w'&&index1!=0)
	      index1--;
	  if(choice=='s'&&index1<=6)
	      index1++;
	  if(choice=='d')   
	    sale2(index1); 
	   if(choice==27)
	      {
	      	if(salehead==NULL)
			  show2();
		    else
		    {
		     sale3();
		     printf("\t\t\t\t\t\t  ������Enter��       �˳���Esc�� ");
             choice=getch();
	           if(choice==27)
	          {
	  	       show2();
	            break; 
	          }
		    } 
      }
   }
}
 
 
 
 
 
 
 
void sale2(int index1)
{
	int i=0,count=0;
	int t=0,j=0;
	int index2=0;
	char choice;
	struct food *p;
	struct food *t2;
	while(1)
	{
	    t=0,j=0;
		count=0; 
	  system("cls");
	  setColor(5,0);
	   p=head;
   	for(i=0;i<8;i++)
	 {
	 	if(i==index1)
	 	setColor(7,0); 
	   printf("\t\t%s\n",t1[i]);
	      setColor(5,0);	
	 }  
	  while(p!=NULL)
	  {
	  	if(strcmp(t1[index1],p->genre)==0)
	  	   {
	  	   	gotoxy(30,count);
	  	 	printf("%s\t%10d",p->name,p->price);
	  	 	count++;   //��¼�˸�����Ʒ������   Ϊ  count 
		   }
		   p=p->next;
	  }
	  
	      p=salehead;
	      while(p!=NULL)
	      {
	      	setColor(7,0);
	      	gotoxy(80,j);
	      	printf("%s\t%d",p->name,p->price);
	      	setColor(5,0);
	      	j++;
	      	p=p->next;
		  }
	          gotoxy(70,index2);
		      printf("<--");
		       choice=getch();
	     	  if(choice=='w'&&index2!=0)
	            index2--;
	          if(choice=='s'&&index2<=count-2)
	            index2++;
	          if(choice=='\r')    //����enter  �����е��   ���Զ�ε��   
	            {    
				     t=0;
	                 p=head;
				    while(p!=NULL)
	              {
	              	  
	  	              if(strcmp(t1[index1],p->genre)==0)    //�������Ϣ���ӳ�����  
	  	            {
	  	   	            if(t==index2)
						{
						   struct food *temp=getnode();
						   strcpy(temp->genre,p->genre);
						   strcpy(temp->name,p->name);
						   (*temp).price=(*p).price;
						   temp->next=salehead;
						    salehead=temp;
							break;	
						}   
	  	 	            else
	  	 	                t++;
		            }
		           p=p->next;
	              }        	
		  }
		  if(choice=='\b')
		  {
		  	 p=salehead;
		  	 t2=p;
		  	 p=p->next;
			 salehead=p;
			  free(t2);
		  	   
		  }
		   if(choice==27)   //����Esc���˳� 
		    break;
    }
}
 
 
 
 
 
 
 
 
 void sale3()   //���� �����Ϣ  ��ӡƱ�� 
 {
 	   int i=0;
 	   int flag=1;
 	   int sum=0;
 	   struct food *p;
 	   struct food *t;
 	   system("cls");
       printf("\t\t\t\t\t      ��ӭ���ٻ����������͵�\n");
	   printf("\t\t\t\t\t ----------------------------------\n");	
 	   printf("\t\t\t\t\t  ����     ����     ����     ���  \n");
 	   
 	   for(i=0;i<8;i++)
 	   {
 	   	 flag=1;
 	   	 p=salehead;
 	   while(p!=NULL)
 	   {
 	     if(strcmp(t1[i],p->genre)==0)
 	     {
 	     	if(flag==1)
 	     	{
			   printf("\t\t\t\t\t ----------------------------------\n");
	           printf("\t\t\t\t\t               %s\n",t1[i]);
	           printf("\t\t\t\t\t ----------------------------------\n");
			   
	           flag=0;
	        }
	       printf("\t\t\t\t\t   %s\t%d\n",p->name,p->price);
	             sum+=(*p).price;
	     }
         p=p->next;
	   }
	          
  } 
              printf("\t\t\t\t\t   �ϼƣ�         %d\n",sum);
	          printf("\t\t\t\t\t ----------------------------------\n");
	  p=salehead;
	  while(p!=NULL)
	  {
	    t=p->next;
	    free(p);
	    p=t;
	   } 
	salehead=NULL;
	
 }
 
 
 
 
 
 
 
 void release()    //�ͷ��ڴ�ռ� 
{
	struct food *p=head;
	struct food *t; 
	while(p!=NULL)
	{
		t=p->next;
		free(p);
		p=t;
	}	
}




int main()
{   
       
    show1();
	 
	return 0;
}


//3.7 快餐POS机计费系统（难度系数：3级） 王维 11703060119 
//【任务描述】
//	某快餐店一共出售3大类食品，饮料，主食，小食品。设计一个POS机计费系统，对快餐店的销售信息进行管理。
//【功能要求】
//（1）管理员功能：
//对食品信息进行管理：添加、查询、修改，删除、存盘。能够对食品进行多种查询。
//销售：录入顾客一次购买的信息。计算购买的总金额，所交金额，找零，输出消费明细账单。
//提供多种统计功能：例如对指定日期、指定名称食品、指定种类食品的销量、收入总额进行统计，并按一定的格式进行显示。
//（2）设计要求
//	管理员通过密码登录系统，进行系统管理和食品销售。
#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<windows.h>
#include<conio.h>


struct food
{
    char genre[9];   //类别
	char name[20];  //名称 
	int  price;   //价格 
	struct food *next;
};


int flag1=0;   //标签   用来判断密码输入是否正确   然后决定下一步的做法 
char password[14];   //用来装用户输入的密码 
char t[5][5]={"添加","修改","删除","销售"};
char t1[8][10]={"套饭","炒饭","炒菜类","面食","米线","刀削","炒粉","特色砂锅"};
struct food *head=NULL,*tail=NULL;   //获得菜品名单的链表的头和尾 
struct food *salehead=NULL;   //点菜的链表的头 
void gotoxy(int x,int y);  //移动鼠标位置 
void show1();   //登录界面 
void fillpassword();     //填写密码 
void pass();   //验证 
void show2(); //主界面 
struct food *getnode();  //获得存储空间 
void getfood();   //获得菜品名单 
void add();   //添加  
void revise();  //修改 
void amputate();  //删除 
void store();   //存盘  
void sale1();    //点餐1 
void sale2(int index1);   //点餐2
void sale3();     //点餐3 
void release();    //释放内存 









void setColor(unsigned short ForeColor,unsigned short BackGroundColor)//字体颜色+背景颜色
{
	HANDLE hCon=GetStdHandle(STD_OUTPUT_HANDLE);//获取当前窗口句柄
	SetConsoleTextAttribute(hCon,(ForeColor%16)|(BackGroundColor%16*16));//设置颜色
}





void gotoxy(int x,int y)  //将光标移动到某个位置 
{
	HANDLE handle=GetStdHandle(STD_OUTPUT_HANDLE);
	COORD pos;
	pos.X=x;
	pos.Y=y;
	SetConsoleCursorPosition(handle,pos);
}





void fillpassword()   //填写密码 
{
    int i=0;
	char ch;
	memset(password,0,14);
	while(i<13)
	{
	  ch=getch();   //getch();   用来接收用户输入的单个字符    并且不显示你的输入在屏幕上    需要头文件  #include<conio.h> 
	  
	  if(ch=='\r')   //  如果  输入enter键   结束输入  
	  {
	  	password[i]='\0';
	  	break;
	  }
	     
	  if(ch=='\b')    //如果  按下删除键  就进行  密码删除 
	  {
	  printf("\b \b");
	  i--;
	  password[i]=' ';
      }
	  else
	  {
	    password[i]=ch;       //将输入的密码存放在char数组中 
		printf("*");        // 并且 在屏幕上显示 一个星号 
		i++; 
	  }
	    	
	}	
 } 
 
 
 
 
 
void pass()   //判断用户名和密码是否匹配 
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
	printf("\t\t\t\t*   密  码：                            *\n");
	printf("\t\t\t\t* （123456）                            *\n");
	printf("\t\t\t\t*                                       *\n");
	printf("\t\t\t\t*                                       *\n");
	printf("\t\t\t\t*****************************************\n");
	gotoxy(46,4);   //输出上面的界面   然后  将光标移动到密码输入的适当位置   不然密码输入时就是在界面外显示  不美观 
	fillpassword();   //调用密码输入函数 
	pass();    //调用判断密码输入是否正确函数    
	
	if(flag1==1)    //进行判断   然后根据情况进行下一步操作 
	{ 
	   system("cls");
	   getfood();   //如果密码输入正确  就调用这个函数  获得菜单   构成链表 
	   show2();     //  显示系统主界面 
	}
	
	 
	if(flag1==0)
	  { 
	    
	    for(;i>=0;i--)    //如果密码输入错误  五秒后 重新输入 
	    { 
		   gotoxy(36,2);
		   printf("验证身份错误！！请重试！！！%d秒",i);
	       Sleep(1000);  
	    }
		system("cls");
	  	show1();
	  }		
}






void show2()    //主界面 
{
	 int i;
	 char choice;
	 int index=0;
	 while(1)
	 {
	   system("cls");// 表示清屏 
	 setColor(5,0);
	 printf("\t\t\t____________________________________________________________________\n\n"); 
	 printf("\t\t\t                    欢迎进入POS机计费系统                           \n");
	 printf("\t\t\t         （w:向上移动     s:向下移动    Enter:进入）                \n\n");
	 for(i=0;i<5;i++)
	 {
	 	if(i==index)
	 	setColor(7,0);
	    printf("\t\t\t                            %s                                    \n",t[i]);
	    setColor(5,0);
     }
	 printf("\t\t\t(销售中 w:上移 s:下移 d:向右选择菜品 Enter:进入 选择结束后连续按两次ESC可获得发票)\n\n");
	 
	 
	 	choice=getch();           //输入 w  向上移动   s  向下移动 
	 	if(choice=='w'&&index!=0)
	 	  index--;
	 	if(choice=='s'&&index<=2)
		   index++;
		if(choice=='\r')     //按下enter键  进行选择 
		  break;  
		if(choice==27)    // 按下esc键   退出系统 
		{   
		   release();
		   exit(0); 
		}
		    
}

	 switch(index)
	 {
	 	case 0:
	 		    add();   //添加 
	 		  break;
	    case 1:  
		        revise(); //修改
		       break;
		case 2:
			    amputate(); //删除   
			    break;
		
		case 3:
			   sale1();    //点餐 
			   break;
	 }
}






struct food *getnode()   //获得存储空间 
{
	 struct food *p;
	 p=(struct food*)malloc(sizeof(struct food));
	 return p;
}







void getfood()   //获得菜品信息 
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
	  fscanf(fp,"%s",p->genre);       //从文件中  或者信息    然后用没有头接点的尾插法构建链表  这个做可以使同类菜品排列在一起  
	  fscanf(fp,"%s",p->name);
	  fscanf(fp,"%d",&p->price);
	   p->next=head;
	   head=p;
		  
      }
}







void add()    //添加菜品 
{
	struct food *p;
	struct food *before;
	char choice;
	while(1)    //运用while循环进行多次重复操作 
	{
	  p=head;
	  before=head;
	  struct food *t=getnode();
	system("cls");
	printf("\t\t\t类别：                   名称：                      价格：              \n");
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
    	printf("\t\t\t\t\t\t 添加成功!!!!!\n");
        }
      printf("\t\t\t\t\t\t  继续（Enter）       退出（Esc） ");
      choice=getch();
	  if(choice==27)   //  如果按下esc键   先调用 store()函数  将你进行的操作进行保存  然后回到主界面 show2();   
	  {                //  如果按下enter键  则从头开始  进行添加操作 
	  	 store();
	  	 show2();
	     break; 
	  }
	    
   }
}







void  revise()   //修改函数 
{
	int index=0;
	char t[20];
	char choice;
	struct food *p=head; 
	while(1)   //while循环进行重复修改操作 
	{
	 memset(t,0,20);
	 p=head;
	 index=0; 
	system("cls");
	printf("\t\t\t名称：                           \n");
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
	 printf("\t\t\t\t没有找到该菜品!\n");
	else
	{
		
	  while(1)
	  { 
	    system("cls");
	     printf("\t\t\t\t类别：%s\n",p->genre);
	     printf("\t\t\t\t名称：%s\n",p->name);
	     printf("\t\t\t\t价格：%d\n",p->price);
	     gotoxy(26,index);
	     printf("->");
	     choice=getch();    //按下 w  进行上移操作   s  进行下移操作   enter键 选择一项进行修改 
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
       	  	    printf("\t\t\t\t类别：%s\n",p->genre);
	            printf("\t\t\t\t名称：%s\n",p->name);
	            printf("\t\t\t\t价格：%d\n",p->price);
       	  	    gotoxy(38,index);
       	  	    gets(p->genre);
       	  	    break;
       	  case 1:
       	  	    system("cls");
       	   	    memset(p->name,0,20);
       	   	    printf("\t\t\t\t类别：%s\n",p->genre);
	            printf("\t\t\t\t名称：%s\n",p->name);
	            printf("\t\t\t\t价格：%d\n",p->price);
       	  	    gotoxy(38,index);
       	  	    gets(p->name);
       	   	    break;
       	  case 2:
       	  	    system("cls");
       	  	    p->price=0;
       	  	    printf("\t\t\t\t类别：%s\n",p->genre);
	            printf("\t\t\t\t名称：%s\n",p->name);
	            printf("\t\t\t\t价格：%d\n",p->price);
       	  	    gotoxy(38,index);
       	  	    scanf("%d",&p->price);
       	  	    break;
	   }
	} 
	printf("\t\t\t\t\t\t  继续（Enter）       退出（Esc） ");
      choice=getch();
	  if(choice==27)
	  {
	  	 store();
	  	 show2();
	     break; 
	  }
       
  }
}









void amputate()    //删除 
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
	printf("\t\t\t名称：                           \n");
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
	 printf("\t\t\t\t没有找到该菜品!\n");
	else 
	   {
	   	 system("cls");
	   	 printf("\t\t\t\t\t\t类别：%s\n",p->genre);
	     printf("\t\t\t\t\t\t名称：%s\n",p->name);
	     printf("\t\t\t\t\t\t价格：%d\n",p->price);
	     printf("\t\t\t\t\t\t    是否删除\n");
	     printf("\t\t\t\t\t\t 1.确定    0.取消\n");
	     choice=getch();
	     if(choice=='1')
	     {
	         temp=p->next;
			 before->next=temp;
			 free(p);
			 system("cls");
			 printf("\t\t\t\t\t\t删除成功！！！");	
		 }
	   }
	   printf("\t\t\t\t\t  继续（Enter）       退出（Esc） ");
      choice=getch();
	  if(choice==27)
	  {
	  	 store();
	  	 show2();
	     break; 
	  } 
	  
    }
}






void store()   //存盘 
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
	      choice=getch();     //按下 w  上移    s下移   d键  向右移动选择  菜品 
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
		     printf("\t\t\t\t\t\t  继续（Enter）       退出（Esc） ");
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
	  	 	count++;   //记录了该类别菜品的数量   为  count 
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
	          if(choice=='\r')    //按下enter  键进行点餐   可以多次点餐   
	            {    
				     t=0;
	                 p=head;
				    while(p!=NULL)
	              {
	              	  
	  	              if(strcmp(t1[index1],p->genre)==0)    //将点餐信息链接成链表  
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
		   if(choice==27)   //按下Esc键退出 
		    break;
    }
}
 
 
 
 
 
 
 
 
 void sale3()   //根据 点餐信息  打印票单 
 {
 	   int i=0;
 	   int flag=1;
 	   int sum=0;
 	   struct food *p;
 	   struct food *t;
 	   system("cls");
       printf("\t\t\t\t\t      欢迎光临花儿与少年快餐店\n");
	   printf("\t\t\t\t\t ----------------------------------\n");	
 	   printf("\t\t\t\t\t  名称     数量     单价     金额  \n");
 	   
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
              printf("\t\t\t\t\t   合计：         %d\n",sum);
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
 
 
 
 
 
 
 
 void release()    //释放内存空间 
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


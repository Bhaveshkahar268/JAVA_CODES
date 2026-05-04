class MyLinkedList
{
class Node
{
int data;
Node next;
private Node()
{
this.data=0;
this.next=null;
}
private Node(int data)
{
this.data= data;
this.next= null;

}
}

Node head=null;
int length=0;

public void add(int data)
{
Node newnode = new Node(data);
if(length==0)
{
length++;
head = newnode;
}
else
{
Node temp= head;
for(int i=0;i<length-1;++i)
{
temp=temp.next;
}
temp.next=newnode;
length++;
}
}
public void addAt(int index, int data)
{
if(index<0) return;
Node temp= head;
for(int i=0;i<index-1;++i)
{
temp=temp.next;
}
Node newnode= new Node(data);
newnode.next=temp.next;
temp.next= newnode;
length++;
}

public void printList()
{
if(head == null) return;
Node temp= head;
for(int i=0;i<length;++i)
{
System.out.println(temp.data);
temp=temp.next;
}
}
public void removeAt(int index)
{
if(index<0) return;
Node temp= head;
for(int i=0;i<index-1;++i)
{
temp=temp.next;
}
temp.next= temp.next.next;
length--;
}

public int dataAt(int index)
{
if(index<0 || index>this.length) return -1;
Node temp= head;
for(int i=0;i<index-1;++i)
{
temp=temp.next;
}
return temp.next.data;

}
public int size()
{
return this.length;
}

public void clear()
{
head=null;
this.length=0;
}

public void clearFrom(int index)
{
if(index<0 || index >this.length) return ;
Node temp= head;
int length=1;
for(int i=0;i<index-1;++i)
{
temp=temp.next;
length++;
}
temp.next= null;
this.length=length;
}
}


class eg52psp
{
public static void main(String gg[])
{
MyLinkedList list = new MyLinkedList();
list.add(10);
list.add(220);
list.add(21);
list.add(11);
list.add(26);
list.addAt(2,20);
list.addAt(4,100);
list.removeAt(1);
list.removeAt(3);
list.printList();
list.clearFrom(4);
System.out.println("----------------------------------");
list.printList();
System.out.println("size:  " +list.size());
System.out.println("data at  3 : " +list.dataAt(3));

}
}

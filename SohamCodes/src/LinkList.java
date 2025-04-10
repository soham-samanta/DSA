/*

class node<E>{
    E val;
    node<E> next;

    public node(E val) {
        this.val = val;
    }
}
*/

import java.util.Stack;

class node{
    int val;
    node next;

    public node(int val) {
        this.val = val;
    }
}
public class LinkList {
    public static void main(String[] args) {
        node head =new node(40);
        node n1 = new node(20);
        node n2 = new node(50);
        node n3 = new node(15);
        node n4 = new node(75);
        head.next=n1;
        n1.next=n2;
        n2.next=n3;
        n3.next=n4;

//        insert(head,2,100);
        print(head);
        System.out.println();
//        delete(head,2);
//        print(head);
//        System.out.println();
//
//        node mid = findMid(head);
//        System.out.println(mid.val);
//
//        removeDup(head);
//        print(head);
//        System.out.println(len(head));
//        node revhead = revRecur(head);
//        print(revhead);

        System.out.println(kthNode(head,0));


    }

    public static void print(node head){
        node curr=head;
        while(curr!=null){
            System.out.print(curr.val+"->");
            curr=curr.next;
        }
        System.out.print("null");
    }

    public static node insert(node head, int pos, int x){
        node newNode = new node(x);

        if(pos==0){
            newNode.next=head;
            return newNode;
        }

        node curr=head;

        for (int i = 0; i < pos-1; i++) {
            curr=curr.next;
        }
        newNode.next=curr.next;
        curr.next=newNode;

        return head;
    }

    public static node delete(node head, int pos){
        node curr=head;
        if(pos==0){
            return head.next;
        }
        for (int i = 0; i < pos-1; i++) {
            curr=curr.next;
        }
        curr.next=curr.next.next;
        return head;
    }

    public static node getMid(node head){
        node fast=head,slow=head;
        while(fast!=null && fast.next!=null){
            fast=fast.next.next;
            slow=slow.next;
        }
        return slow;
    }

    public static node removeDup(node head){
        node curr=head;
        if(head==null || head.next==null){
            return head;
        }
        while(curr.next!=null){
            if(curr.val==curr.next.val){
                curr.next=curr.next.next;
            }else{
                curr=curr.next;
            }
        }
        return head;
    }

    public static int len(node head){
        if(head==null) return 0;
        node curr=head;
        int cnt=0;
        while(curr!=null){
            cnt++;
            curr=curr.next;
        }
        return cnt;
    }

    public static node rev(node head){
        if(head==null || head.next==null) return head;
        node prev=null,curr=head;
        while(curr!=null){
            node nxt=curr.next;
            curr.next=prev;
            prev=curr;
            curr=nxt;
        }
        return prev;
    }

    public static node revRecur(node head){
        if(head==null || head.next==null) return head;
        node headOfSubProb = revRecur(head.next);
        head.next.next=head; // heads next --(-- next node -> head
        head.next=null;
        return headOfSubProb;
    }

    public static boolean palindrome(node head){
        node mid = getMid(head);
        node t2 = rev(mid);
        node t1=head;
        while(t2!=null){
            if(t1.val!=t2.val) return false;
            t1=t1.next;
            t2=t2.next;
        }
        return true;
    }

    public static node addTwoNumI(node l1, node l2){
        node dummy = new node(0);
        node curr = dummy;
        int carry=0;
        while(l1!=null || l2!=null || carry!=0){
            int sum = carry;
            if(l1!=null){
                sum+=l1.val;
                l1=l1.next;
            }
            if(l2!=null){
                sum+=l2.val;
                l2=l2.next;
            }
            carry = sum/10;
            curr.next= new node(sum%10);
            curr=curr.next;
        }
        return dummy.next;
    }

    public static node addTwoNumII(node l1 , node l2){
        node dummy = new node(0);
        node curr = dummy;
        int carry=0;
        l1 = rev(l1);
        l2 = rev(l2);
        while(l1!=null || l2!=null || carry!=0){
            int sum=carry;
            if(l1!=null){
                sum+=l1.val;
                l1=l1.next;
            }
            if(l2!=null){
                sum+=l2.val;
                l2=l2.next;
            }
            carry=sum/10;
            curr.next=new node(sum%10);
            curr=curr.next;
        }
        return rev(dummy.next);
    }

    public static node addTwoNumIIop(node l1,node l2){
        Stack<Integer>st1=new Stack<>();
        Stack<Integer>st2=new Stack<>();

        while(l1!=null){
            st1.push(l1.val);
            l1=l1.next;
        }
        while(l2!=null){
            st2.push(l2.val);
            l2=l2.next;
        }

        node head=null;
        int carry=0;

        while(!st1.isEmpty() || !st2.isEmpty() || carry!=0){
            int sum = carry;
            if(!st1.isEmpty())sum+=st1.pop();
            if(!st2.isEmpty())sum+=st2.pop();

            carry = sum/10;
            node newNode = new node(sum % 10);
            newNode.next = head; // Insert at the head
            head = newNode;
        }
        return head;
    }

    public static node reorder(node head){
        if(head==null || head.next==null) return head;

        node mid = getMid(head);
        node hf=head;
        node hs=rev(mid);

        while(hs!=null || hs.next!=null){
            node t1=hf.next;
            hf.next=hs;
            hf=t1;

            node t2=hs.next;
            hs.next=hf;
            hs=t2;


        }
        if(hf!=null){
            hf.next=null;
        }
        return head;
    }

    public node partition(node head, int x) {
        if(head==null || head.next==null) return head;
        node bh=new node(0);
        node ah=new node(0);
        node c1=bh;
        node c2=ah;

        while(head!=null){
            if(head.val<x){
                c1.next=head;
                c1=c1.next;
            }else{
                c2.next=head;
                c2=c2.next;
            }
            head=head.next;
        }
        c2.next=null;
        c1.next=ah.next;
        return bh.next;
    }


    // homework + Assignment

    public static int kthNode(node head, int b){
        int n = len(head);
        int midN = (n/2)+1;
        if(b>=midN){
            return -1;
        }
        int uptilNode = midN-b;
        node temp=head;
        for (int i = 0; i < uptilNode-1; i++) {
            temp=temp.next;
        }
        return temp.val;
    }

    public static node detectCycle(node head){
        if(head==null || head.next==null) return null;
        node f=head,s=head;
        while(f!=null && f.next!=null){
            f=f.next.next;
            s=s.next;
            if(f==s){
                s=head;
                while(f!=s){
                    f=f.next;
                    s=s.next;
                }
                return s;
            }
        }
        return null;
    }





}



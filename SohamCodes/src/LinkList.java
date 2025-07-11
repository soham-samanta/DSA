/*

class node<E>{
    E val;
    node<E> next;

    public node(E val) {
        this.val = val;
    }
}
*/

import java.util.HashSet;
import java.util.Set;
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
        node head =new node(1);
        node n1 = new node(2);
        node n2 = new node(3);
        node n3 = new node(4);
        node n4 = new node(5);
        head.next=n1;
        n1.next=n2;
        n2.next=n3;
        n3.next=n4;

        insert(head,100,40);
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

//        System.out.println(kthNode(head,0));

        rotateRight(head,2);
        print(head);

    }

    public static void print(node head){
        node curr=head;
        while(curr!=null){
            System.out.print(curr.val+"->");
            curr=curr.next;
        }
        System.out.print("null");
    }

    static node insert(node head, int x, int pos){
        node newNode = new node(x);
        if (pos == 0) {
            newNode.next = head;
            return newNode;
        }
        if (head == null) return null;

        node temp = head;
        for (int i = 0; i < pos - 1; i++) {
            if (temp == null) throw new RuntimeException("Position is out of bound");
            temp = temp.next;
        }

        newNode.next = temp.next;
        temp.next = newNode;

        return head;
    }

    public static node delete(node head, int pos){
        if (pos < 0) throw new RuntimeException("Invalid position");
        if(pos==0) return head.next;

        if(head==null) return null;
        node curr = head;
        for (int i = 0; i < pos-1; i++) {
            if (curr == null || curr.next == null) throw new RuntimeException("Position is out of bound");
            curr=curr.next;
        }
        if (curr.next == null) {
            throw new RuntimeException("Position is out of bound");
        }
        curr.next = curr.next.next;
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

    public static node reorder(node head){ // easy
        if(head==null || head.next==null) return head;
        node mid = getMid(head);
        node l2 = rev(mid);
        mid.next=null;
        node l1=head;

        while(l1!=null && l2!=null){
            node p1=l1.next;
            node p2=l2.next;

            l1.next = l2;
            l2.next=p1;

            l1=p1;
            l2=p2;
        }
        return head;
    }

    public node partition(node head, int x) {
        if(head==null || head.next==null) return head;
        node bh=new node(0);
        node ah=new node(0);

        node a=bh;
        node b=ah;

        while(head!=null){
            if(head.val<x){
                a.next=head;
                a=a.next;
            }else{
                b.next=head;
                b=b.next;
            }
            head=head.next;
        }
        b.next=null;
        a.next=ah.next;
        return bh.next;
    }

    public static node revK(node head, int k){
        if(k<=1 || head==null) return head;
        node prev=null;
        node curr=head;
        int len = len(head);
        int cnt = len/k;

        while(cnt>0){
            node nxt=curr.next;
            node last = prev;
            node newEnd = curr;

            for (int i = 0; i < k; i++) {
                curr.next=prev;
                prev=curr;
                curr=nxt;
                if(nxt!=null){
                    nxt=nxt.next;
                }
            }

            if(last!=null){
                last.next=prev;
            }else{
               head=prev;
            }

            newEnd.next=curr;
            prev=newEnd;

            cnt--;
        }
        return head;
    }

    /*** Important ***/
    static int longestPalindrome(node head){
        if(head==null) return 0;
        if(head.next==null) return 1;
        int ans = 0;

        node prev=null,curr=head;
        while(curr!=null){
            node nxt=curr.next;
            curr.next=prev;
            int cntIfCenter = cntCommon(prev,nxt);
            int lenIfCenter = cntIfCenter*2+1; //center 83138
            int cntIfNotCenter = cntCommon(curr,nxt);
            int lenIfNotCenter = cntIfNotCenter*2; // 648846

            int lenFinal = Math.max(lenIfCenter,lenIfNotCenter);
            ans = Math.max(ans,lenFinal);

            prev=curr;
            curr=nxt;
        }
        return ans;
    }
    static int cntCommon(node a , node b){
        int cnt=0;
        while(a!=null && b!=null){
            if(a.val==b.val){
                cnt++;
                a=a.next;
                b=b.next;
            }else{
                break;
            }
        }
        return cnt;
    }


    // homework ---------------------------
    public static int kthNodeFromMid(node head, int b){
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

    // Assignment ----------------------------------
    public node sortBinaryList(node head){  // only 0s & 1s
        int cnt0 = 0;
        node curr=head;
        while(curr!=null){
            if(curr.val==0){
                cnt0++;
            }
            curr=curr.next;
        }
        node temp=head;
        while(temp!=null){
            if(cnt0>0){
                temp.val=0;
                cnt0--;
            }else{
                temp.val=1;
            }
            temp=temp.next;
        }
        return head;
    }

    public node removeNthFromEnd(node head, int k){
        if(head==null) return head;
        if(k<1) return head;

        int len=len(head);
        int pos = len-k;

        if(k>=len){
            return head.next;
        }

        node curr=head;
        for (int i = 0; i < pos-1; i++) {
            curr=curr.next;
        }
        curr.next=curr.next.next;

        return head;
    }

    public static int findIntersection(node a, node b){
        int l1=len(a);
        int l2=len(b);
        int d=0;
        node p1=new node(-1);
        node p2=new node(-1);

        if(l1>l2){
            d=l1-l2;
            p1=a;
            p2=b;
        }else{
            d=l2-l1;
            p1=b;
            p2=a;
        }
        for (int i = 0; i < d; i++) {
            p1=p1.next;
        }
        while(p1!=null && p2!=null){
            if(p1==p2){
                return p1.val;
            }
            p1=p1.next;
            p2=p2.next;
        }
        return -1;
    }

    public static node mergeTwoSortedLists(node a, node b){
        if(a==null) return b;
        if(b==null) return a;

        node dummy = new node(-1);
        node temp=dummy;
        while(a!=null && b!=null){
            if(a.val<b.val){
                temp.next=a;
                a=a.next;
            }else{
                temp.next=b;
                b=b.next;
            }
            temp=temp.next;
        }

        while(a!=null){
            temp.next=a;
            temp=temp.next;
            a=a.next;
        }
        while(b!=null){
            temp.next=b;
            temp=temp.next;
            b=b.next;
        }
        return dummy.next;
    }

    public node swapPairs(node head) {
        for(node i=head; i!=null && i.next!=null;i=i.next.next){
            int temp=i.val;
            i.val=i.next.val;
            i.next.val=temp;
        }
        return head;
    }

    public static node removeDupII(node head){ //completely remove dup
        node dummy=new node(-1);
        dummy.next=head;
        node prev=dummy;
        while(head!=null){
            if(head.next!=null && head.val==head.next.val){
                while(head.next!=null && head.val==head.next.val){
                    head=head.next;
                }
                prev.next=head.next;
            }else{
                prev=prev.next;
            }
            head=head.next;
        }
        return dummy.next;
    }

    /*** IMPORTANT ***/
    public node evenReverse(node head) {
        if (head == null || head.next == null) return head;

        node evenHead = null;  // Will store even-positioned nodes

        node curr = head;

        // Step 1: Extract even-positioned nodes and push them to front (reverse)
        while (curr != null && curr.next != null) {
            node evenNode = curr.next;
            curr.next = evenNode.next; // Remove even node from original list

            evenNode.next = evenHead; // Push to front of even list (reversed)
            evenHead = evenNode;

            curr = curr.next; // Move two steps
        }

        // Step 2: Merge reversed even list back into even positions
        curr = head;
        while (evenHead != null) {
            node temp = evenHead;
            evenHead = evenHead.next;

            temp.next = curr.next;
            curr.next = temp;

            curr = curr.next.next; // Skip to next original odd-positioned node
        }

        return head;
    }
    // *** imp
    public node evenReverseSimple(node head) {

        node dummy = new node(0);

        node curr1 = head;
        node curr2 = dummy;

        while(curr1 != null){
            node nxt = curr1.next;
            if(nxt != null){
                curr1.next = nxt.next;
                curr2.next = nxt;
                curr2 = curr2.next;
                nxt.next = null;
            }
            curr1 = curr1.next;
        }

        node B = rev(dummy.next);

        curr1 = head;
        curr2 = B;
        dummy.next = null;
        node curr = dummy;
        boolean flag = true;

        while(curr1 != null && curr2 != null){
            curr.next = curr1;
            curr = curr.next;
            curr1 = curr1.next;
            curr.next = curr2;
            curr = curr.next;
            curr2 = curr2.next;
        }

        if(curr1 != null){
            curr.next = curr1;
        }

        if(curr2 != null){
            curr.next = curr2;
        }

        return dummy.next;
    }

    public static node revII(node head, int l, int r){
        if(head == null || l == r) return head;
        node prev = null;
        node curr = head;
        for(int i=0;curr!=null && i<l-1;i++){
            prev=curr;
            curr=curr.next;
        }
        node nxt=curr.next;
        node last=prev;
        node newEnd=curr;
        for(int i=0;curr!=null && i<(r-l+1);i++){
            curr.next=prev;
            prev=curr;
            curr=nxt;
            if(nxt!=null){
                nxt=nxt.next;
            }
        }

        if(last!=null){
            last.next=prev;
        }else{
            head=prev;
        }
        newEnd.next=curr;

        return head;
    }

    public static node reverseAlternateKNodes(node head, int k) {
        if (head == null || k <= 0) return head;

        node curr = head;
        node prevTail = null;
        node newHead = null;

        boolean flag = true;

        while (curr != null) {
            node segmentStart = curr;
            node prev = null;
            int count = 0;

            // Process k nodes
            while (curr != null && count < k) {
                node nxt = curr.next;
                if (flag) {
                    curr.next = prev; // Reverse
                    prev = curr;
                    curr = nxt;
                } else {
                    prev = curr; // Just advance without reversing
                    curr = nxt;
                }
                count++;
            }

            if (flag) {
                if (newHead == null) {
                    newHead = prev; // First reversed segment becomes new head
                }
                if (prevTail != null) {
                    prevTail.next = prev; // Connect previous part to current reversed
                }
                prevTail = segmentStart; // segmentStart becomes tail after reversing
            } else {
                if (prevTail != null) {
                    prevTail.next = segmentStart; // Connect previous reversed part to skipped segment
                }
                prevTail = prev; // last node of skipped segment
            }

            flag = !flag; // Toggle for next segment
        }

        return newHead != null ? newHead : head;
    }


    public static node rotateRight(node head, int x){
        if(head==null || head.next==null || x<=0) return head;
        int l=1;
        node temp=head;
        while(temp.next!=null){
            temp=temp.next;
            l++;
        }
        temp.next=head;
        node last=head;
        int skip = l-(x%l);
        for (int i = 0; i < skip-1; i++) {
            last=last.next;
        }
        head=last.next;
        last.next=null;

        return head;
    }


    public node sortList(node head) {
        if (head == null || head.next == null) {
            return head;
        }
        node mid = getMid(head);
        node left = sortList(head);
        node right = sortList(mid);
        return merge(left, right);
    }
    node merge(node l1, node l2) {
        node dummyHead = new node(-1);
        node temp = dummyHead;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                temp.next = l1;
                l1 = l1.next;
                temp = temp.next;
            } else {
                temp.next = l2;
                l2 = l2.next;
                temp = temp.next;
            }
        }
        temp.next = (l1 != null) ? l1 : l2;
        return dummyHead.next;
    }







}







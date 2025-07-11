import java.util.*;


public class Stacks {
    public static void main(String[] args) {
//        int[]arr={100,80,60,70,60,75,85}; // ans -> [1, 1, 1, 2, 1, 4, 6]
//                // 0  1  2  3  4  5  6
//        System.out.println(Arrays.toString(stockSpan(arr)));

//        String s = "a+(d-e+f)-g*h";
//        System.out.println(infixToPostfix(s));

        int[]arr = {2,7,1,4,3,5};  // -1 0 -1 2 2 4
        System.out.println(Arrays.toString(pse(arr)));
        System.out.println(Arrays.toString(nse(arr)));
        System.out.println(Arrays.toString(pge(arr)));
        System.out.println(Arrays.toString(nge(arr)));

    }


    static int[] stockSpan(int[]arr){ //till how many days price increasing
        int n = arr.length;
        int[]ans = new int[n];
        Stack<Integer>st = new Stack<>();
        ans[0] = 1;
        st.push(0);

        for (int i = 1; i < n; i++) {
            while(!st.isEmpty()){
                int top = st.peek();
                if(arr[top]>arr[i]){
                    break;
                }else{
                    st.pop();
                }
            }
            if(st.isEmpty()){
                ans[i] = i+1;
            }else{
                ans[i] = i - st.peek();
            }
            st.push(i);
        }
        return ans;
    }

    static boolean isValidParenthesis(String s){
        Stack<Character>st = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if(ch=='(' || ch=='{' || ch=='['){
                st.push(ch);
            }else{
                if(st.isEmpty()) return false; // stack is empty but "),},]" is still left
                char opening = st.pop();
                if( (opening=='(' && ch == ')')
                        || (opening=='{' && ch == '}')
                        || (opening=='[' && ch == ']') ){
                    continue;
                }else{
                    return false;
                }
            }
        }
        return st.isEmpty();
    }

    static int evalPostfix(String s){
        String[]arr = s.split(" ");
        ArrayDeque<Integer> st = new ArrayDeque<>();
        for(String e : arr){
            if(isOperator(e)){
                int second = st.pop();
                int first = st.pop();
                int ans = switch (e) {
                    case "+" -> first + second;
                    case "-" -> first - second;
                    case "*" -> first * second;
                    case "/" -> first / second;
                    case "^" -> (int) Math.pow(first, second);
                    default -> 0;
                };
                st.push(ans);
            }else{
                st.push(Integer.parseInt(e));
            }
        }
        return st.pop();
    }
    static boolean isOperator(String s){
        return s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/") || s.equals("^");
    }

    public static String infixToPostfix(String s) {
        StringBuilder sb = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            if (Character.isLetterOrDigit(c)) {
                sb.append(c);
            } else if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    sb.append(stack.pop());
                }
                stack.pop(); // Pop '('
            } else if (isOperator(c)) {
                while (!stack.isEmpty() && precedence(c) <= precedence(stack.peek())) {
                    sb.append(stack.pop());
                }
                stack.push(c);
            }
        }

        while (!stack.isEmpty()) {
            if (stack.peek() == '(') {
                return "Invalid infix expression"; // Unmatched '('
            }
            sb.append(stack.pop());
        }

        return sb.toString();
    }
    static int precedence(char operator) {
        return switch (operator) {
            case '+', '-' -> 1;
            case '*', '/' -> 2;
            case '^' -> 3;
            default -> -1;
        };
    }
    static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '^';
    }


    static int[] pse(int[]arr){
        Stack<Integer>st=new Stack<>();
        int n = arr.length;
        int[]ans = new int[n];
        for (int i = 0; i < n; i++) {
            while(!st.isEmpty() && arr[st.peek()]>=arr[i]){
                st.pop();
            }
            if(st.isEmpty()){
                ans[i]=-1;
            }else{
                ans[i] = st.peek();
            }
            st.push(i);
        }
        return ans;
    }

    static int[] pge(int[] arr) {
        int n = arr.length;
        Stack<Integer> st = new Stack<>();
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            while (!st.isEmpty() && arr[st.peek()] <= arr[i]) {  // Change comparison to <= for greater
                st.pop();
            }
            if (st.isEmpty()) {
                ans[i] = -1;
            } else {
                ans[i] = st.peek();
            }
            st.push(i);
        }
        return ans;
    }

    static int[] nse(int[]arr){ //same code as above
        int n = arr.length;
        Stack<Integer>st=new Stack<>();
        int[]ans = new int[n];
        for (int i = n-1; i >= 0; i--) { //
            while(!st.isEmpty() && arr[st.peek()]>=arr[i]){
                st.pop();
            }
            if(st.isEmpty()){
                ans[i]=n; //
            }else{
                ans[i] = st.peek();
            }
            st.push(i);
        }
        return ans;
    }

    static int[] nge(int[] arr) {
        int n = arr.length;
        Stack<Integer> st = new Stack<>();
        int[] ans = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            while (!st.isEmpty() && arr[st.peek()] <= arr[i]) {  // Change comparison to <= for greater
                st.pop();
            }
            if (st.isEmpty()) {
                ans[i] = n;
            } else {
                ans[i] = st.peek();
            }
            st.push(i);
        }
        return ans;
    }

    static int[] minMaxArr(int[]arr){
        int n=arr.length;
        int[]left = pse(arr);
        int[]right = nse(arr);
        int[]ans = new int[n];
        Arrays.fill(ans,Integer.MIN_VALUE);

        for (int i = 0; i < n; i++) {
            int wl = right[i]-left[i]-1; // wl->window length
            ans[wl-1] = Math.max(ans[wl-1],arr[i]);
        }
        // 2nd hypothesis -> still some pos are not filled
        for (int i = n-2; i >= 0 ; i--) {
            ans[i] = Math.max(ans[i],ans[i+1]);
        }

        return ans;
    }

    static int largestHistogram(int[]arr){
        int n = arr.length;
        int[]left = pse(arr);
        int[]right = nse(arr);
        int ans = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            int w = right[i]-left[i]-1;
            int area = w*arr[i];
            ans=Math.max(ans,area);
        }
        return ans;
    }

    static int largestMatrixWith1(int[][]arr){
        int row = arr.length;
        if(row==0) return 0;
        int col = arr[0].length;
        int ans = 0;
        int[]his = new int[col];
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                if(arr[i][j]==0){
                    his[j]=0;
                }else{
                    his[j]++;
                }
            }
            int currArea = largestHistogram(his);
            ans = Math.max(ans,currArea);
        }
        return ans;
    }


    /** Assignment **/
    // Step 1: Pop the top element
    // Step 2: Recursively reverse the rest of the stack
    // Step 3: Insert the top element at the bottom of the reversed smaller stack
    public void reverseStack(Stack<Integer> st) {
        if (st.isEmpty()) return; // base case

        int top = st.pop();
        reverseStack(st);
        insertAtBottom(st, top);
    }
    private void insertAtBottom(Stack<Integer> st, int e) {
        // Base case: If stack is empty, push the element
        if (st.isEmpty()) {
            st.push(e);
            return;
        }

        // Step 1: Pop the top element
        int top = st.pop();
        // Step 2: Recursively call to reach the bottom
        insertAtBottom(st, e);
        // Step 3: Push the popped element back
        st.push(top);
    }

    public char[] reverseString(char[] s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s) {
            stack.push(c);
        }

        //Pop from stack and overwrite the array
        for (int i = 0; i < s.length; i++) {
            s[i] = stack.pop();
        }

        return s; // optional if you're modifying in-place
    }

    // *** imp ***
    public String removeDuplicateLetters(String s) {
        Stack<Character> st = new Stack<>();
        boolean[] visited = new boolean[26]; // to track if character is in stack
        int[] freq = new int[26];            // to track remaining frequency

        // Count frequency of each character
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        for (char c : s.toCharArray()) { // Logic
            freq[c - 'a']--; // Decrement the remaining count
            if (visited[c - 'a']) continue; // Skip if already in stack
            // Maintain lexicographical order
            while (!st.isEmpty()
                    && c < st.peek()
                    && freq[st.peek() - 'a'] > 0) {
                char removed = st.pop();
                visited[removed - 'a'] = false; // Mark it as not in stack
            }
            st.push(c);
            visited[c - 'a'] = true;
        }

        // Build final string
        StringBuilder sb = new StringBuilder();
        for (char c : st) {
            sb.append(c);
        }
        return sb.toString();
    }

    // Imp ***
    public String removeKdigits(String s, int k) {
        Deque<Character> dq = new ArrayDeque<>();

        for (char ch : s.toCharArray()) {
            // Remove digits from the stack if they are bigger than current and we still have k to remove
            while (!dq.isEmpty() && k > 0 && dq.peekLast() > ch) {
                dq.pollLast();
                k--;
            }
            dq.offerLast(ch);
        }

        // If k > 0, remove remaining digits from end
        while (k > 0 && !dq.isEmpty()) {
            dq.pollLast();
            k--;
        }

        // Build result string
        StringBuilder result = new StringBuilder();
        while (!dq.isEmpty()) {
            result.append(dq.pollFirst());
        }

        // Remove leading zeros
        while (result.length() > 0 && result.charAt(0) == '0') {
            result.deleteCharAt(0);
        }

        // If result is empty, return "0"
        return result.length() == 0 ? "0" : result.toString();
    }


    public boolean hasRedundantBraces(String s) {
        Stack<Character> st = new Stack<>();
        for (char ch : s.toCharArray()) {
            if (ch == '(' || ch == '+' || ch == '-' || ch == '*' || ch == '/') {
                st.push(ch);
            } else if (ch == ')') {
                boolean isOperatorFound = false;

                while (!st.isEmpty() && st.peek() != '(') {
                    char top = st.pop();
                    if (top == '+' || top == '-' || top == '*' || top == '/') {
                        isOperatorFound = true;
                    }
                }

                // Pop the opening '('
                if (!st.isEmpty()) {
                    st.pop();
                }
                // If no operator was found, it's redundant
                if (!isOperatorFound) {
                    return true;
                }
            }
        }
        return false;
    }

    public int longestValidParentheses(String s) {
        Stack<Integer> st = new Stack<>();
        int maxLen = 0;
        int lastInvalid = -1;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                st.push(i);
            } else { // s.charAt(i) == ')'
                if (st.isEmpty()) {
                    lastInvalid = i;
                } else {
                    st.pop();
                    if (st.isEmpty()) {
                        maxLen = Math.max(maxLen, i - lastInvalid);
                    } else {
                        maxLen = Math.max(maxLen, i - st.peek());
                    }
                }
            }
        }
        return maxLen;
    }











}









/******* Min Stack ********/
class MinStack {
    ArrayDeque<Integer> stack = new ArrayDeque<>();
    ArrayDeque<Integer> minStact = new ArrayDeque<>();

    void push(int e){
        stack.push(e);
        if(minStact.isEmpty()){
            minStact.push(e);
            return;
        }
        if(e<=minStact.peek()){
            minStact.push(e);
        }
    }
    int pop() throws Exception {
        if(stack.isEmpty()){
            throw new Exception("Main Stack is Empty !!!");
        }
        int ans=stack.pop();
        if(ans==minStact.peek()){
            minStact.pop();
        }
        return ans;
    }
    int min() throws Exception {
        if(stack.isEmpty()){
            throw new Exception("MinStack is Empty !!!");
        }
        return minStact.peek();
    }

}



class minStackO1space{
    ArrayDeque<Integer>st=new ArrayDeque<>();
    int min=0;

    void push(int x){
        if(st.isEmpty()){
            min=x;
            st.push(x);
            return;
        }
        if(x>=min){
            st.push(x);
        }else{
            st.push(x*2-min);
            min=x;
        }
    }
    int pop() throws Exception{
        if(st.isEmpty()){
            throw new Exception("Stack is Empty !");
        }
        int x= st.pop();
        if(x>=min){
            return x;
        }else{
            int ans=min;
            min=2*min-x;
            return ans;
        }
    }
    int min() throws Exception{
        if(st.isEmpty()){
            throw new Exception("Stack is Empty !");
        }
        return min;
    }
    int peek() throws Exception{
        if(st.isEmpty()){
            throw new Exception("Stack is Empty !");
        }
        int x= st.peek();
        if(x>=min){
            return x;
        }else{
            return min;
        }
    }

}

class main{
    public static void main(String[] args) throws Exception {
//        MinStack st = new MinStack();
//        st.push(10);
//        st.push(2);
//        System.out.println(st.min());
//        st.push(1);
//        System.out.println(st.min());
//        st.pop();
//        st.push(5);
//        System.out.println(st.min());
//        st.pop();
//        st.push(2);
//        System.out.println(st.min());
//        st.push(10);
//        System.out.println(st.min());


        minStackO1space stack = new minStackO1space();
        stack.push(3);
        stack.push(2);
        stack.push(9);
        stack.push(5);
        System.out.println(stack.min());

    }
}


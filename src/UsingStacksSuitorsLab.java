import java.util.LinkedList;
import java.util.Queue;

/*
 * 
 * UsingStacksSuitorsLab
 * 
 * This class is mostly a driver for playing with Strings as palindromes, both
 * iteratively and recursively. The UsingStacksSuitorsLab class itself is a
 * runnable object, so it can be passed to a thread in our Queue demo
 * 
 * 
 */

public class UsingStacksSuitorsLab implements Runnable
{
    private static int threadCount = 0;
    private String name;

    public UsingStacksSuitorsLab()
    {
        name = "#" + threadCount++ + "Thread";
    }

    public static void main(String[] args)
    {
        String s1 = "food"; // not a palindrome
        String s2 = "racecar"; // a palindrome

        System.out.println("String1 is \"" + s1 + "\"");
        System.out.println("String2 is \"" + s2 + "\"");

        System.out.println(s1 + " reversed is: ");
        printReverse(s1);
        System.out.println(s2 + " reversed is: ");
        printReverse(s2);

        recPrintReverse(s1);
        System.out.println();
        recPrintReverse(s2);
        System.out.println();

        System.out.println(s1 + " is a palindrome: " + isPalindrome(s1));
        System.out.println(s2 + " is a palindrome: " + isPalindrome(s2));

        System.out.println(s1 + " is a palindrome(recursively): " + isPalindromeRec(s1));
        System.out.println(s2 + " is a palindrome(recursively): " + isPalindromeRec(s2));

        System.out.println("Did we build a Queue of Threads and start them? " + buildThreadQueue());

        int n = 6;
        System.out.println("For " + n + " suitors, stand in place:" + findPlaceToStand(n));

        n = 10;
        System.out.println("For " + n + " suitors, stand in place:" + findPlaceToStand(n));

    }

    public static void printReverse(String target)
    {
        // todo: use a stack
        LLStack stack = new LLStack();
        for (int i = 0; i < target.length(); i++)
        {
            stack.addToStart(target.charAt(i) + "");
        }
        String reversed = "";
        while (!stack.isEmpty())
        {
            reversed += stack.deleteHead();
        }
        System.out.println(reversed);
    }

    public static void recPrintReverse(String target)
    {
        // todo
        if (target.length() == 0)
            return;
        System.out.print(target.charAt(target.length() - 1));
        recPrintReverse(target.substring(0, target.length() - 1));
    }

    public static boolean isPalindrome(String input)
    {
        LLStack stack = new LLStack();
        for (int i = 0; i < input.length(); i++)
        {
            stack.addToStart(input.charAt(i) + "");
        }
        int idx = 0;
        while (!stack.isEmpty())
        {
            String backChar = stack.deleteHead().toString();
            if (!(input.charAt(idx) + "").equals(backChar))
            {
                return false;
            }
            idx++;
        }
        return true;
    }

    public static boolean isPalindromeRec(String sentence)
    {
        // todo
        if (sentence.length() <= 1)
            return true;
        if (sentence.charAt(0) != sentence.charAt(sentence.length() - 1))
            return false;
        return isPalindromeRec(sentence.substring(1, sentence.length() - 1));
    }

    public static int findPlaceToStand(int numSuitors)
    {
        // todo
        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= numSuitors; i++)
        {
            q.add(i);
        }

        int count = 0;

        while (q.size() > 1)
        {
            int cur = q.poll();
            if (count == 2)
            {
                // get rid of the current element.
                count = 0;
                continue;
            }
            q.add(cur);
            count++;
        }

        return q.poll();
    }

    public static boolean buildThreadQueue()
    { // returns true upon success
        Queue<Thread> q = new LinkedList<Thread>();

        // when our program starts up, it might create multiple threads to use
        q.offer(new Thread(new UsingStacksSuitorsLab()));
        q.offer(new Thread(new UsingStacksSuitorsLab()));
        q.offer(new Thread(new UsingStacksSuitorsLab()));

        System.out.println("Initial Thread order:");
        q.toString();

        // We need to iterate over our pool of threads and call start() on each
        // one
        // Make a loop that dequeues a thread, calls start on it, and //enqueues
        // it again
        // to do:
        // current = get a thread
        // current.start();
        // put the thread back
        Thread startThr = q.poll();
        startThr.start();
        q.add(startThr);
        while (q.peek() != startThr)
        {
            Thread toStart = q.poll();
            toStart.start();
            q.add(toStart);
        }

        System.out.println("Thread order after start()ing:");
        q.toString();

        return true; // on successful start
    }

    @Override
    public void run()
    {
        for (int i = 0; i < 1000; i++)
        {
            System.out.println(name + ": " + i + "th iteration");
            try
            {
                Thread.sleep(10);
            }
            catch (InterruptedException e)
            {
                // do nothing here
            }
        }
    }

}

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Yubo on 2/2/15.
 *
 *
 * greedy:
 *
 * fail if both side is greater than curr_number
 *
 * if both side is greater than curr_number, put it on the smaller side
 *
 *
 *
 */

/*test data
3
9
2 8 4 1 1 4 4 4 4
5
2 16 4 8 2
3
2 2 2
*/

public class cerc2014_E {

    public static void main(String[] args){
        new cerc2014_E().go();
    }

    Scanner in = new Scanner(System.in);

    private class block{
        int v;
        block left;
        block right;

        private block(int value){
            v = value;
            left = null;
            right = null;
        }
    }

    block tail;
    block head;

    public void go(){
        int cc = in.nextInt();

        ArrayList<ArrayList<Integer>> input = new ArrayList<ArrayList<Integer>>();

        while(cc-- >0){
            int num = in.nextInt();
            ArrayList<Integer> line = new ArrayList<Integer>();
            while(num-->0){
                line.add(in.nextInt());
            }
            input.add(line);
        }

        //done reading input
        //System.out.println(input.get(0).toString());

        for (int i = 0; i < input.size(); i++){

            ArrayList<Integer> myArray = input.get(i);

            char[] result = new char[myArray.size()]; result[0]='r'; result[1]='r';
            boolean no = false;

            block a = new block(myArray.get(0));
            head = a;
            tail = a;
            //System.out.println(head.v);

            if (myArray.size() == 1){System.out.println("r");continue;}

            block b = new block(myArray.get(1));
            append_to_right(b);

            if (myArray.size() == 2 && myArray.get(0)==myArray.get(1)){System.out.println("rr");continue;}

            //System.out.println(tail.v);

            for (int j = 2; j < myArray.size(); j++){
                //System.out.printf("#%d number is: %d \n", j, myArray.get(j));
                if (myArray.get(j) == head.v || (myArray.get(j) > tail.v && myArray.get(j) < head.v )){
                    block n = new block(myArray.get(j));
                    append_to_left(n);
                    result[j] = 'l';
                    try_compress_from_head();
                }

                else if (myArray.get(j) == tail.v || (myArray.get(j) < tail.v && myArray.get(j) > head.v ) ){
                    block n = new block(myArray.get(j));
                    append_to_right(n);
                    result[j] = 'r';
                    try_compress_from_tail();
                }

                else if (myArray.get(j) < tail.v && myArray.get(j) < head.v ){
                    if (head.v < tail.v){
                        block n = new block(myArray.get(j));
                        append_to_left(n);
                        result[j] = 'l';
                        try_compress_from_head();

                    } else {
                        block n = new block(myArray.get(j));
                        append_to_right(n);
                        result[j] = 'r';
                        try_compress_from_tail();

                    }
                }

                else if (myArray.get(j) > tail.v && myArray.get(j) > head.v ){
                    no = true;
                    //System.out.println("no");
                }

                //printlist();


            }
            //after put all blocks and compress whenever possible

            if (tail.left == null){
                System.out.println(result);
            } else {
                no = true;
            }

            if (no){
                System.out.println("no");
            }
       }





    }

    private void append_to_right(block b){
        tail.right = b;
        b.left = tail;
        tail = b;
    }

    private void append_to_left(block b){
        head.left = b;
        b.right = head;
        head = b;
    }

    private void try_compress_from_head(){
        if(head.right == null){
            return;
        }
        if (head.v == head.right.v){
            head = head.right;
            head.v = head.v*2;
            head.left = null;
            //System.out.printf("after compression from head: " );
            //printlist();
            try_compress_from_head();
        } else {
           return;
        }
    }

    private void try_compress_from_tail(){
        if(tail.left == null){
            return;
        }
        if (tail.v == tail.left.v){
            tail = tail.left;
            tail.v = tail.v*2;
            tail.right = null;
            //System.out.printf("after compression from tail: " );
            //printlist();
            try_compress_from_tail();
        } else{
            return;
        }
    }

    private void printlist(){
        block temp = head;
        while(temp != null){
            System.out.printf("%d -> ", temp.v);
            temp = temp.right;
        }
        System.out.printf("\n");
    }
}

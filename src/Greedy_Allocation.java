import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;

public class Greedy_Allocation {


    public static void main(String[] args) {

        //declartion
        int bidder_num,bid;
        String bundle;
        Bidder players[];
        ArrayList<Bidder> winner = new ArrayList<Bidder>();

        //input
        Scanner input = new Scanner(System.in);
        System.out.print("Please input bidder's number ? ");
        bidder_num = input.nextInt();
        players = new Bidder[bidder_num];

        for(int i=0;i<bidder_num;i++){
            players[i] = new Bidder("P" + (i+1));
            System.out.print("Input P" + (i+1) + "'s bid ? ");
            bid = input.nextInt();
            players[i].setbid(bid);
            System.out.print("Input p" + (i+1) + "'s bundle ? ");
            bundle = input.next();
            players[i].setbundle(bundle);
        }

//        players = new Bidder[5];
//        players[0] = new Bidder("P1");
//        players[0].setbid(54);
//        players[0].setbundle("A,C,D");
//        players[1] = new Bidder("P2");
//        players[1].setbid(63);
//        players[1].setbundle("A,B,C");
//        players[2] = new Bidder("P3");
//        players[2].setbid(93);
//        players[2].setbundle("B,D,E");
//        players[3] = new Bidder("P4");
//        players[3].setbid(70);
//        players[3].setbundle("D,E");
//        players[4] = new Bidder("P5");
//        players[4].setbid(28);
//        players[4].setbundle("A,C");

        Bidder.sort(players);

//        for(int i=0;i<players.length;i++)
//            System.out.println(players[i].getname()+" : "+players[i].getbidPerNum());

        winner.add(Bidder.max(players));

        for(int i=0;i<players.length;i++){
            boolean flag = true;
            for(int j=0;j<winner.size();j++)
                if(!Bidder.disjointSet(winner.get(j).getbundle(),players[i].getbundle())){
                    flag = false;
                    break;
                }
            if(flag)
                winner.add(players[i]);
        }

//        for(int i=0;i<winner.size();i++)
//            System.out.println(winner.get(i).getname());


        for(int i=0;i<players.length;i++){
            for(int j=0; j<winner.size();j++){
                boolean flag = true;
                if(players[i].getname().equals(winner.get(j).getname())) {
                    for (int q = i + 1; q < players.length; q++)
                        if (players[q].getchecked() && !Bidder.disjointSet(players[i].getbundle(), players[q].getbundle())) {
                            if (flag) {
                                players[i].setcritical(players[q].getbidPerNum() * players[i].getbundle().length);
                                players[q].setchecked();
                                flag = false;
                            } else {
                                players[q].setchecked();
                            }
                        }
                }
                else{
                    if(!Bidder.disjointSet(players[i].getbundle(),winner.get(j).getbundle())){
                        players[i].setcritical(winner.get(j).getbidPerNum()*players[i].getbundle().length);
                        break;
                    }
                }
            }
        }

        System.out.println("******************* Critical Value *******************");
        for(Bidder player : players)
            System.out.println(player.getname() + " : " + player.getcritical());

        System.out.println("******************* Result *******************");
        for(int i=0;i<winner.size();i++)
            System.out.println(winner.get(i).getname() + " should pay " + winner.get(i).getcritical());
        //System.out.println(player.getbid() + " : " + Arrays.toString(player.getbundle()) + " : " + player.getbidPerNum());


    }


}
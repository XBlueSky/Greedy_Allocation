public class Bidder {
    private int bid;
    private float bidPerNum,critical;
    private String [] bundle;
    private String name;
    private boolean checked  = true;

    public Bidder (String name){
        setname(name);
    }

    public void setname(String name){
        this.name = name;
    }

    public void setbid(int bid){
        this.bid = bid;
    }

    public void setbundle(String bundle){
        this.bundle = bundle.split(",");
        this.bidPerNum = (float)bid / this.bundle.length;
    }

    public void setchecked(){
        this.checked = false;
    }
    public void setcritical(float critical){
        this.critical = critical;
    }

    public String getname(){
        return this.name;
    }

    public int getbid(){
        return this.bid;
    }

    public String[] getbundle(){
        return this.bundle;
    }

    public boolean getchecked() {
        return this.checked;
    }

    public float getcritical(){
        return this.critical;
    }

    public float getbidPerNum(){
        return this.bidPerNum;
    }

    public static boolean disjointSet(String[] A,String[] B){
        for(String itemA : A)
            for(String itemB : B)
                if(itemA.equals(itemB)) return false;

        return true;
    }

	public static Bidder max(Bidder[] players){
		Bidder max = new Bidder("max");

		for(Bidder player : players)
			if(player.getbidPerNum() > max.getbidPerNum())
				max = player;

		return max;
	}

    public static void sort(Bidder[] players){
        Bidder temp = new Bidder("temp");
        for(int i=0;i<(players.length-1);i++)
            for(int j=0;j<(players.length-i-1);j++)
                if(players[j].getbidPerNum()<players[j+1].getbidPerNum()){
                    temp = players[j];
                    players[j] = players[j+1];
                    players[j+1] = temp;
                }
    }


}
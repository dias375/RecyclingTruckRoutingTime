import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        int[] D = {2, 1, 1, 1, 2};
        String[] T = {"", "PP", "PP", "GM", ""};
        Solution solution = new Solution();
        solution.solution(D, T);
    }
}

class Solution{
    public int solution(int[] D, String[] T) {
        ArrayList<House> street = new ArrayList<>();
        char P = 'P';
        char G = 'G';
        char M = 'M';


        for(int i = 0; i<D.length; i++){
            street.add(new House(i, D[i], T[i]));
        }

        Route routeP = createRoute(P, street);
        Route routeG = createRoute(G, street);
        Route routeM = createRoute(M, street);

        ArrayList<Route> recyclingRoute = new ArrayList<>();
        recyclingRoute.add(routeP);
        recyclingRoute.add(routeG);
        recyclingRoute.add(routeM);

        return slowestRecyclingRoute(recyclingRoute, street);
    }

    private int slowestRecyclingRoute(ArrayList<Route> recyclingRoute, ArrayList<House> street){
        int slowestRoute = 0;
        for(Route route : recyclingRoute){
            int routeTime = calculateRouteTime(route, street);
            if(routeTime > slowestRoute){
                slowestRoute = routeTime;
            }
        }
        return slowestRoute;
    }

    private Route createRoute(char charPGM, ArrayList<House> street){
        int lastHouse = 0;
        int truckBags = 0;
        for(House house : street){
            int houseBags = house.numberOfBags(charPGM);
            if(houseBags>0){
                lastHouse = house.getHouseIndex();
            }
            truckBags += houseBags;
        }
        return new Route(lastHouse, truckBags);
    }

    private int calculateRouteTime(Route route, ArrayList<House> street){
        int time = 0;
        time += route.getBags();
        for(int index = 0; index <= route.getLastHouseIndex(); index++){
            time += street.get(index).getTimeFromPrevious()*2;
        }
        return time;
    }

}

class Route{
    private int lastHouseIndex;
    private int bags;

    public Route(int lastHouseIndex, int bags) {
        this.lastHouseIndex = lastHouseIndex;
        this.bags = bags;
    }

    public int getLastHouseIndex() {
        return lastHouseIndex;
    }

    public void setLastHouseIndex(int lastHouseIndex) {
        this.lastHouseIndex = lastHouseIndex;
    }

    public int getBags() {
        return bags;
    }

    public void setBags(int bags) {
        this.bags = bags;
    }
}

class House{
    private int houseIndex;
    private int timeFromPrevious;
    String bags;

    public House(int houseIndex, int timeFromPrevious, String bags) {
        this.houseIndex = houseIndex;
        this.timeFromPrevious = timeFromPrevious;
        this.bags = bags;
    }

    public int numberOfBags(char charPGM){
        int bagsQuantity = 0;
        char[] arr = bags.toCharArray();
        for (char x : arr) {
            if(x == charPGM){
                bagsQuantity++;
            }
        }
        return bagsQuantity;
    }

    public int getHouseIndex() {
        return houseIndex;
    }

    public void setHouseIndex(int houseIndex) {
        this.houseIndex = houseIndex;
    }

    public int getTimeFromPrevious() {
        return timeFromPrevious;
    }

    public void setTimeFromPrevious(int timeFromPrevious) {
        this.timeFromPrevious = timeFromPrevious;
    }

    public String getBags() {
        return bags;
    }

    public void setBags(String bags) {
        this.bags = bags;
    }
}
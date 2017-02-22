package q20160904;


import java.util.*;

/**
 * Created by Administrator on 2016/12/17 0017.
 */
class Vnode<idtype> implements Comparable {
    public final idtype ID;
    public Integer distFromRoot = Integer.MAX_VALUE;
    public Vnode<idtype> prev = null;

    private final TreeMap<Vnode<idtype>, Integer> map = new TreeMap<>();

    Vnode(idtype id) {
        ID = id;
    }

    @Override
    public String toString() {
        return String.format("[ID=%s,dist=%s,prev=.. %s",ID,distFromRoot,prev);
    }

    @Override
    public int compareTo(Object o) {
        int compareDist = this.distFromRoot.compareTo(((Vnode) o).distFromRoot);
        if (compareDist == 0) {
            return this.ID.toString().compareTo(((Vnode) o).ID.toString());
        } else {
            return compareDist;
        }
    }

    public Integer distTo(Vnode<idtype> vnode) {
        assert (this.map.keySet().contains(vnode));
        return this.map.get(vnode);
    }

    public void linkedTo(Vnode<idtype> another, int dist) {
        map.put(another, dist);
    }

    public Set<Vnode<idtype>> getLinkedNodes() {
        return map.keySet();
    }
}

class Graph<idtype> {
    private HashMap<idtype, Vnode<idtype>> vnodes = new HashMap<>();

    public void addEdge(idtype aid, idtype bid, int dist) {
        if (!vnodes.keySet().contains(aid)) {
            vnodes.put(aid, new Vnode<idtype>(aid));
        }
        if (!vnodes.keySet().contains(bid)) {
            vnodes.put(bid, new Vnode<idtype>(bid));
        }
        vnodes.get(aid).linkedTo(vnodes.get(bid), dist);
        vnodes.get(bid).linkedTo(vnodes.get(aid), dist);
    }

    public TreeSet<Vnode<idtype>> Dijkstra(final idtype rootId) {
        Vnode<idtype> root;
        root = vnodes.get(rootId);
        root.distFromRoot = 0;
        TreeSet<Vnode<idtype>> set = new TreeSet<>();
        set.add(root);
        TreeMap<idtype, Vnode<idtype>> subset = new TreeMap<idtype, Vnode<idtype>>() {{
            for (Vnode<idtype> vnode : vnodes.values()) {
                if (vnode.ID != rootId) {
                    this.put(vnode.ID, vnode);
                }
            }
        }};

        for (Vnode<idtype> vnode : subset.values()) {
            if (root.getLinkedNodes().contains(vnode)) {
                vnode.prev = root;
                vnode.distFromRoot = root.distTo(vnode);
            }
        }
        int fullsize = vnodes.size();
        while (set.size() < fullsize) {
            root = Collections.min(subset.values(), new Comparator<Vnode<idtype>>() {
                @Override
                public int compare(Vnode<idtype> o1, Vnode<idtype> o2) {
                    return o1.distFromRoot.compareTo(o2.distFromRoot);
                }
            });
            subset.remove(root.ID);
            set.add(root);
            for (Vnode vnode : subset.values()) {
                if (root.getLinkedNodes().contains(vnode)) {
                    switch (vnode.distFromRoot.compareTo(root.distFromRoot + root.distTo(vnode))) {
                        case 1:
                            vnode.prev = root;
                            vnode.distFromRoot = root.distFromRoot + root.distTo(vnode);
                            break;
                        case 0:
                            if (vnode.prev.distTo(vnode) >= root.distTo(vnode)) {
                                vnode.prev = root;
                            }
                            break;
                        default:
                            break;
                    }
                }
            }
        }
        return set;


    }
}


public class Main {
    static int stoi(String s){
        return Integer.parseInt(s);
    }
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        Graph<Integer> graph = new Graph<Integer>();
        int howManyEdge = Integer.parseInt(scanner.nextLine().split(" ")[1]);
        String[] input;
        for(int i = 1;i <=howManyEdge;i++){
            input = scanner.nextLine().split(" ");
            graph.addEdge(stoi(input[0]),stoi(input[1]),stoi(input[2]));
        }
       TreeSet<Vnode<Integer>> set =  graph.Dijkstra(1);
        int total = 0;
        for(Vnode vnode:set){
            if(vnode.prev!=null){
                total+=vnode.distTo(vnode.prev);
            }
        }
        System.out.println(total);
    }
}
/**
1 7
1 2 3
1 3 5
3 2 1
2 4 2
3 4 1
3 5 10
5 4 9
 */
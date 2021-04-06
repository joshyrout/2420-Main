package ceHash;

import edu.princeton.cs.algs4.LinearProbingHashST;
import edu.princeton.cs.algs4.StdRandom;

public class Predictor {

    String context;
    LinearProbingHashST<String, MoveCounter> contextMap;

    public Predictor(){
        context = "****";
        contextMap = new LinearProbingHashST<String, MoveCounter>();
        contextMap.put(context, new MoveCounter());
    }

    public Move predict(){
        int next = 0;
        if(contextMap.contains(context)) {
            MoveCounter mc = contextMap.get(context);
            next = mc.left - mc.right;
        }
        while(next == 0){
            next = StdRandom.uniform(-1000,1000);
        }
        if(next > 0){
            return Move.LEFT;
        } else {
            return Move.RIGHT;
        }

    }

    public void recordMove(Move m){
        if(m == null) throw new NullPointerException("Cant be null");
        if(contextMap.contains(context)) contextMap.get(context).increment(m);
        else contextMap.put(context, new MoveCounter());

        if (m == Move.LEFT) context = context + "L";
        else context = context + "R";
        context = context.substring(1);
    }
}

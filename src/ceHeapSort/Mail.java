package ceHeapSort;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Comparator;

enum DeliveryType{
    GROUND,
    AIR,
    PRIORITY,
    TWO_DAY,
    ONE_DAY
}
public class Mail implements Comparable<Mail> {

    private DeliveryType type;
    private String mailCode;

    public Mail(DeliveryType type, String mailCode){
        this.type = type;
        this.mailCode = mailCode;
    }

    public Mail(){
        this.type = randomType();
        this.mailCode = randomMailCode();
    }

    private String randomMailCode(){
        CharSequence letters = "ABCDEFGHIJKLMNOPQURTUVWXYZ";
        StringBuilder mailCode = new StringBuilder();
        while(mailCode.length() < 5){
            int randInt = StdRandom.uniform(26);
            mailCode.append(letters.charAt(randInt));
        }
        return mailCode.toString();
    }

    private DeliveryType randomType(){
        int randInt = StdRandom.uniform(5);
        switch(randInt){
            case 0: return DeliveryType.GROUND;
            case 1: return DeliveryType.AIR;
            case 2: return DeliveryType.PRIORITY;
            case 3: return DeliveryType.TWO_DAY;
            case 4: return DeliveryType.ONE_DAY;
            default:System.err.println("Outside of expected cases: " + randInt);
                    return DeliveryType.GROUND;
        }
    }

    public String toString(){
        return type + "(" + mailCode + ")";
    }

    @Override
    public int compareTo(Mail o) {
        int compareType = o.type.compareTo(this.type);
        if(compareType == 0){
            compareType = this.mailCode.compareTo(o.mailCode);
        }
        return compareType;
    }
}

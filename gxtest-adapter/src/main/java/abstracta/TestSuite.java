package abstracta;

import java.util.ArrayList;

public class TestSuite {
    private long             start;
    private long             stop;
    private String          name;
    private ArrayList<Test> tests = new ArrayList<>();

    public TestSuite(long start, long stop, String name, ArrayList<Test> tests){
        this.name = name;
        this.start = start;
        this.stop = stop;
        this.tests = tests;
    }

    public long getStart(){
        return this.start;
    }

    public long getStop(){
        return this.stop;
    }

    public String getName(){
        return this.name;
    }

    public ArrayList<Test> getTests(){
        return this.tests;
    }
}

package abstracta;

import java.util.ArrayList;

public class TestSuite {
    private int             start;
    private int             stop;
    private String          name;
    private ArrayList<Test> tests = new ArrayList<>();

    public TestSuite(int start, int stop, String name, ArrayList<Test> tests){
        this.name = name;
        this.start = start;
        this.stop = stop;
        this.tests = tests;
    }

    public int getStart(){
        return this.start;
    }

    public int getStop(){
        return this.stop;
    }

    public String getName(){
        return this.name;
    }

    public ArrayList<Test> getTests(){
        return this.tests;
    }
}

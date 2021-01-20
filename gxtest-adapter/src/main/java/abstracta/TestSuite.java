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
}

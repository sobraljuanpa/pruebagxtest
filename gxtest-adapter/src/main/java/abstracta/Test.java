package abstracta;

public class Test {
    private int start;
    private int stop;
    private String name;
    private String title;
    private String status;
    private String browser;

    public Test(){

    }

    public Test(int start, int stop, String name, String title, String status, String browser){

    }

    public int getStart(){
        return this.start;
    }

    public void setStart(int start){
        this.start = start;
    }

    public int getStop(){
        return this.stop;
    }

    public void setStop(int stop){
        this.stop = stop;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getTitle(){
        return this.title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getStatus(){
        return this.status;
    }

    public void setStatus(String status){
        this.status = status;
    }

    public String getBrowser(){
        return this.browser;
    }

    public void setBrowser(String browser){
        this.browser = browser;
    }

    @Override
    public String toString(){
        return "nombre: " + this.getName() + " stop: " + this.getStop() + " browser" + this.getBrowser();
    }
}

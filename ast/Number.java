package ast;

public class Number {
    public String type;
    public long longNum;
    public double doubleNum;

    public Number(Long number){
        this.longNum = number.longValue();
        this.type = "long";
    }

    public Number(Double number){
        this.doubleNum = number.doubleValue();
        this.type = "double";
    }
}

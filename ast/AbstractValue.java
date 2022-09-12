package ast;
import java.util.*;

public class AbstractValue{
    public String abs_val;
    public String type;
    public AbstractValue(String abs_val, String type){
        this.abs_val = abs_val;
        this.type = type;
    }
}
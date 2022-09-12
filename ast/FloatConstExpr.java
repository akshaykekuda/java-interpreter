package ast;
import java.io.PrintStream;
import java.util.*;

public class FloatConstExpr extends Expr {
    public final Double fval; 
    public FloatConstExpr(Double f, Location loc) {
	super(loc);
	fval = f;
    type = "float";
    }
    public void print(PrintStream ps) {
	ps.print(fval);
    }
    public void type_check(Node tree_table) throws Exception{
        // System.out.println("Inside FloatConst Expr");
    }
    public Number evaluate(Map<String, Number> stateMap, Scanner s){
        return new Number(fval);
    } 
	public void execute(Map<String, Number> stateMap, Scanner s) throws Exception{
    }
    public String abs_evaluate(AbstractState stateMap){
        if (fval>0.){
            return "Pos";
        }
        else if (fval<0.){
            return "Neg";
        }
        else{
            return "Zero";
        }
    } 
	public void abs_execute(AbstractState stateMap) throws Exception{
    }
}

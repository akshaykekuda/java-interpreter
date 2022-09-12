package ast;
import java.io.PrintStream;
import java.util.Map;
import java.util.Scanner;

public class IntConstExpr extends Expr {
    public final Long ival; 
    public IntConstExpr(Long i, Location loc) {
	super(loc);
	ival = i;
    type = "int";
    }
    public void print(PrintStream ps) {
	ps.print(ival);
    }
    public void type_check(Node tree_table) throws Exception{
        // System.out.println("Inside Int Const Expr");
    }
    public Number evaluate(Map<String, Number> stateMap, Scanner s){
        return new Number(ival);
    }
    
    public void execute(Map<String, Number> stateMap, Scanner s) throws Exception{

    }

    public String abs_evaluate(AbstractState stateMap){
        if (ival>0) return "Pos";
        else if (ival<0) return "Neg";
        else return "Zero";
    } 
	public void abs_execute(AbstractState stateMap) throws Exception{
    }
    
}

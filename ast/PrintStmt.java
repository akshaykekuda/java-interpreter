package ast;
import java.io.PrintStream;
import java.util.*;


public class PrintStmt extends Stmt {
    public final Expr expr;
    public PrintStmt(Expr e, Location loc) {
	super(loc);
	expr = e;
    }
    public void print(PrintStream ps, String indent) { 
	ps.print(indent + "print ");
	expr.print(ps);
	ps.print(";");
    }
    public void print(PrintStream ps) { 
	print(ps,"");
    }
    public void type_check(Node tree_table) throws Exception{
        // System.out.println("inside PrintStmt");
        expr.type_check(tree_table);
    }

	public void execute(Map<String, Number> stateMap, Scanner s) throws Exception{
        Number v = expr.evaluate(stateMap, s);
        if (v.type=="long"){
            System.out.println(v.longNum);
        }
        else{
            System.out.println(v.doubleNum);
        }
    }

	public void abs_execute(AbstractState stateMap) throws Exception{
	    String v = expr.abs_evaluate(stateMap);
        System.out.println(v);
    }
}

package ast;
import java.io.PrintStream;
import java.util.Map;
import java.util.Scanner;

public class IdentExpr extends Expr {
    public final String ident; 
    public IdentExpr(String i, Location loc) {
	super(loc);
	ident = i;
    }
    public void print(PrintStream ps) {
	ps.print(ident);
    }
    public void type_check(Node tree_table) throws Exception{
        // System.out.println("Inside Ident Expr");
        type = tree_table.getType(ident, loc);
    }

    public Number evaluate(Map<String, Number> stateMap, Scanner s) throws Exception{
        if (!stateMap.containsKey(ident)){
            String msg = String.format("3Error: variable %s is uninitialized in line %d", ident, loc.line);
            throw new Exception(msg);
        }
        Number num = stateMap.get(ident);
        return num;
    }

	public void execute(Map<String, Number> stateMap, Scanner s) throws Exception{

    }

    public String abs_evaluate(AbstractState stateMap) throws Exception{
        if (!stateMap.as.containsKey(ident)){
            String msg = String.format("3Error: variable %s is uninitialized in line %d", ident, loc.line);
            throw new Exception(msg);
        }
        String abs_val = stateMap.as.get(ident).abs_val;
        return abs_val;        
    }
	public void abs_execute(AbstractState stateMap) throws Exception{

    }
}

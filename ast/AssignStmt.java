package ast;
import java.io.PrintStream;
import java.util.Map;
import java.util.Scanner;

public class AssignStmt extends Stmt {
    public final String ident;
    public final Expr expr;
    public AssignStmt(String i, Expr e, Location loc) {
	super(loc);
	ident = i;
	expr = e;
    }
    public void print(PrintStream ps, String indent) { 
	ps.print(indent + ident + " = ");
	expr.print(ps);
	ps.print(";");
    }
    public void print(PrintStream ps) {     
	print(ps,"");
    }

    public void type_check(Node tree_table) throws Exception{
        // System.out.println("inside assignment statement");
        String type = tree_table.getType(ident, loc);
        expr.type_check(tree_table);
        if (type!=expr.type){
            String msg = (String.format("Type Mismatch: In line %d, type {%s} of ident {%s} not the same as type {%s} of expr",
			loc.line,type, ident, expr.type));
			throw new Exception(msg); 
        }

    }
	public void execute(Map<String, Number> stateMap, Scanner s) throws Exception{
        Number result = expr.evaluate(stateMap, s);
        stateMap.put(ident, result);
	}
    
	public void abs_execute(AbstractState stateMap) throws Exception{
        String result = expr.abs_evaluate(stateMap);
        stateMap.addVar(ident, result, expr.type);
	}
}

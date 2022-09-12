package ast;
import java.io.PrintStream;
import java.util.*;

public class IfStmt extends Stmt {
    public final CondExpr expr; 
    public final Stmt thenstmt, elsestmt;
    public IfStmt(CondExpr e, Stmt s, Location loc) {
	super(loc);
	expr = e;
	thenstmt = s;
	elsestmt = null;
    }
    public IfStmt(CondExpr e, Stmt s1, Stmt s2, Location loc) {
	super(loc);
	expr = e;
	thenstmt = s1;
	elsestmt = s2;
    }
    public void print(PrintStream ps, String indent) { 
	ps.print(indent + "if (");
	expr.print(ps);
	ps.print(")\n");
	thenstmt.print(ps, indent+"  ");
	if (elsestmt != null) {
	    ps.print("\n" + indent + "else\n");	    
	    elsestmt.print(ps, indent + "  ");
	}
    }
    public void print(PrintStream ps) {     
	print(ps,"");
    }
    public void type_check(Node tree_table) throws Exception{
        // System.out.println("inside ifStmt class typecheck");
        // System.out.println("checking if expr");
		expr.type_check(tree_table);
        // System.out.println("checking thenstmt");
		thenstmt.type_check(tree_table);
		if (elsestmt != null){
			// System.out.println("Checking else condition");
			elsestmt.type_check(tree_table);
		}

    }

	public void execute(Map<String, Number> stateMap, Scanner s) throws Exception{
		boolean v = expr.evaluate(stateMap, s);
		if (v){
			thenstmt.execute(stateMap, s);
		}
		else if (elsestmt!=null){
			elsestmt.execute(stateMap, s);
		}

    }
	public void abs_execute(AbstractState stateMap) throws Exception{
		String v = expr.abs_evaluate(stateMap);
		if ((v=="False") || ((v=="True") && (elsestmt!=null))){
			String msg = String.format("6Error: Dead code in line %d", loc.line);
            throw new Exception(msg);
		}
		
		AbstractState stateMap2 =  stateMap.copy();
		thenstmt.abs_execute(stateMap);
		if (elsestmt!=null){
			elsestmt.abs_execute(stateMap2);
		}
		stateMap.abs_merge(stateMap2);
    }
	
}

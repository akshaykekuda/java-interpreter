package ast;
import java.io.PrintStream;
import java.util.*;

import ast.AbstractValue;

public class WhileStmt extends Stmt {
    public final CondExpr expr;
    public final Stmt body;
    public WhileStmt(CondExpr e, Stmt s, Location loc) {
	super(loc);
	expr = e;
	body = s;
    }
    public void print(PrintStream ps, String indent) { 
	ps.print(indent + "while (");
	expr.print(ps);
	ps.print(")\n");
	body.print(ps, indent + "  ");
    }
    public void print(PrintStream ps) {     
	print(ps,"");
    }
    public void type_check(Node tree_table) throws Exception{
        expr.type_check(tree_table);
        body.type_check(tree_table);
    }
	public void execute(Map<String, Number> stateMap, Scanner s) throws Exception{
        boolean result = expr.evaluate(stateMap, s);
        if (result){
            body.execute(stateMap, s);
            this.execute(stateMap, s);
        }
	}

    public void abs_execute(AbstractState stateMap)throws Exception{
        String result = expr.abs_evaluate(stateMap);
        if (result=="False"){
			String msg = String.format("6Error: Dead code in line %d", loc.line);
            throw new Exception(msg);
        }
        AbstractState prev = null;
        do{
            prev = stateMap.copy();
            body.abs_execute(stateMap);
            stateMap.abs_merge(prev);

        } while(!stateMap.equals(prev));

    }
}

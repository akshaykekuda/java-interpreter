package ast;
import java.io.PrintStream;
import java.util.Map;
import java.util.Scanner;

public class Decl extends Unit {
    public final VarDecl varDecl;
    public final Expr expr;
    public Decl(VarDecl d, Location loc) {
	super(loc);
	varDecl = d;
	expr = null;
    }
    public Decl(VarDecl d, Expr e, Location loc) {
	super(loc);
	varDecl = d;
	expr = e;
    }
    public void print(PrintStream ps, String indent) { 
	ps.print(indent);
	varDecl.print(ps); 
	if (expr != null) {
	    ps.print(" = ");
	    expr.print(ps);
	}
	ps.print(";");
    }
    public void print(PrintStream ps) {
	print(ps,"");
    }

	public void type_check(Node tree_table) throws Exception{
		// System.out.println("In Decl class inside type check");
		String ident = varDecl.ident;
		String var_type = varDecl.type;
		if (tree_table.data.containsKey(ident)){
            String msg = (String.format("TypeCheck Error: In line %d, %s is already declared earlier", loc.line, ident));
			throw new Exception(msg);
        }
		if (expr!=null){
		expr.type_check(tree_table);
		// System.out.println(String.format("After type checking expression, Type of ident %s is %s ", varDecl.ident, varDecl.type));
		String expr_type = expr.type;
		if (var_type != expr_type){
			String msg = (String.format("TypeCheck Error: In line %d, type %s of ident %s not the same as type %s of expression",
			loc.line, var_type, ident, expr_type));
			throw new Exception(msg);
		}
		}
		tree_table.data.put(ident, var_type);
		// System.out.println("Added ident type to hashmap");

	}

	public void execute(Map<String, Number> stateMap, Scanner s) throws Exception{
		String ident = varDecl.ident;
		Number result = null;
		if (expr != null){
			result = expr.evaluate(stateMap, s);
			stateMap.put(ident, result);
		}
	}
	public void abs_execute(AbstractState stateMap) throws Exception{
		String ident = varDecl.ident;
		String result = null;
		if (expr != null){
			result = expr.abs_evaluate(stateMap);
			stateMap.addVar(ident, result, expr.type);
		}
	}
}

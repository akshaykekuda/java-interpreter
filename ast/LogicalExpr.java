package ast;
import java.io.PrintStream;
import java.util.Map;
import java.util.Scanner;

public class LogicalExpr extends CondExpr {
    public static final int AND = 1;
    public static final int OR = 2;
    public static final int NOT = 3;
    public final CondExpr expr1, expr2;
    public final int op;
    public LogicalExpr(CondExpr e1, int oper, CondExpr e2, Location loc) {
	super(loc);
	expr1 = e1; 
	expr2 = e2;
	op = oper;
    }
    public LogicalExpr(CondExpr e1, int oper, Location loc) {
	this(e1,oper,null,loc); // used for NOT
    }
    public void print(PrintStream ps) {
	if (op == NOT) {
	    ps.print("!(");
	    expr1.print(ps);
	    ps.print(")");
	    return;
	}
	ps.print("(");
	expr1.print(ps);
	switch (op) { 
	case AND: ps.print("&&"); break;
	case OR: ps.print("||"); break;
	}
	expr2.print(ps);
	ps.print(")");
    }

	public void type_check(Node tree_table)throws Exception{
		// System.out.println("inside LogicalExpr class");
		expr1.type_check(tree_table);
		if (expr2!=null){
			expr2.type_check(tree_table);
		}
	}

    public boolean evaluate(Map<String, Number> stateMap, Scanner s) throws Exception{
		boolean v1 = expr1.evaluate(stateMap, s);
		if (op == NOT){
			if (v1) return false;
			else return true;
		}
		else{
			if (op==AND){
				if (v1){
					boolean v2 = expr2.evaluate(stateMap, s);
					if (v2) return true;
					else return false;
				}
				else return false;
			}
			else{
				if (v1){
					return true;
				}
				else{
					boolean v2 = expr2.evaluate(stateMap, s);
					if (v2) return true;
					else return false;
				}
			}
		}
	}

	public void execute(Map<String, Number> stateMap, Scanner s) throws Exception{

    }
	public String abs_evaluate(AbstractState stateMap) throws Exception{
		String v1 = expr1.abs_evaluate(stateMap);

			if (op == NOT){
				if (v1=="True") return "False";
				else return "True";
			}
			else{
				if (op==AND){
					if (v1=="False") return "False";
					else{
						String v2 = expr2.abs_evaluate(stateMap);
						if (v1=="True") return v2;
						else if (v2=="False") return "False";
						else return "AnyBool";
					}
				}
				else{
					if (v1=="True") return "True";
					else{
						String v2 = expr2.abs_evaluate(stateMap);
						if (v1=="False") return v2;
						else if (v2=="True") return "True";
						else return "AnyBool";
					}
				}
			}	
	}

	public void abs_execute(AbstractState stateMap) throws Exception{
	}


}

package ast;
import java.io.PrintStream;
import java.util.Map;
import java.util.Scanner;

public class UnaryMinusExpr extends Expr {
    public final Expr expr;
    public UnaryMinusExpr(Expr e, Location loc) {
	super(loc);
	expr = e;
    }
    public void print(PrintStream ps) {
	ps.print("-(");
	expr.print(ps);
	ps.print(")");
    }
    public void type_check(Node tree_table) throws Exception{
        // System.out.println("inside UnaryMinus");
        expr.type_check(tree_table);
        type=expr.type;
    }

    public Number evaluate(Map<String, Number> stateMap, Scanner s) throws Exception{
        Number v = expr.evaluate(stateMap, s);
        if (v.type=="long"){
            long num = -v.longNum;
            return new Number(num);
        }
        else{
            double num = -v.doubleNum;
            return new Number(num);
        }
    }

    public void execute(Map<String, Number> stateMap, Scanner s) throws Exception{

    }

    public String abs_evaluate(AbstractState stateMap) throws Exception{
        String v = expr.abs_evaluate(stateMap);
        if (v=="Pos") return "Neg";
        else if (v=="Neg") return "Pos";
        else return v;
    }
	public void abs_execute(AbstractState stateMap) throws Exception{

    }

}

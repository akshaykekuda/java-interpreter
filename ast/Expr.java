package ast;
import java.io.PrintStream;
import java.util.Map;
import java.util.Scanner;

public abstract class Expr extends ASTNode {
    public String type;
    public Expr(Location loc) {
	super(loc);
    }
    public abstract void type_check(Node tree_table) throws Exception;
    public abstract Number evaluate(Map<String, Number>  stateMap, Scanner s) throws Exception;
    public abstract String abs_evaluate(AbstractState  stateMap) throws Exception;

}

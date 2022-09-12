package ast;
import java.io.PrintStream;
import java.util.Map;
import java.util.Scanner;

public abstract class CondExpr extends ASTNode {
    public CondExpr(Location loc) {
	super(loc);
    }
    public abstract void type_check(Node tree_table) throws Exception;
    public abstract boolean evaluate(Map<String, Number> stateMap, Scanner s) throws Exception;
    public abstract String abs_evaluate(AbstractState stateMap) throws Exception;

}

package ast;
import java.io.PrintStream;
import java.util.Map;
import java.util.Scanner;

public abstract class Unit extends ASTNode {
    public Unit(Location loc) {
	super(loc);
    }
    public abstract void print(PrintStream ps, String ident);
    public abstract void type_check(Node tree_table) throws Exception;
    public abstract void execute(Map<String, Number> stateMap, Scanner s) throws Exception;

}

package ast;
import java.io.PrintStream;
import java.util.Map;
import java.util.Scanner;

public class Program extends ASTNode {
    public final UnitList unitList;
    public Program(UnitList ul, Location loc) {
        super(loc);
        unitList = ul;
    }
    public void print(PrintStream ps) {
	unitList.print(ps,"");
    }
    public void type_check(Node tree_table) throws Exception{
        // System.out.println("In class Program, inside typecheck");
        unitList.type_check(tree_table);
    }
    public void execute(Map<String, Number> stateMap, Scanner s) throws Exception{
        // System.out.println("In class Program, inside typecheck");
        unitList.execute(stateMap, s);
    }
    public void abs_execute(AbstractState stateMap) throws Exception{
        // System.out.println("In class Program, inside typecheck");
        unitList.abs_execute(stateMap);
    }
}

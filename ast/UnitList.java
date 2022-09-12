package ast;
import java.io.PrintStream;
import java.util.Map;
import java.util.Scanner;

public class UnitList extends ASTNode {
    public final Unit unit;
    public final UnitList unitList; 
    public UnitList(Unit u, UnitList ul, Location loc) {
        super(loc);
	unit = u;
	unitList = ul;
    }
    public UnitList(Unit u, Location loc) { 
        super(loc);
	unit = u;
        unitList = null;
    }
    public void print(PrintStream ps, String indent) {
	unit.print(ps,indent);
	ps.println();
	if (unitList != null)
	    unitList.print(ps,indent);
    }
    public void print(PrintStream ps) { 
	print(ps,""); 
    }

    public void type_check(Node tree_table) throws Exception{
        // System.out.println("In class Unitlist, inside typecheck");
        unit.type_check(tree_table);
        if (unitList != null){
            unitList.type_check(tree_table);
        }

    }
    public void execute(Map<String, Number> stateMap, Scanner s) throws Exception{
        unit.execute(stateMap, s);
        if (unitList!=null){
            unitList.execute(stateMap, s);
        }
    }
    public void abs_execute(AbstractState stateMap) throws Exception{
        unit.abs_execute(stateMap);
        if (unitList!=null){
            unitList.abs_execute(stateMap);
        }
    }
}

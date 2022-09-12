package ast;
import java.io.PrintStream;
import java.util.*;

public class BlockStmt extends Stmt {
    public final UnitList block;
    public BlockStmt(UnitList b, Location loc) {
	super(loc);
	block = b;
    }
    public void print(PrintStream ps, String indent) { 
	ps.print(indent + "{\n");
	block.print(ps, indent + "  ");
	ps.print(indent + "}");
    }
    public void print(PrintStream ps) {     
	print(ps,"");
    }
    public void type_check(Node tree_table) throws Exception{
        // System.out.println("inside Block Stmt class typecheck");
        Node node = new Node(new HashMap<String, String>());
        Node parent = tree_table.addChild(node);
        block.type_check(node);
    }

    // public Number evaluate(Map<String, Number> stateMap){

    // }
    
    public void execute(Map<String, Number> stateMap, Scanner s) throws Exception{
        block.execute(stateMap, s);
    }

    public void abs_execute(AbstractState stateMap) throws Exception{
        block.abs_execute(stateMap);
    }
}

package ast;
import java.io.PrintStream;
import java.util.Map;
import java.util.Scanner;

public class IntVarDecl extends VarDecl {
    public IntVarDecl(String i, Location loc) {
	super(i,loc);
    type = "int";
    }
    public void print(PrintStream ps) {
	ps.print("int " + ident);
    }
	public void type_check(Node tree_table) throws Exception{
        // System.out.println("inside IntVarDecl class, type check method");
	}

    public void execute(Map<String, Number> stateMap, Scanner s) throws Exception{
        
    }
    public void abs_execute(AbstractState stateMap)throws Exception{

    }
}

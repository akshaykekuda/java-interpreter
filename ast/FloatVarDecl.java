package ast;
import java.io.PrintStream;
import java.util.*;

public class FloatVarDecl extends VarDecl {
    public FloatVarDecl(String i, Location loc) {
	super(i,loc);
    type = "float";
    }
    public void print(PrintStream ps) {
	ps.print("float " + ident);
    }
	public void type_check(Node tree_table) throws Exception{
        // System.out.println("inside FloatVarDecl class, type check method");
	}

	public void execute(Map<String, Number> stateMap, Scanner s) throws Exception{

    }
	public void abs_execute(AbstractState stateMap) throws Exception{

    }
}

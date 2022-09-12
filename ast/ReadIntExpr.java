package ast;
import java.io.PrintStream;
import java.util.*;

public class ReadIntExpr extends Expr {
    public ReadIntExpr(Location loc) {
	super(loc);
    type = "int";
    }
    public void print(PrintStream ps) {
	ps.print("readint");
    }
    public void type_check(Node tree_table){
    }
    public Number evaluate(Map<String, Number> stateMap, Scanner s) throws Exception{
		if(s.hasNextLong()){
            long longVal = s.nextLong();
            return new Number(longVal);
        }
        else{
            String msg = String.format("5Error: Cannot read int in line %d", loc.line);
            throw new Exception(msg);
        }

	}

	public void execute(Map<String, Number> stateMap, Scanner s) throws Exception{

    }
    public String abs_evaluate(AbstractState stateMap)throws Exception{
        return "AnyInt";
    }
    public void abs_execute(AbstractState stateMap)throws Exception{

    }
}

package ast;
import java.io.PrintStream;
import java.util.*;

public class ReadFloatExpr extends Expr {
    public ReadFloatExpr(Location loc) {
	super(loc);
    type = "float";
    }
    public void print(PrintStream ps) {
	ps.print("readfloat");
    }
    public void type_check(Node tree_table){

    }
    public Number evaluate(Map<String, Number> stateMap, Scanner s) throws Exception{
		if (s.hasNextDouble()){
            double doubleVal = s.nextDouble();
            return new Number(doubleVal);
        }
        else{
            String msg = String.format("5Error: Cannot read float in line %d", loc.line);
            throw new Exception(msg);
        }

	}

	public void execute(Map<String, Number> stateMap, Scanner s) throws Exception{

    }
    public String abs_evaluate(AbstractState stateMap)throws Exception{
        return "AnyFloat";
    }
    public void abs_execute(AbstractState stateMap)throws Exception{

    }
}

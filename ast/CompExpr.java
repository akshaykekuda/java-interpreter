package ast;
import java.io.PrintStream;
import java.util.*;

public class CompExpr extends CondExpr {
    public static final int GE = 1;
    public static final int GT = 2;
    public static final int LE = 3;
    public static final int LT = 4;
    public static final int EQ = 5;
    public static final int NE = 6;
    public final Expr expr1, expr2;
    public final int op;
    public CompExpr(Expr e1, int oper, Expr e2, Location loc) {
	super(loc);
	expr1 = e1; 
	expr2 = e2;
	op = oper;
    }
    public void print(PrintStream ps) {
	ps.print("(");
	expr1.print(ps);
	switch (op) { 
	case GE: ps.print(">="); break;
	case GT: ps.print(">"); break;
	case LE: ps.print("<="); break;
	case LT: ps.print("<"); break;
	case EQ: ps.print("=="); break;
	case NE: ps.print("!="); break;
	}
	expr2.print(ps);
	ps.print(")");
    }
	public void type_check(Node tree_table) throws Exception{
		// System.out.println("inside compexpr");
		expr1.type_check(tree_table);
		expr2.type_check(tree_table);
		if (expr1.type!=expr2.type){
			String msg = (String.format("TypeCheck Error: Type mismatch in line %s",
			loc.line));
			throw new Exception(msg); 			
		}
	}

	public boolean evaluate(Map<String, Number> stateMap, Scanner s) throws Exception{
		Number v1 = expr1.evaluate(stateMap, s);
		Number v2 = expr2.evaluate(stateMap, s);
		boolean v=false;
		if (v1.type=="long"){
			long num1 = v1.longNum;
			long num2 = v2.longNum;			

			switch (op) { 
				case GE: v = num1>=num2; break;
				case GT: v = num1>num2; break;
				case LE: v = num1<=num2; break;
				case LT: v = num1<num2; break;
				case EQ: v = num1==num2; break;
				case NE: v = num1!=num2; break;
				}
		}
		else{
			double num1 = v1.doubleNum;
			double num2 = v2.doubleNum;
			switch (op) { 
				case GE: v = num1>=num2; break;
				case GT: v = num1>num2; break;
				case LE: v = num1<=num2; break;
				case LT: v = num1<num2; break;
				case EQ: v = num1 == num2; break;
				case NE: v = num1!=num2; break;
				}			
		}
		return v;

	}

	public void execute(Map<String, Number> stateMap, Scanner s) throws Exception{

	}
	public String abs_evaluate(AbstractState stateMap) throws Exception{
		String v1 = expr1.abs_evaluate(stateMap);
		String v2 = expr2.abs_evaluate(stateMap);
		String v = "False";
		if ((v1== "AnyInt") || (v1=="AnyFloat")|| (v2=="AnyInt") || (v2=="AnyFloat")){
			 v = "AnyBool";
		 }
		else{
			switch(op){
				case GE: v = greaterEquals(v1, v2); break;
				case GT: v = greater(v1, v2); break;
				case LE: v = lessEquals(v1, v2); break;
				case LT: v = less(v1, v2); break;
				case EQ: v = equals(v1, v2); break;
				case NE: v = notEquals(v1, v2); break;
			}
		}

		return v;		
	}

	public void abs_execute(AbstractState stateMap) throws Exception{
	}

	public String less(String val1, String val2){

		if (val1=="Neg"){
			if ((val2=="Zero") || (val2=="Pos")) return "True";
			else return "AnyBool";
		}
		else if (val1=="Pos"){
			if ((val2=="Zero") || (val2=="Neg")) return "False";
			else return "AnyBool";
		}

		else if (val1=="Zero"){
			if ((val2=="Zero") || (val2=="Neg")) return "False";
			else if (val2=="Pos") return "True";
			else return "AnyBool";
		}
		else return "AnyBool";

	}

	public String greater(String val1, String val2){

		if (val1=="Neg"){
			if ((val2=="Zero") || (val2=="Pos")) return "False";
			else return "AnyBool";
		}
		else if (val1=="Pos"){
			if ((val2=="Zero") || (val2=="Neg")) return "True";
			else return "AnyBool";
		}

		else if (val1=="Zero"){
			if (val2=="Neg") return "True";
			else if ((val2=="Zero") || (val2=="Pos")) return "False";
			else return "AnyBool";
		}
		else return "AnyBool";

	}

	public String equals(String val1, String val2){
		if ((val1=="Zero") && (val2=="Zero")) return "True";
		else if (val1==val2) return "AnyBool";
		else return "False";
	}

	public String lessEquals(String val1, String val2){
		if (val1=="Neg"){
			if (val2=="Neg") return "AnyBool";
			else return "True";
		}
		else if (val1=="Zero"){
			if (val2=="Neg") return "False";
			else return "True";
		}
		else{
			if (val2=="Pos") return "AnyBool";
			else return "False";		}
	}

	public String greaterEquals(String val1, String val2){
		if (val1=="Neg"){
			if (val2=="Neg") return "AnyBool";
			else return "False";
		}
		else if (val1=="Zero"){
			if (val2=="Pos") return "False";
			else return "True";
		}
		else{
			if (val2=="Pos") return "AnyBool";
			else return "True";
		}
	}

	public String notEquals(String val1, String val2){
		if ((val1=="Zero") && (val2=="Zero")) return "False";
		else if (val1==val2) return "AnyBool";
		else return "True";		
	}

}

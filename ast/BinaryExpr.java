package ast;
import java.io.PrintStream;
import java.util.Map;
import java.util.Scanner;

public class BinaryExpr extends Expr {
    public static final int PLUS = 1;
    public static final int MINUS = 2;
    public static final int TIMES = 3;
    public static final int DIV = 4;
    public final Expr expr1, expr2;
    public final int op;
    public BinaryExpr(Expr e1, int oper, Expr e2, Location loc) {
	super(loc);
	expr1 = e1; 
	expr2 = e2;
	op = oper;
    }
    public void print(PrintStream ps) {
	ps.print("(");
	expr1.print(ps);
	switch (op) {
	case PLUS: ps.print("+"); break;
	case MINUS: ps.print("-"); break;
	case TIMES: ps.print("*"); break;
	case DIV: ps.print("/"); break;
	}
	expr2.print(ps);
	ps.print(")");
    }
	public void type_check(Node tree_table) throws Exception{
		// System.out.println("Inside Binary Ex type checking");
		expr1.type_check(tree_table);
		expr2.type_check(tree_table);
		if(expr1.type == expr2.type){
			type = expr1.type;
		}
		else{
			String msg = (String.format("TypeCheck Error: Type mismatch in line %s",
			loc.line));
			throw new Exception(msg); 
		}
	}

	public Number evaluate(Map<String, Number> stateMap, Scanner s) throws Exception{
		Number v1 = expr1.evaluate(stateMap, s);
		Number v2 = expr2.evaluate(stateMap, s);
		Number v = null;
		if (v1.type == "long"){
			long num1 = v1.longNum;
			long num2 = v2.longNum;
			if ((op==DIV) && (num2==0)){
				String msg= String.format("4Error: Division by 0 in line %d", loc.line);
				throw new Exception(msg);
			}
			switch (op) {
				case PLUS: v =  new Number(num1 + num2); break;
				case MINUS:  v =  new  Number(num1 - num2); break;
				case TIMES:  v =  new Number(num1 * num2); break;
				case DIV:  v =  new Number((num1 / num2)); break;
				}
		}
		else{
			double num1 = v1.doubleNum;
			double num2 = v2.doubleNum;			
		
			if ((op==DIV) && (num2==0.)){
				String msg= String.format("4Error: Division by 0 in line %d", loc.line);
				throw new Exception(msg);
			}
			switch (op) {
				case PLUS: v =  new Number(num1 + num2); break;
				case MINUS:  v =  new  Number(num1 - num2); break;
				case TIMES:  v =  new Number(num1 * num2); break;
				case DIV:  v =  new Number(num1 / num2); break;
				}
		}
		return v;
		}
		
	public void execute(Map<String, Number> stateMap, Scanner s) throws Exception{
	}
	public void abs_execute(AbstractState stateMap) throws Exception{
	}

	public String abs_evaluate(AbstractState stateMap) throws Exception{
		String v1 = expr1.abs_evaluate(stateMap);
		String v2 = expr2.abs_evaluate(stateMap);
		String v = null;

		if ((op==DIV) && ((v2=="Zero")|| (v2=="AnyInt") || (v2=="AnyFloat"))){
			String msg= String.format("4Error: Division by 0 in line %d", loc.line);
			throw new Exception(msg);			
		}
		switch (op) {
			case PLUS: v =  abs_plus(v1, v2, expr1.type); break;
			case MINUS:  v =  abs_minus(v1, v2, expr1.type); break;
			case TIMES:  v =  abs_multiply(v1, v2, expr1.type); break;
			case DIV:  v =  abs_div(v1, v2, expr1.type); break;
			}
		return v;		
	}

	public String abs_plus(String v1, String v2, String type){
		if (v1==v2) return v1;
		else if (v1 =="Zero") return v2;
		else if (v2 == "Zero") return v1;
		else return (type=="int") ? "AnyInt": "AnyFloat"; 
	}
	public String abs_minus(String v1, String v2, String type){
		String AnyType = (type=="int") ? "AnyInt": "AnyFloat";
		if (v1=="Neg"){
			if ((v2=="Zero") || (v2=="Pos")) return v1;
			else return AnyType;
		}
		else if (v1=="Zero"){
			if (v2=="Neg") return "Pos";
			else if (v2=="Zero") return "Zero";
			else if (v2 == "Pos") return "Neg";
			else return AnyType;
		}
		else if (v1 == "Pos"){
			if ((v2=="Zero") || (v2=="Neg")) return v1;
			else return AnyType;
		}
		else return AnyType	;	
	}

	public String abs_multiply(String v1, String v2, String type){
		String AnyType = (type=="int") ? "AnyInt": "AnyFloat";
		if ((v1=="Zero") || (v2=="Zero")) return "Zero";
		else if (v1=="Neg"){
			if (v2=="Neg") return "Pos";
			else if (v2=="Pos") return v1;
			else return AnyType;
		}
		else if (v1=="Pos") return v2;
		else return AnyType;

	}

	public String abs_div(String v1, String v2, String type){
		if (type =="int"){
			if (v1=="Zero") return "Zero";
			else return "AnyInt";
		}
		else{
			if (v1=="Zero") return "Zero";
			else if (v1=="Neg"){
				if (v2=="Neg") return "Pos";
				else return v1;
			}
			else if (v1=="Pos"){
				if (v2=="Neg") return "Neg";
				else return v1;
			}
			else return "AnyFloat";
		}
	}

}

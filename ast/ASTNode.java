package ast;
import java.io.PrintStream;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

abstract class ASTNode {
    public final Location loc;
    ASTNode(Location loc) { this.loc = loc; }
    public abstract void print(PrintStream ps);
    public String toString() { 
	ByteArrayOutputStream b = new ByteArrayOutputStream();
	print(new PrintStream(b));
	return new String(b.toByteArray(),java.nio.charset.StandardCharsets.UTF_8);
    }
    public abstract void type_check(Node tree_table) throws Exception;
    public abstract void execute(Map<String, Number> stateMap, Scanner s) throws Exception;
    public abstract void abs_execute(AbstractState stateMap) throws Exception;

}

package interpreter;

import java.io.*;
import java.util.*;
import parser.ParserWrapper;
import ast.Program;
import ast.Node;
import ast.Number;
import ast.AbstractState;
import ast.AbstractValue;

public class Interpreter {

    // Process return codes
    public static final int EXIT_SUCCESS = 0;
    public static final int EXIT_PARSING_ERROR = 1;
    public static final int EXIT_STATIC_CHECKING_ERROR = 2;
    public static final int EXIT_UNINITIALIZED_VAR_ERROR = 3;
    public static final int EXIT_DIV_BY_ZERO_ERROR = 4;
    public static final int EXIT_FAILED_STDIN_READ = 5;
    public static final int EXIT_DEAD_CODE = 6;
    
    public static void main(String[] args) {
        String filename = args[0];
        Program astRoot = null;
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(filename));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        try {
	    astRoot = ParserWrapper.parse(reader);
        } catch (Exception ex) {
            Interpreter.fatalError("Uncaught parsing error: " + ex, Interpreter.EXIT_PARSING_ERROR);
        }

    
	// for debugging
	astRoot.print(System.out);

    Node tree_table = new Node(new HashMap<String, String>());

    try{
    astRoot.type_check(tree_table);
    } 
    catch (Exception ex) {
        String error_msg = ex.getMessage();
            Interpreter.fatalError(error_msg, EXIT_STATIC_CHECKING_ERROR);
        }

        
    AbstractState stateMap = new AbstractState(new HashMap<String, AbstractValue>());

    try{
        astRoot.abs_execute(stateMap);
        } 
        catch (Exception ex) {
            String error_msg = ex.getMessage();
                int code = error_msg.charAt(0) - '0';
                String msg = error_msg.substring(1);
                Interpreter.fatalError(msg, code);
            }

    }
    
    public static void fatalError(String message, int processReturnCode) {
        System.out.println(message);
        System.exit(processReturnCode);
    }


}

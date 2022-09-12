package ast;
import java.util.*;

public class Node {
    public Map<String, String> data; //data for storage
    public List<Node> children;//array will keep children
    public Node parent;//parent to start the tree

    public Node(Map data) {
        children = new ArrayList<Node>();
        this.data = data;
    }

    public Node addChild(Node node) {
        // System.out.println("Adding child node");
        children.add(node);
        node.parent = this;
        return this;
    }
    public String getType(String ident, Location loc) throws Exception{
        Node curr = this;
        while (curr !=null && !curr.data.containsKey(ident)){
            curr = curr.parent;
        }
        if(curr == null){
			String msg = (String.format("TypeCheck Error: In line %d, No declaration for ident %s found",
			loc.line,ident));
			throw new Exception(msg);        
            }
        String type = curr.data.get(ident);
        return type;
    }
}

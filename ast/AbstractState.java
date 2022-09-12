package ast;
import java.util.*;

public class AbstractState {
    public Map<String, AbstractValue> as; //map of states
    public AbstractState(Map as) {
        this.as = as;
    }
    public void addVar(String var, String abs_val, String type){
        this.as.put(var, new AbstractValue(abs_val, type));
    }
    public void abs_merge(AbstractState as1) throws Exception{
            Set<String> set1 = this.as.keySet();
            Set<String> set2 = as1.as.keySet();
            set1.retainAll(set2);            
            for(String var : this.as.keySet()){
                String abs_val = this.as.get(var).abs_val;
                String abs_val1 = as1.as.get(var).abs_val;
                String type = this.as.get(var).type;
                String result_val = merge(abs_val, abs_val1, type);
                this.as.put(var, new AbstractValue(result_val, type));    
            }
    }

    public String merge(String x, String y, String type){
        if ((x=="Neg") && (y=="Neg")) return "Neg";
        else if ((x=="Zero") && (y=="Zero")) return "Zero";
        else if ((x=="Pos") && (y=="Pos")) return "Pos";
        else return (type=="int") ? "AnyInt": "AnyFloat";
        
    }
    public AbstractState copy(){
        Map<String, AbstractValue> mp = new HashMap<String, AbstractValue>();
        for(String var : this.as.keySet()){
            mp.put(var, this.as.get(var));
        }
        return new AbstractState(mp);
    }

    public boolean equals(AbstractState as1){
        for (String key: this.as.keySet()){
            if (this.as.get(key).abs_val != as1.as.get(key).abs_val) return false;
        }
        return true;
    }

}


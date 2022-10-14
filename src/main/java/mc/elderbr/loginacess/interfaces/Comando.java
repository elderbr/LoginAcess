package mc.elderbr.loginacess.interfaces;

public interface Comando {

    default String getCommand(String[] args){
        StringBuilder builder = new StringBuilder();
        for(String x : args){
            builder.append(x.concat(" "));
        }
        return builder.toString().trim();
    }

}

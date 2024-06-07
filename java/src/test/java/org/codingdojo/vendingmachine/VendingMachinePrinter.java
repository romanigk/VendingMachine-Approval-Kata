package org.codingdojo.vendingmachine;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class VendingMachinePrinter {

    private final int columns;
    private final VendingMachine machine;

    public VendingMachinePrinter(VendingMachine machine) {
        this.columns = 40;
        this.machine = machine;
    }

    public String print(){
        StringBuilder text = new StringBuilder();
        text.append("VendingMachine\n");
        var fields = new LinkedHashMap<String, String>();

        fields.put("Display", machine.display());
        fields.put("Balance", "" + machine.balance());
        fields.put("Coins", format(machine.coins()));

        for (var field : fields.entrySet())
        {
            text.append(formatLineWithWhitespace(field.getKey(), field.getValue()));
        }
        return text.toString();
    }

    private String format(Integer[] value)
    {
        var data = Arrays.stream(value).map(String::valueOf)
                .collect(Collectors.joining(","));
        return "[" + data + "]";
    }

    private String format(Map<String, Integer> value)
    {
        var data = value.entrySet().stream().map( e -> e.toString())
                .collect(Collectors.joining(","));
        return "{" + data + "}";
    }

    private String formatLineWithWhitespace(String name, String value){
        int whitespaceSize = columns - name.length() - value.length();
        return String.format("%s%s%s\n",
                name, " ".repeat(Math.max(0, whitespaceSize)), value);
    }
}

package entity.root.info;

import lombok.Data;

@Data
public class Tzinfo {

    private String name;
    private String abbr;
    private boolean dst;
    private int offset;
}

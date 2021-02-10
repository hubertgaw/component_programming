package pl.comp;

import java.util.ListResourceBundle;

public class Authors extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        return new Object[][] {
                { "1.", "Hubert Gawłowski"},
                { "2.", "Dominik Jabłoński"}
        };
    }
}

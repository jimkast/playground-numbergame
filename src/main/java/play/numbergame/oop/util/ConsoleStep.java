package play.numbergame.oop.util;

import java.io.BufferedReader;
import java.io.IOException;

public interface ConsoleStep {
    String enter();

    String process(BufferedReader input) throws InvalidInputException, IOException;
}

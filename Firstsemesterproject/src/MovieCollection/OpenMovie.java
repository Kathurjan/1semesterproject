package MovieCollection;

import java.io.IOException;

public class OpenMovie {
    public void testIt()
        {
            Runtime runtime = Runtime.getRuntime();
            try {
                String[] command = {"cmd.exe", "/k", "Start", "\\test.mp4"};
                System.out.println("I am doing things");
                Process p =  runtime.exec(command);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
}

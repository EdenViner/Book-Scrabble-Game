package test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Pattern;

public class IOSearcher
{
    public static boolean search(String word, String... filesNames) throws IOException
    {
        // .+*{}
        // ami*t
        Pattern pattern = Pattern.compile("\\b" + Pattern.quote(word) + "\\b",
                Pattern.CASE_INSENSITIVE);

        for (String curFileName: filesNames)
        {
            BufferedReader reader = null;
            try
            {
                reader = new BufferedReader(new FileReader(curFileName));
                String line;

                while ((line = reader.readLine()) != null)
                {
                    if (pattern.matcher(line).find())
                        return true;
                }
            }
            finally {
                if (reader != null)
                {
                    reader.close();
                }
            }
        }
        return false;
    }
}

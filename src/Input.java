import java.io.*;
import java.lang.*;
import java.util.ArrayList;

class Input
{
    public static void getCommand()
    {
        String strCommand;

        try
        {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

            do
            {
                System.out.println();
                System.out.println("Please enter in a command (For a list of valid commands type 'help' or '?'):");

                strCommand = bufferedReader.readLine().toLowerCase();

                ResourceManager.commandParseAndCallExecute(strCommand);
            }
            while (!strCommand.equals("close") && !strCommand.equals("exit"));

            bufferedReader.close();
        }
        catch (Exception exception)
        {
            // Do nothing
        }
    }

    public static Boolean getConfirmation()
    {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        Boolean bConfirmation = false;
        Boolean bValidAnswer = false;
        String strInput;

        try
        {
            System.out.println("");
            System.out.println("Are you sure? (Enter 'y' or 'yes' to confirm, 'n' or 'no' to abort)");

            do
            {
                strInput = bufferedReader.readLine().trim().toLowerCase();
                if (strInput.equals("yes") || strInput.equals("no") || strInput.equals("y") || strInput.equals("n"))
                {
                    bValidAnswer = true;
                }
            }
            while (!bValidAnswer);

            if (strInput.equals("yes") || strInput.equals("y"))
            {
                bConfirmation = true;
            }
        }
        catch (Exception exception)
        {
            // Do nothing
        }

        return bConfirmation;
    }

    public static ArrayList<Integer> getKillList(Integer intResourcesToKillSize)
    {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Integer> arrInt = new ArrayList<>(0);
        Boolean bAbort = false;
        String strInput;

        try
        {
            System.out.println();
            System.out.println("Please enter the index or indices of the resources to kill (can be single entry, comma delimited, or array). Enter 'all' to kill all processes listed above or enter 'abort' or 'a' to bail.");

            strInput = input.readLine().trim().toLowerCase();
            if (strInput.equals("a") || strInput.equals("abort"))
            {
                bAbort = true;
            }
            else
            {
                if (!strInput.equals("all"))
                {
                    // Strips invalid characters
                    String strTempSanitized = Input.sanitizeString(strInput);

                    if (strTempSanitized.replaceAll(",", "").length() > 0)
                    {
                        String[] arrTemp = strTempSanitized.split(",");
                        for (String strTemp : arrTemp)
                        {
                            if (strTemp.trim().length() > 0)
                            {
                                boolean bFound = false;
                                for(Integer intTemp : arrInt)
                                {
                                    if(intTemp == Integer.parseInt(strTemp))
                                    {
                                        bFound = true;
                                        break;
                                    }
                                }
                                if (!bFound)
                                {
                                    arrInt.add(Integer.parseInt(strTemp));
                                }
                            }
                        }
                    }
                    else
                    {
                        System.out.println("");
                        System.out.println("You did not entered in a valid set of process to kill.");
                    }
                }
                else
                {
                    System.out.println("");
                    System.out.println("Killing the following resources listed below:");
                    for (int x = 0; x < intResourcesToKillSize; x++)
                    {
                        arrInt.add(x);
                    }
                }
            }
        }
        catch(Exception exception)
        {
            // Do nothing
        }

        if(bAbort)
        {
            arrInt.add(-1);
        }

        return arrInt;
    }

    private static String sanitizeString(String strInput)
    {
        return strInput.replaceAll("[a-zA-Z!@#$%^&*()|?}{<>./~` ]", "").replaceAll("\\[", ""). replaceAll("]", "").replaceAll("-", "");
    }

    public static String waitForReturn()
    {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String strInput = "";
        try
        {
            Boolean bReturn = false;
            do
            {
                strInput = bufferedReader.readLine();
                if(strInput.trim().length() == 0 || strInput.trim().toLowerCase().equals("a") || strInput.trim().toLowerCase().equals("all"))
                {
                    bReturn = true;
                }
            }
            while(!bReturn);
        }
        catch (Exception exception)
        {
            // Do nothing
        }

        return strInput.trim().toLowerCase();
    }
}
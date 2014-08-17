import java.lang.*;
import java.util.*;

//***** CLASS: ResourceManager.java
// PURPOSE
//  * Contains constructor, accessor, mutator, and other methods for the ResourceManager class
// RESULTS
//  * Main program output
// NOTES
//  * Executes main program (public static void main( String [] args))
// HISTORY
//  * Created by SEK 08/01/2014
// SOURCE
class ResourceManager
{
    //***** CLASS: ResourceManager.java / METHOD: MAIN / METHOD TYPE: Main
    // PURPOSE
    //  * To execute the program and display some output to the user
    // PARAMETERS
    //  * NONE
    // RESULT
    //  * Displays output to user and executes the main program
    // HISTORY
    //  * Created by SEK 08/01/2014
    // SOURCE
    public static void main ( String [] args )
    {
        System.out.println("Welcome to resource manager!");
        System.out.println();

        Input.getCommand();
    }
    //*****

    public static ArrayList<Resource> cleanKillList(ArrayList<Integer> arrInput, ArrayList<Resource> arrResourcesToKill)
    {
        ArrayList<Resource> arrResourcesToKillCleaned = new ArrayList<>(0);

        for(Integer intIndex : arrInput)
        {
            if(intIndex >= 0 &&  intIndex < arrResourcesToKill.size())
            {
                arrResourcesToKillCleaned.add(arrResourcesToKill.get(intIndex));
            }
        }

        return arrResourcesToKillCleaned;
    }

    public static void commandParseAndCallExecute(String strCommand)
    {
        String[] arrCommand = strCommand.split(" ");
        ArrayList<String> arrFlags = new ArrayList<>(0);
        String strCommandRoot = arrCommand[0];
        Integer x;
        Integer intIndexLastFlag = 0;
        Boolean bFound = false;

        // Iterates through the split string
        for(String temp : arrCommand)
        {
            // A flag will start with - and will only be two char (i.e.: -a, -b, etc)
            if(temp.indexOf('-') == 0 && temp.length() >= 2 && temp.length() <= 4)
            {
                for(x = 0; x < arrFlags.size(); x++)
                {
                    if(temp.equals(arrFlags.get(x)))
                    {
                        bFound = true;
                        break;
                    }
                }
                if(!bFound)
                {
                    arrFlags.add(temp);
                    intIndexLastFlag++;
                }
            }
            else if(temp.indexOf('?') == 0 && temp.length() == 1)
            {
                for(x = 0; x < arrFlags.size(); x++)
                {
                    if(temp.equals(arrFlags.get(x)))
                    {
                        bFound = true;
                        break;
                    }
                }
                if(!bFound)
                {
                    arrFlags.add(temp);
                    intIndexLastFlag++;
                }
            }

            // Currently only support two flags per command
            if(intIndexLastFlag > 1)
            {
                break;
            }
        }

        StringBuilder strSearchCriteria = new StringBuilder(0);
        for(x = intIndexLastFlag + 1; x < arrCommand.length; x++)
        {
            strSearchCriteria.append(arrCommand[x]);
        }

        Boolean bUseRegEx = false;
        Boolean bExactMatch = false;
        if(arrFlags.size() >= 2)
        {
            if(arrFlags.get(1).toLowerCase().equals("-r"))
            {
                bUseRegEx = true;
            }
            else if(arrFlags.get(1).toLowerCase().equals("-e"))
            {
                bExactMatch = true;
            }
        }

        commandExecute(strCommandRoot, arrFlags, strSearchCriteria.toString(), bUseRegEx, bExactMatch);
    }

    private static void commandExecute(String strCommandRoot, ArrayList<String> arrFlags, String strSearchCriteria, Boolean bUseRegEx, Boolean bExactMatch)
    {
        switch (strCommandRoot)
        {
            case "about":
                Display.about();
                break;
            case "search":
                if(arrFlags.size() >= 1)
                {
                    switch(arrFlags.get(0).toLowerCase())
                    {
                        case "?": // View help for the search command
                            Display.helpSearch();
                            break;
                        case "-a":
                            View.all(strSearchCriteria, bUseRegEx, bExactMatch);
                            break;
                        case "-n":
                            View.byName(strSearchCriteria, bUseRegEx, bExactMatch);
                            break;
                        case "-p":
                            View.byPID(strSearchCriteria, bUseRegEx, bExactMatch);
                            break;
                        case "-sna":
                            View.bySessionName(strSearchCriteria, bUseRegEx, bExactMatch);
                            break;
                        case "-snu":
                            View.bySessionNum(strSearchCriteria, bUseRegEx, bExactMatch);
                            break;
                        case "-m":
                            if(strSearchCriteria.contains(">") || strSearchCriteria.contains("<") || strSearchCriteria.contains(">=") || strSearchCriteria.contains("<=") || strSearchCriteria.contains("="))
                            {
                                if (strSearchCriteria.replaceAll("[<>=,]", "").matches("^[0-9]\\d*$"))
                                {
                                    Integer intMemUsage = Integer.parseInt(strSearchCriteria.replaceAll("[<>=,]", ""));
                                    if (strSearchCriteria.startsWith(">="))
                                    {
                                        View.byMemUsage(intMemUsage, ">=");
                                    }
                                    else if (strSearchCriteria.startsWith("<="))
                                    {
                                        View.byMemUsage(intMemUsage, "<=");
                                    }
                                    else if (strSearchCriteria.startsWith("<"))
                                    {
                                        View.byMemUsage(intMemUsage, "<");
                                    }
                                    else if (strSearchCriteria.startsWith(">"))
                                    {
                                        View.byMemUsage(intMemUsage, ">");
                                    }
                                    else if (strSearchCriteria.startsWith("="))
                                    {
                                        View.byMemUsage(intMemUsage, "=");
                                    }
                                    else
                                    {
                                        System.out.print("Invalid search criteria entered. Example of proper use: search -m >32889");
                                    }
                                }
                                else
                                {
                                    System.out.print("Invalid search criteria entered. Example of proper use: search -m >32889");
                                }
                            }
                            else
                            {
                                System.out.print("Invalid search criteria entered. Example of proper use: search -m >32889");
                            }
                            break;
                        case "-s":
                            View.byStatus(strSearchCriteria, bUseRegEx, bExactMatch);
                            break;
                        case "-u":
                            View.byUserName(strSearchCriteria, bUseRegEx, bExactMatch);
                            break;
                        case "-c":
                            if(strSearchCriteria.contains(">") || strSearchCriteria.contains("<") || strSearchCriteria.contains(">=") || strSearchCriteria.contains("<=") || strSearchCriteria.contains("="))
                            {
                                if (strSearchCriteria.replaceAll("[<>=]", "").matches("([0-9]+):([0-5][0-9]):([0-5][0-9])"))
                                {
                                    if (strSearchCriteria.startsWith(">="))
                                    {
                                        View.byCPUTime(strSearchCriteria.replaceAll("[<>=]", ""), ">=");
                                    }
                                    else if (strSearchCriteria.startsWith("<="))
                                    {
                                        View.byCPUTime(strSearchCriteria.replaceAll("[<>=]", ""), "<=");
                                    }
                                    else if (strSearchCriteria.startsWith("<"))
                                    {
                                        View.byCPUTime(strSearchCriteria.replaceAll("[<>=]", ""), "<");
                                    }
                                    else if (strSearchCriteria.startsWith(">"))
                                    {
                                        View.byCPUTime(strSearchCriteria.replaceAll("[<>=]", ""), ">");
                                    }
                                    else if (strSearchCriteria.startsWith("="))
                                    {
                                        View.byCPUTime(strSearchCriteria.replaceAll("[<>=]", ""), "=");
                                    }
                                    else
                                    {
                                        System.out.print("Invalid search criteria entered. Example of proper use: search -c >03:28:89");
                                    }
                                }
                                else
                                {
                                    System.out.print("Invalid search criteria entered. Example of proper use: search -c >03:28:89");
                                }
                            }
                            else
                            {
                                System.out.print("Invalid search criteria entered. Example of proper use: search -c >03:28:89");
                            }
                            break;
                        case "-w":
                            View.byWindowTitle(strSearchCriteria, bUseRegEx, bExactMatch);
                            break;
                        default:
                            System.out.println("Unrecognized flag for search command, please try again. Type 'search ?' for a list a of valid flags.");
                            break;
                    }
                }
                else
                {
                    System.out.println("Can't execute command without a flag, please try again. Type 'search ?' for a list a of valid flags.");
                }
                break;
            case "view":
                if(arrFlags.size() >= 1)
                {
                    switch(arrFlags.get(0))
                    {
                        case "?": // View help for the view command
                            Display.helpView();
                            break;
                        case "-a": // View all resources on a system
                            View.all();
                            break;
                        case "-b": // View all resources with a bad status, high memory usage, or long cpu time
                            View.bad();
                            break;
                        case "-bs": // View all resources in a bad status
                            View.badStatus();
                            break;
                        case "-hm": // View all resources with high memory usage
                            View.highMemUsage();
                            break;
                        case "-lc": // View all resources with a long CPU time
                            View.longCPUTime();
                            break;
                        default:
                            System.out.println("Unrecognized flag for view command, please try again. Type 'view ?' for a list a of valid flags.");
                            break;
                    }
                }
                else
                {
                    System.out.println("Can't execute command without a flag, please try again. Type 'view ?' for a list a of valid flags.");
                }
                break;
            case "clean":
                if(arrFlags.size() >= 1)
                {
                    switch(arrFlags.get(0))
                    {
                        case "?": // View help for the clean command
                            Display.helpClean();
                            break;
                        case "-a":
                            Clean.all(strSearchCriteria, bUseRegEx, bExactMatch);
                            break;
                        case "-b":
                            Clean.bad();
                            break;
                        case "-n":
                            Clean.byName(strSearchCriteria, bUseRegEx, bExactMatch);
                            break;
                        case "-p":
                            Clean.byPID(strSearchCriteria, bUseRegEx, bExactMatch);
                            break;
                        case "-sna":
                            Clean.bySessionName(strSearchCriteria, bUseRegEx, bExactMatch);
                            break;
                        case "-snu":
                            Clean.bySessionNum(strSearchCriteria, bUseRegEx, bExactMatch);
                            break;
                        case "-m":
                            if(strSearchCriteria.contains(">") || strSearchCriteria.contains("<") || strSearchCriteria.contains(">=") || strSearchCriteria.contains("<=") || strSearchCriteria.contains("="))
                            {
                                if (strSearchCriteria.replaceAll("[<>=,]", "").matches("^[0-9]\\d*$"))
                                {
                                    Integer intMemUsage = Integer.parseInt(strSearchCriteria.replaceAll("[<>=,]", ""));
                                    if (strSearchCriteria.startsWith(">="))
                                    {
                                        Clean.byMemUsage(intMemUsage, ">=");
                                    }
                                    else if (strSearchCriteria.startsWith("<="))
                                    {
                                        Clean.byMemUsage(intMemUsage, "<=");
                                    }
                                    else if (strSearchCriteria.startsWith("<"))
                                    {
                                        Clean.byMemUsage(intMemUsage, "<");
                                    }
                                    else if (strSearchCriteria.startsWith(">"))
                                    {
                                        Clean.byMemUsage(intMemUsage, ">");
                                    }
                                    else if (strSearchCriteria.startsWith("="))
                                    {
                                        Clean.byMemUsage(intMemUsage, "=");
                                    }
                                    else
                                    {
                                        System.out.print("Invalid search criteria entered. Example of proper use: search -m >32889");
                                    }
                                }
                                else
                                {
                                    System.out.print("Invalid search criteria entered. Example of proper use: search -m >32889");
                                }
                            }
                            else
                            {
                                System.out.print("Invalid search criteria entered. Example of proper use: search -m >32889");
                            }
                            break;
                        case "-s":
                            Clean.byStatus(strSearchCriteria, bUseRegEx, bExactMatch);
                            break;
                        case "-u":
                            Clean.byUserName(strSearchCriteria, bUseRegEx, bExactMatch);
                            break;
                        case "-c":
                            if(strSearchCriteria.contains(">") || strSearchCriteria.contains("<") || strSearchCriteria.contains(">=") || strSearchCriteria.contains("<=") || strSearchCriteria.contains("="))
                            {
                                if (strSearchCriteria.replaceAll("[<>=]", "").matches("([0-9]+):([0-5][0-9]):([0-5][0-9])"))
                                {
                                    if (strSearchCriteria.startsWith(">="))
                                    {
                                        Clean.byCPUTime(strSearchCriteria.replaceAll("[<>=]", ""), ">=");
                                    }
                                    else if (strSearchCriteria.startsWith("<="))
                                    {
                                        Clean.byCPUTime(strSearchCriteria.replaceAll("[<>=]", ""), "<=");
                                    }
                                    else if (strSearchCriteria.startsWith("<"))
                                    {
                                        Clean.byCPUTime(strSearchCriteria.replaceAll("[<>=]", ""), "<");
                                    }
                                    else if (strSearchCriteria.startsWith(">"))
                                    {
                                        Clean.byCPUTime(strSearchCriteria.replaceAll("[<>=]", ""), ">");
                                    }
                                    else if (strSearchCriteria.startsWith("="))
                                    {
                                        Clean.byCPUTime(strSearchCriteria.replaceAll("[<>=]", ""), "=");
                                    }
                                    else
                                    {
                                        System.out.print("Invalid search criteria entered. Example of proper use: search -c >03:28:89");
                                    }
                                }
                                else
                                {
                                    System.out.print("Invalid search criteria entered. Example of proper use: search -c >03:28:89");
                                }
                            }
                            else
                            {
                                System.out.print("Invalid search criteria entered. Example of proper use: search -c >03:28:89");
                            }
                            break;
                        case "-w":
                            Clean.byWindowTitle(strSearchCriteria, bUseRegEx, bExactMatch);
                            break;
                        case "-bs":
                            Clean.badStatus();
                            break;
                        case "-hm":
                            Clean.highMemUsage();
                            break;
                        case "-lc":
                            Clean.longCPUTime();
                            break;
                        default:
                            System.out.println("Unrecognized flag for clean command, please try again. Type 'clean ?' for a list a of valid flags.");
                            break;
                    }
                }
                else
                {
                    System.out.println("Can't execute command without a flag, please try again. Type 'clean ?' for a list a of valid flags.");
                }
                break;
            case "help":
            case "?":
                Display.help();
                break;
            case "close":
            case "exit":
                System.out.println();
                System.out.println("Exiting app...");
                break;
            default:
                System.out.println("Unrecognized command, please try again. For a list of valid commands and flags type 'help' or '?'");
                break;
        }
    }

    public static void kill(ArrayList<Resource> arrResourcesToKill)
    {
        try
        {
            System.out.println();
            for(Resource resource : arrResourcesToKill)
            {
                Runtime.getRuntime().exec("taskkill /F /IM " + resource.getName()).waitFor();
                System.out.println("Killing process with name: " + resource.getName() + " and PID: " + resource.getPID() + "...please wait...");
                if(resource.getName().equals("explorer.exe"))
                {
                    System.out.println("Restarting explorer.exe...");
                    Runtime.getRuntime().exec("explorer.exe");
                }
            }
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
    }

    //***** CLASS: ResourceManager.java / METHOD: resourcesParse
    // PURPOSE
    //  * To gather the information for all of the currently running resources and to determine which ones are taking up > 100000K of memory
    // PARAMETERS
    //  * arrResources (array list) - the array of resources gathered from task manager or the linux equivalent
    // RESULT
    //  * Returns an array list of resources that are taking up > 100000K of memory
    //  * No visible output to user
    // HISTORY
    //  * Created by SEK 08/09/2014
    // SOURCE
    public static ArrayList<Resource> parse(ArrayList<String> arrResources)
    {

        ArrayList<Resource> arrAllResources = new ArrayList<>(0);
        ArrayList<String> tempResourcesSplit = new ArrayList<>(0);
        ArrayList<String> tempResourceInfo = new ArrayList<>(0);

        for(String strResource : arrResources)
        {
            tempResourceInfo.clear();
            tempResourcesSplit.clear();

            Resource newResource = new Resource();
            newResource.create();

            Integer y;
            String[] temp = strResource.split("  ");

            // Cleans array of additional entries
            for(y = 0; y < temp.length; y++)
            {
                // If the value in the array at the y index is empty (no chars other than "")
                if(temp[y].trim().length() != 0)
                {
                    tempResourcesSplit.add(temp[y]);
                }
            }

            // Parses resource name
            tempResourceInfo.add(tempResourcesSplit.get(0).trim());

            // Parses PID info
            temp = tempResourcesSplit.get(1).split(" ");
            for(y = 0;y < temp.length; y++)
            {
                if(temp[y].trim().length() != 0)
                {
                    tempResourceInfo.add(temp[y].trim());
                    break;
                }
            }
            // Parses session name info
            // Starts at y + 1 to skip previously search parts
            for(int n = y + 1;n < temp.length; n++)
            {
                if(temp[n].trim().length() != 0)
                {
                    tempResourceInfo.add(temp[n].trim());
                    break;
                }
            }

            // Parses the session number info - removes spaces
            tempResourceInfo.add(tempResourcesSplit.get(2).trim());

            // Parses the memory usage and status info - removes spaces, comma, and K and splits at space
            temp = tempResourcesSplit.get(3).replaceAll(",", "").replaceAll("K", "").split(" ");
            for(y = 0;y < temp.length; y++)
            {
                if(temp[y].equals("Not") && temp[y + 1].equals("Responding"))
                {
                    tempResourceInfo.add(temp[y] + ' ' + temp[y+1]);
                    y++;
                }
                else
                {
                    if (temp[y].trim().length() > 0)
                    {
                        tempResourceInfo.add(temp[y]);
                    }
                }
            }

            // Parses the uer name info
            tempResourceInfo.add(tempResourcesSplit.get(4).trim());

            // Parses the CPU Time and Window Title info
            temp = tempResourcesSplit.get(5).split(" ");
            for(y = 0;y < temp.length; y++)
            {
                if(temp[y].trim().length() > 0)
                {
                    tempResourceInfo.add(temp[y]);
                }
            }

            newResource.setName(tempResourceInfo.get(0));
            newResource.setPID(tempResourceInfo.get(1));
            newResource.setSessionName(tempResourceInfo.get(2));
            newResource.setSessionNum(tempResourceInfo.get(3));
            Integer intMemoryUsage = Integer.parseInt(tempResourceInfo.get(4).trim());
            newResource.setMemUsage(intMemoryUsage);
            newResource.setStatus(tempResourceInfo.get(5).trim());
            newResource.setUserName(tempResourceInfo.get(6));
            newResource.setCPUTime(tempResourceInfo.get(7));
            newResource.setWindowTitle(tempResourceInfo.get(8));

            arrAllResources.add(newResource);
        }

        return arrAllResources;
    }
    //*****
}
//*****
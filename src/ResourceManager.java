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
    public static void main(String[] args)
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
            if(intIndex >= 0 && intIndex < arrResourcesToKill.size())
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
                    if (temp.equals(arrFlags.get(x)))
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
            strSearchCriteria.append(arrCommand[x] + " ");
        }

        Boolean bUseRegEx = false;
        Boolean bExactMatch = false;
        Boolean bUseCustomVal = false;
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
            else if(arrFlags.get(1).toLowerCase().equals("-c"))
            {
                bUseCustomVal = true;
            }
        }

        commandExecute(strCommandRoot, arrFlags, strSearchCriteria.toString(), bUseRegEx, bExactMatch, bUseCustomVal);
    }

    private static void commandExecute(String strCommandRoot, ArrayList<String> arrFlags, String strSearchCriteria, Boolean bUseRegEx, Boolean bExactMatch, Boolean bUseCustomVal)
    {
        switch(strCommandRoot)
        {
            case "about":
                Display.about();
                break;
            case "search":
                if (arrFlags.size() >= 1)
                {
                    switch (arrFlags.get(0).toLowerCase())
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
                                if(strSearchCriteria.replaceAll("[<>=,]", "").matches("^[0-9]\\d*$"))
                                {
                                    if(strSearchCriteria.replaceAll("[<>=,]", "").trim().length() < 10)
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
                                        System.out.println("Invalid search criteria entered.  Please enter in a number < 1,000,000,000");
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
                                if(strSearchCriteria.replaceAll("[<>=]", "").matches("([0-9]+):([0-5][0-9]):([0-5][0-9])"))
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
                                        System.out.print("Invalid search criteria entered. Example of proper use: search -c >03:28:00");
                                    }
                                }
                                else
                                {
                                    System.out.print("Invalid search criteria entered. Example of proper use: search -c >03:28:00");
                                }
                            }
                            else
                            {
                                System.out.print("Invalid search criteria entered. Example of proper use: search -c >03:28:00");
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
                    System.out.println("Invalid or no command flag, please try again. Type 'search ?' for a list a of valid flags.");
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
                            if(bUseCustomVal)
                            {
                                String[] arrTemp = strSearchCriteria.split(" ");
                                Boolean bFoundCPUTime = false;
                                Boolean bFoundMemUsageVal = false;
                                Integer intMemUsageVal = 100000;
                                String strCPUTime = "";
                                for(String strTemp : arrTemp)
                                {
                                    if(strTemp.replaceAll(",", "").matches("^[0-9]\\d*$"))
                                    {
                                        if(strTemp.length() < 10)
                                        {
                                            intMemUsageVal = Integer.parseInt(strTemp.replaceAll(",", ""));
                                            bFoundMemUsageVal = true;
                                        }
                                    }

                                    if(strTemp.matches("([0-9]+):([0-5][0-9]):([0-5][0-9])"))
                                    {
                                        strCPUTime = strTemp;
                                        bFoundCPUTime = true;
                                    }
                                }

                                if(bFoundCPUTime && bFoundMemUsageVal)
                                {
                                    View.bad(intMemUsageVal, strCPUTime);
                                }
                                else
                                {
                                    System.out.print("Invalid search criteria entered. Example of proper use: view -b -c 90000 03:28:00");
                                }
                            }
                            else
                            {
                                View.bad(100000, "00:45:00");
                            }
                            break;
                        case "-bs": // View all resources in a bad status
                            View.badStatus();
                            break;
                        case "-hm": // View all resources with high memory usage
                            if(bUseCustomVal)
                            {
                                if(strSearchCriteria.trim().replaceAll(",", "").matches("^[0-9]\\d*$"))
                                {
                                    if(strSearchCriteria.trim().replaceAll(",", "").length() < 10)
                                    {
                                        Integer intMemUsage = Integer.parseInt(strSearchCriteria.trim().replaceAll(",", ""));
                                        View.highMemUsage(intMemUsage);
                                    }
                                    else
                                    {
                                        System.out.println("Invalid search criteria entered.  Please enter in a number < 1,000,000,000");
                                    }
                                }
                                else
                                {
                                    System.out.print("Invalid search criteria entered. Example of proper use: view -hm -c 90000");
                                }
                            }
                            else
                            {
                                View.highMemUsage(100000);
                            }
                            break;
                        case "-lc": // View all resources with a long CPU time
                            if(bUseCustomVal)
                            {
                                if(strSearchCriteria.trim().matches("([0-9]+):([0-5][0-9]):([0-5][0-9])"))
                                {
                                    View.longCPUTime(strSearchCriteria.trim());
                                }
                                else
                                {
                                    System.out.print("Invalid search criteria entered. Example of proper use: view -lc -c 03:28:00");
                                }
                            }
                            else
                            {
                                View.longCPUTime("00:45:00");
                            }
                            break;
                        default:
                            System.out.println("Unrecognized flag for view command, please try again. Type 'view ?' for a list a of valid flags.");
                            break;
                    }
                }
                else
                {
                    System.out.println("Invalid or no command flag, please try again. Type 'view ?' for a list a of valid flags.");
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
                            if(bUseCustomVal)
                            {
                                String[] arrTemp = strSearchCriteria.split(" ");
                                Boolean bFoundCPUTime = false;
                                Boolean bFoundMemUsageVal = false;
                                Integer intMemUsageVal = 100000;
                                String strCPUTime = "";
                                for(String strTemp : arrTemp)
                                {
                                    if(strTemp.replaceAll(",", "").matches("^[0-9]\\d*$"))
                                    {
                                        if(strTemp.replaceAll(",", "").length() < 10)
                                        {
                                            intMemUsageVal = Integer.parseInt(strTemp.replaceAll(",", ""));
                                            bFoundMemUsageVal = true;
                                        }
                                    }
                                    else if(strTemp.matches("([0-9]+):([0-5][0-9]):([0-5][0-9])"))
                                    {
                                        strCPUTime = strTemp;
                                        bFoundCPUTime = true;
                                    }
                                }

                                if(bFoundCPUTime && bFoundMemUsageVal)
                                {
                                    Clean.bad(intMemUsageVal, strCPUTime);
                                }
                                else
                                {
                                    System.out.print("Invalid search criteria entered. Example of proper use: view -b -c 90000 03:28:00");
                                }
                            }
                            else
                            {
                                Clean.bad(100000, "00:45:00");
                            }
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
                                if(strSearchCriteria.replaceAll("[<>=,]", "").matches("^[0-9]\\d*$"))
                                {
                                    if(strSearchCriteria.replaceAll("[<>=,]", "").length() < 10)
                                    {
                                        Integer intMemUsage = Integer.parseInt(strSearchCriteria.replaceAll("[<>=,]", ""));
                                        if(strSearchCriteria.startsWith(">="))
                                        {
                                            Clean.byMemUsage(intMemUsage, ">=");
                                        }
                                        else if(strSearchCriteria.startsWith("<="))
                                        {
                                            Clean.byMemUsage(intMemUsage, "<=");
                                        }
                                        else if(strSearchCriteria.startsWith("<"))
                                        {
                                            Clean.byMemUsage(intMemUsage, "<");
                                        }
                                        else if(strSearchCriteria.startsWith(">"))
                                        {
                                            Clean.byMemUsage(intMemUsage, ">");
                                        }
                                        else if(strSearchCriteria.startsWith("="))
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
                                        System.out.println("Invalid search criteria entered.  Please enter in a number < 1,000,000,000");
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
                                if(strSearchCriteria.replaceAll("[<>=]", "").matches("([0-9]+):([0-5][0-9]):([0-5][0-9])"))
                                {
                                    if(strSearchCriteria.startsWith(">="))
                                    {
                                        Clean.byCPUTime(strSearchCriteria.replaceAll("[<>=]", ""), ">=");
                                    }
                                    else if(strSearchCriteria.startsWith("<="))
                                    {
                                        Clean.byCPUTime(strSearchCriteria.replaceAll("[<>=]", ""), "<=");
                                    }
                                    else if(strSearchCriteria.startsWith("<"))
                                    {
                                        Clean.byCPUTime(strSearchCriteria.replaceAll("[<>=]", ""), "<");
                                    }
                                    else if(strSearchCriteria.startsWith(">"))
                                    {
                                        Clean.byCPUTime(strSearchCriteria.replaceAll("[<>=]", ""), ">");
                                    }
                                    else if(strSearchCriteria.startsWith("="))
                                    {
                                        Clean.byCPUTime(strSearchCriteria.replaceAll("[<>=]", ""), "=");
                                    }
                                    else
                                    {
                                        System.out.print("Invalid search criteria entered. Example of proper use: search -c >03:28:00");
                                    }
                                }
                                else
                                {
                                    System.out.print("Invalid search criteria entered. Example of proper use: search -c >03:28:00");
                                }
                            }
                            else
                            {
                                System.out.print("Invalid search criteria entered. Example of proper use: search -c >03:28:00");
                            }
                            break;
                        case "-w":
                            Clean.byWindowTitle(strSearchCriteria, bUseRegEx, bExactMatch);
                            break;
                        case "-bs":
                            Clean.badStatus();
                            break;
                        case "-hm":
                            if(bUseCustomVal)
                            {
                                if(strSearchCriteria.trim().replaceAll(",", "").matches("^[0-9]\\d*$"))
                                {
                                    if(strSearchCriteria.trim().replaceAll(",", "").length() < 10)
                                    {
                                        Integer intMemUsage = Integer.parseInt(strSearchCriteria.trim().replaceAll(",", ""));
                                        Clean.highMemUsage(intMemUsage);
                                    }
                                    else
                                    {
                                        System.out.println("Invalid search criteria entered.  Please enter in a number < 1,000,000,000");
                                    }
                                }
                                else
                                {
                                    System.out.print("Invalid search criteria entered. Example of proper use: view -hm -c 100,000");
                                }
                            }
                            else
                            {
                                Clean.highMemUsage(10000);
                            }
                            break;
                        case "-lc":
                            if(bUseCustomVal)
                            {
                                if(strSearchCriteria.trim().matches("([0-9]+):([0-5][0-9]):([0-5][0-9])"))
                                {
                                    Clean.longCPUTime(strSearchCriteria.trim());
                                }
                                else
                                {
                                    System.out.print("Invalid search criteria entered. Example of proper use: view -lc -c 03:28:00");
                                }
                            }
                            else
                            {
                                Clean.longCPUTime("00:45:00");
                            }
                            break;
                        default:
                            System.out.println("Unrecognized flag for clean command, please try again. Type 'clean ?' for a list a of valid flags.");
                            break;
                    }
                }
                else
                {
                    System.out.println("Invalid or no command flag, please try again. Type 'clean ?' for a list a of valid flags.");
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
                if (!resource.getWindowTitle().trim().toLowerCase().contains("resourcemanager.jar"))
                {
                    if(!resource.getName().trim().toLowerCase().contains("java.exe"))
                    {
                        if(!resource.getName().trim().toLowerCase().contains("idea.exe"))
                        {
                            if (System.getProperty("os.name").toLowerCase().contains("windows"))
                            {
                                Runtime.getRuntime().exec("taskkill /F /IM " + resource.getName()).waitFor();
                            }
                            else
                            {
                                Runtime.getRuntime().exec("kill -9 " + resource.getName()).waitFor();
                            }
                            System.out.println("Killing process with name: " + resource.getName() + " and PID: " + resource.getPID() + "...please wait...");
                            if (resource.getName().equals("explorer.exe"))
                            {
                                System.out.println("Restarting explorer.exe...");
                                Runtime.getRuntime().exec("explorer.exe");
                            }
                        }
                        else
                        {
                            System.out.println("Not letting the app kill the intellij IDE...");
                        }
                    }
                    else
                    {
                        System.out.println("Not letting the app kill java.exe...");
                    }
                }
                else
                {
                    System.out.println("Not letting the app kill itself...");
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
        StringBuilder strWindowTitle = new StringBuilder(0);
        StringBuilder strSessionName = new StringBuilder(0);
        Integer x;
        Integer y;

        for(String strResource : arrResources)
        {
            tempResourceInfo.clear();
            tempResourcesSplit.clear();
            strWindowTitle.setLength(0);
            strSessionName.setLength(0);

            Resource newResource = new Resource();
            newResource.create();

            String[] arrDoubleSpaceSplit = strResource.split("  ");

            // Cleans array of additional entries
            for(String strTemp : arrDoubleSpaceSplit)
            {
                // If the value in the array at the y index is empty (no chars other than "")
                if(strTemp.trim().length() != 0)
                {
                    tempResourcesSplit.add(strTemp);
                }
            }

            // Parses the name
            tempResourceInfo.add(tempResourcesSplit.get(0).trim());

            // Parses the PID
            String[] arrSingleSpaceSplit = tempResourcesSplit.get(1).split(" ");
            for(x = 0;x < arrSingleSpaceSplit.length; x++)
            {
                if(arrSingleSpaceSplit[x].trim().length() != 0)
                {
                    break;
                }
            }
            tempResourceInfo.add(arrSingleSpaceSplit[x].trim());

            // Parses session name
            // Starts at x + 1 to skip previously searched parts
            for(y = x + 1;y < arrSingleSpaceSplit.length; y++)
            {
                if(arrSingleSpaceSplit[y].trim().length() != 0)
                {
                    strSessionName.append(arrSingleSpaceSplit[y]);
                }
            }
            tempResourceInfo.add(strSessionName.toString());

            // Parses the session number
            tempResourceInfo.add(tempResourcesSplit.get(2).trim());

            // Parses the memory usage and status - removes spaces, comma, and K and splits at space
            arrSingleSpaceSplit = tempResourcesSplit.get(3).replaceAll(",", "").replaceAll("K", "").split(" ");
            for(x = 0;x < arrSingleSpaceSplit.length; x++)
            {
                if(arrSingleSpaceSplit[x].equals("Not") && arrSingleSpaceSplit[x + 1].equals("Responding"))
                {
                    tempResourceInfo.add(arrSingleSpaceSplit[x] + ' ' + arrSingleSpaceSplit[x + 1]);
                    y++;
                }
                else
                {
                    if(arrSingleSpaceSplit[x].trim().length() > 0)
                    {
                        tempResourceInfo.add(arrSingleSpaceSplit[x]);
                    }
                }
            }

            // Parses the user name
            tempResourceInfo.add(tempResourcesSplit.get(4).trim());

            // Parses the CPU Time
            arrSingleSpaceSplit = tempResourcesSplit.get(5).split(" ");
            for(x = 0; x < arrSingleSpaceSplit.length; x++)
            {
                if(arrSingleSpaceSplit[x].matches("([0-9]+):([0-5][0-9]):([0-5][0-9])"))
                {
                    tempResourceInfo.add(arrSingleSpaceSplit[x]);
                    break;
                }
            }

            // Parses the window title
            for(y  = x + 1; y < arrSingleSpaceSplit.length;y++)
            {
                strWindowTitle.append(arrSingleSpaceSplit[y]).append(' ');
            }
            if(tempResourcesSplit.size() > 6)
            {
                for(x = 5; x < tempResourcesSplit.size(); x++)
                {
                    strWindowTitle.append(tempResourcesSplit.get(x)).append(' ');
                }
            }
            tempResourceInfo.add(strWindowTitle.toString());

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
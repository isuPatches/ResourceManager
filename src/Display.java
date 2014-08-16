import java.util.*;

class Display
{
    public static void about()
    {
        System.out.println();
        System.out.println("Author: Patches Klinefelter");
        System.out.println("Version: 1.0");
        System.out.println("Created August 2015");
    }

    public static void help()
    {
        System.out.println();
        System.out.println("##############################################################################");
        System.out.println("############################### Commands #####################################");
        System.out.println("##############################################################################");

        System.out.println();
        System.out.println("You may enter in any of the following root commands followed by a valid flag(s)");
        System.out.println();
        System.out.println("Ex1 (viewing all processes):");
        System.out.println("      view -a");
        System.out.println("Ex2 (searching where user name contains or equals given criteria):");
        System.out.println("      search -u some search criteria");
        System.out.println("Ex3 (searching for any resource where any field matches a regex):");
        System.out.println("      search -a -r ^sv.*");
        System.out.println("Ex4 (search for resources where the memory is over 50,000 K):");
        System.out.println("      search -m >50000");
        System.out.println();

        System.out.println("Other commands: ?, About, Close, Exit, and Help");
        System.out.println();

        helpView();

        helpSearch();

        helpClean();
    }

    public static void helpClean()
    {
        System.out.println();
        System.out.println("Clean");
        System.out.println("-------");
        System.out.printf("%-10s %-5s %n", "FLAG", "DESCRIPTION");
        System.out.printf("%-10s %-5s %n", "  ?", "Displays a list of valid flags and help for the clean command");
        System.out.printf("%-10s %-5s %n", "  -a", "Allows user to kill resources where any field contains criteria");
        System.out.printf("%-10s %-5s %n", "  -a -r", "Allows user to kill resources by any field using regex");
        System.out.printf("%-10s %-5s %n", "  -b", "Allows user to kill resources that are deemed possibly bad");
        System.out.printf("%-10s %-5s %n", "  -n", "Allows user to kill resources where name contains criteria");
        System.out.printf("%-10s %-5s %n", "  -n -r", "Allows user to kill resources by name using regex");
        System.out.printf("%-10s %-5s %n", "  -p", "Allows user to kill resources where PID contains criteria");
        System.out.printf("%-10s %-5s %n", "  -p -r", "Allows user to kill resources by PID using regex");
        System.out.printf("%-10s %-5s %n", "  -sna", "Allows user to kill resources where session name contains criteria");
        System.out.printf("%-10s %-5s %n", "  -sna -r", "Allows user to kill resources by session name using regex");
        System.out.printf("%-10s %-5s %n", "  -snu", "Allows user to kill resources where session num contains criteria");
        System.out.printf("%-10s %-5s %n", "  -snu -r", "Allows user to kill resources by session num using regex");
        System.out.printf("%-10s %-5s %n", "  -m", "Allows user to kill resources by comparing memory to a value");
        System.out.printf("%-10s %-5s %n", "  -s", "Allows user to kill resources where status contains criteria");
        System.out.printf("%-10s %-5s %n", "  -s -r", "Allows user to kill resources by status using regex");
        System.out.printf("%-10s %-5s %n", "  -u", "Allows user to kill resources where user name contains criteria");
        System.out.printf("%-10s %-5s %n", "  -u -r", "Allows user to kill resources by user name using regex");
        System.out.printf("%-10s %-5s %n", "  -c", "Allows user to kill resources by comparing CPU time to a value");
        System.out.printf("%-10s %-5s %n", "  -w", "Allows user to kill resources where window title contains criteria");
        System.out.printf("%-10s %-5s %n", "  -w -r", "Allow user to kill resources by window title using regex");
        System.out.printf("%-10s %-5s %n", "  -bs", "Allows user to kill resources in a bad status");
        System.out.printf("%-10s %-5s %n", "  -hm", "Allows user to kill resources with high memory usage");
        System.out.printf("%-10s %-5s %n", "  -lc", "Allows user to kill resources with a long CPU time");
    }

    public static void helpSearch()
    {
        System.out.println();
        System.out.println("Search");
        System.out.println("-------");
        System.out.printf("%-10s %-5s %n", "FLAG", "DESCRIPTION");
        System.out.printf("%-10s %-5s %n", "  ?", "Displays a list of valid flags and help for the search command");
        System.out.printf("%-10s %-5s %n", "  -a", "Search for resources where any field contains given criteria");
        System.out.printf("%-10s %-5s %n", "  -a -r", "Search for resources where any field matches a regex");
        System.out.printf("%-10s %-5s %n", "  -n", "Search for resources where name contains given criteria");
        System.out.printf("%-10s %-5s %n", "  -n -r", "Search for resources where the name matches a regex");
        System.out.printf("%-10s %-5s %n", "  -p", "Search for resource where PID contains given criteria");
        System.out.printf("%-10s %-5s %n", "  -p -r", "Search for resources where the PID matches a regex");
        System.out.printf("%-10s %-5s %n", "  -sna", "Search for resources where session name contains given criteria");
        System.out.printf("%-10s %-5s %n", "  -sna -r", "Search for resources where the session name matches a regex");
        System.out.printf("%-10s %-5s %n", "  -snu", "Search for resources where session num contains given criteria");
        System.out.printf("%-10s %-5s %n", "  -snu -r", "Search for resources where session num matches a regex");
        System.out.printf("%-10s %-5s %n", "  -m", "Search for resources by comparing memory usage to a value");
        System.out.printf("%-10s %-5s %n", "  -s", "Search for resources where status contains given criteria");
        System.out.printf("%-10s %-5s %n", "  -s -r", "Search for resources where the status matches a regex");
        System.out.printf("%-10s %-5s %n", "  -u", "Search for resources where user name contains given criteria");
        System.out.printf("%-10s %-5s %n", "  -u -r", "Search for resources where the user name matches a regex");
        System.out.printf("%-10s %-5s %n", "  -c", "Search for resources by comparing CPU time to a value");
        System.out.printf("%-10s %-5s %n", "  -w", "Search for resources where window title contains given criteria");
        System.out.printf("%-10s %-5s %n", "  -w -r", "Search for resources where the window title matches a regex");
    }

    public static void helpView()
    {
        System.out.println();
        System.out.println("View");
        System.out.println("-------");
        System.out.printf("%-10s %-5s %n", "FLAG", "DESCRIPTION");
        System.out.printf("%-10s %-5s %n", "  ?", "Displays a list of valid flags and help for the view command");
        System.out.printf("%-10s %-5s %n", "  -a", "Displays all of the currently running resources on a system");
        System.out.printf("%-10s %-5s %n", "  -b", "Displays resources with bad status, high memory, or long CPU time");
        System.out.printf("%-10s %-5s %n", "  -bs", "Displays resources that currently seem to have a bad status");
        System.out.printf("%-10s %-5s %n", "  -hm", "Displays resources that currently have high memory usage");
        System.out.printf("%-10s %-5s %n", "  -lc", "Displays resources that have a long CPU time");
    }

    public static void resources(ArrayList<Resource> arrResources, Boolean bWithIndex)
    {
        if(arrResources.size() > 0)
        {
            System.out.println();
            int intIndex = 0;
            for (Resource resource : arrResources)
            {
                if(bWithIndex)
                {
                    System.out.println("[" + intIndex + "] " + resource.toString());
                }
                else
                {
                    System.out.println(resource.toString());
                }
                intIndex++;
            }
        }
        else
        {
            System.out.println();
            System.out.println("No resources to display");
        }
    }
}
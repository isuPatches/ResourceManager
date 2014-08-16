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
        System.out.println("########################################################################################################");
        System.out.println("############################################### Commands ###############################################");
        System.out.println("########################################################################################################");

        System.out.println();
        System.out.println("You may enter in any of the following root commands followed by a valid flag(s)");
        System.out.println();
        System.out.println("Ex1 (viewing all processes): view -a");
        System.out.println("Ex2 (searching where user name contains or equals given criteria): search -u some search criteria");
        System.out.println("Ex3 (searching for any resource where any field matches a regex): search -a -r ^sv.*");
        System.out.println("Ex4 (search for resources where the memory is over 50,000 K): search -m >50000");
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
        System.out.printf("%-10s %-5s %n", "  ?", "To display a list of valid flags and descriptions for the clean command");
        System.out.printf("%-10s %-5s %n", "  -a", "To gather a list of resources where any field contains or equals the search criteria and allow the user to selective kill them");
        System.out.printf("%-10s %-5s %n", "  -b", "To gather a list of resources that have a bad status, high memory usage, or a long CPU time and allow the user to selective kill them");
        System.out.printf("%-10s %-5s %n", "  -a -r", "To gather a list of resources where any field matches a regex and allow the user to selective kill them");
        System.out.printf("%-10s %-5s %n", "  -n", "To gather a list of resources where the name contains or equals the search criteria and allow the user to selective kill them");
        System.out.printf("%-10s %-5s %n", "  -n -r", "To gather a list of resources where the name matches a regex and allow the user to selective kill them");
        System.out.printf("%-10s %-5s %n", "  -p", "To gather a list of resources where the PID contains or equals the search criteria and allow the user to selective kill them");
        System.out.printf("%-10s %-5s %n", "  -p -r", "To gather a list of resources where the PID matches a regex and allow the user to selective kill them");
        System.out.printf("%-10s %-5s %n", "  -sna", "To gather a list of resources where the session name contains or equals the search criteria and allow the user to selective kill them");
        System.out.printf("%-10s %-5s %n", "  -sna -r", "To gather a list of resources where the session name matches a regex and allow the user to selective kill them");
        System.out.printf("%-10s %-5s %n", "  -snu", "To gather a list of resources where the session number contains or equals the search criteria and allow the user to selective kill them");
        System.out.printf("%-10s %-5s %n", "  -snu -r", "To gather a list of resources where the session number matches a regex and allow the user to selective kill them");
        System.out.printf("%-10s %-5s %n", "  -m", "To gather a list of resources where the memory is >, <, <=, >=, == to a certain value and allow the user to selective kill them");
        System.out.printf("%-10s %-5s %n", "  -s", "To gather a list of resources where the status contains or equals the search criteria and allow the user to selective kill them");
        System.out.printf("%-10s %-5s %n", "  -s -r", "To gather a list of resources where the status matches a regex and allow the user to selective kill them");
        System.out.printf("%-10s %-5s %n", "  -u", "To gather a list of resources where the user name contains or equals the search criteria and allow the user to selective kill them");
        System.out.printf("%-10s %-5s %n", "  -u -r", "To gather a list of resources where the user name matches a regex and allow the user to selective kill them");
        System.out.printf("%-10s %-5s %n", "  -c", "To gather a list of resources where the CPU time is >, <, <=, >=, == to a certain value and allow the user to selective kill them");
        System.out.printf("%-10s %-5s %n", "  -w", "To gather a list of resources where the window title contains or equals the search criteria and allow the user to selective kill them");
        System.out.printf("%-10s %-5s %n", "  -w -r", "To gather a list of resources where the window title matches a regex and allow the user to selective kill them");
        System.out.printf("%-10s %-5s %n", "  -bs", "To gather a list of resources that have a bad status and allow the user to selective kill them");
        System.out.printf("%-10s %-5s %n", "  -hm", "To gather a list of resources that have high memory usage and allow the user to selective kill them");
        System.out.printf("%-10s %-5s %n", "  -lc", "To gather a list of resources that have a long CPU time and allow the user to selective kill them");
    }

    public static void helpSearch()
    {
        System.out.println();
        System.out.println("Search");
        System.out.println("-------");
        System.out.printf("%-10s %-5s %n", "FLAG", "DESCRIPTION");
        System.out.printf("%-10s %-5s %n", "  ?", "To display a list of valid flags and descriptions for the search command");
        System.out.printf("%-10s %-5s %n", "  -a", "To search for any resources where any field contains or equals the search criteria");
        System.out.printf("%-10s %-5s %n", "  -a -r", "To search for any resources where any field matches a regex");
        System.out.printf("%-10s %-5s %n", "  -n", "To search for any resource where the name contains or equals the search criteria");
        System.out.printf("%-10s %-5s %n", "  -n -r", "To search for any resource where the name matches a regex");
        System.out.printf("%-10s %-5s %n", "  -p", "To search for any resource where the PID contains or equals the search criteria");
        System.out.printf("%-10s %-5s %n", "  -p -r", "To search for any resource where the PID matches a regex");
        System.out.printf("%-10s %-5s %n", "  -sna", "To search for any resource where the session name contains or equals the search criteria");
        System.out.printf("%-10s %-5s %n", "  -sna -r", "To search for any resource where the session name matches a regex");
        System.out.printf("%-10s %-5s %n", "  -snu", "To search for any resource where the session number contains or equals the search criteria");
        System.out.printf("%-10s %-5s %n", "  -snu -r", "To search for any resource where the session number matches a regex");
        System.out.printf("%-10s %-5s %n", "  -m", "To search for any resource where the memory is >, <, <=, >=, == to a certain value");
        System.out.printf("%-10s %-5s %n", "  -s", "To search for any resource where the status contains or equals the search criteria");
        System.out.printf("%-10s %-5s %n", "  -s -r", "To search for any resource where the status matches a regex");
        System.out.printf("%-10s %-5s %n", "  -u", "To search for any resource where the user name contains or equals the search criteria");
        System.out.printf("%-10s %-5s %n", "  -u -r", "To search for any resource where the user name matches a regex");
        System.out.printf("%-10s %-5s %n", "  -c", "To search for any resource where the CPU time is >, <, <=, >=, == to a certain value");
        System.out.printf("%-10s %-5s %n", "  -w", "To search for any resource where the window title contains or equals the search criteria");
        System.out.printf("%-10s %-5s %n", "  -w -r", "To search for any resource where the window title matches a regex");
    }

    public static void helpView()
    {
        System.out.println();
        System.out.println("View");
        System.out.println("-------");
        System.out.printf("%-10s %-5s %n", "FLAG", "DESCRIPTION");
        System.out.printf("%-10s %-5s %n", "  ?", "To display a list of valid flags and descriptions for the view command");
        System.out.printf("%-10s %-5s %n", "  -a", "To view all of the currently running resources on a system");
        System.out.printf("%-10s %-5s %n", "  -b", "To view all of the resources on a system that have a bad status, high memory usage, or a long CPU time");
        System.out.printf("%-10s %-5s %n", "  -bs", "To view the resources on a system that have a bad status");
        System.out.printf("%-10s %-5s %n", "  -hm", "To view the processes on a system that have high memory usage");
        System.out.printf("%-10s %-5s %n", "  -lc", "To view the processes on a system that have a long CPU time");
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
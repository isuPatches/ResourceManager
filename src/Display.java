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
        System.out.println("Ex5 (search for resources where PID matched 328:");
        System.out.println("      search -p -e 328");
        System.out.println("Ex6 (clean resources over custom memory usage and CPU time:");
        System.out.println("      clean -b -c 90000 00:32:28");
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
        System.out.printf("%-10s %-5s %n", "  -a -e", "Allows user to kill resources where any field matches exactly");
        System.out.printf("%-10s %-5s %n", "  -b", "Allows user to kill resources that are deemed possibly bad");
        System.out.printf("%-10s %-5s %n", "  -b -c", "Allows user to kill resources over custom memory usage and CPU time");
        System.out.printf("%-10s %-5s %n", "  -n", "Allows user to kill resources where name contains criteria");
        System.out.printf("%-10s %-5s %n", "  -n -r", "Allows user to kill resources by name using regex");
        System.out.printf("%-10s %-5s %n", "  -n -e", "Allows user to kill resources where name matches exactly");
        System.out.printf("%-10s %-5s %n", "  -p", "Allows user to kill resources where PID contains criteria");
        System.out.printf("%-10s %-5s %n", "  -p -r", "Allows user to kill resources by PID using regex");
        System.out.printf("%-10s %-5s %n", "  -p -e", "Allows user to kill resources where PID matches exactly");
        System.out.printf("%-10s %-5s %n", "  -sna", "Allows user to kill resources where session name contains criteria");
        System.out.printf("%-10s %-5s %n", "  -sna -r", "Allows user to kill resources by session name using regex");
        System.out.printf("%-10s %-5s %n", "  -sna -e", "Allows user to kill resources where session name matches exactly");
        System.out.printf("%-10s %-5s %n", "  -snu", "Allows user to kill resources where session num contains criteria");
        System.out.printf("%-10s %-5s %n", "  -snu -r", "Allows user to kill resources by session num using regex");
        System.out.printf("%-10s %-5s %n", "  -snu -e", "Allows user to kill resources where session num matches exactly");
        System.out.printf("%-10s %-5s %n", "  -m", "Allows user to kill resources by comparing memory to a value");
        System.out.printf("%-10s %-5s %n", "  -s", "Allows user to kill resources where status contains criteria");
        System.out.printf("%-10s %-5s %n", "  -s -r", "Allows user to kill resources by status using regex");
        System.out.printf("%-10s %-5s %n", "  -s -e", "Allows user to kill resources where status matches exactly");
        System.out.printf("%-10s %-5s %n", "  -u", "Allows user to kill resources where user name contains criteria");
        System.out.printf("%-10s %-5s %n", "  -u -r", "Allows user to kill resources by user name using regex");
        System.out.printf("%-10s %-5s %n", "  -u -e", "Allows user to kill resources where user name matches exactly");
        System.out.printf("%-10s %-5s %n", "  -c", "Allows user to kill resources by comparing CPU time to a value");
        System.out.printf("%-10s %-5s %n", "  -w", "Allows user to kill resources where window title contains criteria");
        System.out.printf("%-10s %-5s %n", "  -w -r", "Allow user to kill resources by window title using regex");
        System.out.printf("%-10s %-5s %n", "  -w -e", "Allow user to kill resources where window title matches exactly");
        System.out.printf("%-10s %-5s %n", "  -bs", "Allows user to kill resources in a bad status");
        System.out.printf("%-10s %-5s %n", "  -hm", "Allows user to kill resources with high memory usage");
        System.out.printf("%-10s %-5s %n", "  -hm -c", "Allows user to kill resources over a custom memory usage");
        System.out.printf("%-10s %-5s %n", "  -lc", "Allows user to kill resources with a long CPU time");
        System.out.printf("%-10s %-5s %n", "  -lc -c", "Allows user to kill resources over a custom CPU time");
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
        System.out.printf("%-10s %-5s %n", "  -a -e", "Search for resources where any field matches exactly");
        System.out.printf("%-10s %-5s %n", "  -n", "Search for resources where name contains given criteria");
        System.out.printf("%-10s %-5s %n", "  -n -r", "Search for resources where the name matches a regex");
        System.out.printf("%-10s %-5s %n", "  -n -e", "Search for resources where the name matches exactly");
        System.out.printf("%-10s %-5s %n", "  -p", "Search for resource where PID contains given criteria");
        System.out.printf("%-10s %-5s %n", "  -p -r", "Search for resources where the PID matches a regex");
        System.out.printf("%-10s %-5s %n", "  -p -e", "Search for resources where the PID matches exactly");
        System.out.printf("%-10s %-5s %n", "  -sna", "Search for resources where session name contains given criteria");
        System.out.printf("%-10s %-5s %n", "  -sna -r", "Search for resources where the session name matches a regex");
        System.out.printf("%-10s %-5s %n", "  -sna -e", "Search for resources where the session name matches exactly");
        System.out.printf("%-10s %-5s %n", "  -snu", "Search for resources where session num contains given criteria");
        System.out.printf("%-10s %-5s %n", "  -snu -r", "Search for resources where session num matches a regex");
        System.out.printf("%-10s %-5s %n", "  -snu -e", "Search for resources where session num matches exactly");
        System.out.printf("%-10s %-5s %n", "  -m", "Search for resources by comparing memory usage to a value");
        System.out.printf("%-10s %-5s %n", "  -s", "Search for resources where status contains given criteria");
        System.out.printf("%-10s %-5s %n", "  -s -r", "Search for resources where the status matches a regex");
        System.out.printf("%-10s %-5s %n", "  -s -e", "Search for resources where the status matches exactly");
        System.out.printf("%-10s %-5s %n", "  -u", "Search for resources where user name contains given criteria");
        System.out.printf("%-10s %-5s %n", "  -u -r", "Search for resources where the user name matches a regex");
        System.out.printf("%-10s %-5s %n", "  -u -e", "Search for resources where the user name matches exactly");
        System.out.printf("%-10s %-5s %n", "  -c", "Search for resources by comparing CPU time to a value");
        System.out.printf("%-10s %-5s %n", "  -w", "Search for resources where window title contains given criteria");
        System.out.printf("%-10s %-5s %n", "  -w -r", "Search for resources where the window title matches a regex");
        System.out.printf("%-10s %-5s %n", "  -w -r", "Search for resources where the window title matches exactly");
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
        System.out.printf("%-10s %-5s %n", "  -b -c", "Displays resources with over a custom memory usage and CPU time");
        System.out.printf("%-10s %-5s %n", "  -bs", "Displays resources that currently seem to have a bad status");
        System.out.printf("%-10s %-5s %n", "  -hm", "Displays resources that currently have high memory usage");
        System.out.printf("%-10s %-5s %n", "  -hm -c", "Displays resources that currently have over a custom memory usage");
        System.out.printf("%-10s %-5s %n", "  -lc", "Displays resources that have a long CPU time");
        System.out.printf("%-10s %-5s %n", "  -lc -c", "Displays resources that have over a custom CPU time");
    }

    public static void resources(ArrayList<Resource> arrResources, Boolean bWithIndex)
    {
        if(arrResources.size() > 0)
        {
            Integer intPagesNeeded = arrResources.size() / 10;
            Integer intRemainder = arrResources.size() - (intPagesNeeded * 10);
            if (intRemainder > 0)
            {
                intPagesNeeded++;
            }

            Resource[][] arrPages = new Resource[intPagesNeeded][10];

            Integer intPageIndex = 0;
            Integer intPagePlacementPointer = 0;
            Integer intResourceIndex = 0;
            for(Resource resource : arrResources)
            {
                arrPages[intPageIndex][intPagePlacementPointer] = resource;
                intPagePlacementPointer++;
                intResourceIndex++;
                if(intResourceIndex % 10  == 0)
                {
                    intPageIndex++;
                    intPagePlacementPointer = 0;
                }
            }

            System.out.println();
            intResourceIndex = 0;
            String strInput = "";
            for(Resource[] page : arrPages)
            {
                for (int y = 0; y < page.length; y++)
                {
                    if (page[y] != null)
                    {
                        if (bWithIndex)
                        {
                            System.out.println("[" + intResourceIndex + "] " + page[y].toString());
                        }
                        else
                        {
                            System.out.println(page[y].toString());
                        }
                    }
                    intResourceIndex++;
                }
                if(!strInput.equals("a") && !strInput.equals("all") && arrPages.length > 1)
                {
                    System.out.println("...hit return to view next page or 'all' / 'a' to view the remaining entries...");
                    strInput = Input.waitForReturn();
                }
            }
        }
        else
        {
            System.out.println();
            System.out.println("No resources to display");
        }
    }
}
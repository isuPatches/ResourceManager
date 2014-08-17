import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

class Search
{
    public static ArrayList<Resource> all()
    {
        ArrayList<Resource> arrAllResources = new ArrayList<>(0);

        try
        {
            Runtime runtime = Runtime.getRuntime();
            Process proc = runtime.exec("tasklist.exe /v");

            ArrayList<String> arrResources = new ArrayList<>(0);
            String strProcLine;
            BufferedReader input = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            while ((strProcLine = input.readLine()) != null)
            {
                if (!strProcLine.contains("PID") && !strProcLine.contains("=") && strProcLine.length() > 0)
                {
                    arrResources.add(strProcLine);
                }
            }

            arrAllResources = ResourceManager.parse(arrResources);

            input.close();
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }

        return arrAllResources;
    }

    public static ArrayList<Resource> all(ArrayList<Resource> arrAllResources, String strSearchCriteria, Boolean bUseRegEx, Boolean bExactMatch)
    {
        ArrayList<Resource> arrResources = new ArrayList<>(0);

        for(Resource resource : arrAllResources)
        {
            Boolean bCompareResult = compare(resource.getName(), strSearchCriteria, bUseRegEx, bExactMatch);
            if(bCompareResult)
            {
                arrResources.add(resource);
            }

            bCompareResult = compare(resource.getPID(), strSearchCriteria, bUseRegEx, bExactMatch);
            if(bCompareResult)
            {
                arrResources.add(resource);
            }

            bCompareResult = compare(resource.getSessionName(), strSearchCriteria, bUseRegEx, bExactMatch);
            if(bCompareResult)
            {
                arrResources.add(resource);
            }

            bCompareResult = compare(resource.getSessionNum(), strSearchCriteria, bUseRegEx, bExactMatch);
            if(bCompareResult)
            {
                arrResources.add(resource);
            }

            bCompareResult = compare(resource.getStatus(), strSearchCriteria, bUseRegEx, bExactMatch);
            if(bCompareResult)
            {
                arrResources.add(resource);
            }

            bCompareResult = compare(resource.getUserName(), strSearchCriteria, bUseRegEx, bExactMatch);
            if(bCompareResult)
            {
                arrResources.add(resource);
            }

            bCompareResult = compare(resource.getWindowTitle(), strSearchCriteria, bUseRegEx, bExactMatch);
            if(bCompareResult)
            {
                arrResources.add(resource);
            }
        }

        ArrayList<Resource> arrResourcesTemp = new ArrayList<>(0);

        if(strSearchCriteria.contains(">") || strSearchCriteria.contains("<") || strSearchCriteria.contains(">=") || strSearchCriteria.contains("<=") || strSearchCriteria.contains("="))
        {
            if (strSearchCriteria.replaceAll(",", "").matches("^[0-9]\\d*$"))
            {
                Integer intMemUsage = Integer.parseInt(strSearchCriteria.replaceAll(",", ""));
                if (strSearchCriteria.startsWith(">="))
                {
                    arrResourcesTemp = Search.byMemUsage(arrAllResources, intMemUsage, ">=");
                }
                else if (strSearchCriteria.startsWith("<="))
                {
                    arrResourcesTemp = Search.byMemUsage(arrAllResources, intMemUsage, "<=");
                }
                else if (strSearchCriteria.startsWith("<"))
                {
                    arrResourcesTemp = Search.byMemUsage(arrAllResources, intMemUsage, "<");
                }
                else if (strSearchCriteria.startsWith(">"))
                {
                    arrResourcesTemp = Search.byMemUsage(arrAllResources, intMemUsage, ">");
                }
                else if (strSearchCriteria.startsWith("="))
                {
                    arrResourcesTemp = Search.byMemUsage(arrAllResources, intMemUsage, "=");
                }
            }
        }

        for(Resource resource : arrResourcesTemp)
        {
            arrResources.add(resource);
        }

        if(strSearchCriteria.contains(">") || strSearchCriteria.contains("<") || strSearchCriteria.contains(">=") || strSearchCriteria.contains("<=") || strSearchCriteria.contains("="))
        {
            if (strSearchCriteria.replaceAll("[<>=]", "").matches("([0-9]+):([0-5][0-9]):([0-5][0-9])"))
            {
                if (strSearchCriteria.startsWith(">="))
                {
                    arrResourcesTemp = Search.byCPUTime(arrAllResources, strSearchCriteria.replaceAll("[<>=]", ""), ">=");
                }
                else if (strSearchCriteria.startsWith("<="))
                {
                    arrResourcesTemp = Search.byCPUTime(arrAllResources, strSearchCriteria.replaceAll("[<>=]", ""), "<=");
                }
                else if (strSearchCriteria.startsWith("<"))
                {
                    arrResourcesTemp = Search.byCPUTime(arrAllResources, strSearchCriteria.replaceAll("[<>=]", ""), "<");
                }
                else if (strSearchCriteria.startsWith(">"))
                {
                    arrResourcesTemp = Search.byCPUTime(arrAllResources, strSearchCriteria.replaceAll("[<>=]", ""), ">");
                }
                else if (strSearchCriteria.startsWith("="))
                {
                    arrResourcesTemp = Search.byCPUTime(arrAllResources, strSearchCriteria.replaceAll("[<>=]", ""), "=");
                }
            }
        }

        for(Resource resource : arrResourcesTemp)
        {
            arrResources.add(resource);
        }

        return arrResources;
    }

    public static ArrayList<Resource> bad(ArrayList<Resource> arrAllResources, Integer intMemUsageVal)
    {
        ArrayList<Resource> arrBadResources = new ArrayList<>(0);
        ArrayList<Resource> arrBadStatusResources = Search.badStatus(arrAllResources);
        ArrayList<Resource> arrHighMemResources = Search.highMemUsage(arrAllResources, intMemUsageVal);
        ArrayList<Resource> arrLongCPUTimeResources = Search.longCPUTime(arrAllResources);

        for(Resource resource : arrBadStatusResources)
        {
            arrBadResources.add(resource);
        }
        for(Resource resource : arrHighMemResources)
        {
            arrBadResources.add(resource);
        }
        for(Resource resource : arrLongCPUTimeResources)
        {
            arrBadResources.add(resource);
        }

        return arrBadResources;
    }

    public static ArrayList<Resource> byName(ArrayList<Resource> arrAllResources, String strSearchCriteria, Boolean bUseRegEx, Boolean bExactMatch)
    {
        ArrayList<Resource> arrResources = new ArrayList<>(0);

        for(Resource resource : arrAllResources)
        {
            Boolean bCompareResult = compare(resource.getName(), strSearchCriteria, bUseRegEx, bExactMatch);
            if(bCompareResult)
            {
                arrResources.add(resource);
            }
        }

        return arrResources;
    }

    public static ArrayList<Resource> byPID(ArrayList<Resource> arrAllResources, String strSearchCriteria, Boolean bUseRegEx, Boolean bExactMatch)
    {
        ArrayList<Resource> arrResources = new ArrayList<>(0);

        for(Resource resource : arrAllResources)
        {
            Boolean bCompareResult = compare(resource.getPID(), strSearchCriteria, bUseRegEx, bExactMatch);
            if(bCompareResult)
            {
                arrResources.add(resource);
            }
        }

        return arrResources;
    }

    public static ArrayList<Resource> bySessionName(ArrayList<Resource> arrAllResources, String strSearchCriteria, Boolean bUseRegEx, Boolean bExactMatch)
    {
        ArrayList<Resource> arrResources = new ArrayList<>(0);

        for(Resource resource : arrAllResources)
        {
            Boolean bCompareResult = compare(resource.getSessionName(), strSearchCriteria, bUseRegEx, bExactMatch);
            if(bCompareResult)
            {
                arrResources.add(resource);
            }
        }

        return arrResources;
    }

    public static ArrayList<Resource> bySessionNum(ArrayList<Resource> arrAllResources, String strSearchCriteria, Boolean bUseRegEx, Boolean bExactMatch)
    {
        ArrayList<Resource> arrResources = new ArrayList<>(0);

        for(Resource resource : arrAllResources)
        {
            Boolean bCompareResult = compare(resource.getSessionNum(), strSearchCriteria, bUseRegEx, bExactMatch);
            if(bCompareResult)
            {
                arrResources.add(resource);
            }
        }

        return arrResources;
    }

    public static ArrayList<Resource> byMemUsage(ArrayList<Resource> arrAllResources, Integer intSearchCriteria, String strOperator)
    {
        ArrayList<Resource> arrResources = new ArrayList<>(0);

        for(Resource resource : arrAllResources)
        {
            switch(strOperator)
            {
                case "<":
                    if(resource.getMemUsage() < intSearchCriteria)
                    {
                        arrResources.add(resource);
                    }
                    break;
                case ">":
                    if(resource.getMemUsage() > intSearchCriteria)
                    {
                        arrResources.add(resource);
                    }
                    break;
                case "<=":
                    if(resource.getMemUsage() >= intSearchCriteria)
                    {
                        arrResources.add(resource);
                    }
                    break;
                case ">=":
                    if(resource.getMemUsage() <= intSearchCriteria)
                    {
                        arrResources.add(resource);
                    }
                    break;
                case "=":
                    if(resource.getMemUsage().equals(intSearchCriteria))
                    {
                        arrResources.add(resource);
                    }
                    break;
            }
        }

        return arrResources;
    }

    public static ArrayList<Resource> byStatus(ArrayList<Resource> arrAllResources, String strSearchCriteria, Boolean bUseRegEx, Boolean bExactMatch)
    {
        ArrayList<Resource> arrResources = new ArrayList<>(0);

        for(Resource resource : arrAllResources)
        {
            Boolean bCompareResult = compare(resource.getStatus(), strSearchCriteria, bUseRegEx, bExactMatch);
            if(bCompareResult)
            {
                arrResources.add(resource);
            }
        }

        return arrResources;
    }

    public static ArrayList<Resource> byUserName(ArrayList<Resource> arrAllResources, String strSearchCriteria, Boolean bUseRegEx, Boolean bExactMatch)
    {
        ArrayList<Resource> arrResources = new ArrayList<>(0);

        for(Resource resource : arrAllResources)
        {
            Boolean bCompareResult = compare(resource.getUserName(), strSearchCriteria, bUseRegEx, bExactMatch);
            if(bCompareResult)
            {
                arrResources.add(resource);
            }
        }

        return arrResources;
    }

    public static ArrayList<Resource> byCPUTime(ArrayList<Resource> arrAllResources, String strTime, String strOperator)
    {
        ArrayList<Resource> arrResources = new ArrayList<>(0);
        SimpleDateFormat dateFormatter = new SimpleDateFormat("hh:mm:ss");

        for(Resource resource : arrAllResources)
        {
            try
            {
                Date time = dateFormatter.parse(resource.getCPUTime());
                Date timeToCompareTo = dateFormatter.parse(strTime);

                switch(strOperator)
                {
                    case "<":
                        if(time.before(timeToCompareTo))
                        {
                            arrResources.add(resource);
                        }
                        break;
                    case ">":
                        if(time.after(timeToCompareTo))
                        {
                            arrResources.add(resource);
                        }
                        break;
                    case "<=":
                        if(time.before(timeToCompareTo) || time.equals(timeToCompareTo))
                        {
                            arrResources.add(resource);
                        }
                        break;
                    case ">=":
                        if(time.after(timeToCompareTo) || time.equals(timeToCompareTo))
                        {
                            arrResources.add(resource);
                        }
                        break;
                    case "=":
                        if(time.equals(timeToCompareTo))
                        {
                            arrResources.add(resource);
                        }
                        break;
                }
            }
            catch (Exception exception)
            {
                // Do nothing
            }
        }

        return arrResources;
    }

    public static ArrayList<Resource> byWindowTitle(ArrayList<Resource> arrAllResources, String strSearchCriteria, Boolean bUseRegEx, Boolean bExactMatch)
    {
        ArrayList<Resource> arrResources = new ArrayList<>(0);

        for(Resource resource : arrAllResources)
        {
            Boolean bCompareResult = compare(resource.getWindowTitle(), strSearchCriteria, bUseRegEx, bExactMatch);
            if(bCompareResult)
            {
                arrResources.add(resource);
            }
        }

        return arrResources;
    }

    public static ArrayList<Resource> badStatus(ArrayList<Resource> arrAllResources)
    {
        ArrayList<Resource> arrResources = new ArrayList<>(0);

        for(Resource resource : arrAllResources)
        {
            if(resource.getStatus().equals("Not Responding") || resource.getStatus().equals("Suspended"))
            {
                arrResources.add(resource);
            }
        }

        return arrResources;
    }

    public static ArrayList<Resource> highMemUsage(ArrayList<Resource> arrAllResources, Integer intMemUsageVal)
    {
        ArrayList<Resource> arrResources = new ArrayList<>(0);

        for(Resource resource : arrAllResources)
        {
            if(resource.getMemUsage() > intMemUsageVal)
            {
                arrResources.add(resource);
            }
        }

        return arrResources;
    }

    public static ArrayList<Resource> longCPUTime(ArrayList<Resource> arrAllResources)
    {
        ArrayList<Resource> arrResources = new ArrayList<>(0);

        for(Resource resource : arrAllResources)
        {
            String[] temp = resource.getCPUTime().split(":");
            if(Integer.parseInt(temp[0].trim()) > 1)
            {
                arrResources.add(resource);
            }
            else if(Integer.parseInt(temp[1].trim()) > 30)
            {
                arrResources.add(resource);
            }
        }

        return arrResources;
    }

    private static Boolean checkRegExSyntax(String strSearchCriteria)
    {
        Boolean bValidRegEx = true;
        try
        {
            Pattern pattern = Pattern.compile(strSearchCriteria);
        }
        catch (PatternSyntaxException exception)
        {
            bValidRegEx = false;
        }

        return bValidRegEx;
    }

    private static boolean compare(String strCompare, String strSearchCriteria, Boolean bUseRegEx, Boolean bExactMatch)
    {
        Boolean bCompareResult = false;
        Boolean bValidRegEx;

        if(bUseRegEx)
        {
            bValidRegEx = checkRegExSyntax(strSearchCriteria);
            if(bValidRegEx)
            {
                if(strCompare.matches(strSearchCriteria))
                {
                    bCompareResult = true;
                }
            }
        }
        else
        {
            if(bExactMatch)
            {
                if(strCompare.equals(strSearchCriteria))
                {
                    bCompareResult = true;
                }
            }
            else
            {
                if (strCompare.trim().toLowerCase().equals(strSearchCriteria.trim().toLowerCase()) || strCompare.trim().toLowerCase().contains(strSearchCriteria.trim().toLowerCase()))
                {
                    bCompareResult = true;
                }
            }
        }

        return bCompareResult;
    }
}